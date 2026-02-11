package miprimeraconexionjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author darkm
 */
public class MiPrimeraConexionJDBC {

    private static final String URL = "jdbc:mysql://localhost:3306/MyDatabase";
    private static final String user = "root";
    private static final String password = "Sql.introduce.64";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, user, password);
    }

    // Crear tabla de clientes
    public static void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS clientes ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "nombre VARCHAR(100) NOT NULL,"
                + "password VARCHAR(100) NOT NULL)";

        try (
                Connection con = getConnection(); 
                Statement st = con.createStatement()) {
            st.execute(sql);
            System.out.println("Tabla creada con exito");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    // Leer de la tabla de clientes
    public static void obtenerClientes() {
        String sql = "SELECT * FROM clientes";

        try (
                Connection con = getConnection(); 
                Statement st = con.createStatement(); 
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                System.out.println(id + " | " + nombre);
            }
        } catch (SQLException se) {
            se.printStackTrace();   
        }
    }

    public static boolean login(String nombre, String password) {
        String sql = "SELECT * FROM clientes WHERE nombre = '"
                + nombre + "' AND PASSWORD = '"
                + password + "'";

        try (
                Connection con = getConnection(); 
                Statement st = con.createStatement(); 
                ResultSet rs = st.executeQuery(sql)
                ) {
            return rs.next(); // si encuentra resultado, login válida
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loginSeguro(String nombre, String password) {
        String sql = "SELECT * FROM clientes WHERE nombre = ? AND PASSWORD = ?";
        try (
                Connection con = getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, password);
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // si encuentra resultado, login valido
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Insertar cliente
    public static void insertarCliente(String nombre, String password) {
        String sql = "INSERT INTO clientes (nombre, password) VALUES (?, ?)";
        try (
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql, 
                        Statement.RETURN_GENERATED_KEYS)
                ) {
            
            ps.setString(1, nombre);
            ps.setString(2, password);
            
            int filas = ps.executeUpdate();
            
            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    while (rs.next()) {
                        System.out.println("Insertando cliente con ID: " 
                                + rs.getInt(1));
                    }
                }
            }
            
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
    // Leer cliente por ID
    public static void obtenerClientePorId(int id) {
        String sql = "SELECT id, nombre, password FROM clientes WHERE id = ?";
        try (
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
                ) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") 
                            + ", Nombre: " + rs.getString("nombre") 
                            + ", Password: " + rs.getString("password"));
                } else {
                    System.out.println("No se encontro cliente con ID: " + id);
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
    // Actualizar clientes 
    public static void actualizarCliente(int id, String nuevoNombre, 
            String nuevaPassword) {
        String sql = "UPDATE clientes SET nombre = ?, password = ? WHERE id = ?";
        try (
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ) {
            
            ps.setString(1, nuevoNombre);
            ps.setString(2, nuevaPassword);
            ps.setInt(3, id);
            
            int filas = ps.executeUpdate();
            
            if (filas > 0) {
                System.out.println("Cliente actualizado con ID: " + id);
            } else {
                System.out.println("No se encontro cliente con ID: " + id);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
    // Eliminar cliente
    public static void eliminar(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
                ) {
            
            ps.setInt(1, id);
            
            int filas = ps.executeUpdate();
            
            if (filas > 0) {
                System.out.println("Cliente eliminado con ID: " + id);
            } else {
                System.out.println("No se encontro cliente con ID: " + id);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        // 1. Crear tabla
        crearTabla();
        
        // 2. Insertar clientes de prueba
        System.out.println("\n--- Inserciones ---");
        insertarCliente("Juan", "1234");
        insertarCliente("Maria", "abcd");
        insertarCliente("Pedro", "qwerty");
        insertarCliente("Ana", "pass123");
        
        // 3. Leer todos los clientes
        System.out.println("\n--- Todos los clientes ---");
        obtenerClientes();
        
        // 4. Leer clientes por ID
        System.out.println("\n--- Consulta por ID ---");
        obtenerClientePorId(1); // Suponiendo que juan tiene id 1
        obtenerClientePorId(3); // Suponiendo que pedro tiene id 3
        obtenerClientePorId(99); // ID inexistente, debe indicar que no se encontró
        
        // 5. Actualizar cliente
        System.out.println("\n--- Actualizacion ---");
        actualizarCliente(2, "Maria actualizada", "newpass"); // Cambiamos datos de María
        obtenerClientes(); // Verificamos cambios
        
        // 6. Eliminar cliente
        System.out.println("\n--- Eliminacion ---");
        eliminar(3); // Eliminamos a Pedro
        obtenerClientes(); // Verificamos cambios
        
        // 7. Pruebas de login
        System.out.println("\n--- Login normal ---");
        System.out.println("Login Juan/1234: " + login("Juan", "1234")); // true
        System.out.println("Login Ana/pass123: " + login("Ana", "pass123")); // true
        System.out.println("Login incorrecto: " + login("Ana", "wrong")); // false
        System.out.println("\n--- Login seguro ---");
        System.out.println("Login seguro Juan/1234: " + loginSeguro("Juan", "1234")); // true
        System.out.println("Login seguro Ana/pass123: " + loginSeguro("Ana", "pass123")); // true
        System.out.println("Login seguro incorrecto: " + loginSeguro("Ana", "wrong")); // false

        // 8. Prueba de SQL Injection
        System.out.println("\n--- Prueba SQL Injection ---");
        System.out.println("Intento login normal: " + login("Juan", "' OR '1'='1")); // puede fallar
        System.out.println("Intento login seguro: " + loginSeguro("Juan", "' OR '1'='1")); // Siempre falso
    }
}
