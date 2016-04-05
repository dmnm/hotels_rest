package org.example.rest.config;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.example.rest.dao.HotelDao;
import org.example.rest.dao.RoomDao;
import org.example.rest.entity.Hotel;
import org.example.rest.entity.HotelType;
import org.example.rest.entity.Room;
import org.example.rest.entity.RoomType;
import org.example.rest.entity.RoomView;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Inject
    private RoomDao roomDao;
    @Inject
    private HotelDao hotelDao;

    @PostConstruct
    void init() {
        final Hotel oneStar = new Hotel();
        oneStar.name = "One Star";
        oneStar.type = HotelType.SANATORIUM;

        final Hotel twoStars = new Hotel();
        twoStars.name = "Two Stars";
        twoStars.type = HotelType.SANATORIUM;
        twoStars.hasPool = true;
        twoStars.hasTennisCourt = true;

        final Hotel threeStars = new Hotel();
        threeStars.name = "Three Stars";
        threeStars.type = HotelType.SIMPLE;
        threeStars.hasTennisCourt = true;

        final Hotel fourStars = new Hotel();
        fourStars.name = "Four Stars";
        fourStars.type = HotelType.SIMPLE;
        fourStars.hasPool = true;

        final Hotel fiveStars = new Hotel();
        fiveStars.name = "Five Stars";
        fiveStars.type = HotelType.SIMPLE;
        fiveStars.hasPool = true;
        fiveStars.hasTennisCourt = true;
        fiveStars.hasWaterslides = true;

        hotelDao.save(oneStar);
        hotelDao.save(twoStars);
        hotelDao.save(threeStars);
        hotelDao.save(fourStars);
        hotelDao.save(fiveStars);

        for (final Hotel hotel : hotelDao.getAll()) {
            final int roomNumber = random.nextBoolean() ? 10 : 15;
            for (int i = 1; i <= roomNumber; i++) {
                final Room r = createRoom();
                r.roomNumber = i;
                r.hotel = hotel;
                hotel.rooms.add(r);
                System.out.println(r);
            }
        }
    }

    private Room createRoom() {
        final Room r = new Room();
        r.type = type();
        r.view = view();
        r.hasTV = random.nextBoolean();
        r.hasBalcony = random.nextBoolean();
        r.hasAirConditioning = random.nextBoolean();

        return roomDao.save(r);
    }

    private static final Random random = new Random();

    private static RoomView view() {
        final int i = random.nextInt(15) % 3;
        switch (i) {
            case 0:
                return RoomView.TRASH_HEAP_VIEW;
            case 1:
                return RoomView.SEA_VIEW;
            default:
                return RoomView.POOL_VIEW;
        }
    }

    private static RoomType type() {
        final int i = random.nextInt(15) % 3;
        switch (i) {
            case 0:
                return RoomType.TWIN;
            case 1:
                return RoomType.SINGLE;
            default:
                return RoomType.DOUBLE;
        }
    }
}
