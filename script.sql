------------------------------------------------------------
--        Script Postgre 
------------------------------------------------------------



------------------------------------------------------------
-- Table: user
------------------------------------------------------------
CREATE TABLE project.user(
    username   VARCHAR (50) NOT NULL ,
    password   VARCHAR (50) NOT NULL  ,
    CONSTRAINT user_PK PRIMARY KEY (username)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: data
------------------------------------------------------------
CREATE TABLE project.data(
    path       VARCHAR (50) NOT NULL ,
    type       VARCHAR (50) NOT NULL ,
    username   VARCHAR (50) NOT NULL  ,
    CONSTRAINT data_PK PRIMARY KEY (path)

    ,CONSTRAINT data_user_FK FOREIGN KEY (username) REFERENCES project.user(username)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: peuEcrire
------------------------------------------------------------
CREATE TABLE project.peuEcrire(
    path       VARCHAR (50) NOT NULL ,
    username   VARCHAR (50) NOT NULL  ,
    CONSTRAINT peuEcrire_PK PRIMARY KEY (path,username)

    ,CONSTRAINT peuEcrire_data_FK FOREIGN KEY (path) REFERENCES project.data(path)
    ,CONSTRAINT peuEcrire_user0_FK FOREIGN KEY (username) REFERENCES project.user(username)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: peuLire
------------------------------------------------------------
CREATE TABLE project.peuLire(
    path       VARCHAR (50) NOT NULL ,
    username   VARCHAR (50) NOT NULL  ,
    CONSTRAINT peuLire_PK PRIMARY KEY (path,username)

    ,CONSTRAINT peuLire_data_FK FOREIGN KEY (path) REFERENCES project.data(path)
    ,CONSTRAINT peuLire_user0_FK FOREIGN KEY (username) REFERENCES project.user(username)
)WITHOUT OIDS;