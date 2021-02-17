create database ecommerce

create table customer(
   cust_id int primary key,
   name varchar(100),
   address varchar(100),
   contact varchar(100)


);
create table orders(
  order_id int primary key,
  cust_id int foreign key,
  prod_id int foreign key,
  order_time timestamp default current_timestamp
);

create table product(
   prod_id int primary key,
   prod_name varchar(100),
   sell_price int,
   description varchar(100)


);
create index idx1
on customer(name);

create index idx2
on product(prod_name,sell_price);