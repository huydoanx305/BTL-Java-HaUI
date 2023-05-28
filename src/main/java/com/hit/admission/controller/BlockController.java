package com.hit.admission.controller;

import com.hit.admission.base.BaseDAO;
import com.hit.admission.model.Block;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class BlockController extends BaseDAO {

    public List<Block> getBlocks() throws Exception {
        return (List<Block>) findAll(Block.class);
    }

    public List<Block> getBlocksByCode(List<String> codes) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createQuery("FROM Block WHERE code IN :codes", Block.class);
            query.setParameter("codes", codes);
            tx.commit();
            return query.getResultList();
        } catch (Exception e) {
            rollback(tx);
            e.printStackTrace();
            return null;
        } finally {
            close(session);
        }

    }

}
