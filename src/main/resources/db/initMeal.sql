DROP TABLE IF EXISTS meals;
DROP SEQUENCE IF EXISTS meal_seq;
CREATE SEQUENCE meal_seq
  START 100;

CREATE TABLE meals
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('meal_seq'),
  date_time   TIMESTAMP               NOT NULL,
  description VARCHAR(50)             NOT NULL,
  calories    INTEGER DEFAULT 2000    NOT NULL,
  user_id     INTEGER                 NOT NULL,
  FOREIGN KEY (user_Id) REFERENCES users (id) ON DELETE CASCADE

);
CREATE UNIQUE INDEX meals_uniq_datatime
  ON meals (date_time);
