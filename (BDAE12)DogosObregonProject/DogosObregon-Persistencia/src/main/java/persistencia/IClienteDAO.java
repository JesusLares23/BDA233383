/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;


import dominio.Cliente;
import jakarta.persistence.EntityManager;

/**
 *
 * @author martinbl
 */
public interface IClienteDAO extends IGenericoDAO<Cliente, Long>{
    Cliente buscarPorNombre(String nombre, EntityManager em);
}
