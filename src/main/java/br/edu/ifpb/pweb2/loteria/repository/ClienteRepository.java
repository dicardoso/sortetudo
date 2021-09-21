package br.edu.ifpb.pweb2.loteria.repository;

import br.edu.ifpb.pweb2.loteria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
