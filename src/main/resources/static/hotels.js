var current = {
    hotel : 0,
    room : 0,
};

function showHotels(hotels) {
    debug(hotels);

    cleanContent();
    addContent("<h1>Available hotels</h1>");

    $.each(hotels, function(i, hotel) {
        addContent('<p><a href="#" data-identity="' + hotel.id
                + '" onclick="onHotelClicked($(this).attr(`data-identity`));">' + hotel.name + '</a></p>');
    });
}

function showHotel(hotel) {
    debug(hotel);

    var details = asView(".hotelDetails");
    var arrivalPlansBtn = details.find("#arrivalPlansBtn");
    var arrivalPlans = details.find(".arrivalPlans");

    arrivalPlansBtn.hide();
    arrivalPlans.hide();

    details.find(".name").text(hotel.name);

    details.find(".hasPool").append(hotel.hasPool ? checked : unchecked);
    details.find(".hasWaterslides").append(hotel.hasWaterslides ? checked : unchecked);
    details.find(".hasTennisCourt").append(hotel.hasTennisCourt ? checked : unchecked);
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

    details.find(".dubl").append(dubl ? checked : unchecked);
    details.find(".single").append(single ? checked : unchecked);
    details.find(".twin").append(twin ? checked : unchecked);
    details.find(".balcony").append(hotel.hasPool ? checked : unchecked);
    details.find(".tv").append(balcony ? checked : unchecked);
    details.find(".airConditioning").append(airConditioning ? checked : unchecked);

    if (hotel.type == "Sanitarium") {
        details.find(".name").append("<sup>*</sup>");
        arrivalPlansBtn.show();
        arrivalPlansBtn.click(function() {
            arrivalPlansBtn.hide();
            onArrivalPlansClicked(arrivalPlans);
        });
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

    var form = fillRoomDetails(room)
    form.find(".endDate").datepicker({
        dateFormat : "yy-mm-dd"
    });
    form.find(".startDate").datepicker({
        dateFormat : "yy-mm-dd"
    });
    form.find(".reserve").click(function() {
        submitForm(form);
    });

    showContent(form);
    $("#back").show();
}

function submitForm(form) {
    var guestName = form.find(".guestName").val();
    var guestPhone = form.find(".guestPhone").val();
    var startDate = form.find(".startDate").val();
    var endDate = form.find(".endDate").val();

    var data = {
        roomId : current.room,
        personName : guestName,
        personPhone : guestPhone,
        startDate : startDate,
        endDate : endDate,
    };

    debug(data);

    reserveRoom(data, function() {
        alert("Thanks for your cooperation.");
        getHotels(showHotels);
    }, function(xhr, status, thrown) {
        if (xhr.status == 400) {
            var errors = $("#errors");
            errors.empty();
            errors.show();

            $.each(xhr.responseJSON, function(i, e) {
                errors.append("<p class='red'>" + e.message + "</p>");
            });
        } else {
            showErrors(xhr);
        }
    });
}

function showSearchForm() {
    var form = asView(".searchForm");
    form.find(".doSearch").click(function() {
        doSearch(form);
    });
    showContent(form);
}

function doSearch(form) {
    var data = {
        roomType : form.find(".type").val(),
        roomView : form.find(".view").val(),
        hasTV : form.find(".hasTV").prop("checked"),
        hasBalcony : form.find(".hasBalcony").prop("checked"),
        hasAirCnditioner : form.find(".hasAirConditioning").prop("checked"),
        hasPool : form.find(".hasPool").prop("checked"),
        hasWaterslides : form.find(".hasWaterslides").prop("checked"),
        hasTennisCourt : form.find(".hasTennisCourt").prop("checked"),
    };

    searchRooms(data, function(result) {
        current.hotel = 0;
        showRooms(result);
    }, function(xhr, status, thrown) {
        debug(xhr);
        if (xhr.status == 404) {
            var errors = $("#errors");
            errors.empty();
            errors.show();
            errors.append("<p class='red'>Sorry, no results found</p>");
        } else {
            showErrors(xhr);
        }
    });
}

function onHotelClicked(hotelId) {
    debug(hotelId);
    current.hotel = hotelId;
    getHotel(showHotel);
}

function onArrivalPlansClicked(content) {
    getPlans(function(data) {
        $.each(data.ranges, function(i, plan) {
            content.append("<p>From " + new Date(plan.startDate).toLocaleDateString() + " to "
                    + new Date(plan.endDate).toLocaleDateString() + "</p>");
        });
        content.show();
    });
}

function onBackClicked() {
    if (current.hotel == 0) {
        showSearchForm();
    } else {
        getRooms(showRooms);
    }
}

function fillRoomDetails(room) {
    var details = asView(".roomDetails");
    details.find(".roomNumber").text(room.roomNumber);
    details.find(".type").text(room.type);
    details.find(".hasTV").append(room.hasTV ? checked : unchecked);
    details.find(".hasBalcony").append(room.hasBalcony ? checked : unchecked);
    details.find(".hasAirConditioning").append(room.hasAirConditioning ? checked : unchecked);
    details.find(".view").text(room.view);
    return details;
}
