package com.buildweek.usemytechstuff.controllers;

import com.buildweek.usemytechstuff.models.Item;
import com.buildweek.usemytechstuff.services.ItemService;
import com.buildweek.usemytechstuff.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
