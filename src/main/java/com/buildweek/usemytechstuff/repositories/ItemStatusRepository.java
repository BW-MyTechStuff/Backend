package com.buildweek.usemytechstuff.repositories;

import com.buildweek.usemytechstuff.models.ItemStatus;
import org.springframework.data.repository.CrudRepository;

public interface ItemStatusRepository extends CrudRepository<ItemStatus, Long>
{
}
