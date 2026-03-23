/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author martinbl
 */
public interface IGenericoDAO<T, ID> {
    void guardar(T entidad, EntityManager em);
    void actualizar(T entidad, EntityManager em);
    T buscarPorId(ID id, EntityManager em);
    void eliminar(ID id, EntityManager em);
    List<T> buscarTodos(EntityManager em);
}
