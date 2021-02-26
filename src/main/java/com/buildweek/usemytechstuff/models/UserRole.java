package com.buildweek.usemytechstuff.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "userrole")
public class UserRole
{
    /**
     * The primary key (long) of the userrole table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userroleid;

    private String userroletype;

    /**
     * List of users associated with this role type. Does not get save in the database
     * Forms a One-to-Many relationship with Users. One role to many users
     */
    @OneToMany(mappedBy = "userrole", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "userrole", allowSetters = true)
    private Set<User> users = new HashSet<>();

    // Constructors


    public UserRole()
    {
        //required by JPA
    }

    public UserRole(String userroletype)
    {
        this.userroletype = userroletype;
    }

    // getters and setters


    public long getUserroleid()
    {
        return userroleid;
    }

    public void setUserroleid(long userroleid)
    {
        this.userroleid = userroleid;
    }

    public String getUserroletype()
    {
        return userroletype;
    }

    public void setUserroletype(String userroletype)
    {
        this.userroletype = userroletype;
    }
}
