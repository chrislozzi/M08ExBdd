-- ------------------------------------------------------------------------------
-- - Reconstruction de la base de données                                     ---
-- ------------------------------------------------------------------------------
DROP DATABASE IF EXISTS Shop;
CREATE DATABASE Shop;
USE Shop;
---------------------------------------------------------------------------------
-----------------------Exercice 1 ----------------------------------------------
-----------------------Question 1.1 ---------------------------------------------
-- ------------------------------------------------------------------------------
-- - Construction de la tables des articles en vente                          ---
-- ------------------------------------------------------------------------------
CREATE TABLE T_Articles (
	IdArticle			int(4)		PRIMARY KEY AUTO_INCREMENT,
	Description			varchar(30)	NOT NULL,
	Brand				varchar(30)	NOT NULL,
	UnitaryPrice		float(8)	NOT NULL DEFAULT 0,
	CartQuantity		int(4)
) ENGINE = InnoDB;

INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Souris'     ,	'Logitoch', 65 );
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Clavier'    ,	'Microhard', 49.5 );
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Systeme d''exploitation',	'Fenetres Vistouille',	150 );
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Tapis souris', 'Chapeau Bleu',5 );
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Cle USB 8 To', 'Syno', 8 );
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Laptop'      , 	'PH',	1199 );
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'CD x 500'    , 'CETME', 250 );
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'DVD-R x 100' , 'CETME', 99 );
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'DVD+R x 100' , 'CETME', 105 );
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Batterie Laptop', 'PH',	80 );
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Casque Audio', 'Syno',	105 );
INSERT INTO T_Articles ( Description, Brand  ) VALUES ( 'WebCam'      , 	'Logitoch' );

SELECT * FROM T_Articles;

-----------------------Exercice 6   ---------------------------------------------
-- ------------------------------------------------------------------------------
-- - Construction de la tables des utilisateurs                               ---
-- ------------------------------------------------------------------------------
CREATE TABLE T_Users (
IdUser int(4) PRIMARY KEY AUTO_INCREMENT,
Login varchar(20) NOT NULL UNIQUE,
Password varchar(20) NOT NULL
) ENGINE = InnoDB;


-----------------------Exercice 7   ---------------------------------------------
-- ------------------------------------------------------------------------------
---- Nouveaux utilisateurs en base, garantie sans doublon pour le champs Login---
---- grâce à la contrainte (ici d'intégrité) UNIQUE, qui garantit que les valeurs
---- de cette colonne seront uniques										-----
-- ------------------------------------------------------------------------------
INSERT INTO T_Users ( Login, Password ) VALUES ( 'Robert'  , '123' );
INSERT INTO T_Users ( Login, Password ) VALUES ( 'Nasser'  , 'Wa7edJoujTelata' );
INSERT INTO T_Users ( Login, Password ) VALUES ( 'Momar'   , 'BennNaarNett' );
INSERT INTO T_Users ( Login, Password ) VALUES ( 'Jean-Claude'   , 'Plantedbaton' );
INSERT INTO T_Users ( Login, Password ) VALUES ( 'Christophe'   , 'admin' );

SELECT * FROM T_Users;
