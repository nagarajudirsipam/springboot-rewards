create table customer(customer_id INT PRIMARY KEY, customer_name VARCHAR(50) NOT NULL);

create table transaction(transaction_id INT PRIMARY KEY, 
	description VARCHAR(250) NOT NULL, 
	total DOUBLE NOT NULL, 
	save_date timestamp NOT NULL, 
	customer_id INT NOT NULL,
	foreign key (customer_id) references customer(customer_id));