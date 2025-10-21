-- Services
INSERT INTO service (nom, description) VALUES
                                           ('Sonatel', 'Telecom provider'),
                                           ('Seneau', 'Water supplier'),
                                           ('Banque CBAO', 'Bank');

-- Locations
INSERT INTO localisation (nom, service_nom) VALUES
                                                ('Mermoz', 'Sonatel'),
                                                ('Rufisque', 'Sonatel'),
                                                ('Bargny', 'Sonatel'),
                                                ('Almadies', 'Sonatel'),
                                                ('Medina', 'Seneau'),
                                                ('Rufisque', 'Seneau'),
                                                ('Blaise Diagne', 'Banque CBAO'),
                                                ('Almadies', 'Banque CBAO'),
                                                ('Rufisque', 'Banque CBAO');

-- Admins
INSERT INTO admin (nom, prenom) VALUES
    ('Diop', 'Mamadou');

-- Agents
INSERT INTO agent (nom, prenom, specialite) VALUES
                                                ('Ndoye', 'Cheikh', 'Mobile services'),
                                                ('Fall', 'Awa', 'Water management');

-- File Attente (queues) (must reference existing service noms and admin IDs)
INSERT INTO file_attente (service_id, admin_id) VALUES
                                                    ('Sonatel', 1),
                                                    ('Seneau', 1),
                                                    ('Banque CBAO', 1);

INSERT INTO client (id, nom, prenom, adresse, file_attente_id) VALUES
                                                                  (1, 'Sow', 'Fatou', 'Dakar, Medina', 1),
                                                                  (2, 'Ba', 'Abdoulaye', 'Dakar, Almadies', 2),
                                                                  (3, 'Gueye', 'Astou', 'Rufisque', 3);

INSERT INTO ticket (numero, positionDansFile, nombreDevant, status, localisation, agentId, isPrevClient, client_id,
                    nomService) VALUES
                                    ('001', 1, 0, 'waiting', 'Rufisque', 1, false, 1, 'Sonatel'),
                                    ('002', 2, 1, 'waiting', 'Mermoz', 2, false, 2, 'Seneau'),
                                    ('003', 1, 0, 'serving', 'Medina', 3, false, 3, 'Banque CBAO');
