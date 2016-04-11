var current = {
    hotel : null,
    room : null
};

function showHotels(hotels) {
    debug(hotels);

    cleanContent();
    $.each(hotels, function(i, hotel) {
        addContent('<p><a href="#" data-identity="' + hotel.id
                + '" onclick="onHotelClicked($(this).attr(`data-identity`));">' + hotel.name + '</a></p>');
    });
}

function showHotel(hotel) {
    debug(hotel);

    var details = asView(".hotelDetails");
    details.find(".name").text(hotel.name);
    details.find(".hasPool").text(hotel.hasPool);
    details.find(".hasWaterslides").text(hotel.hasWaterslides);
    details.find(".hasTennisCourt").text(hotel.hasTennisCourt);
    details.find(".roomCount").text(hotel.rooms.length);

    var dubl, single, twin, balcony, tv, airConditioning;
    $.each(hotel.rooms, function(i, room) {
        dubl = dubl || room.type == "Double";
        single = single || room.type == "Single";
        twin = twin || room.type == "Twin";
        balcony = balcony || room.hasBalcony;
        tv = tv || room.hasTV;
        airConditioning = airConditioning || room.hasAirConditioning;
    });

    details.find(".dubl").text(dubl);
    details.find(".single").text(single);
    details.find(".twin").text(twin);
    details.find(".balcony").text(balcony);
    details.find(".tv").text(tv);
    details.find(".airConditioning").text(airConditioning);

    if (hotel.type == "Sanitarium") {
        details.find(".name").append("<sup>*</sup>");
    }

    details.find("#availableRooms").click(function() {
        getRooms(showRooms);
    });

    showContent(details);
}

function showRooms(rooms) {
    debug(rooms);

    cleanContent();
    $.each(rooms, function(i, room) {
        var details = fillRoomDetails(room)
        details.find(".reservationDetails").hide();
        details.find(".reserve").click(function() {
            current.room = room.id;
            getRoom(showReservationForm);
        });

        addContent(details);
    });
}

function showReservationForm(room) {
    debug(room);

    var details = fillRoomDetails(room)
    details.find(".reserve").click(function() {
        submitForm();
    });
    
    showContent(details);
}

function submitForm() {
    alert("Reserved!");
    getHotels(showHotels);
}

function onHotelClicked(hotelId) {
    debug(hotelId);
    current.hotel = hotelId;
    getHotel(showHotel);
}

function fillRoomDetails(room) {
    var details = asView(".roomDetails");
    details.find(".type").text(room.type);
    details.find(".hasTV").text(room.hasTV);
    details.find(".hasBalcony").text(room.hasBalcony);
    details.find(".hasAirConditioning").text(room.hasAirConditioning);
    details.find(".view").text(room.view);
    return details;
}
