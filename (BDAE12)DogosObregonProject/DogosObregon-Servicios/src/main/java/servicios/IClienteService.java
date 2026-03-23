package servicios;

import dominio.Cliente;

public interface IClienteService extends IGenericoService<Cliente, Long> {
    Cliente buscarPorNombre(String nombre);
    void registrarClienteNuevo(Cliente c);
}
