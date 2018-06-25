package com.vetweb.dao.auth;
//@author renan.rodrigues@metasix.com.br

import com.vetweb.model.auth.Usuario;
import com.vetweb.dao.IDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;


@Repository 
public class UsuarioDAO implements IDAO<Usuario>{
	
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void salvar(Usuario usuario) {
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword())); 
        entityManager.persist(usuario);
    }

    @Override
    public List<Usuario> listar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Usuario consultarPorId(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remover(Usuario e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Usuario consultarPorNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long quantidadeRegistros() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
