package com.sompopo.api.query.service;

import com.sompopo.api.query.contract.UserQueryContract;
import com.sompopo.api.query.model.UserQuery;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable; // Importa la anotación Cacheable
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author gcalvaradoa
 */
@Service
public class QueryStackService implements ICommandReadService {

    private final UserQueryContract userContract;

    @Autowired
    public QueryStackService(UserQueryContract userContract) {
        this.userContract = userContract;
    }

    @Override
    @Cacheable(value = "users", key = "#id") 
    public Optional<UserQuery> getUserById(Long id) {
        return userContract.getUserById(id);
    }

    @Override
    @Cacheable(value = "users") 
    public List<UserQuery> getAllUsers() {
        return userContract.getAllUsers();
    }


    @Override
    @Cacheable(value = "usersPageable") 
    public Page<UserQuery> getAllUsers(Pageable pageable) {
        return userContract.getAllUsers(pageable);
    }
}
