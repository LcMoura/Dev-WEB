package br.ifpe.pg.provacolegiada.cadastro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.ifpe.pg.provacolegiada.cadastro.domain.Curso;
import br.ifpe.pg.provacolegiada.cadastro.domain.Turma;
import br.ifpe.pg.provacolegiada.cadastro.repository.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository repositorio;

	public List<Turma> listarTodas() {
		return repositorio.findAll(Sort.by("curso.nome"));
	}

	public List<Turma> buscarPorCurso(Curso curso) {
		return repositorio.findByCurso(curso);
	}
	
	public Turma buscarPorId(Integer id) {
		return repositorio.findById(id).orElse(null);
	}

	public <S extends Turma> S salvar(S entity) {
		return repositorio.saveAndFlush(entity);
	}

	public void removerPorId(Integer id) {
		repositorio.deleteById(id);
	}
	
}
