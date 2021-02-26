package com.buildweek.usemytechstuff.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "itemstatus")
public class ItemStatus
{
    /**
     * The primary key (long) of the users table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemstatusid;

    private String itemstatustype;

    /**
     * List of items associated with this itemstatus. Does not get save in the database
     * Forms a One-to-Many relationship with items. One itemstatus to many items
     */
    @OneToMany(mappedBy = "itemstatus", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "itemstatus", allowSetters = true)
    private Set<Item> items = new HashSet<>();

    // Constructors


    public ItemStatus()
    {
        //required by JPA
    }

    public ItemStatus(String itemstatustype)
    {
        this.itemstatustype = itemstatustype;
    }

    public long getItemstatusid()
    {
        return itemstatusid;
    }

    public void setItemstatusid(long itemstatusid)
    {
        this.itemstatusid = itemstatusid;
    }

    public String getItemstatustype()
    {
        return itemstatustype;
    }

    public void setItemstatustype(String itemstatustype)
    {
        this.itemstatustype = itemstatustype;
    }
}
