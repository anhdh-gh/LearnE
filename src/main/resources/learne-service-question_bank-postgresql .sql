CREATE TABLE question
(
    Id           varchar(255) NOT NULL,
    QuestionType varchar(255) NOT NULL,
    Header       text,
    Text         text         NOT NULL,
    GroupId      varchar(255) NOT NULL,
    Image        varchar(2500),
    Audio        varchar(2500),
    UpdateTime   timestamp(7),
    CreateTime   timestamp(7)
);
CREATE TABLE answer
(
    Id         varchar(255) NOT NULL,
    QuestionId varchar(255) NOT NULL,
    IsCorrect  bool         NOT NULL,
    Text       text         NOT NULL,
    UpdateTime timestamp(7),
    CreateTime timestamp(7)
);

ALTER TABLE question
    ADD PRIMARY KEY (Id);
ALTER TABLE answer
    ADD PRIMARY KEY (Id);

ALTER TABLE answer
    ADD CONSTRAINT FKAnswer406451 FOREIGN KEY (QuestionId) REFERENCES question (Id) ON UPDATE Cascade ON DELETE Cascade;

