package br.edu.ifpb.pweb2.loteria.repository;

import br.edu.ifpb.pweb2.loteria.model.Cliente;
import br.edu.ifpb.pweb2.loteria.model.UserControlador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserControladorRepository extends JpaRepository<UserControlador, Long> {
}
