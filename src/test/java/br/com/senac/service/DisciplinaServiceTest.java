package br.com.senac.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.senac.entity.Disciplina;
import br.com.senac.repository.DisciplinaRepository;

//Integra o Mockito para configurar e usar mocks nos testes
@ExtendWith(MockitoExtension.class)
class DisciplinaServiceTest {

//	Cria um mock (objeto simulado) para AlunoRepository
	@Mock
	private DisciplinaRepository disciplinaRepository;
	
	@InjectMocks
	private DisciplinaService disciplinaService;
	
	private Disciplina disciplinaTeste;
	
//  Configuração prévia para testes
	@BeforeEach
	public void setUp() {
		disciplinaTeste = new Disciplina();
		disciplinaTeste.setId(1);
		disciplinaTeste.setNome("Programação Web 1");
	}
	
	/**
	 * Teste para verificar se o método salvarDisciplina() salva corretamente uma disciplina.
	 */
	@Test
	public void salvarDisciplinaTest() {
//		Configura o comportamento simulado para salvar a disciplina
		when(disciplinaRepository.save(any(Disciplina.class))).thenReturn(disciplinaTeste);
		
//		Salva uma disciplina utilizando o DisciplinaService e atribuindo o resultado a uma variável 'disciplinaSalva'
		Disciplina disciplinaSalva = disciplinaService.salvarDisciplina(disciplinaTeste);
		
//		Verifica se a disciplina salva não é nula
		assertNotNull(disciplinaSalva);
		
//		Verifica se o nome da disciplina salva é igual ao nome da disciplina de teste
		assertEquals(disciplinaTeste.getNome(), disciplinaSalva.getNome());
	}
	
	/**
	 * Teste para verificar se o método buscarTodasDisciplinasTest() encontra corretamente todas as disciplinas.
	 */
	@Test
	public void buscarTodasDisciplinasTest() {
//		Configura o comportamento simulado para encontrar todas as disciplinas
		when(disciplinaRepository.findAll()).thenReturn(Arrays.asList(disciplinaTeste));
		
//	    Chama o método para buscar todas as disciplinas e armazena o resultado em uma lista de disciplinas
		List<Disciplina> disciplinas = disciplinaService.buscarTodasDisciplinas();
		
//		Verifica se a lista de disciplinas retornada não é nula
		assertNotNull(disciplinas);
		
//		Verifica se o tamanho da lista de disciplinas é igual a 1 (neste caso, se espera encontrar uma única disciplina)
        assertEquals(1, disciplinas.size());
		
//		Verifica se a lista de disciplinas contém exatamente a disciplina de teste (disciplinaTeste)
        assertTrue(disciplinas.contains(disciplinaTeste));
	}
	
	/**
	 * Teste para verificar se o método buscarDisciplinaPorId() encontra corretamente uma disciplina com base no ID.
	 */
	@Test
    public void buscarDisciplinaPorIdTest() {
//      Configura o comportamento simulado do repositório para o ID 1
    	when(disciplinaRepository.findById(1)).thenReturn(Optional.of(disciplinaTeste));

//      Chama o método para buscar uma disciplina pelo ID 1 e armazena o resultado na variável disciplinaEncontrada
    	Disciplina disciplinaEncontrada = disciplinaService.buscarDisciplinaPorId(1);
        
//      Compara os nomes da disciplina de teste (previamente definida) e da disciplina encontrada pelo ID
        assertEquals(disciplinaTeste.getNome(), disciplinaEncontrada.getNome());
    }
	
	/**
	 * Teste para verificar se o método atualizarDisciplina() atualiza corretamente as informações de uma disciplina.
	 */
	@Test
    public void atualizarDisciplinaTest() {
//      Configura o comportamento simulado do repositório para o ID 1
    	when(disciplinaRepository.findById(1)).thenReturn(Optional.of(disciplinaTeste));
        
//      Configura o comportamento simulado do repositório para salvar qualquer instância de disciplina e retornar o próprio objeto salvo.
        when(disciplinaRepository.save(any(Disciplina.class))).thenAnswer(invocation -> invocation.getArgument(0));

//      Cria uma nova instância de Disciplina com o ID 1 e nome "Teste de Software"
        Disciplina disciplinaAtualizada = new Disciplina();
        disciplinaAtualizada.setId(1);
        disciplinaAtualizada.setNome("Teste de Software");

//      Chama o método de atualização para a disciplina com ID 1 e armazena o resultado na variável disciplinaAtualizada
        disciplinaAtualizada = disciplinaService.atualizarDisciplina(1, disciplinaAtualizada);

//      Garante que a disciplina atualizada não é nula
        assertNotNull(disciplinaAtualizada);
        
//      Verifica se o nome foi atualizado corretamente
        assertEquals("Teste de Software", disciplinaAtualizada.getNome());
        
//      Verifica se o ID permaneceu o mesmo após a atualização
        assertEquals(1, disciplinaAtualizada.getId());
    }
	
	/**
	 * Teste para verificar se o método excluirDisciplina() remove corretamente uma disciplina.
	 */
	@Test
    public void excluirDisciplinaTest() {
//      Configura o comportamento simulado do repositório para o ID 1
        when(disciplinaRepository.findById(1)).thenReturn(Optional.of(disciplinaTeste));

//      Verifica se o método excluirDisciplina(1) da DisciplinaService chama corretamente o método deleteById(1) do repositório
        disciplinaService.excluirDisciplina(1);
        
//      Verifica se o método deleteById(1) do disciplinaRepository foi chamado exatamente uma vez
        verify(disciplinaRepository, times(1)).deleteById(1);
    }

}
