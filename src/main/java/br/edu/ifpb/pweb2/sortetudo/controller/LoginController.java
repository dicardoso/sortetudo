package br.edu.ifpb.pweb2.sortetudo.controller;


import br.edu.ifpb.pweb2.sortetudo.auxiliar.SenhaAuxiliar;
import br.edu.ifpb.pweb2.sortetudo.model.Cliente;
import br.edu.ifpb.pweb2.sortetudo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ClienteRepository clienteRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getLoginForm(ModelAndView modelAndView) {
        modelAndView.setViewName("login/login");
        modelAndView.addObject("usuario", new Cliente());
        return modelAndView;
    }

    @PostMapping()
    public ModelAndView fazerLogin(Cliente usuario, HttpSession session, ModelAndView mav, RedirectAttributes redirectAttts){
        if ((usuario = this.usuarioValido(usuario)) != null) {
            session.setAttribute("usuario", usuario);
            mav.setViewName("algumlugar");
        } else {
            redirectAttts.addFlashAttribute("mensagem", "Login e/ou senha inválidos!");
            mav.setViewName("redirect:/login");
        }
        return mav;
    }

    public Cliente usuarioValido(Cliente usuario) {
        Cliente usuarioBanco = clienteRepository.findBycpf(usuario.getCpf());
        boolean verificacao = false;
        if (usuarioBanco != null){
            if (SenhaAuxiliar.checkSenha(usuario.getSenha(), usuarioBanco.getSenha())){
                verificacao = true;
            }
        }

        return verificacao ? usuarioBanco : null;
    }

}
