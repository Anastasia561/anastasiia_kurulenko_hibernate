DROP TABLE IF EXISTS `city`;

CREATE TABLE `city`
(
    `id`         int         NOT NULL AUTO_INCREMENT,
    `name`       varchar(35) NOT NULL DEFAULT '',
    `country_id` int NULL,
    `district`   varchar(20) NOT NULL DEFAULT '',
    `population` int         NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `country`;

CREATE TABLE `country`
(
    `id`              int            NOT NULL DEFAULT '0',
    `code`            varchar(3)     NOT NULL DEFAULT '',
    `code_2`          varchar(2)     NOT NULL DEFAULT '',
    `name`            varchar(52)    NOT NULL DEFAULT '',
    `continent`       int            NOT NULL DEFAULT '0',
    `region`          varchar(26)    NOT NULL DEFAULT '',
    `surface_area`    decimal(10, 2) NOT NULL DEFAULT '0.00',
    `indep_year`      smallint                DEFAULT NULL,
    `population`      int            NOT NULL DEFAULT '0',
    `life_expectancy` decimal(3, 1)           DEFAULT NULL,
    `gnp`             decimal(10, 2)          DEFAULT NULL,
    `gnpo_id`         decimal(10, 2)          DEFAULT NULL,
    `local_name`      varchar(45)    NOT NULL DEFAULT '',
    `government_form` varchar(45)    NOT NULL DEFAULT '',
    `head_of_state`   varchar(60)             DEFAULT NULL,
    `capital`         int                     DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `country_ibfk_1` FOREIGN KEY (`capital`) REFERENCES `city` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE `city`
    ADD CONSTRAINT `city_ibfk_1` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;


DROP TABLE IF EXISTS `country_language`;

CREATE TABLE `country_language`
(
    `id`          int           NOT NULL AUTO_INCREMENT,
    `country_id`  int           NOT NULL,
    `language`    varchar(30)   NOT NULL DEFAULT '',
    `is_official` int           NOT NULL DEFAULT '0',
    `percentage`  decimal(4, 1) NOT NULL DEFAULT '0.0',
    PRIMARY KEY (`id`),
    CONSTRAINT `country_language_ibfk_1` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
