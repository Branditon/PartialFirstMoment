/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence;

import Entity.CoworkingReservationsEntity;
import Persistence.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author brandonescudero
 */
public class CoworkingReservationsEntityJpaController implements Serializable {

    public CoworkingReservationsEntityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CoworkingReservationsEntityJpaController ()
    {
        emf = Persistence.createEntityManagerFactory("PersistenceUnitBD");
    }

    public void create(CoworkingReservationsEntity coworkingReservationsEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(coworkingReservationsEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CoworkingReservationsEntity coworkingReservationsEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            coworkingReservationsEntity = em.merge(coworkingReservationsEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = coworkingReservationsEntity.getId();
                if (findCoworkingReservationsEntity(id) == null) {
                    throw new NonexistentEntityException("The coworkingReservationsEntity with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CoworkingReservationsEntity coworkingReservationsEntity;
            try {
                coworkingReservationsEntity = em.getReference(CoworkingReservationsEntity.class, id);
                coworkingReservationsEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coworkingReservationsEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(coworkingReservationsEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CoworkingReservationsEntity> findCoworkingReservationsEntityEntities() {
        return findCoworkingReservationsEntityEntities(true, -1, -1);
    }

    public List<CoworkingReservationsEntity> findCoworkingReservationsEntityEntities(int maxResults, int firstResult) {
        return findCoworkingReservationsEntityEntities(false, maxResults, firstResult);
    }

    private List<CoworkingReservationsEntity> findCoworkingReservationsEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CoworkingReservationsEntity.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CoworkingReservationsEntity findCoworkingReservationsEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CoworkingReservationsEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoworkingReservationsEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CoworkingReservationsEntity> rt = cq.from(CoworkingReservationsEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
