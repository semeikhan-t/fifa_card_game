CREATE TABLE IF NOT EXISTS countries (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    ovr_attack INT DEFAULT 70,
    ovr_defense INT DEFAULT 70
);

CREATE TABLE IF NOT EXISTS players (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    country_id INT REFERENCES countries(id) ON DELETE CASCADE,
    position VARCHAR(10) NOT NULL, -- GK, DEF, MID, FWD
    overall INT NOT NULL,
    is_starter BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS matches (
    id SERIAL PRIMARY KEY,
    home_id INT REFERENCES countries(id),
    away_id INT REFERENCES countries(id),
    home_score INT NOT NULL,
    away_score INT NOT NULL,
    played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
