package com.netcracker.repository;


import com.netcracker.jpa.AuthorizationApp;
import com.netcracker.jpa.Service;
import com.netcracker.jpa.Status;
import com.netcracker.jpa.User;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public final class SpecificationForJpa {
    public static Specification<AuthorizationApp> checkRetriesLogin(final String login){
        return new Specification<AuthorizationApp>() {
            @Override
            public Predicate toPredicate(Root<AuthorizationApp> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("login"), login);
            }
        };
    }

    public static Specification<AuthorizationApp> checkRetriesUser(final User user){
        return new Specification<AuthorizationApp>() {
            @Override
            public Predicate toPredicate(Root<AuthorizationApp> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("user"), user);
            }
        };
    }

    public static Specification<AuthorizationApp> checkRetriesService(final Service service){
        return new Specification<AuthorizationApp>() {
            @Override
            public Predicate toPredicate(Root<AuthorizationApp> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("service"), service);
            }
        };
    }

    public static Specification<Status> checkRetriesStatus(final String status){
        return new Specification<Status>() {
            @Override
            public Predicate toPredicate(Root<Status> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("statusName"), status);
            }
        };
    }
}
