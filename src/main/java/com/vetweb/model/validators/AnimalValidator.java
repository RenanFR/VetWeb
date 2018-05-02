package com.vetweb.model.validators;
// @author 11151504898

import com.vetweb.model.Animal;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AnimalValidator implements Validator {//Interface do módulo de validação do spring
    @Override
    public boolean supports(Class<?> type) {//Usado p/ informar qual classe (Modelo) esse validador suporta. Para qual classe ele serve
        return Animal.class.isAssignableFrom(type);
    }
    @Override
    public void validate(Object o, Errors errors) {//Responsável pelas regras de validação p/ a entidade Animal
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "field.required");//errors armazena as falhas de validação
        //helper com validações básicas, provido pelo spring. 
        //reject    Recebem o errors onde será armazenada o registro da falha, o atributo da entidade sendo validado e a chave p/ recuperação do erro
        //Se o atributo a ser validado for composição usar .
        Animal animal = (Animal)o;
        if(animal.getPeso() == 0){
            errors.rejectValue("peso", "field.required");//rejectValue, usado p/ criar regra de validação customizada inexistente na ValidationUtils
        }
    }
    
}
