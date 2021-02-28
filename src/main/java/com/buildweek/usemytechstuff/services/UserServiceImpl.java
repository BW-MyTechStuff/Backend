package com.buildweek.usemytechstuff.services;

import com.buildweek.usemytechstuff.exceptions.ResourceNotFoundException;
import com.buildweek.usemytechstuff.models.Item;
import com.buildweek.usemytechstuff.models.User;
import com.buildweek.usemytechstuff.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Autowired
    private HelperFunctions helperFunctions;

    @Override
    public List<User> findAll()
    {
        List<User> userList = new ArrayList<>();

        userRepository.findAll().iterator().forEachRemaining(userList::add);

        return userList;
    }

    @Override
    public User findByUserId(long id) throws ResourceNotFoundException
    {
        return userRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public User findByName(String name)
    {
        User uu = userRepository.findByUsername(name);
        if (uu == null)
        {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return uu;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public User save(User user)
    {
        User newUser = new User();

        if (user.getUserid() != 0)
        {
            userRepository.findById(user.getUserid())
                .orElseThrow(() -> new ResourceNotFoundException("User id" + user.getUserid() + " not found!"));
            newUser.setUserid(user.getUserid());
        }

        newUser.setUsername(user.getUsername().toLowerCase());      // might not need to lower case
        newUser.setFname(user.getFname());
        newUser.setLname(user.getLname());
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.setEmail(user.getEmail().toLowerCase());
        newUser.setUserrole(user.getUserrole());

        // dont need ad item when adding user, seperate form
//        newUser.getItems().clear();
//        for (Item item : user.getItems())
//        {
//            newUser.getItems().add(new Item(newUser, item.getItemname()));
//        }

        return userRepository.save(newUser);
    }

    @Transactional
    @Override
    public User update(User user, long id)
    {
        User currentuser = findByUserId(id);

        if (helperFunctions.isAuthorizedToMakeChange(currentuser.getUsername()))
        {
            if (user.getUsername() != null)
            {
                currentuser.setUsername(user.getUsername().toLowerCase());
            }
            if (user.getFname() != null)
            {
                currentuser.setFname(user.getFname().toLowerCase());
            }
            if (user.getLname() != null)
            {
                currentuser.setLname(user.getLname().toLowerCase());
            }

            if (user.getPassword() != null)
            {
                currentuser.setPasswordNoEncrypt(user.getPassword());
            }

            if (user.getEmail() != null)
            {
                currentuser.setEmail(user.getEmail().toLowerCase());
            }

            if (user.getUserrole() != null)
            {
                currentuser.setUserrole(user.getUserrole());
            }

            // dont need to update item when updating user
//            if (user.getItems().size() > 0)
//            {
//                currentuser.getItems().clear();
//                for (Item i : user.getItems())
//                {
//                    currentuser.getItems().add(new Item(currentuser, i.getItemname()));
//                }
//            }
            return userRepository.save(currentuser);
        } else
        {
            // note we should never get to this line but is needed for the compiler
            // to recognize that this exception can be thrown
            throw new OAuth2AccessDeniedException();
        }
    }


}
