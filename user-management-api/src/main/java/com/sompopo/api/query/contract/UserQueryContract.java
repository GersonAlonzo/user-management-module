package com.sompopo.api.query.contract;

import com.sompopo.api.query.model.UserQuery;
import com.sompopo.api.infraestructure.entity.UserEntity;
import com.sompopo.api.infraestructure.mapper.Mapper;
import com.sompopo.api.infraestructure.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 *
 * @author gcalvaradoa
 */
@Component
public class UserQueryContract {

    @Autowired
    private UserRepository userRepository;

    public Optional<UserQuery> getUserById(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);

        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            UserQuery user = Mapper.getInstance().map(userEntity, UserQuery.class);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }


    public Page<UserQuery> getAllUsers(Pageable pageable) {
        Page<UserEntity> userEntityPage = userRepository.findAll(pageable); // Usa el método findAll de JpaRepository

        // Convierte Page<UserEntity> a Page<UserQuery> de forma eficiente
        return userEntityPage.map(userEntity -> Mapper.getInstance().map(userEntity, UserQuery.class));
    }

    public List<UserQuery> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(userEntity -> Mapper.getInstance().map(userEntity, UserQuery.class))
                .collect(Collectors.toList());
    }

}
