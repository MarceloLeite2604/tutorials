var beforeSendAddCsrfToken = function(xhr, settings) {
    /* Somente verbos HTTP POST, PUT e DELETE */
    if (settings.type == 'POST' || settings.type == 'PUT' || settings.type == 'DELETE') {

        /* Somente para endereços locais. */
        if ( !( /^http:.*/.test(settings.url) || /^https:.*/.test(settings.url) ) ) {
            xhr.setRequestHeader("X-XSRF-TOKEN", Cookies.get('XSRF-TOKEN'));
        }
    }
}

$.ajaxSetup({
    beforeSend : beforeSendAddCsrfToken
});