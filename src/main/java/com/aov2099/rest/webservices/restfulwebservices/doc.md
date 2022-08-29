* Create controller
* Create Bean
* Create Dao
* Create rest controller (Resourse), autowire

###Table structure
create table user(
    id integer not null,
    birth_date timestamp,
    name varchar(255),
    primary key (id)
)

jdbc:h2Lmem:testdb