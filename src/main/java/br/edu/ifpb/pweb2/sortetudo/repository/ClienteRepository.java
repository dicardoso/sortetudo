package br.edu.ifpb.pweb2.sortetudo.repository;

import br.edu.ifpb.pweb2.sortetudo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findBycpf(String cpf);
}
