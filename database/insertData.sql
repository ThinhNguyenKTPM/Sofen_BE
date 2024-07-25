set
search_path to sofendb;
select *
from hotel_address ha
where ha.nation ilike '%Việt%';

--hotel_image
delete
from hotel_images hi
where hi.hotel_image_id >= 8;

--hotel_address
delete
from hotel_address ha
where ha.hotel_address_id = 2;
--hotel_contact
delete
from hotel_contacts hc
where hc.hotel_contact_id = 2;
INSERT INTO INSERT INTO sofendb.hotels (latitude,longitude,hotel_address_id,hotel_contact_id,hotel_id,"name",address_detail,description,status)
VALUES
    (0.0, 0.0, 1, 1, '0962b1ef-bd1b-445e-aa3c-cb01ac861b1d', 'The Sofen Ninh Van Bay', 'Ninh Vân Bay', NULL, 'ACTIVE'),
    (0.0, 0.0, 2, 2, 'c39e433a-c6cb-46c1-a14d-8500ae7dbd46', 'The Sofen Can Tho Riverside Hotel', '1 Hoa Binh', NULL, 'ACTIVE'),
    (0.0, 0.0, 3, 3, '9634b099-c25f-4eaa-8402-3ff047013553', 'The Sofen Con Dao Hotel', '08 Tôn Đức Thắng', NULL, 'ACTIVE');
INSERT INTO sofendb.hotel_address (hotel_address_id, district, nation, province, ward)
VALUES (1, 'Ninh Hòa', 'Việt Nam', 'Khánh Hòa', 'Ninh Vân'),
       (2, 'Ninh Kiều', 'Việt Nam', 'Cần Thơ', 'Xuân Khánh'),
       (3, 'Côn Đảo (Hòn Sơn)', 'Việt Nam', 'Bà Rịa - Vũng Tàu', '');
INSERT INTO sofendb.hotel_contacts (phone_number_code, hotel_contact_id, email, facebook, instagram, phone_number)
VALUES (84, 1, 'TheSofenNinhVanBay@gmail.com', 'facebook.com/the-sofen-nv', 'instagram.com/the-sofen-nv', '123456787'),
       (84, 2, 'TheSofenCanTho@gmail.com', 'facebook.com/the-sofen-ct', 'instagram.com/the-sofen-ct', '123456789'),
       (84, 3, 'TheSofenConDao@gmail.com', 'facebook.com/the-sofen-cd', 'instagram.com/the-sofen-cd', '');

-- Add policy into hotel
insert into hotels_hotel_policies (hotel_policy_id, hotel_id)
values (1, '0962b1ef-bd1b-445e-aa3c-cb01ac861b1d'),
       (2, '0962b1ef-bd1b-445e-aa3c-cb01ac861b1d'),
       (3, '0962b1ef-bd1b-445e-aa3c-cb01ac861b1d'),
       (4, '0962b1ef-bd1b-445e-aa3c-cb01ac861b1d'),
       (5, '0962b1ef-bd1b-445e-aa3c-cb01ac861b1d'),
       (1, 'c39e433a-c6cb-46c1-a14d-8500ae7dbd46'),
       (2, 'c39e433a-c6cb-46c1-a14d-8500ae7dbd46'),
       (3, 'c39e433a-c6cb-46c1-a14d-8500ae7dbd46'),
       (4, 'c39e433a-c6cb-46c1-a14d-8500ae7dbd46'),
       (5, 'c39e433a-c6cb-46c1-a14d-8500ae7dbd46'),
       (1, '9634b099-c25f-4eaa-8402-3ff047013553'),
       (2, '9634b099-c25f-4eaa-8402-3ff047013553'),
       (3, '9634b099-c25f-4eaa-8402-3ff047013553'),
       (4, '9634b099-c25f-4eaa-8402-3ff047013553'),
       (5, '9634b099-c25f-4eaa-8402-3ff047013553');

