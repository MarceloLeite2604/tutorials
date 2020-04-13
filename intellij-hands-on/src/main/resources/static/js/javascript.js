function deleteElement(url, id) {
    $.ajax({
        url: url,
        type: 'DELETE',
        contentType: 'application/json',
        accept: 'application/json',
        complete: handleServerResult
    });
}

function handleServerResult(data) {

    switch (data.status) {
        case 200:
            location.reload();
            break;
        case 404:
            // TODO Complete
            break;
        case 500:
            // TODO Complete
            break;
        default:
            // TODO Complete
            break;
    }
    if (data.status === 200) {
        console.log(data.responseJSON.message);
    }
}