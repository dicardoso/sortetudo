package br.edu.ifpb.pweb2.sortetudo.controller;

import br.edu.ifpb.pweb2.sortetudo.model.Aposta;
import br.edu.ifpb.pweb2.sortetudo.model.Cliente;
import br.edu.ifpb.pweb2.sortetudo.model.Sorteio;
import br.edu.ifpb.pweb2.sortetudo.repository.ApostaRepository;
import br.edu.ifpb.pweb2.sortetudo.repository.ClienteRepository;
import br.edu.ifpb.pweb2.sortetudo.repository.SorteioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("usuario")
@RequestMapping("/apostas")
public class ApostaController {

    @Autowired
    ApostaRepository apostaRepository;
    @Autowired
    SorteioRepository sorteioRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAposta(ModelAndView mv) {
        List<Aposta> apostas = apostaRepository.findAll();
        mv.addObject("aposta", apostas);
        mv.setViewName("aposta");
        return mv;
    }

    @RequestMapping(value = "/{idCliente}", method = RequestMethod.POST)
    public String cadastrarAposta(@PathVariable Long idCliente, HttpServletRequest request, Long idSorteio, int num1, int num2, int num3, int num4, int num5, int num6, int num7, int num8, int num9, int num10) {

        System.out.println(idCliente);
        Sorteio sorteio = sorteioRepository.getById(idSorteio);
        Cliente cliente = clienteRepository.getById(idCliente);
        Aposta aposta = new Aposta();
        ArrayList<Integer> numeros = new ArrayList();
        ArrayList<Integer> numerosDigitados = new ArrayList();

        //Cliente usuario = (Cliente) request.getSession().getAtribute("usuario");

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


        if (numerosDigitados.size() == 6) aposta.setValor(3);
        else if (numerosDigitados.size() == 7) aposta.setValor(15);
        else if (numerosDigitados.size() == 8) aposta.setValor(90);
        else if (numerosDigitados.size() == 9) aposta.setValor(300);
        else if (numerosDigitados.size() == 10) aposta.setValor(1500);


        aposta.setNumeros(numerosDigitados);
        aposta.setDiaAposta(LocalDate.now());
        aposta.setSorteio(sorteio);
        aposta.setCliente(cliente);

     //apostas favoritas, colocar um checkbox no front, if true,
        // salva os numeros no array de favoritos de clientes


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
