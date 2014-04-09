/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pangea
 */
@Entity
@Table(name = "PROVEEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByIdpro", query = "SELECT p FROM Proveedor p WHERE p.idpro = :idpro"),
    @NamedQuery(name = "Proveedor.findByNombrepro", query = "SELECT p FROM Proveedor p WHERE p.nombrepro = :nombrepro")})
public class Proveedor implements Serializable {

    
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROVEEDOR")
    @SequenceGenerator(name = "SEQ_PROVEEDOR", sequenceName = "SEQ_PROVEEDOR", allocationSize = 1)
   
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPRO")
    private BigDecimal idpro;
    @Size(max = 200)
    @Column(name = "NOMBREPRO")
    private String nombrepro;
    @Size(max = 20)
    @Column(name = "CODIGOPRO")
    private String codigopro;
    @Size(max = 20)
    @Column(name = "TELEFONOPRO")
    private String telefonopro;
    @OneToMany(mappedBy = "idpro")
    private Collection<Proveedorsede> proveedorsedeCollection;
    

    public Proveedor() {
    }

    public Proveedor(BigDecimal idpro) {
        this.idpro = idpro;
    }

    public BigDecimal getIdpro() {
        return idpro;
    }

    public void setIdpro(BigDecimal idpro) {
        this.idpro = idpro;
    }

    public String getNombrepro() {
        return nombrepro;
    }

    public void setNombrepro(String nombrepro) {
        this.nombrepro = nombrepro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpro != null ? idpro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idpro == null && other.idpro != null) || (this.idpro != null && !this.idpro.equals(other.idpro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Proveedor[ idpro=" + idpro + " ]";
    }

    public String getTelefonopro() {
        return telefonopro;
    }

    public void setTelefonopro(String telefonopro) {
        this.telefonopro = telefonopro;
    }

    @XmlTransient
    public Collection<Proveedorsede> getProveedorsedeCollection() {
        return proveedorsedeCollection;
    }

    public void setProveedorsedeCollection(Collection<Proveedorsede> proveedorsedeCollection) {
        this.proveedorsedeCollection = proveedorsedeCollection;
    }

   

    public String getCodigopro() {
        return codigopro;
    }

    public void setCodigopro(String codigopro) {
        this.codigopro = codigopro;
    }
}
