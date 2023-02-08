CREATE TABLE hangar
(
    id              BIGSERIAL PRIMARY KEY,
    name            CHARACTER VARYING,
    planet          CHARACTER VARYING,
    is_available    BOOLEAN
);

CREATE TABLE ship
(
    id              BIGSERIAL PRIMARY KEY,
    name            CHARACTER VARYING,
    planet          CHARACTER VARYING,
    ship_type       CHARACTER VARYING,
    capacity        CHARACTER VARYING,
    power_of_engine CHARACTER VARYING,
    max_speed       CHARACTER VARYING,
    mileage         CHARACTER VARYING,
    hangar_id       BIGINT REFERENCES hangar (id)
);
