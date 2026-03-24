/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dominio.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author martinbl
 */
public class ClienteDAO implements IClienteDAO {

    @Override
    public void guardar(Cliente cliente, EntityManager em) {
        em.persist(cliente);
    }

    @Override
    public void actualizar(Cliente cliente, EntityManager em) {
        em.merge(cliente);
    }

    @Override
    public Cliente buscarPorId(Long id, EntityManager em) {
        return em.find(Cliente.class, id);
    }

    @Override
    public void eliminar(Long id, EntityManager em) {
        Cliente cliente = em.find(Cliente.class, id);
        if (cliente != null) {
            em.remove(cliente);
        }
    }

    @Override
    public Cliente buscarPorNombre(String nombre, EntityManager em) {
        TypedQuery<Cliente> query = 
                em.createNamedQuery("Cliente.buscarPorNombre", Cliente.class);
        query.setParameter("nombre", nombre);
        return query.getSingleResult();
    }

    @Override
    public List<Cliente> buscarTodos(EntityManager em) {
        TypedQuery<Cliente> query = em.createQuery(
                "SELECT - FROM Cliente c ORDER BY c.id", Cliente.class);
        
        // Limita la cantidad de resultados obtenidos a la vez
        query.setFirstResult(0);
        query.setMaxResults(100);
        
        return query.getResultList();
    }
    
    public List<Cliente> buscarNacidosDespuesDe(EntityManager em) {
        TypedQuery<Cliente> query = 
                em.createNamedQuery("Cliente.buscarNacidosDespuesDe", Cliente.class);
        query.setParameter("fecha", LocalDate.of(2000, 1, 1));
        
        query.setFirstResult(0);
        query.setMaxResults(100);
        
        return query.getResultList();
    }
    
    public List<Cliente> buscarPorRecomendador(EntityManager em) {
        TypedQuery<Cliente> query = 
                em.createNamedQuery("Cliente.buscarPorRecomendador", Cliente.class);
        
        query.setFirstResult(0);
        query.setMaxResults(100);
        
        return query.getResultList();
    }
    
    public List<Cliente> clienteConPedidos(EntityManager em) {
        TypedQuery<Cliente> query = 
                em.createNamedQuery("Cliente.clienteConPedidos", Cliente.class);
        
        query.setFirstResult(0);
        query.setMaxResults(100);
        
        return query.getResultList();
    }
    
    public List<Cliente> buscarTodosPorNombre(String nombre, EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
        Root<Cliente> cliente = cq.from(Cliente.class);
        
        cq.select(cliente).where(cb.equal(cliente.get("nombre"), nombre));
        
        return em.createQuery(cq).getResultList();
    }
    
}
