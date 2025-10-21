CREATE TABLE service (
                         nom VARCHAR(64) PRIMARY KEY,
                         description VARCHAR(255)
);

CREATE TABLE localisation (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              nom VARCHAR(64) NOT NULL,
                              service_nom VARCHAR(64) NOT NULL,
                              FOREIGN KEY (service_nom) REFERENCES service(nom)
);

CREATE TABLE admin (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       nom VARCHAR(100) NOT NULL,
                       prenom VARCHAR(100) NOT NULL
);

CREATE TABLE agent (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       nom VARCHAR(100) NOT NULL,
                       prenom VARCHAR(100) NOT NULL,
                       specialite VARCHAR(100)
);

CREATE TABLE file_attente (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              service_id VARCHAR(64),
                              admin_id BIGINT,
                              FOREIGN KEY (service_id) REFERENCES service(nom),
                              FOREIGN KEY (admin_id) REFERENCES admin(id)
);

CREATE TABLE client (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nom VARCHAR(100) NOT NULL,
                        prenom VARCHAR(100) NOT NULL,
                        adresse VARCHAR(200),
                        file_attente_id BIGINT,
                        FOREIGN KEY (file_attente_id) REFERENCES file_attente(id) ON DELETE SET NULL
);

CREATE TABLE ticket (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        numero VARCHAR(20),
                        positionDansFile INT,
                        nombreDevant INT,
                        status VARCHAR(30),
                        localisation VARCHAR(100),
                        agentId BIGINT,
                        isPrevClient BOOLEAN DEFAULT FALSE,
                        client_id BIGINT,
                        nomService VARCHAR(64),
                        FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE SET NULL,
                        FOREIGN KEY (nomService) REFERENCES service(nom) ON DELETE SET NULL
);
