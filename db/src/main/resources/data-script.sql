INSERT INTO journalists (id_journalist, name_journalist, birth_date, rating)
VALUES
  (1, 'John', '1987-11-11', 10),
  (2, 'Larry', '1977-09-26', 90);

INSERT INTO articles (id_article, naim, create_date, pupularity, id_journalist)
VALUES
  (1, 'Some Article', '1999-03-01', 100, 1),
  (2, 'Millenium!', '1999-12-31', 100, 2);