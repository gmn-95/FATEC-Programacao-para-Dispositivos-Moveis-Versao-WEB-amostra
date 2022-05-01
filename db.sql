-- MySQL dump 10.13  Distrib 8.0.28, for Linux (x86_64)
--
-- Host: localhost    Database: db_agenda
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.20.04.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_agendamento`
--

DROP TABLE IF EXISTS `tb_agendamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_agendamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fk_id_contato` bigint NOT NULL,
  `fk_id_usuario` bigint NOT NULL,
  `data_agendada` date NOT NULL,
  `hora_agendada` time DEFAULT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `conteudo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK_tb_agendamento_2` (`fk_id_contato`),
  KEY `FK_tb_agendamento_4` (`fk_id_usuario`),
  CONSTRAINT `FK_tb_agendamento_2` FOREIGN KEY (`fk_id_contato`) REFERENCES `tb_contato` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_tb_agendamento_4` FOREIGN KEY (`fk_id_usuario`) REFERENCES `tb_usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_agendamento`
--

LOCK TABLES `tb_agendamento` WRITE;
/*!40000 ALTER TABLE `tb_agendamento` DISABLE KEYS */;
INSERT INTO `tb_agendamento` VALUES (17,35,15,'2022-10-13','22:00:00','jantar','teste editar'),(19,32,15,'2023-05-19','15:59:00','aadasda','asdasd'),(20,34,15,'2022-06-10','12:50:00','aniversario','meu aniversário'),(21,32,15,'2022-12-25','00:00:00','natal','festa de natal'),(22,34,15,'2023-01-01','00:00:00','ano novo','festa ano novo'),(23,35,15,'2025-10-13','22:00:00','jantar','jantar em casa'),(24,35,15,'2022-10-13','22:00:00','jantar','jantar em casa');
/*!40000 ALTER TABLE `tb_agendamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_contato`
--

DROP TABLE IF EXISTS `tb_contato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_contato` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fk_id_usuario` bigint NOT NULL,
  `nome` varchar(100) NOT NULL,
  `telefone_fixo` varchar(20) DEFAULT NULL,
  `celular` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `obs` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK_tb_contato_4` (`fk_id_usuario`),
  CONSTRAINT `FK_tb_contato_4` FOREIGN KEY (`fk_id_usuario`) REFERENCES `tb_usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_contato`
--

LOCK TABLES `tb_contato` WRITE;
/*!40000 ALTER TABLE `tb_contato` DISABLE KEYS */;
INSERT INTO `tb_contato` VALUES (32,15,'aaa','66-6666-6666','66-66666-6666','teste3@editar','asss'),(34,15,'teste','84-8468-4654','46-54546-5465','teste@criar','teste criar'),(35,15,'Gustavo Macedo','11-5646-5465','11-96545-4546','gustavo@gmail','teste'),(36,15,'jose','11-5484-8498','11-54546-4646','jose@gmail','');
/*!40000 ALTER TABLE `tb_contato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_endereco`
--

DROP TABLE IF EXISTS `tb_endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_endereco` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bairro` varchar(150) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `estado` varchar(100) DEFAULT NULL,
  `cep` varchar(100) NOT NULL,
  `logradouro` varchar(100) DEFAULT NULL,
  `numero` varchar(20) NOT NULL,
  `complemento` varchar(100) DEFAULT NULL,
  `fk_id_usuario` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_tb_usuario_endereco` (`fk_id_usuario`),
  CONSTRAINT `fk_tb_usuario_endereco` FOREIGN KEY (`fk_id_usuario`) REFERENCES `tb_usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_endereco`
--

LOCK TABLES `tb_endereco` WRITE;
/*!40000 ALTER TABLE `tb_endereco` DISABLE KEYS */;
INSERT INTO `tb_endereco` VALUES (30,'Vila Bela Vista (Zona Sul)','São Paulo','SP','04952-080','Rua Joaquim Manuel da Rocha','15','',15);
/*!40000 ALTER TABLE `tb_endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_endereco_do_contato`
--

DROP TABLE IF EXISTS `tb_endereco_do_contato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_endereco_do_contato` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fk_id_endereco` bigint NOT NULL,
  `fk_id_contato` bigint NOT NULL,
  `obs` varchar(150) DEFAULT NULL,
  `fk_id_usuario` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tb_endereco` (`fk_id_endereco`),
  KEY `FK_tb_contato` (`fk_id_contato`),
  KEY `fk_tb_usuario` (`fk_id_usuario`),
  CONSTRAINT `FK_tb_contato` FOREIGN KEY (`fk_id_contato`) REFERENCES `tb_contato` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_tb_endereco` FOREIGN KEY (`fk_id_endereco`) REFERENCES `tb_endereco` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_tb_usuario` FOREIGN KEY (`fk_id_usuario`) REFERENCES `tb_usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_endereco_do_contato`
--

LOCK TABLES `tb_endereco_do_contato` WRITE;
/*!40000 ALTER TABLE `tb_endereco_do_contato` DISABLE KEYS */;
INSERT INTO `tb_endereco_do_contato` VALUES (30,30,35,'',15);
/*!40000 ALTER TABLE `tb_endereco_do_contato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pessoa`
--

DROP TABLE IF EXISTS `tb_pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_pessoa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pessoa`
--

LOCK TABLES `tb_pessoa` WRITE;
/*!40000 ALTER TABLE `tb_pessoa` DISABLE KEYS */;
INSERT INTO `tb_pessoa` VALUES (18,'Gustavo Macedo'),(22,'teste'),(23,'jose'),(24,'maria'),(25,'user'),(26,'asd'),(27,'asd'),(28,'asd'),(29,'asd'),(30,'asd'),(31,'asd'),(32,'asd'),(33,'gmn');
/*!40000 ALTER TABLE `tb_pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_usuario`
--

DROP TABLE IF EXISTS `tb_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `login` varchar(150) NOT NULL,
  `senha` varchar(150) NOT NULL,
  `fk_id_pessoa` bigint NOT NULL,
  `criar_novo_usuario` tinyint(1) NOT NULL,
  `editar_usuario` tinyint(1) NOT NULL,
  `excluir_usuario` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fk_id_pessoa` (`fk_id_pessoa`,`id`),
  UNIQUE KEY `login` (`login`),
  CONSTRAINT `FK_id_pessoa` FOREIGN KEY (`fk_id_pessoa`) REFERENCES `tb_pessoa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuario`
--

LOCK TABLES `tb_usuario` WRITE;
/*!40000 ALTER TABLE `tb_usuario` DISABLE KEYS */;
INSERT INTO `tb_usuario` VALUES (15,'admin','admin',18,1,1,1),(20,'user','123',22,0,0,0);
/*!40000 ALTER TABLE `tb_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'db_agenda'
--

--
-- Dumping routines for database 'db_agenda'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-30 19:24:15
