/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgw.domaine_webchatt.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;


import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import mgw.domaine_webchatt.tools.ModuleTools;
import mgw.domaine_webchatt.tools.Validateur;

/**
 *
 * @author smallwave
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //	@Version // l'attribut updatedVersionDate remplace la version !
    //  private int version;    
    @Basic(optional = true)
    @Column(name = "UPDATED_VERSION_DATE")//  
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedVersionDate;
    //	@Version 
    
    @Basic(optional = true)
    @Column(name = "DATE_PERSISTENCE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePersistence;

    @Basic(optional = false)
    @Column(name = "CSS_RAW_COLOR_CODE")
    private String cssRawColorCode;

    
    
    public Date getUpdatedVersionDate() {
		return updatedVersionDate;
	}

	public void setUpdatedVersionDate(Date updatedVersionDate) {
		this.updatedVersionDate = updatedVersionDate;
	}

	public Date getDatePersistence() {
		return datePersistence;
	}

	public void setDatePersistence(Date datePersistence) {
		this.datePersistence = datePersistence;
	}

	public String getCssRawColorCode() {
		return cssRawColorCode;
	}

	public void setCssRawColorCode(String cssRawColorCode) {
		this.cssRawColorCode = cssRawColorCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
     * méthode de rappel initialisant l'attribut datePersistence et l'attribut
     * CssRawColorCode
     */
    @PrePersist
    protected void initDatas() {
        //setUpdatedVersionDate(ModuleTools.asDateHMS(LocalDateTime.now()));//indice d'update et de version
        setDatePersistence(ModuleTools.asDateHMS(LocalDateTime.now()));//indice de creation        
        //datePersistence = ModuleTools.asDateHMS(LocalDateTime.now());
        cssRawColorCode = Validateur.bootstrapRowColorValue(Validateur.generateRandomInt());
    }

    @PreUpdate
    private void onUpdate() {
        setUpdatedVersionDate(ModuleTools.asDateHMS(LocalDateTime.now()));//indice d'update et de version
    }
}
