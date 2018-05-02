package com.vetweb.model.auth;
 // @author 11151504898
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario implements UserDetails {
    @Id
    private String username;
    private String password;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)//Cria a tabela de associação (Auxiliar) entre usuários e perfis
    private List<Perfil> authorities = new ArrayList<>();//Papéis/Perfis de acesso associados ao usuário
    @Override
    public Collection<Perfil> getAuthorities() {//? extends GrantedAuthority
        return authorities;
    }
    
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    //Métodos abaixo podem ser usados p/ renovação de senha, bloqueio por inatividade, etc.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }    

    public Usuario() {
        
    }
    
    public Usuario(String username, String password, String name) {
        this.username = username;
        this.name = name;
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthorities(List<Perfil> authorities) {
        this.authorities = authorities;
    }
    
}
