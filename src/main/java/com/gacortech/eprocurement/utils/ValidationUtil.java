package com.gacortech.eprocurement.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ValidationUtil {
    private final Validator validator;

    public void validate(Object o) {
        Set<ConstraintViolation<Object>> violations = validator.validate(o);

        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}
