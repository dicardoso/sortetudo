package br.edu.ifpb.pweb2.sortetudo.controller;

import br.edu.ifpb.pweb2.sortetudo.model.Sorteio;
import br.edu.ifpb.pweb2.sortetudo.repository.SorteioRepository;
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
@RequestMapping("/sorteios")
public class SorteioController {

    @Autowired
    SorteioRepository sorteioRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getSorteios() {
        ModelAndView mv = new ModelAndView("sorteios");
        List<Sorteio> sorteios = sorteioRepository.findAll();
        mv.addObject("sorteios", sorteios);
        return mv;
    }

    @RequestMapping(value = "/novoSorteios", method = RequestMethod.POST)
    public String cadastrarSorteios(@Valid Sorteio sorteio, BindingResult result, RedirectAttributes attributes, ModelAndView modelAndView) {
        if (result.hasErrors()) {
            return "redirect:/novoSorteio";
        }
        sorteioRepository.save(sorteio);
        return "redirect:/sorteios";
        }


    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Long id, ModelAndView mav, RedirectAttributes attr) {
        sorteioRepository.deleteById(id);
        attr.addFlashAttribute("mensagem", "Sorteio removido com sucesso!");
        mav.setViewName("redirect:/sorteios");
        return mav;
    }
}
