---------------------------------------------------------------------------------
-----------------------Question 1.2----------------------------------------------
-----Affiche toutes les tables											---------
---------------------------------------------------------------------------------
SHOW DATABASES;

---------------------------------------------------------------------------------
-----------------------Question 1.3----------------------------------------------
-----Description d'une table											---------
---------------------------------------------------------------------------------
DESCRIBE T_Articles;
---------------------------------------------------------------------------------
-----------------------Question 1.4----------------------------------------------
-----Description d'une table											---------
---------------------------------------------------------------------------------
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'HDD','Corsair',550 );
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Mechanical keyboard','Blackmagic Design',615 );
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'McBouc','Apple',500 );
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'IphoneVR','Apple',25000 );
select *from t_articles;
---------------------------------------------------------------------------------
-----------------------Question 1.5----------------------------------------------
-----Modifier un article avant de vérifier si c'est pris en compte      ---------
---------------------------------------------------------------------------------
update t_articles set UnitaryPrice = 180 where IdArticle = 13;
select *from t_articles;
-----------------------Question 1.6----------------------------------------------
-----Supprimer un article puis vérifier                                 ---------
---------------------------------------------------------------------------------
delete from t_articles where IdArticle = 13;
select *from t_articles;
-----------------------Question 1.7----------------------------------------------
-----Sélectionner tous les articles dont le prix est supérieur é 100    ---------
---------------------------------------------------------------------------------
select from t_articles where UnitaryPrice > 100;
-----------------------Question 1.8----------------------------------------------
-----Sélectionner tous les articles dont le prix est commpris entre 50 et 150----
---------------------------------------------------------------------------------
select* from t_articles where UnitaryPrice between 50 and 150;
-----------------------Question 1.9----------------------------------------------
-----Afficher les articles dans l'ordre croissant des prix              ---------
---------------------------------------------------------------------------------
select *from t_articles order by UnitaryPrice;
-----------------------Question 1.10----------------------------------------------
-----Afficher uniquement la description des articles                     ---------
----------------------------------------------------------------------------------
select disctint Description from t_articles;
-----------------------Question 1.11----------------------------------------------
-----Choisissez une requéte particulérement intéressante é prétenter aux autres---
----------------------------------------------------------------------------------
-----Affiche le Prix moyen des articles de chaque marque lorqu'il est est inférieur à 600  
----------------------------------------------------------------------------------
select Brand, avg(UnitaryPrice) from t_articles group by Brand HAVING avg(UnitaryPrice)<=600;
-----------------------Question 1.12----------------------------------------------
-----Ajouter la table des catégories é votre base de données et insérez-en -------
----------------------------------------------------------------------------------
CREATE TABLE t_Categories (IdCategory INT(4) PRIMARY KEY AUTO_INCREMENT,CatName VARCHAR(30) NOT NULL, Desciption VARCHAR(100) NOT NULL);
ALTER TABLE t_articles ADD COLUMN IdCategory INT(4);
ALTER TABLE t_articles ADD FOREIGN KEY(IdCategory) REFERENCES t_Categories(IdCategory);
INSERT INTO t_Categories ( CatName, Description ) VALUES ( 'PC','ordinateur portable ou pas' );
INSERT INTO t_Categories ( CatName, Description ) VALUES ( 'Smartphone','téléphone du future ecran holographique + stargate' );
INSERT INTO t_Categories ( CatName, Description ) VALUES ( 'Materiel info','Tout materiel informatique physique en lien avec un ordinateur');
INSERT INTO t_Categories ( CatName, Description ) VALUES ( 'Logiciel','Logiciel ou tout autre programme interne d un ordinateur');
-----------------------Question 1.13----------------------------------------------
-----Renseignement du champs catégory de chaque article(en fonction des insert Q1.4
----- Puis trouver LA requéte qui affiche les lignes: clés>10 sort by UnitaryPrice                
----------------------------------------------------------------------------------
----FIELD() retourne la position du premier argument dans la liste des arguments suivants													
----ELT() retourne une chaîne passée en argument à partir d’un index spécifié-----
----------------------------------------------------------------------------------
UPDATE t_articles SET IdCategory = ELT(FIELD(IdArticle,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16),3,3,4,3,3,1,3,3,3,3,3,3,3,3,1,2) WHERE IdArticle IN(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);
select * from t_articles where IdArticle >10 ORDER BY UnitaryPrice;
