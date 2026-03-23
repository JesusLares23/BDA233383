/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dominio.Pedido;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author martinbl
 */
public class PedidoDAO implements IPedidoDAO{
    @Override
    public void guardar(Pedido pedido, EntityManager em) {
        em.persist(pedido);
    }

    @Override
    public void actualizar(Pedido pedido, EntityManager em) {
        em.merge(pedido);
    }

    @Override
    public Pedido buscarPorId(Long id, EntityManager em) {
        return em.find(Pedido.class, id);
    }

    @Override
    public void eliminar(Long id, EntityManager em) {
        // Buscamos la instancia manejada antes de eliminar
        Pedido pedido = em.find(Pedido.class, id);
        if (pedido != null) {
            em.remove(pedido);
        }
    }

    @Override
    public List<Pedido> buscarTodos(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
