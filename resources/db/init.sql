CREATE TABLE IF NOT EXISTS countries (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    ovr_attack INT NOT NULL,
    ovr_defense INT NOT NULL
);

CREATE TABLE IF NOT EXISTS players (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    country_id INT REFERENCES countries(id),
    position VARCHAR(10) NOT NULL,
    overall INT NOT NULL,
    is_starter BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS matches (
    id SERIAL PRIMARY KEY,
    home_id INT REFERENCES countries(id),
    away_id INT REFERENCES countries(id),
    home_score INT NOT NULL,
    away_score INT NOT NULL,
    played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
