package br.ifpe.pg.provacolegiada.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifpe.pg.provacolegiada.cadastro.domain.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

	public boolean existsByNome(String nome);
}
