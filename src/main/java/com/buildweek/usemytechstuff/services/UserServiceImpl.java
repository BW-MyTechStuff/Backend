package com.buildweek.usemytechstuff.services;


import com.buildweek.usemytechstuff.models.User;
import com.buildweek.usemytechstuff.repositories.UserRepository;
import com.buildweek.usemytechstuff.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService
{
    /**
     * Connects this service to the User table.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Connects this service to the UserRole table
     */
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<User> findAll()
    {
        List<User> userList = new ArrayList<>();

        userRepository.findAll().iterator().forEachRemaining(userList::add);

        return userList;
    }

    @Override
    public User findByUserId(long id) throws EntityNotFoundException
    {
        return userRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public User findByName(String name)
    {
        User uu = userRepository.findByUsername(name.toLowerCase());
        if (uu == null)
        {
            throw new EntityNotFoundException("User name " + name + " not found!");
        }
        return uu;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User id " + id + " not found!"));
        userRepository.deleteById(id);
    }
}
