package br.ifpe.pg.provacolegiada.cadastro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ifpe.pg.provacolegiada.cadastro.domain.Curso;
import br.ifpe.pg.provacolegiada.cadastro.domain.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {

	@Query("select e from Turma e where e.curso = :curso order by e.ano, e.entrada")
	public List<Turma> findByCurso(Curso curso);
}
