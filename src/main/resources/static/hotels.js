var debug = true;
var rootUrl = "http://localhost:8080/api/";
var hotels = rootUrl + "hotels/";
var rooms = rootUrl + "rooms/";
var search = rootUrl + "search/";

$(document).ready(function() {
    loadHotels(showHotels);
});

if (!debug) {
    $("#debug").remove();
}

$("#home").click(function() {
    loadHotels(showHotels);
});

function loadHotels(callback) {
    $.ajax({
        url : hotels,
        success : callback,
        error : showErrors
    });
}

function loadHotel(hotelId, callback) {
    $.ajax({
        url : hotels + hotelId,
        success : callback,
        error : showErrors
    });
}

function showHotels(data) {
    if (debug) {
        $("#debug").text(JSON.stringify(data));
        window.data = data;
    }

    clean();

    $.each(data, function(i, hotel) {
        $('#content').append(
                '<p><a href="#" data-identity="' + hotel.id
                        + '" onclick="onHotelClicked($(this).attr(`data-identity`));">' + hotel.name + '</a></p>');
    });
}

function showHotel(data) {
    if (debug) {
        $("#debug").text(JSON.stringify(data));
    }

    var details = $(".hotelDetails").clone();
    details.find(".name").text(data.name);
    details.find(".type").text(data.type.toLowerCase());
    details.find(".hasPool").text(data.hasPool);
    details.find(".hasWaterslides").text(data.hasWaterslides);
    details.find(".hasTennisCourt").text(data.hasTennisCourt);
    details.find(".roomCount").text(data.rooms.length);

    var dubl, single, twin, balcony, tv, airConditioning;
    $.each(data.rooms, function(i, room) {
        dubl = dubl || room.type == "DOUBLE";
        single = single || room.type == "SINGLE";
        twin = twin || room.type == "TWIN";
        balcony = balcony || room.hasBalcony;
        tv = tv || room.hasTV;
        airConditioning = airConditioning || room.hasAirConditioning;
    })

    details.find(".dubl").text(dubl);
    details.find(".single").text(single);
    details.find(".twin").text(twin);
    details.find(".balcony").text(balcony);
    details.find(".tv").text(tv);
    details.find(".airConditioning").text(airConditioning);

    showContent(details);
}

function onHotelClicked(hotelId) {
    if (debug) {
        $("#debug").text(hotelId);
    }
    loadHotel(hotelId, showHotel);
}

function showErrors(message) {
    alert("error: " + message);
}

function showContent(content) {
    $("#content").empty();
    $("#content").append(content);
}

function clean() {
    $("#content").empty();
}
