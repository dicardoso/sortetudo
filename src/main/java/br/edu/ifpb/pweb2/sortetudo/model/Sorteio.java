package br.edu.ifpb.pweb2.sortetudo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data // Lombok
public class Sorteio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSorteio;

    @ElementCollection
    private List<Integer> dezenasSorteadas = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@NotNull(message="Campo obrigatório!")
    private Date dataHoraSorteio;

    @NotNull(message = "Campo obrigatório")
    @Positive(message = "Precisa ser um valor positivo")
    private int valorPremio;

    private boolean realizado = false;
}
