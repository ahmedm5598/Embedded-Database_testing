package com.example.demo.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter
public class StringToArrayConverter implements AttributeConverter<String[], String> {

    @Override
    public String convertToDatabaseColumn(String[] arr) {
        if(arr==null)
            return null;
        return String.join(",",arr);
    }

    @Override
    public String[] convertToEntityAttribute(String joined) {
        if(joined==null)
            return null;
        return joined.split(",");
    }

}