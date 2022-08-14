package com.api.application.core.persistance.repository.student;

import com.api.application.core.domain.dto.student.StudentFilterRequest;
import com.api.application.core.domain.entity.Student;
import com.api.application.core.utils.core.resquests.FilterRequest;
import com.api.application.core.utils.core.resquests.PaginationRequest;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Repository
public class StudentCriteriaRepository implements StudentRepositoryCustom {

    private final EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    public StudentCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Student> findAllWithFilters(PaginationRequest paginationRequest,
                                            FilterRequest<StudentFilterRequest> studentFilterRequest) {
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);

        Predicate predicate = getPredicate(studentFilterRequest, root);

        criteriaQuery.where(predicate).distinct(true);
        setOrder(paginationRequest, criteriaQuery, root);
        TypedQuery<Student> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(paginationRequest.getPage().getPageNumber() * paginationRequest.getPage().getPageSize());
        query.setMaxResults(paginationRequest.getPage().getPageSize());
        Pageable pageable = getPageable(paginationRequest);
        long pricingCount = getCount(predicate);

        return new PageImpl<>(query.getResultList(), pageable, pricingCount);
    }

    private Predicate getPredicate(FilterRequest<StudentFilterRequest> studentFilterRequestFilter,
                                   Root<Student> root) {
        List<Predicate> predicateList = new ArrayList<>();

        if (studentFilterRequestFilter.getData().getIds() != null) {
            predicateList.add(
                    criteriaBuilder.in(root.get(Student.Fields.ID.toString()))
                            .value(studentFilterRequestFilter.getData().getIds())
            );
        }

        if (!Objects.equals(studentFilterRequestFilter.getStrSearch(), "")) {
            List<Predicate> predicateSearchList = new ArrayList<>();
            String searchLike = "%" + studentFilterRequestFilter.getStrSearch().toUpperCase(Locale.ROOT) + "%";

            predicateSearchList.add(criteriaBuilder.like(
                    criteriaBuilder.upper(root.get(Student.Fields.ID.toString()).as(String.class)), searchLike));

            predicateSearchList.add(criteriaBuilder.like(
                    criteriaBuilder.upper(root.get(Student.Fields.NAME.toString()).as(String.class)), searchLike));

            predicateSearchList.add(criteriaBuilder.like(
                    criteriaBuilder.upper(root.get(Student.Fields.LASTNAME.toString()).as(String.class)), searchLike));

            predicateSearchList.add(criteriaBuilder.like(
                    criteriaBuilder.upper(root.get(Student.Fields.BIRTHDATE.toString()).as(String.class)), searchLike));

            Predicate predicateForSearch
                    = criteriaBuilder.or(predicateSearchList.toArray(new Predicate[0]));
            predicateList.add(predicateForSearch);

        }
        return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
    }

    private void setOrder(PaginationRequest paginationRequest,
                          CriteriaQuery<Student> criteriaQuery,
                          Root<Student> root) {

        if (paginationRequest.getSortByAttribute() == null) {
            setDefaultOrder(criteriaQuery, root);
        } else {
            setOrderByAllColumns(paginationRequest, criteriaQuery, root);
        }
    }

    private void setDefaultOrder(CriteriaQuery<Student> criteriaQuery,
                                 Root<Student> root) {
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get(Student.Fields.ID.toString())));
    }

    private void setOrderByAllColumns(PaginationRequest paginationRequest,
                                      CriteriaQuery<Student> criteriaQuery,
                                      Root<Student> root) {
        if (paginationRequest.getAscendingOrder().equals(Boolean.TRUE)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(paginationRequest.getSortByAttribute())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(paginationRequest.getSortByAttribute())));
        }
    }

    private Pageable getPageable(PaginationRequest paginationRequest) {
        Sort sort;
        if (paginationRequest.getSortByAttribute() == null) {
            sort = Sort.by("DESC", "id");
        } else {
            sort = Sort.by(paginationRequest.getSortByAttribute(), paginationRequest.getSortByAttribute());
        }
        return PageRequest.of(paginationRequest.getPage().getPageNumber(), paginationRequest.getPage()
                .getPageSize(), sort);
    }

    private long getCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Student> countRoot = countQuery.from(Student.class);

        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate).distinct(true);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}