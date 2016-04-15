package org.example.rest.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.example.rest.dao.ArrivalPlansDao;
import org.example.rest.dao.HotelDao;
import org.example.rest.dao.RoomDao;
import org.example.rest.entity.ArrivalPlan;
import org.example.rest.entity.ArrivalPlan.StartEndDates;
import org.example.rest.entity.Hotel;
import org.example.rest.entity.HotelType;
import org.example.rest.entity.Room;
import org.example.rest.entity.RoomType;
import org.example.rest.entity.RoomView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    private static final Logger log = LoggerFactory.getLogger(Config.class);

    @Inject
    private RoomDao roomDao;
    @Inject
    private HotelDao hotelDao;

    @Inject
    private ArrivalPlansDao arrivalPlansDao;

    @PostConstruct
    void init() {
        final Hotel oneStar = new Hotel();
        oneStar.name = "One Star";
        oneStar.type = HotelType.Sanitarium;

        final Hotel twoStars = new Hotel();
        twoStars.name = "Two Stars";
        twoStars.type = HotelType.Sanitarium;
        twoStars.hasPool = true;
        twoStars.hasTennisCourt = true;

        final Hotel threeStars = new Hotel();
        threeStars.name = "Three Stars";
        threeStars.type = HotelType.Simple;
        threeStars.hasTennisCourt = true;

        final Hotel fourStars = new Hotel();
        fourStars.name = "Four Stars";
        fourStars.type = HotelType.Simple;
        fourStars.hasPool = true;

        final Hotel fiveStars = new Hotel();
        fiveStars.name = "Five Stars";
        fiveStars.type = HotelType.Simple;
        fiveStars.hasPool = true;
        fiveStars.hasTennisCourt = true;
        fiveStars.hasWaterslides = true;

        hotelDao.save(oneStar);
        hotelDao.save(twoStars);
        hotelDao.save(threeStars);
        hotelDao.save(fourStars);
        hotelDao.save(fiveStars);

        hotelDao.getAll().forEach(hotel -> {
            final int roomNumber = random.nextBoolean() ? 10 : 15;
            IntStream.rangeClosed(1, roomNumber).forEach(i -> {
                final Room room = createRoom();
                room.roomNumber = i;
                room.hotel = hotel;
                hotel.rooms.add(room);

                if (hotel.type == HotelType.Sanitarium) {
                    room.hasAirConditioning = false;
                }

                log.info("\r\n" + room);
            });
        });

        addArrivalPlans(oneStar);
        addArrivalPlans(twoStars);
    }

    private void addArrivalPlans(final Hotel hotel) {
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            final Date june01 = format.parse("01/06/2016");
            final Date june21 = format.parse("21/06/2016");
            final Date july01 = format.parse("01/07/2016");
            final Date july21 = format.parse("21/07/2016");
            final Date august01 = format.parse("01/08/2016");
            final Date august21 = format.parse("21/08/2016");

            final ArrivalPlan plan = new ArrivalPlan(hotel, new StartEndDates(june01, june21),
                    new StartEndDates(july01, july21), new StartEndDates(august01, august21));

            arrivalPlansDao.save(plan);
        } catch (final Exception ignore) {}

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
                return RoomType.Twin;
            case 1:
                return RoomType.Single;
            default:
                return RoomType.Double;
        }
    }
}
