-- Countries
INSERT INTO countries (name, ovr_attack, ovr_defense) VALUES ('Kazakhstan', 65, 68);
INSERT INTO countries (name, ovr_attack, ovr_defense) VALUES ('Brazil', 92, 88);
INSERT INTO countries (name, ovr_attack, ovr_defense) VALUES ('France', 90, 89);
INSERT INTO countries (name, ovr_attack, ovr_defense) VALUES ('Argentina', 91, 87);
INSERT INTO countries (name, ovr_attack, ovr_defense) VALUES ('England', 89, 90);
INSERT INTO countries (name, ovr_attack, ovr_defense) VALUES ('Germany', 88, 88);
INSERT INTO countries (name, ovr_attack, ovr_defense) VALUES ('Spain', 87, 89);
INSERT INTO countries (name, ovr_attack, ovr_defense) VALUES ('Portugal', 89, 86);
INSERT INTO countries (name, ovr_attack, ovr_defense) VALUES ('Netherlands', 86, 88);
INSERT INTO countries (name, ovr_attack, ovr_defense) VALUES ('Italy', 85, 90);
INSERT INTO countries (name, ovr_attack, ovr_defense) VALUES ('Japan', 82, 80);
INSERT INTO countries (name, ovr_attack, ovr_defense) VALUES ('USA', 80, 81);
INSERT INTO countries (name, ovr_attack, ovr_defense) VALUES ('Morocco', 83, 84);
INSERT INTO countries (name, ovr_attack, ovr_defense) VALUES ('Croatia', 84, 85);
INSERT INTO countries (name, ovr_attack, ovr_defense) VALUES ('Uruguay', 85, 86);
INSERT INTO countries (name, ovr_attack, ovr_defense) VALUES ('Belgium', 86, 84);

-- Players for Kazakhstan (Country ID 1)
INSERT INTO players (name, country_id, position, overall, is_starter) VALUES ('Igor Shatskiy', 1, 'GK', 72, TRUE);
INSERT INTO players (name, country_id, position, overall, is_starter) VALUES ('Nuraly Alip', 1, 'DEF', 75, TRUE);
INSERT INTO players (name, country_id, position, overall, is_starter) VALUES ('Aleksandr Marochkin', 1, 'DEF', 70, TRUE);
INSERT INTO players (name, country_id, position, overall, is_starter) VALUES ('Yan Vorogovskiy', 1, 'DEF', 71, TRUE);
INSERT INTO players (name, country_id, position, overall, is_starter) VALUES ('Marat Bystrov', 1, 'DEF', 69, TRUE);
INSERT INTO players (name, country_id, position, overall, is_starter) VALUES ('Askhat Tagybergen', 1, 'MID', 74, TRUE);
INSERT INTO players (name, country_id, position, overall, is_starter) VALUES ('Bakhtiyar Zaynutdinov', 1, 'MID', 78, TRUE);
INSERT INTO players (name, country_id, position, overall, is_starter) VALUES ('Ramazan Orazov', 1, 'MID', 71, TRUE);
INSERT INTO players (name, country_id, position, overall, is_starter) VALUES ('Abat Aimbetov', 1, 'FWD', 72, TRUE);
INSERT INTO players (name, country_id, position, overall, is_starter) VALUES ('Maksim Samorodov', 1, 'FWD', 70, TRUE);
INSERT INTO players (name, country_id, position, overall, is_starter) VALUES ('Elkhan Astanov', 1, 'FWD', 68, TRUE);

-- Players for Argentina (Country ID 4)
INSERT INTO players (name, country_id, position, overall, is_starter) VALUES ('Emi Martinez', 4, 'GK', 87, TRUE);
INSERT INTO players (name, country_id, position, overall, is_starter) VALUES ('Cuti Romero', 4, 'DEF', 88, TRUE);
INSERT INTO players (name, country_id, position, overall, is_starter) VALUES ('Lisandro Martinez', 4, 'DEF', 86, TRUE);
INSERT INTO players (name, country_id, position, overall, is_starter) VALUES ('Enzo Fernandez', 4, 'MID', 85, TRUE);
INSERT INTO players (name, country_id, position, overall, is_starter) VALUES ('Alexis Mac Allister', 4, 'MID', 86, TRUE);
INSERT INTO players (name, country_id, position, overall, is_starter) VALUES ('Lionel Messi', 4, 'FWD', 91, TRUE);
INSERT INTO players (name, country_id, position, overall, is_starter) VALUES ('Julian Alvarez', 4, 'FWD', 88, TRUE);
INSERT INTO players (name, country_id, position, overall, is_starter) VALUES ('Lautaro Martinez', 4, 'FWD', 89, TRUE);
-- ... (Simplified for seed)
