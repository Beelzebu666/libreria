package com.buchrei.mislibros.repositorio;

import com.buchrei.mislibros.modelos.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
}
