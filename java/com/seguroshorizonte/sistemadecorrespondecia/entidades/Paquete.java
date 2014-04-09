/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seguroshorizonte.sistemadecorrespondecia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pangea
 */
@Entity
@Table(name = "PAQUETE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paquete.findAll", query = "SELECT p FROM Paquete p"),
    @NamedQuery(name = "Paquete.findByIdpaq", query = "SELECT p FROM Paquete p WHERE p.idpaq = :idpaq"),
    @NamedQuery(name = "Paquete.findByCodigoBarras", query = "SELECT p FROM Paquete p WHERE p.idpaq = :idpaq AND SUBSTRING( p.fechapaq, 7, 2) = :anio AND p.idsed.codigosed = :idsed"),
    @NamedQuery(name = "Paquete.findByAsuntopaq", query = "SELECT p FROM Paquete p WHERE p.asuntopaq = :asuntopaq"),
    @NamedQuery(name = "Paquete.findByTextopaq", query = "SELECT p FROM Paquete p WHERE p.textopaq = :textopaq"),
    @NamedQuery(name = "Paquete.findByFechapaq", query = "SELECT p FROM Paquete p WHERE p.fechapaq = :fechapaq"),
    @NamedQuery(name = "Paquete.findVencidosXUsuarioOrigen", query = "SELECT p FROM Paquete p WHERE p.origenpaq.idusu = :origen AND p.idsed = :idsed AND p.statuspaq = '0'"),
    @NamedQuery(name = "Paquete.findNormalXUsuarioDestino", query = "SELECT p FROM Paquete p WHERE p.destinopaq.idusu = :destino AND p.destinopaq.idatr.idsed= :idsed AND p.statuspaq = '0'"),
    @NamedQuery(name = "Paquete.findByStatuspaq", query = "SELECT p FROM Paquete p WHERE p.statuspaq = :statuspaq"),
    @NamedQuery(name = "Paquete.findByLocalizacionpaq", query = "SELECT p FROM Paquete p WHERE p.localizacionpaq = :localizacionpaq"),
    @NamedQuery(name = "Paquete.findByIdval", query = "SELECT p FROM Paquete p WHERE p.idval.idval = :idval order by p.origenpaq.idatr.idsed.idsed,p.destinopaq.idatr.idsed.idsed,p.origenpaq.idusu.idusu,p.destinopaq.idusu.idusu"),
    @NamedQuery(name = "Paquete.findByRespaq", query = "SELECT p FROM Paquete p WHERE p.respaq = :respaq"),
    @NamedQuery(name = "Paquete.findByPaqYValija", query = "SELECT p FROM Paquete p WHERE p.idval = :idval"),
    @NamedQuery(name = "Paquete.findByStatuspaqYRespaq", query = "SELECT p FROM Paquete p WHERE p.statuspaq = :statuspaq AND p.respaq = :respaq"),
    @NamedQuery(name = "Paquete.paqBySede", query = "SELECT p FROM Paquete p, Usuariosede s WHERE  p.origenpaq.idusu.idusu = s.idusu.idusu AND s.idsed.nombresed = :sede AND p.localizacionpaq= :sed AND p.destinopaq.tipobuz= :tipo"),
    @NamedQuery(name = "Paquete.findByExternos", query = "SELECT p FROM Paquete p WHERE  p.destinopaq.tipobuz = '1' AND p.origenpaq.idatr.idsed = :sede AND p.localizacionpaq = 'Sede' AND p.statuspaq = '0' "),
    @NamedQuery(name = "Paquete.SedeByValija", query = "SELECT DISTINCT p.destinopaq.idatr.idsed.nombresed FROM Paquete p, Usuariosede s WHERE  p.origenpaq.idusu.idusu = s.idusu.idusu AND s.idsed.nombresed = :sede AND p.localizacionpaq= :sed AND p.destinopaq.tipobuz =:tipo"),
    @NamedQuery(name = "Paquete.findByidPaqueteYSedeDeValija", query = "SELECT p FROM Paquete p WHERE p.idval.destinoval = :idSede AND p.idpaq =:idpaq"),
    @NamedQuery(name = "Paquete.findMaxPaqXOrigen", query = "SELECT MAX(p.idpaq) FROM Paquete p WHERE p.origenpaq.idusu = :origenpaq"),
    @NamedQuery(name = "Paquete.findPaqXBuscarArea", query = "SELECT p FROM Paquete p, Seguimiento s WHERE  p.destinopaq.idatr.idatr=:idatr AND p.destinopaq.idatr.idsed.idsed= :idsed AND p.localizacionpaq= :sede AND p.idpaq=s.idpaq.idpaq AND s.tiposeg='1'"),
    @NamedQuery(name = "Paquete.findPaqXOrigen", query = "SELECT p FROM Paquete p WHERE p.idpaq = :idpaq AND p.origenpaq = :origenpaq"),
    @NamedQuery(name = "Paquete.findPaqXDestino", query = "SELECT p FROM Paquete p WHERE p.idpaq = :idpaq AND p.destinopaq.idusu = :destinopaq"),
    @NamedQuery(name = "Paquete.totalPaquetesEnviadosXSede", query = "SELECT p FROM Paquete p WHERE p.fechapaq BETWEEN :fechaIni AND :fechaFin AND p.idsed = :idsed"),
    @NamedQuery(name = "Paquete.totalPaquetesEnviados", query = "SELECT p FROM Paquete p WHERE p.fechapaq BETWEEN :fechaIni AND :fechaFin"),
    @NamedQuery(name = "Paquete.totalPaquetesRecibidosXSede", query = "SELECT p FROM Paquete p WHERE p.fechapaq BETWEEN :fechaIni AND :fechaFin AND p.idsed = :idsed AND p.statuspaq='1'"),
    @NamedQuery(name = "Paquete.totalPaquetesRecibidos", query = "SELECT p FROM Paquete p WHERE p.fechapaq BETWEEN :fechaIni AND :fechaFin AND p.statuspaq='1'"),
    @NamedQuery(name = "Paquete.totalPaquetesPorEntregarXSede", query = "SELECT p FROM Paquete p WHERE p.fechapaq BETWEEN :fechaIni AND :fechaFin AND p.idsed = :idsed AND p.statuspaq='0'"),
    @NamedQuery(name = "Paquete.totalPaquetesPorEntregar", query = "SELECT p FROM Paquete p WHERE p.fechapaq BETWEEN :fechaIni AND :fechaFin AND p.statuspaq='0'")})
