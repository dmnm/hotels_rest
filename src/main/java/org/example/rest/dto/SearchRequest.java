package org.example.rest.dto;

import javax.ws.rs.QueryParam;

public class SearchRequest {
    @QueryParam("roomType")
    public String roomType;
    @QueryParam("roomView")
    public String roomView;
    @QueryParam("hasTV")
    public boolean hasTV;
    @QueryParam("hasBalcony")
    public boolean hasBalcony;
    @QueryParam("hasAirCnditioner")
    public boolean hasAirCnditioner;
    @QueryParam("hasPool")
    public boolean hasPool;
    @QueryParam("hasWaterslides")
    public boolean hasWaterslides;
    @QueryParam("hasTennisCourt")
    public boolean hasTennisCourt;
}
