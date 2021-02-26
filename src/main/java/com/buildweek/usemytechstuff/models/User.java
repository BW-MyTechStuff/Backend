package com.buildweek.usemytechstuff.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User
{
    /**
     * The primary key (long) of the users table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    /**
     * A foreign key to userrole table
     * Forms a Many-to_one relationship with the userrole table
     * Many Users to one role type
     * Contains a object pointer to the full userrole object
     */
    @ManyToOne
    @JoinColumn(name = "userroleid", nullable = false)
    @JsonIgnoreProperties(value = "users", allowSetters = true)
    private UserRole userrole;

    private String username;

    private String email;

    private String password;

    /**
     * List of items associated with this user. Does not get save in the database
     * Forms a One-to-Many relationship with items. One user to many items
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private Set<Item> items = new HashSet<>();

    // Constructors


    public User()
    {
        // Required by JPA
    }

    public User(
        UserRole userrole,
        String username,
        String email,
        String password)
    {
        this.userrole = userrole;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters


    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public UserRole getUserrole()
    {
        return userrole;
    }

    public void setUserrole(UserRole userrole)
    {
        this.userrole = userrole;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
