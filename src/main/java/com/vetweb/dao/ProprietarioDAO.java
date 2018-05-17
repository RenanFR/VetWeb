package com.vetweb.dao;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vetweb.model.Animal;
import com.vetweb.model.Prontuario;
import com.vetweb.model.Proprietario;

 //@author renanrodrigues
@Repository//Indica que a classe é gerenciada pelo container do Spring e é uma entidade de persistência
public class ProprietarioDAO implements IDAO<Proprietario> {
	
    @PersistenceContext//Especificação da JPA para receber injeção de objeto de contexto com o banco de dados (EntityManager)
    private EntityManager entityManager;
    
    @Autowired
    private AnimalDAO animalDAO;
    
    @Override
    public void salvar(Proprietario proprietario) {
        if(proprietario.getPessoaId() == null)
            entityManager.persist(proprietario);//Spring instancia, injeta e finaliza o EntityManager
        else
            entityManager.merge(proprietario);
    }

    @Override
    public List<Proprietario> listar() {
        return entityManager.createQuery("select p from Proprietario p", Proprietario.class).getResultList();
    }

    @Override
    public Proprietario consultarPorId(long id) {
        return entityManager.find(Proprietario.class, id);
    }

    @Override
    public void remover(Proprietario proprietario) {
        entityManager.remove(proprietario);
    }
    
    public void removerAnimal(long animalId) {
        Animal animal = animalDAO.consultarPorId(animalId);
        Proprietario proprietario = consultarPorId(animal.getProprietario().getPessoaId());
        Animal a = proprietario.getAnimais().stream().filter(animal1 -> animal1.getAnimalId().equals(animalId)).findFirst().get();
        proprietario.getAnimais().remove(a);
        animalDAO.remover(a);
        System.out.println(a.getNome() + a.getAnimalId());
    }

    @Override
    public Proprietario consultarPorNome(String nome) {
        Optional<Proprietario> o = Optional.of(entityManager.createNamedQuery("proprietarioPorNome", Proprietario.class)
                .setParameter("nomeProprietario", nome).getSingleResult());
        Proprietario p = o.orElseThrow(RuntimeException::new);
        return p;
    }

    @Override
    public long quantidadeRegistros() {
        return entityManager.createNamedQuery("quantidadeClientes", Long.class).getSingleResult();
    }
    
    public List<Prontuario> getBalancoFinanceiro(Long proprietarioId) {
    	return entityManager.createNamedQuery("despesasCliente", Prontuario.class)
    			.setParameter("Id", proprietarioId)
    			.getResultList();
    }

}
