package com.buildweek.usemytechstuff.controllers;

import com.buildweek.usemytechstuff.models.Item;
import com.buildweek.usemytechstuff.models.User;
import com.buildweek.usemytechstuff.services.ItemService;
import com.buildweek.usemytechstuff.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
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
     * Given a complete Item Object, create a new Item record
     * <br> Example: <a href="http://localhost:2019/items/item">http://localhost:2019/items/item</a>
     *
     * @param newitem A complete new item to add including emails and roles.
     *                roles must already exist.
     * @return A location header with the URI to the newly created item and a status of CREATED
     * @throws URISyntaxException Exception if something does not work in creating the location header
     * @see ItemService#save(Item) ItemService.save(Item)
     */
    @PostMapping(value = "/item", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addNewItem(@Valid @RequestBody Item newitem) throws URISyntaxException
    {
        newitem = itemService.save(newitem);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("{/itemid}")
            .buildAndExpand(newitem.getItemid())
            .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

    }

    /**
     * Given a complete Item Object
     * Given the item id, primary key, is in the item table,
     * replace the Item
     * Roles are handled through different endpoints
     * <br> Example: <a href="http://localhost:2019/users/user/15">http://localhost:2019/users/user/15</a>
     *
     * @param updateItem A complete User including all emails and roles to be used to
     *                   replace the User. Roles must already exist.
     * @param itemid     The primary key of the user you wish to replace.
     * @return status of OK
     * @see ItemService#save(Item) ItemService.save(Item)
     */
    @PutMapping(value = "/item/{itemid}", consumes = "application/json")
    public ResponseEntity<?> updateItem(@Valid @RequestBody Item updateItem, @PathVariable long itemid)
    {
        updateItem.setItemid(itemid);
        itemService.save(updateItem);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Deletes a given item
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
