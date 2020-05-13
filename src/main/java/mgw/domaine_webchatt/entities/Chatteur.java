/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgw.domaine_webchatt.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author BigWave
 */
@Entity
@Table(name = "chatteur", catalog = "webchatt_db", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chatteur.findAll", query = "SELECT c FROM Chatteur c")
    , @NamedQuery(name = "Chatteur.findByChatteurId", query = "SELECT c FROM Chatteur c WHERE c.chatteurId = :chatteurId")
    , @NamedQuery(name = "Chatteur.findBySessionId", query = "SELECT c FROM Chatteur c WHERE c.sessionId = :sessionId")
    , @NamedQuery(name = "Chatteur.findByDateAdded", query = "SELECT c FROM Chatteur c WHERE c.dateAdded = :dateAdded")
    , @NamedQuery(name = "Chatteur.findByPathDle", query = "SELECT c FROM Chatteur c WHERE c.pathDle = :pathDle")
    , @NamedQuery(name = "Chatteur.findByLogin", query = "SELECT c FROM Chatteur c WHERE c.login = :login")
    , @NamedQuery(name = "Chatteur.findByUsername", query = "SELECT c FROM Chatteur c WHERE c.username = :username")})
@NamedNativeQueries({
    @NamedNativeQuery(name = "Chatteur.findChatteurByKeyWord",
            query = "SELECT * FROM chatteur WHERE USERNAME like CONCAT('%', :search, '%') OR chatteur_id like CONCAT('%', :search, '%')",
            resultClass = Chatteur.class)
//,
//    @NamedNativeQuery(name = "Chatteur.findChatteurByLoginAndPassword",
//            query = "SELECT * FROM chatteur WHERE login=:email AND password=:password",
//            resultClass = Chatteur.class)
})
public class Chatteur extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CHATTEUR_ID")
    private Long chatteurId;
    @Size(max = 255)
    @Column(name = "SESSION_ID")
    private String sessionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_ADDED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
    @Size(max = 255)
    @Column(name = "PATH_DLE")
    private String pathDle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "LOGIN")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USERNAME")
    private String username;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chatteurId")
    private Collection<Message> messageCollection;

    public Chatteur() {
    }

    public Chatteur(Long chatteurId) {
        this.chatteurId = chatteurId;
    }

    public Chatteur(Long chatteurId, Date dateAdded, String login, String username) {
        this.chatteurId = chatteurId;
        this.dateAdded = dateAdded;
        this.login = login;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getChatteurId() {
        return chatteurId;
    }

    public void setChatteurId(Long chatteurId) {
        this.chatteurId = chatteurId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getPathDle() {
        return pathDle;
    }

    public void setPathDle(String pathDle) {
        this.pathDle = pathDle;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chatteurId != null ? chatteurId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chatteur)) {
            return false;
        }
        Chatteur other = (Chatteur) object;
        if ((this.chatteurId == null && other.chatteurId != null) || (this.chatteurId != null && !this.chatteurId.equals(other.chatteurId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + chatteurId;
    }

}
