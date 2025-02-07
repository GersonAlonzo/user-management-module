// UserRepository.java
package com.sompopo.api.infraestructure.repository;

import com.sompopo.api.infraestructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    @Query(value = "SELECT * FROM active_users_view", nativeQuery = true)
    List<UserEntity> findAllActiveUsersUsingView();

//    todo: implementar borrado logica para conservar registro / auditoria
}