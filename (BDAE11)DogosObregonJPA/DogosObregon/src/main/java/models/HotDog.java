/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 *
 * @author Jesús Pedro Lares Valencia - 00000233383
 */
@Entity
public class HotDog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nombre;
        
    private BigDecimal precio;
    
    private BigDecimal iva;
    
    @OneToMany(mappedBy = "hotdog")
    private Set<PedidoDetalle> detalles;

    public HotDog() {
    }

    public HotDog(Long id, String nombre, BigDecimal precio, BigDecimal iva, 
            Set<PedidoDetalle> detalles) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.iva = iva;
        this.detalles = detalles;
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

    public BigDecimal getPrecio() {
        return precio;
    }
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getIva() {
        return iva;
    }
    public void setIva(BigDecimal iva) {
        this.iva = iva;
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
        if (!(object instanceof HotDog)) {
            return false;
        }
        HotDog other = (HotDog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.HotDog[ id=" + id + " ]";
    }
    
}
