package com.earo.test.spec;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Iterables.toArray;

/**
 * Created by lauearo on 10/05/2017.
 * Specification to query entity illegibility.
 */
public class CustomSpec {
    public static <T> Specification<T> byAuto(final EntityManager entityManager, final T example){
        final Class<T> type = (Class<T>) example.getClass();    //get the class of current entity

        return new Specification<T>(){

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>(); //Predicate list to store condition of query
                EntityType<T> entityType = entityManager.getMetamodel().entity(type);
                for (Attribute<T, ?> attr :
                        entityType.getDeclaredAttributes()) {
                    Object attrValue = getValue(example, attr);
                    if(attrValue != null){  //check if this attribute need to add as a condition
                        if(attr.getJavaType() == String.class){ //only string condition using 'like' query
                            if(!StringUtils.isEmpty(attrValue)) {
                                predicates.add(
                                        criteriaBuilder.like(   //'like' statement
                                                root.get(attribute(entityType, attr.getName(), String.class)),  //field
                                                pattern((String) attrValue) //query value
                                        )
                                );
                            }
                        } else{
                            predicates.add(
                                    criteriaBuilder.equal(  //'equal' statement
                                            root.get(attribute(entityType, attr.getName(), attrValue.getClass())),   //field
                                            attrValue   //query value
                                    )
                            );
                        }
                    }
                }
                //convert predicate list to Predicate
                return predicates.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.and(toArray(predicates, Predicate.class));
            }

            private Object getValue(T example, Attribute<T, ?> attr){
                return ReflectionUtils.getField((Field) attr.getJavaMember(), example);
            }

            //SingularAttribute contain the single value instance of filed
            private <E, T> SingularAttribute<T, E> attribute(EntityType<T> entityType, String fieldName, Class<E> filedClass){
                return entityType.getDeclaredSingularAttribute(fieldName, filedClass);
            }
        };
    }

    static private String pattern(String value){
        return "%" + value + "%";
    }
}
