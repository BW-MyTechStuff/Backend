package com.buildweek.usemytechstuff.services;

import com.buildweek.usemytechstuff.models.Item;

import java.util.List;

public interface ItemService
{
    /**
     * Returns a list of all the Items
     *
     * @return List of Users. If no users, empty list.
     */
    List<Item> findAll();

    /**
     * Returns the item with the given primary key.
     *
     * @param id The primary key (long) of the item you seek.
     * @return The given item or throws an exception if not found.
     */
    Item findByItemId(long id);

    /**
     * Deletes the user record and its useremail items from the database based off of the provided primary key
     *
     * @param id id The primary key (long) of the user you seek.
     */
    void delete(long id);

    /**
     * Given a complete item object, saves that item object in the database.
     * If a primary key is provided, the record is completely replaced
     * If no primary key is provided, one is automatically generated and the record is added to the database.
     *
     * @param item the item object to be saved
     * @return the saved item object including any automatically generated fields
     */
    Item save(Item item);

    /**
     * Updates the provided fields in the item record referenced by the primary key.
     * <p>
     * Regarding status and userid items, this process only allows adding those. Deleting and editing those lists
     * is done through a separate endpoint.
     *
     * @param item just the user fields to be updated.
     * @param id   The primary key (long) of the item to update
     * @return the complete item object that got updated
     */
    Item update(Item item,
                long id);
}
