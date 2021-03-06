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

    /**
     * Given a complete user object, saves that user object in the database.
     * If a primary key is provided, the record is completely replaced
     * If no primary key is provided, one is automatically generated and the record is added to the database.
     *
     * @param user the user object to be saved
     * @return the saved user object including any automatically generated fields
     */
    User save(User user);

    /**
     * Updates the provided fields in the user record referenced by the primary key.
     * <p>
     * Regarding Role and Useremail items, this process only allows adding those. Deleting and editing those lists
     * is done through a separate endpoint.
     *
     * @param user just the user fields to be updated.
     * @param id   The primary key (long) of the user to update
     * @return the complete user object that got updated
     */
    User update(
        User user,
        long id);
}
