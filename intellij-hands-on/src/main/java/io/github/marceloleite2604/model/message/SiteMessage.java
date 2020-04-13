package io.github.marceloleite2604.model.message;

import java.io.File;

public enum SiteMessage implements Message {

    PERSON_CREATED("person.created"),
    PERSON_UPDATED("person.updated"),
    PERSON_DELETED("person.deleted"),
    COULD_NOT_DELETE_PERSON("person.could-not-delete"),
    COULD_NOT_GET_MESSAGE("message.could-not-get");

    public static final String FILE_PATH = "site/site".replace("/",File.separator);

    public static final String MESSAGE_PREFIX = "site.";

    private String value;

    private SiteMessage(String value) {
        this.value = value;
    }

    @Override
    public String getCode() {
        return MESSAGE_PREFIX + value;
    }
}
