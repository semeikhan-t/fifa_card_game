DROP TABLE IF EXISTS matches CASCADE;
DROP TABLE IF EXISTS players CASCADE;
DROP TABLE IF EXISTS countries CASCADE;

CREATE TABLE countries (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    code        VARCHAR(20)  NOT NULL,
    ovr_attack  INT NOT NULL,
    ovr_defense INT NOT NULL
);

CREATE TABLE players (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    country_id  INT REFERENCES countries(id),
    position    VARCHAR(5)   NOT NULL CHECK (position IN ('GK','DEF','MID','FWD')),
    overall     INT          NOT NULL CHECK (overall BETWEEN 0 AND 99),
    is_starter  BOOLEAN      NOT NULL DEFAULT true,
    photo_path  VARCHAR(255)
);

CREATE TABLE matches (
    id          SERIAL PRIMARY KEY,
    home_id     INT REFERENCES countries(id),
    away_id     INT REFERENCES countries(id),
    home_score  INT NOT NULL,
    away_score  INT NOT NULL,
    played_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
