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

    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;
    private int num7;
    private int num8;
    private int num9;
    private int num10;

    private LocalDate diaAposta;

    @ManyToOne
    private Sorteio sorteio;

    @ManyToOne
    private Cliente cliente;
}
