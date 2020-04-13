USE intellij_hands_on_db;

CREATE TABLE aspirations (
    pers_id  BINARY(16) NOT NULL,
    skil_id  BINARY(16) NOT NULL
);

ALTER TABLE aspirations ADD CONSTRAINT aspi_pk PRIMARY KEY ( pers_id,
                                                             skil_id );

CREATE TABLE knowledges (
    pers_id  BINARY(16) NOT NULL,
    skil_id  BINARY(16) NOT NULL
);

ALTER TABLE knowledges ADD CONSTRAINT know_pk PRIMARY KEY ( skil_id,
                                                            pers_id );

CREATE TABLE persons (
    id    BINARY(16) NOT NULL,
    name  VARCHAR(64) NOT NULL
);

ALTER TABLE persons ADD CONSTRAINT pers_pk PRIMARY KEY ( id );

ALTER TABLE persons ADD CONSTRAINT pers_uk UNIQUE ( name );

CREATE TABLE skills (
    id    BINARY(16) NOT NULL,
    name  VARCHAR(64) NOT NULL
);

ALTER TABLE skills ADD CONSTRAINT skil_pk PRIMARY KEY ( id );

ALTER TABLE skills ADD CONSTRAINT skil_uk UNIQUE ( name );

ALTER TABLE aspirations
    ADD CONSTRAINT aspi_pers_fk FOREIGN KEY ( pers_id )
        REFERENCES persons ( id );

ALTER TABLE aspirations
    ADD CONSTRAINT aspi_skil_fk FOREIGN KEY ( skil_id )
        REFERENCES skills ( id );

ALTER TABLE knowledges
    ADD CONSTRAINT know_pers_fk FOREIGN KEY ( pers_id )
        REFERENCES persons ( id );

ALTER TABLE knowledges
    ADD CONSTRAINT know_skil_fk FOREIGN KEY ( skil_id )
        REFERENCES skills ( id );
