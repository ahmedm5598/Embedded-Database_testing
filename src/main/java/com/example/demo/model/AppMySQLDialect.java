package com.example.demo.model;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class AppMySQLDialect extends Dialect {

    public AppMySQLDialect() {
        super();
        registerFunction("regexp", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "?1 regexp_matches ?2"));

    }
}