/*
SQLyog Community v13.3.1 (64 bit)
MySQL - 8.0.44 : Database - seminarski
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`seminarski` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `seminarski`;

/*Table structure for table `dijagnoza` */

DROP TABLE IF EXISTS `dijagnoza`;

CREATE TABLE `dijagnoza` (
  `id_dijagnoza` int unsigned NOT NULL AUTO_INCREMENT,
  `sifra` varchar(50) NOT NULL,
  `latinski_naziv` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `srpski_naziv` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id_dijagnoza`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `dijagnoza` */

insert  into `dijagnoza`(`id_dijagnoza`,`sifra`,`latinski_naziv`,`srpski_naziv`) values 
(6,'I10','Hypertensio arterialis','Есенцијална хипертензија'),
(7,'E11','Diabetes mellitus non-insulin dependent','Дијабетес тип 2'),
(8,'J20','Acute bronchitis','Акутни бронхитис'),
(9,'K21','Gastroesophageal reflux disease','Гастроезофагеална рефлуксна болест'),
(10,'M54.5','Dorsalgia','Бол у леђима'),
(16,'Z00.0','Status sanus','Без оболења');

/*Table structure for table `krvna_grupa` */

DROP TABLE IF EXISTS `krvna_grupa`;

CREATE TABLE `krvna_grupa` (
  `id_krvna_grupa` int unsigned NOT NULL AUTO_INCREMENT,
  `abo_tip` enum('A','B','AB','O') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rh_faktor` enum('+','-') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id_krvna_grupa`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `krvna_grupa` */

insert  into `krvna_grupa`(`id_krvna_grupa`,`abo_tip`,`rh_faktor`) values 
(1,'A','+'),
(5,'A','-'),
(6,'B','+'),
(7,'B','-'),
(8,'AB','+'),
(9,'AB','-'),
(10,'O','+'),
(11,'O','-');

/*Table structure for table `lekar` */

DROP TABLE IF EXISTS `lekar`;

CREATE TABLE `lekar` (
  `id_lekar` int unsigned NOT NULL AUTO_INCREMENT,
  `ime` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `prezime` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pol` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `datum_rodjenja` date DEFAULT NULL,
  `korisnicko_ime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `lozinka` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id_lekar`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lekar` */

insert  into `lekar`(`id_lekar`,`ime`,`prezime`,`pol`,`datum_rodjenja`,`korisnicko_ime`,`lozinka`) values 
(1,'Милош','Ивановић','MUSKI','2026-03-17','mi','mi'),
(2,'Предраг','Милић','ZENSKI','2026-04-14','p','p'),
(3,'Александар','Недељковић','MUSKI','2026-04-15','a','a'),
(4,'Милица','Илић','ZENSKI','2026-04-14','mil','mil'),
(5,'Ивана','Ракићевић','ZENSKI','2026-01-14','i','i');

/*Table structure for table `lekar_specijalizacija` */

DROP TABLE IF EXISTS `lekar_specijalizacija`;

CREATE TABLE `lekar_specijalizacija` (
  `id_lekar` int unsigned NOT NULL,
  `id_specijalizacija` int unsigned NOT NULL,
  `datum_sticanja` date NOT NULL,
  `institucija_sticanja` varchar(50) NOT NULL,
  `trajanje` float NOT NULL,
  PRIMARY KEY (`id_lekar`,`id_specijalizacija`),
  KEY `spoljni_specijalizacija_ls` (`id_specijalizacija`),
  CONSTRAINT `spoljni_lekar_ls` FOREIGN KEY (`id_lekar`) REFERENCES `lekar` (`id_lekar`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `spoljni_specijalizacija_ls` FOREIGN KEY (`id_specijalizacija`) REFERENCES `specijalizacija` (`id_specijalizacija`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `lekar_specijalizacija` */

/*Table structure for table `pacijent` */

DROP TABLE IF EXISTS `pacijent`;

CREATE TABLE `pacijent` (
  `id_pacijent` int unsigned NOT NULL AUTO_INCREMENT,
  `ime` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `prezime` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pol` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `datum_rodjenja` date DEFAULT NULL,
  `mesto_rodjenja` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `mejl` varchar(100) DEFAULT NULL,
  `id_krvna_grupa` int unsigned DEFAULT NULL,
  PRIMARY KEY (`id_pacijent`),
  KEY `spoljni_krvna_grupa` (`id_krvna_grupa`),
  CONSTRAINT `spoljni_krvna_grupa` FOREIGN KEY (`id_krvna_grupa`) REFERENCES `krvna_grupa` (`id_krvna_grupa`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `pacijent` */

insert  into `pacijent`(`id_pacijent`,`ime`,`prezime`,`pol`,`datum_rodjenja`,`mesto_rodjenja`,`mejl`,`id_krvna_grupa`) values 
(1,'Ана','Јовановић','MUSKI','2006-07-10','Нови Сад','ana@example.com',1),
(3,'Јована','Милић','ZENSKI','2005-01-18','Крагујевац','jovana@example.com',1),
(4,'Милан','Пантић','MUSKI','2005-06-20','Суботица','milan@example.com',1),
(5,'Софија','Милошевић','ZENSKI','2006-09-15','Краљево','sofija@example.com',10),
(17,'Александар','Чегањац','MUSKI','2001-08-17','Чачак','neki@gmail.com',10),
(19,'Пера','Перић','MUSKI','1987-08-28','Суботица','neki@gmail.com',1),
(23,'Миланка','Милић','MUSKI','1997-08-17','Ниш','',1);

/*Table structure for table `pregled` */

DROP TABLE IF EXISTS `pregled`;

CREATE TABLE `pregled` (
  `id_pregled` int unsigned NOT NULL AUTO_INCREMENT,
  `datum_vreme_zavrsetka` datetime DEFAULT NULL,
  `datum_kontrole` date DEFAULT NULL,
  `vreme_kontrole` time DEFAULT NULL,
  `ukupno_vreme_trajanja` int DEFAULT NULL,
  `terapija` varchar(100) DEFAULT NULL,
  `id_lekar` int unsigned DEFAULT NULL,
  `id_pacijent` int unsigned DEFAULT NULL,
  PRIMARY KEY (`id_pregled`),
  KEY `spoljni_lekar` (`id_lekar`),
  KEY `spoljni_pacijent` (`id_pacijent`),
  CONSTRAINT `spoljni_lekar` FOREIGN KEY (`id_lekar`) REFERENCES `lekar` (`id_lekar`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `spoljni_pacijent` FOREIGN KEY (`id_pacijent`) REFERENCES `pacijent` (`id_pacijent`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `pregled` */

insert  into `pregled`(`id_pregled`,`datum_vreme_zavrsetka`,`datum_kontrole`,`vreme_kontrole`,`ukupno_vreme_trajanja`,`terapija`,`id_lekar`,`id_pacijent`) values 
(6,'2025-03-02 11:00:00','2025-03-12','15:45:00',45,'Panklav, 400 mg',1,1),
(8,'2025-03-04 13:00:00','2025-03-18','08:30:00',60,'Физикална терапија',4,4),
(45,'2026-04-26 20:35:42','2026-05-17','11:50:00',0,'Brufen 400mg, 2x1',1,5),
(53,'2026-04-29 23:02:55','2026-08-17','10:00:00',5,NULL,1,1),
(57,'2026-04-30 11:39:51','2026-05-12','11:50:00',5,'Paracetamol, 1x1, pp',1,17),
(62,'2026-05-01 14:06:30','2026-08-14','11:45:00',20,'нема',1,1);

/*Table structure for table `specijalizacija` */

DROP TABLE IF EXISTS `specijalizacija`;

CREATE TABLE `specijalizacija` (
  `id_specijalizacija` int unsigned NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id_specijalizacija`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `specijalizacija` */

insert  into `specijalizacija`(`id_specijalizacija`,`naziv`) values 
(2,'neka'),
(3,'Хируригја'),
(4,'хематологија');

/*Table structure for table `stavka_pregleda` */

DROP TABLE IF EXISTS `stavka_pregleda`;

CREATE TABLE `stavka_pregleda` (
  `id_pregled` int unsigned NOT NULL,
  `id_stavka_pregleda` int unsigned NOT NULL,
  `naziv` varchar(50) NOT NULL,
  `lekarski_nalaz` varchar(700) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `vreme_trajanja` int NOT NULL,
  `id_dijagnoza` int unsigned NOT NULL,
  PRIMARY KEY (`id_pregled`,`id_stavka_pregleda`),
  KEY `id_stavka_pregleda` (`id_stavka_pregleda`),
  KEY `spoljni_dijagnoza` (`id_dijagnoza`),
  CONSTRAINT `spoljni_dijagnoza` FOREIGN KEY (`id_dijagnoza`) REFERENCES `dijagnoza` (`id_dijagnoza`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `spoljni_pregled` FOREIGN KEY (`id_pregled`) REFERENCES `pregled` (`id_pregled`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `stavka_pregleda` */

insert  into `stavka_pregleda`(`id_pregled`,`id_stavka_pregleda`,`naziv`,`lekarski_nalaz`,`vreme_trajanja`,`id_dijagnoza`) values 
(8,3,'Масажа леђа','Смањен бол након терапије',15,10),
(45,13,'Мерење притиска','Уредан налаз.',5,10),
(45,14,'Преглед стомака','Уредан налаз.',10,10),
(53,15,'proba','proba',5,6),
(62,1,'Лабораторијски налаз урина','Без бактерија',15,7),
(62,2,'Мерење телесне температуре','Температура нормална',5,7);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
