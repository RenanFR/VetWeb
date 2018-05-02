package com.vetweb.model.auth;
 // @author 11151504898
import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Perfil implements GrantedAuthority {//Classe p/ autorização no Security
    @Id
    private String descricaoPerfil;
    @Override
    public String getAuthority() {
        return descricaoPerfil;
    }
    
    public Perfil(String descricaoPerfil) {
        this.descricaoPerfil = descricaoPerfil;
    }

    public Perfil() {
    }

    public void setDescricaoPerfil(String descricaoPerfil) {
        this.descricaoPerfil = descricaoPerfil;
    }
    
}
