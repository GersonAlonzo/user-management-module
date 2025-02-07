package com.sompopo.api.http.controller;

import com.sompopo.api.command.model.UserCommand;
import com.sompopo.api.command.service.ICommandWriteService;
import com.sompopo.api.config.ResourceNotFoundException;
import com.sompopo.api.query.model.UserQuery;
import com.sompopo.api.query.service.ICommandReadService;
import com.sompopo.api.infraestructure.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final ICommandWriteService commandWriteService;
    private final ICommandReadService queryStackService;

    @Autowired
    public UserController(ICommandWriteService commandWriteService, ICommandReadService queryStackService) {
        this.commandWriteService = commandWriteService;
        this.queryStackService = queryStackService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserCommand userCommand) {
        try {
            UserEntity createdUser = commandWriteService.create(userCommand);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error al crear el usuario", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            Optional<UserQuery> user = queryStackService.getUserById(id);

            if (user.isPresent()) {
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            } else {
                throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
            }

        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error al obtener el usuario con ID: {}", id, e);
            return new ResponseEntity<>("Error al obtener el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserQuery> users = queryStackService.getAllUsers();
            if (!users.isEmpty()) {
                return new ResponseEntity<>(users, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se encontraron usuarios", HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("Error al obtener la lista de usuarios", e);
            return new ResponseEntity<>("Error al obtener la lista de usuarios", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/pageable")
    public ResponseEntity<?> getAllUsers(
            @PageableDefault(size = 10, page = 0) Pageable pageable) { 
        try {
            Page<UserQuery> usersPage = queryStackService.getAllUsers(pageable); 

            if (!usersPage.isEmpty()) {
                 return new ResponseEntity<>(usersPage, HttpStatus.OK); 
            } else {
                return new ResponseEntity<>("No se encontraron usuarios", HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("Error al obtener la lista de usuarios", e);
            return new ResponseEntity<>("Error al obtener la lista de usuarios", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserCommand userCommand) {
        try {
            if (userCommand.getId() == null || !userCommand.getId().equals(id)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
            }
            UserEntity updatedUser = commandWriteService.update(userCommand);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error al actualizar el usuario con ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            commandWriteService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error al eliminar el usuario con ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}