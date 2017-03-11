package com.ksmk.back;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by mdcow on 02.03.17.
 */
public class HibernateSetup {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("/hbnt/hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
