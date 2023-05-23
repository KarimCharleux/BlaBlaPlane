package com.example.blablaplane.object.message;

import java.util.ArrayList;

public class MessageProfileArray extends ArrayList<MessageProfile> {
    private static MessageProfileArray instance;

    private MessageProfileArray() {
        super();
        add(new MessageProfile(0, "Melanie", "STANISLAS", "res/drawable/profile1.png"));
        add(new MessageProfile(1, "Julien", "TANTI", "res/drawable/profile1.png"));
        add(new MessageProfile(2, "Kaaris", "FPV", "res/drawable/profile1.png"));
    }

    /**
     * Get the instance of the MessageProfileArray
     *
     * @return the instance of the MessageProfileArray
     */
    public static MessageProfileArray getInstance() {
        if (instance == null) {
            instance = new MessageProfileArray();
        }
        return instance;
    }
}
