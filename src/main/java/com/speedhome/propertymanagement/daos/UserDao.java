package com.speedhome.propertymanagement.daos;

import com.speedhome.propertymanagement.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Muhammad Danish Khan
 * created 20/5/21 - 7:07 PM
 */
public interface UserDao extends CrudRepository<UserEntity, Integer> {
    UserEntity findByUsername(@Param("username") String username);
}
