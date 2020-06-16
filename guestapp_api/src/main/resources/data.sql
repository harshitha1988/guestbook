
insert into guestuser(name, created_on, updated_on)
values('Rachel', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

insert into feedback(guest_id, comments, likes, unlikes, created_on, updated_on)
values((select guest_id from guestuser where name = 'Rachel'),'This is indeed a beautiful film and a must WATCH!!', 4 , 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into feedback(guest_id, comments, likes, unlikes, created_on, updated_on)
values((select guest_id from guestuser where name = 'Rachel'),'but a little dragging script ', 0 , 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into guestuser(name, created_on, updated_on)
values('Tom', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP );

insert into feedback(guest_id, comments, likes, unlikes, created_on, updated_on)
values((select guest_id from guestuser where name = 'Tom'),'Really disappointing and extremely boring Movie', 5 , 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


