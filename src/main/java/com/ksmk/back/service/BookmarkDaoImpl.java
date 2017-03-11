package com.ksmk.back.service;

import com.ksmk.back.HibernateSetup;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by mdcow on 02.03.17.
 */
public class BookmarkDaoImpl implements BookmarkDao {

    private final Logger logger = Logger.getLogger(getClass().getName());
    private SessionFactory sessionFactory;

    public BookmarkDaoImpl() {
        sessionFactory = HibernateSetup.getSessionFactory();
    }

    public BookmarkDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void create(Bookmark bookmark) {
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.persist(bookmark);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            logger.info("Create error: " + ex.getLocalizedMessage());
            if (tx != null) { tx.rollback(); }
        }  finally {
            if (session != null) { session.close(); }
        }
    }

    @Override
    public void delete(Bookmark bookmark) {
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.delete(bookmark);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            logger.info("Delete error: " + ex.getLocalizedMessage());
            if (tx != null) { tx.rollback(); }
        }  finally {
            if (session != null) { session.close(); }
        }
    }

    @Override
    public List<Bookmark> getAll() {
        List<Bookmark> result = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            result = (List<Bookmark>) session.createCriteria(Bookmark.class).list();
        } catch (HibernateException ex) {
            logger.info("Get all error: " + ex.getLocalizedMessage());
        }  finally {
            if (session != null) { session.close(); }
        }
        return result;
    }

    @Override
    public Bookmark getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Bookmark bookmark = session.get(Bookmark.class, id);
            return bookmark;
        } catch (HibernateException ex) {
            logger.info("Get by id error: " + ex.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public void update(Bookmark bookmark) {
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(bookmark);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            logger.info("Update error: " + ex.getLocalizedMessage());
            if (tx != null) { tx.rollback(); }
        }  finally {
            if (session != null) { session.close(); }
        }
    }

    @Override
    public List<Bookmark> selectPage(int start, int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long countAll() {
        try (Session session = sessionFactory.openSession()) {
            return (Long) session.
                    createQuery("select count(*) from Bookmark").
                    uniqueResult();
        } catch (HibernateException ex) {
            logger.info("getCount error: " + ex.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public Integer deleteAll() {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            int result = session.
                    createQuery("delete from Bookmark").
                    executeUpdate();
            tx.commit();
            return result;
        } catch (HibernateException ex) {
            logger.info("deleteAll error: " + ex.getLocalizedMessage());
            if (tx != null) { tx.rollback(); }
            return null;
        } finally {
            if (session != null) { session.close(); }
        }
    }



}
