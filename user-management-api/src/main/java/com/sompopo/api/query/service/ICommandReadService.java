/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sompopo.api.query.service;

import com.sompopo.api.query.model.UserQuery;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author gcalvaradoa
 */
public interface ICommandReadService {
    
    public Optional<UserQuery> getUserById(Long id);
    public List<UserQuery> getAllUsers();
    public Page<UserQuery> getAllUsers(Pageable pageable);
    
}
