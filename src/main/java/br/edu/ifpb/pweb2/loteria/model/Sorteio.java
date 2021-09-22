package br.edu.ifpb.pweb2.loteria.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data // Lombok
public class Sorteio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSorteio;

    @ElementCollection
    private List<String> dezenasSorteadas = new ArrayList<>();

    private LocalDate dataHoraSorteio;

    private int valorPremio;
}
