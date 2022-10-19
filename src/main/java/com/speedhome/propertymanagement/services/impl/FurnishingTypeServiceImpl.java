package com.speedhome.propertymanagement.services.impl;

import com.speedhome.propertymanagement.daos.FurnishingDao;
import com.speedhome.propertymanagement.dtos.FurnishingTypesDto;
import com.speedhome.propertymanagement.services.FurnishingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Muhammad Danish Khan
 * created 21/5/21 - 12:50 PM
 */
@Service
@RequiredArgsConstructor
public class FurnishingTypeServiceImpl implements FurnishingService {

    private final FurnishingDao furnishingDao;


    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<FurnishingTypesDto> getAllFurnishingTypes() {
        List<FurnishingTypesDto> furnishingTypes = new ArrayList<>();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        furnishingDao.findAll().forEach(furnishingEntity -> furnishingTypes.add(modelMapper.map(furnishingEntity, FurnishingTypesDto.class)));
        return furnishingTypes;
    }
}
