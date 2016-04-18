function onDeleteHotelclicked() {
    if (confirm("Are you sure?")) {
        deleteHotels(function() {
            alert("Success!");
            location.reload();
        });
    }
}

function onEditHotelclicked(hotel) {
    $("#editHotelBtn").hide();

    var form = asView(".addHotelForm");
    form.find(".hiddenHotelId").val(current.hotel);
    form.find(".name").val(hotel.name);
    form.find(".type").val(hotel.type);
    form.find(".hasPool").prop("checked", hotel.hasPool);
    form.find(".hasWaterslides").prop("checked", hotel.hasWaterslides);
    form.find(".hasTennisCourt").prop("checked", hotel.hasTennisCourt);

    form.find("#saveHotelBtn").click(function() {
        onSaveHotelClicked(form);
    });

    form.find("h3").text("Edit hotel");

    addContent(form);
}

function onAddHotelClicked() {
    $("#addHotelBtn").hide();

    var form = asView(".addHotelForm");
    form.find("#saveHotelBtn").click(function() {
        onSaveHotelClicked(form);
    });

    addContent(form);
}

function onSaveHotelClicked(form, addHotelFlow) {
    var hotelId = current.hotel;
    var addHotelFlow = hotelId == 0 ? true : false;

    var data = {
        id : addHotelFlow ? null : hotelId,
        name : form.find(".name").val(),
        type : form.find(".type").val(),
        hasPool : form.find(".hasPool").prop("checked"),
        hasWaterslides : form.find(".hasWaterslides").prop("checked"),
        hasTennisCourt : form.find(".hasTennisCourt").prop("checked"),
    };

    if (addHotelFlow) {
        addHotel(data, function(result) {
            alert("Success!");
            location.reload();
        });
    } else {
        updateHotel(data, function(result) {
            alert("Success!");
            onHotelClicked(hotelId);
        });
    }
}

function onAddRoomClicked() {
    current.room = 0;

    var form = asView(".addRoomForm");
    form.find("#saveRoomBtn").click(function() {
        onSaveRoomClicked(form);
    });

    showContent(form);
}

function onEditRoomClicked(room) {
    var form = asView(".addRoomForm");
    form.find(".roomNumber").val(room.roomNumber);
    form.find(".type").val(room.type);
    form.find(".view").val(room.view);
    form.find(".hasTV").prop("checked", room.hasTV);
    form.find(".hasBalcony").prop("checked", room.hasBalcony);
    form.find(".hasAirConditioning").prop("checked", room.hasAirConditioning);

    form.find("#saveRoomBtn").click(function() {
        onSaveRoomClicked(form);
    });

    form.find("h3").text("Edit room");

    showContent(form);
}

function onSaveRoomClicked(form) {
    var roomId = current.room;
    var addRoomFlow = roomId == 0 ? true : false;

    var data = {
        id : addRoomFlow ? null : roomId,
        roomNumber : form.find(".roomNumber").val(),
        type : form.find(".type").val(),
        view : form.find(".view").val(),
        hasTV : form.find(".hasTV").prop("checked"),
        hasBalcony : form.find(".hasBalcony").prop("checked"),
        hasAirConditioning : form.find(".hasAirConditioning").prop("checked"),
    };

    if (addRoomFlow) {
        addRoom(data, function(result) {
            alert("Success!");
            getRooms(showRooms);
        });
    } else {
        updateRoom(data, function(result) {
            alert("Success!");
            getRooms(showRooms);
        });
    }
}

function onDeleteRoomClicked() {
    if (confirm("Are you sure?")) {
        deleteRoom(function() {
            alert("Success!");
            getRooms(showRooms);
        });
    }
}
