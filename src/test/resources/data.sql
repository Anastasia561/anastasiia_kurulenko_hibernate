INSERT INTO `city` (id, name, district, population)
VALUES (1, 'Kabul', 'Kabol', 1780000),
       (2, 'Qandahar', 'Qandahar', 237500),
       (3, 'Herat', 'Herat', 186800),
       (4, 'Mazar-e-Sharif', 'Balkh', 127800),
       (5, 'Amsterdam', 'Noord-Holland', 731200);

INSERT INTO `country`
VALUES (1, 'ABW', 'AW', 'Aruba', 2, 'Caribbean', 193.00, NULL, 103000, 78.4, 828.00, 793.00, 'Aruba',
        'Nonmetropolitan Territory of The Netherlands', 'Beatrix', 1),
       (2, 'AFG', 'AF', 'Afghanistan', 0, 'Southern and Central Asia', 652090.00, 1919, 22720000, 45.9, 5976.00, NULL,
        'Afganistan/Afqanestan', 'Islamic Emirate', 'Mohammad Omar', 2),
       (3, 'AGO', 'AO', 'Angola', 3, 'Central Africa', 1246700.00, 1975, 12878000, 38.3, 6648.00, 7984.00, 'Angola',
        'Republic', 'José Eduardo dos Santos', 4),
       (4, 'AIA', 'AI', 'Anguilla', 2, 'Caribbean', 96.00, NULL, 8000, 76.1, 63.20, NULL, 'Anguilla',
        'Dependent Territory of the UK', 'Elisabeth II', 5),
       (5, 'ALB', 'AL', 'Albania', 1, 'Southern Europe', 28748.00, 1912, 3401200, 71.6, 3205.00, 2500.00, 'Shqipëria',
        'Republic', 'Rexhep Mejdani', 3);

UPDATE `city`
SET country_id = 1
WHERE id = 1;

UPDATE `city`
SET country_id = 3
WHERE id = 2;

UPDATE `city`
SET country_id = 2
WHERE id = 5;

UPDATE `city`
SET country_id = 5
WHERE id = 4;

UPDATE `city`
SET country_id = 4
WHERE id = 3;

INSERT INTO `country_language`
VALUES (1, 1, 'Dutch', 1, 5.3),
       (2, 1, 'English', 0, 9.5),
       (3, 1, 'Papiamento', 0, 76.7),
       (4, 1, 'Spanish', 0, 7.4),
       (5, 2, 'Balochi', 0, 0.9),
       (6, 5, 'Dari', 1, 32.1),
       (7, 2, 'Pashto', 1, 52.4),
       (8, 5, 'Turkmenian', 0, 1.9),
       (9, 2, 'Uzbek', 0, 8.8),
       (10, 3, 'Ambo', 0, 2.4),
       (11, 3, 'Chokwe', 0, 4.2),
       (12, 3, 'Kongo', 0, 13.2),
       (13, 3, 'Luchazi', 0, 2.4),
       (14, 4, 'Luimbe-nganguela', 0, 5.4),
       (15, 4, 'Luvale', 0, 3.6);
