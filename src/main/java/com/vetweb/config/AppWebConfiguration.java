package com.vetweb.config;
// @author renan.rodrigues@metasix.com.br

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

@Configuration
@EnableWebMvc
@EnableCaching
@EnableJms
@ComponentScan(basePackages = {"com.vetweb.controller", "com.vetweb.dao", "com.vetweb.scheduled",
    "com.vetweb.model", "com.vetweb.dao.auth", "com.vetweb.model.auth", "com.vetweb.controller.advice",
    "com.vetweb.model.error", "com.vetweb.model.pojo", "com.vetweb.service", "com.vetweb.controller.rest",
    "com.vetweb.endpoint", "com.vetweb.client", "com.vetweb.patterns", "com.vetweb.jms", "com.vetweb.model.report"})
public class AppWebConfiguration extends WebMvcConfigurerAdapter implements WebApplicationInitializer {
	
	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
        bundle.setBasename("/WEB-INF/messages");
        bundle.setDefaultEncoding("UTF-8");
        bundle.setCacheSeconds(1);
        return bundle;
    }
    
    @Bean
    public FormattingConversionService mvcConversionService(){
        FormattingConversionService formattingConversionService = new DefaultFormattingConversionService(true);
        DateTimeFormatterRegistrar formatter = new DateTimeFormatterRegistrar();
        formatter.setDateFormatter((DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        formatter.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        formatter.registerFormatters(formattingConversionService);
        return formattingConversionService;
    }
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/forbidden").setViewName("/login/403");
        registry.addViewController("/table").setViewName("/proprietario/table");
        registry.addViewController("/").setStatusCode(HttpStatus.NOT_FOUND).setViewName("/exception/404");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor());
    }
    
    @Bean
    public LocaleResolver localeResolver(){
        return new CookieLocaleResolver();
    }
    
    @Bean
    public MultipartResolver multipartResolver() {
    	return new StandardServletMultipartResolver();
    }
    
    @Bean
    public RestTemplate restTemplate() {
    	return new RestTemplate();
    }
    
    @Bean
    public CacheManager cacheManager() {
    	CacheBuilder<Object, Object> cacheBuilder = 
    			CacheBuilder.newBuilder()
    			.maximumSize(100)
    			.expireAfterAccess(5, TimeUnit.MINUTES);
    	GuavaCacheManager cacheManager = new GuavaCacheManager();
    	cacheManager.setCacheBuilder(cacheBuilder);
    	return cacheManager;
    			
    }
    
    @Bean
    public ConnectionFactory connectionFactory() {
    	return connectionFactory;
    }
    
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
    	DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
    	defaultJmsListenerContainerFactory.setConnectionFactory(connectionFactory);
    	return defaultJmsListenerContainerFactory;
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
