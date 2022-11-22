CREATE TABLE Question
(
    Id         varchar(255)  NOT NULL,
    Text       varchar(255)  NOT NULL,
    UpdateTime timestamp(7),
    CreateTime timestamp(7),
    Time       int4,
    Pdf        varchar(1000) NOT NULL,
    PRIMARY KEY (Id)
);
CREATE TABLE Answer
(
    Id         int8         NOT NULL,
    QuestionId varchar(255) NOT NULL,
    Text       text         NOT NULL,
    UpdateTime timestamp(7),
    CreateTime timestamp(7),
    Audio      varchar(1000),
    PRIMARY KEY (Id)
);
CREATE TABLE TestResult
(
    Id             varchar(255) NOT NULL,
    CompletionTime int8         NOT NULL,
    Score          float4       NOT NULL,
    UserId         varchar(255) NOT NULL,
    UpdateTime     timestamp(7),
    CreateTime     timestamp(7),
    QuestionId     varchar(255) NOT NULL,
    PRIMARY KEY (Id)
);
ALTER TABLE Answer
    ADD CONSTRAINT FKAnswer406451 FOREIGN KEY (QuestionId) REFERENCES Question (Id) ON UPDATE Cascade ON DELETE Cascade;
ALTER TABLE TestResult
    ADD CONSTRAINT FKTestResult342670 FOREIGN KEY (QuestionId) REFERENCES Question (Id) ON UPDATE Cascade ON DELETE Cascade;