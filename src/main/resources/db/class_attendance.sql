-- MySQL dump 10.13  Distrib 5.7.28, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: class_attendance
-- ------------------------------------------------------
-- Server version	5.7.28-0ubuntu0.19.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `spread` tinyint(4) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'基础数据管理','&#xe716;','',0,1),(2,'考勤信息管理','&#xe613;','',0,1),(3,'考勤信息管理','&#xe613;','',0,2),(4,'考勤信息管理','&#xe613;','',0,3),(5,'选课管理','&#xe716;','',0,NULL);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu_children`
--

DROP TABLE IF EXISTS `sys_menu_children`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu_children` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `spread` tinyint(4) DEFAULT '0',
  `menu_menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3x9iahy8ma4rxeg4b7p9bpalg` (`menu_menu_id`),
  CONSTRAINT `FK3x9iahy8ma4rxeg4b7p9bpalg` FOREIGN KEY (`menu_menu_id`) REFERENCES `sys_menu` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu_children`
--

LOCK TABLES `sys_menu_children` WRITE;
/*!40000 ALTER TABLE `sys_menu_children` DISABLE KEYS */;
INSERT INTO `sys_menu_children` VALUES (1,'二级学院信息','&#xe602;','/ClassAttendance/page/biz/tb_college/college-list.html',0,1),(2,'教师信息','&#xe602;','/ClassAttendance/page/biz/tb_teacher/teacher-list.html',0,1),(3,'课程信息','&#xe602;','/ClassAttendance/page/biz/tb_course/course-list.html',0,1),(4,'班级信息','&#xe602;','/ClassAttendance/page/biz/tb_class/class-list.html',0,1),(5,'学生信息','&#xe602;','/ClassAttendance/page/biz/tb_student/student-list.html',0,1),(6,'选课信息','&#xe602;','/ClassAttendance/page/biz/tb_course_choose/courseChoose-list.html',0,NULL),(7,'考勤记录','&#xe602;','/ClassAttendance/page/biz/tb_attendance/attendance-list-admin.html',0,2),(10,'考勤记录','&#xe602;','/ClassAttendance/page/biz/tb_attendance/attendance-list-tea.html',0,3),(11,'点名考勤','&#xe602;','/ClassAttendance/page/biz/tb_attendance/checkWork/my-course-stu-list.html',0,3),(20,'考勤记录','&#xe602;','/ClassAttendance/page/biz/tb_attendance/attendance-list-stu.html',0,4),(21,'已选课程','&#xe602;','/ClassAttendance/page/biz/tb_course_choose/have-chosen.html',0,5),(22,'未选课程','&#xe602;','/ClassAttendance/page/biz/tb_course_choose/no-choice.html',0,5);
/*!40000 ALTER TABLE `sys_menu_children` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `login_number` varchar(255) NOT NULL COMMENT '账号==工号/学号',
  `login_password` varchar(255) NOT NULL COMMENT '密码',
  `user_type` int(11) NOT NULL DEFAULT '3' COMMENT '用户类型：1-管理员，2-老师，3-学生(默认)',
  `user_name` varchar(255) NOT NULL COMMENT '用户姓名',
  `user_status` int(11) NOT NULL DEFAULT '1' COMMENT '0-删除，1-正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','admin',1,'系统管理员',1),(157416442866746,'teacher','123456',2,'教师1',1),(157416503562142,'student','123456',3,'学生1',1);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_attendance`
--

