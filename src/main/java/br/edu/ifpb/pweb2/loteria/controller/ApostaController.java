package br.edu.ifpb.pweb2.loteria.controller;

import br.edu.ifpb.pweb2.loteria.model.Aposta;
import br.edu.ifpb.pweb2.loteria.model.Cliente;
import br.edu.ifpb.pweb2.loteria.repository.ApostaRepository;
import br.edu.ifpb.pweb2.loteria.repository.ClienteRepository;
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
@RequestMapping("/apostas")
public class ApostaController {

    @Autowired
    ApostaRepository apostaRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getClientes() {
        ModelAndView mv = new ModelAndView("clientes");
        List<Aposta> apostas = apostaRepository.findAll();
        mv.addObject("clientes", apostas);
        return mv;
    }

    @RequestMapping(value = "/novoCliente", method = RequestMethod.POST)
    public String cadastrarCliente(@Valid Aposta aposta, BindingResult result, RedirectAttributes attributes, ModelAndView modelAndView) {
        if (result.hasErrors()) {
            return "redirect:/novoCliente";
        }
        apostaRepository.save(aposta);
        return "redirect:/clientes";
        }


    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Long id, ModelAndView mav, RedirectAttributes attr) {
        apostaRepository.deleteById(id);
        attr.addFlashAttribute("mensagem", "Conta removida com sucesso!");
        mav.setViewName("redirect:/clientes");
        return mav;
    }
}
