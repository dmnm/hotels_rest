var debugMode = false;

debugMode = true;

var checked = "<span class='checked'></span>";
var unchecked = "<span class='unchecked'></span>"

function debug(data) {
    if (debugMode) {
        $("#debug").empty();
        $("#debug").append("<p>" + current.hotel + "</p>");
        $("#debug").append("<p>" + current.room + "</p>");
        $("#debug").append("<p>" + JSON.stringify(data) + "</p>");
    }
}

function asView(name) {
    return $("#hiddenBlock").find(name).clone();
}

function showErrors(message) {
    var msg = JSON.stringify(message);
    $("#errors").text(msg);
    $("#errors").show();
    alert("error: " + msg);
}

function showContent(content) {
    cleanContent();
    addContent(content);
}

function cleanContent() {
    $("#back").hide();
    $("#errors").empty();
    $("#errors").hide();
    $("#content").empty();
}

function addContent(content) {
    $("#content").append(content);
}

// Elements default states:
if (!debugMode) {
    $("#debug").remove();
}

$("#errors").hide();

$("#back").hide();

// Buttons default actions:
$("#home").click(function() {
    location.reload();
});

$("#back").click(function() {
    $("#back").hide();
    onBackClicked();
});

$("#search").click(function() {
    showSearchForm();
});

$("#admin").click(function() {
    location.href = "admin.html";
});

$("#index").click(function() {
    location.href = "index.html";
});
