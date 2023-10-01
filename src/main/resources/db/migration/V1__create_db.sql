CREATE TABLE client (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(200),
    CONSTRAINT CK_client_name CHECK (CHAR_LENGTH(name) > 2)
);

CREATE TABLE planet (
    id VARCHAR PRIMARY KEY,
    name VARCHAR(500),
    CONSTRAINT CK_planet_id CHECK (REGEXP_LIKE(id, '^[A-Z0-9]+$')),
    CONSTRAINT CK_planet_name CHECK (CHAR_LENGTH(name) > 0)
);

CREATE TABLE ticket (
    id IDENTITY PRIMARY KEY,
    created_at TIMESTAMP default CURRENT_TIMESTAMP,
    client_id BIGINT,
    from_planet_id VARCHAR(10),
    to_planet_id VARCHAR(10),
    FOREIGN KEY (client_id) REFERENCES client(id),
    FOREIGN KEY (from_planet_id) REFERENCES planet(id),
    FOREIGN KEY (to_planet_id) REFERENCES planet(id)
);