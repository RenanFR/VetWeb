package com.vetweb.model.validators;
// @author Maria Jéssica

import com.vetweb.model.Pessoa;
import com.vetweb.model.Proprietario;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ProprietarioValidator implements Validator
{

    @Override
    public boolean supports(Class<?> type) {
        return Proprietario.class.isAssignableFrom(type);
        
    }

    @Override
    public void validate(Object o, Errors errors) {
        Proprietario p = (Proprietario)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rg", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpf", "field.required");
        String sexo = String.valueOf(p.getSexo());
        System.out.println(sexo);
        if (!(sexo.equalsIgnoreCase("M") || sexo.equalsIgnoreCase("F"))){
            errors.rejectValue("sexo", "field.invalidValue");
        }
        Pessoa.TipoPessoa tipoPessoa = p.getTipoPessoa();
        System.out.println(tipoPessoa);
        if(!(tipoPessoa == Pessoa.TipoPessoa.FISICA || tipoPessoa == Pessoa.TipoPessoa.JURIDICA)) {
            errors.rejectValue("tipoPessoa", "field.invalidValue");
        }
        ValidationUtils.rejectIfEmpty(errors, "inclusao", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "nacionalidade", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "nascimento", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "profissao", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "comoNosConheceu", "field.required");
    }

}
