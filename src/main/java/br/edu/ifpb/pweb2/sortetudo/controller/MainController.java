package br.edu.ifpb.pweb2.sortetudo.controller;

import br.edu.ifpb.pweb2.sortetudo.model.UserControlador;
import br.edu.ifpb.pweb2.sortetudo.repository.UserControladorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/controladores")
public class MainController {

    @Autowired
    UserControladorRepository userControladorRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getControlador() {
        ModelAndView mv = new ModelAndView("clientes");
        List<UserControlador> controladors = userControladorRepository.findAll();
        mv.addObject("controladores", controladors);
        return mv;
    }

    @RequestMapping(value = "/novoControlador", method = RequestMethod.POST)
    public String cadastrarControlador(@Valid UserControlador controlador, BindingResult result, RedirectAttributes attributes, ModelAndView modelAndView) {
        if (result.hasErrors()) {
            return "redirect:/novoControlador";
        }
        userControladorRepository.save(controlador);
        return "redirect:/controladores";
        }


    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Long id, ModelAndView mav, RedirectAttributes attr) {
        userControladorRepository.deleteById(id);
        attr.addFlashAttribute("mensagem", "Conta removida com sucesso!");
        mav.setViewName("redirect:/controladores");
        return mav;
    }
}
