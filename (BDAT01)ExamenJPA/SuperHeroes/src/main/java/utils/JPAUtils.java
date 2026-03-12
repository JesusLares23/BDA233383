
package utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

/**
 *
 * @author Jesús Pedro Lares Valencia - 00000233383
 */
public class JPAUtils {
    
    private static final String PERSISTENCE_UNIT = "SuperHeroesPU";
    
    private static EntityManagerFactory emf;
    
    public static EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        return emf.createEntityManager();
    }
    
}
