
DROP TABLE IF EXISTS `project_type`;

CREATE TABLE `project_type` (
  `code` varchar(255) NOT NULL,
  `desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `project_type` VALUES ('100','MySQL'),('200','Linux'),('300','Mule');
