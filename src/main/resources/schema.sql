create database ecommerce

create table customer{
   cust_id int primary key;
   name varchar;
   address varchar;
   contact varchar;
   order_ids blob foreign key;
   date_ordered
}

create table orders{
  order_id int primary key;
  cust_id int foreign key;
  product_ids blob foreign key;
  deliever_date date
}

create table product{
   prod_id int primary key;
   prod_name varchar;
   sell_price int;
   description varchar;


}