package com.speedhome.propertymanagement.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author Muhammad Danish Khan
 * created 20/5/21 - 6:53 PM
 */
@Entity
@Table(name = "ADDRESS")
@Getter
@Setter
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Integer addressId;

    @Column(name = "FULL_ADDRESS")
    private String fullAddress;

    @Column(name = "POST_CODE")
    private Integer postCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddressEntity that = (AddressEntity) o;
        return addressId.equals(that.addressId) && fullAddress.equals(that.fullAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, fullAddress);
    }
}
