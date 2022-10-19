package com.speedhome.propertymanagement.services.impl;

import com.speedhome.propertymanagement.daos.PropertyDao;
import com.speedhome.propertymanagement.dtos.GenericSpecification;
import com.speedhome.propertymanagement.dtos.PropertyDto;
import com.speedhome.propertymanagement.dtos.SearchCriteria;
import com.speedhome.propertymanagement.entities.FloorLevelEntity;
import com.speedhome.propertymanagement.entities.FurnishingEntity;
import com.speedhome.propertymanagement.entities.PropertyEntity;
import com.speedhome.propertymanagement.entities.UserEntity;
import com.speedhome.propertymanagement.services.PropertyService;
import com.speedhome.propertymanagement.utils.exceptions.NoPropertyFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Muhammad Danish Khan
 * created 21/5/21 - 1:51 PM
 */
@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyDao propertyDao;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public PropertyDto createAndUpdateProperty(PropertyDto property) {
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        PropertyEntity propertyEntity = modelMapper.map(property, PropertyEntity.class);
        propertyEntity.setCreatedBy(new UserEntity(property.getCreatedBy()));
        propertyEntity.setFloorLevel(new FloorLevelEntity(property.getFloorLevelId()));
        propertyEntity.setFurnishing(new FurnishingEntity(property.getFurnishingTypeId()));
        propertyEntity.setCreationDate(new Date());
        propertyDao.save(propertyEntity);
        property.setPropertyId(propertyEntity.getPropertyId());
        property.getAddress().setAddressId(propertyEntity.getAddress().getAddressId());
        property.setCreationDate(propertyEntity.getCreationDate());
        return property;
    }

    @Override
    @Transactional
    public void approveProperty(Integer propertyId, Boolean status) throws NoPropertyFoundException {
        int rowUpdated = propertyDao.updatePropertyStatus(status ? (short) 1 : (short) 0, propertyId);
        if(rowUpdated == 0){
            throw new NoPropertyFoundException("No Property found with ID: "+ propertyId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PropertyDto> searchProperty(SearchCriteria searchCriteria) throws NoPropertyFoundException {
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        Pageable pageable = PageRequest.of(searchCriteria.getPageNumber(), searchCriteria.getPageSize());
        Page<PropertyEntity> propertiesPage = propertyDao.findAll(new GenericSpecification(searchCriteria), pageable);
        if(propertiesPage.hasContent()){
            return propertiesPage.getContent().stream().map(property -> modelMapper.map(property, PropertyDto.class)).collect(Collectors.toList());
        }else{
            throw new NoPropertyFoundException("No Property Found against the search criteria");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PropertyDto> getAllProperties() {
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        List<PropertyDto> properties = new ArrayList<>();
        propertyDao.findAll().forEach(propertyEntity ->{
            modelMapper.typeMap(PropertyEntity.class, PropertyDto.class).addMappings(mapper -> mapper.skip(PropertyDto::setCreatedBy));
            PropertyDto property = modelMapper.map(propertyEntity, PropertyDto.class);
            property.setCreatedBy(propertyEntity.getCreatedBy().getUserId());
            properties.add(property);
        });
        return properties;
    }
}
