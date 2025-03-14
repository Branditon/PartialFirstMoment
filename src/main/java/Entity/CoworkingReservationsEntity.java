/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author brandonescudero
 */
@Entity
@Table(name = "coworkingReservation")
public class CoworkingReservationsEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "userName", nullable = false)
    private String userName;
    
    @Column(name = "reservationDate", nullable = false)
    private Date reservationDate;
    
    @Column(name = "workspace", nullable = false)
    private String workspace;
    
    @Column(name = "reservationDuration", nullable = false)
    private String reservationDuration;

    public CoworkingReservationsEntity(Long id, String userName, Date reservationDate, String workspace, String reservationDuration) {
        this.id = id;
        this.userName = userName;
        this.reservationDate = reservationDate;
        this.workspace = workspace;
        this.reservationDuration = reservationDuration;
    }

    public CoworkingReservationsEntity() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public String getReservationDuration() {
        return reservationDuration;
    }

    public void setReservationDuration(String reservationDuration) {
        this.reservationDuration = reservationDuration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof CoworkingReservationsEntity)) {
            return false;
        }
        CoworkingReservationsEntity other = (CoworkingReservationsEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.CoworkingReservationsEntity[ id=" + id + " ]";
    }
    
}
