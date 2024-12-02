-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: bdinnovaschool
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumno` (
  `DiasInhabilitado` int DEFAULT NULL,
  `Usuario_CodUsuario` int NOT NULL,
  `Estado` varchar(20) NOT NULL,
  `NombresApellidos` varchar(90) NOT NULL,
  PRIMARY KEY (`Usuario_CodUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES (NULL,1,'1','jhonatan quiñones');
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `CodMaterial` int NOT NULL AUTO_INCREMENT,
  `Stock` int NOT NULL,
  `Tipo` varchar(25) NOT NULL,
  `Descripcion` varchar(45) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`CodMaterial`)
) ENGINE=InnoDB AUTO_INCREMENT=1069 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (1001,271,'libros','libro de matematica avanzada','vectores y matrices'),(1002,230,'Libros','Introducción a la programación en Java','Java I'),(1003,116,'Libros','Guía básica de álgebra lineal','Álgebra II'),(1004,90,'Libros','Historia de las civilizaciones','Historia Antigua'),(1005,84,'Libros','Manual práctico de biología','Biología 101'),(1006,64,'Libros','Técnicas modernas en física','Física Moderna'),(1007,42,'Libros','Fundamentos de química','Química Orgánica'),(1008,30,'Libros','Literatura juvenil contemporánea','Cuentos Clásicos'),(1009,150,'Revistas','Revista científica para estudiantes','Ciencia Escolar'),(1010,100,'Revistas','Tecnología y avances educativos','Tech News'),(1011,80,'Revistas','Arte y cultura para jóvenes','Mundo del Arte'),(1012,60,'Revistas','Revista mensual de astronomía','Estrellas y Planetas'),(1013,50,'Revistas','Revista de tendencias literarias','Letras en Acción'),(1014,30,'Revistas','Revista de diseño gráfico','Diseña Tu Mundo'),(1015,40,'Instrumentos','Set de microscopios básicos','Microscopio Escolar Básico'),(1016,35,'Instrumentos','Cámara fotográfica profesional','Canon EOS Rebel T7'),(1017,20,'Instrumentos','Proyector de alta resolución','BenQ MS535'),(1018,50,'Instrumentos','Calculadora avanzada de gráficos','Texas Instruments TI-84'),(1019,25,'Instrumentos','Juego de lentes ópticos','Lentes de Laboratorio'),(1020,15,'Instrumentos','Medidor de pH portátil','PH Metro Escolar'),(1021,300,'Otros','Kits de geometría completos','Kit Geométrico Pro'),(1022,200,'Otros','Mochilas reutilizables','Mochila Escolar Básica'),(1023,180,'Otros','Tableros de dibujo técnico','Tablero para Estudiantes'),(1024,160,'Otros','Cajas de marcadores de colores','Marcadores Premium'),(1025,120,'Otros','Juegos de plastilina educativa','Plastilina Multicolor'),(1026,90,'Otros','Modelos anatómicos educativos','Esqueleto Humano'),(1027,60,'Otros','Set de herramientas para física','Kit de Física Escolar'),(1028,50,'Otros','Relojes de arena para laboratorio','Reloj de Arena Escolar'),(1029,50,'Libros','Introducción a la robótica para principiantes','Robótica Escolar'),(1030,70,'Libros','Manual de circuitos eléctricos básicos','Electrónica Básica'),(1031,65,'Libros','El universo y su historia','Astronomía para Todos'),(1032,90,'Libros','Guía de laboratorio de química','Prácticas Químicas'),(1033,80,'Libros','Historia de las matemáticas','Números y Teoremas'),(1034,45,'Libros','Técnicas de escritura creativa','Escribir con Imaginación'),(1035,55,'Libros','Guía de programación en Python','Python para Principiantes'),(1036,40,'Libros','Diccionario de términos biológicos','Biología de A a Z'),(1037,30,'Libros','Introducción a la literatura clásica','Clásicos Literarios'),(1038,25,'Libros','Filosofía para estudiantes','Pensamiento Crítico'),(1039,150,'Revistas','Revista mensual sobre inteligencia artificial','AI Hoy'),(1040,100,'Revistas','Educación ambiental para jóvenes','Planeta Verde'),(1041,80,'Revistas','Avances en neurociencia escolar','Cerebro y Aprendizaje'),(1042,90,'Revistas','Diseño gráfico y multimedia','Arte Digital'),(1043,60,'Revistas','Tendencias de programación','Código en Acción'),(1044,50,'Revistas','Mundo de las matemáticas','Matemáticas Cotidianas'),(1045,40,'Revistas','Avances en energía renovable','Futuro Sustentable'),(1046,30,'Revistas','Robótica y mecatrónica juvenil','Robots en Marcha'),(1047,35,'Revistas','Aplicaciones científicas modernas','Ciencia Avanza'),(1048,25,'Revistas','Novedades en literatura juvenil','Historias Jóvenes'),(1049,20,'Instrumentos','Kit de laboratorio químico','Laboratorio Escolar'),(1050,18,'Instrumentos','Telescopio para observación lunar','Telescopio Escolar'),(1051,15,'Instrumentos','Cronómetro digital','Cronómetro Escolar'),(1052,25,'Instrumentos','Cámara de alta velocidad','Cámara GoPro'),(1053,30,'Instrumentos','Estación meteorológica portátil','Estación Climática'),(1054,12,'Instrumentos','Multímetro digital','Multímetro Avanzado'),(1055,40,'Instrumentos','Brazo robótico educativo','Robot Escolar'),(1056,35,'Instrumentos','Microscopio binocular','Microscopio Avanzado'),(1057,22,'Instrumentos','Medidor de temperatura y humedad','Termohigrómetro Escolar'),(1058,28,'Instrumentos','Cámara de realidad virtual','Cámara VR Escolar'),(1059,300,'Otros','Juegos de lápices ópticos','Lápices Ópticos'),(1060,250,'Otros','Paquetes de hojas para impresión','Hojas de Impresión'),(1061,200,'Otros','Set de borradores y sacapuntas','Kit Escolar'),(1062,180,'Otros','Mochilas con ruedas','Mochilas Rodantes'),(1063,170,'Otros','Set de cintas adhesivas','Cinta Adhesiva Escolar'),(1064,150,'Otros','Mapas del mundo en papel','Mapamundi Escolar'),(1065,140,'Otros','Estuches para lápices y colores','Estuche Escolar'),(1066,130,'Otros','Cubetas para experimentos','Cubetas de Laboratorio'),(1067,120,'Otros','Tablas periódicas ilustradas','Tabla Periódica Visual'),(1068,100,'Otros','Kits de reciclaje para escuelas','Kit de Reciclaje Escolar');
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `penalizacion`
--

