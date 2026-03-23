package servicios;

import dominio.Cliente;
import jakarta.persistence.EntityManager;
import java.util.List;
import persistencia.ClienteDAO;
import persistencia.IClienteDAO;
import utils.JPAUtil;

public class ClienteService implements IClienteService {

    private IClienteDAO clienteDao;

    public ClienteService() {
        this.clienteDao = new ClienteDAO();
    }

    private void validarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio.");
        }
    }

    @Override
    public void guardar(Cliente entidad) {
        validarCliente(entidad);
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            clienteDao.guardar(entidad, em);
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
    public void actualizar(Cliente entidad) {
        validarCliente(entidad);
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            clienteDao.actualizar(entidad, em);
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
    public Cliente buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return clienteDao.buscarPorId(id, em);
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            clienteDao.eliminar(id, em);
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
    public List<Cliente> buscarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return clienteDao.buscarTodos(em);
        } finally {
            em.close();
        }
    }

    @Override
    public Cliente buscarPorNombre(String nombre) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return clienteDao.buscarPorNombre(nombre, em);
        } finally {
            em.close();
        }
    }

    @Override
    public void registrarClienteNuevo(Cliente c) {
        validarCliente(c);
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            // Aquí se pueden agregar validaciones o reglas de negocio adicionales antes de guardar
            clienteDao.guardar(c, em);

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
}
