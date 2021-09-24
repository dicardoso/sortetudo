package br.edu.ifpb.pweb2.sortetudo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data // Lombok
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Campo obrigatório!")
    private String nome;

    //@NotBlank(message="Campo obrigatório!")
    @NotNull
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date nascimento; // perguntar se pode usar localDate ao inves de Date

    @NotBlank(message="Campo obrigatório!")
    private String cpf;

    @OneToMany
    private List<Aposta> apostasFavoritas;

}
