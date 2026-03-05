package daos;

import jakarta.persistence.EntityManager;
import java.util.List;
import models.SuperHeroe;
import utils.JPAUtils;

/**
 *
 * @author Jesús Pedro Lares Valencia - 00000233383
 */
public class SuperHeroeDAO implements ISuperHeroeDAO {

    @Override
    public void insertar(SuperHeroe e) {
        EntityManager em = JPAUtils.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(SuperHeroe e) {
        EntityManager em = JPAUtils.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(e);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(Long id) {
        EntityManager em = JPAUtils.getEntityManager();
        try {
            em.getTransaction().begin();
            SuperHeroe sh = em.find(SuperHeroe.class, id);
            if (sh != null) {
                em.remove(sh);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public SuperHeroe buscar(Long id) {
        EntityManager em = JPAUtils.getEntityManager();
        try {
            return em.find(SuperHeroe.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<SuperHeroe> listar() {
        EntityManager em = JPAUtils.getEntityManager();
        try {
            return em.createQuery("SELECT s FROM SuperHeroe s", 
                    SuperHeroe.class).getResultList();
        } finally { 
            em.close();
        }
    }

}
