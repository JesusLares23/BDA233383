package servicios;

import java.util.List;

public interface IGenericoService<T, ID> {
    void guardar(T entidad);
    void actualizar(T entidad);
    T buscarPorId(ID id);
    void eliminar(ID id);
    List<T> buscarTodos();
}
