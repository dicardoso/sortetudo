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

    private int valor;

    public Aposta() {}

    public Aposta(Sorteio sorteio, List<Integer> numeros) {
        this.sorteio = sorteio;
        this.numeros = numeros;
    }

    public void adicionarNumero(Integer numero) {
        numeros.add(numero);
    }

    @Override
    public String toString() {
        return "Aposta [id=" + id + ", sorteio=" + sorteio + ", numeros=" + numeros + "]";
    }
}
