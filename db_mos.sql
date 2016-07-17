/*
SQLyog v10.2 
MySQL - 5.1.32-community : Database - db_mos
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_mos` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_mos`;

/*Table structure for table `tb_business` */

DROP TABLE IF EXISTS `tb_business`;

CREATE TABLE `tb_business` (
  `businessID` int(11) NOT NULL AUTO_INCREMENT COMMENT '商家ID',
  `businessName` varchar(30) DEFAULT NULL COMMENT '商家名称',
  `sendOutPrice` double DEFAULT NULL COMMENT '起送价钱',
  `distributionPrice` double DEFAULT NULL COMMENT '配送价钱',
  `shopHours` datetime DEFAULT NULL COMMENT '营业时间',
  `businessAddress` varchar(100) DEFAULT NULL COMMENT '商家地址',
  `businessDepict` varchar(100) DEFAULT NULL COMMENT '商家简介',
  `notice` varchar(500) DEFAULT NULL COMMENT '公告与活动',
  `businessScenery` varchar(100) DEFAULT NULL COMMENT '商家实景',
  `logo` varchar(100) DEFAULT NULL COMMENT '商家logo',
  `categoryID` int(11) DEFAULT NULL COMMENT '经营类别ID，外键',
  PRIMARY KEY (`businessID`),
  KEY `b_fk_1` (`categoryID`),
  CONSTRAINT `b_fk_1` FOREIGN KEY (`categoryID`) REFERENCES `tb_category` (`categoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tb_business` */

insert  into `tb_business`(`businessID`,`businessName`,`sendOutPrice`,`distributionPrice`,`shopHours`,`businessAddress`,`businessDepict`,`notice`,`businessScenery`,`logo`,`categoryID`) values (1,'清香奶茶店',12,0,'2004-00-00 20:00:00','淮南安理大本部东门学院南路','每日清晨会去菜市挑选最新鲜的原材料，让您吃的放心，实惠！','各位小主，本店优先配送饿了么，遇到恶劣天气送餐较慢，请多多担待哈！如果您的美食凉了，或者口味不好，一定要第一时间和本店联系，做得不好的地方我们一定加油改进，在这个寒冷的冬季，我们一定努力温暖您的胃，如您对送餐服务口味满意请给我5分好评呦！！！饿了么网络订餐，汇集周边所有美食，水果、生鲜、烧烤、超市、美食快餐应有尽！美好生活，触手可得！！！','/business/images/logo1.png','/business/images/logo1.png',1),(2,'时速鲜果店',20,0,'2004-00-00 20:00:00','合肥市蜀山区安粮城市广场A区1-3幢商109室','每天推出不同水果做特惠活动，让每位进店顾客都能感受到实惠又新鲜的水果','为了最大程度保证大家的利益，水果在运输过程中受到任何损坏和问题请及时与客服联系，我们将无条件为你调换！电话13349112321','/business/images/logo2.png','/business/images/logo2.png',1),(3,'清香奶茶店',10,0,'2004-00-00 20:00:00','淮南安理大本部东门学院南路','每日清晨会去菜市挑选最新鲜的原材料，让您吃的放心，实惠！','各位小主，本店优先配送饿了么，遇到恶劣天气送餐较慢，请多多担待哈！如果您的美食凉了，或者口味不好，一定要第一时间和本店联系，做得不好的地方我们一定加油改进，在这个寒冷的冬季，我们一定努力温暖您的胃，如您对送餐服务口味满意请给我5分好评呦！！！饿了么网络订餐，汇集周边所有美食，水果、生鲜、烧烤、超市、美食快餐应有尽！美好生活，触手可得！！！','/business/images/logo1.png','/business/images/logo1.png',1);

/*Table structure for table `tb_category` */

DROP TABLE IF EXISTS `tb_category`;

CREATE TABLE `tb_category` (
  `categoryID` int(11) NOT NULL AUTO_INCREMENT COMMENT '经营类别ID',
  `categoryName` varchar(30) DEFAULT NULL COMMENT '经营类别名称',
  PRIMARY KEY (`categoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `tb_category` */

insert  into `tb_category`(`categoryID`,`categoryName`) values (1,'甜品饮品'),(2,'快餐便当'),(3,'特色菜系'),(4,'异国料理'),(5,'小吃夜宵'),(6,'果蔬生鲜'),(7,'鲜花蛋糕'),(8,'商店超市');

/*Table structure for table `tb_members` */

DROP TABLE IF EXISTS `tb_members`;

CREATE TABLE `tb_members` (
  `memberID` varchar(10) NOT NULL COMMENT '会员账号',
  `password` varchar(10) DEFAULT NULL COMMENT '会员密码',
  `rank` varchar(10) DEFAULT NULL COMMENT '会员等级',
  `credit` int(11) DEFAULT NULL COMMENT '会员积分',
  `phone` varchar(20) DEFAULT NULL COMMENT '会员手机号',
  `imgPath` varchar(100) DEFAULT NULL COMMENT '会员皮肤',
  PRIMARY KEY (`memberID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_members` */

insert  into `tb_members`(`memberID`,`password`,`rank`,`credit`,`phone`,`imgPath`) values ('ara123','123456','LV1',0,NULL,'/admin/images/face.jpg'),('ara123456','123456','LV1',1000,'1531231231',NULL);

/*Table structure for table `tb_menus` */

DROP TABLE IF EXISTS `tb_menus`;

CREATE TABLE `tb_menus` (
  `menusID` int(11) NOT NULL AUTO_INCREMENT COMMENT '餐品IID',
  `menusName` varchar(30) DEFAULT NULL COMMENT '餐品名称',
  `menusPrice` double DEFAULT NULL COMMENT '餐品价钱',
  `menusDepict` varchar(100) DEFAULT NULL COMMENT '餐品简述',
  `menusImagePath` varchar(100) DEFAULT NULL COMMENT '餐品图片路径',
  `businessID` int(11) DEFAULT NULL COMMENT '商家ID，外键',
  PRIMARY KEY (`menusID`),
  KEY `m_fk_1` (`businessID`),
  CONSTRAINT `m_fk_1` FOREIGN KEY (`businessID`) REFERENCES `tb_business` (`businessID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_menus` */

insert  into `tb_menus`(`menusID`,`menusName`,`menusPrice`,`menusDepict`,`menusImagePath`,`businessID`) values (1,'蜂蜜绿茶',10,'蜂蜜绿茶，农家纯正蜂蜜','/images/img1.png',1),(2,'蜂蜜绿茶',10,'蜂蜜绿茶，农家纯正蜂蜜','/images/img1.png',1);

/*Table structure for table `tb_order` */

DROP TABLE IF EXISTS `tb_order`;

CREATE TABLE `tb_order` (
  `orderID` varchar(10) NOT NULL COMMENT '订单编号',
  `commitTime` datetime DEFAULT NULL COMMENT '订单提交时间',
  `amount` int(11) DEFAULT NULL COMMENT '餐品数量',
  `totalPrice` double DEFAULT NULL COMMENT '总价钱',
  `status` varchar(10) DEFAULT NULL COMMENT '订单状态',
  `menusID` int(11) DEFAULT NULL COMMENT '餐品ID，外键',
  `memberID` varchar(10) DEFAULT NULL COMMENT '会员ID，外键',
  PRIMARY KEY (`orderID`),
  KEY `o_fk_1` (`menusID`),
  KEY `o_fk_2` (`memberID`),
  CONSTRAINT `o_fk_1` FOREIGN KEY (`menusID`) REFERENCES `tb_menus` (`menusID`),
  CONSTRAINT `o_fk_2` FOREIGN KEY (`memberID`) REFERENCES `tb_members` (`memberID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_order` */

insert  into `tb_order`(`orderID`,`commitTime`,`amount`,`totalPrice`,`status`,`menusID`,`memberID`) values ('',NULL,NULL,NULL,NULL,NULL,NULL),('1','2016-07-02 00:00:00',10,300,'已付款',1,'ara123456'),('2','2016-07-04 15:33:47',10,300,'已付款',2,'ara123456');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
