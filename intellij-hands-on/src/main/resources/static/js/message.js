let Message = {
    constants: {
        ajaxRetrieveParametersTemplate: {
            type: 'GET',
            accept: 'application/json',
            timeout: 1000
        },
        errorRetrieveMessageResponse: {
            type: 'error',
            content: 'Could not retrieve message.'
        },
        messagesDivQuery: 'div#messages',
        messageTypeDivQueryPrefix: 'div#messages-',
        messageTypes: ['information', 'error'],
        path: '/message'
    },
    display: async function (data) {

        if (data.id === undefined && data.content === undefined) {
            throw new Error('Either "id" or "content" should be informed.');
        }

        if (data.type === undefined) {
            throw new Error('"type" is not informed.')
        }

        if (!Message.constants.messageTypes.includes(data.type)) {
            throw new Error('Unrecognized message type "' + data.type + '".')
        }

        let messageTypeDivQuery = Message.constants.messageTypeDivQueryPrefix + data.type;

        let content;
        if (data.id !== undefined) {
            content = await Message.retrieveMessageFromServer(data.id);
        } else {
            content = data.content;
        }

        Message.clearAllMessageAreas();
        let messageTypeDiv = $(Message.constants.messagesDivQuery)
            .children(messageTypeDivQuery);
        messageTypeDiv.append("<span>" + content + "</span>");
    },
    clearAllMessageAreas: function () {
        Message.constants.messageTypes.forEach((type) => {
            Message.clearMessageArea(type)
        });
    },
    clearMessageArea: function (type) {
        let messageTypeDivQuery = Message.constants.messageTypeDivQueryPrefix + type;
        $(Message.constants.messagesDivQuery)
            .children(messageTypeDivQuery).empty();
    },
    retrieveMessageFromServer: async function (id) {

        let handleAjaxSuccess = function (resultObject) {
            return resultObject.message;
        };

        let handleAjaxError = function () {
            return Message.constants.errorRetrieveMessageResponse;
        };

        let ajaxParameters = $.extend(true, {}, Message.constants.ajaxRetrieveParametersTemplate);
        ajaxParameters.url = Message.constants.path + '/' + id;
        return $.ajax(ajaxParameters).done(handleAjaxSuccess).catch(handleAjaxError);
    }
};