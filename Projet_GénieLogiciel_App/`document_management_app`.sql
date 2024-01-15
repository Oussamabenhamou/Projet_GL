CREATE USER IF NOT EXISTS 'admin'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON Document_Management_App.* TO 'admin'@'%';
FLUSH PRIVILEGES;

DROP DATABASE IF EXISTS Document_Management_App;
CREATE DATABASE IF NOT EXISTS Document_Management_App;
USE Document_Management_App;

CREATE TABLE `administrateurs` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Nom_Complet` varchar(100) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Mot_De_Passe` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);


CREATE TABLE `etudiants` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Numero_Apogee` int DEFAULT NULL,
  `CIN` varchar(10) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Nom` varchar(50) DEFAULT NULL,
  `Prenom` varchar(50) DEFAULT NULL,
  `Filiere` varchar(25) DEFAULT NULL,
  `Date_naissance` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `demandes_etudiants` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ID_Etudiant` int DEFAULT NULL,
  `Nom_Document` varchar(100) DEFAULT NULL,
  `Statut` tinyint(1) DEFAULT '0',
  `Date` date DEFAULT (curdate()),
  `Annee_Etude` int DEFAULT NULL,
  `Code_Demande` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Code_Demande` (`Code_Demande`),
  KEY `ID_Etudiant` (`ID_Etudiant`),
  CONSTRAINT `demandes_etudiants_ibfk_1` FOREIGN KEY (`ID_Etudiant`) REFERENCES `etudiants` (`ID`)
);
CREATE TABLE `releves_de_notes` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Matiere` varchar(50) DEFAULT NULL,
  `statut` tinyint(1) DEFAULT NULL,
  `Note` float DEFAULT NULL,
  `ID_Etudiant` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_Etudiant` (`ID_Etudiant`),
  CONSTRAINT `releves_de_notes_ibfk_1` FOREIGN KEY (`ID_Etudiant`) REFERENCES `etudiants` (`ID`)
);



 CREATE TABLE Internship (
     ID INT AUTO_INCREMENT PRIMARY KEY,
     Name_Societe VARCHAR(255),
     Email VARCHAR(255),
     Number_Phone VARCHAR(15),
     Address VARCHAR(255),
     Full_Name VARCHAR(255),
     Encadrant_Fullname VARCHAR(255),
     Name_Eta VARCHAR(255),
     Beg_Date DATE,
     Final_Date DATE,
     Theme_Name VARCHAR(255),
     id_dem int ,
    FOREIGN KEY (id_dem) REFERENCES Demandes_Etudiants(ID)
 );

