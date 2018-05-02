package com.vetweb.dao;
// @author Maria Jéssica

import com.vetweb.model.Clinica;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
@Repository
public class ConfigDAO {
    @PersistenceContext
    EntityManager entityManager;
    public void salvarClinica(Clinica c){
        if(c.getCnpj() == null) entityManager.persist(c);
        else entityManager.merge(c);
    }
    public Clinica clinicaPorCnpj(String cnpj){
        return entityManager.createQuery("SELECT c FROM Clinica c WHERE c.cnpj = :cnpj", Clinica.class)
                .setParameter("cnpj", cnpj).getSingleResult();
    }
    public Clinica clinica(){
        return entityManager.createQuery("SELECT c FROM Clinica c ORDER BY c.cnpj ASC", Clinica.class)
                .getResultList().stream().findFirst().get();
    }
}
