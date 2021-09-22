package br.edu.ifpb.pweb2.loteria.model;

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
    private List<String> dezenas = new ArrayList<>();

    private LocalDate diaAposta;

    @ManyToOne
    private Sorteio sorteio;

    @ManyToOne
    private Cliente cliente;
}
