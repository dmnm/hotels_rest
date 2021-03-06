package org.example.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Room extends Entity {
    private static final long serialVersionUID = 4959369356203006858L;

    @JsonIgnore
    public Hotel hotel;
    public int roomNumber;
    public RoomType type = RoomType.Single;
    public RoomView view = RoomView.NONE;
    public boolean hasTV;
    public boolean hasBalcony;
    public boolean hasAirConditioning;

    @Override
    public String toString() {
        return "Room [id=" + getId() + ", roomNumber=" + roomNumber + ", hotel=" + hotel.name + ", type=" + type
                + ", view=" + view + ", hasTV=" + hasTV + ", hasBalcony=" + hasBalcony + ", hasAirConditioning="
                + hasAirConditioning + "]";
    }
}
