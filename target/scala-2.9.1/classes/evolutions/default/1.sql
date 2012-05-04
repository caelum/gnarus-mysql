# gnarus_runner schema
 
# --- !Ups
 
CREATE TABLE Exercise (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    content text NOT NULL,
    queryValidator text NOT NULL,
    expectedResult int,
    PRIMARY KEY (id)
);
 
# --- !Downs
 
DROP TABLE Exercise;