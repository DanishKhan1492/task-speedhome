package com.speedhome.propertymanagement.dtos;

import com.speedhome.propertymanagement.entities.PropertyEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Muhammad Danish Khan
 * @created 21/5/21 - 3:43 PM
 */
public class GenericSpecification implements Specification<PropertyEntity> {
    private SearchCriteria searchCriteria;

    public GenericSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<PropertyEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(searchCriteria.getPropertyName())) {
            predicates.add(builder.and(builder.like(builder.lower(root.get("propertyName")), '%' + searchCriteria.getPropertyName() + '%')));
        }

        if (!StringUtils.isEmpty(searchCriteria.getAddress())) {
            predicates.add(builder.and(builder.like(builder.lower(root.join("address").get("fullAddress")), '%' + searchCriteria.getPropertyName() + '%')));
        }

        if (searchCriteria.getBathroom() != null && !searchCriteria.getBathroom().equals(0)) {
            predicates.add(builder.and(builder.equal(root.get("bathroom"), searchCriteria.getBathroom())));
        }

        if (searchCriteria.getBedroom() != null && !searchCriteria.getBedroom().equals(0)) {
            predicates.add(builder.and(builder.equal(root.get("bedroom"), searchCriteria.getBedroom())));
        }

        if (searchCriteria.getBuildUpSize() != null && !searchCriteria.getBuildUpSize().equals(0)) {
            predicates.add(builder.and(builder.equal(root.get("buildupSize"), searchCriteria.getBuildUpSize())));
        }

        if (searchCriteria.getFloorLevelId() != null && !searchCriteria.getFloorLevelId().equals(0)) {
            predicates.add(builder.and(builder.equal(root.join("floorLevel").get("floorLevelId"), searchCriteria.getFloorLevelId())));
        }

        if (searchCriteria.getFurnishingTypeId() != null && !searchCriteria.getFurnishingTypeId().equals(0)) {
            predicates.add(builder.and(builder.equal(root.join("furnishing").get("furnishingId"), searchCriteria.getFurnishingTypeId())));
        }

        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
