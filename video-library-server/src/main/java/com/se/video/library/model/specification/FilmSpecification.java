package com.se.video.library.model.specification;

import com.se.video.library.model.Film;
import com.se.video.library.payload.request.FilmListRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

import static org.springframework.data.jpa.domain.Specification.where;


@Component
public class FilmSpecification// extends BaseSpecification<Film, FilmListRequest>
{
//
//
//    @Override
//    public Specification<Film> getFilter(FilmListRequest request) {
//        return (root, query, cb) -> {
//            query.distinct(true);
//            return where(
//                    nameContains(request.getName())).and(directorContains(request.getDirector()))
//                    .and(dateRange(request.getYearStart(), request.getYearEnd())
//
//            ).toPredicate(root, query, cb);
//        };
//    }
//
//    private Specification<Price> betweenAttributes(String attribute, Double minValue, Double maxValue) {
//
//        if (minValue != null && maxValue != null) {
//            return (root, query, cb) -> cb.between(root.get(attribute), minValue, maxValue);
//        } else if (minValue != null) {
//            return greaterThanOrEqualTo("cost", minValue);
//        } else if (maxValue != null) {
//            return lessThanOrEqualTo("cost", maxValue);
//        }
//
//        return null;
//    }
//
//    private Specification<Film> nameContains(String value) {
//        return userAttributeContains("name", value);
//    }
//
//    private Specification<Film> directorContains(String value) {
//        return userAttributeContains("director", value);
//    }
//
//
//    private Specification<Film> userAttributeContains(String attribute, String value) {
//        return (root, query, cb) -> {
//            if(value == null) {
//                return null;
//            }
//
//            return cb.like(
//                    cb.lower(root.get(attribute)),
//                    containsLowerCase(value)
//            );
//        };
//    }
//
//    private Specification<Film> valueMoreThanOrEqualTo(String attribute, Integer value) {
//        return (root, query, cb) -> {
//            if (value == null) {
//                return null;
//            }
//            return cb.lessThanOrEqualTo(root.get(attribute), value);
//        };
//    }
//
//    private Specification<Film> valueLessThanOrEqualTo(String attribute, Integer value) {
//        return (root, query, cb) -> {
//            if (value == null) {
//                return null;
//            }
//            return cb.lessThanOrEqualTo(root.get(attribute), value);
//        };
//    }
//
//    public Specification<Film> calledBetween(String attribute, Integer start, Integer end) {
//        return (root, query, cb) ->{
//            return  cb.between(root.get(attribute), start, end);
//        };
//    }
//
}
