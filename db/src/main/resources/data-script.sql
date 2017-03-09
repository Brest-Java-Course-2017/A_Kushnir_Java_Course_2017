INSERT INTO journalists (id_journalist, name_journalist, birth_date, rating)
VALUES
  (1, 'Walter Cronkite', '1916-11-04', 100),
  (2, 'Peter Jennings', '1938-07-29', 99),
  (3, 'Kate Adie', '1945-09-19', 80),
  (4, 'Anna Politkovskaya', '1958-08-30', 100);

INSERT INTO articles (id_article, naim, create_date, popularity, id_journalist)
VALUES
  (1, 'Chechnya. BUSINESS ON "EIGHTH MARCH"', '2005-12-05', 100, 4),
  (2, 'THE RALLY IS AGAINST CORRUPTION', '2006-05-18', 100, 4),
  (3, 'Moose Jaw, U.S.A.? Never! Jamais!', '1990-06-25', 90, 2),
  (4, 'TV opportunity for service at Geneva', '1985-11-28', 80, 2);