CREATE DATABASE IF NOT EXISTS db2_project;

USE db2_project;

CREATE TABLE IF NOT EXISTS Users (
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  username VARCHAR(32) UNIQUE NOT NULL,
  email VARCHAR(64) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  isBlocked BOOLEAN DEFAULT false,
  isAdmin BOOLEAN DEFAULT false
);

CREATE TABLE IF NOT EXISTS Products (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  image LONGBLOB DEFAULT NULL,
  createDate DATETIME DEFAULT NOW(),
  highlightDate DATETIME DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS Reviews (
  prodId INT,
  userId INT,
  body VARCHAR(255) NOT NULL,
  PRIMARY KEY (prodId, userId)
);

ALTER TABLE Reviews ADD FOREIGN KEY (prodId) REFERENCES Users (id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Reviews ADD FOREIGN KEY (userId) REFERENCES Products (id) ON UPDATE CASCADE ON DELETE CASCADE;


CREATE TABLE IF NOT EXISTS  Questions (
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  prodId INT,
  body varchar(255) NOT NULL
);

ALTER TABLE Questions ADD FOREIGN KEY (prodId) REFERENCES Products (id) ON UPDATE CASCADE ON DELETE CASCADE;

CREATE TABLE IF NOT EXISTS Answers (
  userId INT,
  quetionID INT,
  body varchar(255) NOT NULL,
  PRIMARY KEY (userId, quetionID)
);

ALTER TABLE Answers ADD FOREIGN KEY (userId) REFERENCES Users (id) ON UPDATE CASCADE ON DELETE NO ACTION;

ALTER TABLE Answers ADD FOREIGN KEY (quetionID) REFERENCES Questions (id) ON UPDATE CASCADE ON DELETE CASCADE;


CREATE TABLE IF NOT EXISTS StatisticAnswers (
  userId INT,
  prodID INT,
  gender ENUM('male', 'famale', 'helicopter') NOT NULL,
  expLvl ENUM('low', 'medium', 'high') NOT NULL,
  age INT CHECK (age > 0) NOT NULL,
  PRIMARY KEY (userId, prodID)
);

ALTER TABLE StatisticAnswers ADD FOREIGN KEY (userId) REFERENCES Users (id);

ALTER TABLE StatisticAnswers ADD FOREIGN KEY (prodID) REFERENCES Products (id);


CREATE TABLE IF NOT EXISTS Score (
  userId INT,
  prodID INT,
  points INT CHECK (points > 0),
  PRIMARY KEY (userId, prodID)
);

ALTER TABLE Score ADD FOREIGN KEY (userId) REFERENCES Users (id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Score ADD FOREIGN KEY (prodID) REFERENCES Products (id) ON UPDATE CASCADE ON DELETE CASCADE;


CREATE TABLE IF NOT EXISTS QuestionaireLogs (
  userId INT,
  prodID INT,
  openDate DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (userId, prodID, openDate)
);

ALTER TABLE QuestionaireLogs ADD FOREIGN KEY (userId) REFERENCES Users (id) ON UPDATE CASCADE ON DELETE NO ACTION;

ALTER TABLE QuestionaireLogs ADD FOREIGN KEY (prodID) REFERENCES Products (id) ON UPDATE CASCADE ON DELETE CASCADE;


CREATE TABLE IF NOT EXISTS BlackList (
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  word varchar(255) NOT NULL
);

