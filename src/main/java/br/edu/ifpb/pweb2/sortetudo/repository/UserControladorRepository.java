package br.edu.ifpb.pweb2.sortetudo.repository;

import br.edu.ifpb.pweb2.sortetudo.model.UserControlador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserControladorRepository extends JpaRepository<UserControlador, Long> {
}
