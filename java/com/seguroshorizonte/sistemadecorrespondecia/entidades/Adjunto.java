/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pangea
 */
@Entity
@Table(name = "ADJUNTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adjunto.findAll", query = "SELECT a FROM Adjunto a"),
    @NamedQuery(name = "Adjunto.findByIdadj", query = "SELECT a FROM Adjunto a WHERE a.idadj = :idadj"),
    @NamedQuery(name = "Adjunto.findByNombreadj", query = "SELECT a FROM Adjunto a WHERE a.nombreadj = :nombreadj"),
    @NamedQuery(name = "Adjunto.findByUrladj", query = "SELECT a FROM Adjunto a WHERE a.urladj = :urladj"),
    @NamedQuery(name = "Adjunto.findByIdpaq", query = "SELECT a FROM Adjunto a WHERE a.idpaq = :idpaq")})
public class Adjunto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADJUNTOSEQ")
    @SequenceGenerator(name = "ADJUNTOSEQ", sequenceName = "SEQ_ADJUNTO", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDADJ")
    private BigDecimal idadj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBREADJ")
    private String nombreadj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "URLADJ")
    private String urladj;
    @JoinColumn(name = "IDPAQ", referencedColumnName = "IDPAQ")
    @ManyToOne
    private Paquete idpaq;

    public Adjunto() {
    }

    public Adjunto(BigDecimal idadj) {
        this.idadj = idadj;
    }

    public Adjunto(BigDecimal idadj, String nombreadj, String urladj) {
        this.idadj = idadj;
        this.nombreadj = nombreadj;
        this.urladj = urladj;
    }

    public BigDecimal getIdadj() {
        return idadj;
    }

    public void setIdadj(BigDecimal idadj) {
        this.idadj = idadj;
    }

    public String getNombreadj() {
        return nombreadj;
    }

    public void setNombreadj(String nombreadj) {
        this.nombreadj = nombreadj;
    }

    public String getUrladj() {
        return urladj;
    }

    public void setUrladj(String urladj) {
        this.urladj = urladj;
    }

    public Paquete getIdpaq() {
        return idpaq;
    }

    public void setIdpaq(Paquete idpaq) {
        this.idpaq = idpaq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idadj != null ? idadj.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adjunto)) {
            return false;
        }
        Adjunto other = (Adjunto) object;
        if ((this.idadj == null && other.idadj != null) || (this.idadj != null && !this.idadj.equals(other.idadj))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Adjunto[ idadj=" + idadj + " ]";
    }
}
