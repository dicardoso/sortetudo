package br.edu.ifpb.pweb2.sortetudo.repository;

import br.edu.ifpb.pweb2.sortetudo.model.Sorteio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SorteioRepository extends JpaRepository<Sorteio, Long> {

    @Query("SELECT MAX(dataHoraSorteio) FROM Sorteio")
    public LocalDate ultimoSorteio();

    @Query("SELECT s FROM Sorteio s WHERE s.realizado = false ORDER BY s.idSorteio ASC")
    public Sorteio getSorteioAtual();

    @Query("SELECT MAX(s.idSorteio) FROM Sorteio s WHERE s.realizado = false ORDER BY s.idSorteio ASC")
    public Sorteio listarDezenas();

}
