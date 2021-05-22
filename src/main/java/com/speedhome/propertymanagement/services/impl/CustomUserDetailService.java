package com.speedhome.propertymanagement.services.impl;

import com.speedhome.propertymanagement.daos.UserDao;
import com.speedhome.propertymanagement.dtos.CustomUserDetails;
import com.speedhome.propertymanagement.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Muhammad Danish Khan
 * @created 20/5/21 - 5:34 PM
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            UserEntity user = userDao.findByUsername(username);
            return new CustomUserDetails(user.getUsername(), user.getPassword(), new ArrayList<>(), user.getUserId());
        }catch (Exception ex){
            throw new UsernameNotFoundException("User not found");
        }
    }
}
