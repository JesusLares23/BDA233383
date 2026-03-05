
package daos;

import jakarta.persistence.EntityManager;
import java.util.List;
import models.SuperHeroe;

/**
 *
 * @author Jesús Pedro Lares Valencia - 00000233383
 */
public interface ISuperHeroeDAO {
    
    void insertar(SuperHeroe e);
    
    void actualizar(SuperHeroe e);
    
    void eliminar(Long id);
    
    SuperHeroe buscar(Long id);
    
    List<SuperHeroe> listar();
    
}
