var getUserSuccessCallback = function(data) {
    $("#user").html(data.name);
    $(".unauthenticated").hide()
    $(".authenticated").show()
};

$.get("/user", getUserSuccessCallback);