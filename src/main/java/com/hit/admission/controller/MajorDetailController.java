package com.hit.admission.controller;

import com.hit.admission.base.BaseDAO;
import com.hit.admission.constants.MajorDetailsStatus;
import com.hit.admission.dto.CommonResponse;
import com.hit.admission.dto.MajorDetailDTO;
import com.hit.admission.mapper.MajorDetailMapper;
import com.hit.admission.model.Major;
import com.hit.admission.model.MajorDetail;
import static com.hit.admission.utils.SessionUtil.close;
import static com.hit.admission.utils.SessionUtil.getSession;
import static com.hit.admission.utils.SessionUtil.rollback;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Huy Doan
 */
public class MajorDetailController extends BaseDAO {
    
    private final Logger logger = LogManager.getLogger(MajorDetailController.class);
    
    private final MajorController majorController;
    
    private final MajorDetailMapper majorDetailMapper;
    
    public MajorDetailController() {
        this.majorController = new MajorController();
        this.majorDetailMapper = Mappers.getMapper(MajorDetailMapper.class);
    }
    
    public List<MajorDetailDTO> getMajorDetailsForAdmin(Integer year, String keyword) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            StringBuilder stringQuery = new StringBuilder();
            stringQuery.append("SELECT md.id, m.code, m.name, GROUP_CONCAT(b.code SEPARATOR ',') AS block_codes, ");
            stringQuery.append("md.bench_mark, md.amount_student_received, m.id AS major_id  FROM majors m ");
            stringQuery.append("LEFT JOIN major_details md ON m.id = md.major_id AND YEAR(md.created_date) = :year ");
            stringQuery.append("LEFT JOIN major_block mb ON m.id = mb.major_id  ");
            stringQuery.append("LEFT JOIN blocks b ON mb.block_id = b.id  ");
            stringQuery.append("WHERE (COALESCE(:keyword, '') = '' OR m.code LIKE CONCAT('%', :keyword, '%') ");
            stringQuery.append("OR m.name LIKE CONCAT('%', :keyword, '%'))");
            stringQuery.append("GROUP BY m.id ");
            Query query = session.createNativeQuery(stringQuery.toString());
            query.setParameter("year", year, IntegerType.INSTANCE);
            query.setParameter("keyword", keyword, StringType.INSTANCE);
            List<Object[]> queryResults = query.getResultList();
            tx.commit();
            return majorDetailMapper.objectsToMajorDetailDtos(queryResults);
        } catch (Exception e) {
            rollback(tx);
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            close(session);
        }
        return null;
    }
    
    public List<MajorDetailDTO> getMajorDetails(Integer year, String keyword) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            List<Object[]> queryResults = getResultMajors(session, year, keyword);
            tx.commit();
            return majorDetailMapper.objectsToMajorDetailDtos(queryResults);
        } catch (Exception e) {
            rollback(tx);
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            close(session);
        }
        return null;
    }
    
    public List<Object[]> getResultMajors(Session session, Integer year, String keyword) {
        StringBuilder stringQuery = new StringBuilder();
        stringQuery.append("SELECT md.id, m.code, m.name, GROUP_CONCAT(b.code SEPARATOR ',') AS block_codes, ");
        stringQuery.append("md.bench_mark, md.amount_student_received, m.id AS major_id FROM majors m ");
        stringQuery.append("LEFT JOIN major_block mb ON m.id = mb.major_id  ");
        stringQuery.append("LEFT JOIN blocks b ON mb.block_id = b.id  ");
        stringQuery.append("LEFT JOIN major_details md ON m.id = md.major_id ");
        stringQuery.append("AND YEAR(md.created_date) = :year AND md.status = :status ");
        stringQuery.append("WHERE (COALESCE(:keyword, '') = '' OR m.code LIKE CONCAT('%', :keyword, '%') ");
        stringQuery.append("OR m.name LIKE CONCAT('%', :keyword, '%'))");
        stringQuery.append("GROUP BY m.id ");
        Query query = session.createNativeQuery(stringQuery.toString());
        query.setParameter("year", year, IntegerType.INSTANCE);
        query.setParameter("status", MajorDetailsStatus.PUBLIC.toString(), StringType.INSTANCE);
        query.setParameter("keyword", keyword, StringType.INSTANCE);
        return query.getResultList();
    }
    
    public Integer getYearMinMajor() {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createNativeQuery("SELECT YEAR(MIN(md.created_date)) FROM major_details md");
            BigInteger year = (BigInteger) query.uniqueResult();
            tx.commit();
            if (ObjectUtils.isEmpty(year)) {
                return 2016;
            } else {
                return year.intValue();
            }
        } catch (Exception e) {
            rollback(tx);
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            close(session);
        }
        return null;
    }
    
    public MajorDetail getMajorDetailNowByMajorId(Integer majorId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            Integer yearNow = LocalDate.now().getYear();
            String queryString = "SELECT * FROM major_details "
                    + "WHERE major_id = :majorId AND YEAR(created_date) = :yearNow";
            Query query = session.createNativeQuery(queryString, MajorDetail.class);
            query.setParameter("majorId", majorId);
            query.setParameter("yearNow", yearNow);
            List<MajorDetail> majors = query.getResultList();
            tx.commit();
            if (CollectionUtils.isEmpty(majors)) {
                return null;
            }
            MajorDetail major = majors.get(0);
            return major;
        } catch (Exception e) {
            rollback(tx);
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            close(session);
        }
    }
    
    public CommonResponse createOrUpdateMajorDetails(List<MajorDetailDTO> majorDetailDTOs) {
        try {
            majorDetailDTOs.forEach(majorDetailDTO -> {
                createOrUpdateMajorDetail(majorDetailDTO);
            });
            return new CommonResponse(Boolean.TRUE, "Cập nhật thông tin chi tiết chuyên ngành thành công");
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new CommonResponse(Boolean.FALSE, "Hệ thống đã xảy ra lỗi. Vui lòng quay lại sau!");
        }
    }
    
    public CommonResponse createOrUpdateMajorDetail(MajorDetailDTO majorDetailDTO) {
        try {
            Major major = majorController.getMajorByCode(majorDetailDTO.getCode());
            MajorDetail majorDetail = getMajorDetailNowByMajorId(majorDetailDTO.getMajorId());
            if (ObjectUtils.isEmpty(majorDetail)) {
                majorDetail = majorDetailMapper.toMajorDetail(majorDetailDTO);
            } else {
                majorDetailMapper.updateMajorDetailFromDTO(majorDetailDTO, majorDetail);
            }
            majorDetail.setStatus(MajorDetailsStatus.IMPORT);
            majorDetail.setMajor(major);
            save(majorDetail);
            return new CommonResponse(Boolean.TRUE, "Cập nhật thông tin chi tiết chuyên ngành thành công");
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new CommonResponse(Boolean.FALSE, "Hệ thống đã xảy ra lỗi. Vui lòng quay lại sau!");
        }
    }
    
    public CommonResponse deleteMajorDetailById(Integer majorDetailId) throws Exception {
        MajorDetail majorDetail = (MajorDetail) findById(MajorDetail.class, majorDetailId);
        if (ObjectUtils.isEmpty(majorDetail)) {
            return new CommonResponse(Boolean.FALSE, "Không tìm thấy chi tiết chuyên ngành có id " + majorDetailId);
        }
        delete(majorDetail);
        return new CommonResponse(Boolean.TRUE, "Xóa chi tiết chuyên ngành thành công");
    }
    
}
