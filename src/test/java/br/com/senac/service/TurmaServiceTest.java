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

import br.com.senac.entity.Turma;
import br.com.senac.repository.TurmaRepository;

//Integra o Mockito para configurar e usar mocks nos testes
@ExtendWith(MockitoExtension.class)
class TurmaServiceTest {

//	Cria um mock (objeto simulado) para AlunoRepository
	@Mock
	private TurmaRepository turmaRepository;
	
//  Injeta mocks automaticamente em AlunoService
	@InjectMocks
	private TurmaService turmaService;
	
	private Turma turmaTeste;
	
//  Configuração prévia para testes
	@BeforeEach
	public void setUp() {
		turmaTeste = new Turma();
		turmaTeste.setId(1);
		turmaTeste.setNome("Java");
	}
	
	/**
	 * Teste para verificar se o método salvarTurma() salva corretamente uma turma.
	 */
	@Test
    public void salvarTurmaTest() {
//      Configura o comportamento simulado para salvar a turma 
        when(turmaRepository.save(any(Turma.class))).thenReturn(turmaTeste);
        
//		Salva uma turma utilizando o TurmaService e atribuindo o resultado a uma variável 'turmaSalva'
        Turma turmaSalva = turmaService.salvarTurma(turmaTeste);
        
//		Verifica se a turma salva não é nula
        assertNotNull(turmaSalva);
        
//		Verifica se o nome da turma salva é igual ao nome da turma de teste
        assertEquals(turmaTeste.getNome(), turmaSalva.getNome());
    }
	
	/**
	 * Teste para verificar se o método buscarTodasTurmas() encontra corretamente todas as turmas.
	 */
	@Test
	public void buscarTodasTurmasTest() {
//		Configura o comportamento simulado para encontrar todas as turmas
		when(turmaRepository.findAll()).thenReturn(Arrays.asList(turmaTeste));
		
//	    Chama o método para buscar todas as turmas e armazena o resultado em uma lista de turmas
		List<Turma> turmas = turmaService.buscarTodasTurmas();
		
//		Verifica se a lista de turmas retornada não é nula
		assertNotNull(turmas);
		
//		Verifica se o tamanho da lista de turmas é igual a 1 (neste caso, se espera encontrar uma única turma)
        assertEquals(1, turmas.size());
		
//		Verifica se a lista de turmas contém exatamente a turma de teste (turmaTeste)
        assertTrue(turmas.contains(turmaTeste));
	}
	
	/**
	 * Teste para verificar se o método buscarTurmaPorId() encontra corretamente uma turma com base no ID.
	 */
	@Test
    public void buscarTurmaPorIdTest() {
//      Configura o comportamento simulado do repositório para o ID 1
    	when(turmaRepository.findById(1)).thenReturn(Optional.of(turmaTeste));

//      Chama o método para buscar uma turma pelo ID 1 e armazena o resultado na variável turmaEncontrada
        Turma turmaEncontrada = turmaService.buscarTurmaPorId(1);
        
//      Compara os nomes da turma de teste (previamente definida) e da turma encontrada pelo ID
        assertEquals(turmaTeste.getNome(), turmaEncontrada.getNome());
    }
	
	/**
	 * Teste para verificar se o método atualizarTurma() atualiza corretamente as informações de uma turma.
	 */
	@Test
    public void atualizarTurmaTest() {
//      Configura o comportamento simulado do repositório para o ID 1
    	when(turmaRepository.findById(1)).thenReturn(Optional.of(turmaTeste));
        
//      Configura o comportamento simulado do repositório para salvar qualquer instância de turma e retornar o próprio objeto salvo.
        when(turmaRepository.save(any(Turma.class))).thenAnswer(invocation -> invocation.getArgument(0));

//      Cria uma nova instância de Turma com o ID 1 e nome "Java Avançado"
        Turma turmaAtualizada = new Turma();
        turmaAtualizada.setId(1);
        turmaAtualizada.setNome("Java Avançado");

//      Chama o método de atualização para a turma com ID 1 e armazena o resultado na variável turmaAtualizada
        turmaAtualizada = turmaService.atualizarTurma(1, turmaAtualizada);

//      Garante que a turma atualizada não é nula
        assertNotNull(turmaAtualizada);
        
//      Verifica se o nome foi atualizado corretamente
        assertEquals("Java Avançado", turmaAtualizada.getNome());
        
//      Verifica se o ID permaneceu o mesmo após a atualização
        assertEquals(1, turmaAtualizada.getId());
    }
	
	/**
	 * Teste para verificar se o método excluirTurma() remove corretamente uma turma.
	 */
	@Test
    public void excluirTurmaTest() {
//      Configura o comportamento simulado do repositório para o ID 1
        when(turmaRepository.findById(1)).thenReturn(Optional.of(turmaTeste));

//      Verifica se o método excluirTurma(1) da TurmaService chama corretamente o método deleteById(1) do repositório
        turmaService.excluirTurma(1);
        
//      Verifica se o método deleteById(1) do turmaRepository foi chamado exatamente uma vez
        verify(turmaRepository, times(1)).deleteById(1);
    }

}
