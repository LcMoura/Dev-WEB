package br.ifpe.pg.provacolegiada.cadastro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.pg.provacolegiada.cadastro.domain.Curso;
import br.ifpe.pg.provacolegiada.cadastro.domain.Modalidade;
import br.ifpe.pg.provacolegiada.cadastro.service.CursoService;
import br.ifpe.pg.provacolegiada.cadastro.service.ProfessorService;

@Controller
@RequestMapping("/cursos/")
public class CursoController {

	@Autowired
	private CursoService service;
	
	@Autowired
	private ProfessorService profService;

	@GetMapping("list")
	public ModelAndView exibirLista(Curso curso) {
		ModelAndView mv = new ModelAndView("cadastros/cursos-list");
		mv.addObject("lista", service.listarTodos());
		mv.addObject("curso", curso);
		mv.addObject("listaModalidade", Modalidade.values());
		mv.addObject("coordenador", profService.listarTodas());

		return mv;
	}

	@PostMapping("/salvar")
	public String salvar(@Valid @ModelAttribute Curso curso, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			ra.addFlashAttribute("mensagemErro", "Não foi possível salvar curso: " + errors.getFieldErrors());
		} else {
			try {
				service.salvar(curso);
				ra.addFlashAttribute("mensagemSucesso", "Curso salvo com sucesso [" + curso.getNome() + "]");
				System.out.println("Curso cadastrado com sucesso: \t" + curso.getId());
				System.out.println("Coordenador cadastrado com sucesso: \t" + curso.getProfCoordenador().getNome());

			} catch (Exception e) {
				ra.addFlashAttribute("mensagemErro", "Não foi possível salvar curso: " + e.getMessage());
			}
		}
		return "redirect:/cursos/list";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView exibirEdicao(@PathVariable("id") Integer id) {
		Curso curso = service.buscarPorId(id);
		//Teste início
		ModelAndView mv = new ModelAndView("cadastros/cursos-list");
		mv.addObject("listaCursos", service.listarTodos());
		mv.addObject("listaModalidade", Modalidade.values());
		mv.addObject("coordenador", profService.listarTodas());
		mv.addObject("curso", curso);
		return exibirLista(curso);
	}

	@GetMapping("/remover/{id}")
	public String remover(@PathVariable("id") Integer id, RedirectAttributes ra) {
		Curso cursoRemovido = service.removerPorId(id);
		ra.addFlashAttribute("mensagemSucesso", "Curso removido com sucesso [" + cursoRemovido.getNome() + "]");
		return "redirect:/cursos/list";
	}

}
