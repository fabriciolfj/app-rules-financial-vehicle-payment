package com.github.fabriciolfj.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface UseCase {

    @AliasFor(annotation = Service.class)
    String value();

}