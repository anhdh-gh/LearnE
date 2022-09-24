CREATE TABLE Question (
  Id           varchar(255) NOT NULL, 
  QuestionType varchar(255) NOT NULL, 
  Text         varchar(255) NOT NULL, 
  Image        varchar(255), 
  Audio        varchar(255), 
  UpdateTime   date, 
  CreateTime   date, 
  PRIMARY KEY (Id)) ENGINE=InnoDB CHARACTER SET UTF8;
CREATE TABLE Answer (
  Id         varchar(255) NOT NULL, 
  QuestionId varchar(255) NOT NULL, 
  Text       varchar(255) NOT NULL, 
  UpdateTime date, 
  CreateTime date, 
  PRIMARY KEY (Id)) ENGINE=InnoDB CHARACTER SET UTF8;
ALTER TABLE Answer ADD CONSTRAINT FKAnswer406451 FOREIGN KEY (QuestionId) REFERENCES Question (Id);
