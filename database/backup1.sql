PGDMP  '    %                |            sofendb    16.1    16.1 �    -           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            .           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            /           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            0           1262    25045    sofendb    DATABASE     �   CREATE DATABASE sofendb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE sofendb;
                sofen    false                        2615    35332    sofendb    SCHEMA        CREATE SCHEMA sofendb;
    DROP SCHEMA sofendb;
                sofen    false                        3079    35333    unaccent 	   EXTENSION     =   CREATE EXTENSION IF NOT EXISTS unaccent WITH SCHEMA sofendb;
    DROP EXTENSION unaccent;
                   false    7            1           0    0    EXTENSION unaccent    COMMENT     P   COMMENT ON EXTENSION unaccent IS 'text search dictionary that removes accents';
                        false    2                        3079    35340 	   uuid-ossp 	   EXTENSION     @   CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA sofendb;
    DROP EXTENSION "uuid-ossp";
                   false    7            2           0    0    EXTENSION "uuid-ossp"    COMMENT     W   COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';
                        false    3            �            1259    35351    booking_detail_rooms    TABLE     p   CREATE TABLE sofendb.booking_detail_rooms (
    booking_detail_id uuid NOT NULL,
    room_id bigint NOT NULL
);
 )   DROP TABLE sofendb.booking_detail_rooms;
       sofendb         heap    sofen    false    7            �            1259    35354    booking_details    TABLE     �   CREATE TABLE sofendb.booking_details (
    number_of_rooms integer,
    check_in date,
    check_out date,
    price bigint,
    booking_detail_id uuid NOT NULL,
    booking_id uuid,
    booking_status character varying(255)
);
 $   DROP TABLE sofendb.booking_details;
       sofendb         heap    sofen    false    7            �            1259    35357    bookings    TABLE     �   CREATE TABLE sofendb.bookings (
    number_of_guests integer,
    booking_date timestamp(6) without time zone,
    guest_id bigint,
    total_price bigint,
    booking_id uuid NOT NULL,
    payment_id uuid,
    user_id uuid
);
    DROP TABLE sofendb.bookings;
       sofendb         heap    sofen    false    7            �            1259    35360    guests    TABLE       CREATE TABLE sofendb.guests (
    is_women boolean,
    date_of_birth timestamp(6) without time zone,
    guest_id bigint NOT NULL,
    email character varying(255),
    name character varying(255),
    phone character varying(255),
    purpose character varying(255)
);
    DROP TABLE sofendb.guests;
       sofendb         heap    sofen    false    7            �            1259    35365 
   guests_seq    SEQUENCE     u   CREATE SEQUENCE sofendb.guests_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE sofendb.guests_seq;
       sofendb          sofen    false    7            �            1259    35366    hotel_address    TABLE     �   CREATE TABLE sofendb.hotel_address (
    hotel_address_id bigint NOT NULL,
    district character varying(255),
    nation character varying(255),
    province character varying(255),
    ward character varying(255)
);
 "   DROP TABLE sofendb.hotel_address;
       sofendb         heap    sofen    false    7            �            1259    35371 "   hotel_address_hotel_address_id_seq    SEQUENCE     �   CREATE SEQUENCE sofendb.hotel_address_hotel_address_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 :   DROP SEQUENCE sofendb.hotel_address_hotel_address_id_seq;
       sofendb          sofen    false    7    222            3           0    0 "   hotel_address_hotel_address_id_seq    SEQUENCE OWNED BY     k   ALTER SEQUENCE sofendb.hotel_address_hotel_address_id_seq OWNED BY sofendb.hotel_address.hotel_address_id;
          sofendb          sofen    false    223            �            1259    35372    hotel_address_seq    SEQUENCE     |   CREATE SEQUENCE sofendb.hotel_address_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE sofendb.hotel_address_seq;
       sofendb          sofen    false    7            �            1259    35373    hotel_contacts    TABLE       CREATE TABLE sofendb.hotel_contacts (
    phone_number_code integer,
    hotel_contact_id bigint NOT NULL,
    email character varying(255),
    facebook character varying(255),
    instagram character varying(255),
    phone_number character varying(255)
);
 #   DROP TABLE sofendb.hotel_contacts;
       sofendb         heap    sofen    false    7            �            1259    35378 #   hotel_contacts_hotel_contact_id_seq    SEQUENCE     �   CREATE SEQUENCE sofendb.hotel_contacts_hotel_contact_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ;   DROP SEQUENCE sofendb.hotel_contacts_hotel_contact_id_seq;
       sofendb          sofen    false    7    225            4           0    0 #   hotel_contacts_hotel_contact_id_seq    SEQUENCE OWNED BY     m   ALTER SEQUENCE sofendb.hotel_contacts_hotel_contact_id_seq OWNED BY sofendb.hotel_contacts.hotel_contact_id;
          sofendb          sofen    false    226            �            1259    35379    hotel_contacts_seq    SEQUENCE     }   CREATE SEQUENCE sofendb.hotel_contacts_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE sofendb.hotel_contacts_seq;
       sofendb          sofen    false    7            �            1259    35380    hotel_images    TABLE     �   CREATE TABLE sofendb.hotel_images (
    is_main boolean,
    hotel_image_id bigint NOT NULL,
    hotel_id uuid,
    alt character varying(255),
    url character varying(255)
);
 !   DROP TABLE sofendb.hotel_images;
       sofendb         heap    sofen    false    7            �            1259    35385    hotel_images_seq    SEQUENCE     {   CREATE SEQUENCE sofendb.hotel_images_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE sofendb.hotel_images_seq;
       sofendb          sofen    false    7            �            1259    35386    hotel_policies    TABLE     �  CREATE TABLE sofendb.hotel_policies (
    hotel_policy_id bigint NOT NULL,
    name character varying(50) NOT NULL,
    status character varying(255),
    CONSTRAINT hotel_policies_status_check CHECK (((status)::text = ANY (ARRAY[('ACTIVE'::character varying)::text, ('INACTIVE'::character varying)::text, ('DEACTIVATED'::character varying)::text, ('DELETED'::character varying)::text])))
);
 #   DROP TABLE sofendb.hotel_policies;
       sofendb         heap    sofen    false    7            �            1259    35390 "   hotel_policies_hotel_policy_id_seq    SEQUENCE     �   CREATE SEQUENCE sofendb.hotel_policies_hotel_policy_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 :   DROP SEQUENCE sofendb.hotel_policies_hotel_policy_id_seq;
       sofendb          sofen    false    230    7            5           0    0 "   hotel_policies_hotel_policy_id_seq    SEQUENCE OWNED BY     k   ALTER SEQUENCE sofendb.hotel_policies_hotel_policy_id_seq OWNED BY sofendb.hotel_policies.hotel_policy_id;
          sofendb          sofen    false    231            �            1259    35391    hotel_policy_details    TABLE     �   CREATE TABLE sofendb.hotel_policy_details (
    description character varying(500),
    name character varying(50),
    hotel_policy_id bigint NOT NULL,
    language_id integer NOT NULL
);
 )   DROP TABLE sofendb.hotel_policy_details;
       sofendb         heap    sofen    false    7            �            1259    35396    hotel_service_detail    TABLE     �   CREATE TABLE sofendb.hotel_service_detail (
    language_id integer NOT NULL,
    hotel_service_id bigint NOT NULL,
    name character varying(50),
    description character varying(500)
);
 )   DROP TABLE sofendb.hotel_service_detail;
       sofendb         heap    sofen    false    7            �            1259    35401    hotel_services    TABLE     �  CREATE TABLE sofendb.hotel_services (
    hotel_service_id bigint NOT NULL,
    price bigint,
    name character varying(50) NOT NULL,
    icon character varying(500),
    status character varying(255),
    CONSTRAINT hotel_services_status_check CHECK (((status)::text = ANY (ARRAY[('ACTIVE'::character varying)::text, ('INACTIVE'::character varying)::text, ('DEACTIVATED'::character varying)::text, ('DELETED'::character varying)::text])))
);
 #   DROP TABLE sofendb.hotel_services;
       sofendb         heap    sofen    false    7            �            1259    35407 #   hotel_services_hotel_service_id_seq    SEQUENCE     �   CREATE SEQUENCE sofendb.hotel_services_hotel_service_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ;   DROP SEQUENCE sofendb.hotel_services_hotel_service_id_seq;
       sofendb          sofen    false    234    7            6           0    0 #   hotel_services_hotel_service_id_seq    SEQUENCE OWNED BY     m   ALTER SEQUENCE sofendb.hotel_services_hotel_service_id_seq OWNED BY sofendb.hotel_services.hotel_service_id;
          sofendb          sofen    false    235            �            1259    35408    hotels    TABLE     3  CREATE TABLE sofendb.hotels (
    latitude double precision,
    longitude double precision,
    hotel_address_id bigint,
    hotel_contact_id bigint,
    hotel_id uuid NOT NULL,
    name character varying(50),
    address_detail character varying(100),
    description character varying(1000),
    status character varying(255),
    CONSTRAINT hotels_status_check CHECK (((status)::text = ANY (ARRAY[('ACTIVE'::character varying)::text, ('INACTIVE'::character varying)::text, ('DEACTIVATED'::character varying)::text, ('DELETED'::character varying)::text])))
);
    DROP TABLE sofendb.hotels;
       sofendb         heap    sofen    false    7            �            1259    35414    hotels_hotel_policies    TABLE     p   CREATE TABLE sofendb.hotels_hotel_policies (
    hotel_id uuid NOT NULL,
    hotel_policy_id bigint NOT NULL
);
 *   DROP TABLE sofendb.hotels_hotel_policies;
       sofendb         heap    sofen    false    7            �            1259    35417    hotels_hotel_services    TABLE     q   CREATE TABLE sofendb.hotels_hotel_services (
    hotel_service_id bigint NOT NULL,
    hotel_id uuid NOT NULL
);
 *   DROP TABLE sofendb.hotels_hotel_services;
       sofendb         heap    sofen    false    7            �            1259    35420 	   languages    TABLE     �   CREATE TABLE sofendb.languages (
    code character varying(2),
    is_default boolean,
    language_id integer NOT NULL,
    name character varying(50)
);
    DROP TABLE sofendb.languages;
       sofendb         heap    sofen    false    7            �            1259    35423    languages_language_id_seq    SEQUENCE     �   CREATE SEQUENCE sofendb.languages_language_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE sofendb.languages_language_id_seq;
       sofendb          sofen    false    239    7            7           0    0    languages_language_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE sofendb.languages_language_id_seq OWNED BY sofendb.languages.language_id;
          sofendb          sofen    false    240            �            1259    35424    payments    TABLE     �   CREATE TABLE sofendb.payments (
    payment_date timestamp(6) without time zone,
    payment_id uuid NOT NULL,
    payment_method character varying(255),
    payment_status character varying(255)
);
    DROP TABLE sofendb.payments;
       sofendb         heap    sofen    false    7            �            1259    35429    promotion_detail    TABLE     �   CREATE TABLE sofendb.promotion_detail (
    language_id integer NOT NULL,
    promotion_id bigint NOT NULL,
    name character varying(50),
    description character varying(500)
);
 %   DROP TABLE sofendb.promotion_detail;
       sofendb         heap    sofen    false    7            �            1259    35434 
   promotions    TABLE     �   CREATE TABLE sofendb.promotions (
    discount_percent integer,
    end_date timestamp(6) without time zone,
    promotion_id bigint NOT NULL,
    start_date timestamp(6) without time zone
);
    DROP TABLE sofendb.promotions;
       sofendb         heap    sofen    false    7            �            1259    35437    promotions_hotels    TABLE     i   CREATE TABLE sofendb.promotions_hotels (
    promotion_id bigint NOT NULL,
    hotel_id uuid NOT NULL
);
 &   DROP TABLE sofendb.promotions_hotels;
       sofendb         heap    sofen    false    7            �            1259    35440    promotions_seq    SEQUENCE     y   CREATE SEQUENCE sofendb.promotions_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE sofendb.promotions_seq;
       sofendb          sofen    false    7            �            1259    35441    roles    TABLE     �  CREATE TABLE sofendb.roles (
    role_id bigint NOT NULL,
    role character varying(255),
    CONSTRAINT roles_role_check CHECK (((role)::text = ANY (ARRAY[('ROLE_USER'::character varying)::text, ('ROLE_MANAGER_MASTER'::character varying)::text, ('ROLE_MANAGER'::character varying)::text, ('ROLE_ADMIN_MASTER'::character varying)::text, ('ROLE_ADMIN'::character varying)::text, ('ROLE_EMPLOYEE'::character varying)::text])))
);
    DROP TABLE sofendb.roles;
       sofendb         heap    sofen    false    7            �            1259    35445    roles_role_id_seq    SEQUENCE     {   CREATE SEQUENCE sofendb.roles_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE sofendb.roles_role_id_seq;
       sofendb          sofen    false    7    246            8           0    0    roles_role_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE sofendb.roles_role_id_seq OWNED BY sofendb.roles.role_id;
          sofendb          sofen    false    247            �            1259    35446    room_furniture_details    TABLE     �   CREATE TABLE sofendb.room_furniture_details (
    language_id integer NOT NULL,
    furniture_id bigint NOT NULL,
    name character varying(50),
    description character varying(500)
);
 +   DROP TABLE sofendb.room_furniture_details;
       sofendb         heap    sofen    false    7            �            1259    35451    room_furnitures    TABLE     p   CREATE TABLE sofendb.room_furnitures (
    room_furniture_id bigint NOT NULL,
    name character varying(50)
);
 $   DROP TABLE sofendb.room_furnitures;
       sofendb         heap    sofen    false    7            �            1259    35454    room_furnitures_rooms    TABLE     s   CREATE TABLE sofendb.room_furnitures_rooms (
    room_furniture_id bigint NOT NULL,
    room_id bigint NOT NULL
);
 *   DROP TABLE sofendb.room_furnitures_rooms;
       sofendb         heap    sofen    false    7            �            1259    35457    room_furnitures_seq    SEQUENCE     ~   CREATE SEQUENCE sofendb.room_furnitures_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE sofendb.room_furnitures_seq;
       sofendb          sofen    false    7            �            1259    35458    room_images    TABLE     �   CREATE TABLE sofendb.room_images (
    is_main boolean,
    room_id bigint NOT NULL,
    alt character varying(255),
    status character varying(255),
    url character varying(255)
);
     DROP TABLE sofendb.room_images;
       sofendb         heap    sofen    false    7            �            1259    35463    room_images_room_id_seq    SEQUENCE     �   CREATE SEQUENCE sofendb.room_images_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE sofendb.room_images_room_id_seq;
       sofendb          sofen    false    7    252            9           0    0    room_images_room_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE sofendb.room_images_room_id_seq OWNED BY sofendb.room_images.room_id;
          sofendb          sofen    false    253            �            1259    35464    room_type_detail    TABLE     �   CREATE TABLE sofendb.room_type_detail (
    language_id integer NOT NULL,
    room_type_id bigint NOT NULL,
    name character varying(50),
    description character varying(500)
);
 %   DROP TABLE sofendb.room_type_detail;
       sofendb         heap    sofen    false    7            �            1259    35469 
   room_types    TABLE     �   CREATE TABLE sofendb.room_types (
    area integer,
    max_adults integer,
    max_children integer,
    price bigint,
    room_type_id bigint NOT NULL,
    name character varying(50) NOT NULL,
    bed integer
);
    DROP TABLE sofendb.room_types;
       sofendb         heap    sofen    false    7                        1259    35472    room_types_seq    SEQUENCE     y   CREATE SEQUENCE sofendb.room_types_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE sofendb.room_types_seq;
       sofendb          sofen    false    7                       1259    35473    rooms    TABLE     �  CREATE TABLE sofendb.rooms (
    amount integer,
    quantity_remaining integer,
    room_id bigint NOT NULL,
    room_type_id bigint,
    hotel_id uuid,
    status character varying(255),
    CONSTRAINT rooms_status_check CHECK (((status)::text = ANY (ARRAY[('ACTIVE'::character varying)::text, ('INACTIVE'::character varying)::text, ('DEACTIVATED'::character varying)::text, ('DELETED'::character varying)::text])))
);
    DROP TABLE sofendb.rooms;
       sofendb         heap    sofen    false    7                       1259    35477    rooms_room_id_seq    SEQUENCE     {   CREATE SEQUENCE sofendb.rooms_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE sofendb.rooms_room_id_seq;
       sofendb          sofen    false    257    7            :           0    0    rooms_room_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE sofendb.rooms_room_id_seq OWNED BY sofendb.rooms.room_id;
          sofendb          sofen    false    258                       1259    35478    tokens    TABLE     �  CREATE TABLE sofendb.tokens (
    is_revoked boolean,
    expired_at timestamp(6) without time zone,
    token_id bigint NOT NULL,
    account_id uuid NOT NULL,
    token character varying(255),
    token_type character varying(255),
    CONSTRAINT tokens_token_type_check CHECK (((token_type)::text = ANY (ARRAY[('ACCESS_TOKEN'::character varying)::text, ('REFRESH_TOKEN'::character varying)::text, ('RESET_PASSWORD_TOKEN'::character varying)::text, ('GG_TOKEN'::character varying)::text])))
);
    DROP TABLE sofendb.tokens;
       sofendb         heap    sofen    false    7                       1259    35484    tokens_token_id_seq    SEQUENCE     }   CREATE SEQUENCE sofendb.tokens_token_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE sofendb.tokens_token_id_seq;
       sofendb          sofen    false    259    7            ;           0    0    tokens_token_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE sofendb.tokens_token_id_seq OWNED BY sofendb.tokens.token_id;
          sofendb          sofen    false    260                       1259    35485    user_hotels    TABLE     \   CREATE TABLE sofendb.user_hotels (
    hotel_id uuid NOT NULL,
    user_id uuid NOT NULL
);
     DROP TABLE sofendb.user_hotels;
       sofendb         heap    sofen    false    7                       1259    35488    user_ranking    TABLE     �   CREATE TABLE sofendb.user_ranking (
    reward_percent integer,
    user_ranking_id bigint NOT NULL,
    name character varying(255)
);
 !   DROP TABLE sofendb.user_ranking;
       sofendb         heap    sofen    false    7                       1259    35491    user_ranking_detail    TABLE     �   CREATE TABLE sofendb.user_ranking_detail (
    language_id integer NOT NULL,
    user_ranking_id bigint NOT NULL,
    name character varying(50),
    description character varying(500)
);
 (   DROP TABLE sofendb.user_ranking_detail;
       sofendb         heap    sofen    false    7                       1259    35496    user_ranking_seq    SEQUENCE     {   CREATE SEQUENCE sofendb.user_ranking_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE sofendb.user_ranking_seq;
       sofendb          sofen    false    7            	           1259    35497    users    TABLE     �  CREATE TABLE sofendb.users (
    date_of_birth date,
    is_women boolean,
    point integer,
    created_at timestamp(6) without time zone,
    role_id bigint,
    user_ranking_id bigint,
    user_id uuid NOT NULL,
    address character varying(255),
    email character varying(255),
    full_name character varying(255),
    password character varying(255),
    phone_number character varying(255),
    status character varying(255),
    CONSTRAINT users_status_check CHECK (((status)::text = ANY (ARRAY[('ACTIVE'::character varying)::text, ('INACTIVE'::character varying)::text, ('DEACTIVATED'::character varying)::text, ('DELETED'::character varying)::text])))
);
    DROP TABLE sofendb.users;
       sofendb         heap    sofen    false    7            �           2604    35503    hotel_address hotel_address_id    DEFAULT     �   ALTER TABLE ONLY sofendb.hotel_address ALTER COLUMN hotel_address_id SET DEFAULT nextval('sofendb.hotel_address_hotel_address_id_seq'::regclass);
 N   ALTER TABLE sofendb.hotel_address ALTER COLUMN hotel_address_id DROP DEFAULT;
       sofendb          sofen    false    223    222            �           2604    35504    hotel_contacts hotel_contact_id    DEFAULT     �   ALTER TABLE ONLY sofendb.hotel_contacts ALTER COLUMN hotel_contact_id SET DEFAULT nextval('sofendb.hotel_contacts_hotel_contact_id_seq'::regclass);
 O   ALTER TABLE sofendb.hotel_contacts ALTER COLUMN hotel_contact_id DROP DEFAULT;
       sofendb          sofen    false    226    225            �           2604    35505    hotel_policies hotel_policy_id    DEFAULT     �   ALTER TABLE ONLY sofendb.hotel_policies ALTER COLUMN hotel_policy_id SET DEFAULT nextval('sofendb.hotel_policies_hotel_policy_id_seq'::regclass);
 N   ALTER TABLE sofendb.hotel_policies ALTER COLUMN hotel_policy_id DROP DEFAULT;
       sofendb          sofen    false    231    230            �           2604    35506    hotel_services hotel_service_id    DEFAULT     �   ALTER TABLE ONLY sofendb.hotel_services ALTER COLUMN hotel_service_id SET DEFAULT nextval('sofendb.hotel_services_hotel_service_id_seq'::regclass);
 O   ALTER TABLE sofendb.hotel_services ALTER COLUMN hotel_service_id DROP DEFAULT;
       sofendb          sofen    false    235    234            �           2604    35507    languages language_id    DEFAULT     �   ALTER TABLE ONLY sofendb.languages ALTER COLUMN language_id SET DEFAULT nextval('sofendb.languages_language_id_seq'::regclass);
 E   ALTER TABLE sofendb.languages ALTER COLUMN language_id DROP DEFAULT;
       sofendb          sofen    false    240    239            �           2604    35508    roles role_id    DEFAULT     p   ALTER TABLE ONLY sofendb.roles ALTER COLUMN role_id SET DEFAULT nextval('sofendb.roles_role_id_seq'::regclass);
 =   ALTER TABLE sofendb.roles ALTER COLUMN role_id DROP DEFAULT;
       sofendb          sofen    false    247    246            �           2604    35509    room_images room_id    DEFAULT     |   ALTER TABLE ONLY sofendb.room_images ALTER COLUMN room_id SET DEFAULT nextval('sofendb.room_images_room_id_seq'::regclass);
 C   ALTER TABLE sofendb.room_images ALTER COLUMN room_id DROP DEFAULT;
       sofendb          sofen    false    253    252            �           2604    35510    rooms room_id    DEFAULT     p   ALTER TABLE ONLY sofendb.rooms ALTER COLUMN room_id SET DEFAULT nextval('sofendb.rooms_room_id_seq'::regclass);
 =   ALTER TABLE sofendb.rooms ALTER COLUMN room_id DROP DEFAULT;
       sofendb          sofen    false    258    257            �           2604    35511    tokens token_id    DEFAULT     t   ALTER TABLE ONLY sofendb.tokens ALTER COLUMN token_id SET DEFAULT nextval('sofendb.tokens_token_id_seq'::regclass);
 ?   ALTER TABLE sofendb.tokens ALTER COLUMN token_id DROP DEFAULT;
       sofendb          sofen    false    260    259            �          0    35351    booking_detail_rooms 
   TABLE DATA           K   COPY sofendb.booking_detail_rooms (booking_detail_id, room_id) FROM stdin;
    sofendb          sofen    false    217   �      �          0    35354    booking_details 
   TABLE DATA           �   COPY sofendb.booking_details (number_of_rooms, check_in, check_out, price, booking_detail_id, booking_id, booking_status) FROM stdin;
    sofendb          sofen    false    218         �          0    35357    bookings 
   TABLE DATA           {   COPY sofendb.bookings (number_of_guests, booking_date, guest_id, total_price, booking_id, payment_id, user_id) FROM stdin;
    sofendb          sofen    false    219   �      �          0    35360    guests 
   TABLE DATA           a   COPY sofendb.guests (is_women, date_of_birth, guest_id, email, name, phone, purpose) FROM stdin;
    sofendb          sofen    false    220   m      �          0    35366    hotel_address 
   TABLE DATA           \   COPY sofendb.hotel_address (hotel_address_id, district, nation, province, ward) FROM stdin;
    sofendb          sofen    false    222   �                0    35373    hotel_contacts 
   TABLE DATA           x   COPY sofendb.hotel_contacts (phone_number_code, hotel_contact_id, email, facebook, instagram, phone_number) FROM stdin;
    sofendb          sofen    false    225                   0    35380    hotel_images 
   TABLE DATA           T   COPY sofendb.hotel_images (is_main, hotel_image_id, hotel_id, alt, url) FROM stdin;
    sofendb          sofen    false    228   �                0    35386    hotel_policies 
   TABLE DATA           H   COPY sofendb.hotel_policies (hotel_policy_id, name, status) FROM stdin;
    sofendb          sofen    false    230   �      	          0    35391    hotel_policy_details 
   TABLE DATA           `   COPY sofendb.hotel_policy_details (description, name, hotel_policy_id, language_id) FROM stdin;
    sofendb          sofen    false    232   �      
          0    35396    hotel_service_detail 
   TABLE DATA           a   COPY sofendb.hotel_service_detail (language_id, hotel_service_id, name, description) FROM stdin;
    sofendb          sofen    false    233   �                0    35401    hotel_services 
   TABLE DATA           V   COPY sofendb.hotel_services (hotel_service_id, price, name, icon, status) FROM stdin;
    sofendb          sofen    false    234   |	                0    35408    hotels 
   TABLE DATA           �   COPY sofendb.hotels (latitude, longitude, hotel_address_id, hotel_contact_id, hotel_id, name, address_detail, description, status) FROM stdin;
    sofendb          sofen    false    236   �	                0    35414    hotels_hotel_policies 
   TABLE DATA           K   COPY sofendb.hotels_hotel_policies (hotel_id, hotel_policy_id) FROM stdin;
    sofendb          sofen    false    237   �
                0    35417    hotels_hotel_services 
   TABLE DATA           L   COPY sofendb.hotels_hotel_services (hotel_service_id, hotel_id) FROM stdin;
    sofendb          sofen    false    238   U                0    35420 	   languages 
   TABLE DATA           I   COPY sofendb.languages (code, is_default, language_id, name) FROM stdin;
    sofendb          sofen    false    239   �                0    35424    payments 
   TABLE DATA           ]   COPY sofendb.payments (payment_date, payment_id, payment_method, payment_status) FROM stdin;
    sofendb          sofen    false    241                   0    35429    promotion_detail 
   TABLE DATA           Y   COPY sofendb.promotion_detail (language_id, promotion_id, name, description) FROM stdin;
    sofendb          sofen    false    242   9                0    35434 
   promotions 
   TABLE DATA           [   COPY sofendb.promotions (discount_percent, end_date, promotion_id, start_date) FROM stdin;
    sofendb          sofen    false    243   V                0    35437    promotions_hotels 
   TABLE DATA           D   COPY sofendb.promotions_hotels (promotion_id, hotel_id) FROM stdin;
    sofendb          sofen    false    244   s                0    35441    roles 
   TABLE DATA           /   COPY sofendb.roles (role_id, role) FROM stdin;
    sofendb          sofen    false    246   �                0    35446    room_furniture_details 
   TABLE DATA           _   COPY sofendb.room_furniture_details (language_id, furniture_id, name, description) FROM stdin;
    sofendb          sofen    false    248   �                0    35451    room_furnitures 
   TABLE DATA           C   COPY sofendb.room_furnitures (room_furniture_id, name) FROM stdin;
    sofendb          sofen    false    249   p                0    35454    room_furnitures_rooms 
   TABLE DATA           L   COPY sofendb.room_furnitures_rooms (room_furniture_id, room_id) FROM stdin;
    sofendb          sofen    false    250   '                0    35458    room_images 
   TABLE DATA           J   COPY sofendb.room_images (is_main, room_id, alt, status, url) FROM stdin;
    sofendb          sofen    false    252   C                0    35464    room_type_detail 
   TABLE DATA           Y   COPY sofendb.room_type_detail (language_id, room_type_id, name, description) FROM stdin;
    sofendb          sofen    false    254   `                 0    35469 
   room_types 
   TABLE DATA           e   COPY sofendb.room_types (area, max_adults, max_children, price, room_type_id, name, bed) FROM stdin;
    sofendb          sofen    false    255   K      "          0    35473    rooms 
   TABLE DATA           e   COPY sofendb.rooms (amount, quantity_remaining, room_id, room_type_id, hotel_id, status) FROM stdin;
    sofendb          sofen    false    257   �      $          0    35478    tokens 
   TABLE DATA           b   COPY sofendb.tokens (is_revoked, expired_at, token_id, account_id, token, token_type) FROM stdin;
    sofendb          sofen    false    259   a      &          0    35485    user_hotels 
   TABLE DATA           9   COPY sofendb.user_hotels (hotel_id, user_id) FROM stdin;
    sofendb          sofen    false    261   ~      '          0    35488    user_ranking 
   TABLE DATA           N   COPY sofendb.user_ranking (reward_percent, user_ranking_id, name) FROM stdin;
    sofendb          sofen    false    262   �      (          0    35491    user_ranking_detail 
   TABLE DATA           _   COPY sofendb.user_ranking_detail (language_id, user_ranking_id, name, description) FROM stdin;
    sofendb          sofen    false    263   �      *          0    35497    users 
   TABLE DATA           �   COPY sofendb.users (date_of_birth, is_women, point, created_at, role_id, user_ranking_id, user_id, address, email, full_name, password, phone_number, status) FROM stdin;
    sofendb          sofen    false    265         <           0    0 
   guests_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('sofendb.guests_seq', 1, false);
          sofendb          sofen    false    221            =           0    0 "   hotel_address_hotel_address_id_seq    SEQUENCE SET     R   SELECT pg_catalog.setval('sofendb.hotel_address_hotel_address_id_seq', 1, false);
          sofendb          sofen    false    223            >           0    0    hotel_address_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('sofendb.hotel_address_seq', 151, true);
          sofendb          sofen    false    224            ?           0    0 #   hotel_contacts_hotel_contact_id_seq    SEQUENCE SET     S   SELECT pg_catalog.setval('sofendb.hotel_contacts_hotel_contact_id_seq', 1, false);
          sofendb          sofen    false    226            @           0    0    hotel_contacts_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('sofendb.hotel_contacts_seq', 151, true);
          sofendb          sofen    false    227            A           0    0    hotel_images_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('sofendb.hotel_images_seq', 1, false);
          sofendb          sofen    false    229            B           0    0 "   hotel_policies_hotel_policy_id_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('sofendb.hotel_policies_hotel_policy_id_seq', 6, true);
          sofendb          sofen    false    231            C           0    0 #   hotel_services_hotel_service_id_seq    SEQUENCE SET     R   SELECT pg_catalog.setval('sofendb.hotel_services_hotel_service_id_seq', 5, true);
          sofendb          sofen    false    235            D           0    0    languages_language_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('sofendb.languages_language_id_seq', 2, true);
          sofendb          sofen    false    240            E           0    0    promotions_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('sofendb.promotions_seq', 1, false);
          sofendb          sofen    false    245            F           0    0    roles_role_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('sofendb.roles_role_id_seq', 6, true);
          sofendb          sofen    false    247            G           0    0    room_furnitures_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('sofendb.room_furnitures_seq', 51, true);
          sofendb          sofen    false    251            H           0    0    room_images_room_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('sofendb.room_images_room_id_seq', 1, false);
          sofendb          sofen    false    253            I           0    0    room_types_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('sofendb.room_types_seq', 51, true);
          sofendb          sofen    false    256            J           0    0    rooms_room_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('sofendb.rooms_room_id_seq', 1, false);
          sofendb          sofen    false    258            K           0    0    tokens_token_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('sofendb.tokens_token_id_seq', 1, false);
          sofendb          sofen    false    260            L           0    0    user_ranking_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('sofendb.user_ranking_seq', 1, false);
          sofendb          sofen    false    264            �           2606    35513 $   booking_details booking_details_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY sofendb.booking_details
    ADD CONSTRAINT booking_details_pkey PRIMARY KEY (booking_detail_id);
 O   ALTER TABLE ONLY sofendb.booking_details DROP CONSTRAINT booking_details_pkey;
       sofendb            sofen    false    218                       2606    35515    bookings bookings_guest_id_key 
   CONSTRAINT     ^   ALTER TABLE ONLY sofendb.bookings
    ADD CONSTRAINT bookings_guest_id_key UNIQUE (guest_id);
 I   ALTER TABLE ONLY sofendb.bookings DROP CONSTRAINT bookings_guest_id_key;
       sofendb            sofen    false    219                       2606    35517     bookings bookings_payment_id_key 
   CONSTRAINT     b   ALTER TABLE ONLY sofendb.bookings
    ADD CONSTRAINT bookings_payment_id_key UNIQUE (payment_id);
 K   ALTER TABLE ONLY sofendb.bookings DROP CONSTRAINT bookings_payment_id_key;
       sofendb            sofen    false    219                       2606    35519    bookings bookings_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY sofendb.bookings
    ADD CONSTRAINT bookings_pkey PRIMARY KEY (booking_id);
 A   ALTER TABLE ONLY sofendb.bookings DROP CONSTRAINT bookings_pkey;
       sofendb            sofen    false    219                       2606    35521    guests guests_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY sofendb.guests
    ADD CONSTRAINT guests_pkey PRIMARY KEY (guest_id);
 =   ALTER TABLE ONLY sofendb.guests DROP CONSTRAINT guests_pkey;
       sofendb            sofen    false    220            	           2606    35523     hotel_address hotel_address_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY sofendb.hotel_address
    ADD CONSTRAINT hotel_address_pkey PRIMARY KEY (hotel_address_id);
 K   ALTER TABLE ONLY sofendb.hotel_address DROP CONSTRAINT hotel_address_pkey;
       sofendb            sofen    false    222                       2606    35525 '   hotel_contacts hotel_contacts_email_key 
   CONSTRAINT     d   ALTER TABLE ONLY sofendb.hotel_contacts
    ADD CONSTRAINT hotel_contacts_email_key UNIQUE (email);
 R   ALTER TABLE ONLY sofendb.hotel_contacts DROP CONSTRAINT hotel_contacts_email_key;
       sofendb            sofen    false    225                       2606    35527 .   hotel_contacts hotel_contacts_phone_number_key 
   CONSTRAINT     r   ALTER TABLE ONLY sofendb.hotel_contacts
    ADD CONSTRAINT hotel_contacts_phone_number_key UNIQUE (phone_number);
 Y   ALTER TABLE ONLY sofendb.hotel_contacts DROP CONSTRAINT hotel_contacts_phone_number_key;
       sofendb            sofen    false    225                       2606    35529 "   hotel_contacts hotel_contacts_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY sofendb.hotel_contacts
    ADD CONSTRAINT hotel_contacts_pkey PRIMARY KEY (hotel_contact_id);
 M   ALTER TABLE ONLY sofendb.hotel_contacts DROP CONSTRAINT hotel_contacts_pkey;
       sofendb            sofen    false    225                       2606    35531    hotel_images hotel_images_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY sofendb.hotel_images
    ADD CONSTRAINT hotel_images_pkey PRIMARY KEY (hotel_image_id);
 I   ALTER TABLE ONLY sofendb.hotel_images DROP CONSTRAINT hotel_images_pkey;
       sofendb            sofen    false    228                       2606    35533 &   hotel_policies hotel_policies_name_key 
   CONSTRAINT     b   ALTER TABLE ONLY sofendb.hotel_policies
    ADD CONSTRAINT hotel_policies_name_key UNIQUE (name);
 Q   ALTER TABLE ONLY sofendb.hotel_policies DROP CONSTRAINT hotel_policies_name_key;
       sofendb            sofen    false    230                       2606    35535 "   hotel_policies hotel_policies_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY sofendb.hotel_policies
    ADD CONSTRAINT hotel_policies_pkey PRIMARY KEY (hotel_policy_id);
 M   ALTER TABLE ONLY sofendb.hotel_policies DROP CONSTRAINT hotel_policies_pkey;
       sofendb            sofen    false    230                       2606    35537 .   hotel_policy_details hotel_policy_details_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotel_policy_details
    ADD CONSTRAINT hotel_policy_details_pkey PRIMARY KEY (hotel_policy_id, language_id);
 Y   ALTER TABLE ONLY sofendb.hotel_policy_details DROP CONSTRAINT hotel_policy_details_pkey;
       sofendb            sofen    false    232    232                       2606    35539 .   hotel_service_detail hotel_service_detail_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotel_service_detail
    ADD CONSTRAINT hotel_service_detail_pkey PRIMARY KEY (language_id, hotel_service_id);
 Y   ALTER TABLE ONLY sofendb.hotel_service_detail DROP CONSTRAINT hotel_service_detail_pkey;
       sofendb            sofen    false    233    233                       2606    35541 &   hotel_services hotel_services_name_key 
   CONSTRAINT     b   ALTER TABLE ONLY sofendb.hotel_services
    ADD CONSTRAINT hotel_services_name_key UNIQUE (name);
 Q   ALTER TABLE ONLY sofendb.hotel_services DROP CONSTRAINT hotel_services_name_key;
       sofendb            sofen    false    234                       2606    35543 "   hotel_services hotel_services_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY sofendb.hotel_services
    ADD CONSTRAINT hotel_services_pkey PRIMARY KEY (hotel_service_id);
 M   ALTER TABLE ONLY sofendb.hotel_services DROP CONSTRAINT hotel_services_pkey;
       sofendb            sofen    false    234                       2606    35545 "   hotels hotels_hotel_address_id_key 
   CONSTRAINT     j   ALTER TABLE ONLY sofendb.hotels
    ADD CONSTRAINT hotels_hotel_address_id_key UNIQUE (hotel_address_id);
 M   ALTER TABLE ONLY sofendb.hotels DROP CONSTRAINT hotels_hotel_address_id_key;
       sofendb            sofen    false    236            !           2606    35547 "   hotels hotels_hotel_contact_id_key 
   CONSTRAINT     j   ALTER TABLE ONLY sofendb.hotels
    ADD CONSTRAINT hotels_hotel_contact_id_key UNIQUE (hotel_contact_id);
 M   ALTER TABLE ONLY sofendb.hotels DROP CONSTRAINT hotels_hotel_contact_id_key;
       sofendb            sofen    false    236            #           2606    35549    hotels hotels_name_key 
   CONSTRAINT     R   ALTER TABLE ONLY sofendb.hotels
    ADD CONSTRAINT hotels_name_key UNIQUE (name);
 A   ALTER TABLE ONLY sofendb.hotels DROP CONSTRAINT hotels_name_key;
       sofendb            sofen    false    236            %           2606    35551    hotels hotels_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY sofendb.hotels
    ADD CONSTRAINT hotels_pkey PRIMARY KEY (hotel_id);
 =   ALTER TABLE ONLY sofendb.hotels DROP CONSTRAINT hotels_pkey;
       sofendb            sofen    false    236            '           2606    35553    languages languages_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY sofendb.languages
    ADD CONSTRAINT languages_pkey PRIMARY KEY (language_id);
 C   ALTER TABLE ONLY sofendb.languages DROP CONSTRAINT languages_pkey;
       sofendb            sofen    false    239            )           2606    35555    payments payments_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY sofendb.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (payment_id);
 A   ALTER TABLE ONLY sofendb.payments DROP CONSTRAINT payments_pkey;
       sofendb            sofen    false    241            +           2606    35557 &   promotion_detail promotion_detail_pkey 
   CONSTRAINT     |   ALTER TABLE ONLY sofendb.promotion_detail
    ADD CONSTRAINT promotion_detail_pkey PRIMARY KEY (language_id, promotion_id);
 Q   ALTER TABLE ONLY sofendb.promotion_detail DROP CONSTRAINT promotion_detail_pkey;
       sofendb            sofen    false    242    242            -           2606    35559    promotions promotions_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY sofendb.promotions
    ADD CONSTRAINT promotions_pkey PRIMARY KEY (promotion_id);
 E   ALTER TABLE ONLY sofendb.promotions DROP CONSTRAINT promotions_pkey;
       sofendb            sofen    false    243            /           2606    35561    roles roles_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY sofendb.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (role_id);
 ;   ALTER TABLE ONLY sofendb.roles DROP CONSTRAINT roles_pkey;
       sofendb            sofen    false    246            1           2606    35563 2   room_furniture_details room_furniture_details_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY sofendb.room_furniture_details
    ADD CONSTRAINT room_furniture_details_pkey PRIMARY KEY (language_id, furniture_id);
 ]   ALTER TABLE ONLY sofendb.room_furniture_details DROP CONSTRAINT room_furniture_details_pkey;
       sofendb            sofen    false    248    248            3           2606    35565 $   room_furnitures room_furnitures_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY sofendb.room_furnitures
    ADD CONSTRAINT room_furnitures_pkey PRIMARY KEY (room_furniture_id);
 O   ALTER TABLE ONLY sofendb.room_furnitures DROP CONSTRAINT room_furnitures_pkey;
       sofendb            sofen    false    249            5           2606    35567    room_images room_images_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY sofendb.room_images
    ADD CONSTRAINT room_images_pkey PRIMARY KEY (room_id);
 G   ALTER TABLE ONLY sofendb.room_images DROP CONSTRAINT room_images_pkey;
       sofendb            sofen    false    252            7           2606    35569 &   room_type_detail room_type_detail_pkey 
   CONSTRAINT     |   ALTER TABLE ONLY sofendb.room_type_detail
    ADD CONSTRAINT room_type_detail_pkey PRIMARY KEY (language_id, room_type_id);
 Q   ALTER TABLE ONLY sofendb.room_type_detail DROP CONSTRAINT room_type_detail_pkey;
       sofendb            sofen    false    254    254            9           2606    35571    room_types room_types_name_key 
   CONSTRAINT     Z   ALTER TABLE ONLY sofendb.room_types
    ADD CONSTRAINT room_types_name_key UNIQUE (name);
 I   ALTER TABLE ONLY sofendb.room_types DROP CONSTRAINT room_types_name_key;
       sofendb            sofen    false    255            ;           2606    35573    room_types room_types_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY sofendb.room_types
    ADD CONSTRAINT room_types_pkey PRIMARY KEY (room_type_id);
 E   ALTER TABLE ONLY sofendb.room_types DROP CONSTRAINT room_types_pkey;
       sofendb            sofen    false    255            =           2606    35575    rooms rooms_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY sofendb.rooms
    ADD CONSTRAINT rooms_pkey PRIMARY KEY (room_id);
 ;   ALTER TABLE ONLY sofendb.rooms DROP CONSTRAINT rooms_pkey;
       sofendb            sofen    false    257            ?           2606    35577    tokens tokens_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY sofendb.tokens
    ADD CONSTRAINT tokens_pkey PRIMARY KEY (token_id);
 =   ALTER TABLE ONLY sofendb.tokens DROP CONSTRAINT tokens_pkey;
       sofendb            sofen    false    259            C           2606    35579 ,   user_ranking_detail user_ranking_detail_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY sofendb.user_ranking_detail
    ADD CONSTRAINT user_ranking_detail_pkey PRIMARY KEY (language_id, user_ranking_id);
 W   ALTER TABLE ONLY sofendb.user_ranking_detail DROP CONSTRAINT user_ranking_detail_pkey;
       sofendb            sofen    false    263    263            A           2606    35581    user_ranking user_ranking_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY sofendb.user_ranking
    ADD CONSTRAINT user_ranking_pkey PRIMARY KEY (user_ranking_id);
 I   ALTER TABLE ONLY sofendb.user_ranking DROP CONSTRAINT user_ranking_pkey;
       sofendb            sofen    false    262            E           2606    35583    users users_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY sofendb.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);
 ;   ALTER TABLE ONLY sofendb.users DROP CONSTRAINT users_pkey;
       sofendb            sofen    false    265            `           2606    35584 ,   room_type_detail fk1uxuyrr4jo7w3kivjj8o5ceet    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.room_type_detail
    ADD CONSTRAINT fk1uxuyrr4jo7w3kivjj8o5ceet FOREIGN KEY (room_type_id) REFERENCES sofendb.room_types(room_type_id);
 W   ALTER TABLE ONLY sofendb.room_type_detail DROP CONSTRAINT fk1uxuyrr4jo7w3kivjj8o5ceet;
       sofendb          sofen    false    255    4923    254            e           2606    35589 '   user_hotels fk2xs4pbj0cjbycdk3nlxpcwd1w    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.user_hotels
    ADD CONSTRAINT fk2xs4pbj0cjbycdk3nlxpcwd1w FOREIGN KEY (hotel_id) REFERENCES sofendb.hotels(hotel_id);
 R   ALTER TABLE ONLY sofendb.user_hotels DROP CONSTRAINT fk2xs4pbj0cjbycdk3nlxpcwd1w;
       sofendb          sofen    false    236    261    4901            W           2606    35594 ,   promotion_detail fk3ch8y7e4jsvp5qdd7wrd899x3    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.promotion_detail
    ADD CONSTRAINT fk3ch8y7e4jsvp5qdd7wrd899x3 FOREIGN KEY (language_id) REFERENCES sofendb.languages(language_id);
 W   ALTER TABLE ONLY sofendb.promotion_detail DROP CONSTRAINT fk3ch8y7e4jsvp5qdd7wrd899x3;
       sofendb          sofen    false    242    239    4903            Q           2606    35599 "   hotels fk3r849tfl7rq9ntmo494x23aov    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotels
    ADD CONSTRAINT fk3r849tfl7rq9ntmo494x23aov FOREIGN KEY (hotel_contact_id) REFERENCES sofendb.hotel_contacts(hotel_contact_id);
 M   ALTER TABLE ONLY sofendb.hotels DROP CONSTRAINT fk3r849tfl7rq9ntmo494x23aov;
       sofendb          sofen    false    4879    225    236            M           2606    35604 0   hotel_policy_details fk4259jt6w82ygcj82a830b9aw3    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotel_policy_details
    ADD CONSTRAINT fk4259jt6w82ygcj82a830b9aw3 FOREIGN KEY (language_id) REFERENCES sofendb.languages(language_id);
 [   ALTER TABLE ONLY sofendb.hotel_policy_details DROP CONSTRAINT fk4259jt6w82ygcj82a830b9aw3;
       sofendb          sofen    false    232    4903    239            N           2606    35609 0   hotel_policy_details fk79m2y3issxuu0hfvx2d3kqomi    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotel_policy_details
    ADD CONSTRAINT fk79m2y3issxuu0hfvx2d3kqomi FOREIGN KEY (hotel_policy_id) REFERENCES sofendb.hotel_policies(hotel_policy_id);
 [   ALTER TABLE ONLY sofendb.hotel_policy_details DROP CONSTRAINT fk79m2y3issxuu0hfvx2d3kqomi;
       sofendb          sofen    false    232    4885    230            [           2606    35614 2   room_furniture_details fkaiclvflm92jtge6qolb272oyh    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.room_furniture_details
    ADD CONSTRAINT fkaiclvflm92jtge6qolb272oyh FOREIGN KEY (language_id) REFERENCES sofendb.languages(language_id);
 ]   ALTER TABLE ONLY sofendb.room_furniture_details DROP CONSTRAINT fkaiclvflm92jtge6qolb272oyh;
       sofendb          sofen    false    248    4903    239            O           2606    35619 0   hotel_service_detail fkb48n921uxqqlmt3fnmunlw225    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotel_service_detail
    ADD CONSTRAINT fkb48n921uxqqlmt3fnmunlw225 FOREIGN KEY (language_id) REFERENCES sofendb.languages(language_id);
 [   ALTER TABLE ONLY sofendb.hotel_service_detail DROP CONSTRAINT fkb48n921uxqqlmt3fnmunlw225;
       sofendb          sofen    false    233    4903    239            X           2606    35624 ,   promotion_detail fkc2cukei4dwsx0dgwgsp2ii92m    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.promotion_detail
    ADD CONSTRAINT fkc2cukei4dwsx0dgwgsp2ii92m FOREIGN KEY (promotion_id) REFERENCES sofendb.promotions(promotion_id);
 W   ALTER TABLE ONLY sofendb.promotion_detail DROP CONSTRAINT fkc2cukei4dwsx0dgwgsp2ii92m;
       sofendb          sofen    false    242    4909    243            P           2606    35629 0   hotel_service_detail fkc2xlk8ij9bw0jsc5ci0qc3b8m    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotel_service_detail
    ADD CONSTRAINT fkc2xlk8ij9bw0jsc5ci0qc3b8m FOREIGN KEY (hotel_service_id) REFERENCES sofendb.hotel_services(hotel_service_id);
 [   ALTER TABLE ONLY sofendb.hotel_service_detail DROP CONSTRAINT fkc2xlk8ij9bw0jsc5ci0qc3b8m;
       sofendb          sofen    false    233    4893    234            F           2606    35634 0   booking_detail_rooms fkdbl5xltc54fxe244hrp92v5r6    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.booking_detail_rooms
    ADD CONSTRAINT fkdbl5xltc54fxe244hrp92v5r6 FOREIGN KEY (booking_detail_id) REFERENCES sofendb.booking_details(booking_detail_id);
 [   ALTER TABLE ONLY sofendb.booking_detail_rooms DROP CONSTRAINT fkdbl5xltc54fxe244hrp92v5r6;
       sofendb          sofen    false    217    4863    218            \           2606    35639 2   room_furniture_details fkelitbnvk35svwcc923nq6f0ye    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.room_furniture_details
    ADD CONSTRAINT fkelitbnvk35svwcc923nq6f0ye FOREIGN KEY (furniture_id) REFERENCES sofendb.room_furnitures(room_furniture_id);
 ]   ALTER TABLE ONLY sofendb.room_furniture_details DROP CONSTRAINT fkelitbnvk35svwcc923nq6f0ye;
       sofendb          sofen    false    248    4915    249            I           2606    35644 $   bookings fkeyog2oic85xg7hsu2je2lx3s6    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.bookings
    ADD CONSTRAINT fkeyog2oic85xg7hsu2je2lx3s6 FOREIGN KEY (user_id) REFERENCES sofendb.users(user_id);
 O   ALTER TABLE ONLY sofendb.bookings DROP CONSTRAINT fkeyog2oic85xg7hsu2je2lx3s6;
       sofendb          sofen    false    219    265    4933            U           2606    35649 1   hotels_hotel_services fkf2bgo418n7pifcka93wtk01ob    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotels_hotel_services
    ADD CONSTRAINT fkf2bgo418n7pifcka93wtk01ob FOREIGN KEY (hotel_service_id) REFERENCES sofendb.hotel_services(hotel_service_id);
 \   ALTER TABLE ONLY sofendb.hotels_hotel_services DROP CONSTRAINT fkf2bgo418n7pifcka93wtk01ob;
       sofendb          sofen    false    238    4893    234            a           2606    35654 ,   room_type_detail fkfkwimme1yh4jvfx16bjr1i0xg    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.room_type_detail
    ADD CONSTRAINT fkfkwimme1yh4jvfx16bjr1i0xg FOREIGN KEY (language_id) REFERENCES sofendb.languages(language_id);
 W   ALTER TABLE ONLY sofendb.room_type_detail DROP CONSTRAINT fkfkwimme1yh4jvfx16bjr1i0xg;
       sofendb          sofen    false    254    4903    239            b           2606    35659 !   rooms fkh9m2n1paq5hmd3u0klfl7wsfv    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.rooms
    ADD CONSTRAINT fkh9m2n1paq5hmd3u0klfl7wsfv FOREIGN KEY (room_type_id) REFERENCES sofendb.room_types(room_type_id);
 L   ALTER TABLE ONLY sofendb.rooms DROP CONSTRAINT fkh9m2n1paq5hmd3u0klfl7wsfv;
       sofendb          sofen    false    257    4923    255            ]           2606    35664 1   room_furnitures_rooms fkhmp0hgjo8ea43wb04lb7xsqy3    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.room_furnitures_rooms
    ADD CONSTRAINT fkhmp0hgjo8ea43wb04lb7xsqy3 FOREIGN KEY (room_id) REFERENCES sofendb.rooms(room_id);
 \   ALTER TABLE ONLY sofendb.room_furnitures_rooms DROP CONSTRAINT fkhmp0hgjo8ea43wb04lb7xsqy3;
       sofendb          sofen    false    250    4925    257            g           2606    35669 /   user_ranking_detail fkjd7apv9ft2c6298xq6pkf8jtk    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.user_ranking_detail
    ADD CONSTRAINT fkjd7apv9ft2c6298xq6pkf8jtk FOREIGN KEY (user_ranking_id) REFERENCES sofendb.user_ranking(user_ranking_id);
 Z   ALTER TABLE ONLY sofendb.user_ranking_detail DROP CONSTRAINT fkjd7apv9ft2c6298xq6pkf8jtk;
       sofendb          sofen    false    263    4929    262            J           2606    35674 $   bookings fkjki6p9c5yckce7owst8vxu17u    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.bookings
    ADD CONSTRAINT fkjki6p9c5yckce7owst8vxu17u FOREIGN KEY (payment_id) REFERENCES sofendb.payments(payment_id);
 O   ALTER TABLE ONLY sofendb.bookings DROP CONSTRAINT fkjki6p9c5yckce7owst8vxu17u;
       sofendb          sofen    false    219    4905    241            Y           2606    35679 -   promotions_hotels fkjveobtm4jffh1l5qmpdbicxwv    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.promotions_hotels
    ADD CONSTRAINT fkjveobtm4jffh1l5qmpdbicxwv FOREIGN KEY (promotion_id) REFERENCES sofendb.promotions(promotion_id);
 X   ALTER TABLE ONLY sofendb.promotions_hotels DROP CONSTRAINT fkjveobtm4jffh1l5qmpdbicxwv;
       sofendb          sofen    false    244    4909    243            H           2606    35684 +   booking_details fkkbcan6ybv86uappnh0qtdmvas    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.booking_details
    ADD CONSTRAINT fkkbcan6ybv86uappnh0qtdmvas FOREIGN KEY (booking_id) REFERENCES sofendb.bookings(booking_id);
 V   ALTER TABLE ONLY sofendb.booking_details DROP CONSTRAINT fkkbcan6ybv86uappnh0qtdmvas;
       sofendb          sofen    false    218    4869    219            ^           2606    35689 1   room_furnitures_rooms fklciavggdgbwtlgyrcwgbyoykb    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.room_furnitures_rooms
    ADD CONSTRAINT fklciavggdgbwtlgyrcwgbyoykb FOREIGN KEY (room_furniture_id) REFERENCES sofendb.room_furnitures(room_furniture_id);
 \   ALTER TABLE ONLY sofendb.room_furnitures_rooms DROP CONSTRAINT fklciavggdgbwtlgyrcwgbyoykb;
       sofendb          sofen    false    250    4915    249            h           2606    35694 /   user_ranking_detail fkmihm6p9ql0o8d4ac0beav1sc3    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.user_ranking_detail
    ADD CONSTRAINT fkmihm6p9ql0o8d4ac0beav1sc3 FOREIGN KEY (language_id) REFERENCES sofendb.languages(language_id);
 Z   ALTER TABLE ONLY sofendb.user_ranking_detail DROP CONSTRAINT fkmihm6p9ql0o8d4ac0beav1sc3;
       sofendb          sofen    false    239    263    4903            S           2606    35699 1   hotels_hotel_policies fkndixp9cpvd7mxahg6kqn4k1aw    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotels_hotel_policies
    ADD CONSTRAINT fkndixp9cpvd7mxahg6kqn4k1aw FOREIGN KEY (hotel_policy_id) REFERENCES sofendb.hotel_policies(hotel_policy_id);
 \   ALTER TABLE ONLY sofendb.hotels_hotel_policies DROP CONSTRAINT fkndixp9cpvd7mxahg6kqn4k1aw;
       sofendb          sofen    false    4885    237    230            T           2606    35704 1   hotels_hotel_policies fko5kd5m6u884n0ob99fk0h5q3f    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotels_hotel_policies
    ADD CONSTRAINT fko5kd5m6u884n0ob99fk0h5q3f FOREIGN KEY (hotel_id) REFERENCES sofendb.hotels(hotel_id);
 \   ALTER TABLE ONLY sofendb.hotels_hotel_policies DROP CONSTRAINT fko5kd5m6u884n0ob99fk0h5q3f;
       sofendb          sofen    false    4901    237    236            Z           2606    35709 -   promotions_hotels fkp3d2fh5oh6lhibxsy1wlwr066    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.promotions_hotels
    ADD CONSTRAINT fkp3d2fh5oh6lhibxsy1wlwr066 FOREIGN KEY (hotel_id) REFERENCES sofendb.hotels(hotel_id);
 X   ALTER TABLE ONLY sofendb.promotions_hotels DROP CONSTRAINT fkp3d2fh5oh6lhibxsy1wlwr066;
       sofendb          sofen    false    236    244    4901            i           2606    35714 !   users fkp56c1712k691lhsyewcssf40f    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.users
    ADD CONSTRAINT fkp56c1712k691lhsyewcssf40f FOREIGN KEY (role_id) REFERENCES sofendb.roles(role_id);
 L   ALTER TABLE ONLY sofendb.users DROP CONSTRAINT fkp56c1712k691lhsyewcssf40f;
       sofendb          sofen    false    4911    265    246            c           2606    35719 !   rooms fkp5lufxy0ghq53ugm93hdc941k    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.rooms
    ADD CONSTRAINT fkp5lufxy0ghq53ugm93hdc941k FOREIGN KEY (hotel_id) REFERENCES sofendb.hotels(hotel_id);
 L   ALTER TABLE ONLY sofendb.rooms DROP CONSTRAINT fkp5lufxy0ghq53ugm93hdc941k;
       sofendb          sofen    false    257    4901    236            G           2606    35724 0   booking_detail_rooms fkpgg7i257ceoh89t3apaopi5a8    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.booking_detail_rooms
    ADD CONSTRAINT fkpgg7i257ceoh89t3apaopi5a8 FOREIGN KEY (room_id) REFERENCES sofendb.rooms(room_id);
 [   ALTER TABLE ONLY sofendb.booking_detail_rooms DROP CONSTRAINT fkpgg7i257ceoh89t3apaopi5a8;
       sofendb          sofen    false    4925    257    217            K           2606    35729 $   bookings fkpvlyfwhomknrbmo2d20src5vi    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.bookings
    ADD CONSTRAINT fkpvlyfwhomknrbmo2d20src5vi FOREIGN KEY (guest_id) REFERENCES sofendb.guests(guest_id);
 O   ALTER TABLE ONLY sofendb.bookings DROP CONSTRAINT fkpvlyfwhomknrbmo2d20src5vi;
       sofendb          sofen    false    4871    219    220            L           2606    35734 (   hotel_images fkrj3n45f8oqy1yr996g14j757i    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotel_images
    ADD CONSTRAINT fkrj3n45f8oqy1yr996g14j757i FOREIGN KEY (hotel_id) REFERENCES sofendb.hotels(hotel_id);
 S   ALTER TABLE ONLY sofendb.hotel_images DROP CONSTRAINT fkrj3n45f8oqy1yr996g14j757i;
       sofendb          sofen    false    228    236    4901            j           2606    35739 !   users fkrl713nbfrarmcniij55vytp33    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.users
    ADD CONSTRAINT fkrl713nbfrarmcniij55vytp33 FOREIGN KEY (user_ranking_id) REFERENCES sofendb.user_ranking(user_ranking_id);
 L   ALTER TABLE ONLY sofendb.users DROP CONSTRAINT fkrl713nbfrarmcniij55vytp33;
       sofendb          sofen    false    262    265    4929            V           2606    35744 0   hotels_hotel_services fksds38h34p1kplygd07f810ik    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotels_hotel_services
    ADD CONSTRAINT fksds38h34p1kplygd07f810ik FOREIGN KEY (hotel_id) REFERENCES sofendb.hotels(hotel_id);
 [   ALTER TABLE ONLY sofendb.hotels_hotel_services DROP CONSTRAINT fksds38h34p1kplygd07f810ik;
       sofendb          sofen    false    236    238    4901            d           2606    35749 "   tokens fkspv3olbktcqfgg77mm0fr535t    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.tokens
    ADD CONSTRAINT fkspv3olbktcqfgg77mm0fr535t FOREIGN KEY (account_id) REFERENCES sofendb.users(user_id);
 M   ALTER TABLE ONLY sofendb.tokens DROP CONSTRAINT fkspv3olbktcqfgg77mm0fr535t;
       sofendb          sofen    false    265    259    4933            f           2606    35754 '   user_hotels fkssw53klugmlw27tbs3i0bg081    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.user_hotels
    ADD CONSTRAINT fkssw53klugmlw27tbs3i0bg081 FOREIGN KEY (user_id) REFERENCES sofendb.users(user_id);
 R   ALTER TABLE ONLY sofendb.user_hotels DROP CONSTRAINT fkssw53klugmlw27tbs3i0bg081;
       sofendb          sofen    false    265    261    4933            _           2606    35759 '   room_images fktky1jnwoh1hv50m263p2vlt0y    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.room_images
    ADD CONSTRAINT fktky1jnwoh1hv50m263p2vlt0y FOREIGN KEY (room_id) REFERENCES sofendb.rooms(room_id);
 R   ALTER TABLE ONLY sofendb.room_images DROP CONSTRAINT fktky1jnwoh1hv50m263p2vlt0y;
       sofendb          sofen    false    4925    257    252            R           2606    35764 "   hotels fktn7smg3n77hmfii6q4ddt0c11    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotels
    ADD CONSTRAINT fktn7smg3n77hmfii6q4ddt0c11 FOREIGN KEY (hotel_address_id) REFERENCES sofendb.hotel_address(hotel_address_id);
 M   ALTER TABLE ONLY sofendb.hotels DROP CONSTRAINT fktn7smg3n77hmfii6q4ddt0c11;
       sofendb          sofen    false    4873    236    222            �   K   x��̱�  �v��FvI�(���l������.�^I���s�fk��aW����8�:��A2l�f�}�S��'>c      �   �   x���9
1�Z�����K�R$��il���GȐʁ�(�T|x�� �)�g��~zm�m{XQXf�*��X����T{�5���a՘Я�u^$8>���v>���T`��3�]S@�f���k2��LSwM�h7��L�I�A�>���b1�d�ι7띥      �   �   x��ҹ�0�ښB0��Cd7:�!J��%a���䃑� %��D\}\�5��1],���O����P|���r��b��GYZ�kU	��Լr����RK0��l	�F^�%Rk�1Z���^�&[#K|�dRk^���ؒ�4Rk���KRRk�������      �      x������ � �      �   �   x�3�����P�8�)�3,������\N��a�`a��qA��@u���ʝ�Z���ql!gD)P�D?�1���-y
G&<ܵ8_Ah\�B�y�Ț�/Pz��;QAW!��ʼt���J9�b���� �A?         �   x��0�4��H�OK�����K�sJ�tH�M���K���LKLNM���q�K2Ru�AJu��83�KӋs1���ML��-̹,L8��8'�d�6<���@)�� Í���$cx
n�S8�b���� �c]�         �  x���Ao�(�Ϟ_�S.�1ە�U��=l�$�JU%�1����2�ɯ�=�VmՌܮv/�,����x�PBQI1���"� �sP�$X���	�Y����ǷR�!hk�6����P�f]��vka{xM���c��6��+����mB��Ű��b_A�z34(�z�FY��C{��$Y�h��k�4�⽖clUZ�6�.�B�_u�/��AXSs{�0�i|:�k�$E0��[鬟H��gѕ��_6���o�U����x�/�������&�fp��݃�t���U��և�I`��.%��AEpDjPdI�e^W5�˳������hJ���t3x�;Y?7�e>�g�OS�.� �5���g�:���rd��e�l�+�S�ꈂן�܃�V��Q��6�uN��i嚛#7��I鿯0�����
T5�
$��cD� .
�*T/(���,sC����X�����Hح[�Qwg���?74TT,�������e�������A��,�J
���?eǳ3LE�2��������k�E�@L�mC����#����Q���	Ǫ#�p�;������m�<��m��k#�7r:!����i�&��~�ԡ)��aȰ��}�fOh6����KA���l��:4�ֻP��nC�;G�;AJ��a3��:���N�s�O�x���⢕�(�"Iv�t���	��E�ݗ$?�W������         F   x�3�t�8�6/C����������v+��*�p::�x��r�P`S`�C�L�)�0f���c���� %&>4      	      x������ � �      
   n   x�3�4�ty��;9C����
ٙw7�*�%*qreKR�K��S��2�S9��8�p�0i1�j1D�c�[�H�1L��.<��v�����f�CH�)D\u� mdY�         I   x�3�444 N�����3��^�P�Z\�`��������e�G�!�:c|����U�l�)N�H�b���� ��?3         �   x�]�;N1Ek�*����2.I@�&�R�y�1#![��.X�a#�	�B1B����9(PP��=�>��Mf x$�%OQ�S��5�"�s�䎋\�8�������|3^�.;l��KZ)�`Cs�@��#��*���½i�q��f~N�sL�>�{AmY�[�_C5�Uڣsz�A'f4��rF�BRƨe�y��ό��E����C��>�ݢ�?��K�Ux            x��Ϲ1 ����s�^� ��/�*�|���#y�ŉ��1B
+��^���q�=&=�=fP2����e^����5���ʥ~-6zLzL{�`�hҜX���Wi��C����B����3�}����         |   x��ϻ! �x�} ��^.��K8K �d��i=h�E"���2�<�Q�j�ƸƤƴ��I�[���2K'Y0���b��B�q�I�i��3�%pNȮd�����|H�ʷPb\cRc����VÊ�         +   x�K��L�4�t��OJ��*��8�2SK�sS�S�b���� �B
6            x������ � �            x������ � �            x������ � �            x������ � �         E   x�3���q�v�2��}��]��tpP�E���ut���)1E�2�p\}|�#]]�b���� �Is         {  x�]QKN�0\���'@$����(�7uc��.N
�p �f�
	���f��U`�t�<�L��<����?��v�8� ��rE��v%�)����z�Ո9Izf)ހ���6!v:�U;M�m!9�	�l�v"�Z�S��}S<�9�$1K�5oN׆HM&��>�c���k��D�\h%�LIO�pN��٬1Y�t6w`��I25���� � ��:��*?���Ζ=�,��徽�~�/d��o[h�j*��Y��jAR��T��A��Hy��TbD��f=#IU�cD�S!3䛇�c�Ě��"�HNJ%���	,6����N���\3��LΩL���t ���V֦H����'��ah>�X�$�W���G���Vz�         �   x��;�0�z�9b��i	B �D��,d[���A\w#��c��xÌ�b�ŻbN��_���T�����nP,��}�Z��TÊ��C����5�������xJ;Ӕ�93f1�����4J��s��6�	������%5�+xI	�Z�VԚ$WT�kj\�e�6���7�           x�%ӻ�#1Q�
fO����cQ�m����~�o�M������{~*�8|*������{��n����n�����;`��v��ӆ�ap��ap� �p �8 �O���ip���ip��pN�	8'���>m\��ep\��ep.�� �\�p��mp���mp����7�܀pn��ӎ�c�<��c�<�<�� �x �O���k����k����^�x/���>�|��g�|��g�>�� �|���4�.�]l�w��b�ž��W^�xe㕑WV^�ye畡W�^�E�I=�'�$������Wї����Wҗ���5��DO"(Q�HJ4%�U)Y)])a)e)i)m)q)u)yq�L&���De"3љ�R���Қ�R���қ���Ds":Q��Nt'��)�)�)�)�)�)�)**	r��PT(2�E�"EѢ�R���ң�R���Ҥ��߿���S��m            x������ � �         �  x��TMn�0^ON���N��,ٸΛ��q��̚���.�
�UT����񮩸�o�s�a�~v3�I���O����׍t�lq2y��D����jP���Oj���L�W���5�h����?kp�7B��(��ۿA���E�ş���[$�f�L�<`�¨�x/��>09��ޅn���߀�t!$pV����x�3�E�B2M��e�+���)�\
���L��;�H+ߞ����`[�x�V���h���ڣ�d�Fi�0���ot�&���}#p��Z��Q9T -0P���J� � r��xsJ���)�ee(G�7�R�R�u
;�]6J��7��1�k��lJ`%j�$��Ekfa�\I�֌c:�1X�hV�#���!�j>6f=0�J��5Zg�$�e����o��F{��:���\�-דd�[����X��Vk�6�t�ث��j�A��m�$�_��^          A   x�35�4�4�465 NC���̒TC#c�?.STy#$y,��i,�&pY,��I�L� �]      "   �   x��Ϲ�0 �Zޅ_�Z"����G#H��8�Ę(q��|�j�jj��!�~ej4��>d	N��$[B��E^�|�DvѥL��sw�;A%pb��hC�~�=*���{T�y�{T�9}�Q��D~/Y�a)���Y+\��<�'����N��(�y�G�ޓz?J~}�~�ܯ�8>��l      $      x������ � �      &      x������ � �      '   =   x�34�4�t*�ϫJ�22�4���)K-�26�4�t��I�21�4��I,��+������ c�"      (      x������ � �      *   �   x��οj�0�Y~
=�Ϝ�ڗ�!t�ڦ�
E��M����4��W
���M��P��V(D�Q[@�$�ʘRAT�	���L�cZ	lc��K��n]����F�L���a�����_�x�h��xM?R�}���a���K��\��l�v��"r�sf����¶	�m5X�*�J�FǨ=y#R�\��¹����ͧ�>��"���A�S���ϊ�,��s�;t]���,�ft��ߊ,�~ N�~�     