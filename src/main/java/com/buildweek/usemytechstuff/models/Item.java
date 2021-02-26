package com.buildweek.usemytechstuff.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item extends Auditable
{
    /**
     * The primary key (long) of the users table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemid;

    /**
     * A foreign key to users table
     * Forms a Many-to_one relationship with the users table
     * Many Users to one item
     * Contains a object pointer to the full users object
     */
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties(value = "items", allowSetters = true)
    private User user;

    private String itemname;

    private String itemdescription;

    private float itemcostperday;

    /**
     * A foreign key to itemstatus table
     * Forms a Many-to_one relationship with the itemstatus table
     * Many itemstatus to one item
     * Contains a object pointer to the full itemstatus object
     */
    @ManyToOne
    @JoinColumn(name = "itemstatusid", nullable = false)
    @JsonIgnoreProperties(value = "items", allowSetters = true)
    private ItemStatus itemstatus;

    private int numberofdaysrented;

    // Contructors

    public Item()
    {
        // required by JPA
    }

    public Item(
        User user,
        String itemname,
        String itemdescription,
        float itemcostperday,
        ItemStatus itemstatus,
        int numberofdaysrented)
    {
        this.user = user;
        this.itemname = itemname;
        this.itemdescription = itemdescription;
        this.itemcostperday = itemcostperday;
        this.itemstatus = itemstatus;
        this.numberofdaysrented = numberofdaysrented;
    }

    // Getters and Setters

    public long getItemid()
    {
        return itemid;
    }

    public void setItemid(long itemid)
    {
        this.itemid = itemid;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getItemname()
    {
        return itemname;
    }

    public void setItemname(String itemname)
    {
        this.itemname = itemname;
    }

    public String getItemdescription()
    {
        return itemdescription;
    }

    public void setItemdescription(String itemdescription)
    {
        this.itemdescription = itemdescription;
    }

    public float getItemcostperday()
    {
        return itemcostperday;
    }

    public void setItemcostperday(float itemcostperday)
    {
        this.itemcostperday = itemcostperday;
    }

    public ItemStatus getItemstatus()
    {
        return itemstatus;
    }

    public void setItemstatus(ItemStatus itemstatus)
    {
        this.itemstatus = itemstatus;
    }

    public int getNumberofdaysrented()
    {
        return numberofdaysrented;
    }

    public void setNumberofdaysrented(int numberofdaysrented)
    {
        this.numberofdaysrented = numberofdaysrented;
    }
}
