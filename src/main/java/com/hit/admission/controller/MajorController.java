package com.hit.admission.controller;

import com.hit.admission.base.BaseDAO;
import com.hit.admission.dto.MajorDTO;
import com.hit.admission.mapper.MajorMapper;
import java.time.LocalDate;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Huy Doan
 */
public class MajorController extends BaseDAO {

    private final Logger logger = Logger.getLogger(MajorController.class);

    private final MajorMapper majorMapper;

    public MajorController() {
        this.majorMapper = Mappers.getMapper(MajorMapper.class);
    }

    public List<MajorDTO> getMajors(Integer year, String keyword) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            List<Object[]> queryResults = getResultMajors(session, year, keyword);
            tx.commit();
            return majorMapper.objectsToMajorDtos(queryResults);
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
        if (nowYear.equals(year)) {
            StringBuilder stringQuery = new StringBuilder();
            stringQuery.append("SELECT m.id, m.code, m.name, md.bench_mark, md.amount_student_received FROM majors m ");
            stringQuery.append("INNER JOIN major_details md ON m.id = md.major_id ");
            stringQuery.append("WHERE YEAR(md.created_date) IS NULL OR YEAR(md.created_date) = :year ");
            Query query = session.createNativeQuery(stringQuery.toString());
            query.setParameter("year", year);
            return query.getResultList();
        } else {
            StringBuilder stringQuery = new StringBuilder();
            stringQuery.append("SELECT m.id, m.code, m.name, md.bench_mark, md.amount_student_received FROM majors m ");
            stringQuery.append("LEFT JOIN major_details md ON m.id = md.major_id ");
            stringQuery.append("WHERE YEAR(md.created_date) IS NULL OR YEAR(md.created_date) = :year ");
            Query query = session.createNativeQuery(stringQuery.toString());
            query.setParameter("year", year);
            return query.getResultList();
        }
    }

}
