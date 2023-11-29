package br.com.senac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.entity.Aluno;
import br.com.senac.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AlunoService {

//  Realiza a injeção automática de dependência do AlunoRepository pelo Spring
	@Autowired
	AlunoRepository alunoRepository;

	/**
	 * Salva um novo aluno no repositório.
	 * 
	 * @param aluno O aluno a ser salvo.
	 * @return O aluno salvo no repositório.
	 */
	public Aluno salvarAluno(Aluno aluno) {
		return alunoRepository.save(aluno);
	}

	/**
	 * Retorna uma lista de todos os alunos do repositório.
	 * 
	 * @return Lista contendo todos os alunos.
	 */
	public List<Aluno> buscarTodosAlunos() {
		return alunoRepository.findAll();
	}

	/**
	 * Busca um aluno no repositório com base no ID fornecido.
	 * 
	 * @param id O ID do aluno a ser buscado.
	 * @return O aluno encontrado com o ID especificado.
	 * @throws EntityNotFoundException Se o aluno com o ID fornecido não for encontrado.
	 */
	public Aluno buscarAlunoPorId(Integer id) {
		return alunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno com o ID " + id + " não encontrado."));
	}

	/**
	 * Atualiza as informações de um aluno no repositório com base no ID fornecido.
	 * 
	 * @param id O ID do aluno a ser atualizado.
	 * @param alunoAlteracao O objeto contendo as informações atualizadas do aluno.
	 * @return O aluno com as informações atualizadas no repositório.
	 * @throws EntityNotFoundException Se o aluno com o ID fornecido não for encontrado.
	 */
	public Aluno atualizarAluno(Integer id, Aluno alunoAlteracao) {
		Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno com o ID " + id + " não encontrado."));
		aluno.setNome(alunoAlteracao.getNome());
		return alunoRepository.save(aluno);
	}

	/**
	 * Exclui um aluno do repositório com base no ID fornecido.
	 * 
	 * @param id O ID do aluno a ser excluído.
	 * @throws EntityNotFoundException Se o aluno com o ID fornecido não for encontrado.
	 */
	public void excluirAluno(Integer id) {
		alunoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Aluno com o ID " + id + " não encontrado."));
		alunoRepository.deleteById(id);
	}

}
