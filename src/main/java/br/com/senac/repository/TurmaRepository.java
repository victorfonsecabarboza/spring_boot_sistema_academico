package br.com.senac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.entity.Turma;

@Repository
//Esta interface herda métodos prontos para operações CRUD do JpaRepository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {

}
