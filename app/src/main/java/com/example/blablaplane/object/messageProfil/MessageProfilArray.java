package com.example.blablaplane.object.messageProfil;

import java.util.ArrayList;

public class MessageProfilArray extends ArrayList<MessageProfil> {
    private static MessageProfilArray instance;

    private MessageProfilArray() {
        super();
        add(new MessageProfil(0, "Melanie", "STANISLAS", "res/drawable/profile1.png"));
        add(new MessageProfil(1, "Julien", "TANTI", "res/drawable/profile1.png"));
        add(new MessageProfil(2, "Kaaris", "FPV", "res/drawable/profile1.png"));
    }

    /**
     * Get the instance of the MessageProfilArray
     *
     * @return the instance of the MessageProfilArray
     */
    public static MessageProfilArray getInstance() {
        if (instance == null) {
            instance = new MessageProfilArray();
        }
        return instance;
    }
}
