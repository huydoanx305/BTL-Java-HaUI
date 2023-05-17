package com.hit.admission.controller;

import static com.hit.admission.utils.SessionUtil.close;
import static com.hit.admission.utils.SessionUtil.getSession;
import static com.hit.admission.utils.SessionUtil.rollback;
import java.math.BigInteger;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Huy Doan
 */
public class MajorDetailController {
    
    private final Logger logger = Logger.getLogger(MajorDetailController.class);
    
    public Integer getYearMinMajor() {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createNativeQuery("SELECT YEAR(MIN(md.created_date)) FROM major_details md");
            BigInteger year = (BigInteger) query.uniqueResult();
            tx.commit();
            if(ObjectUtils.isEmpty(year)) {
                return 2016;
            } else {
                return year.intValue();
            }
        } catch (Exception e) {
            rollback(tx);
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            close(session);
        }
        return null;
    }
}
