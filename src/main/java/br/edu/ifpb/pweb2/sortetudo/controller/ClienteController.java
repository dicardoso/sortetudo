package br.edu.ifpb.pweb2.sortetudo.controller;

import br.edu.ifpb.pweb2.sortetudo.auxiliar.SenhaAuxiliar;
import br.edu.ifpb.pweb2.sortetudo.model.Cliente;
import br.edu.ifpb.pweb2.sortetudo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getCadastroClientes(ModelAndView mv) {
        mv.addObject("cliente", new Cliente());
        mv.setViewName("clientes/clientes");
        return mv;
    }

    @RequestMapping(value = "/novoCliente", method = RequestMethod.GET)
    public String getClienteForm() {
        return "clienteForm";
    }


    @PostMapping()
    public ModelAndView  cadastrarCliente(@Valid Cliente cliente, BindingResult result, ModelAndView mv, RedirectAttributes attr) {
        if (result.hasErrors()) {
            mv.addObject("cliente", cliente);
            mv.setViewName("/clientes/clientes");
        }
        cliente.setSenha(SenhaAuxiliar.hashSenha(cliente.getSenha()));
        clienteRepository.save(cliente);
        attr.addFlashAttribute("mensagem", "Conta cadastrada com sucesso!");
        mv.setViewName("redirect:/clientes/clienteCadastroSucesso");
        return mv;
    }


    @RequestMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Long id, ModelAndView mav, RedirectAttributes attr) {
        clienteRepository.deleteById(id);
        attr.addFlashAttribute("mensagem", "Conta removida com sucesso!");
        mav.setViewName("redirect:/clientes");
        return mav;
    }

    @GetMapping(value = "/clienteCadastroSucesso")
    public ModelAndView redirectSucesso(ModelAndView mav){
        mav.addObject("mensagem", "Conta cadastrada com sucesso!");
        mav.setViewName("clientes/clienteCadastroSucesso");
        return mav;
    }

}
