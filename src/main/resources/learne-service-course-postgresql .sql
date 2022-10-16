CREATE TABLE Chapter
(
    Id         BIGSERIAL    NOT NULL,
    CourseId   varchar(255) NOT NULL,
    Name       varchar(255) NOT NULL,
    CreateTime timestamp(7),
    UpdateTime timestamp(7)
);
CREATE TABLE Lesson
(
    Id          BIGSERIAL    NOT NULL,
    ChapterId   int8         NOT NULL,
    Name        varchar(255) NOT NULL,
    Duration    varchar(255) NOT NULL,
    Description text,
    Video       varchar(255) NOT NULL,
    CreateTime  timestamp(7),
    UpdateTime  timestamp(7)
);
CREATE TABLE LessonExerciseStatus
(
    Id               varchar(255) NOT NULL,
    LessonExerciseId int8         NOT NULL,
    LessonId         int8         NOT NULL,
    UserId           varchar(255) NOT NULL,
    Status           varchar(255) NOT NULL,
    CreateTime       timestamp(7),
    UpdateTime       timestamp(7)
);
CREATE TABLE LessonExercise
(
    Id          BIGSERIAL    NOT NULL,
    LessonId    int8         NOT NULL,
    Name        varchar(255) NOT NULL,
    Description text,
    CreateTime  timestamp(7),
    UpdateTime  timestamp(7)
);
CREATE TABLE LessonStatus
(
    Id         varchar(255) NOT NULL,
    LessonId   int8         NOT NULL,
    UserId     varchar(255) NOT NULL,
    Status     varchar(255) NOT NULL,
    CreateTime timestamp(7),
    UpdateTime timestamp(7)
);
CREATE TABLE AnswerChoice
(
    Id                      varchar(255) NOT NULL,
    LessonQuestionHistoryId varchar(255) NOT NULL,
    AnswerId                varchar(255) NOT NULL,
    CreateTime              timestamp(7),
    UpdateTime              timestamp(7)
);
CREATE TABLE LessonQuestion
(
    Id               BIGSERIAL    NOT NULL,
    LessonExerciseId int8         NOT NULL,
    CreateTime       timestamp(7),
    UpdateTime       timestamp(7),
    QuestionId       varchar(255) NOT NULL,
    Score            float4       NOT NULL
);
CREATE TABLE Request
(
    Id         varchar(255) NOT NULL,
    CourseId   varchar(255) NOT NULL,
    Text       text         NOT NULL,
    CreateTime timestamp(7),
    UpdateTime timestamp(7)
);
CREATE TABLE Target
(
    Id         varchar(255) NOT NULL,
    CourseId   varchar(255) NOT NULL,
    Text       text         NOT NULL,
    CreateTime timestamp(7),
    UpdateTime timestamp(7)
);
CREATE TABLE LessonQuestionHistory
(
    Id               varchar(255) NOT NULL,
    LessonQuestionId int8         NOT NULL,
    UserId           varchar(255) NOT NULL,
    CreateTime       timestamp(7),
    UpdateTime       timestamp(7),
    Score            float4       NOT NULL
);
CREATE TABLE Course
(
    Id          varchar(255) NOT NULL,
    Name        varchar(255) NOT NULL,
    Description text,
    Author      varchar(255),
    Image       varchar(255),
    Level       varchar(255),
    Price       varchar(255),
    CreateTime  timestamp(7),
    UpdateTime  timestamp(7)
);

ALTER TABLE Chapter
    ADD PRIMARY KEY (Id);
ALTER TABLE Lesson
    ADD PRIMARY KEY (Id);
ALTER TABLE LessonExerciseStatus
    ADD PRIMARY KEY (Id);
ALTER TABLE LessonExercise
    ADD PRIMARY KEY (Id);
ALTER TABLE LessonStatus
    ADD PRIMARY KEY (Id);
ALTER TABLE AnswerChoice
    ADD PRIMARY KEY (Id);
ALTER TABLE LessonQuestion
    ADD PRIMARY KEY (Id);
ALTER TABLE Request
    ADD PRIMARY KEY (Id);
ALTER TABLE Target
    ADD PRIMARY KEY (Id);
ALTER TABLE LessonQuestionHistory
    ADD PRIMARY KEY (Id);
ALTER TABLE Course
    ADD PRIMARY KEY (Id);

ALTER TABLE Lesson
    ADD CONSTRAINT FKLesson123481 FOREIGN KEY (ChapterId) REFERENCES Chapter (Id) ON UPDATE Cascade ON DELETE Cascade;
ALTER TABLE LessonExerciseStatus
    ADD CONSTRAINT FKLessonExer936762 FOREIGN KEY (LessonId) REFERENCES Lesson (Id);
ALTER TABLE LessonExercise
    ADD CONSTRAINT FKLessonExer910564 FOREIGN KEY (LessonId) REFERENCES Lesson (Id) ON UPDATE Cascade ON DELETE Cascade;
ALTER TABLE LessonExerciseStatus
    ADD CONSTRAINT FKLessonExer257807 FOREIGN KEY (LessonExerciseId) REFERENCES LessonExercise (Id) ON UPDATE Cascade ON DELETE Cascade;
ALTER TABLE LessonStatus
    ADD CONSTRAINT FKLessonStat434395 FOREIGN KEY (LessonId) REFERENCES Lesson (Id) ON UPDATE Cascade ON DELETE Cascade;
ALTER TABLE LessonQuestion
    ADD CONSTRAINT FKLessonQues641174 FOREIGN KEY (LessonExerciseId) REFERENCES LessonExercise (Id) ON UPDATE Cascade ON DELETE Cascade;
ALTER TABLE LessonQuestionHistory
    ADD CONSTRAINT FKLessonQues214875 FOREIGN KEY (LessonQuestionId) REFERENCES LessonQuestion (Id) ON UPDATE Cascade ON DELETE Cascade;
ALTER TABLE AnswerChoice
    ADD CONSTRAINT FKAnswerChoi188174 FOREIGN KEY (LessonQuestionHistoryId) REFERENCES LessonQuestionHistory (Id) ON UPDATE Cascade ON DELETE Cascade;
ALTER TABLE Chapter
    ADD CONSTRAINT FKChapter746961 FOREIGN KEY (CourseId) REFERENCES Course (Id) ON UPDATE Cascade ON DELETE Cascade;
ALTER TABLE Request
    ADD CONSTRAINT FKRequest930580 FOREIGN KEY (CourseId) REFERENCES Course (Id) ON UPDATE Cascade ON DELETE Cascade;
ALTER TABLE Target
    ADD CONSTRAINT FKTarget487279 FOREIGN KEY (CourseId) REFERENCES Course (Id) ON UPDATE Cascade ON DELETE Cascade;
