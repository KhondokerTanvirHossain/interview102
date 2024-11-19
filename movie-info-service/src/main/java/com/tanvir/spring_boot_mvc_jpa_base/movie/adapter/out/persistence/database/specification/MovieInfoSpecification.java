package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.specification;

import com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.persistence.database.entity.MovieInfoDatabaseEntity;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieInfoSpecification {

    public static Specification<MovieInfoDatabaseEntity> getFilterSpecifications(Map<String, String> filters, Metamodel metamodel) {
        EntityType<MovieInfoDatabaseEntity> entityType = metamodel.entity(MovieInfoDatabaseEntity.class);

        return (root, query, criteriaBuilder) -> {
            if (filters.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return filters.entrySet().stream()
                .map(entry -> {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    Class<?> fieldType = entityType.getAttribute(key).getJavaType();

                    if (fieldType.equals(Integer.class)) {
                        return criteriaBuilder.equal(root.get(key), Integer.parseInt(value));
                    } else if (fieldType.equals(Long.class)) {
                        return criteriaBuilder.equal(root.get(key), Long.parseLong(value));
                    } else if (fieldType.equals(Double.class)) {
                        return criteriaBuilder.equal(root.get(key), Double.parseDouble(value));
                    } else {
                        return criteriaBuilder.equal(root.get(key), value);
                    }
                })
                .reduce(criteriaBuilder::and)
                .orElse(criteriaBuilder.conjunction());
        };
    }

    public static Specification<MovieInfoDatabaseEntity> getListFilterSpecifications(Map<String, List<String>> filters, Metamodel metamodel) {
        EntityType<MovieInfoDatabaseEntity> entityType = metamodel.entity(MovieInfoDatabaseEntity.class);

        return (root, query, criteriaBuilder) -> {
            if (filters.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return filters.entrySet().stream()
                .map(entry -> {
                    String key = entry.getKey();
                    List<String> values = entry.getValue();
                    Class<?> fieldType = entityType.getAttribute(key).getJavaType();

                    if (fieldType.equals(Integer.class)) {
                        List<Integer> intValues = values.stream().map(Integer::parseInt).collect(Collectors.toList());
                        return root.get(key).in(intValues);
                    } else if (fieldType.equals(Long.class)) {
                        List<Long> longValues = values.stream().map(Long::parseLong).collect(Collectors.toList());
                        return root.get(key).in(longValues);
                    } else if (fieldType.equals(Double.class)) {
                        List<Double> doubleValues = values.stream().map(Double::parseDouble).collect(Collectors.toList());
                        return root.get(key).in(doubleValues);
                    } else {
                        return root.get(key).in(values);
                    }
                })
                .reduce(criteriaBuilder::and)
                .orElse(criteriaBuilder.conjunction());
        };
    }
}
