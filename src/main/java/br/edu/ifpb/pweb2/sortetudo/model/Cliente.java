package br.edu.ifpb.pweb2.sortetudo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull
    private Date nascimento; // perguntar se pode usar localDate ao inves de Date

    @NotBlank(message="Campo obrigatório!")
    @Size(min = 11, max = 11, message = "CPF tem que ter 11 digitos!")
    private String cpf;

    private boolean adm = false;

    @NotBlank(message="Campo obrigatório")
    @Size(min=6, message = "Senha deve ter no mínimo 6 caracteres")
    @Pattern.List({
            @Pattern(regexp = "(?=.*[A-Z]).+", message = "Senha deve ter no minimo uma letra maiuscula."),
            @Pattern(regexp = "(?=.*[a-z]).+", message = "Senha deve ter no minimo uma letra  minuscula."),
            @Pattern(regexp = "(?=.*[0-9]).+", message = "Senha deve ter no minimo um numero."),
            @Pattern(regexp = "(?=\\S+$).+", message = "Senha não pode ter espaços em branco."),
            @Pattern(regexp = "(?=.*[!@#$%^&*+=?-_()/\"\\.,<>~`;:]).+", message ="Senha deve ter no minimo um caractere especial.")
    })
    private String senha;


    @OneToMany
    private List<Aposta> apostasFavoritas;

    public void addApostaFavorita(Aposta aposta){
        apostasFavoritas.add(aposta);
    }

}
