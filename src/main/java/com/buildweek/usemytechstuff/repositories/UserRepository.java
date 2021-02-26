package com.buildweek.usemytechstuff.repositories;

import com.buildweek.usemytechstuff.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
}
