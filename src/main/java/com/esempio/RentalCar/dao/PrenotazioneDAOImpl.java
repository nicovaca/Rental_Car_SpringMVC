package com.esempio.RentalCar.dao;

import com.esempio.RentalCar.config.HibernateConfig;
import com.esempio.RentalCar.entities.Prenotazione;
import com.esempio.RentalCar.entities.Utente;
import com.esempio.RentalCar.entities.Veicolo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PrenotazioneDAOImpl implements PrenotazioneDAO {

    @Override
    public void savePrenotazione(Prenotazione p) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = null;


        try {
            transaction = session.beginTransaction();
            session.save(p);
            transaction.commit();


        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();

        } finally {
            if (session != null)
                session.close();
        }
    }

    @Override
    public void updatePrenotazione(Prenotazione p) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(p);
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
    }

    @Override
    public void deletePrenotazione(Prenotazione p) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(p);
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
    }

    @Override
    public Prenotazione getPrenotazione(Long id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        try {
            return session.get(Prenotazione.class, id);
        } catch (Exception e) {
            return null;
        } finally {
            if (session != null)
                session.close();
        }
    }

    @Override
    public List<Prenotazione> getListaPrenotazioni() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Prenotazione> query = cb.createQuery(Prenotazione.class);
        Root<Prenotazione> root = query.from(Prenotazione.class);

        query.select(root);
        query.orderBy(cb.desc(root.get("id")));

        Query<Prenotazione> q = session.createQuery(query);
        List<Prenotazione> result = q.getResultList();
        return result;

    }

    public List<Prenotazione> getPrenotazioniByUtente(Utente utente) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Prenotazione> query = cb.createQuery(Prenotazione.class);
        Root<Prenotazione> root = query.from(Prenotazione.class);

        query.select(root).where(cb.equal(root.get("utente"), utente));
        query.orderBy(cb.desc(root.get("id")));

        Query<Prenotazione> q = session.createQuery(query);
        List<Prenotazione> result = q.getResultList();
        return result;
    }

    //metodo per filtrare le prenotazioni
    public List<Prenotazione> getPrenotazioneByFilter(String filtro) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Prenotazione> query = cb.createQuery(Prenotazione.class);
        Root<Prenotazione> root = query.from(Prenotazione.class);

        Predicate espressione1 = cb.like(root.get("veicolo").get("casaCostruttrice"), "%" + filtro + "%");
        Predicate espressione2 = cb.like(root.get("veicolo").get("modello"), "%" + filtro + "%");
        Predicate espressione3 = cb.like(root.get("utente").get("nome"), "%" + filtro + "%");
        Predicate espressione4 = cb.like(root.get("utente").get("cognome"), "%" + filtro + "%");
        Predicate espressione5 = cb.like(root.get("utente").get("username"), "%" + filtro + "%");


        query.select(root).where(cb.or(espressione1, espressione2, espressione3, espressione4, espressione5));

        Query<Prenotazione> q = session.createQuery(query);
        List<Prenotazione> result = q.getResultList();
        return result;
    }

    public List<Prenotazione> getPrenotazioniCustomerByFilter(String filtro, Utente utente) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Prenotazione> query = cb.createQuery(Prenotazione.class);
        Root<Prenotazione> root = query.from(Prenotazione.class);

        Predicate espressione1 = cb.like(root.get("veicolo").get("casaCostruttrice"), "%" + filtro + "%");
        Predicate espressione2 = cb.like(root.get("veicolo").get("modello"), "%" + filtro + "%");
        Predicate filtroTot = cb.or(espressione1,espressione2);

        List<Prenotazione> prenotazioniUtente = getPrenotazioniByUtente(utente);
        Predicate prenUtente = root.in(prenotazioniUtente);

        Predicate finale = cb.and(filtroTot,prenUtente);

        query.select(root).where(finale);

        Query<Prenotazione> q = session.createQuery(query);
        List<Prenotazione> result = q.getResultList();
        return result;

    }
}
