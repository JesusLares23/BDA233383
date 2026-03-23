/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dominio.HotDog;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author martinbl
 */
public class HotDogDAO implements IHotDogDAO {
    @Override
    public void guardar(HotDog hotdog, EntityManager em) {
        em.persist(hotdog);
    }

    @Override
    public void actualizar(HotDog hotdog, EntityManager em) {
        em.merge(hotdog);
    }

    @Override
    public HotDog buscarPorId(Long id, EntityManager em) {
        return em.find(HotDog.class, id);
    }

    @Override
    public void eliminar(Long id, EntityManager em) {
        HotDog hotdog = em.find(HotDog.class, id);
        if (hotdog != null) {
            em.remove(hotdog);
        }
    }

    @Override
    public List<HotDog> buscarTodos(EntityManager em) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
