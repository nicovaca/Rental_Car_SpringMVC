package com.esempio.RentalCar.dao;

import com.esempio.RentalCar.config.HibernateConfig;
import com.esempio.RentalCar.entities.Utente;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;


import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UtenteDAOImpl implements UtenteDAO {

    public void saveUtente(Utente u) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = null;


        try {
            transaction = session.beginTransaction();
            session.save(u);
            transaction.commit();


        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();

        } finally {
            if (session != null)
                session.close();
        }

    }


    public void updateUtente(Utente u) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(u);
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }


    }


    public void deleteUtente(Utente u) {
        Session session = null;
        session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(u);
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }


    }


    public Utente getUtente(Long id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        try {
            return session.get(Utente.class, id);
        } catch (Exception e) {
            return null;
        } finally {
            if (session != null)
                session.close();
        }

    }


    public List<Utente> getListaUtenti() {

        Session session = HibernateConfig.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Utente> query = cb.createQuery(Utente.class);
        Root<Utente> root = query.from(Utente.class);
        query.select(root);
        Query<Utente> q = session.createQuery(query);
        List<Utente> result = q.getResultList();
        return result;

    }


    public List<Utente> getUtenteByFilter(String filtro) {

        Session session = HibernateConfig.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Utente> query = cb.createQuery(Utente.class);
        Root<Utente> root = query.from(Utente.class);

        Predicate espressione1 = cb.like(root.get("nome"), "%" + filtro + "%");
        Predicate espressione2 = cb.like(root.get("username"), "%" + filtro + "%");
        Predicate espressione3 = cb.like(root.get("cognome"), "%" + filtro + "%");


        query.select(root).where(cb.or(espressione1, espressione2, espressione3));

        Query<Utente> q = session.createQuery(query);
        List<Utente> result = q.getResultList();
        return result;
    }


    public Utente getUtenteByUsername(String username) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Utente> query = cb.createQuery(Utente.class);
        Root<Utente> root = query.from(Utente.class);

        query.select(root).where(cb.equal(root.get("username"), username));

        Query<Utente> q = session.createQuery(query);
        Utente utente = q.getSingleResult();
        return utente;
    }


}
