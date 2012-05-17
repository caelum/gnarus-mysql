# gnarus_runner schema
 
# --- !Ups
alter table Exercise add column setupQuery text NOT NULL
  
# --- !Downs

alter table Exercise drop column setupQuery