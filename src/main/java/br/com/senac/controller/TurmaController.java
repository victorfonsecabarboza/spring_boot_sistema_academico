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

import br.com.senac.dto.TurmaDTO;
import br.com.senac.entity.Turma;
import br.com.senac.service.TurmaService;

@RestController
@RequestMapping("/turma")
public class TurmaController {

//  Realiza a injeção automática de dependência do ModelMapper pelo Spring
	@Autowired
	ModelMapper modelMapper;

//  Realiza a injeção automática de dependência da TurmaService pelo Spring
	@Autowired
	private TurmaService turmaService;
	
	/**
	 * Cadastrar uma nova turma a partir dos dados fornecidos na TurmaDTO.
	 * 
	 * @param turmaDTO Os dados da turma a serem cadastrados.
	 * @return Uma resposta HTTP com a TurmaDTO da turma recém-cadastrada (codigo 200 OK).
	 */
	@PostMapping
	public ResponseEntity<TurmaDTO> cadastrarTurma(@RequestBody TurmaDTO turmaDTO) {
		Turma turma = modelMapper.map(turmaDTO, Turma.class);
		turma = turmaService.salvarTurma(turma);
		TurmaDTO turmaNova = modelMapper.map(turma, TurmaDTO.class);
		return ResponseEntity.ok().body(turmaNova);
	}

	/**
	 * Busca todos as turmas cadastradas e retorna uma lista de TurmaDTOs.
	 * 
	 * @return Uma resposta HTTP 200 (OK)com a lista de TurmaDTOs das turmas encontradas.
	 */
	@GetMapping
	public ResponseEntity<List<TurmaDTO>> buscarTodasTurmas() {
		List<Turma> listarTurmas = turmaService.buscarTodasTurmas();
		List<TurmaDTO> listarTurmaDTO = listarTurmas.stream().map(turma -> modelMapper.map(turma, TurmaDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listarTurmaDTO);
	}

	/**
	 * Busca uma turma pelo ID fornecido e retorna sua TurmaDTO correspondente.
	 * 
	 * @param id O ID da turma a ser buscada.
	 * @return Uma resposta HTTP 200 (OK) com a TurmaDTO da turma encontrada.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<TurmaDTO> buscarTurmaPorId(@PathVariable("id") Integer id) {
		Turma turma = turmaService.buscarTurmaPorId(id);
		TurmaDTO turmaDTO = modelMapper.map(turma, TurmaDTO.class);
		return ResponseEntity.ok().body(turmaDTO);
	}

	/**
	 * Atualiza as informações de uma turma com base no ID fornecido, utilizando os dados da TurmaDTO.
	 * 
	 * @param id O ID da turma a ser atualizada.
	 * @param turmaDTO Os novos dados da turma a serem utilizados na atualização.
	 * @return Uma resposta HTTP 200 (OK) com a turmaDTO da turma após a atualização.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<TurmaDTO> atualizarTurma(@PathVariable("id") Integer id, @RequestBody TurmaDTO turmaDTO) {
		Turma turma = modelMapper.map(turmaDTO, Turma.class);
		turma = turmaService.atualizarTurma(id, turma);
		TurmaDTO turmaAlteradaDTO = modelMapper.map(turma, TurmaDTO.class);
		return ResponseEntity.ok().body(turmaAlteradaDTO);
	}

	/**
	 * Exclui uma turma pelo ID fornecido.
	 * 
	 * @param id O ID da turma a ser excluída.
	 * @return Uma resposta HTTP 200 (OK) indicando o sucesso da exclusão.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirTurma(@PathVariable("id") Integer id) {
		turmaService.excluirTurma(id);
		return ResponseEntity.ok().build();
	}

}
