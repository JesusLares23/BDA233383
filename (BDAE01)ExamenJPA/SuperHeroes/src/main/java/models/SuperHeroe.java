
package models;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import java.io.Serializable;

/**
 *
 * @author Jesús Pedro Lares Valencia - 00000233383
 */
@Entity
@Table(name = "superheroes",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})})
public class SuperHeroe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Enumerated(EnumType.STRING)
    private Universo universo;
    
    @Embedded()
    private IdentidadSecreta idSecreta;
    
    @Transient
    private String fraseIconica;
    
    public SuperHeroe() {
    }
    public SuperHeroe(Long id, String nombre, Universo universo, 
            IdentidadSecreta idSecreta, String fraseIconica) {
        this.id = id;
        this.nombre = nombre;
        this.universo = universo;
        this.idSecreta = idSecreta;
        this.fraseIconica = fraseIconica;
    }

    public SuperHeroe(String nombre, Universo universo, 
            IdentidadSecreta idSecreta, String fraseIconica) {
        this.nombre = nombre;
        this.universo = universo;
        this.idSecreta = idSecreta;
        this.fraseIconica = fraseIconica;
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

    public Universo getUniverso() {
        return universo;
    }
    public void setUniverso(Universo universo) {
        this.universo = universo;
    }

    public IdentidadSecreta getIdSecreta() {
        return idSecreta;
    }
    public void setIdSecreta(IdentidadSecreta idSecreta) {
        this.idSecreta = idSecreta;
    }

    public String getFraseIconica() {
        return fraseIconica;
    }
    public void setFraseIconica(String fraseIconica) {
        this.fraseIconica = fraseIconica;
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
        if (!(object instanceof SuperHeroe)) {
            return false;
        }
        SuperHeroe other = (SuperHeroe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.SuperHeroe[ id=" + id + " ]";
    }
    
}
