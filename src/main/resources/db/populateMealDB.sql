DELETE FROM meals;
ALTER SEQUENCE meal_seq RESTART WITH 100;

INSERT INTO meals (date_time, description, calories, user_id) VALUES
  ('2018-01-28 01:00:00', 'Сheeseburger', 1200, 100000),
  ('2018-01-28 09:30:00', 'Сorn porridge', 400, 100000),
  ('2018-01-28 14:07:00', 'French fries', 560, 100000),
  ('2018-02-01 08:30:00', 'Cabbage rolls', 750, 100000),
  ('2018-02-01 16:40:00', 'Casserole', 700, 100000),
  ('2018-02-01 19:00:00', 'Fish and chips', 800, 100000),
  ('2018-03-03 01:00:00', 'Kefir', 140, 100001),
  ('2018-03-03 08:05:00', 'Roll', 270, 100001),
  ('2018-03-03 11:45:00', 'Fried fish', 420, 100001),
  ('2018-04-12 21:00:00', 'Swiss roll', 620, 100001);