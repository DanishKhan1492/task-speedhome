package com.speedhome.propertymanagement.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

/**
 * @author Muhammad Danish Khan
 * created 20/5/21 - 6:53 PM
 */
@Entity
@Table(name = "PROPERTY")
@Setter
@Getter
@NoArgsConstructor
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROPERTY_ID", nullable = false)
    private Integer propertyId;

    @Column(name = "PROPERTY_NAME")
    private String propertyName;

    @Column(name = "BUILDUP_SIZE")
    private Integer buildupSize;

    @Column(name = "BEDROOM")
    private Integer bedroom;

    @Column(name = "BATHROOM")
    private Integer bathroom;

    @Column(name = "PARKING")
    private Integer parking;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "STATUS")
    private Short status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private AddressEntity address;

    @ManyToOne
    @JoinColumn(name = "FLOOR_LEVEL_ID")
    private FloorLevelEntity floorLevel;

    @ManyToOne
    @JoinColumn(name = "FURNISHING_ID")
    private FurnishingEntity furnishing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATED_BY")
    private UserEntity createdBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PropertyEntity that = (PropertyEntity) o;
        return propertyId.equals(that.propertyId) && propertyName.equals(that.propertyName) && buildupSize.equals(that.buildupSize) && creationDate.equals(that.creationDate) && status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyId, propertyName, buildupSize, creationDate, status);
    }
}
