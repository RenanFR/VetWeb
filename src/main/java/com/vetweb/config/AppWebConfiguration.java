package com.vetweb.config;
// @author 11151504898

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.vetweb.dao.ConfigDAO;
import com.vetweb.model.Clinica;

@EnableWebMvc//Habilita funcionalidades do Spring (Serialização, validações, etc.)
@Configuration
@ComponentScan(basePackages = {"com.vetweb.controller", "com.vetweb.dao", 
    "com.vetweb.model", "com.vetweb.dao.auth", "com.vetweb.model.auth", "com.vetweb.advice",
    "com.vetweb.model.error", "com.vetweb.model.pojo", "com.vetweb.service"})
//Informa ao Spring os pacotes cujas classes devem ser lidas e carregadas
public class AppWebConfiguration extends WebMvcConfigurerAdapter implements WebApplicationInitializer {//Classe de configurações
	
	@Autowired
	private ConfigDAO configDAO;
	
    @Bean//Retorna objeto gerenciado pelo container
    public InternalResourceViewResolver internalResourceViewResolver() {//Config. um tipo de View p/ a aplicação
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();//Objeto responsável por interpretar as rotas da controller em páginas
        resolver.setPrefix("/WEB-INF/view/");//Informa o caminho base para busca de recursos
        resolver.setSuffix(".jsp");//Informa a extensão das páginas a serem buscadas
        return resolver;
    }
    
    @Bean//Config. do messages.properties
    public MessageSource messageSource(){//Para busca do arquivo de propriedades com o mapa de mensagens. Bean deve ter o nome messageSource
        ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
        bundle.setBasename("/WEB-INF/messages");//Informa localização e nome do arquivo de propriedades
        bundle.setDefaultEncoding("UTF-8");//Codificação
        bundle.setCacheSeconds(1);//Recarrega o arquivo a cada 1 segundo
        return bundle;
    }
    
    @Bean//Registra conversores de formato p/ a aplicação
    public FormattingConversionService mvcConversionService(){//Nome do método tem que ser mvcConversionService
        FormattingConversionService formattingConversionService = new DefaultFormattingConversionService(true);
        DateTimeFormatterRegistrar formatter = new DateTimeFormatterRegistrar();
        formatter.setDateFormatter((DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        formatter.registerFormatters(formattingConversionService);
        return formattingConversionService;
    }
    
    @Override//Sendo usado p/ habilitar mapeamento de recursos css & js
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override//o	Permite mapear páginas para determinada URL sem a necessidade de criar uma controller somente para realizar esse mapeamento
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index");//O caminho / redireciona para a página index com as configurações do internalResourceViewResolver
        registry.addViewController("/forbidden").setViewName("/login/403");
        registry.addViewController("/table").setViewName("/proprietario/table");
        registry.addViewController("/").setStatusCode(HttpStatus.NOT_FOUND).setViewName("/exception/404");
//        registry.addViewController("/cadastroEspecie").setViewName("/animal/cadastroEspecie");
//        registry.addViewController("/cadastroPelagem").setViewName("/animal/cadastroPelagem");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {//Filtros aplicados antes/depois de requisições
//        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();//Busca na URL o parâmetro de localização selecionado pelo usuário
//        interceptor.setParamName("local");//Nome do parâmetro na URL que irá engatilhar o filtro de localização
        registry.addInterceptor(new LocaleChangeInterceptor());//Para observar mudança de idioma
    }
    
    @Bean
    public LocaleResolver localeResolver(){
        return new CookieLocaleResolver();//Armazena a localização selecionada em Cookie em função do parâmetro recebido pelo LocaleChangeInterceptor
    }
    
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.setInitParameter("razaoSocial", "vetwork");		
		servletContext.setInitParameter("fundadaEm", LocalDate.now()
				.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));	
		servletContext.setInitParameter("cnpj", "11.545.952/0001-07");
		servletContext.setInitParameter("proprietario", "proprietario");
	}
	
}
