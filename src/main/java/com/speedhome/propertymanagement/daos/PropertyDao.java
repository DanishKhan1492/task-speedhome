package com.speedhome.propertymanagement.daos;

import com.speedhome.propertymanagement.entities.PropertyEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

/**
 * @author Muhammad Danish Khan
 * @created 21/5/21 - 12:31 PM
 */
public interface PropertyDao extends PagingAndSortingRepository<PropertyEntity, Integer>, JpaSpecificationExecutor<PropertyEntity> {
    @Modifying
    @Query("update PropertyEntity p set p.status=:status where p.propertyId=:propertyId")
    int updatePropertyStatus(@Param("status") Short status, @Param("propertyId") Integer propertyId);
}