CREATE TABLE `reclamation` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Code_Reference` varchar(50) DEFAULT NULL,
  `Id_Etudiant` int DEFAULT NULL,
  `Message` text,
  `Date` date DEFAULT (curdate()),
  PRIMARY KEY (`ID`),
  KEY `Id_Etudiant` (`Id_Etudiant`),
  CONSTRAINT `reclamation_ibfk_1` FOREIGN KEY (`Id_Etudiant`) REFERENCES `etudiants` (`ID`)
);

-- Insert into administrateurs table
INSERT INTO `administrateurs` (`Nom_Complet`, `Email`, `Mot_De_Passe`)
VALUES ('Khalid Hadi', 'khalid.hadi@example.com', 'password123');


-- Insert into etudiants table
INSERT INTO etudiants (Numero_Apogee, CIN, Email, Nom, Prenom, Filiere, Date_naissance) VALUES (22052799, 'OD63216', 'oussama.elfahsi1@etu.uae.ac.ma', 'Oussama', 'El fahsi', 'GI2', '2002-06-18');
INSERT INTO etudiants (Numero_Apogee, CIN, Email, Nom, Prenom, Filiere, Date_naissance) VALUES (22052802, 'kb219552', 'oussama.benhammou@etu.uae.ac.ma', 'Oussama', 'Ben hammou', 'GI2', '2002-07-07');
INSERT INTO etudiants (Numero_Apogee, CIN, Email, Nom, Prenom, Filiere, Date_naissance) VALUES (22152921, 'kb219511', 'ibrahim.jabrane@etu.uae.ac.ma', 'ibrahim', 'ben jabrane', 'GI2', '2001-07-09');
INSERT INTO etudiants (Numero_Apogee, CIN, Email, Nom, Prenom, Filiere, Date_naissance) VALUES (19112821, 'OD21951', 'salman.benomar@etu.uae.ac.ma', 'salman', 'benomar', 'GR2', '2002-02-09');
INSERT INTO etudiants (Numero_Apogee, CIN, Email, Nom, Prenom, Filiere, Date_naissance) VALUES (221528211, 'kb219511', 'ouail.madani@etu.uae.ac.ma', 'ouail', 'madani', 'GSTR2', '2002-02-19');


-- Insert into releves_de_notes table
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Théorie de Graphs', 1, 14, 1);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Mathematique', 0, 11, 1);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Réseau Informatique', 1, 16, 1);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Technique de Communication', 1, 13.5, 1);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Managemnet', 1, 12, 1);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Base de données', 1, 17.5, 1);
-- Insert into releves_de_notes table
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Théorie de Graphs', 1, 16, 2);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Mathematique', 0, 10, 2);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Réseau Informatique', 1, 16, 2);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Technique de Communication', 1, 15, 2);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Managemnet', 1, 13, 2);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Base de données', 1, 17, 2);
-- Insert into releves_de_notes table
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Théorie de Graphs', 1, 17, 3);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Mathematique', 1, 13, 3);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Réseau Informatique', 0, 9, 3);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Technique de Communication', 0, 8.5, 3);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Managemnet', 0, 11.5, 3);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Base de données', 0, 8, 3);
-- Insert into releves_de_notes table
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Théorie de Graphs', 1, 17, 4);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Mathematique', 1, 13, 4);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Réseau Informatique', 1, 29, 4);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Technique de Communication', 1, 18.5, 4);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Managemnet', 1, 14.5, 4);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Base de données', 1, 13, 4);
-- Insert into releves_de_notes table
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Vision Artificielle', 1, 17, 5);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Electronique Numerique', 1, 13, 5);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Réseau Informatique 2', 1, 16, 5);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Technique de Communication 2', 1, 12.5, 5);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Managemnet 2', 0, 11.5, 5);
INSERT INTO releves_de_notes (Matiere, Statut, Note, ID_Etudiant) VALUES ('Cloud computing', 1, 18, 5);
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (1, 'certificat de scolarité',0, '2023-06-25', 'REQ202130413');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (1, 'Relevés de Notes',0, '2023-07-15', 'REQ20223001');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (1, 'Attestation de réussite',0, '2023-08-10', 'REQ12023302');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (1, 'Stage',0, '2023-06-18', 'REQ202330241');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (2, 'certificat de scolarité',0, '2023-06-25', 'REQ202355005');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (2, 'Relevés de Notes',0, '2023-07-15', 'REQ202613006');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (2, 'Attestation de réussite',0, '2023-08-10', 'REQ20230707');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (2, 'Stage',0, '2023-06-18', 'REQ20293008');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (3, 'certificat de scolarité',0, '2023-07-25', 'REQ20230409');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (3, 'Relevés de Notes',0, '2023-07-23', 'REQ20253010');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (3, 'Attestation de réussite',0, '2023-08-16', 'REQ82023011');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (3, 'Stage',0, '2023-07-18', 'RE4Q2202302');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (4, 'certificat de scolarité',0, '2023-04-25', 'REQ20293013');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (4, 'Relevés de Notes',0, '2023-07-25', 'REQ20235014');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (4, 'Attestation de réussite',0, '2023-09-16', 'R5EQ2023015');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (4, 'Stage',0, '2023-07-18', 'REQ20230126');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (5, 'certificat de scolarité',0, '2023-07-01', 'REQ20235017');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (5, 'Relevés de Notes',0, '2023-08-05', 'REQ2023015l8');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (5, 'Attestation de réussite',0, '2023-07-10', 'REQ62023019');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (5, 'Stage',0, '2023-07-18', 'REQ20230260');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (1, 'certificat de scolarité',0, '2023-06-25', 'REQ20239003');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (1, 'Relevés de Notes',0, '2023-07-15', 'REQ20230402');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (1, 'Attestation de réussite',0, '2023-08-10', 'REQ20293022');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (1, 'Stage',0, '2023-06-18', 'REQ20238042');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (2, 'certificat de scolarité',0, '2023-06-25', 'REQ20243005');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (2, 'Relevés de Notes',0, '2023-07-15', 'REQ202293006');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (2, 'Attestation de réussite',0, '2023-08-10', 'REQ72023007');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (2, 'Stage',0, '2023-06-18', 'REQ29023008');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (3, 'certificat de scolarité',0, '2023-07-25', 'REQ20243009');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (3, 'Relevés de Notes',0, '2023-07-23', 'REQ20239010');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (3, 'Attestation de réussite',0, '2023-08-16', 'REQ20423011');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (3, 'Stage',0, '2023-07-18', 'REQ36202302');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (4, 'certificat de scolarité',0, '2023-04-25', 'REQ25023013');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (4, 'Relevés de Notes',0, '2023-07-25', 'REQ20230814');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (4, 'Attestation de réussite',0, '2023-09-16', 'REQ20243015');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (4, 'Stage',0, '2023-07-18', 'REQ82023016');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (5, 'certificat de scolarité',0, '2023-07-01', 'REQ20923017');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (5, 'Relevés de Notes',0, '2023-08-05', 'REQ2023015mb8');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (5, 'Attestation de réussite',0, '2023-07-10', 'REQ20253019');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (5, 'Stage',0, '2023-07-18', 'REQ20236020');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (1, 'certificat de scolarité',0, '2023-06-25', 'REQ20723013');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (1, 'Relevés de Notes',0, '2023-07-15', 'REQ20263003');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (1, 'Attestation de réussite',0, '2023-08-10', 'REQ24023032');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (1, 'Stage',0, '2023-06-18', 'REQ20623043');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (2, 'certificat de scolarité',0, '2023-06-25', 'REQ20723005');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (2, 'Relevés de Notes',0, '2023-07-15', 'REQ202393006');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (2, 'Attestation de réussite',0, '2023-08-10', 'R7EQ2023007');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (2, 'Stage',0, '2023-06-18', 'REQ202305908');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (3, 'certificat de scolarité',0, '2023-07-25', 'REQ20235009');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (3, 'Relevés de Notes',0, '2023-07-23', 'REQ20230810');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (3, 'Attestation de réussite',0, '2023-08-16', 'REQ52023011');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (3, 'Stage',0, '2023-07-18', 'REQ42082302');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (4, 'certificat de scolarité',0, '2023-04-25', 'REQ20823013');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (4, 'Relevés de Notes',0, '2023-07-25', 'REQ20263014');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (4, 'Attestation de réussite',0, '2023-09-16', 'REQ20283015');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (4, 'Stage',0, '2023-07-18', 'REQ20243016');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (5, 'certificat de scolarité',0, '2023-07-01', 'REQ52023017');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (5, 'Relevés de Notes',0, '2023-08-05', 'REQ20263018');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (5, 'Attestation de réussite',0, '2023-07-10', 'REQ207823019');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (5, 'Stage',0, '2023-07-18', 'REQ20230250');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (1, 'certificat de scolarité',0, '2023-06-25', 'REQ72023023');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (1, 'Relevés de Notes',0, '2023-07-15', 'REQ20273041');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (1, 'Attestation de réussite',0, '2023-08-10', 'REQ20293042');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (1, 'Stage',0, '2023-06-18', 'REQ20230464');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (2, 'certificat de scolarité',0, '2023-06-25', 'REQ20923075');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (2, 'Relevés de Notes',0, '2023-07-15', 'REQ202453006');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (2, 'Attestation de réussite',0, '2023-08-10', 'REQ20823007');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (2, 'Stage',0, '2023-06-18', 'REQ27023008');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (3, 'certificat de scolarité',0, '2023-07-25', 'REQ20239009');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (3, 'Relevés de Notes',0, '2023-07-23', 'REQ20723010');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (3, 'Attestation de réussite',0, '2023-08-16', 'REQ20293011');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (3, 'Stage',0, '2023-07-18', 'REQ52072302');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (4, 'certificat de scolarité',0, '2023-04-25', 'REQ8012023013');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (4, 'Relevés de Notes',0, '2023-07-25', 'REQ20230149');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (4, 'Attestation de réussite',0, '2023-09-16', 'REQ289023015');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (4, 'Stage',0, '2023-07-18', 'REQ202301586');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (5, 'certificat de scolarité',0, '2023-07-01', 'REQ208423017');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (5, 'Relevés de Notes',0, '2023-08-05', 'REQ69sd2023018');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (5, 'Attestation de réussite',0, '2023-07-10', 'REQ2023dv019');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (5, 'Stage',0, '2023-07-18', 'REQ20vd23020');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (1, 'certificat de scolarité',0, '2023-06-25', 'REQ202303vf3');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (1, 'Relevés de Notes',0, '2023-07-15', 'REQ202cs3051');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (1, 'Attestation de réussite',0, '2023-08-10', 'REQ20230zc52');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (1, 'Stage',0, '2023-06-18', 'REQ2cd023045');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (2, 'certificat de scolarité',0, '2023-06-25', 'REQ202c4005');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (2, 'Relevés de Notes',0, '2023-07-15', 'REQ202xz53006');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (2, 'Attestation de réussite',0, '2023-08-10', 'REQ98z2023007');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (2, 'Stage',0, '2023-06-18', 'REQ209z7x23008');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (3, 'certificat de scolarité',0, '2023-07-25', 'REQ20xz423009');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (3, 'Relevés de Notes',0, '2023-07-23', 'REQ202zx53010');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (3, 'Attestation de réussite',0, '2023-08-16', 'REQ20230xzx811');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (3, 'Stage',0, '2023-07-18', 'REQ6202xz5302');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (4, 'certificat de scolarité',0, '2023-04-25', 'REQ2023xz7013');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (4, 'Relevés de Notes',0, '2023-07-25', 'REQ202xz93014');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (4, 'Attestation de réussite',0, '2023-09-16', 'REQ2023z7015');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (4, 'Stage',0, '2023-07-18', 'REQ202xs93016');
-- Insert into demandes_etudiants table
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut,  Date, Code_Demande) VALUES (5, 'certificat de scolarité',0, '2023-07-01', 'REQ202x93017');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (5, 'Relevés de Notes',0, '2023-08-05', 'REQ20230cx818');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (5, 'Attestation de réussite',0, '2023-07-10', 'REQ202301cx99');
INSERT INTO demandes_etudiants (ID_Etudiant, Nom_Document, Statut, Date, Code_Demande) VALUES (5, 'Stage',0, '2023-07-18', 'REQ202302cxc70');


-- Insert into internship table
INSERT INTO internship (Name_Societe, Email, Number_Phone, Address, Full_Name, Encadrant_Fullname, Name_Eta, Beg_Date, Final_Date, Theme_Name, id_dem) 
VALUES ('Amendis', 'Amendis@gmail.com', '+212527189901', '116 RUE IBN ROMI AV MOATASSIM', 'Oussama el fahsi', 'YASSER MESMOUDI', 'ENSA TETOUAN', '2024-07-01', '2024-08-31', 'Tech Projet', 4);
-- Insert into internship table
INSERT INTO internship (Name_Societe, Email, Number_Phone, Address, Full_Name, Encadrant_Fullname, Name_Eta, Beg_Date, Final_Date, Theme_Name, id_dem) 
VALUES ('Renault', 'renault@gmail.com', '+212577678857', '155 zone tanger asillah', 'Oussama Ben hammou', 'YASSER Abtoy', 'ENSA TETOUAN', '2024-06-01', '2024-09-01', 'Digitalisation', 8);
-- Insert into internship table
INSERT INTO internship (Name_Societe, Email, Number_Phone, Address, Full_Name, Encadrant_Fullname, Name_Eta, Beg_Date, Final_Date, Theme_Name, id_dem) 
VALUES ('dasd', 'dsadat@gmail.com', '+212527678899', '135 zone hajeb asillah', 'Ibrahim Jabrane ', 'YASSER Abtoy', 'ENSA TETOUAN', '2024-07-01', '2024-09-01', 'Securite', 12);
-- Insert into internship table
INSERT INTO internship (Name_Societe, Email, Number_Phone, Address, Full_Name, Encadrant_Fullname, Name_Eta, Beg_Date, Final_Date, Theme_Name, id_dem) 
VALUES ('GOLD', 'GOLF@gmail.com', '+212527678899', '135 zone hajeb asillah', 'achraf ben ayyad ', 'YASSER Abtoy', 'ENSA TETOUAN', '2024-07-01', '2024-09-01', 'Securite', 16);
-- Insert into internship table
INSERT INTO internship (Name_Societe, Email, Number_Phone, Address, Full_Name, Encadrant_Fullname, Name_Eta, Beg_Date, Final_Date, Theme_Name, id_dem) 
VALUES ('BMW', 'BMW@gmail.com', '+212527642879', '135 zone hajeb asillah', 'Ouail Madani ', 'YASSER Abtoy', 'ENSA TETOUAN', '2024-07-01', '2024-09-01', 'management', 20);


INSERT INTO reclamation (Code_Reference, Id_Etudiant, Message, Date)
VALUES ('REF20005', 1, 'Je tiens à exprimer ma préoccupation concernant
 le retard dans la réception
  de mon attestation de réussite.
   Cela fait plus de 15 jours.
    Merci.', '2023-08-26');

INSERT INTO reclamation (Code_Reference, Id_Etudiant, Message, Date)
VALUES ('REF20004', 2, 
'Je tiens à exprimer ma préoccupation concernant le retard 
de l envoi de mon attestation de scolarite.
 Cela fait plus de 15 jours. Merci.',
  '2023-05-01');

INSERT INTO reclamation (Code_Reference, Id_Etudiant, Message, Date)
VALUES ('REF20003', 3, 
'Je tiens à exprimer ma préoccupation concernant le retard 
de l envoi de mon attestation de scolarite.
 Cela fait plus de 15 jours. Merci.',
  '2023-09-01');

INSERT INTO reclamation (Code_Reference, Id_Etudiant, Message, Date)
VALUES ('REF20002', 4, 
'Je tiens à exprimer ma préoccupation concernant le retard 
de l envoi de mon releves de notes .
 Cela fait plus de 15 jours. Merci.',
  '2023-09-01');
  
INSERT INTO reclamation (Code_Reference, Id_Etudiant, Message, Date)
VALUES ('REF20001', 5, 
'Je tiens à exprimer ma préoccupation concernant le retard 
de l envoi de mon attestation de scolarite.
 Cela fait plus de 15 jours. Merci.',
  '2023-09-01');
