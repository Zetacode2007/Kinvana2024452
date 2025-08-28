package org.codematrix.kinvana.persistence.crud;

import org.codematrix.kinvana.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteCrud  extends JpaRepository<Cliente, Integer> {


}