DROP TABLE IF EXISTS `tb_attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_attendance` (
  `id` bigint(20) NOT NULL,
  `teacher_code` varchar(255) DEFAULT NULL COMMENT '被考勤学生',
  `course_id` bigint(20) DEFAULT NULL,
  `attendance_time` varchar(255) DEFAULT NULL COMMENT '考勤时间',
  `attendance_type` int(11) DEFAULT NULL COMMENT '考勤情况：1-出勤，2-请假，3-迟到，4-旷课',
  `course_name` varchar(255) DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `student_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_attendance`
--

LOCK TABLES `tb_attendance` WRITE;
/*!40000 ALTER TABLE `tb_attendance` DISABLE KEYS */;
INSERT INTO `tb_attendance` VALUES (157476228359436,'teacher',157416514147151,'2019-11-26 17:58:03',2,'课程1','测试2','201700208205'),(157476228374108,'teacher',157416514147151,'2019-11-26 17:58:03',3,'课程1','学生1','student');
/*!40000 ALTER TABLE `tb_attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_class`
--

DROP TABLE IF EXISTS `tb_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_class` (
  `id` bigint(20) NOT NULL COMMENT '班级编号',
  `college_id` bigint(20) NOT NULL COMMENT '所属学院',
  `class_name` varchar(255) NOT NULL COMMENT '班级名称',
  `class_grade` varchar(255) NOT NULL COMMENT '年级',
  `class_major` varchar(255) NOT NULL COMMENT '专业名称',
  PRIMARY KEY (`id`),
  KEY `FKF9ED1067680738EF` (`college_id`),
  CONSTRAINT `FKF9ED1067680738EF` FOREIGN KEY (`college_id`) REFERENCES `tb_college` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班级表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_class`
--

LOCK TABLES `tb_class` WRITE;
/*!40000 ALTER TABLE `tb_class` DISABLE KEYS */;
INSERT INTO `tb_class` VALUES (157416062420736,157415521953321,'软件2班','17级','软件工程');
/*!40000 ALTER TABLE `tb_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_college`
--

DROP TABLE IF EXISTS `tb_college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_college` (
  `id` bigint(20) NOT NULL COMMENT '学院编号',
  `college_name` varchar(255) NOT NULL COMMENT '学院名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学院表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_college`
--

LOCK TABLES `tb_college` WRITE;
/*!40000 ALTER TABLE `tb_college` DISABLE KEYS */;
INSERT INTO `tb_college` VALUES (1,'宝艺学院'),(157415521953321,'大数据学院');
/*!40000 ALTER TABLE `tb_college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_course`
--

DROP TABLE IF EXISTS `tb_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_course` (
  `id` bigint(20) NOT NULL,
  `query_code` varchar(255) DEFAULT NULL,
  `teacher_id` bigint(20) DEFAULT NULL COMMENT '开课老师',
  `course_name` varchar(255) DEFAULT NULL COMMENT '课程名称',
  `course_year` varchar(3) DEFAULT NULL COMMENT '学年',
  `course_term` varchar(150) DEFAULT NULL COMMENT '学期',
  `course_hour` int(11) DEFAULT NULL COMMENT '学时',
  `course_capacity` int(11) DEFAULT NULL COMMENT '课程最大容量',
  `course_residual` int(11) DEFAULT NULL COMMENT '剩余容量',
  PRIMARY KEY (`id`),
  KEY `FK43E8570CB294ECCF` (`teacher_id`),
  CONSTRAINT `FK43E8570CB294ECCF` FOREIGN KEY (`teacher_id`) REFERENCES `tb_teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_course`
--

LOCK TABLES `tb_course` WRITE;
/*!40000 ALTER TABLE `tb_course` DISABLE KEYS */;
INSERT INTO `tb_course` VALUES (157416514147151,'157416514147151',157416442866746,'课程1','10','11',11,20,20),(157416514147152,'157416514147152',157416442866746,'课程2','10','11',11,20,20),(157416514147153,'157416514147153',157416442866746,'课程3','10','11',11,20,20),(157416514147158,'157416514147158',157416442866746,'测试','10','11',11,20,20);
/*!40000 ALTER TABLE `tb_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_student`
--

DROP TABLE IF EXISTS `tb_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_student` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `student_code` varchar(255) NOT NULL COMMENT '学号',
  `class_id` bigint(20) DEFAULT NULL COMMENT '所属班级',
  `student_sex` int(11) DEFAULT '1' COMMENT '性别：0-女，1-男(默认)',
  `student_phone` varchar(11) DEFAULT NULL COMMENT '电话',
  `student_email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `student_name` varchar(255) DEFAULT NULL,
  `course_set` varchar(255) DEFAULT NULL,
  `checkWorkType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9007E96AABEEC10F` (`class_id`),
  CONSTRAINT `FK9007E96AABEEC10F` FOREIGN KEY (`class_id`) REFERENCES `tb_class` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_student`
--

LOCK TABLES `tb_student` WRITE;
/*!40000 ALTER TABLE `tb_student` DISABLE KEYS */;
INSERT INTO `tb_student` VALUES (1,'201700208204',157416062420736,0,'18277404022','583403411@qq.com','测试1','157416514147152,157416514147153',NULL),(2,'201700208205',157416062420736,0,'18277404022','583403411@qq.com','测试2','157416514147151',NULL),(3,'201700208206',157416062420736,0,'18277404022','583403411@qq.com','测试3','157416514147158',NULL),(157416503562142,'student',157416062420736,1,'18277404022','583403411@qq.com','学生1','157416514147151,157416514147152',NULL);
/*!40000 ALTER TABLE `tb_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_teacher`
--

DROP TABLE IF EXISTS `tb_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_teacher` (
  `id` bigint(20) NOT NULL,
  `teacher_code` varchar(255) DEFAULT NULL COMMENT '工号',
  `teacher_name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `teacher_sex` int(11) DEFAULT '1' COMMENT '性别：0-女，1-男(默认)',
  `college_id` bigint(20) DEFAULT NULL COMMENT '学院',
  PRIMARY KEY (`id`),
  KEY `FKAA3B3311680738EF` (`college_id`),
  CONSTRAINT `FKAA3B3311680738EF` FOREIGN KEY (`college_id`) REFERENCES `tb_college` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_teacher`
--

LOCK TABLES `tb_teacher` WRITE;
/*!40000 ALTER TABLE `tb_teacher` DISABLE KEYS */;
INSERT INTO `tb_teacher` VALUES (157416442866746,'teacher','教师1',1,157415521953321);
/*!40000 ALTER TABLE `tb_teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-30 12:51:14
