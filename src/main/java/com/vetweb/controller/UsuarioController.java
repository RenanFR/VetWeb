package com.vetweb.controller;
 //@author renanrodrigues
import com.vetweb.dao.auth.UsuarioDAO;
import com.vetweb.model.auth.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller//Indica classe p/ atender requisições
@Transactional//Habilita transações automáticas nas operações no BD
@RequestMapping("/usuarios")//Caminho base p/ essa Controller
public class UsuarioController {
//    @Autowired//Injeção da dependência e gerenciamento do ciclo de vida do Obj. feito pelo spring
//    UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
    @Autowired
    UsuarioDAO usuarioDAO;
    @RequestMapping("/cadastro")//Caminho p/ o método na URL (Será base + cadastro)
    public String frmCadastro(){//Retorno String indica redirecionamento p/ página
        return "login/cadastroUsuario";//Caminho da página a partir da base definida na configuração (InternalResourceViewResolver)
    }
    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)//Caminho + Método HTTP
    public ModelAndView cadastrar(Usuario usuario){//Retorna página com atributos dinâmicos
        ModelAndView modelAndView = new ModelAndView("login/cadastroUsuario");//Obj. aponta página destino
        try{
            usuarioDAO.salvar(usuario);
            modelAndView.addObject("result", "Usuário cadastrado com sucesso.   ".toUpperCase());//Adc. mensagem de resultado (Atributo)
        } catch (Exception exception){
            modelAndView.addObject("result", "Erro ao cadastrar usuário.    ".toUpperCase());
        }
        return modelAndView;//Retorna Obj. com os atributos redirecionando p/ a página. Atributos disponíveis p/ consumo na mesma
    }
    @RequestMapping("/login")
    public String login(){//Retorna nossa página de login customizada
        return "login/login";
    }
    @RequestMapping("/fail")
    public String failLogin(){
        return "login/fail";
    }
    @ExceptionHandler//Tratamento de exceções da Controller Spring
    @ResponseStatus(HttpStatus.BAD_REQUEST)//Responde p/ resposta Http BAD_REQUEST
    public void handle(Exception exception){
        exception.printStackTrace();//Imprime pilha de exceção no console do servidor
    }
}
