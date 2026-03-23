/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dominio.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
}
