var rootUrl = "http://localhost:8080/api";
var hotels = rootUrl + "/hotels/";
var rooms = "/rooms/";
var plans = "/plans/"
var search = rootUrl + "/search/";
var reserve = "/reserve/"

$.ajaxSetup({
    beforeSend : function(xhr) {
        xhr.setRequestHeader("Authorization", "Basic YXBpQ2xpZW50OmFwaUNsaWVudA==");
    }
});

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

function getPlans(callback) {
    $.ajax({
        url : hotels + current.hotel + plans,
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

function reserveRoom(data, callback, onFailure) {
    $.ajax({
        method : "POST",
        contentType : "application/json",
        dataType : "json",
        data : JSON.stringify(data),
        url : hotels + current.hotel + rooms + current.room + reserve,
        success : callback,
        error : onFailure
    });
}

function searchRooms(data, callback, onFailure) {
    $.ajax({
        contentType : "application/json",
        dataType : "json",
        data : data,
        url : search,
        success : callback,
        error : onFailure
    });
}

function addHotel(data, callback) {
    $.ajax({
        method : "POST",
        contentType : "application/json",
        dataType : "json",
        data : JSON.stringify(data),
        url : hotels,
        success : callback,
        error : showErrors
    });
}

function updateHotel(data, callback) {
    $.ajax({
        method : "PUT",
        contentType : "application/json",
        dataType : "json",
        data : JSON.stringify(data),
        url : hotels + current.hotel,
        success : callback,
        error : showErrors
    });
}

function deleteHotels(callback) {
    $.ajax({
        method : "DELETE",
        url : hotels + current.hotel,
        success : callback,
        error : showErrors
    });
}

function deleteRoom(callback) {
    $.ajax({
        method : "DELETE",
        url : hotels + current.hotel + rooms + current.room,
        success : callback,
        error : showErrors
    });
}

function addRoom(data, callback) {
    $.ajax({
        method : "POST",
        contentType : "application/json",
        dataType : "json",
        data : JSON.stringify(data),
        url : hotels + current.hotel + rooms,
        success : callback,
        error : showErrors
    });
}

function updateRoom(data, callback) {
    $.ajax({
        method : "PUT",
        contentType : "application/json",
        dataType : "json",
        data : JSON.stringify(data),
        url : hotels + current.hotel + rooms + current.room,
        success : callback,
        error : showErrors
    });
}
