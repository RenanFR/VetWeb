package com.vetweb.config;
 // @author 11151504898
import com.vetweb.config.security.SecurityConfig;
import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class DispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected Class<?>[] getRootConfigClasses() {//Classes carregadas em 1° ao iniciar servidor
        return new Class[]{SecurityConfig.class, AppWebConfiguration.class, ConfigJPA.class};
        //O filtro do Security é carregado antes do que o Spring
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {//Demais classes de configuração a serem carregadas/lidas
        return new Class[]{};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};//Informa o padrão de endereço delegado ao Spring MVC
    }

    @Override
    protected Filter[] getServletFilters() {//Filtros executados na aplicação
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        OpenEntityManagerInViewFilter managerInViewFilter = new OpenEntityManagerInViewFilter();
            //Permite que o EntityManager permaneça aberto ao fim da transação para carregar relacionamentos em LAZY
        return new Filter[]
        {
            encodingFilter, managerInViewFilter
        };
    }
    
    @Override
    protected void customizeRegistration(Dynamic registration) {//Configurações relativas ao armazenamento de arquivos
    	registration.setMultipartConfig(new MultipartConfigElement(""));
    }
    
}
