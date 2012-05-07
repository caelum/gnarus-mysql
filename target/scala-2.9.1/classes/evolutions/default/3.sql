# gnarus_runner schema
 
# --- !Ups
 
CREATE TABLE SystemUser (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    login varchar(255),
    password varchar(255),
    PRIMARY KEY (id)
);

insert into SystemUser(login,password) values('gnarus','974ee4b4bf0f010fa1442a1d114646a0e02917e4') 
 
# --- !Downs
 
DROP TABLE SystemUser;