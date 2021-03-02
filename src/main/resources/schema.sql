create database ecommerce

create table customer(
   cust_id uuid primary key ,--customer id
   f_name varchar(100) , -- name of the customer
   l_name varchar(100),
   mobile_no varchar(10),
   email_id varchar(100)  ,
   address varchar(100), -- address of the customer
   date_of_birth varchar(100),
   date_created timestamp default current_timestamp,
   date_last_modified timestamp default current_timestamp
);
create table orders(
  order_id uuid primary key,--order id
  cust_id uuid , --customer id
  order_name varchar(100) unique,
  order_time timestamp default current_timestamp ,-- time at which order is placed
  total_price float,
  quantity varchar(100), --units of product ordered
  product_ids varchar(100), -- list of product ids
  foreign key (cust_id) references customer(cust_id)
);

create table product(
   prod_id uuid primary key,-- product id
   prod_name varchar(100) , -- product name
   sell_price int ,-- selling price of the product
   description varchar(100), --short description about the product
   type varchar (100) ,
   quantity int -- total units available

);
create index idx1 -- indexing for name column in customer table
on customer(name);

create index idx2 -- indexing for product and sell_price column in product
on product(prod_name,sell_price);