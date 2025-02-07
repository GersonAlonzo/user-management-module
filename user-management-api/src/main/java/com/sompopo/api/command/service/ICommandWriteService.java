/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sompopo.api.command.service;

import com.sompopo.api.command.model.UserCommand;
import com.sompopo.api.infraestructure.entity.UserEntity;

/**
 *
 * @author gcalvaradoa
 */
public interface ICommandWriteService {
    
    public UserEntity create(UserCommand createUserCommand);
    public UserEntity update(UserCommand updateUserCommand);
     public void delete(Long id);
    
}
