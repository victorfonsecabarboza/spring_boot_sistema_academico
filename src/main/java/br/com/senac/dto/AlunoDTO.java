package br.com.senac.dto;

import lombok.Data;

//Anotação do Lombok para gerar métodos getter, setter, toString, equals e hashCode
@Data
public class AlunoDTO {

	private Integer id;
	private String nome;
	
}
