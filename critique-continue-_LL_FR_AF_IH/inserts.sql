INSERT INTO PUBLIC.UTILISATEUR (ID, DATE_DE_NAISSANCE, IDENTIFIANT, NOM, TYPE) VALUES (1, '2023-10-03', 'felix', 'felix', 'Expert');
INSERT INTO PUBLIC.UTILISATEUR (ID, DATE_DE_NAISSANCE, IDENTIFIANT, NOM, TYPE) VALUES (2, '2023-10-03', 'michel', 'MICHEEEEEEEEL', 'Influencer');
INSERT INTO PUBLIC.PRODUIT (ID, COTE, DATE_DE_SORTIE, DESCRIPTION, IMAGE, NOM) VALUES (1, 5, '2023-09-09', null, null, 'COD');
INSERT INTO PUBLIC.PRODUIT (ID, COTE, DATE_DE_SORTIE, DESCRIPTION, IMAGE, NOM) VALUES (2, 5, '2023-08-08', null, null, 'Minecraft');
INSERT INTO PUBLIC.PRODUIT (ID, COTE, DATE_DE_SORTIE, DESCRIPTION, IMAGE, NOM) VALUES (3, 5, '2023-07-07', null, null, 'Fortnite');
INSERT INTO PUBLIC.PRODUIT (ID, COTE, DATE_DE_SORTIE, DESCRIPTION, IMAGE, NOM) VALUES (4, 5, '2023-06-06', null, null, 'Valorant');

INSERT INTO PUBLIC.CRITIQUE_LIEN_PRODUIT (ID, ECART, EST_NEUTRE, CRITIQUE_ID, PRODUIT_ID, ORDRE_LISTE) VALUES (1, 'GRAND', false, 1, 3, 1);
INSERT INTO PUBLIC.CRITIQUE_LIEN_PRODUIT (ID, ECART, EST_NEUTRE, CRITIQUE_ID, PRODUIT_ID, ORDRE_LISTE) VALUES (2, 'GRAND', false, 1, 4, 2);
INSERT INTO PUBLIC.CRITIQUE_LIEN_PRODUIT (ID, ECART, EST_NEUTRE, CRITIQUE_ID, PRODUIT_ID, ORDRE_LISTE) VALUES (3, 'GRAND', true, 1, 2, 3);
INSERT INTO PUBLIC.CRITIQUE_LIEN_PRODUIT (ID, ECART, EST_NEUTRE, CRITIQUE_ID, PRODUIT_ID, ORDRE_LISTE) VALUES (4, 'GRAND', false, 1, 1, 4);
INSERT INTO PUBLIC.CRITIQUE_LIEN_PRODUIT (ID, ECART, EST_NEUTRE, CRITIQUE_ID, PRODUIT_ID, ORDRE_LISTE) VALUES (52, 'GRAND', false, 2, 2, 1);
INSERT INTO PUBLIC.CRITIQUE_LIEN_PRODUIT (ID, ECART, EST_NEUTRE, CRITIQUE_ID, PRODUIT_ID, ORDRE_LISTE) VALUES (53, 'GRAND', true, 2, 3, 2);
INSERT INTO PUBLIC.CRITIQUE_LIEN_PRODUIT (ID, ECART, EST_NEUTRE, CRITIQUE_ID, PRODUIT_ID, ORDRE_LISTE) VALUES (54, 'GRAND', false, 2, 4, 3);
INSERT INTO PUBLIC.CRITIQUE_LIEN_PRODUIT (ID, ECART, EST_NEUTRE, CRITIQUE_ID, PRODUIT_ID, ORDRE_LISTE) VALUES (102, 'FAIBLE', false, 52, 1, 1);
INSERT INTO PUBLIC.CRITIQUE_LIEN_PRODUIT (ID, ECART, EST_NEUTRE, CRITIQUE_ID, PRODUIT_ID, ORDRE_LISTE) VALUES (103, 'FAIBLE', true, 52, 3, 2);
INSERT INTO PUBLIC.CRITIQUE_LIEN_PRODUIT (ID, ECART, EST_NEUTRE, CRITIQUE_ID, PRODUIT_ID, ORDRE_LISTE) VALUES (152, 'GRAND', false, 102, 3, 1);
INSERT INTO PUBLIC.CRITIQUE_LIEN_PRODUIT (ID, ECART, EST_NEUTRE, CRITIQUE_ID, PRODUIT_ID, ORDRE_LISTE) VALUES (153, 'GRAND', true, 102, 4, 2);

INSERT INTO PUBLIC.CRITIQUE (ID, DATE_CRITIQUE, UTILISATEUR_ID) VALUES (1, '2023-10-04', 1);
INSERT INTO PUBLIC.CRITIQUE (ID, DATE_CRITIQUE, UTILISATEUR_ID) VALUES (2, '2023-10-11', 1);
INSERT INTO PUBLIC.CRITIQUE (ID, DATE_CRITIQUE, UTILISATEUR_ID) VALUES (52, '2023-10-11', 1);
INSERT INTO PUBLIC.CRITIQUE (ID, DATE_CRITIQUE, UTILISATEUR_ID) VALUES (102, '2023-10-19', 1);

insert into PUBLIC.DEVELOPPEUR (ID, INFORMATION, NOTE, TYPE_PARTICIPANT) values(1,'Antoine','100','etudiant');
insert into PUBLIC.DEVELOPPEUR (ID, INFORMATION, NOTE, TYPE_PARTICIPANT) values(2,'Felix','100','etudiant');
insert into PUBLIC.DEVELOPPEUR (ID, INFORMATION, NOTE, TYPE_PARTICIPANT) values(3,'Lawrence','100','etudiant');
insert into PUBLIC.DEVELOPPEUR (ID, INFORMATION, NOTE, TYPE_PARTICIPANT) values(4,'Ibrahim','100','etudiant');
insert into PUBLIC.DEVELOPPEUR (ID, INFORMATION, NOTE, TYPE_PARTICIPANT) values(5,'Martin','100','professeur');