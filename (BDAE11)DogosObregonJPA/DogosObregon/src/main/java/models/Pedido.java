
package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 *
 * @author Jesús Pedro Lares Valencia - 00000233383
 */
@Entity
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "fch_pago")
    private LocalDateTime fecha;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "met_pago", nullable = false)
    private MetodoPago metodoPago;
    
    @ManyToOne
    @JoinColumn(name = "clliente_id")
    private Cliente cliente;
    
    @OneToMany(mappedBy = "pedido")
    private Set<PedidoDetalle> detalles;

    public Pedido() {
    }

    public Pedido(Long id, LocalDateTime fecha, MetodoPago metodoPago, 
            Cliente cliente, Set<PedidoDetalle> detalles) {
        this.id = id;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.cliente = cliente;
        this.detalles = detalles;
    }  

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }
    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<PedidoDetalle> getDetalles() {
        return detalles;
    }
    public void setDetalles(Set<PedidoDetalle> detalles) {
        this.detalles = detalles;
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
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Pedido[ id=" + id + " ]";
    }
    
}
