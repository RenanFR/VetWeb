package com.vetweb.config;
// @author 11151504898

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement//Habilita uso do controle transacional do Spring
public class ConfigJPA {//Para configurações do ORM
    @Bean//Ciclo de vida gerenciado pelo Spring/Permite injeção
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {//Criação do EntityManager
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();//Faz o papel do persistence.xml
        entityManagerFactory.setDataSource(dataSource());//Atribui a fonte de dados do EntityManager
        entityManagerFactory.setPackagesToScan(new String[]{"com.vetweb.model", 
            "com.vetweb.model.auth"});//Array com pacotes mapeados pela JPA (Entidades mapeadas)
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();//Implementação JPA selecionada (Hibernate)
        entityManagerFactory.setJpaVendorAdapter(adapter);
        entityManagerFactory.setJpaProperties(properties());//Configura propriedades adicionais do Hibernate
        return entityManagerFactory;
    }
    @Bean
    private DataSource dataSource(){//Configura a fonte de dados. Parâmetros de conexão
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");//Biblioteca JDBC do provedor de banco de dados
        dataSource.setUrl("jdbc:postgresql://localhost:5432/vetweb_database");//URL JDBC
        dataSource.setUsername("postgres");//Usuário do banco
        dataSource.setPassword("postgres");//Senha do banco
//        dataSource.setPassword("admw2k91");//Senha do banco
        return dataSource;
    }
    private Properties properties(){//Retorna objeto armazenando propriedades adicionais do banco de dados
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");//Estratégia de criação do banco de dados (Atualiza banco a cada alteração nas entidades)
//        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");//Cria/Exclui o banco a cada execução. Para homologaçao
//        properties.setProperty("hibernate.hbm2ddl.auto", "create");//Cria o banco somente caso não exista. Para homologaçao
//        properties.setProperty("hibernate.hbm2ddl.import_files", "import.sql");//Arquivo de Seed 
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");//Dialeto de SQL p/ Postgresql (Fonte: Documentação Hibernate)
        properties.setProperty("hibernate.show_sql", "true");//Exibe os comandos SQL sendo executados
        return properties;
    }
    @Bean//Responsável por criar e injetar o controle transacional da JPA no Spring
    public PlatformTransactionManager transactionManager(EntityManagerFactory managerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(managerFactory);
        return transactionManager;
    }
}
