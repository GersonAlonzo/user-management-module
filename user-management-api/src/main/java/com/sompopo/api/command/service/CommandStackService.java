package com.sompopo.api.command.service;

import com.sompopo.api.command.model.UserCommand;
import com.sompopo.api.command.contract.UserCommandContract;
import com.sompopo.api.infraestructure.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandStackService implements ICommandWriteService {

    private final UserCommandContract userContract;

    @Autowired
    public CommandStackService(UserCommandContract userContract) {
        this.userContract = userContract;
    }

    @Override
    public UserEntity create(UserCommand createUserCommand) {
        return userContract.create(createUserCommand);
    }

    @Override
    public UserEntity update(UserCommand updateUserCommand) {
        return userContract.update(updateUserCommand);
    }

    @Override
    public void delete(Long id) {
        userContract.delete(id);
    }
}