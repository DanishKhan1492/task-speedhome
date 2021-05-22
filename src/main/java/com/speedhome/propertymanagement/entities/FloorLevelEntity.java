package com.speedhome.propertymanagement.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
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
@Table(name = "FLOOR_LEVEL")
@Data
@NoArgsConstructor
public class FloorLevelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLOOR_LEVEL_ID")
    private Integer floorLevelId;

    @Column(name = "FLOOR_LEVEL_NAME")
    private String floorLevelName;

    public FloorLevelEntity(Integer floorLevelId) {
        this.floorLevelId = floorLevelId;
    }
}
