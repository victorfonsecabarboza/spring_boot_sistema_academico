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

import br.com.senac.dto.AlunoDTO;
import br.com.senac.entity.Aluno;
import br.com.senac.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

//  Realiza a injeção automática de dependência do ModelMapper pelo Spring
	@Autowired
	ModelMapper modelMapper;

//  Realiza a injeção automática de dependência do AlunoService pelo Spring
	@Autowired
	private AlunoService alunoService;

	/**
	 * Cadastrar um novo aluno a partir dos dados fornecidos no AlunoDTO.
	 * 
	 * @param alunoDTO Os dados do aluno a serem cadastrados.
	 * @return Uma resposta HTTP com o AlunoDTO do aluno recém-cadastrado (codigo 200 OK).
	 */
	@PostMapping
	public ResponseEntity<AlunoDTO> cadastrarAluno(@RequestBody AlunoDTO alunoDTO) {
		Aluno aluno = modelMapper.map(alunoDTO, Aluno.class);
		aluno = alunoService.salvarAluno(aluno);
		AlunoDTO alunoNovo = modelMapper.map(aluno, AlunoDTO.class);
		return ResponseEntity.ok().body(alunoNovo);
	}

	/**
	 * Busca todos os alunos cadastrados e retorna uma lista de AlunoDTOs.
	 * 
	 * @return Uma resposta HTTP com a lista de AlunoDTOs dos alunos encontrados (codigo 200 OK).
	 */
	@GetMapping
	public ResponseEntity<List<AlunoDTO>> buscarTodosAlunos() {
		List<Aluno> listarAlunos = alunoService.buscarTodosAlunos();
		List<AlunoDTO> listarAlunoDTO = listarAlunos.stream().map(aluno -> modelMapper.map(aluno, AlunoDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listarAlunoDTO);
	}

	/**
	 * Busca um aluno pelo ID fornecido e retorna seu AlunoDTO correspondente.
	 * 
	 * @param id O ID do aluno a ser buscado.
	 * @return Uma resposta HTTP com o AlunoDTO do aluno encontrado (codigo 200 OK).
	 */
	@GetMapping("/{id}")
	public ResponseEntity<AlunoDTO> buscarAlunoPorId(@PathVariable("id") Integer id) {
		Aluno aluno = alunoService.buscarAlunoPorId(id);
		AlunoDTO alunoDTO = modelMapper.map(aluno, AlunoDTO.class);
		return ResponseEntity.ok().body(alunoDTO);
	}

	/**
	 * Atualiza as informações de um aluno com base no ID fornecido, utilizando os dados do AlunoDTO.
	 * 
	 * @param id O ID do aluno a ser atualizado.
	 * @param alunoDTO Os novos dados do aluno a serem utilizados na atualização.
	 * @return Uma resposta HTTP com o AlunoDTO do aluno após a atualização (codigo 200 OK).
	 */
	@PutMapping("/{id}")
	public ResponseEntity<AlunoDTO> atualizarAluno(@PathVariable("id") Integer id, @RequestBody AlunoDTO alunoDTO) {
		Aluno aluno = modelMapper.map(alunoDTO, Aluno.class);
		aluno = alunoService.atualizarAluno(id, aluno);
		AlunoDTO alunoAlteradoDTO = modelMapper.map(aluno, AlunoDTO.class);
		return ResponseEntity.ok().body(alunoAlteradoDTO);
	}

	/**
	 * Exclui um aluno pelo ID fornecido.
	 * 
	 * @param id O ID do aluno a ser excluído.
	 * @return Uma resposta HTTP indicando o sucesso da exclusão (codigo 200 OK).
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirAluno(@PathVariable("id") Integer id) {
		alunoService.excluirAluno(id);
		return ResponseEntity.ok().build();
	}

}
