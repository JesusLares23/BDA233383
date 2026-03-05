
import daos.ISuperHeroeDAO;
import daos.SuperHeroeDAO;
import jakarta.persistence.EntityManager;
import models.IdentidadSecreta;
import models.SuperHeroe;
import models.Universo;
import utils.JPAUtils;


/**
 *
 * @author Jesús Pedro Lares Valencia - 00000233383
 */
public class SuperHeroes {

    public static void main(String[] args) {
        ISuperHeroeDAO dao = new SuperHeroeDAO();
        EntityManager em = JPAUtils.getEntityManager();
        
        // 1. Insertar superheroes de prueba
        SuperHeroe spiderman = new SuperHeroe("Spider-Man", Universo.MARVEL, 
                new IdentidadSecreta("Peter Parker", "New York", "Fotografo"), 
                "Con gran poder viene una gran responsabilidad");
        
        SuperHeroe batman = new SuperHeroe("Batman", Universo.DC, 
                new IdentidadSecreta("Bruce Wayne", "Gotham", "Empresario"), 
                "Soy la venganza");
        
        SuperHeroe goku = new SuperHeroe("Son Goku", Universo.INDEPENDIENTE, 
                new IdentidadSecreta("Kakarotto", "Planeta Tierra", "Granjero"), 
                "¡Kamehameha!");
        
        dao.insertar(spiderman);
        dao.insertar(batman);
        dao.insertar(goku);
        
        // 2. Listar todos los superheroes
        System.out.println("Lista inicial de superheroes");
        dao.listar().forEach(h -> 
                System.out.printf("\n%d - %s - (%s)", h.getId(), h.getNombre(), 
                        h.getUniverso()));
        
        // 3. Modificar un superhéroe
        SuperHeroe batmanBD = dao.buscar(batman.getId());
        batmanBD.getIdSecreta().setCiudad("Ciudad Gotica");
        dao.actualizar(batmanBD);
        
        System.out.println("\nLista después de modificar a batman:");
        dao.listar().forEach(h -> 
                System.out.printf("\n%d - %s - [%s]", h.getId(), h.getNombre(), 
                        h.getIdSecreta().getCiudad()));
        
        // 4. Eliminar un superheroe
        dao.eliminar(spiderman.getId());
        System.out.println("\nLista despues de eliminar a Spider-man");
        dao.listar().forEach(h -> 
                System.out.printf("\n%d - %s", h.getId(), h.getNombre()));
    }
}
