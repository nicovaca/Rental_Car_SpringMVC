package com.esempio.RentalCar.dao;

import com.esempio.RentalCar.config.HibernateConfig;
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
import java.util.List;

@Repository
public class VeicoloDAOImpl implements VeicoloDAO {

    @Override
    public void saveVeicolo(Veicolo v) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = null;


        try {
            transaction = session.beginTransaction();
            session.save(v);
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
    public void updateVeicolo(Veicolo v) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(v);
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
    public void deleteVeicolo(Veicolo v) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(v);
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
    public Veicolo getVeicolo(Long id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        try {
            return session.get(Veicolo.class, id);
        } catch (Exception e) {
            return null;
        }finally {
            if (session != null)
                session.close();
        }
    }

    @Override
    public List<Veicolo> getListaVeicoli() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Veicolo> query = cb.createQuery(Veicolo.class);
        Root<Veicolo> root = query.from(Veicolo.class);

        query.select(root);

        Query<Veicolo> q = session.createQuery(query);
        List<Veicolo> result = q.getResultList();
        return result;
    }


    //metodo per filtrare i veicoli
    public List<Veicolo> getVeicoloByFilter(String filtro){
        Session session = HibernateConfig.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Veicolo> query = cb.createQuery(Veicolo.class);
        Root<Veicolo> root = query.from(Veicolo.class);

        Predicate espressione1 = cb.like(root.get("casaCostruttrice"), "%"+filtro+"%" );
        Predicate espressione2 = cb.like(root.get("modello"), "%"+filtro+"%" );

        query.select(root).where(cb.or(espressione1,espressione2));
        Query<Veicolo> q = session.createQuery(query);
        List<Veicolo> result = q.getResultList();
        return result;
    }
}
