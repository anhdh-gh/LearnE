CREATE TABLE Studyset
(
    Id          varchar(255) NOT NULL,
    Title       varchar(255) NOT NULL,
    Description text         NOT NULL,
    CreateTime  timestamp,
    UpdateTime  timestamp
);
CREATE TABLE WordCard
(
    Id         varchar(255) NOT NULL,
    StudysetId varchar(255) NOT NULL,
    "Key"      text         NOT NULL,
    Value      text         NOT NULL,
    CreateTime timestamp,
    UpdateTime timestamp,
    Image      varchar(255)
);
CREATE TABLE Examination
(
    Id         varchar(255) NOT NULL,
    StudysetId varchar(255) NOT NULL,
    CreateTime timestamp,
    UpdateTime timestamp,
    UserId     varchar(255) NOT NULL,
    Score      float4       NOT NULL
);

ALTER TABLE Studyset
    ADD PRIMARY KEY (Id);
ALTER TABLE WordCard
    ADD PRIMARY KEY (Id);
ALTER TABLE Examination
    ADD PRIMARY KEY (Id);

ALTER TABLE WordCard
    ADD CONSTRAINT FKWordCard380497 FOREIGN KEY (StudysetId) REFERENCES Studyset (Id) ON UPDATE Cascade ON DELETE Cascade;
ALTER TABLE Examination
    ADD CONSTRAINT FKExaminatio35894 FOREIGN KEY (StudysetId) REFERENCES Studyset (Id) ON UPDATE Cascade ON DELETE Cascade;
