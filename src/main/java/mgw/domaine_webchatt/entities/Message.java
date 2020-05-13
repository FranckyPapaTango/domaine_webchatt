/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgw.domaine_webchatt.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BigWave
 */
@Entity
@Table(name = "message", catalog = "webchatt_db", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m")
    , @NamedQuery(name = "Message.findByMessageId", query = "SELECT m FROM Message m WHERE m.messageId = :messageId")
    , @NamedQuery(name = "Message.findByDateSent", query = "SELECT m FROM Message m WHERE m.dateSent = :dateSent")
    , @NamedQuery(name = "Message.findBySeenBy", query = "SELECT m FROM Message m WHERE m.seenBy = :seenBy")
    , @NamedQuery(name = "Message.findByIdDestinataire", query = "SELECT m FROM Message m WHERE m.idDestinataire = :idDestinataire")})
@NamedNativeQueries({
    @NamedNativeQuery(name = "Message.findMessageByDestinataire",
            query = "SELECT * FROM message WHERE ((ID_DESTINATAIRE=:idDestinataire AND CHATTEUR_ID=:chatteurId) OR "
                    + "(ID_DESTINATAIRE=:chatteurId AND CHATTEUR_ID=:idDestinataire)) ORDER BY date_sent;",
            resultClass = Message.class),
        @NamedNativeQuery(name = "Message.findMessageOfChatteurByKeyWord",
            query = "SELECT * FROM message WHERE (chatteur_id = :search) OR message_content like CONCAT('%', :search, '%') "
            + "ORDER BY date_sent ASC;",
            resultClass = Message.class)
})
public class Message extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MESSAGE_ID")
    private Long messageId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_SENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSent;
//    @Basic(optional = true)
//    @NotNull
    @Lob
//    @Size(min = 1, max = 2147483647)
    @Size(max = 2147483647)//ne pas mettre de valeur minimale sinon la validation ne permet pas les valeurs nulle et vide
    @Column(name = "MESSAGE_CONTENT")
    private String messageContent;
    @Size(max = 255)
    @Column(name = "SEEN_BY")
    private String seenBy;
    //======= Attribut/colonne/champ rajouté ==========//
    @Size(max = 255)
    @Column(name = "PATH_PJ")
    private String pathPj;
    //=================================================//
    @Basic(optional = false)
//    @NotNull
//    @Column(name = "ID_DESTINATAIRE")
//    private Long idDestinataire;
    @JoinColumn(name = "ID_DESTINATAIRE", referencedColumnName = "CHATTEUR_ID")
    @ManyToOne(optional = false)
    private Chatteur idDestinataire;
    @JoinColumn(name = "CHATTEUR_ID", referencedColumnName = "CHATTEUR_ID")
    @ManyToOne(optional = false)
    private Chatteur chatteurId;

    public Message() {
    }

    public Message(Long messageId) {
        this.messageId = messageId;
    }

    public Message(Long messageId, Date dateSent, String messageContent, Chatteur idDestinataire) {
        this.messageId = messageId;
        this.dateSent = dateSent;
        this.messageContent = messageContent;
        this.idDestinataire = idDestinataire;
    }

    public String getPathPj() {
        return pathPj;
    }

    public void setPathPj(String pathPj) {
        this.pathPj = pathPj;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getSeenBy() {
        return seenBy;
    }

    public void setSeenBy(String seenBy) {
        this.seenBy = seenBy;
    }

    public Chatteur getIdDestinataire() {
        return idDestinataire;
    }

    public void setIdDestinataire(Chatteur idDestinataire) {
        this.idDestinataire = idDestinataire;
    }

    public Chatteur getChatteurId() {
        return chatteurId;
    }

    public void setChatteurId(Chatteur chatteurId) {
        this.chatteurId = chatteurId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageId != null ? messageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ""+ messageId;
    }

}
