INSERT INTO client (name) VALUES
    ('jegdeukg'),
    ('gdfhhfhj'),
    ('sfhsfhsg'),
    ('fsgd'),
    ('gfdgf'),
    ('gteg'),
    ('erbb'),
    ('ngsbg'),
    ('dsfss'),
    ('sggsw')
;

INSERT INTO planet VALUES
    ('MERC','Mercury'),
    ('VENU','Venus'),
    ('EART','Earth'),
    ('MARS','Mars'),
    ('JUPI','Jupiter'),
    ('SATU','Saturn'),
    ('URAN','Uranus'),
    ('NEPT','Neptune'),
    ('PLUT','Pluto')
;

INSERT INTO ticket (client_id, planet_from_id, planet_to_id)
    SELECT client.id, planet.id, planet.id FROM client, planet LIMIT 10;
