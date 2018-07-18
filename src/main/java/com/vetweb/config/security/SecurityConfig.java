package com.vetweb.config.security;
//@author renan.rodrigues@metasix.com.br

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.vetweb.client.ProfileClient;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private ProfileClient profileClient;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        auth.inMemoryAuthentication().withUser("renanfr").password("renanfr").roles("admin");
        auth.inMemoryAuthentication().withUser("usuario").password("usuario").roles("usuario");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	Map<String, List<String>> permissionsWithProfiles = profileClient.getPermissionsWithProfiles();
    	ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry auth = http.authorizeRequests();
    	for (Map.Entry<String, List<String>> perm : permissionsWithProfiles.entrySet()) {
    		List<String> profiles = perm.getValue();
			auth.antMatchers(perm.getKey()).hasAnyRole(profiles.toArray(new String[profiles.size()]));
    	}
    	auth
                .antMatchers("/endpoint/auth").permitAll()
                .antMatchers("/integration/mappings").permitAll()
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
