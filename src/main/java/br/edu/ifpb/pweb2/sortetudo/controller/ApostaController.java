package br.edu.ifpb.pweb2.sortetudo.controller;

import br.edu.ifpb.pweb2.sortetudo.model.Aposta;
import br.edu.ifpb.pweb2.sortetudo.model.Sorteio;
import br.edu.ifpb.pweb2.sortetudo.repository.ApostaRepository;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/apostas")
public class ApostaController {

    @Autowired
    ApostaRepository apostaRepository;
    @Autowired
    SorteioRepository sorteioRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAposta() {
        ModelAndView mv = new ModelAndView("aposta");
        List<Aposta> apostas = apostaRepository.findAll();
        mv.addObject("aposta", apostas);
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String cadastrarAposta(Long idSorteio, int num1,int num2,int num3,int num4,int num5, int num6, int num7, int num8, int num9, int num10) {

        Sorteio sorteio = sorteioRepository.getById(idSorteio);
        Aposta aposta = new Aposta();
        ArrayList<Integer> numeros = new ArrayList();
        ArrayList<Integer> numerosDigitados = new ArrayList();

        numeros.add(num1);
        numeros.add(num2);
        numeros.add(num3);
        numeros.add(num4);
        numeros.add(num5);
        numeros.add(num6);
        numeros.add(num7);
        numeros.add(num8);
        numeros.add(num9);
        numeros.add(num10);

        for (int i =0; i < 10; i++) {
            if (numeros.get(i) >=1 && numeros.get(i) <=60) {
                numerosDigitados.add(numeros.get(i));
            }
        }

        aposta.setNumeros(numerosDigitados);
        aposta.setDiaAposta(LocalDate.now());
        aposta.setSorteio(sorteio);

     //apostas favoritas, colocar um checkbox no front, if true,
        // salva os numeros no array de favoritos de clientes
        apostaRepository.save(aposta);

        return "foi";
        }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Long id, ModelAndView mav, RedirectAttributes attr) {
        apostaRepository.deleteById(id);
        attr.addFlashAttribute("mensagem", "Aposta removida com sucesso!");
        mav.setViewName("redirect:/apostas");
        return mav;
    }
}
