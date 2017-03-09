DROP TABLE IF EXISTS journalists;
CREATE TABLE journalists (
    id_journalist   INT              NOT NULL AUTO_INCREMENT,
    name_journalist VARCHAR(150)     NOT NULL,
    birth_date      DATE             NOT NULL,
    rating          INT              NOT NULL,
    PRIMARY KEY (id_journalist),
);

DROP TABLE IF EXISTS articles;
CREATE TABLE articles (
    id_article      INT              NOT NULL AUTO_INCREMENT,
    naim            VARCHAR(150)     NOT NULL,
    create_date     DATE             NOT NULL,
    popularity      INT              NOT NULL,
    id_journalist   INT              NOT NULL,
    PRIMARY KEY (id_article),
    FOREIGN KEY (id_journalist) REFERENCES journalists (id_journalist)
);