package servicios;

import dominio.HotDog;
import jakarta.persistence.EntityManager;
import java.util.List;
import persistencia.HotDogDAO;
import persistencia.IHotDogDAO;
import utils.JPAUtil;

public class HotDogService implements IHotDogService {

    private IHotDogDAO hotDogDao;

    public HotDogService() {
        this.hotDogDao = new HotDogDAO();
    }

    private void validarHotDog(HotDog hotdog) {
        if (hotdog == null) {
            throw new IllegalArgumentException("El hotdog no puede ser nulo.");
        }
        if (hotdog.getNombre() == null || hotdog.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del hotdog es obligatorio.");
        }
        if (hotdog.getPrecio() == null || hotdog.getPrecio().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero.");
        }
    }

    @Override
    public void guardar(HotDog entidad) {
        validarHotDog(entidad);
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            hotDogDao.guardar(entidad, em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(HotDog entidad) {
        validarHotDog(entidad);
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            hotDogDao.actualizar(entidad, em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public HotDog buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return hotDogDao.buscarPorId(id, em);
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            hotDogDao.eliminar(id, em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<HotDog> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return hotDogDao.buscarTodos(em);
        } finally {
            em.close();
        }
    }
}
