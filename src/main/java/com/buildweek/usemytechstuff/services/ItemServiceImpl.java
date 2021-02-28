package com.buildweek.usemytechstuff.services;

import com.buildweek.usemytechstuff.exceptions.ResourceNotFoundException;
import com.buildweek.usemytechstuff.models.Item;
import com.buildweek.usemytechstuff.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
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

    @Autowired
    private  HelperFunctions helperFunctions;

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
        Item newItem = new Item();

        if (item.getItemid() != 0)
        {
            itemRepository.findById(item.getItemid())
                .orElseThrow(() -> new ResourceNotFoundException("Item id " + item.getItemid() + " not found!"));
            newItem.setItemid(item.getItemid());
        }

        newItem.setUser(item.getUser());
        newItem.setItemname(item.getItemname());
        newItem.setItemdescription(item.getItemdescription());
        newItem.setItemcostperday(item.getItemcostperday());
        newItem.setItemstatus(item.getItemstatus());
        newItem.setNumberofdaysrented(item.getNumberofdaysrented());

        return itemRepository.save(newItem);
    }

    @Transactional
    @Override
    public Item update(Item item, long id)
    {
        Item currentItem = findByItemId(id);

        if (helperFunctions.isAuthorizedToMakeChange(currentItem.getItemname()))
        {
            if (item.getItemname() != null)
            {
                currentItem.setItemname(item.getItemname());
            }

            if (item.getItemdescription() != null)
            {
                currentItem.setItemdescription(item.getItemdescription());
            }
            if (item.getItemcostperday() != 0)
            {
                currentItem.setItemcostperday(item.getItemcostperday());
            }
            if (item.getItemstatus() != null)
            {
                currentItem.setItemstatus(item.getItemstatus());
            }
            if (item.getNumberofdaysrented() != 0)
            {
                currentItem.setNumberofdaysrented(item.getNumberofdaysrented());
            }
            return itemRepository.save(currentItem);
        } else
        {
            // note we should never get to this line but is needed for the compiler
            // to recognize that this exception can be thrown
            throw new OAuth2AccessDeniedException();
        }
    }

}
