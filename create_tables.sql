
CREATE TABLE public.tracker_user (
  user_id SERIAL PRIMARY KEY,
  username text unique not null ,
  first_name text,
  last_name text,
  email VARCHAR(255) NOT NULL,
  password text not null
);

INSERT INTO tracker_user (first_name,last_name,email)
VALUES ('admin','test','example@gmail.com');

INSERT INTO tracker_user (first_name,last_name,email)
VALUES ('just','user','just_user@gmail.com');