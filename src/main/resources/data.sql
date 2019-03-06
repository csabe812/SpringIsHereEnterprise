insert into Shop (name) values ('Walmarkt');
insert into Shop (name) values ('Auchan');
insert into Product(name, price, description, shop_id) values ('Absinthe', 1000, '80%', (select id from shop where name = 'Walmarkt'));
insert into Product(name, price, description, shop_id) values ('vodka', 200, '40%', (select id from shop where name = 'Walmarkt'));
insert into Product(name, price, description, shop_id) values ('Absinthe', 555, '80%', (select id from shop where name = 'Auchan'));
insert into Product(name, price, description, shop_id) values ('Kalinka', 525, '35%', (select id from shop where name = 'Auchan'));