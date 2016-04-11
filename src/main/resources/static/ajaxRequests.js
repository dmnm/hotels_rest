var rootUrl = "http://localhost:8080/api";
var hotels = rootUrl + "/hotels/";
var rooms = "/rooms/";
var search = rootUrl + "/search/";

function getHotels(callback) {
    $.ajax({
        url : hotels,
        success : callback,
        error : showErrors
    });
}

function getHotel(callback) {
    $.ajax({
        url : hotels + current.hotel,
        success : callback,
        error : showErrors
    });
}

function getRooms(callback) {
    $.ajax({
        url : hotels + current.hotel + rooms,
        success : callback,
        error : showErrors
    });
}

function getRoom(callback) {
    $.ajax({
        url : hotels + current.hotel + rooms + current.room,
        success : callback,
        error : showErrors
    });
}
