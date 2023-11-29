package br.com.senac.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	/**
	 * Configura e fornece uma instância do ModelMapper para mapeamento de objetos.
	 * 
	 * @return Uma instância de ModelMapper configurada.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
