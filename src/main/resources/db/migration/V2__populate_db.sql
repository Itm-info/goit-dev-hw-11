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
    ('MERC', 'Mercury'),
    ('VEN','Venus'),
    ('EAR','Earth'),
    ('MAR','Mars'),
    ('JUP','Jupiter')
;

INSERT INTO ticket (client_id, from_planet_id, to_planet_id)
    SELECT client.id, planet.id, planet.id FROM client, planet LIMIT 10;
