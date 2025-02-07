/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sompopo.api.query.model;

import lombok.Data;

/**
 *
 * @author gcalvaradoa
 */

@Data
public class UserQuery {

    private Long id;

    private String username;

    private String email;

    private String msisdn; 

    private String password;


}
