var postLogoutSuccessCallback = function() {
    $("#user").html('');
    $(".unauthenticated").show();
    $(".authenticated").hide();
}

var logout = function() {
    $.post("/logout", postLogoutSuccessCallback);
    return true;
}

