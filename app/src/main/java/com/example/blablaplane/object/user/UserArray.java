package com.example.blablaplane.object.user;

import java.util.ArrayList;

public class UserArray extends ArrayList<User> {
    private static UserArray instance;

    private UserArray() {
        super();
        add(new User("Mario", "Rossi", "mario.rossi@gmail.com", "password", "3333333333", 4.5F));
        add(new User("Luigi", "Verdi", "luigi.verdi@gmail.com", "password", "3333333333", 4.35F));
        add(new User("Giovanni", "Bianchi", "giovanni.bianchi@gmail.com", "password", "3333333333", 3.2F));
        add(new User("Francesco", "Neri", "francesco@gmail.com", "password", "3333333333", 0));
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
    public User getUserById(String id) {
        for (User user : this) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
