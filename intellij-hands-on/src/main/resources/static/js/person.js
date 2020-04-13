let Person = {
    constants: {
        path: '/person',
        ajaxDeleteParametersTemplate: {
            type: 'DELETE',
            contentType: 'application/json',
            accept: 'application/json',
            timeout: 1000
        },
        errorDeleteResponse: {
            message: {
                type: 'error',
                content: 'An error occurred while trying to delete. Please refresh the page and try again later.'
            }
        },
        personRowIdQueryPrefix: 'tr#person-',
        personsListDivIdQuery: 'div#persons-list',
        personsTableIdQuery: 'table#persons-table',
        emptyPersonsListMessageId: 'site.person-list.empty'
    },
    delete: async function (id) {
        let deleteResponse = await Person.requestPersonDelete(id);
        await Message.display(deleteResponse.message);
    },
    requestPersonDelete: async function (id) {
        if (id === undefined) {
            throw Error('"Id" parameter should be informed.');
        }

        function isPersonsListIsEmpty() {
            let personDiv = $(Person.constants.personsListDivIdQuery);
            let personsTableBody = personDiv.find(Person.constants.personsTableIdQuery).find('tbody');
            return ( personsTableBody.children().length === 0);
        }

        async function showEmptyPersonsListMessage() {
            let personDiv = $(Person.constants.personsListDivIdQuery);
            let personsTableBody = personDiv.find(Person.constants.personsTableIdQuery).find('tbody');
            if ( personsTableBody.children().length === 0) {
                let emptyPersonsListMessage = await Message.retrieveMessageFromServer(Person.constants.emptyPersonsListMessageId);
                personDiv.empty();
                personDiv.append("<p>" + emptyPersonsListMessage.message.content + "</p>");
            }
        }

        let handleAjaxSuccess = async function (data, textStatus, jqXHR) {
            if (jqXHR.status / 100 === 2) {
                $(Person.constants.personRowIdQueryPrefix+id).remove();
                if (isPersonsListIsEmpty()) {
                    await showEmptyPersonsListMessage();
                }
            }
            return data;
        };

        let handleAjaxError = function (data, textStatus, errorThrown) {

            if (errorThrown === 'timeout' || data.responseJSON === undefined) {
                return Person.constants.errorDeleteResponse;
            }

            return data.responseJSON;
        };

        let ajaxParameters = $.extend(true, {}, Person.constants.ajaxDeleteParametersTemplate);
        ajaxParameters.url = Person.constants.path + '/' + id;

        return $.ajax(ajaxParameters).done(handleAjaxSuccess)
            .catch(handleAjaxError);
    }
};