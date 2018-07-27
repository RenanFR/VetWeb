package com.vetweb.dao;
//@author renan.rodrigues@metasix.com.br

import com.vetweb.model.Especie;
import com.vetweb.model.Animal;
import com.vetweb.model.Patologia;
import com.vetweb.model.Pelagem;
import com.vetweb.model.Raca;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class AnimalDAO implements IDAO<Animal>{
	
    @PersistenceContext
    private EntityManager entityManager;
    
    @SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(AnimalDAO.class);
    
    @Override
    public void salvar(Animal animal) { 
        if(animal.getAnimalId() == null) {
            entityManager.persist(animal);
        }
        else {
            entityManager.merge(animal);
        }
    }
    
    @Override
    public List<Animal> listarTodos() {
        return entityManager
        		.createQuery("SELECT a FROM Animal a", Animal.class)
        		.getResultList();
    }

    @Override
    public Animal buscarPorId(long id) {
        return entityManager
        		.find(Animal.class, id);
    }

    @Override
    public void remover(Animal e) {
        entityManager.remove(e);
    }

    public Animal buscarPorNome(String nome) {
        Optional<Animal> optionalAnimal = Optional
        		.of(entityManager
        				.createNamedQuery("animalPorNome", Animal.class)
        				.setParameter("nomeAnimal", nome)
        				.getSingleResult());                
        Animal a = optionalAnimal.orElseThrow(RuntimeException::new);
        return a;
    }
    
    public long buscarQuantidade() {
        return entityManager
        		.createNamedQuery("quantidadeAnimais", Long.class)
        		.getSingleResult();
    }
    
    public void salvarEspecie(Especie especie){
        if(especie.getEspecieId() == null) {
            entityManager.persist(especie);
        } else{
            entityManager.merge(especie);
        }
    }
    
    public void salvarRaca(Raca raca){
        if(raca.getRacaId() == null)
            entityManager.persist(raca);
        else
            entityManager.merge(raca);
    }
    
    public void salvarPelagem(Pelagem pelagem){
        if(pelagem.getPelagemId()== null)
            entityManager.persist(pelagem);
        else
            entityManager.merge(pelagem);
    }
    
    public void salvarPatologia(Patologia patologia){
        if(patologia.getNome() == null)
            entityManager.persist(patologia);
        else
            entityManager.merge(patologia);
    }
    
    public List<Pelagem> buscarPelagens(){
        return entityManager
        		.createQuery("SELECT p FROM Pelagem p", Pelagem.class)
        		.getResultList();
    }
    
    public List<Especie> buscarEspecies(){
        return entityManager
        		.createQuery("SELECT e FROM Especie e", Especie.class)
        		.getResultList();
    }
    
    public List<Raca> buscarRacas(){
        return entityManager
        		.createQuery("SELECT r FROM Raca r", Raca.class)
        		.getResultList();
    }
    
    public List<Patologia> buscarPatologias(){
        return entityManager
        		.createQuery("SELECT pat FROM Patologia pat", Patologia.class)
        		.getResultList();
    }
    
    public Especie buscarEspeciePorDescricao(String especie){
        return entityManager
        		.createQuery("SELECT e FROM Especie e WHERE e.descricao = :desc", Especie.class)
                .setParameter("desc", especie)
                .getSingleResult();
    }
    
    public List<Raca> buscarRacasPorEspecie(String especie){
        return entityManager
        		.createQuery("SELECT r FROM Raca r WHERE r.especie.descricao = :especie", Raca.class)
                .setParameter("especie", especie)
                .getResultList();
    }
    
    public Raca buscarRacaPorDescricao(String raca){
        return entityManager
        		.createQuery("SELECT r FROM Raca r WHERE r.descricao = :descricao", Raca.class)
                .setParameter("descricao", raca)
                .getSingleResult();
    }
    
    public Pelagem buscarPelagemPorDescricao(String pelagem){
        return entityManager
        		.createQuery("SELECT p FROM Pelagem p WHERE p.descricao = :descricao", Pelagem.class)
                .setParameter("descricao", pelagem)
                .getSingleResult();
    }
    
    public Patologia buscarPatologiaPorDescricao(String patologia){
        return entityManager
        		.createQuery("SELECT p FROM Patologia p WHERE p.nome = :nome", Patologia.class)
                .setParameter("nome", patologia)
                .getSingleResult();
    }
    
    public Especie buscarEspeciePorId(Long especieId) {
        return entityManager
        		.find(Especie.class, especieId);
    }
    
    public Raca buscarRacaPorId(Long racaId) {
        return entityManager
        		.find(Raca.class, racaId);
    }
    
    public Pelagem buscarPelagemPorId(Long pelagemId) {
        return entityManager
        		.find(Pelagem.class, pelagemId);
    }
    
    public void removerEspecie(Especie especie) {
        entityManager.remove(especie);
    }
    
    public void removerPelagem(Pelagem pelagem) {
        entityManager.remove(pelagem);
    }
    
    public void removerRaca(Raca raca) {
        entityManager.remove(raca);
    }
    
    public void removerPatologia(Patologia patologia) {
        entityManager.remove(patologia);
    }
    
}
