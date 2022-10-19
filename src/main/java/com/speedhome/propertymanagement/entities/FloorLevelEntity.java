package com.speedhome.propertymanagement.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
 * created 20/5/21 - 6:53 PM
 */
@Entity
@Table(name = "FLOOR_LEVEL")
@Setter
@Getter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FloorLevelEntity that = (FloorLevelEntity) o;
        return floorLevelId.equals(that.floorLevelId) && floorLevelName.equals(that.floorLevelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(floorLevelId, floorLevelName);
    }
}
