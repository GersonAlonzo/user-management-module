package com.sompopo.api.command.contract;

import com.sompopo.api.command.model.UserCommand;
import com.sompopo.api.config.ResourceNotFoundException;
import com.sompopo.api.infraestructure.entity.UserEntity;
import com.sompopo.api.infraestructure.mapper.Mapper;
import com.sompopo.api.infraestructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCommandContract {

    @Autowired
    private UserRepository userRepository;

    public UserEntity create(UserCommand createUserCommand) {

        return userRepository.save(Mapper.getInstance().map(createUserCommand, UserEntity.class));
    }

    public UserEntity update(UserCommand updateUserCommand) {
        UserEntity userEntity = userRepository.findById(updateUserCommand.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + updateUserCommand.getId())); 
        return userRepository.save(Mapper.getInstance().map(updateUserCommand, UserEntity.class));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}