package com.hit.admission.controller;

import com.hit.admission.base.BaseDAO;
import com.hit.admission.dto.MajorDetailDTO;
import com.hit.admission.mapper.MajorMapper;
import static com.hit.admission.utils.SessionUtil.close;
import static com.hit.admission.utils.SessionUtil.getSession;
import static com.hit.admission.utils.SessionUtil.rollback;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.log4j.Logger;
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

    private final Logger logger = Logger.getLogger(MajorDetailController.class);

    private final MajorMapper majorMapper;

    public MajorDetailController() {
        this.majorMapper = Mappers.getMapper(MajorMapper.class);
    }

    public List<MajorDetailDTO> getMajorDetailsForAdmin(Integer year, String keyword) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            StringBuilder stringQuery = new StringBuilder();
            stringQuery.append("SELECT m.id, m.code, m.name, GROUP_CONCAT(b.code SEPARATOR ',') AS block_codes, md.bench_mark, md.amount_student_received FROM majors m ");
            stringQuery.append("LEFT JOIN major_details md ON m.id = md.major_id ");
            stringQuery.append("INNER JOIN major_block mb ON m.id = mb.major_id  ");
            stringQuery.append("INNER JOIN blocks b ON mb.block_id = b.id  ");
            stringQuery.append("WHERE (YEAR(md.created_date) IS NULL OR YEAR(md.created_date) = :year) ");
            stringQuery.append("AND (COALESCE(:keyword, '') = '' OR m.code LIKE CONCAT('%', :keyword, '%') OR m.name LIKE CONCAT('%', :keyword, '%')) ");
            stringQuery.append("GROUP BY m.id ");
            Query query = session.createNativeQuery(stringQuery.toString());
            query.setParameter("year", year, IntegerType.INSTANCE);
            query.setParameter("keyword", keyword, StringType.INSTANCE);
            List<Object[]> queryResults = query.getResultList();
            tx.commit();
            return majorMapper.objectsToMajorDetailDtos(queryResults);
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
            return majorMapper.objectsToMajorDetailDtos(queryResults);
        } catch (Exception e) {
            rollback(tx);
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            close(session);
        }
        return null;
    }

    private List<Object[]> getResultMajors(Session session, Integer year, String keyword) {
        Integer nowYear = LocalDate.now().getYear();
        StringBuilder stringQuery = new StringBuilder();
        stringQuery.append("SELECT m.id, m.code, m.name, GROUP_CONCAT(b.code SEPARATOR ',') AS block_codes, md.bench_mark, md.amount_student_received FROM majors m ");
        stringQuery.append("INNER JOIN major_block mb ON m.id = mb.major_id  ");
        stringQuery.append("INNER JOIN blocks b ON mb.block_id = b.id  ");
        if (nowYear.equals(year)) {
            stringQuery.append("INNER JOIN major_details md ON m.id = md.major_id ");
            stringQuery.append("WHERE YEAR(md.created_date) = :year ");
        } else {
            stringQuery.append("LEFT JOIN major_details md ON m.id = md.major_id ");
            stringQuery.append("WHERE (YEAR(md.created_date) IS NULL OR YEAR(md.created_date) = :year) ");
        }
        stringQuery.append("AND (COALESCE(:keyword, '') = '' OR m.code LIKE CONCAT('%', :keyword, '%') OR m.name LIKE CONCAT('%', :keyword, '%')) ");
        stringQuery.append("GROUP BY m.id ");
        Query query = session.createNativeQuery(stringQuery.toString());
        query.setParameter("year", year, IntegerType.INSTANCE);
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
}
