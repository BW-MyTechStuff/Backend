package com.buildweek.usemytechstuff.repositories;

import com.buildweek.usemytechstuff.models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long>
{
}
