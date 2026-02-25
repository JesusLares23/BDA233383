
import java.util.List;

/**
 *
 * @author Jesús Pedro Lares Valencia - 00000233383
 */
public class TiendaJPA {

    public static void main(String[] args) {

        ProductoDAO productoDAO = new ProductoDAO();

        Producto p1 = new Producto(0, "Papitas", 19.90);
        Producto p2 = new Producto(0, "Galletas", 21.90);
        Producto p3 = new Producto(0, "Coca Cola", 25.90);
        Producto p4 = new Producto(0, "Gansito", 15.90);

        productoDAO.insertar(p1);
        productoDAO.insertar(p2);
        productoDAO.insertar(p3);
        productoDAO.insertar(p4);
        
        System.out.println("Listado de productos");
        for (Producto producto : productoDAO.listar()) {
            System.out.println(producto);
        }

        Producto pBuscado = productoDAO.buscar(3);
        System.out.println("Producto encontrado: " + pBuscado.getNombre());

        pBuscado.setPrecio(26.90);
        productoDAO.actualizar(pBuscado);

        System.out.println("Producto modificado");
        for (Producto producto : productoDAO.listar()) {
            System.out.println(producto);
        }

        productoDAO.eliminar(4);

        System.out.println("Producto eliminado");
        for (Producto producto : productoDAO.listar()) {
            System.out.println(producto);
        }
    }
}
