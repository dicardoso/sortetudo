package br.edu.ifpb.pweb2.sortetudo.controller;

import br.edu.ifpb.pweb2.sortetudo.model.Sorteio;
import br.edu.ifpb.pweb2.sortetudo.repository.SorteioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sorteios")
public class SorteioController {

    @Autowired
    SorteioRepository sorteioRepository;

    @RequestMapping(value = "/listarSorteios", method = RequestMethod.GET)
    public ModelAndView listAllSorteios(ModelAndView mav) {
        mav.addObject("sorteios", sorteioRepository.findAll());
        mav.setViewName("sorteios/sorteios");
        return mav;
    }

    @GetMapping("/listarDezenas")
    public List<Integer> listarDezenas() {
         List<Integer> dezenas = new ArrayList<>();
         int id = 1;
        Sorteio ultimoSorteio = sorteioRepository.getById((long) id);
        dezenas = ultimoSorteio.getDezenasSorteadas();
        return dezenas;
    }

    @PostMapping()
    public String realizarSorteioManual(String idSorteio, int num1,int num2,int num3,int num4,int num5, int num6) {
            int id = Integer.parseInt(idSorteio);
            Sorteio ultimoSorteio = sorteioRepository.findById((long) id).get();
            ArrayList<Integer> sorteados = new ArrayList();

            sorteados.add(num1);
            sorteados.add(num2);
            sorteados.add(num3);
            sorteados.add(num4);
            sorteados.add(num5);
            sorteados.add(num6);

            if (sorteados.size() == 6) {
                ultimoSorteio.setDezenasSorteadas(sorteados);
                ultimoSorteio.setRealizado(true);
                sorteioRepository.save(ultimoSorteio);
            }
        return "redirect:/sorteios/listarSorteios";
    }

    public String realizarSorteio(String idSorteio) {
        int id = Integer.parseInt(idSorteio);
        Sorteio sorteio = sorteioRepository.findById((long) id).get();
        ArrayList<Integer> list = new ArrayList();
        ArrayList<Integer> sorteados = new ArrayList();
        int contador = 0;
        int[] valores = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60};
        for (int i=0; i<valores.length; i++){
            list.add(valores[i]);
        }

        Collections.shuffle(list);

        while(contador < 6) {
            sorteados.add(list.get(contador));
            contador++;
        }
        //TODO parametrizar do premio no front

        sorteio.setDezenasSorteadas(sorteados);
        LocalDate dataUltimoSorteio = sorteioRepository.ultimoSorteio();

        //calcula diferença e datas entre sorteios

        sorteio.setRealizado(true);

        return  "redirect:/sorteios";
    }
/*
    @RequestMapping(value = "/formSorteio", method = RequestMethod.GET)
    public ModelAndView getCadastroClientes(ModelAndView mv) {
        mv.addObject("sorteio", new Sorteio());
        mv.setViewName("sorteios/formSorteio");
        return mv;
    }
*/
    @RequestMapping(value = "/formSorteio", method = RequestMethod.POST)
    public String cadastrarSorteio(@Valid Sorteio sorteio, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            System.out.println(result);
            return "redirect:/sorteios/listarSorteios";
        }
        //sorteio.setDataHoraSorteio();
        sorteio.setRealizado(false);
        sorteio.setDezenasSorteadas(null);
        sorteioRepository.save(sorteio);
        attr.addFlashAttribute("mensagem", "Sorteio cadastrado com sucesso!");
        return "redirect:/sorteios/listarSorteios";
    }

    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Long id, ModelAndView mav, RedirectAttributes attr) {
        sorteioRepository.deleteById(id);
        attr.addFlashAttribute("mensagem", "Sorteio removido com sucesso!");
        mav.setViewName("redirect:/sorteios");
        return mav;
    }
}
