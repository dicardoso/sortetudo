package br.edu.ifpb.pweb2.sortetudo.repository;

import br.edu.ifpb.pweb2.sortetudo.model.Sorteio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SorteioRepository extends JpaRepository<Sorteio, Long> {

    @Query("SELECT MAX(dataHoraSorteio) FROM Sorteio")
    public LocalDate ultimoSorteio();
}
