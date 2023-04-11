package com.example.blablaplane.object;

import java.util.ArrayList;

public class UserArray extends ArrayList<User> {
    private static UserArray instance;

    private UserArray() {
        super();
        add(new User(1, "Mario", "Rossi", "mario.rossi@gmail.com", "password", "3333333333", 4.5F));
        add(new User(2, "Luigi", "Verdi", "luigi.verdi@gmail.com", "password", "3333333333", 4.35F));
        add(new User(3, "Giovanni", "Bianchi", "giovanni.bianchi@gmail.com", "password", "3333333333", 3.2F));
        add(new User(4, "Francesco", "Neri", "francesco@gmail.com", "password", "3333333333", 0));
    }

    /**
     * Get the instance of the UserArray
     *
     * @return the instance of the UserArray
     */
    public static UserArray getInstance() {
        if (instance == null) {
            instance = new UserArray();
        }
        return instance;
    }

    /**
     * Get a user by its id
     *
     * @param id the id of the user
     * @return the user with the given id
     */
    public User getUserById(int id) {
        for (User user : this) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
