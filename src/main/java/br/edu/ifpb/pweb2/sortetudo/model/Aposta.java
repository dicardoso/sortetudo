package br.edu.ifpb.pweb2.sortetudo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data // Lombok
public class Aposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    List<Integer> numeros = new ArrayList<>();

    private LocalDate diaAposta;

    @ManyToOne
    private Sorteio sorteio;

    @ManyToOne
    private Cliente cliente;

    public void adicionarNumero(Integer numero) {
        numeros.add(numero);
    }
}
