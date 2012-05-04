# gnarus_runner schema
 
# --- !Ups
 
CREATE TABLE Attempt (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    exercise_id bigint(20) NOT NULL,
    query text NOT NULL,
    user_id bigint(20),
    correct bit(1),
    returnUri varchar(255),
    description text,
    PRIMARY KEY (id)
);
 
# --- !Downs
 
DROP TABLE Attempt;