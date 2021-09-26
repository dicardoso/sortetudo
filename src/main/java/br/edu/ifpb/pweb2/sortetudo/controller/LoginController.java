package br.edu.ifpb.pweb2.sortetudo.controller;


import br.edu.ifpb.pweb2.sortetudo.model.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getLoginForm(ModelAndView modelAndView) {
        modelAndView.setViewName("login/login");
        modelAndView.addObject("usuario", new Cliente());
        return modelAndView;
    }

}
