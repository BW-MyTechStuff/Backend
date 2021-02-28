package com.buildweek.usemytechstuff.controllers;

import com.buildweek.usemytechstuff.models.Item;
import com.buildweek.usemytechstuff.services.ItemService;
import com.buildweek.usemytechstuff.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController
{
    /**
     * Using the Item service to process user data
     */
    @Autowired
    private ItemService itemService;

    /**
     * Returns a list of all users
     * <br>Example: <a href="http://localhost:2019/items/items">http://localhost:2019/items/items</a>
     *
     * @return JSON list of all users with a status of OK
     * @see UserService#findAll() UserService.findAll()
     */
    @GetMapping(value = "/items", produces = "application/json")
    public ResponseEntity<?> listAllItems()
    {
        List<Item> itemList = itemService.findAll();
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    /**
     * Returns a single user based off a user id number
     * <br>Example: http://localhost:2019/items/item/2
     *
     * @param itemid The primary key of the user you seek
     * @return JSON object of the user you seek
     * @see ItemService#findByItemId(long) (long) ItemService.findByItemId(long)
     */
    @GetMapping(value = "/item/{itemid}", produces = "application/json")
    public ResponseEntity<?> getItemById(@PathVariable long itemid)
    {
        Item item = itemService.findByItemId(itemid);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    /**
     * Deletes a given user along with associated emails and roles
     * <br>Example: <a href="http://localhost:2019/items/item/2">http://localhost:2019/items/item/2</a>
     *
     * @param itemid the primary key of the user you wish to delete
     * @return Status of OK
     */
    @DeleteMapping(value = "/item/{itemid}")
    public ResponseEntity<?> deleteItemById(@PathVariable long itemid)
    {
        itemService.delete(itemid);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
