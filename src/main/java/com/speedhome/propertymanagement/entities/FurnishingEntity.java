package com.speedhome.propertymanagement.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author Muhammad Danish Khan
 * @created 20/5/21 - 6:53 PM
 */
@Entity
@Table(name = "FURNISHING")
@Data
@NoArgsConstructor
public class FurnishingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FURNISHING_ID")
    private Integer furnishingId;

    @Column(name = "FURNISHING_TYPE")
    private String furnishingType;

    public FurnishingEntity(Integer furnishingId) {
        this.furnishingId = furnishingId;
    }
}
