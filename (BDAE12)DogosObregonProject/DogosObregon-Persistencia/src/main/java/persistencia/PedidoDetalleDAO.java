/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dominio.PedidoDetalle;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author martinbl
 */
public class PedidoDetalleDAO implements IPedidoDetalleDAO {
    @Override
    public void guardar(PedidoDetalle detalle, EntityManager em) {
        em.persist(detalle);
    }

    @Override
    public void actualizar(PedidoDetalle detalle, EntityManager em) {
        em.merge(detalle);
    }

    @Override
    public PedidoDetalle buscarPorId(Long id, EntityManager em) {
        return em.find(PedidoDetalle.class, id);
    }

    @Override
    public void eliminar(Long id, EntityManager em) {
        PedidoDetalle detalle = em.find(PedidoDetalle.class, id);
        if (detalle != null) {
            em.remove(detalle);
        }
    }

    @Override
    public List<PedidoDetalle> buscarTodos(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
