# VetWeb
### PFC (Projeto final de curso) Sistemas de Informação UMC

* Clone o projeto vetweb no Eclipse (Basta copiar a URL do repositório e colar na janela do git)

* Faça o mesmo para o projeto vetweb-auth (CRUD em JSF para perfis de acesso, usuários e permissões), esse projeto funciona em sincronia com a aplicação principal portanto a autenticação e autorização do vetweb só irá funcionar com ele no Classpath

* Importe os projetos para abri-los no Eclipse e em seguida clique com o botão direito, na opção de configuração selecione "Convert to Maven Project". Isso irá habilitar o download das dependências do Maven

* Crie o banco de dados da aplicação no postgresql com o nome vetweb_database. As duas aplicações compartilham a mesma base de dados, no vetweb os dados de conexão com o banco são feitos de forma programática na classe ConfigJPA, já no auth os dados são referenciados no persistence.xml por meio de datasource (Um datasource é uma seção no arquivo de configuração do servidor onde podemos colocar os parâmetros de conexão com o banco, deixando-os isolados da aplicação)

* Faça o download do WildFly 10 como servidor de aplicação para executar os projetos (Disponibilizei o arquivo compactado com o servidor e as configurações necessárias no drive), caso queira usar uma instância própria do WildFly adicione as seguintes configurações no arquivo standalone-full.xml (Listei soltas as tags abaixo, porém cada uma tem sua seção correta, no caso do driver do banco pode ser necessário incluir o arquivo Jar JDBC do postgresql)
```		
		<driver name="postgresql" module="org.postgresql">
				<driver-class>org.postgresql.Driver</driver-class>
		</driver>
		
                <datasource jndi-name="java:jboss/datasources/vetwebds" pool-name="vetwebds" enabled="true" use-java-context="true">
                    <connection-url>jdbc:postgresql://localhost:5432/vetweb_database</connection-url>
                    <driver>postgresql</driver>
                    <security>
                        <user-name>postgres</user-name>
                        <password>postgres</password>
                    </security>
                </datasource>

                <security-domain name="Dblogin" cache-type="default">
                    <authentication>
                        <login-module code="Database" flag="required">
                            <module-option name="dsJndiName" value="java:jboss/datasources/vetwebds"/>
                            <module-option name="principalsQuery" value="select password from Usuario where username = ?"/>
                            <module-option name="rolesQuery" value="select perfis_descricao, 'Roles' from usuario_perfil up inner join usuario u on up.usuarios_username = u.username where u.username = ?"/>
                            <module-option name="hashAlgorithm" value="MD5"/>
                            <module-option name="hashEncoding" value="base64"/>
                        </login-module>
                    </authentication>
                </security-domain>					
								
        <outbound-socket-binding name="mail-smtp">
            <remote-destination host="localhost" port="25"/>
        </outbound-socket-binding>
        <outbound-socket-binding name="smtp-gmail">
            <remote-destination host="smtp.gmail.com" port="465"/>
        </outbound-socket-binding>								
				
            <mail-session name="default" jndi-name="java:jboss/mail/Default">
                <smtp-server outbound-socket-binding-ref="mail-smtp"/>
            </mail-session>
            <mail-session name="gmail" jndi-name="java:jboss/mail/gmail">
                <smtp-server outbound-socket-binding-ref="smtp-gmail" ssl="true" username="springbootalura@gmail.com" password="springboot"/>
            </mail-session>
						
```

* Adicione os projetos ao servidor após finalizar o download das dependências e execute (Serão criadas as tabelas no banco na primeira execução por conta da estratégia utilizada no hibernate)

* Para preencher os sistemas com alguns dados de teste pode-se executar os arquivos import.sql (Irá inserir também um usuário padrão para autenticar nas duas aplicações, o usuário e senha são "vetweb")

* A autenticação no auth é feita via JAAS (Que é uma especificação J2EE para essa finalidade). Para especificar de onde são provenientes os usuários, bem como o tipo de criptografia das senhas é criada uma sessão denominada security-domain no arquivo de configuração do servidor

* Para salvar imagens é necessário realizar o download do s3ninja (Que permite simular API da Amazon para armazenamento de arquivos na nuvem s3 de forma gratuita para desenvolvimento) e executar uma instância local. Coloquei no drive o s3ninja, basta realizar o download e executar via linha de comando a classe IPL, com isso você terá uma instância executando no endereço http://localhost:9444/s3/vetweb/ e os arquivos serão salvos lá