insert into hotels_hotel_services (hotel_service_id, hotel_id)
values (1, '0962b1ef-bd1b-445e-aa3c-cb01ac861b1d'),
       (2, '0962b1ef-bd1b-445e-aa3c-cb01ac861b1d'),
       (3, '0962b1ef-bd1b-445e-aa3c-cb01ac861b1d'),
       (4, '0962b1ef-bd1b-445e-aa3c-cb01ac861b1d'),
       (5, '0962b1ef-bd1b-445e-aa3c-cb01ac861b1d'),
       (1, 'c39e433a-c6cb-46c1-a14d-8500ae7dbd46'),
       (2, 'c39e433a-c6cb-46c1-a14d-8500ae7dbd46'),
       (3, 'c39e433a-c6cb-46c1-a14d-8500ae7dbd46'),
       (4, 'c39e433a-c6cb-46c1-a14d-8500ae7dbd46'),
       (5, 'c39e433a-c6cb-46c1-a14d-8500ae7dbd46'),
       (1, '9634b099-c25f-4eaa-8402-3ff047013553'),
       (2, '9634b099-c25f-4eaa-8402-3ff047013553'),
       (3, '9634b099-c25f-4eaa-8402-3ff047013553'),
       (4, '9634b099-c25f-4eaa-8402-3ff047013553'),


-- create room
insert into rooms (room_id, hotel_id, room_type_id, amount, quantity_remaining)
values
    (1, '0962b1ef-bd1b-445e-aa3c-cb01ac861b1d', 2, 20, 20 ), (2, '0962b1ef-bd1b-445e-aa3c-cb01ac861b1d', 3, 30, 20 ), (3, '0962b1ef-bd1b-445e-aa3c-cb01ac861b1d', 4, 40, 20 ), (4, '0962b1ef-bd1b-445e-aa3c-cb01ac861b1d', 5, 50, 20 ), (5, '0962b1ef-bd1b-445e-aa3c-cb01ac861b1d', 6, 60, 20 ), (6, 'c39e433a-c6cb-46c1-a14d-8500ae7dbd46', 6, 20, 20 ), (7, 'c39e433a-c6cb-46c1-a14d-8500ae7dbd46', 2, 30, 20 ), (8, 'c39e433a-c6cb-46c1-a14d-8500ae7dbd46', 3, 40, 20 ), (9, 'c39e433a-c6cb-46c1-a14d-8500ae7dbd46', 4, 50, 20 ), (10, 'c39e433a-c6cb-46c1-a14d-8500ae7dbd46', 5, 60, 20 ), (11, '9634b099-c25f-4eaa-8402-3ff047013553', 6, 20, 20 ), (12, '9634b099-c25f-4eaa-8402-3ff047013553', 2, 30, 20 ), (13, '9634b099-c25f-4eaa-8402-3ff047013553', 3, 40, 20 ), (14, '9634b099-c25f-4eaa-8402-3ff047013553', 4, 50, 20 ), (15, '9634b099-c25f-4eaa-8402-3ff047013553', 5, 60, 20 );

DO
$$
DECLARE
room_id bigint;
   room_furniture_id
bigint;
BEGIN
FOR room_id IN 1..15 LOOP
      FOR room_furniture_id IN 1..18 LOOP
         EXECUTE format('INSERT INTO room_furnitures_rooms (room_furniture_id, room_id) VALUES (%L, %L)', room_furniture_id, room_id);
END LOOP;
END LOOP;
END $$;


select rt."name", r.room_id, count(*)
from hotels h
         inner join rooms r on r.hotel_id = h.hotel_id
         inner join room_types rt on rt.room_type_id = r.room_type_id
         inner join booking_detail_rooms bdr on r.room_id = bdr.room_id
         inner join booking_details bd on bdr.booking_detail_id = bd.booking_detail_id
where h.hotel_id = '0962b1ef-bd1b-445e-aa3c-cb01ac861b1d'
  and bd.check_in <= '2024-03-21'
  and '2024-03-25' <= bd.check_out
group by rt."name", r.room_id;

select uuid_generate_v4();



INSERT INTO sofendb.roles ("role")
VALUES ('ROLE_USER'),
       ('ROLE_MANAGER_MASTER'),
       ('ROLE_MANAGER'),
       ('ROLE_ADMIN_MASTER'),
       ('ROLE_ADMIN'),
       ('ROLE_EMPLOYEE');

INSERT INTO sofendb.user_ranking (reward_percent, user_ranking_id, "name")
VALUES (10, 1, 'Bronze'),
       (20, 2, 'Silver'),
       (30, 3, 'Gold'),
       (40, 4, 'Platinum');

CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";



INSERT INTO sofendb.users (is_women, point, created_at, date_of_birth, role_id, user_ranking_id, user_id, address,
                           email, full_name, "password", phone_number, status)
VALUES (false, 100, CURRENT_TIMESTAMP, '2000-01-01', 1, 1, 'e7693c6d-03a6-4c33-b967-ade52f5faba6', '123 Street, City',
        'user1@example.com', 'User 1', 'hashed_password', '123456789', 'ACTIVE'),
       (false, 150, CURRENT_TIMESTAMP, '1995-05-05', 1, 1, 'd110fca9-b4f2-400c-85e6-79d32dd26963', '456 Avenue, Town',
        'user2@example.com', 'User 2', 'hashed_password', '987654321', 'ACTIVE'),
       (true, 200, CURRENT_TIMESTAMP, '1990-10-10', 1, 1, 'd110fca9-b4f2-400c-85e6-79d32dd26964', '789 Road, Village',
        'user3@example.com', 'User 3', 'hashed_password', '555555555', 'ACTIVE');


INSERT INTO sofendb.bookings (number_of_guests, booking_date, guest_id, total_price, booking_id, payment_id, user_id)
VALUES (2, '2024-03-18 09:00:00', null, 2000000, 'f47ac10b-58cc-4372-a567-0e02b2c3d479', null,
        'e7693c6d-03a6-4c33-b967-ade52f5faba6'),
       (1, '2024-03-19 10:00:00', null, 1500000, 'f47ac10b-58cc-4372-a567-0e02b2c3d481', null,
        'e7693c6d-03a6-4c33-b967-ade52f5faba6'),
       (4, '2024-03-20 11:00:00', null, 3500000, 'f47ac10b-58cc-4372-a567-0e02b2c3d483', null,
        'e7693c6d-03a6-4c33-b967-ade52f5faba6'),
       (3, '2024-03-21 12:00:00', null, 2800000, 'f47ac10b-58cc-4372-a567-0e02b2c3d485', null,
        'e7693c6d-03a6-4c33-b967-ade52f5faba6'),
       (2, '2024-03-22 13:00:00', null, 2100000, 'f47ac10b-58cc-4372-a567-0e02b2c3d487', null,
        'e7693c6d-03a6-4c33-b967-ade52f5faba6');


insert into booking_details ()

insert into sofendb.booking_details(booking_detail_id, booking_id, number_of_rooms, price, check_in, check_out, booking_status)
values
    ('cabf2d5b-6c1e-434f-85ab-d29309fe2c54', 'f47ac10b-58cc-4372-a567-0e02b2c3d479', 1, 2000000, '2021-03-21', '2021-03-21', 'CONFIRMED'), ('cabf2d5b-6c1e-434f-85ab-d29309fe2c55', 'f47ac10b-58cc-4372-a567-0e02b2c3d481', 1, 3000000, '2021-03-21', '2021-03-25', 'CONFIRMED'), ('cabf2d5b-6c1e-434f-85ab-d29309fe2c56', 'f47ac10b-58cc-4372-a567-0e02b2c3d483', 1, 4000000, '2021-03-21', '2021-03-24', 'CONFIRMED'), ('cabf2d5b-6c1e-434f-85ab-d29309fe2c57', 'f47ac10b-58cc-4372-a567-0e02b2c3d485', 1, 5000000, '2021-03-21', '2021-03-23', 'CONFIRMED'), ('cabf2d5b-6c1e-434f-85ab-d29309fe2c58', 'f47ac10b-58cc-4372-a567-0e02b2c3d487', 1, 6000000, '2021-03-21', '2021-03-22', 'CONFIRMED')
;
insert into sofendb.booking_detail_rooms (booking_detail_id, room_id)
values ('cabf2d5b-6c1e-434f-85ab-d29309fe2c54', 1),
       ('cabf2d5b-6c1e-434f-85ab-d29309fe2c55', 2),
       ('cabf2d5b-6c1e-434f-85ab-d29309fe2c56', 3),
       ('cabf2d5b-6c1e-434f-85ab-d29309fe2c57', 4),
       ('cabf2d5b-6c1e-434f-85ab-d29309fe2c58', 5);























