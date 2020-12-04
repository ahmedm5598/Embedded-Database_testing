package com.example.demo.model;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Setter@Getter@NoArgsConstructor
@Entity
@TypeDef(typeClass = StringArrayType.class, name = "string-array")
@Table(name = "employees")
public class Employee {
    public Employee(String firstName,String lastName, String email) {
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "SERIAL", name = "id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    //@Type(type = "string-array")
    @Column(columnDefinition = "text[]", name = "keywords", nullable = true)
    @Convert(converter = StringToArrayConverter.class)
    private String[] keywords;

    @Column(name = "keywords", insertable = false, updatable = false)
    private String keywordsString; //No need for setter
}
