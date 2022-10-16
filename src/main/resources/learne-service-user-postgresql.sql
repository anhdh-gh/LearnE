CREATE TABLE "user"
(
    Id          varchar(255) NOT NULL,
    Role        varchar(255) NOT NULL,
    Gender      varchar(255),
    UserName    varchar(255) NOT NULL UNIQUE,
    DateOfBirth date,
    PhoneNumber varchar(255),
    Avatar      varchar(255),
    CreateTime  timestamp,
    UpdateTime  timestamp(7),
    FullNameId  varchar(255),
    AddressId   varchar(255),
    AccountId   varchar(255) NOT NULL,
    PRIMARY KEY (Id)
);
CREATE TABLE fullName
(
    Id         varchar(255) NOT NULL,
    FirstName  varchar(255),
    MidName    varchar(255),
    LastName   varchar(255),
    CreateTime timestamp(7),
    UpdateTime timestamp(7),
    PRIMARY KEY (Id)
);
CREATE TABLE address
(
    Id          varchar(255) NOT NULL,
    Nation      varchar(255),
    City        varchar(255),
    Province    varchar(255),
    District    varchar(255),
    Street      varchar(255),
    NumberHouse varchar(255),
    CreateTime  timestamp(7),
    UpdateTime  timestamp(7),
    PRIMARY KEY (Id)
);
CREATE TABLE account
(
    Id         varchar(255) NOT NULL,
    Email      varchar(255) NOT NULL UNIQUE,
    Password   varchar(255) NOT NULL,
    CreateTime timestamp,
    UpdateTime timestamp,
    PRIMARY KEY (Id)
);
ALTER TABLE "user"
    ADD CONSTRAINT FKUser568361 FOREIGN KEY (FullNameId) REFERENCES fullName (Id);
ALTER TABLE "user"
    ADD CONSTRAINT FKUser847479 FOREIGN KEY (AddressId) REFERENCES address (Id);
ALTER TABLE "user"
    ADD CONSTRAINT FKUser921429 FOREIGN KEY (AccountId) REFERENCES account (Id) ON UPDATE Cascade ON DELETE Cascade;