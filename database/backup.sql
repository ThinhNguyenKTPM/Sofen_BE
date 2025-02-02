PGDMP                       |            sofendb    16.1    16.1 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    65906    sofendb    DATABASE     �   CREATE DATABASE sofendb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE sofendb;
                sofen    false                        2615    65908    sofendb    SCHEMA        CREATE SCHEMA sofendb;
    DROP SCHEMA sofendb;
                sofen    false                        3079    67248    unaccent 	   EXTENSION     =   CREATE EXTENSION IF NOT EXISTS unaccent WITH SCHEMA sofendb;
    DROP EXTENSION unaccent;
                   false    7            �           0    0    EXTENSION unaccent    COMMENT     P   COMMENT ON EXTENSION unaccent IS 'text search dictionary that removes accents';
                        false    3                        3079    66742 	   uuid-ossp 	   EXTENSION     @   CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA sofendb;
    DROP EXTENSION "uuid-ossp";
                   false    7            �           0    0    EXTENSION "uuid-ossp"    COMMENT     W   COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';
                        false    2            	           1259    67215    booking_detail_rooms    TABLE     p   CREATE TABLE sofendb.booking_detail_rooms (
    booking_detail_id uuid NOT NULL,
    room_id bigint NOT NULL
);
 )   DROP TABLE sofendb.booking_detail_rooms;
       sofendb         heap    sofen    false    7            �            1259    66769    booking_details    TABLE     �   CREATE TABLE sofendb.booking_details (
    number_of_rooms integer,
    check_in date,
    check_out date,
    price bigint,
    booking_detail_id uuid NOT NULL,
    booking_id uuid,
    booking_status character varying(255)
);
 $   DROP TABLE sofendb.booking_details;
       sofendb         heap    sofen    false    7            �            1259    66774    bookings    TABLE     �   CREATE TABLE sofendb.bookings (
    number_of_guests integer,
    booking_date timestamp(6) without time zone,
    guest_id bigint,
    total_price bigint,
    booking_id uuid NOT NULL,
    payment_id uuid,
    user_id uuid
);
    DROP TABLE sofendb.bookings;
       sofendb         heap    sofen    false    7            �            1259    66783    guests    TABLE       CREATE TABLE sofendb.guests (
    is_women boolean,
    date_of_birth timestamp(6) without time zone,
    guest_id bigint NOT NULL,
    email character varying(255),
    name character varying(255),
    phone character varying(255),
    purpose character varying(255)
);
    DROP TABLE sofendb.guests;
       sofendb         heap    sofen    false    7            �            1259    66760 
   guests_seq    SEQUENCE     u   CREATE SEQUENCE sofendb.guests_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE sofendb.guests_seq;
       sofendb          sofen    false    7            �            1259    66791    hotel_address    TABLE     �   CREATE TABLE sofendb.hotel_address (
    hotel_address_id bigint NOT NULL,
    district character varying(255),
    nation character varying(255),
    province character varying(255),
    ward character varying(255)
);
 "   DROP TABLE sofendb.hotel_address;
       sofendb         heap    sofen    false    7            �            1259    66790 "   hotel_address_hotel_address_id_seq    SEQUENCE     �   CREATE SEQUENCE sofendb.hotel_address_hotel_address_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 :   DROP SEQUENCE sofendb.hotel_address_hotel_address_id_seq;
       sofendb          sofen    false    229    7            �           0    0 "   hotel_address_hotel_address_id_seq    SEQUENCE OWNED BY     k   ALTER SEQUENCE sofendb.hotel_address_hotel_address_id_seq OWNED BY sofendb.hotel_address.hotel_address_id;
          sofendb          sofen    false    228            �            1259    66126    hotel_address_seq    SEQUENCE     |   CREATE SEQUENCE sofendb.hotel_address_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE sofendb.hotel_address_seq;
       sofendb          sofen    false    7            �            1259    66800    hotel_contacts    TABLE       CREATE TABLE sofendb.hotel_contacts (
    phone_number_code integer,
    hotel_contact_id bigint NOT NULL,
    email character varying(255),
    facebook character varying(255),
    instagram character varying(255),
    phone_number character varying(255)
);
 #   DROP TABLE sofendb.hotel_contacts;
       sofendb         heap    sofen    false    7            �            1259    66799 #   hotel_contacts_hotel_contact_id_seq    SEQUENCE     �   CREATE SEQUENCE sofendb.hotel_contacts_hotel_contact_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ;   DROP SEQUENCE sofendb.hotel_contacts_hotel_contact_id_seq;
       sofendb          sofen    false    231    7            �           0    0 #   hotel_contacts_hotel_contact_id_seq    SEQUENCE OWNED BY     m   ALTER SEQUENCE sofendb.hotel_contacts_hotel_contact_id_seq OWNED BY sofendb.hotel_contacts.hotel_contact_id;
          sofendb          sofen    false    230            �            1259    66127    hotel_contacts_seq    SEQUENCE     }   CREATE SEQUENCE sofendb.hotel_contacts_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE sofendb.hotel_contacts_seq;
       sofendb          sofen    false    7            �            1259    66812    hotel_images    TABLE     �   CREATE TABLE sofendb.hotel_images (
    is_main boolean,
    hotel_image_id bigint NOT NULL,
    hotel_id uuid,
    alt character varying(255),
    url character varying(255)
);
 !   DROP TABLE sofendb.hotel_images;
       sofendb         heap    sofen    false    7            �            1259    66761    hotel_images_seq    SEQUENCE     {   CREATE SEQUENCE sofendb.hotel_images_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE sofendb.hotel_images_seq;
       sofendb          sofen    false    7            �            1259    66820    hotel_policies    TABLE     r  CREATE TABLE sofendb.hotel_policies (
    hotel_policy_id bigint NOT NULL,
    name character varying(50) NOT NULL,
    status character varying(255),
    CONSTRAINT hotel_policies_status_check CHECK (((status)::text = ANY ((ARRAY['ACTIVE'::character varying, 'INACTIVE'::character varying, 'DEACTIVATED'::character varying, 'DELETED'::character varying])::text[])))
);
 #   DROP TABLE sofendb.hotel_policies;
       sofendb         heap    sofen    false    7            �            1259    66819 "   hotel_policies_hotel_policy_id_seq    SEQUENCE     �   CREATE SEQUENCE sofendb.hotel_policies_hotel_policy_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 :   DROP SEQUENCE sofendb.hotel_policies_hotel_policy_id_seq;
       sofendb          sofen    false    234    7            �           0    0 "   hotel_policies_hotel_policy_id_seq    SEQUENCE OWNED BY     k   ALTER SEQUENCE sofendb.hotel_policies_hotel_policy_id_seq OWNED BY sofendb.hotel_policies.hotel_policy_id;
          sofendb          sofen    false    233                       1259    67177    hotel_policy_details    TABLE     �   CREATE TABLE sofendb.hotel_policy_details (
    description character varying(500),
    name character varying(50),
    hotel_policy_id bigint NOT NULL,
    language_id integer NOT NULL
);
 )   DROP TABLE sofendb.hotel_policy_details;
       sofendb         heap    sofen    false    7            �            1259    66836    hotel_service_detail    TABLE     �   CREATE TABLE sofendb.hotel_service_detail (
    language_id integer NOT NULL,
    hotel_service_id bigint NOT NULL,
    name character varying(50),
    description character varying(500)
);
 )   DROP TABLE sofendb.hotel_service_detail;
       sofendb         heap    sofen    false    7            �            1259    66844    hotel_services    TABLE     �  CREATE TABLE sofendb.hotel_services (
    hotel_service_id bigint NOT NULL,
    price bigint,
    name character varying(50) NOT NULL,
    icon character varying(500),
    status character varying(255),
    CONSTRAINT hotel_services_status_check CHECK (((status)::text = ANY ((ARRAY['ACTIVE'::character varying, 'INACTIVE'::character varying, 'DEACTIVATED'::character varying, 'DELETED'::character varying])::text[])))
);
 #   DROP TABLE sofendb.hotel_services;
       sofendb         heap    sofen    false    7            �            1259    66843 #   hotel_services_hotel_service_id_seq    SEQUENCE     �   CREATE SEQUENCE sofendb.hotel_services_hotel_service_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ;   DROP SEQUENCE sofendb.hotel_services_hotel_service_id_seq;
       sofendb          sofen    false    237    7                        0    0 #   hotel_services_hotel_service_id_seq    SEQUENCE OWNED BY     m   ALTER SEQUENCE sofendb.hotel_services_hotel_service_id_seq OWNED BY sofendb.hotel_services.hotel_service_id;
          sofendb          sofen    false    236            �            1259    66855    hotels    TABLE       CREATE TABLE sofendb.hotels (
    latitude double precision,
    longitude double precision,
    hotel_address_id bigint,
    hotel_contact_id bigint,
    hotel_id uuid NOT NULL,
    name character varying(50),
    address_detail character varying(100),
    description character varying(1000),
    status character varying(255),
    CONSTRAINT hotels_status_check CHECK (((status)::text = ANY ((ARRAY['ACTIVE'::character varying, 'INACTIVE'::character varying, 'DEACTIVATED'::character varying, 'DELETED'::character varying])::text[])))
);
    DROP TABLE sofendb.hotels;
       sofendb         heap    sofen    false    7                       1259    67184    hotels_hotel_policies    TABLE     p   CREATE TABLE sofendb.hotels_hotel_policies (
    hotel_id uuid NOT NULL,
    hotel_policy_id bigint NOT NULL
);
 *   DROP TABLE sofendb.hotels_hotel_policies;
       sofendb         heap    sofen    false    7            �            1259    66872    hotels_hotel_services    TABLE     q   CREATE TABLE sofendb.hotels_hotel_services (
    hotel_service_id bigint NOT NULL,
    hotel_id uuid NOT NULL
);
 *   DROP TABLE sofendb.hotels_hotel_services;
       sofendb         heap    sofen    false    7            �            1259    66876 	   languages    TABLE     �   CREATE TABLE sofendb.languages (
    code character varying(2),
    is_default boolean,
    language_id integer NOT NULL,
    name character varying(50)
);
    DROP TABLE sofendb.languages;
       sofendb         heap    sofen    false    7            �            1259    66875    languages_language_id_seq    SEQUENCE     �   CREATE SEQUENCE sofendb.languages_language_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE sofendb.languages_language_id_seq;
       sofendb          sofen    false    7    241                       0    0    languages_language_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE sofendb.languages_language_id_seq OWNED BY sofendb.languages.language_id;
          sofendb          sofen    false    240            �            1259    66882    payments    TABLE     �   CREATE TABLE sofendb.payments (
    payment_date timestamp(6) without time zone,
    payment_id uuid NOT NULL,
    payment_method character varying(255),
    payment_status character varying(255)
);
    DROP TABLE sofendb.payments;
       sofendb         heap    sofen    false    7            �            1259    66889    promotion_detail    TABLE     �   CREATE TABLE sofendb.promotion_detail (
    language_id integer NOT NULL,
    promotion_id bigint NOT NULL,
    name character varying(50),
    description character varying(500)
);
 %   DROP TABLE sofendb.promotion_detail;
       sofendb         heap    sofen    false    7            �            1259    66896 
   promotions    TABLE     �   CREATE TABLE sofendb.promotions (
    discount_percent integer,
    end_date timestamp(6) without time zone,
    promotion_id bigint NOT NULL,
    start_date timestamp(6) without time zone
);
    DROP TABLE sofendb.promotions;
       sofendb         heap    sofen    false    7            �            1259    66901    promotions_hotels    TABLE     i   CREATE TABLE sofendb.promotions_hotels (
    promotion_id bigint NOT NULL,
    hotel_id uuid NOT NULL
);
 &   DROP TABLE sofendb.promotions_hotels;
       sofendb         heap    sofen    false    7            �            1259    66762    promotions_seq    SEQUENCE     y   CREATE SEQUENCE sofendb.promotions_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE sofendb.promotions_seq;
       sofendb          sofen    false    7            �            1259    66905    roles    TABLE     �  CREATE TABLE sofendb.roles (
    role_id bigint NOT NULL,
    role character varying(255),
    CONSTRAINT roles_role_check CHECK (((role)::text = ANY ((ARRAY['ROLE_USER'::character varying, 'ROLE_MANAGER_MASTER'::character varying, 'ROLE_MANAGER'::character varying, 'ROLE_ADMIN_MASTER'::character varying, 'ROLE_ADMIN'::character varying, 'ROLE_EMPLOYEE'::character varying])::text[])))
);
    DROP TABLE sofendb.roles;
       sofendb         heap    sofen    false    7            �            1259    66904    roles_role_id_seq    SEQUENCE     {   CREATE SEQUENCE sofendb.roles_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE sofendb.roles_role_id_seq;
       sofendb          sofen    false    7    247                       0    0    roles_role_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE sofendb.roles_role_id_seq OWNED BY sofendb.roles.role_id;
          sofendb          sofen    false    246            �            1259    66912    room_furniture_details    TABLE     �   CREATE TABLE sofendb.room_furniture_details (
    language_id integer NOT NULL,
    furniture_id bigint NOT NULL,
    name character varying(50),
    description character varying(500)
);
 +   DROP TABLE sofendb.room_furniture_details;
       sofendb         heap    sofen    false    7            �            1259    66919    room_furnitures    TABLE     p   CREATE TABLE sofendb.room_furnitures (
    room_furniture_id bigint NOT NULL,
    name character varying(50)
);
 $   DROP TABLE sofendb.room_furnitures;
       sofendb         heap    sofen    false    7            �            1259    66924    room_furnitures_rooms    TABLE     s   CREATE TABLE sofendb.room_furnitures_rooms (
    room_furniture_id bigint NOT NULL,
    room_id bigint NOT NULL
);
 *   DROP TABLE sofendb.room_furnitures_rooms;
       sofendb         heap    sofen    false    7            �            1259    66763    room_furnitures_seq    SEQUENCE     ~   CREATE SEQUENCE sofendb.room_furnitures_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE sofendb.room_furnitures_seq;
       sofendb          sofen    false    7            �            1259    66928    room_images    TABLE     �   CREATE TABLE sofendb.room_images (
    is_main boolean,
    room_id bigint NOT NULL,
    alt character varying(255),
    status character varying(255),
    url character varying(255)
);
     DROP TABLE sofendb.room_images;
       sofendb         heap    sofen    false    7            �            1259    66927    room_images_room_id_seq    SEQUENCE     �   CREATE SEQUENCE sofendb.room_images_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE sofendb.room_images_room_id_seq;
       sofendb          sofen    false    252    7                       0    0    room_images_room_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE sofendb.room_images_room_id_seq OWNED BY sofendb.room_images.room_id;
          sofendb          sofen    false    251                        1259    66951    room_type_detail    TABLE     �   CREATE TABLE sofendb.room_type_detail (
    language_id integer NOT NULL,
    room_type_id bigint NOT NULL,
    name character varying(50),
    description character varying(500)
);
 %   DROP TABLE sofendb.room_type_detail;
       sofendb         heap    sofen    false    7            �            1259    66936 
   room_types    TABLE     �   CREATE TABLE sofendb.room_types (
    area integer,
    max_adults integer,
    max_children integer,
    price bigint,
    room_type_id bigint NOT NULL,
    name character varying(50) NOT NULL
);
    DROP TABLE sofendb.room_types;
       sofendb         heap    sofen    false    7            �            1259    66764    room_types_seq    SEQUENCE     y   CREATE SEQUENCE sofendb.room_types_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE sofendb.room_types_seq;
       sofendb          sofen    false    7            �            1259    66944    rooms    TABLE     �  CREATE TABLE sofendb.rooms (
    amount integer,
    quantity_remaining integer,
    room_id bigint NOT NULL,
    room_type_id bigint,
    hotel_id uuid,
    status character varying(255),
    CONSTRAINT rooms_status_check CHECK (((status)::text = ANY ((ARRAY['ACTIVE'::character varying, 'INACTIVE'::character varying, 'DEACTIVATED'::character varying, 'DELETED'::character varying])::text[])))
);
    DROP TABLE sofendb.rooms;
       sofendb         heap    sofen    false    7            �            1259    66943    rooms_room_id_seq    SEQUENCE     {   CREATE SEQUENCE sofendb.rooms_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE sofendb.rooms_room_id_seq;
       sofendb          sofen    false    255    7                       0    0    rooms_room_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE sofendb.rooms_room_id_seq OWNED BY sofendb.rooms.room_id;
          sofendb          sofen    false    254                       1259    66959    tokens    TABLE     �  CREATE TABLE sofendb.tokens (
    is_revoked boolean,
    expired_at timestamp(6) without time zone,
    token_id bigint NOT NULL,
    account_id uuid NOT NULL,
    token character varying(255),
    token_type character varying(255),
    CONSTRAINT tokens_token_type_check CHECK (((token_type)::text = ANY ((ARRAY['ACCESS_TOKEN'::character varying, 'REFRESH_TOKEN'::character varying, 'RESET_PASSWORD_TOKEN'::character varying, 'GG_TOKEN'::character varying])::text[])))
);
    DROP TABLE sofendb.tokens;
       sofendb         heap    sofen    false    7                       1259    66958    tokens_token_id_seq    SEQUENCE     }   CREATE SEQUENCE sofendb.tokens_token_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE sofendb.tokens_token_id_seq;
       sofendb          sofen    false    7    258                       0    0    tokens_token_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE sofendb.tokens_token_id_seq OWNED BY sofendb.tokens.token_id;
          sofendb          sofen    false    257                       1259    66968    user_hotels    TABLE     \   CREATE TABLE sofendb.user_hotels (
    hotel_id uuid NOT NULL,
    user_id uuid NOT NULL
);
     DROP TABLE sofendb.user_hotels;
       sofendb         heap    sofen    false    7                       1259    66971    user_ranking    TABLE     �   CREATE TABLE sofendb.user_ranking (
    reward_percent integer,
    user_ranking_id bigint NOT NULL,
    name character varying(255)
);
 !   DROP TABLE sofendb.user_ranking;
       sofendb         heap    sofen    false    7                       1259    66976    user_ranking_detail    TABLE     �   CREATE TABLE sofendb.user_ranking_detail (
    language_id integer NOT NULL,
    user_ranking_id bigint NOT NULL,
    name character varying(50),
    description character varying(500)
);
 (   DROP TABLE sofendb.user_ranking_detail;
       sofendb         heap    sofen    false    7            �            1259    66765    user_ranking_seq    SEQUENCE     {   CREATE SEQUENCE sofendb.user_ranking_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE sofendb.user_ranking_seq;
       sofendb          sofen    false    7                       1259    66983    users    TABLE     �  CREATE TABLE sofendb.users (
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
    CONSTRAINT users_status_check CHECK (((status)::text = ANY ((ARRAY['ACTIVE'::character varying, 'INACTIVE'::character varying, 'DEACTIVATED'::character varying, 'DELETED'::character varying])::text[])))
);
    DROP TABLE sofendb.users;
       sofendb         heap    sofen    false    7            �           2604    66794    hotel_address hotel_address_id    DEFAULT     �   ALTER TABLE ONLY sofendb.hotel_address ALTER COLUMN hotel_address_id SET DEFAULT nextval('sofendb.hotel_address_hotel_address_id_seq'::regclass);
 N   ALTER TABLE sofendb.hotel_address ALTER COLUMN hotel_address_id DROP DEFAULT;
       sofendb          sofen    false    228    229    229            �           2604    66803    hotel_contacts hotel_contact_id    DEFAULT     �   ALTER TABLE ONLY sofendb.hotel_contacts ALTER COLUMN hotel_contact_id SET DEFAULT nextval('sofendb.hotel_contacts_hotel_contact_id_seq'::regclass);
 O   ALTER TABLE sofendb.hotel_contacts ALTER COLUMN hotel_contact_id DROP DEFAULT;
       sofendb          sofen    false    231    230    231            �           2604    66823    hotel_policies hotel_policy_id    DEFAULT     �   ALTER TABLE ONLY sofendb.hotel_policies ALTER COLUMN hotel_policy_id SET DEFAULT nextval('sofendb.hotel_policies_hotel_policy_id_seq'::regclass);
 N   ALTER TABLE sofendb.hotel_policies ALTER COLUMN hotel_policy_id DROP DEFAULT;
       sofendb          sofen    false    234    233    234            �           2604    66847    hotel_services hotel_service_id    DEFAULT     �   ALTER TABLE ONLY sofendb.hotel_services ALTER COLUMN hotel_service_id SET DEFAULT nextval('sofendb.hotel_services_hotel_service_id_seq'::regclass);
 O   ALTER TABLE sofendb.hotel_services ALTER COLUMN hotel_service_id DROP DEFAULT;
       sofendb          sofen    false    237    236    237            �           2604    66879    languages language_id    DEFAULT     �   ALTER TABLE ONLY sofendb.languages ALTER COLUMN language_id SET DEFAULT nextval('sofendb.languages_language_id_seq'::regclass);
 E   ALTER TABLE sofendb.languages ALTER COLUMN language_id DROP DEFAULT;
       sofendb          sofen    false    241    240    241            �           2604    66908    roles role_id    DEFAULT     p   ALTER TABLE ONLY sofendb.roles ALTER COLUMN role_id SET DEFAULT nextval('sofendb.roles_role_id_seq'::regclass);
 =   ALTER TABLE sofendb.roles ALTER COLUMN role_id DROP DEFAULT;
       sofendb          sofen    false    246    247    247            �           2604    66931    room_images room_id    DEFAULT     |   ALTER TABLE ONLY sofendb.room_images ALTER COLUMN room_id SET DEFAULT nextval('sofendb.room_images_room_id_seq'::regclass);
 C   ALTER TABLE sofendb.room_images ALTER COLUMN room_id DROP DEFAULT;
       sofendb          sofen    false    251    252    252            �           2604    66947    rooms room_id    DEFAULT     p   ALTER TABLE ONLY sofendb.rooms ALTER COLUMN room_id SET DEFAULT nextval('sofendb.rooms_room_id_seq'::regclass);
 =   ALTER TABLE sofendb.rooms ALTER COLUMN room_id DROP DEFAULT;
       sofendb          sofen    false    254    255    255            �           2604    66962    tokens token_id    DEFAULT     t   ALTER TABLE ONLY sofendb.tokens ALTER COLUMN token_id SET DEFAULT nextval('sofendb.tokens_token_id_seq'::regclass);
 ?   ALTER TABLE sofendb.tokens ALTER COLUMN token_id DROP DEFAULT;
       sofendb          sofen    false    258    257    258            �          0    67215    booking_detail_rooms 
   TABLE DATA           K   COPY sofendb.booking_detail_rooms (booking_detail_id, room_id) FROM stdin;
    sofendb          sofen    false    265   ?      �          0    66769    booking_details 
   TABLE DATA           �   COPY sofendb.booking_details (number_of_rooms, check_in, check_out, price, booking_detail_id, booking_id, booking_status) FROM stdin;
    sofendb          sofen    false    225   �      �          0    66774    bookings 
   TABLE DATA           {   COPY sofendb.bookings (number_of_guests, booking_date, guest_id, total_price, booking_id, payment_id, user_id) FROM stdin;
    sofendb          sofen    false    226   H      �          0    66783    guests 
   TABLE DATA           a   COPY sofendb.guests (is_women, date_of_birth, guest_id, email, name, phone, purpose) FROM stdin;
    sofendb          sofen    false    227   �      �          0    66791    hotel_address 
   TABLE DATA           \   COPY sofendb.hotel_address (hotel_address_id, district, nation, province, ward) FROM stdin;
    sofendb          sofen    false    229         �          0    66800    hotel_contacts 
   TABLE DATA           x   COPY sofendb.hotel_contacts (phone_number_code, hotel_contact_id, email, facebook, instagram, phone_number) FROM stdin;
    sofendb          sofen    false    231   �      �          0    66812    hotel_images 
   TABLE DATA           T   COPY sofendb.hotel_images (is_main, hotel_image_id, hotel_id, alt, url) FROM stdin;
    sofendb          sofen    false    232   8      �          0    66820    hotel_policies 
   TABLE DATA           H   COPY sofendb.hotel_policies (hotel_policy_id, name, status) FROM stdin;
    sofendb          sofen    false    234   U      �          0    67177    hotel_policy_details 
   TABLE DATA           `   COPY sofendb.hotel_policy_details (description, name, hotel_policy_id, language_id) FROM stdin;
    sofendb          sofen    false    263   �      �          0    66836    hotel_service_detail 
   TABLE DATA           a   COPY sofendb.hotel_service_detail (language_id, hotel_service_id, name, description) FROM stdin;
    sofendb          sofen    false    235   �      �          0    66844    hotel_services 
   TABLE DATA           V   COPY sofendb.hotel_services (hotel_service_id, price, name, icon, status) FROM stdin;
    sofendb          sofen    false    237   F      �          0    66855    hotels 
   TABLE DATA           �   COPY sofendb.hotels (latitude, longitude, hotel_address_id, hotel_contact_id, hotel_id, name, address_detail, description, status) FROM stdin;
    sofendb          sofen    false    238   �      �          0    67184    hotels_hotel_policies 
   TABLE DATA           K   COPY sofendb.hotels_hotel_policies (hotel_id, hotel_policy_id) FROM stdin;
    sofendb          sofen    false    264   �      �          0    66872    hotels_hotel_services 
   TABLE DATA           L   COPY sofendb.hotels_hotel_services (hotel_service_id, hotel_id) FROM stdin;
    sofendb          sofen    false    239         �          0    66876 	   languages 
   TABLE DATA           I   COPY sofendb.languages (code, is_default, language_id, name) FROM stdin;
    sofendb          sofen    false    241   �      �          0    66882    payments 
   TABLE DATA           ]   COPY sofendb.payments (payment_date, payment_id, payment_method, payment_status) FROM stdin;
    sofendb          sofen    false    242   �      �          0    66889    promotion_detail 
   TABLE DATA           Y   COPY sofendb.promotion_detail (language_id, promotion_id, name, description) FROM stdin;
    sofendb          sofen    false    243   	      �          0    66896 
   promotions 
   TABLE DATA           [   COPY sofendb.promotions (discount_percent, end_date, promotion_id, start_date) FROM stdin;
    sofendb          sofen    false    244    	      �          0    66901    promotions_hotels 
   TABLE DATA           D   COPY sofendb.promotions_hotels (promotion_id, hotel_id) FROM stdin;
    sofendb          sofen    false    245   =	      �          0    66905    roles 
   TABLE DATA           /   COPY sofendb.roles (role_id, role) FROM stdin;
    sofendb          sofen    false    247   Z	      �          0    66912    room_furniture_details 
   TABLE DATA           _   COPY sofendb.room_furniture_details (language_id, furniture_id, name, description) FROM stdin;
    sofendb          sofen    false    248   �	      �          0    66919    room_furnitures 
   TABLE DATA           C   COPY sofendb.room_furnitures (room_furniture_id, name) FROM stdin;
    sofendb          sofen    false    249   :      �          0    66924    room_furnitures_rooms 
   TABLE DATA           L   COPY sofendb.room_furnitures_rooms (room_furniture_id, room_id) FROM stdin;
    sofendb          sofen    false    250   �      �          0    66928    room_images 
   TABLE DATA           J   COPY sofendb.room_images (is_main, room_id, alt, status, url) FROM stdin;
    sofendb          sofen    false    252         �          0    66951    room_type_detail 
   TABLE DATA           Y   COPY sofendb.room_type_detail (language_id, room_type_id, name, description) FROM stdin;
    sofendb          sofen    false    256   *      �          0    66936 
   room_types 
   TABLE DATA           `   COPY sofendb.room_types (area, max_adults, max_children, price, room_type_id, name) FROM stdin;
    sofendb          sofen    false    253         �          0    66944    rooms 
   TABLE DATA           e   COPY sofendb.rooms (amount, quantity_remaining, room_id, room_type_id, hotel_id, status) FROM stdin;
    sofendb          sofen    false    255   b      �          0    66959    tokens 
   TABLE DATA           b   COPY sofendb.tokens (is_revoked, expired_at, token_id, account_id, token, token_type) FROM stdin;
    sofendb          sofen    false    258   '      �          0    66968    user_hotels 
   TABLE DATA           9   COPY sofendb.user_hotels (hotel_id, user_id) FROM stdin;
    sofendb          sofen    false    259   D      �          0    66971    user_ranking 
   TABLE DATA           N   COPY sofendb.user_ranking (reward_percent, user_ranking_id, name) FROM stdin;
    sofendb          sofen    false    260   a      �          0    66976    user_ranking_detail 
   TABLE DATA           _   COPY sofendb.user_ranking_detail (language_id, user_ranking_id, name, description) FROM stdin;
    sofendb          sofen    false    261   �      �          0    66983    users 
   TABLE DATA           �   COPY sofendb.users (date_of_birth, is_women, point, created_at, role_id, user_ranking_id, user_id, address, email, full_name, password, phone_number, status) FROM stdin;
    sofendb          sofen    false    262   �                 0    0 
   guests_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('sofendb.guests_seq', 1, false);
          sofendb          sofen    false    219                       0    0 "   hotel_address_hotel_address_id_seq    SEQUENCE SET     R   SELECT pg_catalog.setval('sofendb.hotel_address_hotel_address_id_seq', 1, false);
          sofendb          sofen    false    228                       0    0    hotel_address_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('sofendb.hotel_address_seq', 151, true);
          sofendb          sofen    false    217            	           0    0 #   hotel_contacts_hotel_contact_id_seq    SEQUENCE SET     S   SELECT pg_catalog.setval('sofendb.hotel_contacts_hotel_contact_id_seq', 1, false);
          sofendb          sofen    false    230            
           0    0    hotel_contacts_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('sofendb.hotel_contacts_seq', 151, true);
          sofendb          sofen    false    218                       0    0    hotel_images_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('sofendb.hotel_images_seq', 1, false);
          sofendb          sofen    false    220                       0    0 "   hotel_policies_hotel_policy_id_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('sofendb.hotel_policies_hotel_policy_id_seq', 6, true);
          sofendb          sofen    false    233                       0    0 #   hotel_services_hotel_service_id_seq    SEQUENCE SET     R   SELECT pg_catalog.setval('sofendb.hotel_services_hotel_service_id_seq', 5, true);
          sofendb          sofen    false    236                       0    0    languages_language_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('sofendb.languages_language_id_seq', 2, true);
          sofendb          sofen    false    240                       0    0    promotions_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('sofendb.promotions_seq', 1, false);
          sofendb          sofen    false    221                       0    0    roles_role_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('sofendb.roles_role_id_seq', 6, true);
          sofendb          sofen    false    246                       0    0    room_furnitures_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('sofendb.room_furnitures_seq', 51, true);
          sofendb          sofen    false    222                       0    0    room_images_room_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('sofendb.room_images_room_id_seq', 1, false);
          sofendb          sofen    false    251                       0    0    room_types_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('sofendb.room_types_seq', 51, true);
          sofendb          sofen    false    223                       0    0    rooms_room_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('sofendb.rooms_room_id_seq', 1, false);
          sofendb          sofen    false    254                       0    0    tokens_token_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('sofendb.tokens_token_id_seq', 1, false);
          sofendb          sofen    false    257                       0    0    user_ranking_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('sofendb.user_ranking_seq', 1, false);
          sofendb          sofen    false    224            �           2606    66773 $   booking_details booking_details_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY sofendb.booking_details
    ADD CONSTRAINT booking_details_pkey PRIMARY KEY (booking_detail_id);
 O   ALTER TABLE ONLY sofendb.booking_details DROP CONSTRAINT booking_details_pkey;
       sofendb            sofen    false    225            �           2606    66780    bookings bookings_guest_id_key 
   CONSTRAINT     ^   ALTER TABLE ONLY sofendb.bookings
    ADD CONSTRAINT bookings_guest_id_key UNIQUE (guest_id);
 I   ALTER TABLE ONLY sofendb.bookings DROP CONSTRAINT bookings_guest_id_key;
       sofendb            sofen    false    226            �           2606    66782     bookings bookings_payment_id_key 
   CONSTRAINT     b   ALTER TABLE ONLY sofendb.bookings
    ADD CONSTRAINT bookings_payment_id_key UNIQUE (payment_id);
 K   ALTER TABLE ONLY sofendb.bookings DROP CONSTRAINT bookings_payment_id_key;
       sofendb            sofen    false    226            �           2606    66778    bookings bookings_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY sofendb.bookings
    ADD CONSTRAINT bookings_pkey PRIMARY KEY (booking_id);
 A   ALTER TABLE ONLY sofendb.bookings DROP CONSTRAINT bookings_pkey;
       sofendb            sofen    false    226            �           2606    66789    guests guests_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY sofendb.guests
    ADD CONSTRAINT guests_pkey PRIMARY KEY (guest_id);
 =   ALTER TABLE ONLY sofendb.guests DROP CONSTRAINT guests_pkey;
       sofendb            sofen    false    227            �           2606    66798     hotel_address hotel_address_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY sofendb.hotel_address
    ADD CONSTRAINT hotel_address_pkey PRIMARY KEY (hotel_address_id);
 K   ALTER TABLE ONLY sofendb.hotel_address DROP CONSTRAINT hotel_address_pkey;
       sofendb            sofen    false    229            �           2606    66809 '   hotel_contacts hotel_contacts_email_key 
   CONSTRAINT     d   ALTER TABLE ONLY sofendb.hotel_contacts
    ADD CONSTRAINT hotel_contacts_email_key UNIQUE (email);
 R   ALTER TABLE ONLY sofendb.hotel_contacts DROP CONSTRAINT hotel_contacts_email_key;
       sofendb            sofen    false    231            �           2606    66811 .   hotel_contacts hotel_contacts_phone_number_key 
   CONSTRAINT     r   ALTER TABLE ONLY sofendb.hotel_contacts
    ADD CONSTRAINT hotel_contacts_phone_number_key UNIQUE (phone_number);
 Y   ALTER TABLE ONLY sofendb.hotel_contacts DROP CONSTRAINT hotel_contacts_phone_number_key;
       sofendb            sofen    false    231            �           2606    66807 "   hotel_contacts hotel_contacts_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY sofendb.hotel_contacts
    ADD CONSTRAINT hotel_contacts_pkey PRIMARY KEY (hotel_contact_id);
 M   ALTER TABLE ONLY sofendb.hotel_contacts DROP CONSTRAINT hotel_contacts_pkey;
       sofendb            sofen    false    231            �           2606    66818    hotel_images hotel_images_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY sofendb.hotel_images
    ADD CONSTRAINT hotel_images_pkey PRIMARY KEY (hotel_image_id);
 I   ALTER TABLE ONLY sofendb.hotel_images DROP CONSTRAINT hotel_images_pkey;
       sofendb            sofen    false    232            �           2606    66828 &   hotel_policies hotel_policies_name_key 
   CONSTRAINT     b   ALTER TABLE ONLY sofendb.hotel_policies
    ADD CONSTRAINT hotel_policies_name_key UNIQUE (name);
 Q   ALTER TABLE ONLY sofendb.hotel_policies DROP CONSTRAINT hotel_policies_name_key;
       sofendb            sofen    false    234            �           2606    66826 "   hotel_policies hotel_policies_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY sofendb.hotel_policies
    ADD CONSTRAINT hotel_policies_pkey PRIMARY KEY (hotel_policy_id);
 M   ALTER TABLE ONLY sofendb.hotel_policies DROP CONSTRAINT hotel_policies_pkey;
       sofendb            sofen    false    234                       2606    67183 .   hotel_policy_details hotel_policy_details_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotel_policy_details
    ADD CONSTRAINT hotel_policy_details_pkey PRIMARY KEY (hotel_policy_id, language_id);
 Y   ALTER TABLE ONLY sofendb.hotel_policy_details DROP CONSTRAINT hotel_policy_details_pkey;
       sofendb            sofen    false    263    263            �           2606    66842 .   hotel_service_detail hotel_service_detail_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotel_service_detail
    ADD CONSTRAINT hotel_service_detail_pkey PRIMARY KEY (language_id, hotel_service_id);
 Y   ALTER TABLE ONLY sofendb.hotel_service_detail DROP CONSTRAINT hotel_service_detail_pkey;
       sofendb            sofen    false    235    235            �           2606    66854 &   hotel_services hotel_services_name_key 
   CONSTRAINT     b   ALTER TABLE ONLY sofendb.hotel_services
    ADD CONSTRAINT hotel_services_name_key UNIQUE (name);
 Q   ALTER TABLE ONLY sofendb.hotel_services DROP CONSTRAINT hotel_services_name_key;
       sofendb            sofen    false    237            �           2606    66852 "   hotel_services hotel_services_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY sofendb.hotel_services
    ADD CONSTRAINT hotel_services_pkey PRIMARY KEY (hotel_service_id);
 M   ALTER TABLE ONLY sofendb.hotel_services DROP CONSTRAINT hotel_services_pkey;
       sofendb            sofen    false    237            �           2606    66864 "   hotels hotels_hotel_address_id_key 
   CONSTRAINT     j   ALTER TABLE ONLY sofendb.hotels
    ADD CONSTRAINT hotels_hotel_address_id_key UNIQUE (hotel_address_id);
 M   ALTER TABLE ONLY sofendb.hotels DROP CONSTRAINT hotels_hotel_address_id_key;
       sofendb            sofen    false    238            �           2606    66866 "   hotels hotels_hotel_contact_id_key 
   CONSTRAINT     j   ALTER TABLE ONLY sofendb.hotels
    ADD CONSTRAINT hotels_hotel_contact_id_key UNIQUE (hotel_contact_id);
 M   ALTER TABLE ONLY sofendb.hotels DROP CONSTRAINT hotels_hotel_contact_id_key;
       sofendb            sofen    false    238            �           2606    66868    hotels hotels_name_key 
   CONSTRAINT     R   ALTER TABLE ONLY sofendb.hotels
    ADD CONSTRAINT hotels_name_key UNIQUE (name);
 A   ALTER TABLE ONLY sofendb.hotels DROP CONSTRAINT hotels_name_key;
       sofendb            sofen    false    238            �           2606    66862    hotels hotels_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY sofendb.hotels
    ADD CONSTRAINT hotels_pkey PRIMARY KEY (hotel_id);
 =   ALTER TABLE ONLY sofendb.hotels DROP CONSTRAINT hotels_pkey;
       sofendb            sofen    false    238            �           2606    66881    languages languages_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY sofendb.languages
    ADD CONSTRAINT languages_pkey PRIMARY KEY (language_id);
 C   ALTER TABLE ONLY sofendb.languages DROP CONSTRAINT languages_pkey;
       sofendb            sofen    false    241            �           2606    66888    payments payments_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY sofendb.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (payment_id);
 A   ALTER TABLE ONLY sofendb.payments DROP CONSTRAINT payments_pkey;
       sofendb            sofen    false    242            �           2606    66895 &   promotion_detail promotion_detail_pkey 
   CONSTRAINT     |   ALTER TABLE ONLY sofendb.promotion_detail
    ADD CONSTRAINT promotion_detail_pkey PRIMARY KEY (language_id, promotion_id);
 Q   ALTER TABLE ONLY sofendb.promotion_detail DROP CONSTRAINT promotion_detail_pkey;
       sofendb            sofen    false    243    243            �           2606    66900    promotions promotions_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY sofendb.promotions
    ADD CONSTRAINT promotions_pkey PRIMARY KEY (promotion_id);
 E   ALTER TABLE ONLY sofendb.promotions DROP CONSTRAINT promotions_pkey;
       sofendb            sofen    false    244            �           2606    66911    roles roles_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY sofendb.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (role_id);
 ;   ALTER TABLE ONLY sofendb.roles DROP CONSTRAINT roles_pkey;
       sofendb            sofen    false    247            �           2606    66918 2   room_furniture_details room_furniture_details_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY sofendb.room_furniture_details
    ADD CONSTRAINT room_furniture_details_pkey PRIMARY KEY (language_id, furniture_id);
 ]   ALTER TABLE ONLY sofendb.room_furniture_details DROP CONSTRAINT room_furniture_details_pkey;
       sofendb            sofen    false    248    248            �           2606    66923 $   room_furnitures room_furnitures_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY sofendb.room_furnitures
    ADD CONSTRAINT room_furnitures_pkey PRIMARY KEY (room_furniture_id);
 O   ALTER TABLE ONLY sofendb.room_furnitures DROP CONSTRAINT room_furnitures_pkey;
       sofendb            sofen    false    249            �           2606    66935    room_images room_images_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY sofendb.room_images
    ADD CONSTRAINT room_images_pkey PRIMARY KEY (room_id);
 G   ALTER TABLE ONLY sofendb.room_images DROP CONSTRAINT room_images_pkey;
       sofendb            sofen    false    252                       2606    66957 &   room_type_detail room_type_detail_pkey 
   CONSTRAINT     |   ALTER TABLE ONLY sofendb.room_type_detail
    ADD CONSTRAINT room_type_detail_pkey PRIMARY KEY (language_id, room_type_id);
 Q   ALTER TABLE ONLY sofendb.room_type_detail DROP CONSTRAINT room_type_detail_pkey;
       sofendb            sofen    false    256    256            �           2606    66942    room_types room_types_name_key 
   CONSTRAINT     Z   ALTER TABLE ONLY sofendb.room_types
    ADD CONSTRAINT room_types_name_key UNIQUE (name);
 I   ALTER TABLE ONLY sofendb.room_types DROP CONSTRAINT room_types_name_key;
       sofendb            sofen    false    253                       2606    66940    room_types room_types_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY sofendb.room_types
    ADD CONSTRAINT room_types_pkey PRIMARY KEY (room_type_id);
 E   ALTER TABLE ONLY sofendb.room_types DROP CONSTRAINT room_types_pkey;
       sofendb            sofen    false    253                       2606    66950    rooms rooms_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY sofendb.rooms
    ADD CONSTRAINT rooms_pkey PRIMARY KEY (room_id);
 ;   ALTER TABLE ONLY sofendb.rooms DROP CONSTRAINT rooms_pkey;
       sofendb            sofen    false    255                       2606    66967    tokens tokens_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY sofendb.tokens
    ADD CONSTRAINT tokens_pkey PRIMARY KEY (token_id);
 =   ALTER TABLE ONLY sofendb.tokens DROP CONSTRAINT tokens_pkey;
       sofendb            sofen    false    258                       2606    66982 ,   user_ranking_detail user_ranking_detail_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY sofendb.user_ranking_detail
    ADD CONSTRAINT user_ranking_detail_pkey PRIMARY KEY (language_id, user_ranking_id);
 W   ALTER TABLE ONLY sofendb.user_ranking_detail DROP CONSTRAINT user_ranking_detail_pkey;
       sofendb            sofen    false    261    261            	           2606    66975    user_ranking user_ranking_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY sofendb.user_ranking
    ADD CONSTRAINT user_ranking_pkey PRIMARY KEY (user_ranking_id);
 I   ALTER TABLE ONLY sofendb.user_ranking DROP CONSTRAINT user_ranking_pkey;
       sofendb            sofen    false    260                       2606    66990    users users_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY sofendb.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);
 ;   ALTER TABLE ONLY sofendb.users DROP CONSTRAINT users_pkey;
       sofendb            sofen    false    262            &           2606    67131 ,   room_type_detail fk1uxuyrr4jo7w3kivjj8o5ceet    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.room_type_detail
    ADD CONSTRAINT fk1uxuyrr4jo7w3kivjj8o5ceet FOREIGN KEY (room_type_id) REFERENCES sofendb.room_types(room_type_id);
 W   ALTER TABLE ONLY sofendb.room_type_detail DROP CONSTRAINT fk1uxuyrr4jo7w3kivjj8o5ceet;
       sofendb          sofen    false    4865    253    256            )           2606    67146 '   user_hotels fk2xs4pbj0cjbycdk3nlxpcwd1w    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.user_hotels
    ADD CONSTRAINT fk2xs4pbj0cjbycdk3nlxpcwd1w FOREIGN KEY (hotel_id) REFERENCES sofendb.hotels(hotel_id);
 R   ALTER TABLE ONLY sofendb.user_hotels DROP CONSTRAINT fk2xs4pbj0cjbycdk3nlxpcwd1w;
       sofendb          sofen    false    238    4845    259                       2606    67076 ,   promotion_detail fk3ch8y7e4jsvp5qdd7wrd899x3    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.promotion_detail
    ADD CONSTRAINT fk3ch8y7e4jsvp5qdd7wrd899x3 FOREIGN KEY (language_id) REFERENCES sofendb.languages(language_id);
 W   ALTER TABLE ONLY sofendb.promotion_detail DROP CONSTRAINT fk3ch8y7e4jsvp5qdd7wrd899x3;
       sofendb          sofen    false    241    243    4847                       2606    67051 "   hotels fk3r849tfl7rq9ntmo494x23aov    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotels
    ADD CONSTRAINT fk3r849tfl7rq9ntmo494x23aov FOREIGN KEY (hotel_contact_id) REFERENCES sofendb.hotel_contacts(hotel_contact_id);
 M   ALTER TABLE ONLY sofendb.hotels DROP CONSTRAINT fk3r849tfl7rq9ntmo494x23aov;
       sofendb          sofen    false    4825    238    231            /           2606    67192 0   hotel_policy_details fk4259jt6w82ygcj82a830b9aw3    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotel_policy_details
    ADD CONSTRAINT fk4259jt6w82ygcj82a830b9aw3 FOREIGN KEY (language_id) REFERENCES sofendb.languages(language_id);
 [   ALTER TABLE ONLY sofendb.hotel_policy_details DROP CONSTRAINT fk4259jt6w82ygcj82a830b9aw3;
       sofendb          sofen    false    241    263    4847            0           2606    67187 0   hotel_policy_details fk79m2y3issxuu0hfvx2d3kqomi    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotel_policy_details
    ADD CONSTRAINT fk79m2y3issxuu0hfvx2d3kqomi FOREIGN KEY (hotel_policy_id) REFERENCES sofendb.hotel_policies(hotel_policy_id);
 [   ALTER TABLE ONLY sofendb.hotel_policy_details DROP CONSTRAINT fk79m2y3issxuu0hfvx2d3kqomi;
       sofendb          sofen    false    263    234    4831                       2606    67096 2   room_furniture_details fkaiclvflm92jtge6qolb272oyh    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.room_furniture_details
    ADD CONSTRAINT fkaiclvflm92jtge6qolb272oyh FOREIGN KEY (language_id) REFERENCES sofendb.languages(language_id);
 ]   ALTER TABLE ONLY sofendb.room_furniture_details DROP CONSTRAINT fkaiclvflm92jtge6qolb272oyh;
       sofendb          sofen    false    4847    241    248                       2606    67036 0   hotel_service_detail fkb48n921uxqqlmt3fnmunlw225    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotel_service_detail
    ADD CONSTRAINT fkb48n921uxqqlmt3fnmunlw225 FOREIGN KEY (language_id) REFERENCES sofendb.languages(language_id);
 [   ALTER TABLE ONLY sofendb.hotel_service_detail DROP CONSTRAINT fkb48n921uxqqlmt3fnmunlw225;
       sofendb          sofen    false    241    235    4847                       2606    67081 ,   promotion_detail fkc2cukei4dwsx0dgwgsp2ii92m    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.promotion_detail
    ADD CONSTRAINT fkc2cukei4dwsx0dgwgsp2ii92m FOREIGN KEY (promotion_id) REFERENCES sofendb.promotions(promotion_id);
 W   ALTER TABLE ONLY sofendb.promotion_detail DROP CONSTRAINT fkc2cukei4dwsx0dgwgsp2ii92m;
       sofendb          sofen    false    244    243    4853                       2606    67041 0   hotel_service_detail fkc2xlk8ij9bw0jsc5ci0qc3b8m    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotel_service_detail
    ADD CONSTRAINT fkc2xlk8ij9bw0jsc5ci0qc3b8m FOREIGN KEY (hotel_service_id) REFERENCES sofendb.hotel_services(hotel_service_id);
 [   ALTER TABLE ONLY sofendb.hotel_service_detail DROP CONSTRAINT fkc2xlk8ij9bw0jsc5ci0qc3b8m;
       sofendb          sofen    false    235    237    4837            3           2606    67223 0   booking_detail_rooms fkdbl5xltc54fxe244hrp92v5r6    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.booking_detail_rooms
    ADD CONSTRAINT fkdbl5xltc54fxe244hrp92v5r6 FOREIGN KEY (booking_detail_id) REFERENCES sofendb.booking_details(booking_detail_id);
 [   ALTER TABLE ONLY sofendb.booking_detail_rooms DROP CONSTRAINT fkdbl5xltc54fxe244hrp92v5r6;
       sofendb          sofen    false    4809    225    265                        2606    67101 2   room_furniture_details fkelitbnvk35svwcc923nq6f0ye    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.room_furniture_details
    ADD CONSTRAINT fkelitbnvk35svwcc923nq6f0ye FOREIGN KEY (furniture_id) REFERENCES sofendb.room_furnitures(room_furniture_id);
 ]   ALTER TABLE ONLY sofendb.room_furniture_details DROP CONSTRAINT fkelitbnvk35svwcc923nq6f0ye;
       sofendb          sofen    false    249    4859    248                       2606    67016 $   bookings fkeyog2oic85xg7hsu2je2lx3s6    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.bookings
    ADD CONSTRAINT fkeyog2oic85xg7hsu2je2lx3s6 FOREIGN KEY (user_id) REFERENCES sofendb.users(user_id);
 O   ALTER TABLE ONLY sofendb.bookings DROP CONSTRAINT fkeyog2oic85xg7hsu2je2lx3s6;
       sofendb          sofen    false    4877    262    226                       2606    67066 1   hotels_hotel_services fkf2bgo418n7pifcka93wtk01ob    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotels_hotel_services
    ADD CONSTRAINT fkf2bgo418n7pifcka93wtk01ob FOREIGN KEY (hotel_service_id) REFERENCES sofendb.hotel_services(hotel_service_id);
 \   ALTER TABLE ONLY sofendb.hotels_hotel_services DROP CONSTRAINT fkf2bgo418n7pifcka93wtk01ob;
       sofendb          sofen    false    4837    239    237            '           2606    67136 ,   room_type_detail fkfkwimme1yh4jvfx16bjr1i0xg    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.room_type_detail
    ADD CONSTRAINT fkfkwimme1yh4jvfx16bjr1i0xg FOREIGN KEY (language_id) REFERENCES sofendb.languages(language_id);
 W   ALTER TABLE ONLY sofendb.room_type_detail DROP CONSTRAINT fkfkwimme1yh4jvfx16bjr1i0xg;
       sofendb          sofen    false    256    241    4847            $           2606    67126 !   rooms fkh9m2n1paq5hmd3u0klfl7wsfv    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.rooms
    ADD CONSTRAINT fkh9m2n1paq5hmd3u0klfl7wsfv FOREIGN KEY (room_type_id) REFERENCES sofendb.room_types(room_type_id);
 L   ALTER TABLE ONLY sofendb.rooms DROP CONSTRAINT fkh9m2n1paq5hmd3u0klfl7wsfv;
       sofendb          sofen    false    255    4865    253            !           2606    67111 1   room_furnitures_rooms fkhmp0hgjo8ea43wb04lb7xsqy3    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.room_furnitures_rooms
    ADD CONSTRAINT fkhmp0hgjo8ea43wb04lb7xsqy3 FOREIGN KEY (room_id) REFERENCES sofendb.rooms(room_id);
 \   ALTER TABLE ONLY sofendb.room_furnitures_rooms DROP CONSTRAINT fkhmp0hgjo8ea43wb04lb7xsqy3;
       sofendb          sofen    false    255    250    4867            +           2606    67156 /   user_ranking_detail fkjd7apv9ft2c6298xq6pkf8jtk    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.user_ranking_detail
    ADD CONSTRAINT fkjd7apv9ft2c6298xq6pkf8jtk FOREIGN KEY (user_ranking_id) REFERENCES sofendb.user_ranking(user_ranking_id);
 Z   ALTER TABLE ONLY sofendb.user_ranking_detail DROP CONSTRAINT fkjd7apv9ft2c6298xq6pkf8jtk;
       sofendb          sofen    false    260    261    4873                       2606    67011 $   bookings fkjki6p9c5yckce7owst8vxu17u    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.bookings
    ADD CONSTRAINT fkjki6p9c5yckce7owst8vxu17u FOREIGN KEY (payment_id) REFERENCES sofendb.payments(payment_id);
 O   ALTER TABLE ONLY sofendb.bookings DROP CONSTRAINT fkjki6p9c5yckce7owst8vxu17u;
       sofendb          sofen    false    226    4849    242                       2606    67091 -   promotions_hotels fkjveobtm4jffh1l5qmpdbicxwv    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.promotions_hotels
    ADD CONSTRAINT fkjveobtm4jffh1l5qmpdbicxwv FOREIGN KEY (promotion_id) REFERENCES sofendb.promotions(promotion_id);
 X   ALTER TABLE ONLY sofendb.promotions_hotels DROP CONSTRAINT fkjveobtm4jffh1l5qmpdbicxwv;
       sofendb          sofen    false    244    4853    245                       2606    67001 +   booking_details fkkbcan6ybv86uappnh0qtdmvas    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.booking_details
    ADD CONSTRAINT fkkbcan6ybv86uappnh0qtdmvas FOREIGN KEY (booking_id) REFERENCES sofendb.bookings(booking_id);
 V   ALTER TABLE ONLY sofendb.booking_details DROP CONSTRAINT fkkbcan6ybv86uappnh0qtdmvas;
       sofendb          sofen    false    225    226    4815            "           2606    67106 1   room_furnitures_rooms fklciavggdgbwtlgyrcwgbyoykb    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.room_furnitures_rooms
    ADD CONSTRAINT fklciavggdgbwtlgyrcwgbyoykb FOREIGN KEY (room_furniture_id) REFERENCES sofendb.room_furnitures(room_furniture_id);
 \   ALTER TABLE ONLY sofendb.room_furnitures_rooms DROP CONSTRAINT fklciavggdgbwtlgyrcwgbyoykb;
       sofendb          sofen    false    249    250    4859            ,           2606    67161 /   user_ranking_detail fkmihm6p9ql0o8d4ac0beav1sc3    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.user_ranking_detail
    ADD CONSTRAINT fkmihm6p9ql0o8d4ac0beav1sc3 FOREIGN KEY (language_id) REFERENCES sofendb.languages(language_id);
 Z   ALTER TABLE ONLY sofendb.user_ranking_detail DROP CONSTRAINT fkmihm6p9ql0o8d4ac0beav1sc3;
       sofendb          sofen    false    261    241    4847            1           2606    67197 1   hotels_hotel_policies fkndixp9cpvd7mxahg6kqn4k1aw    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotels_hotel_policies
    ADD CONSTRAINT fkndixp9cpvd7mxahg6kqn4k1aw FOREIGN KEY (hotel_policy_id) REFERENCES sofendb.hotel_policies(hotel_policy_id);
 \   ALTER TABLE ONLY sofendb.hotels_hotel_policies DROP CONSTRAINT fkndixp9cpvd7mxahg6kqn4k1aw;
       sofendb          sofen    false    264    4831    234            2           2606    67202 1   hotels_hotel_policies fko5kd5m6u884n0ob99fk0h5q3f    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotels_hotel_policies
    ADD CONSTRAINT fko5kd5m6u884n0ob99fk0h5q3f FOREIGN KEY (hotel_id) REFERENCES sofendb.hotels(hotel_id);
 \   ALTER TABLE ONLY sofendb.hotels_hotel_policies DROP CONSTRAINT fko5kd5m6u884n0ob99fk0h5q3f;
       sofendb          sofen    false    238    264    4845                       2606    67086 -   promotions_hotels fkp3d2fh5oh6lhibxsy1wlwr066    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.promotions_hotels
    ADD CONSTRAINT fkp3d2fh5oh6lhibxsy1wlwr066 FOREIGN KEY (hotel_id) REFERENCES sofendb.hotels(hotel_id);
 X   ALTER TABLE ONLY sofendb.promotions_hotels DROP CONSTRAINT fkp3d2fh5oh6lhibxsy1wlwr066;
       sofendb          sofen    false    245    238    4845            -           2606    67166 !   users fkp56c1712k691lhsyewcssf40f    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.users
    ADD CONSTRAINT fkp56c1712k691lhsyewcssf40f FOREIGN KEY (role_id) REFERENCES sofendb.roles(role_id);
 L   ALTER TABLE ONLY sofendb.users DROP CONSTRAINT fkp56c1712k691lhsyewcssf40f;
       sofendb          sofen    false    247    262    4855            %           2606    67121 !   rooms fkp5lufxy0ghq53ugm93hdc941k    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.rooms
    ADD CONSTRAINT fkp5lufxy0ghq53ugm93hdc941k FOREIGN KEY (hotel_id) REFERENCES sofendb.hotels(hotel_id);
 L   ALTER TABLE ONLY sofendb.rooms DROP CONSTRAINT fkp5lufxy0ghq53ugm93hdc941k;
       sofendb          sofen    false    238    255    4845            4           2606    67218 0   booking_detail_rooms fkpgg7i257ceoh89t3apaopi5a8    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.booking_detail_rooms
    ADD CONSTRAINT fkpgg7i257ceoh89t3apaopi5a8 FOREIGN KEY (room_id) REFERENCES sofendb.rooms(room_id);
 [   ALTER TABLE ONLY sofendb.booking_detail_rooms DROP CONSTRAINT fkpgg7i257ceoh89t3apaopi5a8;
       sofendb          sofen    false    265    4867    255                       2606    67006 $   bookings fkpvlyfwhomknrbmo2d20src5vi    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.bookings
    ADD CONSTRAINT fkpvlyfwhomknrbmo2d20src5vi FOREIGN KEY (guest_id) REFERENCES sofendb.guests(guest_id);
 O   ALTER TABLE ONLY sofendb.bookings DROP CONSTRAINT fkpvlyfwhomknrbmo2d20src5vi;
       sofendb          sofen    false    227    4817    226                       2606    67021 (   hotel_images fkrj3n45f8oqy1yr996g14j757i    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotel_images
    ADD CONSTRAINT fkrj3n45f8oqy1yr996g14j757i FOREIGN KEY (hotel_id) REFERENCES sofendb.hotels(hotel_id);
 S   ALTER TABLE ONLY sofendb.hotel_images DROP CONSTRAINT fkrj3n45f8oqy1yr996g14j757i;
       sofendb          sofen    false    238    232    4845            .           2606    67171 !   users fkrl713nbfrarmcniij55vytp33    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.users
    ADD CONSTRAINT fkrl713nbfrarmcniij55vytp33 FOREIGN KEY (user_ranking_id) REFERENCES sofendb.user_ranking(user_ranking_id);
 L   ALTER TABLE ONLY sofendb.users DROP CONSTRAINT fkrl713nbfrarmcniij55vytp33;
       sofendb          sofen    false    262    260    4873                       2606    67071 0   hotels_hotel_services fksds38h34p1kplygd07f810ik    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotels_hotel_services
    ADD CONSTRAINT fksds38h34p1kplygd07f810ik FOREIGN KEY (hotel_id) REFERENCES sofendb.hotels(hotel_id);
 [   ALTER TABLE ONLY sofendb.hotels_hotel_services DROP CONSTRAINT fksds38h34p1kplygd07f810ik;
       sofendb          sofen    false    238    4845    239            (           2606    67141 "   tokens fkspv3olbktcqfgg77mm0fr535t    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.tokens
    ADD CONSTRAINT fkspv3olbktcqfgg77mm0fr535t FOREIGN KEY (account_id) REFERENCES sofendb.users(user_id);
 M   ALTER TABLE ONLY sofendb.tokens DROP CONSTRAINT fkspv3olbktcqfgg77mm0fr535t;
       sofendb          sofen    false    4877    262    258            *           2606    67151 '   user_hotels fkssw53klugmlw27tbs3i0bg081    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.user_hotels
    ADD CONSTRAINT fkssw53klugmlw27tbs3i0bg081 FOREIGN KEY (user_id) REFERENCES sofendb.users(user_id);
 R   ALTER TABLE ONLY sofendb.user_hotels DROP CONSTRAINT fkssw53klugmlw27tbs3i0bg081;
       sofendb          sofen    false    262    4877    259            #           2606    67116 '   room_images fktky1jnwoh1hv50m263p2vlt0y    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.room_images
    ADD CONSTRAINT fktky1jnwoh1hv50m263p2vlt0y FOREIGN KEY (room_id) REFERENCES sofendb.rooms(room_id);
 R   ALTER TABLE ONLY sofendb.room_images DROP CONSTRAINT fktky1jnwoh1hv50m263p2vlt0y;
       sofendb          sofen    false    252    255    4867                       2606    67046 "   hotels fktn7smg3n77hmfii6q4ddt0c11    FK CONSTRAINT     �   ALTER TABLE ONLY sofendb.hotels
    ADD CONSTRAINT fktn7smg3n77hmfii6q4ddt0c11 FOREIGN KEY (hotel_address_id) REFERENCES sofendb.hotel_address(hotel_address_id);
 M   ALTER TABLE ONLY sofendb.hotels DROP CONSTRAINT fktn7smg3n77hmfii6q4ddt0c11;
       sofendb          sofen    false    229    238    4819            �   K   x��̱�  �v��FvI�(���l������.�^I���s�fk��aW����8�:��A2l�f�}�S��'>c      �   �   x���9
1�Z�����K�R$��il���GȐʁ�(�T|x�� �)�g��~zm�m{XQXf�*��X����T{�5���a՘Я�u^$8>���v>���T`��3�]S@�f���k2��LSwM�h7��L�I�A�>���b1�d�ι7띥      �   �   x��ҹ�0�ښB0��Cd7:�!J��%a���䃑� %��D\}\�5��1],���O����P|���r��b��GYZ�kU	��Լr����RK0��l	�F^�%Rk�1Z���^�&[#K|�dRk^���ؒ�4Rk���KRRk�������      �      x������ � �      �   �   x�3�����P�8�)�3,������\N��a�`a��qA��@u���ʝ�Z���ql!gD)P�D?�1���-y
G&<ܵ8_Ah\�B�y�Ț�/Pz��;QAW!��ʼt���J9�b���� �A?      �   �   x��0�4��H�OK�����K�sJ�tH�M���K���LKLNM���q�K2Ru�AJu��83�KӋs1���ML��-̹,L8��8'�d�6<���@)�� Í���$cx
n�S8�b���� �c]�      �      x������ � �      �   F   x�3�t�8�6/C����������v+��*�p::�x��r�P`S`�C�L�)�0f���c���� %&>4      �      x������ � �      �   n   x�3�4�ty��;9C����
ٙw7�*�%*qreKR�K��S��2�S9��8�p�0i1�j1D�c�[�H�1L��.<��v�����f�CH�)D\u� mdY�      �   I   x�3�444 N�����3��^�P�Z\�`��������e�G�!�:c|����U�l�)N�H�b���� ��?3      �   �   x�]�;N1Ek�*����2.I@�&�R�y�1#![��.X�a#�	�B1B����9(PP��=�>��Mf x$�%OQ�S��5�"�s�䎋\�8�������|3^�.;l��KZ)�`Cs�@��#��*���½i�q��f~N�sL�>�{AmY�[�_C5�Uڣsz�A'f4��rF�BRƨe�y��ό��E����C��>�ݢ�?��K�Ux      �      x��Ϲ1 ����s�^� ��/�*�|���#y�ŉ��1B
+��^���q�=&=�=fP2����e^����5���ʥ~-6zLzL{�`�hҜX���Wi��C����B����3�}����      �   |   x��ϻ! �x�} ��^.��K8K �d��i=h�E"���2�<�Q�j�ƸƤƴ��I�[���2K'Y0���b��B�q�I�i��3�%pNȮd�����|H�ʷPb\cRc����VÊ�      �   +   x�K��L�4�t��OJ��*��8�2SK�sS�S�b���� �B
6      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �   E   x�3���q�v�2��}��]��tpP�E���ut���)1E�2�p\}|�#]]�b���� �Is      �   {  x�]QKN�0\���'@$����(�7uc��.N
�p �f�
	���f��U`�t�<�L��<����?��v�8� ��rE��v%�)����z�Ո9Izf)ހ���6!v:�U;M�m!9�	�l�v"�Z�S��}S<�9�$1K�5oN׆HM&��>�c���k��D�\h%�LIO�pN��٬1Y�t6w`��I25���� � ��:��*?���Ζ=�,��徽�~�/d��o[h�j*��Y��jAR��T��A��Hy��TbD��f=#IU�cD�S!3䛇�c�Ě��"�HNJ%���	,6����N���\3��LΩL���t ���V֦H����'��ah>�X�$�W���G���Vz�      �   �   x��;�0�z�9b��i	B �D��,d[���A\w#��c��xÌ�b�ŻbN��_���T�����nP,��}�Z��TÊ��C����5�������xJ;Ӕ�93f1�����4J��s��6�	������%5�+xI	�Z�VԚ$WT�kj\�e�6���7�      �     x�%ӻ�#1Q�
fO����cQ�m����~�o�M������{~*�8|*������{��n����n�����;`��v��ӆ�ap��ap� �p �8 �O���ip���ip��pN�	8'���>m\��ep\��ep.�� �\�p��mp���mp����7�܀pn��ӎ�c�<��c�<�<�� �x �O���k����k����^�x/���>�|��g�|��g�>�� �|���4�.�]l�w��b�ž��W^�xe㕑WV^�ye畡W�^�E�I=�'�$������Wї����Wҗ���5��DO"(Q�HJ4%�U)Y)])a)e)i)m)q)u)yq�L&���De"3љ�R���Қ�R���қ���Ds":Q��Nt'��)�)�)�)�)�)�)**	r��PT(2�E�"EѢ�R���ң�R���Ҥ��߿���S��m      �      x������ � �      �   �  x��TMn�0^ON���N��,ٸΛ��q��̚���.�
�UT����񮩸�o�s�a�~v3�I���O����׍t�lq2y��D����jP���Oj���L�W���5�h����?kp�7B��(��ۿA���E�ş���[$�f�L�<`�¨�x/��>09��ޅn���߀�t!$pV����x�3�E�B2M��e�+���)�\
���L��;�H+ߞ����`[�x�V���h���ڣ�d�Fi�0���ot�&���}#p��Z��Q9T -0P���J� � r��xsJ���)�ee(G�7�R�R�u
;�]6J��7��1�k��lJ`%j�$��Ekfa�\I�֌c:�1X�hV�#���!�j>6f=0�J��5Zg�$�e����o��F{��:���\�-דd�[����X��Vk�6�t�ث��j�A��m�$�_��^      �   =   x�35�4�4�465 NC���̒TC#c.STI#$It9c���	\
]�"����� !��      �   �   x��Ϲ�0 �Zޅ_�Z"����G#H��8�Ę(q��|�j�jj��!�~ej4��>d	N��$[B��E^�|�DvѥL��sw�;A%pb��hC�~�=*���{T�y�{T�9}�Q��D~/Y�a)���Y+\��<�'����N��(�y�G�ޓz?J~}�~�ܯ�8>��l      �      x������ � �      �      x������ � �      �   =   x�34�4�t*�ϫJ�22�4���)K-�26�4�t��I�21�4��I,��+������ c�"      �      x������ � �      �   �   x��οj�0�Y~
=�Ϝ�ڗ�!t�ڦ�
E��M����4��W
���M��P��V(D�Q[@�$�ʘRAT�	���L�cZ	lc��K��n]����F�L���a�����_�x�h��xM?R�}���a���K��\��l�v��"r�sf����¶	�m5X�*�J�FǨ=y#R�\��¹����ͧ�>��"���A�S���ϊ�,��s�;t]���,�ft��ߊ,�~ N�~�     