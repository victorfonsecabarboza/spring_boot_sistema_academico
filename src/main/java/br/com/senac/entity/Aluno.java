package br.com.senac.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
//Anotação do Lombok para gerar métodos getter, setter, toString, equals e hashCode
@Data
public class Aluno {

//  Indica que o campo id é a chave primária da entidade
	@Id
//	Define a estratégia de geração automática para a chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;

}
