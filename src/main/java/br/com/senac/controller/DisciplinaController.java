package br.com.senac.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senac.dto.DisciplinaDTO;
import br.com.senac.entity.Disciplina;
import br.com.senac.service.DisciplinaService;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

//  Realiza a injeção automática de dependência do ModelMapper pelo Spring
	@Autowired
	ModelMapper modelMapper;
	
//  Realiza a injeção automática de dependência da DisciplinaService pelo Spring
	@Autowired
	DisciplinaService disciplinaService;
	
	/**
	 * Cadastrar uma nova disciplina a partir dos dados fornecidos na DisciplinaDTO
	 * 
	 * @param disciplinaDTO Os dados da disciplina a serem cadastrados.
	 * @return Uma resposta HTTP 200 (OK) com a DisciplinaDTO da disciplina recém-cadastrada.
	 */
	@PostMapping
	public ResponseEntity<DisciplinaDTO> cadastrarDisciplina(@RequestBody DisciplinaDTO disciplinaDTO) {
		Disciplina disciplina = modelMapper.map(disciplinaDTO, Disciplina.class);
		disciplina = disciplinaService.salvarDisciplina(disciplina);
		DisciplinaDTO disciplinaNova = modelMapper.map(disciplina, DisciplinaDTO.class);
		return ResponseEntity.ok().body(disciplinaNova);
	}
	
	/**
	 * Busca todos as disciplinas cadastradas e retorna uma lista de DisciplinaDTOs.
	 * 
	 * @return Uma resposta HTTP 200 (OK) com a lista de DisciplinaDTOs das disciplinas encontradas.
	 */
	@GetMapping
	public ResponseEntity<List<DisciplinaDTO>> buscarTodasDisciplinas() {
		List<Disciplina> listarDisciplinas = disciplinaService.buscarTodasDisciplinas();
		List<DisciplinaDTO> listarDisciplinaDTO = listarDisciplinas.stream().map(disciplina -> modelMapper.map(disciplina, DisciplinaDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listarDisciplinaDTO);
	}
	
	/**
	 * Busca uma disciplina pelo ID fornecido e retorna sua TurmaDTO correspondente.
	 * 
	 * @param id O ID da disciplina a ser buscada.
	 * @return Uma resposta HTTP 200 (OK) com a TurmaDTO da turma encontrada.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<DisciplinaDTO> buscarDisciplinaPorId(@PathVariable("id") Integer id) {
		Disciplina disciplina = disciplinaService.buscarDisciplinaPorId(id);
		DisciplinaDTO disciplinaDTO = modelMapper.map(disciplina, DisciplinaDTO.class);
		return ResponseEntity.ok().body(disciplinaDTO);
	}
	
	/**
	 * Atualiza as informações de uma disciplina com base no ID fornecido, utilizando os dados da DisciplinaDTO.
	 * 
	 * @param id O ID da disciplina a ser atualizada.
	 * @param disciplinaDTO Os novos dados da disciplina a serem utilizados na atualização.
	 * @return Uma resposta HTTP 200 (OK) com a disciplinaDTO da disciplina após a atualização.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<DisciplinaDTO> atualizarDisciplina(@PathVariable("id") Integer id, @RequestBody DisciplinaDTO disciplinaDTO) {
		Disciplina disciplina = modelMapper.map(disciplinaDTO, Disciplina.class);
		disciplina = disciplinaService.atualizarDisciplina(id, disciplina);
		DisciplinaDTO disciplinaAlteradaDTO = modelMapper.map(disciplina, DisciplinaDTO.class);
		return ResponseEntity.ok().body(disciplinaAlteradaDTO);
	}

	/**
	 * Exclui uma disciplina pelo ID fornecido.
	 * 
	 * @param id O ID da disciplina a ser excluída.
	 * @return Uma resposta HTTP 200 (OK) indicando o sucesso da exclusão.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirDisciplina(@PathVariable("id") Integer id) {
		disciplinaService.excluirDisciplina(id);
		return ResponseEntity.ok().build();
	}
	
}
