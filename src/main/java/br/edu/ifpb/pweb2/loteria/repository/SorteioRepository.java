package br.edu.ifpb.pweb2.loteria.repository;

import br.edu.ifpb.pweb2.loteria.model.Aposta;
import br.edu.ifpb.pweb2.loteria.model.Sorteio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SorteioRepository extends JpaRepository<Sorteio, Long> {
}
