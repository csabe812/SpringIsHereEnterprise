insert into Shop (id, name) values (1,'Walmarkt');
insert into Product(id, name, price, description, shop_id) values (1,'Absinthe', 1000, '80%', (select id from shop where name = 'Walmarkt'));
insert into Product(id, name, price, description, shop_id) values (2,'vodka', 200, '40%', (select id from shop where name = 'Walmarkt'));
insert into Shop (id, name) values (2,'Auchan');
insert into Product(id, name, price, description, shop_id) values (3,'Absinthe', 555, '80%', (select id from shop where name = 'Auchan'));
insert into Product(id, name, price, description, shop_id) values (4,'Kalinka', 525, '35%', (select id from shop where name = 'Auchan'));