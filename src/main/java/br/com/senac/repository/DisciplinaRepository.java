package br.com.senac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.entity.Disciplina;

@Repository
//Esta interface herda métodos prontos para operações CRUD do JpaRepository

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

}
