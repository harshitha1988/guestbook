
insert into guestuser(name, created_on, updated_on)
values('Ranga', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

insert into feedback(guest_id, comments, likes, created_on, updated_on)
values((select guest_id from guestuser where name = 'Ranga'),'Movie was ok', 0 ,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into feedback(guest_id, comments, likes, created_on, updated_on)
values((select guest_id from guestuser where name = 'Ranga'),'Drink was ok', 0 ,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into guestuser(name, created_on, updated_on)
values('Georg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

insert into feedback(guest_id, comments, likes, created_on, updated_on)
values((select guest_id from guestuser where name = 'Georg'),'Drink was ok', 5 ,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


