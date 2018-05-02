package com.vetweb.dao.auth;
// @author 11151504898

import com.vetweb.model.auth.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository//Obj. p/ persistência gerenciado pelo spring
public class UsuarioDAOImpl implements UserDetailsService {//UserDetailsService:    Interface usada p/ injeção
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//Retorna Usuario que implementa UserDetails
        //JPQL p/ consulta de usuários no banco de dados
        List<Usuario> usuarios = entityManager.createQuery("select u from Usuario u where u.username = :username", Usuario.class) 
                .setParameter("username", username).getResultList();//setParameter:    Substitui paramêtro após : no JPQL pelo 2° argumento
        if(usuarios.isEmpty())  throw new UsernameNotFoundException("Usuário " + username + " não encontrado.   ");
        return usuarios.get(0);
    }
}
