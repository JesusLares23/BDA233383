
package models;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 *
 * @author Jesús Pedro Lares Valencia - 00000233383
 */
@Entity
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    
    @Column(name = "ap_paterno")
    private String apPaterno;
    
    @Column(name = "ap_materno")
    private String apMaterno;
    
    @Column(name = "fch_nac")
    private LocalDate fchNac;
    
    @OneToOne
    @JoinColumn(name = "cliente_recomienda_id", referencedColumnName = "id")
    private Cliente clienteRecomienda;
    
    @ElementCollection
    @CollectionTable(name = "Cliente_Telefonos", 
            joinColumns = @JoinColumn(name = "cliente_id"))
    @Column(name = "telefono")
    private Set<String> telefonos;
    
    @ElementCollection
    @CollectionTable(name = "Cliente_Preferencias", 
            joinColumns = @JoinColumn(name = "cliente_id"))
    @Column(name = "preferencia")
    private Set<String> preferencias;
    
    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos;

    public Cliente() {
    }

    public Cliente(Long id, String nombre, String apPaterno, String apMaterno, 
            LocalDate fchNac, Cliente clienteRecomienda, Set<String> telefonos, 
            Set<String> preferencias, Set<Pedido> pedidos) {
        this.id = id;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.fchNac = fchNac;
        this.clienteRecomienda = clienteRecomienda;
        this.telefonos = telefonos;
        this.preferencias = preferencias;
        this.pedidos = pedidos;
    }    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }
    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public LocalDate getFchNac() {
        return fchNac;
    }

    public void setFchNac(LocalDate fchNac) {
        this.fchNac = fchNac;
    }

    public Cliente getClienteRecomienda() {
        return clienteRecomienda;
    }

    public void setClienteRecomienda(Cliente clienteRecomienda) {
        this.clienteRecomienda = clienteRecomienda;
    }

    public Set<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(Set<String> telefonos) {
        this.telefonos = telefonos;
    }

    public Set<String> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(Set<String> preferencias) {
        this.preferencias = preferencias;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }
    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Cliente[ id=" + id + " ]";
    }
    
}
