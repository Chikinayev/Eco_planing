package kz.arb.ecoplaning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

public class Event {

    void init() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Event.class);
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()){
            Session session = sessionFactory.getCurrentSession();







        }
    }


}
