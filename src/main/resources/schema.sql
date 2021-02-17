create database ecommerce

create table customer(
   cust_id int primary key,--customer id
   name varchar(100), -- name of the customer
   address varchar(100), -- address of the customer
   contact varchar(100) -- phone number of the customer


);
create table orders(
  order_id int primary key,--order id
  cust_id int foreign key, --customer id
  prod_id int foreign key, --product id
  order_time timestamp default current_timestamp -- time at which order is placed
);

create table product(
   prod_id int primary key,-- product id
   prod_name varchar(100), -- product name
   sell_price int,-- selling price of the product
   description varchar(100) --short description about the product


);
create index idx1 -- indexing for name column in customer table
on customer(name);

create index idx2 -- indexing for product and sell_price column in product
on product(prod_name,sell_price);