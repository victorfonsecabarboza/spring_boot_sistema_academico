package br.com.senac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.entity.Disciplina;
import br.com.senac.repository.DisciplinaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DisciplinaService {

//  Realiza a injeção automática de dependência do DisciplinaRepository pelo Spring
	@Autowired
	DisciplinaRepository disciplinaRepository;

	/**
	 * Salva uma nova disciplina no repositório.
	 * 
	 * @param disciplina A disciplina a ser salva.
	 * @return A disciplina salva no repositório.
	 */
	public Disciplina salvarDisciplina(Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}

	/**
	 * Retorna uma lista de todas as disciplinas do repositório.
	 * 
	 * @return Lista contendo todas as disciplinas.
	 */
	public List<Disciplina> buscarTodasDisciplinas() {
		return disciplinaRepository.findAll();
	}

	/**
	 * Busca uma disciplina no repositório com base no ID fornecido.
	 * 
	 * @param id O ID da disciplina a ser buscada.
	 * @return A disciplina encontrada com o ID especificado.
	 * @throws EntityNotFoundException Se a disciplina com o ID fornecido não for encontrada.
	 */
	public Disciplina buscarDisciplinaPorId(Integer id) {
		return disciplinaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Disciplina com o ID " + id + " não encontrada."));
	}

	/**
	 * Atualiza as informações de uma disciplina no repositório com base no ID fornecido.
	 * 
	 * @param id O ID da disciplina a ser atualizada.
	 * @param disciplinaAlteracao O objeto contendo as informações atualizadas da disciplina.
	 * @return A disciplina com as informações atualizadas no repositório.
	 * @throws EntityNotFoundException Se a disciplina com o ID fornecido não for encontrada.
	 */
	public Disciplina atualizarDisciplina(Integer id, Disciplina disciplinaAlteracao) {
		Disciplina disciplina = disciplinaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Disciplina com o ID " + id + " não encontrada."));
		disciplina.setNome(disciplinaAlteracao.getNome());
	    return disciplinaRepository.save(disciplina);
	}

	/**
	 * Exclui uma disciplina do repositório com base no ID fornecido.
	 * 
	 * @param id O ID da disciplina a ser excluída.
	 * @throws EntityNotFoundException Se a disciplina com o ID fornecido não for encontrada.
	 */
	public void excluirDisciplina(Integer id) {
		disciplinaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Disciplina com o ID " + id + " não encontrada."));
		disciplinaRepository.deleteById(id);
	}

}
