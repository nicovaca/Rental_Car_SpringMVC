package com.esempio.RentalCar.config;

import com.esempio.RentalCar.entities.Prenotazione;
import com.esempio.RentalCar.entities.Utente;
import com.esempio.RentalCar.entities.Veicolo;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import java.util.Properties;


@org.springframework.context.annotation.Configuration
@ComponentScan({"com.esempio.RentalCar.config"})
@PropertySource(value = {"classpath:application.properties"})
public class HibernateConfig {
    private static SessionFactory sessionFactory;

    @Bean
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();


                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/rentalcar?serverTimezone=UTC");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");


                //C3p0 properties
                settings.put(Environment.C3P0_MIN_SIZE, "${hibernate.c3p0.min_size}");
                settings.put(Environment.C3P0_MAX_SIZE, "${hibernate.c3p0.max_size}");
                settings.put(Environment.C3P0_ACQUIRE_INCREMENT, "${hibernate.c3p0.acquire_increment}");
                settings.put(Environment.C3P0_TIMEOUT, "${hibernate.c3p0.timeout}");
                settings.put(Environment.C3P0_MAX_STATEMENTS, "${hibernate.c3p0.max_statements}");


                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Utente.class);
                configuration.addAnnotatedClass(Prenotazione.class);
                configuration.addAnnotatedClass(Veicolo.class);


                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }


}
