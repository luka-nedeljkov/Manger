package me.manger.model;

import me.manger.model.building.Building;
import me.manger.model.user.User;

public class Session {

    public User loggedIn;
    public Building activeBuilding;

    public Session(User loggedIn) {
        this.loggedIn = loggedIn;
    }

}
