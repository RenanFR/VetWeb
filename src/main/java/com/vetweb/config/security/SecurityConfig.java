package com.vetweb.config.security;
//@author renan.rodrigues@metasix.com.br

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        auth.inMemoryAuthentication().withUser("renanfr").password("renanfr").roles("admin");
        auth.inMemoryAuthentication().withUser("murillo").password("murillo").roles("admin");
        auth.inMemoryAuthentication().withUser("andre").password("andre").roles("admin");
        auth.inMemoryAuthentication().withUser("usuario").password("usuario").roles("usuario");
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("admin");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/clientes").hasRole("usuario")
                .antMatchers("/usuarios/**").hasRole("admin")
                .antMatchers("/prontuario/**").hasRole("admin")
                .anyRequest().authenticated() 
                .and().formLogin().loginPage("/usuarios/login")
                .permitAll()
                .defaultSuccessUrl("/")
                .failureUrl("/usuarios/fail")
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/WEB-INF/view/exception/403.jsp")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
         web.ignoring().antMatchers("/resources/**");
    }
    
}
