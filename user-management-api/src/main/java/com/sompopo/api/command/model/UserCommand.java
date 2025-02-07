package com.sompopo.api.command.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserCommand {

    private Long id;

    private String username; 

    private String email; 

    private String msisdn; 

    private String password;
    
    

    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

    private LocalDateTime updatedAt;
    
    private boolean delete;
    
//    todo: update with value Object

}