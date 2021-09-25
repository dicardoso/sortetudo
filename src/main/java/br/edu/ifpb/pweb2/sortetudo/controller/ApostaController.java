package br.edu.ifpb.pweb2.sortetudo.controller;

import br.edu.ifpb.pweb2.sortetudo.model.Aposta;
import br.edu.ifpb.pweb2.sortetudo.repository.ApostaRepository;
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
    public ModelAndView getAposta() {
        ModelAndView mv = new ModelAndView("aposta");
        List<Aposta> apostas = apostaRepository.findAll();
        mv.addObject("aposta", apostas);
        return mv;
    }

    @RequestMapping(value = "/novaAposta", method = RequestMethod.POST)
    public String cadastrarAposta(Aposta aposta, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/novaAposta";
        }
        apostaRepository.save(aposta);
        return "redirect:/apostas";
        }


    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Long id, ModelAndView mav, RedirectAttributes attr) {
        apostaRepository.deleteById(id);
        attr.addFlashAttribute("mensagem", "Aposta removida com sucesso!");
        mav.setViewName("redirect:/apostas");
        return mav;
    }
}
