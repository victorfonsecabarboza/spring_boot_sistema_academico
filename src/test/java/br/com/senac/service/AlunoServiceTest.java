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

import br.com.senac.entity.Aluno;
import br.com.senac.repository.AlunoRepository;

//Integra o Mockito para configurar e usar mocks nos testes
@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

//	Cria um mock (objeto simulado) para AlunoRepository
	@Mock
	private AlunoRepository alunoRepository;

// Injeta mocks automaticamente em AlunoService
	@InjectMocks
	private AlunoService alunoService;

	private Aluno alunoTeste;

//  Configuração prévia para testes
	@BeforeEach
	public void setUp() {
		alunoTeste = new Aluno();
		alunoTeste.setId(1);
		alunoTeste.setNome("João");
	}

	/**
	 * Teste para verificar se o método salvarAluno() salva corretamente um aluno.
	 */
	@Test
    public void salvarAlunoTest() {
//      Configura o comportamento simulado para salvar o aluno 
        when(alunoRepository.save(any(Aluno.class))).thenReturn(alunoTeste);
        
//		Salva um aluno utilizando o AlunoService e atribuindo o resultado a uma variável 'alunoSalvo'
        Aluno alunoSalvo = alunoService.salvarAluno(alunoTeste);
        
//		Verifica se o aluno salvo não é nulo
        assertNotNull(alunoSalvo);
        
//		Verifica se o nome do aluno salvo é igual ao nome do aluno de teste
        assertEquals(alunoTeste.getNome(), alunoSalvo.getNome());
    }

	/**
	  * Teste para verificar se o método buscarTodosAlunos() retorna corretamente a lista de alunos.
	 */
	@Test
    public void buscarTodosAlunosTest() {
//      Configura o comportamento simulado para encontrar todos os alunos
    	when(alunoRepository.findAll()).thenReturn(Arrays.asList(alunoTeste));
    	
//	    Chama o método para buscar todas os alunos e armazena o resultado em uma lista de alunos
        List<Aluno> alunos = alunoService.buscarTodosAlunos();
        
//		Verifica se a lista de alunos retornado não é nulo
        assertNotNull(alunos);
        
//		Verifica se o tamanho da lista de alunos é igual a 1 (neste caso, se espera encontrar um único aluno)
        assertEquals(1, alunos.size());
        
//		Verifica se a lista de alunos contém exatamente o aluno de teste (alunoTeste)
        assertTrue(alunos.contains(alunoTeste));
    }

	/**
	 * Teste para verificar se o método buscarAlunoPorId() encontra corretamente um aluno com base no ID.
	 */
	@Test
    public void buscarAlunoPorIdTest() {
//      Configura o comportamento simulado do repositório para o ID 1
    	when(alunoRepository.findById(1)).thenReturn(Optional.of(alunoTeste));

//      Chama o método para buscar um aluno pelo ID 1 e armazena o resultado na variável alunoEncontrado
        Aluno alunoEncontrado = alunoService.buscarAlunoPorId(1);
        
//      Compara os nomes do aluno de teste (previamente definido) e do aluno encontrado pelo ID
        assertEquals(alunoTeste.getNome(), alunoEncontrado.getNome());
    }

	/**
	 * Teste para verificar se o método atualizarAluno() atualiza corretamente as informações de um aluno.
	 */
	@Test
    public void atualizarAlunoTest() {
//       Configura o comportamento simulado do repositório para o ID 1
    	when(alunoRepository.findById(1)).thenReturn(Optional.of(alunoTeste));
        
//     Configura o comportamento simulado do repositório para salvar qualquer instância de Aluno e retornar o próprio objeto salvo
        when(alunoRepository.save(any(Aluno.class))).thenAnswer(invocation -> invocation.getArgument(0));

//      Cria uma nova instância de Turma com o ID 1 e nome "Java Avançado"
        Aluno alunoAtualizado = new Aluno();
        alunoAtualizado.setId(1);
        alunoAtualizado.setNome("João Silva");

//      Chama o método de atualização para a disciplina com ID 1 e armazena o resultado na variável disciplinaAtualizada
        alunoAtualizado = alunoService.atualizarAluno(1, alunoAtualizado);

//      Garante que a turma atualizada não é nula
        assertNotNull(alunoAtualizado);
        
//      Verifica se o nome foi atualizado corretamente
        assertEquals("João Silva", alunoAtualizado.getNome());
        
//      Verifica se o ID permaneceu o mesmo após a atualização
        assertEquals(1, alunoAtualizado.getId());
    }

	/**
	 * Teste para verificar se o método excluirAluno() remove corretamente um aluno.
	 */
	@Test
    public void excluirAlunoTest() {
//      Configura o comportamento simulado do repositório para o ID 1
        when(alunoRepository.findById(1)).thenReturn(Optional.of(alunoTeste));

//      Verifica se o método excluirAluno(1) do AlunoService chama corretamente o método deleteById(1) do repositório
        alunoService.excluirAluno(1);
        
//      Verifica se o método deleteById(1) do turmaRepository foi chamado exatamente uma vez
        verify(alunoRepository, times(1)).deleteById(1);
    }

}
