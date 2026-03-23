
package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Jesús Pedro Lares Valencia - 00000233383
 */
@Entity
@Table(name = "empleados",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "salario",
            nullable = false,
            columnDefinition = "DECIMAL CHECK = (salario >= 0)")
    private double salario;
    
    @Enumerated(EnumType.STRING)
    private Estatus estatus;
    
    @Column(name = "fecha_contratacion", nullable = false)
    private LocalDate fechaContratacion;

    public Empleado() {
    }
    public Empleado(Long id, String nombre, String email, double salario, 
            Estatus estatus, LocalDate fechaContratacion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.salario = salario;
        this.estatus = estatus;
        this.fechaContratacion = fechaContratacion;
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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Estatus getEstatus() {
        return estatus;
    }
    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }
    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
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
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Empleado[ id=" + id + " ]";
    }
    
}
