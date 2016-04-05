package org.example.rest.entity;

import java.util.ArrayList;
import java.util.List;

public class Hotel extends Entity {
    private static final long serialVersionUID = 1730048616163340749L;

    public String name;
    public transient List<Room> rooms = new ArrayList<>();
    public HotelType type;
    public boolean hasPool;
    public boolean hasWaterslides;
    public boolean hasTennisCourt;
}
