package br.com.senac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.entity.Turma;
import br.com.senac.repository.TurmaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TurmaService {

//  Realiza a injeção automática de dependência do DisciplinaRepository pelo Spring
	@Autowired
	TurmaRepository turmaRepository;

	/**
	 * Salva uma nova turma no repositório.
	 * 
	 * @param turma A turma a ser salva.
	 * @return A turma salva no repositório.
	 */
	public Turma salvarTurma(Turma turma) {
		return turmaRepository.save(turma);
	}

	/**
	 * Retorna uma lista de todas as turmas do repositório.
	 * 
	 * @return Turma contendo todas as turmas.
	 */
	public List<Turma> buscarTodasTurmas() {
		return turmaRepository.findAll();
	}

	/**
	 * Busca uma turma no repositório com base no ID fornecido.
	 * 
	 * @param id O ID da turma a ser buscada.
	 * @return A turma encontrada com o ID especificado.
	 * @throws EntityNotFoundException Se a turma com o ID fornecido não for encontrada.
	 */
	public Turma buscarTurmaPorId(Integer id) {
		return turmaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Turma com o ID " + id + " não encontrada."));
	}

	/**
	 * Atualiza as informações de uma turma no repositório com base no ID fornecido.
	 * 
	 * @param id O ID da turma a ser atualizada.
	 * @param turmaAlteracao O objeto contendo as informações atualizadas da turma.
	 * @return A turma com as informações atualizadas no repositório.
	 * @throws EntityNotFoundException Se a turma com o ID fornecido não for encontrada.
	 */
	public Turma atualizarTurma(Integer id, Turma turmaAlteracao) {
		Turma turma = turmaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Turma com o ID " + id + " não encontrada."));
	    turma.setNome(turmaAlteracao.getNome());
	    return turmaRepository.save(turma);
	}

	/**
	 * Exclui uma turma do repositório com base no ID fornecido.
	 * 
	 * @param id O ID da turma a ser excluída.
	 * @throws EntityNotFoundException Se a turma com o ID fornecido não for encontrada.
	 */
	public void excluirTurma(Integer id) {
		turmaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Aluno com o ID " + id + " não encontrado."));
		turmaRepository.deleteById(id);
	}

}