public class Paquete implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpaq")
    private Collection<Mensaje> mensajeCollection;
    @Size(max = 20)
    @Column(name = "FRAGILPAQ")
    private String fragilpaq;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAQUETESEQ")
    @SequenceGenerator(name = "PAQUETESEQ", sequenceName = "SEQ_PAQUETE", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "IDPAQ")
    private BigDecimal idpaq;
    @Size(max = 200)
    @Column(name = "ASUNTOPAQ")
    private String asuntopaq;
    @Size(max = 4000)
    @Column(name = "TEXTOPAQ")
    private String textopaq;
    @Column(name = "FECHAPAQ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechapaq;
    @Size(max = 20)
    @Column(name = "STATUSPAQ")
    private String statuspaq;
    @Size(max = 250)
    @Column(name = "LOCALIZACIONPAQ")
    private String localizacionpaq;
    @Size(max = 20)
    @Column(name = "RESPAQ")
    private String respaq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpaq")
    private Collection<Seguimiento> seguimientoCollection;
    @OneToMany(mappedBy = "idpaq")
    private Collection<Adjunto> adjuntoCollection;
    @OneToMany(mappedBy = "idpaq")
    private Collection<Bandeja> bandejaCollection;
    @JoinColumn(name = "IDVAL", referencedColumnName = "IDVAL")
    @ManyToOne
    private Valija idval;
    @JoinColumn(name = "IDSED", referencedColumnName = "IDSED")
    @ManyToOne
    private Sede idsed;
    @JoinColumn(name = "IDPRI", referencedColumnName = "IDPRI")
    @ManyToOne
    private Prioridad idpri;
    @OneToMany(mappedBy = "idpaqres")
    private Collection<Paquete> paqueteCollection;
    @JoinColumn(name = "IDPAQRES", referencedColumnName = "IDPAQ")
    @ManyToOne
    private Paquete idpaqres;
    @JoinColumn(name = "IDDOC", referencedColumnName = "IDDOC")
    @ManyToOne(optional = false)
    private Documento iddoc;
    @JoinColumn(name = "DESTINOPAQ", referencedColumnName = "IDBUZ")
    @ManyToOne(optional = false)
    private Buzon destinopaq;
    @JoinColumn(name = "ORIGENPAQ", referencedColumnName = "IDBUZ")
    @ManyToOne(optional = false)
    private Buzon origenpaq;

    public Paquete() {
    }

    public Paquete(BigDecimal idpaq) {
        this.idpaq = idpaq;
    }

    public BigDecimal getIdpaq() {
        return idpaq;
    }

    public void setIdpaq(BigDecimal idpaq) {
        this.idpaq = idpaq;
    }

    public String getAsuntopaq() {
        return asuntopaq;
    }

    public void setAsuntopaq(String asuntopaq) {
        this.asuntopaq = asuntopaq;
    }

    public String getTextopaq() {
        return textopaq;
    }

    public void setTextopaq(String textopaq) {
        this.textopaq = textopaq;
    }

    public Date getFechapaq() {
        return fechapaq;
    }

    public void setFechapaq(Date fechapaq) {
        this.fechapaq = fechapaq;
    }

    public String getStatuspaq() {
        return statuspaq;
    }

    public void setStatuspaq(String statuspaq) {
        this.statuspaq = statuspaq;
    }

    public String getLocalizacionpaq() {
        return localizacionpaq;
    }

    public void setLocalizacionpaq(String localizacionpaq) {
        this.localizacionpaq = localizacionpaq;
    }

    public String getRespaq() {
        return respaq;
    }

    public void setRespaq(String respaq) {
        this.respaq = respaq;
    }

    @XmlTransient
    public Collection<Seguimiento> getSeguimientoCollection() {
        return seguimientoCollection;
    }

    public void setSeguimientoCollection(Collection<Seguimiento> seguimientoCollection) {
        this.seguimientoCollection = seguimientoCollection;
    }

    @XmlTransient
    public Collection<Adjunto> getAdjuntoCollection() {
        return adjuntoCollection;
    }

    public void setAdjuntoCollection(Collection<Adjunto> adjuntoCollection) {
        this.adjuntoCollection = adjuntoCollection;
    }

    @XmlTransient
    public Collection<Bandeja> getBandejaCollection() {
        return bandejaCollection;
    }

    public void setBandejaCollection(Collection<Bandeja> bandejaCollection) {
        this.bandejaCollection = bandejaCollection;
    }

    public Valija getIdval() {
        return idval;
    }

    public void setIdval(Valija idval) {
        this.idval = idval;
    }

    public Sede getIdsed() {
        return idsed;
    }

    public void setIdsed(Sede idsed) {
        this.idsed = idsed;
    }

    public Prioridad getIdpri() {
        return idpri;
    }

    public void setIdpri(Prioridad idpri) {
        this.idpri = idpri;
    }

    @XmlTransient
    public Collection<Paquete> getPaqueteCollection() {
        return paqueteCollection;
    }

    public void setPaqueteCollection(Collection<Paquete> paqueteCollection) {
        this.paqueteCollection = paqueteCollection;
    }

    public Paquete getIdpaqres() {
        return idpaqres;
    }

    public void setIdpaqres(Paquete idpaqres) {
        this.idpaqres = idpaqres;
    }

    public Documento getIddoc() {
        return iddoc;
    }

    public void setIddoc(Documento iddoc) {
        this.iddoc = iddoc;
    }

    public Buzon getDestinopaq() {
        return destinopaq;
    }

    public void setDestinopaq(Buzon destinopaq) {
        this.destinopaq = destinopaq;
    }

    public Buzon getOrigenpaq() {
        return origenpaq;
    }

    public void setOrigenpaq(Buzon origenpaq) {
        this.origenpaq = origenpaq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpaq != null ? idpaq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paquete)) {
            return false;
        }
        Paquete other = (Paquete) object;
        if ((this.idpaq == null && other.idpaq != null) || (this.idpaq != null && !this.idpaq.equals(other.idpaq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seguroshorizonte.sistemadecorrespondecia.entidades.Paquete[ idpaq=" + idpaq + " ]";
    }

    public String getFragilpaq() {
        return fragilpaq;
    }

    public void setFragilpaq(String fragilpaq) {
        this.fragilpaq = fragilpaq;
    }

    @XmlTransient
    public Collection<Mensaje> getMensajeCollection() {
        return mensajeCollection;
    }

    public void setMensajeCollection(Collection<Mensaje> mensajeCollection) {
        this.mensajeCollection = mensajeCollection;
    }
}
