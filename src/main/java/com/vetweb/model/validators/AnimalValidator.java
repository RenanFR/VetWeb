package com.vetweb.model.validators;
//@author renan.rodrigues@metasix.com.br

import org.springframework.validation.Errors;

import com.vetweb.model.Animal;

import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AnimalValidator implements Validator {
	
    @Override
    public boolean supports(Class<?> type) {
        return Animal.class.isAssignableFrom(type);
    }
    
    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "field.required");
        Animal animal = (Animal)o;
        if(animal.getPeso() == 0){
            errors.rejectValue("peso", "field.required");
        }
    }
    
}
