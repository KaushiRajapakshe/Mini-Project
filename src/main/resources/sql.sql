create database mini;

# category table
create table mini.category(
	category_id int primary key auto_increment,
	category_name varchar(50) not null,
    active boolean not null,
	added_date timestamp DEFAULT NOW(),
	updated_date timestamp
);

# procuct table
create table mini.product(
	product_id int primary key auto_increment,
	category_id int not null,
	product_name varchar(50) not null,
	product_info_detail varchar(50) not null,
	product_info_count double not null,
	product_count int,
	product_price double,
    active boolean not null,
	added_date timestamp DEFAULT NOW(),
	updated_date timestamp,
    constraint fk_category_id foreign key (category_id) references category(category_id)
);

# cart table
create table mini.cart(
	cart_id int primary key auto_increment,
    cart_name varchar(50) not null,
    active boolean not null,
	added_date timestamp DEFAULT NOW(),
	updated_date timestamp
);

# cart_product table
create table mini.cart_product(
	cart_id int not null,
    product_id int not null,
    product_count int not null,
	product_total_price double not null,
    active boolean not null,
    constraint pk_cart_product primary key (cart_id, product_id),
	constraint fk_card_id foreign key (cart_id) references cart(cart_id),
    constraint fk_product_id foreign key (product_id) references product(product_id)
);

# order table
create table mini.order_m(
	order_id int primary key auto_increment,
    user_id int not null,
    order_date timestamp not null,
    required_date timestamp not null,
    shipped_date timestamp not null,
    status varchar(50) not null,
    active boolean not null,
    constraint fk__order_user_id foreign key (user_id) references user(user_id)
);

# user table
create table mini.user(
	user_id int primary key auto_increment,
    user_name varchar(50) not null,
    password varchar(100),
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    date_of_birth timestamp not null,
    gender varchar(15) not null,
    contact_number varchar(50) not null,
    email varchar(50) not null,
    address_1 varchar(100) not null,
    address_2 varchar(100),
    city varchar(100) not null,
    state varchar(100) not null,
    postal_code varchar(100) not null,
    country varchar(100) not null,
    added_date timestamp DEFAULT NOW(),
    updated_date timestamp
);

# payment table
create table mini.payment(
	payment_id int primary key auto_increment,
    user_id int not null,
    amount double not null,
    payment_date timestamp not null,
    constraint fk_user_id foreign key (user_id) references user(user_id)
);

