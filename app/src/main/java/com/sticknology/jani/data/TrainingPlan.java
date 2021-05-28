package com.sticknology.jani.data;

import java.util.ArrayList;

public class TrainingPlan {

    private String mName;
    private boolean mOnline;

    public TrainingPlan(String name, boolean online) {
        mName = name;
        mOnline = online;
    }

    public String getName() {
        return mName;
    }

    public boolean isOnline() {
        return mOnline;
    }

    private static int lastContactId = 0;

    public static ArrayList<TrainingPlan> createContactsList(int numContacts) {
        ArrayList<TrainingPlan> contacts = new ArrayList<TrainingPlan>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new TrainingPlan("Person " + ++lastContactId, i <= numContacts / 2));
        }

        return contacts;
    }
}
