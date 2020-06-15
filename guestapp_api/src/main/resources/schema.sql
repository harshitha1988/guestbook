create table guestuser (
 guest_id identity primary key,
 name varchar(255) not null,
 created_on TIMESTAMP,
 updated_on TIMESTAMP
);

create table feedback
(
  feedback_id identity primary key,
  guest_id int not null,
  comments varchar not null,
  likes integer ,
  unlikes integer,
  created_on TIMESTAMP,
  updated_on TIMESTAMP,
  foreign key (guest_id) references guestuser(guest_id)
  );

 