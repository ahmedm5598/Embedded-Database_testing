package com.example.demo.model;

import lombok.AllArgsConstructor;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.regex.Pattern;


public class EmployeeSpecification extends Dialect implements Specification<Employee> {
    private SearchCriteria criteria;
    private List<String> types;

    public EmployeeSpecification(SearchCriteria criteria, List<String> types) {
        super();
        registerFunction("regexp_match", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "?1 regexp_match() ?2"));
        this.criteria = criteria;
        this.types = types;
    }

    @Override
    public Predicate toPredicate
            (Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        // for in operator
        if (types != null && !types.isEmpty()) {
            return root.get(criteria.getKey()).in(types);
        }

        if(criteria.getOperation() == null) {
            Pattern regexPattern = Pattern.compile("metro:new-england:[a-z\\-\\_]*[^:]$");

            Expression<String> patternExpression = builder.<String>literal(regexPattern.pattern());

            Path<String> path = root.<String>get(criteria.getKey());// regex comparison column

            Predicate theRegexPredicate = builder.equal(builder.function("regexp_matches", Integer.class, path, patternExpression), 1);

            return theRegexPredicate;
        }

        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
