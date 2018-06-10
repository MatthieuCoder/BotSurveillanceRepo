package fr.fanaticstudio.matthis974jump.botsurveillancediscord.hibernate;

import org.hibernate.Session;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Main mgr = new Main();
        System.setProperty("hibernate.hikari.dataSource.password","xd");
        System.setProperty("hibernate.hikari.dataSource.user","xd");
        System.setProperty("hibernate.hikari.dataSource.url","jdbc:mariadb://localhost:3307/botsdiscord");

        if (args[0].equals("store")) {
            mgr.createAndStoreEvent("My Event", new Date());
        }

        HibernateUtil.getSessionFactory().close();
    }

    private void createAndStoreEvent(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        session.save(theEvent);

        session.getTransaction().commit();
    }

}