DROP TABLE IF EXISTS `penalizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `penalizacion` (
  `IdPenalizacion` int NOT NULL AUTO_INCREMENT,
  `Prestamo_idPrestamo` int DEFAULT NULL,
  `FechaPenalizacion` datetime(6) NOT NULL,
  `Descripcion` varchar(255) NOT NULL,
  PRIMARY KEY (`IdPenalizacion`),
  KEY `FKj35miqo2h2u44nnsowtts6u8f` (`Prestamo_idPrestamo`),
  CONSTRAINT `FKj35miqo2h2u44nnsowtts6u8f` FOREIGN KEY (`Prestamo_idPrestamo`) REFERENCES `prestamo` (`IdPrestamo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `penalizacion`
--

LOCK TABLES `penalizacion` WRITE;
/*!40000 ALTER TABLE `penalizacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `penalizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamo` (
  `IdPrestamo` int NOT NULL AUTO_INCREMENT,
  `Solicitud_idSolicitud` int DEFAULT NULL,
  `FechaPrestamo` datetime(6) NOT NULL,
  `Fecha_dev_real` datetime(6) DEFAULT NULL,
  `Estado` varchar(15) NOT NULL,
  PRIMARY KEY (`IdPrestamo`),
  KEY `FKodd7q9fitmjlf3w11sqmghoxr` (`Solicitud_idSolicitud`),
  CONSTRAINT `FKodd7q9fitmjlf3w11sqmghoxr` FOREIGN KEY (`Solicitud_idSolicitud`) REFERENCES `solicitud` (`IdSolicitud`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
INSERT INTO `prestamo` VALUES (1,17,'2024-11-18 00:03:23.000000',NULL,'En Curso'),(4,17,'2024-11-18 00:04:15.000000',NULL,'En Curso'),(7,17,'2024-11-18 00:04:17.000000',NULL,'En Curso'),(10,18,'2024-11-18 00:04:18.000000',NULL,'En Curso'),(15,19,'2024-11-17 21:53:02.000000',NULL,'En Curso'),(16,20,'2024-11-17 21:53:54.000000',NULL,'En Curso'),(17,21,'2024-11-17 22:05:25.000000',NULL,'En Curso'),(18,23,'2024-11-17 22:49:05.000000',NULL,'En Curso'),(19,22,'2024-11-17 23:17:13.000000',NULL,'En Curso'),(20,24,'2024-11-17 23:24:41.000000',NULL,'En Curso'),(21,26,'2024-11-17 23:32:53.000000',NULL,'En Curso'),(22,25,'2024-11-17 23:33:08.000000',NULL,'En Curso'),(23,28,'2024-11-18 08:49:55.000000',NULL,'En Curso'),(24,27,'2024-11-18 08:50:18.000000',NULL,'En Curso'),(25,29,'2024-11-18 12:14:09.000000',NULL,'En Curso'),(26,32,'2024-11-18 13:40:11.000000',NULL,'En Curso'),(27,30,'2024-11-26 21:18:12.000000',NULL,'En Curso'),(28,31,'2024-11-26 21:19:52.000000',NULL,'En Curso'),(29,33,'2024-11-26 21:21:45.000000',NULL,'En Curso'),(30,34,'2024-11-26 21:27:07.000000',NULL,'En Curso');
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKldv0v52e0udsh2h1rs0r0gw1n` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud`
--

DROP TABLE IF EXISTS `solicitud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitud` (
  `Alumno_Usuario_CodUsuario` int DEFAULT NULL,
  `Cantidad` int NOT NULL,
  `IdSolicitud` int NOT NULL AUTO_INCREMENT,
  `Material_CodMaterial` int DEFAULT NULL,
  `Estado` varchar(15) NOT NULL,
  PRIMARY KEY (`IdSolicitud`),
  KEY `FK8qce5nxvg098hx20n9tvymjub` (`Alumno_Usuario_CodUsuario`),
  KEY `FK20c41q8ceqstrrfccl6fiqi2a` (`Material_CodMaterial`),
  CONSTRAINT `FK20c41q8ceqstrrfccl6fiqi2a` FOREIGN KEY (`Material_CodMaterial`) REFERENCES `material` (`CodMaterial`),
  CONSTRAINT `FK8qce5nxvg098hx20n9tvymjub` FOREIGN KEY (`Alumno_Usuario_CodUsuario`) REFERENCES `alumno` (`Usuario_CodUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud`
--

LOCK TABLES `solicitud` WRITE;
/*!40000 ALTER TABLE `solicitud` DISABLE KEYS */;
INSERT INTO `solicitud` VALUES (1,1,17,1002,'Aprobado'),(1,1,18,1004,'Aprobado'),(1,1,19,1005,'Aprobado'),(1,1,20,1007,'Aprobado'),(1,1,21,1004,'Aprobado'),(1,1,22,1004,'Aprobado'),(1,1,23,1006,'Aprobado'),(1,1,24,1005,'Aprobado'),(1,1,25,1006,'Aprobado'),(1,1,26,1007,'Aprobado'),(1,1,27,1006,'Aprobado'),(1,1,28,1005,'Aprobado'),(1,1,29,1007,'Aprobado'),(1,1,30,1006,'Aprobado'),(1,1,31,1004,'Aprobado'),(1,1,32,1003,'Aprobado'),(1,1,33,1002,'Aprobado'),(1,1,34,1003,'Aprobado');
/*!40000 ALTER TABLE `solicitud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `CodUsuario` int NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) DEFAULT NULL,
  `Contrasenia` varchar(200) NOT NULL,
  `username` varchar(12) NOT NULL,
  PRIMARY KEY (`CodUsuario`),
  UNIQUE KEY `UK471i15k6vbj1lfsfb19getcdi` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,_binary '','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS','jhon'),(2,_binary '','$2a$10$LGqfgAjxFFOHby4QU1deDO2LGcOZpleA6GeFG6sQ4N89BrLut9Ufe','tony');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_roles`
--

DROP TABLE IF EXISTS `usuario_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_roles` (
  `Usuario_CodUsuario` int NOT NULL,
  `roles_id` bigint NOT NULL,
  PRIMARY KEY (`Usuario_CodUsuario`,`roles_id`),
  KEY `FK66by6tur2v4lshluyc4pi9o07` (`roles_id`),
  CONSTRAINT `FK66by6tur2v4lshluyc4pi9o07` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKa7cb09efnd60cqd4x8dn55ryj` FOREIGN KEY (`Usuario_CodUsuario`) REFERENCES `usuario` (`CodUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_roles`
--

LOCK TABLES `usuario_roles` WRITE;
/*!40000 ALTER TABLE `usuario_roles` DISABLE KEYS */;
INSERT INTO `usuario_roles` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `usuario_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-29 23:55:11
