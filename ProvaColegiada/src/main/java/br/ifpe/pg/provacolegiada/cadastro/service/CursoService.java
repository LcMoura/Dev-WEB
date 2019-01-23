package br.ifpe.pg.provacolegiada.cadastro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.ifpe.pg.provacolegiada.cadastro.domain.Curso;
import br.ifpe.pg.provacolegiada.cadastro.repository.CursoRepository;

@Service
public class CursoService {

	@Autowired
	private CursoRepository repositorio;

	public List<Curso> listarTodos() {
		return repositorio.findAll(Sort.by("nome"));
	}

	public Curso buscarPorId(Integer id) {
		return repositorio.findById(id).orElse(null);
	}

	public Curso salvar(Curso curso) throws Exception {
		if (repositorio.existsByNome(curso.getNome()) && (equals(curso.getCargaHoraria()))) {
			throw new Exception("Já existe curso com este nome");
		}
		return repositorio.saveAndFlush(curso);

	}

	public Curso removerPorId(Integer id) {
		Curso curso = buscarPorId(id);
		repositorio.deleteById(id);
		return curso;
	}

	
}
