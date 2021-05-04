package com.BeyondLearning.projetointegrador.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.BeyondLearning.projetointegrador.models.entity.Usuarios;

@Repository
public interface UsuariosRepository extends CrudRepository<Usuarios, Long> {

}
