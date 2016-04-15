package org.example.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

import org.example.rest.dao.HotelDao;
import org.example.rest.dto.SearchRequest;
import org.example.rest.entity.Hotel;
import org.example.rest.entity.Room;
import org.example.rest.entity.RoomType;
import org.example.rest.entity.RoomView;

@Named
public class SearchService {
    @Inject
    private HotelDao hotelDao;

    public List<Room> search(final SearchRequest rq) {
        final List<Room> result = new ArrayList<>();

        final RoomType type = RoomType.byValue(rq.roomType);
        final RoomView view = RoomView.byValue(rq.roomView);
        
        boolean ignoreType = type == null;
        boolean ignoreView = view == null;

        final List<Hotel> hotels = hotelDao.findByParams(rq.hasPool, rq.hasWaterslides, rq.hasTennisCourt);
        hotels.forEach(h -> {
            result.addAll(
                    h.rooms.stream().filter(r ->
                        (r.type == type || ignoreType) &&
                        (r.view == view || ignoreView) && 
                        (r.hasTV == rq.hasTV) &&
                        (r.hasBalcony == rq.hasBalcony) &&
                        (r.hasAirConditioning == rq.hasAirCnditioner)
                    ).collect(Collectors.toCollection(ArrayList::new)));
        });

        return result;
    }

}
