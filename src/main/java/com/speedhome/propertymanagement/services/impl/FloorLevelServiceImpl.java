package com.speedhome.propertymanagement.services.impl;

import com.speedhome.propertymanagement.daos.FloorLevelDao;
import com.speedhome.propertymanagement.dtos.FloorLevelDto;
import com.speedhome.propertymanagement.entities.FloorLevelEntity;
import com.speedhome.propertymanagement.services.FloorLevelService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Muhammad Danish Khan
 * @created 21/5/21 - 12:36 PM
 */
@Service
public class FloorLevelServiceImpl implements FloorLevelService {

    @Autowired
    private FloorLevelDao floorLevelDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
   @Transactional(readOnly = true)
    public List<FloorLevelDto> getAllFloors() {
        List<FloorLevelDto> floorLevels = new ArrayList<>();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        floorLevelDao.findAll().forEach(floorLevelEntity -> floorLevels.add(modelMapper.map(floorLevelEntity, FloorLevelDto.class)));
        return floorLevels;
    }
}
