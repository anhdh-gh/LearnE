create table bookings
(
    booking_id  bigint      not null,
    create_time datetime(6) not null,
    update_time datetime(6) not null,
    primary key (booking_id)
);
