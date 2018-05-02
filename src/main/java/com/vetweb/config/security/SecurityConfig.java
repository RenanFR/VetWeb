package com.vetweb.config.security;
 //@author est.renanfr
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity//Habilita o filtro de acesso as URL(s)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//Classe de configuração do Security. Regras de acesso
    @Autowired//Injeção de objeto implementador da interface p/ busca de usuários
    private UserDetailsService userDetailsService;
    //
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());//Associa Security ao UserDetailsService
            //Configura o método (Algoritmo) de criptografia
        auth.inMemoryAuthentication().withUser("renanfr").password("renanfr").roles("admin");//Usuários p/ homologação
        auth.inMemoryAuthentication().withUser("murillo").password("murillo").roles("admin");
        auth.inMemoryAuthentication().withUser("andre").password("andre").roles("admin");
        auth.inMemoryAuthentication().withUser("usuario").password("usuario").roles("usuario");//Criados em memória durante execução
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("admin");
            //  Autenticação em mémoria. Evita necessidade de Seed de usuários no banco de dados
    }
    
    //
    @Override
    protected void configure(HttpSecurity http) throws Exception {//Método com as regras de controle de acesso
        http.authorizeRequests()//Obj. de configuração das regras de acesso
                .antMatchers("/clientes").hasRole("usuario")//Precisa ser Admin p/ cadastrar
                .antMatchers("/usuarios/**").hasRole("admin")//Define restrições em primeiro lugar. ** restringe por prefixo
                .antMatchers("/prontuario/**").hasRole("admin")
//                .antMatchers("/prontuario/adicionarAtendimento").permitAll()
                //cadastro permite acesso c/ qualquer Role. Seguir ordem:   Restrições - Liberações 
                .anyRequest().authenticated()//Todo método exige autenticação. 
                .and().formLogin().loginPage("/usuarios/login")
                .permitAll()//Página de login customizada (Do contrário usa padrão spring)
                    //Autenticação baseada em formulário
//                .defaultSuccessUrl("/home/index")//Url padrão p/ login bem sucedido
                .defaultSuccessUrl("/")//Url padrão p/ login bem sucedido
                .failureUrl("/usuarios/fail")//Url padrão p/ falha no login
                .and().csrf()//Proteção contra Cross-Site Request Forgery
                .and().exceptionHandling().accessDeniedPage("/WEB-INF/view/exception/403.jsp")//Página customizada p/ acesso negado 403 Forbidden
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
        //and:  Volta ao Obj. authorizeRequests para adc. configurações        
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
         web.ignoring().antMatchers("/resources/**");
    }
}
