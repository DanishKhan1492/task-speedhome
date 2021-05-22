package com.speedhome.propertymanagement.entities;

import lombok.Data;

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
 * @created 20/5/21 - 6:53 PM
 */
@Entity
@Table(name = "ADDRESS")
@Data
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Integer addressId;

    @Column(name = "FULL_ADDRESS")
    private String fullAddress;

    @Column(name = "POST_CODE")
    private Integer postCode;
}
