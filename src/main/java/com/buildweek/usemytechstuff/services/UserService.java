package com.buildweek.usemytechstuff.services;

import com.buildweek.usemytechstuff.models.User;

import java.util.List;

public interface UserService
{
    /**
     * Returns a list of all the Users
     *
     * @return List of Users. If no users, empty list.
     */
    List<User> findAll();

    /**
     * Returns the user with the given primary key.
     *
     * @param id The primary key (long) of the user you seek.
     * @return The given User or throws an exception if not found.
     */
    User findByUserId(long id);

    /**
     * Returns the user with the given name
     *
     * @param name The full name (String) of the User you seek.
     * @return The User with the given name or throws an exception if not found.
     */
    User findByName(String name);

    /**
     * Deletes the user record and its useremail items from the database based off of the provided primary key
     *
     * @param id id The primary key (long) of the user you seek.
     */
    void delete(long id);
}
