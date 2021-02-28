package com.buildweek.usemytechstuff.services;

import com.buildweek.usemytechstuff.exceptions.ResourceNotFoundException;
import com.buildweek.usemytechstuff.models.Item;
import com.buildweek.usemytechstuff.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "itemService")
public class ItemServiceImpl implements ItemService
{
    /**
     * Connects this service to the item table.
     */
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> findAll()
    {
        List<Item> itemList = new ArrayList<>();

        itemRepository.findAll().iterator().forEachRemaining(itemList::add);

        return itemList;
    }

    @Override
    public Item findByItemId(long id)
    {
        return itemRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Item id " + id + " not found!"));
    }

    @Transactional
    @Override
    public void delete(long id)
    {

        itemRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Item id " + id + " not found!"));
        itemRepository.deleteById(id);

    }

    @Transactional
    @Override
    public Item save(Item item)
    {
        return null;
    }

    @Transactional
    @Override
    public Item update(
        Item item,
        long id)
    {
        return null;
    }
}
