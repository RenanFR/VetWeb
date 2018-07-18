package com.vetweb.service;
//@author renan.rodrigues@metasix.com.br


import java.util.Arrays;
import java.util.List;

import com.vetweb.client.UsersClient;
import com.vetweb.model.auth.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioService implements UserDetailsService {
	
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private UsersClient usersClient;  
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Usuario> usuarios = Arrays.asList(usersClient.loadByUsername(username));
        if(usuarios.isEmpty())  throw new UsernameNotFoundException("USUÁRIO " + username + " NÃO ENCONTRADO.   ");
        return usuarios.get(0);
    }
}
