$(document).ready(function() {
	$.ajax({
		url : "http://localhost:8080/api/hotels"
	}).then(function(data) {
		// $('.id').append(data.id);
		$('.content').append(data[0].name);
		window.data = data;
	});
});