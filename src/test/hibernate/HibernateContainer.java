package fr.fanaticstudio.matthis974jump.botsurveillancediscord.hibernate;

import fr.fanaticstudio.matthis974jump.botsurveillancediscord.corebot.InitialisableObject;
import org.hibernate.SessionFactory;

public class HibernateContainer implements InitialisableObject {

    private SessionFactory sessionFactory;

    public HibernateContainer(String url,String user,String password,int port){

    }

    @Override
    public void postInit() {

    }

    @Override
    public void init() {

    }

    @Override
    public boolean isLoaded() {

        return false;
    }
}
