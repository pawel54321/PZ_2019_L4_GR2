package com.gr2lab4.projekt.Entities.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {

    private Session currentSession;
    private Transaction currentTransaction;
    private String  pathConfiguration;
    private static ServiceRegistry serviceRegistry;
    private static String mainConfiguration;

    public HibernateUtil(){
        this.pathConfiguration = mainConfiguration;
    }
    
    public static void setMainConfiguration(String configurationPath){
        mainConfiguration = configurationPath;
    }
    
    public static String getMainConfiguration(){
        return mainConfiguration;
    }
    
    public static ServiceRegistry getServiceRegistry() {
        return HibernateUtil.serviceRegistry;
    }
    
    public String getPathConfiguration() {
        return pathConfiguration;
    }

    public void setPathConfiguration(String path) {
        pathConfiguration = path;
    }

    private static SessionFactory getSessionFactory(String pathConfiguration) {
        Configuration configuration = new Configuration()
                .configure("cfg/hibernate.cfg.xml");

        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();

        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory(pathConfiguration).openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory(pathConfiguration).openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

}
