package org.example.rest.entity;

public class Room extends Entity {
    private static final long serialVersionUID = 4959369356203006858L;

    public Hotel hotel;
    public int roomNumber;
    public RoomType type;
    public RoomView view;
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
