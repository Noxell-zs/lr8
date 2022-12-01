DROP TABLE IF EXISTS coffee_machine;
CREATE TABLE coffee_machine (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    country TEXT,
    year INTEGER CHECK (year >= 1800 AND year <= 2023),
    reservoir INTEGER,
    description TEXT,
    bought BOOLEAN DEFAULT FALSE
);
INSERT INTO coffee_machine (name, country, year, reservoir, description) VALUES
    ('KRUPS AB1234', 'France', 2010, 500, 'Good coffee machine'),
    ('Cuisinart Brew', 'USA', 2015, 1000, 'Very good coffee machine'),
    ('GUFO Mini', 'Russia', 2020, 1500, 'The best coffee machine');