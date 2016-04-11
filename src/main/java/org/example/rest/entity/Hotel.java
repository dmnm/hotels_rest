package org.example.rest.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class Hotel extends Entity {
    private static final long serialVersionUID = 1730048616163340749L;

    @NotNull
    public String name;
    // @JsonIgnore
    public List<Room> rooms = new ArrayList<>();
    public HotelType type = HotelType.Simple;
    public boolean hasPool;
    public boolean hasWaterslides;
    public boolean hasTennisCourt;
}
