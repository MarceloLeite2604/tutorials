var getErrorSuccessCallback = function(data) {
     if (data) {
         $(".error").html(data);
     } else {
         $(".error").html('');
     }
}

$.get("/error", getErrorSuccessCallback);