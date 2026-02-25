
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;


/**
 *
 * @author Jesús Pedro Lares Valencia - 00000233383
 */
public class ProductoDAO {
    
    private EntityManagerFactory emf = Persistence.
            createEntityManagerFactory("MyPU");
    
    public void insertar(Producto producto) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(producto);
        em.getTransaction().commit();
        em.close();
    }
    
    public Producto buscar(int id) {
        EntityManager em = emf.createEntityManager();
        Producto p = em.find(Producto.class, id);
        em.close();
        return p;
    }
    
    public void actualizar(Producto p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        em.close();
    }
    
    public void eliminar(int id) {
        EntityManager em = emf.createEntityManager();
        Producto p = em.find(Producto.class, id);
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
        em.close();        
    }
    
    public List<Producto> listar() {
        EntityManager em = emf.createEntityManager();
        List<Producto> lista = em.createQuery(
                "SELECT id, nombre, precio FROM Producto", Producto.class).
                getResultList();
        em.close();
        return lista;
    }
    
}
