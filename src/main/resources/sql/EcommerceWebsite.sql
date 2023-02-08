--CÃ³ thá»ƒ dÃ¹ng lá»‡nh dÆ°á»›i Ä‘á»ƒ drop column status trong báº£ng bill trong trÆ°á»?ng há»£p lá»¡ táº¡o db
--alter table bill
--    drop column status;
-- ADD COLUMN PRICE INTO PRODUCT TABLE
ALTER TABLE PRODUCT
    MODIFY price decimal(12,6);
ALTER TABLE PRODUCT 
 MODIFY DESCRIPTION NVARCHAR2(2000);
--ADD COLUMN DELETED INTO ALL TABLE
ALTER TABLE PRODUCT
    ADD deleted NUMBER(1,0) DEFAULT 0;
    
    
ALTER TABLE ACCOUNT
    ADD deleted NUMBER(1,0) DEFAULT 0;
    
ALTER TABLE BILL
    ADD deleted NUMBER(1,0) DEFAULT 0;
    
ALTER TABLE BILL_DETAIL
    ADD deleted NUMBER(1,0) DEFAULT 0;
    
ALTER TABLE BILL_STATUS
    ADD deleted NUMBER(1,0) DEFAULT 0;
    
ALTER TABLE CART
    ADD deleted NUMBER(1,0) DEFAULT 0;
    
ALTER TABLE CART_COUPON
    ADD deleted NUMBER(1,0) DEFAULT 0;
    
ALTER TABLE CART_PRODUCT
    ADD deleted NUMBER(1,0) DEFAULT 0;
    
ALTER TABLE CATEGORIES
    ADD deleted NUMBER(1,0) DEFAULT 0;
    
ALTER TABLE COUPON
    ADD deleted NUMBER(1,0) DEFAULT 0;
    
ALTER TABLE FUNCTION
    ADD deleted NUMBER(1,0) DEFAULT 0;
    
ALTER TABLE RATING
    ADD deleted NUMBER(1,0) DEFAULT 0;
    
ALTER TABLE ROLE
    ADD deleted NUMBER(1,0) DEFAULT 0;
    
ALTER TABLE ROLE_FUNCTION
    ADD deleted NUMBER(1,0) DEFAULT 0;
    
ALTER TABLE USER_ROLE
    ADD deleted NUMBER(1,0) DEFAULT 0;
    
ALTER TABLE USER_WEBSITE
    ADD deleted NUMBER(1,0) DEFAULT 0;


--CREATE TABLE
CREATE TABLE product (
    id          NUMBER(10, 0) NOT NULL,
    name        VARCHAR2(255),
    type        VARCHAR2(255),
    discount    NUMBER(10, 0),
    avgrating   NUMBER(10, 0),
    material    VARCHAR2(255),
    categoryid  NUMBER(10, 0),
    height      NUMBER(10, 0),
    width       NUMBER(10, 0),
    weight      NUMBER(10, 0),
    description VARCHAR2(255),
    image       VARCHAR2(255),
    brand       VARCHAR2(255),
    madein      VARCHAR2(255),
    amount      NUMBER(10, 0),
    color       VARCHAR2(255),
    price       decimal(12,6),
    CONSTRAINT product_pk PRIMARY KEY ( id )
);

---
CREATE TABLE categories (
    categoriesid    NUMBER(20) NOT NULL,
    categories_name VARCHAR2(100),
    image           VARCHAR2(100),
    
    CONSTRAINT categories_pk PRIMARY KEY ( categoriesid )
);

---

---
CREATE TABLE bill (
    bill_id        NUMBER(10, 0) NOT NULL,
    customer_id    NUMBER(10, 0),
    id_bill_status NUMBER(10, 0),
    date_order     DATE,
    total          DECIMAL(10, 0),
    note           VARCHAR2(255),
    status         VARCHAR2(255),
    payment        VARCHAR2(255),
    codemomo       VARCHAR2(255),
    created_at     DATE,
    updated_at     DATE,
    
    CONSTRAINT bill_pk PRIMARY KEY ( bill_id )
);

---
CREATE TABLE bill_detail (
    id         NUMBER(10, 0) NOT NULL,
    quantity   NUMBER(10, 0),
    price      DECIMAL(10, 0),
    createdat  TIMESTAMP,
    updatedat  TIMESTAMP,
    id_bill    NUMBER(10, 0),
    id_rating  NUMBER(10, 0),
    id_product NUMBER(10, 0),
    
    CONSTRAINT bill_detail_pk PRIMARY KEY ( id )
);

--- 
CREATE TABLE bill_status (
    id     NUMBER(10, 0) NOT NULL,
    status VARCHAR(100),
    
    CONSTRAINT bill_status_pk PRIMARY KEY ( id )
);

---
CREATE TABLE cart_product (
    id         NUMBER(10, 0) NOT NULL,
    cart_id    NUMBER(10, 0),
    product_id NUMBER(10, 0),
    quantity   NUMBER(10, 0),
    
    CONSTRAINT cart_product_pk PRIMARY KEY ( id )
);

------
CREATE TABLE rating (
    id              NUMBER(10, 0) NOT NULL,
    comment_product VARCHAR2(200),
    ratescore       NUMBER(10, 0),
    iduser          NUMBER(10, 0),
    
    CONSTRAINT rating_pk PRIMARY KEY ( id )
);


---
CREATE TABLE cart_coupon (
    id        NUMBER(10, 0) NOT NULL,
    cart_id   NUMBER(10, 0),
    coupon_id NUMBER(10, 0),
    
    CONSTRAINT cart_coupon_pk PRIMARY KEY ( id )
);
--- 
CREATE TABLE coupon (
    id       NUMBER(10, 0) NOT NULL,
    name     VARCHAR2(200),
    discount NUMBER(3,2),
    MIN_PRICE_APPLY NUMBER(10,0),
    MAX_PRICE NUMBER(10,0),
    CODE VARCHAR2(20),
    AMOUNT NUMBER(10,0),
    
    CONSTRAINT coupon_pk PRIMARY KEY ( id )
);
---
CREATE TABLE cart (
    id    NUMBER(10, 0) NOT NULL,
    total DECIMAL(10, 0),
    
    CONSTRAINT cart_pk PRIMARY KEY ( id )
);
---
CREATE TABLE account (
    id               NUMBER(10, 0) NOT NULL,
    email            VARCHAR2(255),
    account_password VARCHAR2(255),
    
    CONSTRAINT account_pk PRIMARY KEY ( id )
);
---
CREATE TABLE user_website (
    id        NUMBER(10, 0) NOT NULL,
    user_name VARCHAR2(255),
    address   VARCHAR2(255),
    phone     VARCHAR2(15),
    idcart    NUMBER(10, 0),
    idaccount NUMBER(10, 0),
    
    CONSTRAINT user_website_pk PRIMARY KEY ( id )
);
--- 
CREATE TABLE user_role (
    id      NUMBER(10, 0) NOT NULL,
    id_role NUMBER(10, 0),
    iduser  NUMBER(10, 0),
    
    CONSTRAINT user_role_pk PRIMARY KEY ( id )
);
--
CREATE TABLE role (
    id        NUMBER(10, 0) NOT NULL,
    name_role VARCHAR2(50),
    
    CONSTRAINT role_pk PRIMARY KEY ( id )
);
--
CREATE TABLE function (
    id           NUMBER(10, 0) NOT NULL,
    name         VARCHAR2(255),
    display_name VARCHAR2(255),
    
    CONSTRAINT function_pk PRIMARY KEY ( id )
);
--
CREATE TABLE role_function (
    id          NUMBER(10, 0) NOT NULL,
    role_id     NUMBER(10, 0),
    function_id NUMBER(10, 0),
    
    CONSTRAINT role_function_pk PRIMARY KEY ( id )
);

-- Khoa ngoai -> khoa chinh
--
SELECT a.table_name,a.constraint_name
  FROM all_cons_columns a;

--
--  Role_Function - Role 
ALTER TABLE role_function
    ADD CONSTRAINT fk01_r_rf FOREIGN KEY ( role_id )
        REFERENCES role ( id );
         
-- USER_ROLE - Role 
ALTER TABLE user_role
    ADD CONSTRAINT fk02 FOREIGN KEY ( id_role )
        REFERENCES role ( id );

-- ROLE_FUNCTION - FUNCTION
ALTER TABLE role_function
    ADD CONSTRAINT fk03_rf_f FOREIGN KEY ( function_id )
        REFERENCES function ( id );
        
-- USER_ROLE - USER_WEBSITE
ALTER TABLE user_role
    ADD CONSTRAINT fk04 FOREIGN KEY ( iduser )
        REFERENCES user_website ( id );
        
--  USER_WEBSITE- ACCOUNT
ALTER TABLE user_website
    ADD CONSTRAINT fk05 FOREIGN KEY ( idaccount )
        REFERENCES account ( id );
--USER_WEBSITE - CART
ALTER TABLE user_website
    ADD CONSTRAINT fk06 FOREIGN KEY ( idcart )
        REFERENCES cart ( id );
-- CART_COUPON - CART
ALTER TABLE cart_coupon
    ADD CONSTRAINT fk07 FOREIGN KEY ( cart_id )
        REFERENCES cart ( id );
-- CART_COUPON - COUPON
ALTER TABLE cart_coupon
    ADD CONSTRAINT fk08 FOREIGN KEY ( coupon_id )
        REFERENCES coupon ( id );
-- BILL - USER_WEBSITE
ALTER TABLE bill
    ADD CONSTRAINT fk09 FOREIGN KEY ( customer_id )
        REFERENCES user_website ( id );
        
-- RATING - USER_WEBSITE
ALTER TABLE rating
    ADD CONSTRAINT fk10 FOREIGN KEY ( iduser )
        REFERENCES user_website ( id );

-- CART_PRODUCT - CART
ALTER TABLE cart_product
    ADD CONSTRAINT fk11 FOREIGN KEY ( cart_id )
        REFERENCES cart ( id );

-- BILL - BILL_STATUS
ALTER TABLE bill
    ADD CONSTRAINT fk12 FOREIGN KEY ( id_bill_status )
        REFERENCES bill_status ( id );

--  BILL_DETAIL - BILL 
ALTER TABLE bill_detail
    ADD CONSTRAINT fk13 FOREIGN KEY ( id_bill )
        REFERENCES bill ( bill_id );

-- BILL_DETAIL - RATING
ALTER TABLE bill_detail
    ADD CONSTRAINT fk14 FOREIGN KEY ( id_rating )
        REFERENCES rating ( id );

--BILL_DETAIL - PRODUCT
ALTER TABLE bill_detail
    ADD CONSTRAINT fk15 FOREIGN KEY ( id_product )
        REFERENCES product ( id );

--CART_PRODUCT - PRODUCT
ALTER TABLE cart_product
    ADD CONSTRAINT fk16 FOREIGN KEY ( product_id )
        REFERENCES product ( id );

--PRODUCT - CATEGORIES
ALTER TABLE product
    ADD CONSTRAINT fk17 FOREIGN KEY ( categoryid )
        REFERENCES categories ( categoriesid );
        
--INSERT DATA

--TABLE ACCOUNT
CREATE SEQUENCE account_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;
    
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, 'darklord0305@gmail.com', '123456789');
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, 'thelionking0305@gmail.com', '123456789');
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, 'thanhluan130201@gmail.com', '123456789');
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, '0.199qui@gmail.com', '123456789');
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, 'nguyenvanan@gmail.com', '123456789');
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, 'nguyenthiphuong123@gmail.com', '123456789');
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, 'dinhvanbua125@gmail.com', '123456789');
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, 'helloworld125@gmail.com', '123456789');
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, 'nguyenvanhieu123@gmail.com', '123456789');
INSERT INTO account ( id, email, account_password) values (account_seq.NEXTVAL, 'buivantinh123@gmail.com', '123456789');

--TABLE CART
CREATE SEQUENCE cart_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;

INSERT INTO CART (ID, TOTAL) VALUES (cart_seq.NEXTVAL, 100000);
INSERT INTO CART (ID, TOTAL) VALUES (cart_seq.NEXTVAL, 200000);
INSERT INTO CART (ID, TOTAL) VALUES (cart_seq.NEXTVAL, 123000);
INSERT INTO CART (ID, TOTAL) VALUES (cart_seq.NEXTVAL, 400000);
INSERT INTO CART (ID, TOTAL) VALUES (cart_seq.NEXTVAL, 1000000);
INSERT INTO CART (ID, TOTAL) VALUES (cart_seq.NEXTVAL, 2000000);
INSERT INTO CART (ID, TOTAL) VALUES (cart_seq.NEXTVAL, 10000000);
INSERT INTO CART (ID, TOTAL) VALUES (cart_seq.NEXTVAL, 10000);
INSERT INTO CART (ID, TOTAL) VALUES (cart_seq.NEXTVAL, 13548125);
INSERT INTO CART (ID, TOTAL) VALUES (cart_seq.NEXTVAL, 15489641);

--TABLE USER_WEBSITE
CREATE SEQUENCE user_website_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;
    
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'Nguyá»…n Viáº¿t QuÃ½', 'ChÆ° Puh, Gia Lai', '0134567896', 1, 1);
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'Pháº¡m PhÃºc Háº­u', 'VÅ©ng TÃ u', '0125487898', 2, 2);
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'LÃª ThÃ nh LuÃ¢n', 'VÅ©ng TÃ u', '0125487897', 3, 3);
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'Nguyá»…n VÄƒn A', 'TP.Há»“ ChÃ­ Minh', '0125487154', 4, 4);
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'Ä?inh CÃ´ng LÆ°Æ¡ng', 'Quáº£ng BÃ¬nh', '0125487458', 5, 5);
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'Duy Máº¡nh', 'Nghá»‡ An', '0125487487', 6, 6);
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'Nguyá»…n VÄƒn Minh', 'BÃ¬nh Thuáº­n', '0125487879', 7, 7);
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'Nguyá»…n VÄƒn SÃº', 'LÃ o Cai', '0125487147', 8, 8);
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'Aladin', 'BÃ¬nh DÆ°Æ¡ng', '0125484587', 9, 9);
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'Nguyá»…nn VÄƒn Hiáº¿u', 'Dak Lak', '0125487852', 10, 10);


--TABLE COUPON  
CREATE SEQUENCE coupon_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;
    
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Khuyáº¿n mÃ£i cho khÃ¡ch hÃ ng má»›i', 0.1, 0, 50000, 'KMKHM', 50);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giáº£m 10% Giáº£m tá»‘i Ä‘a 50,000Ä‘ Ä‘Æ¡n tá»‘i thiá»ƒu 99,000Ä‘', 0.1, 99000, 50000, 'HUJUI', 30);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giáº£m 20,000Ä‘ Ä‘Æ¡n tá»‘i thiá»ƒu 199,000Ä‘', 0, 199000, 20000, 'HUYEJ', 30);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giáº£m 10% Giáº£m tá»‘i Ä‘a 300,000Ä‘ Ä‘Æ¡n tá»‘i thiá»ƒu 499,000Ä‘', 0.1, 499000, 300000, 'YUITLR', 30);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giáº£m 50,000Ä‘ Ä‘Æ¡n tá»‘i thiá»ƒu 0Ä‘', 0, 0, 50000, 'QIEHFU', 30);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giáº£m 10,000Ä‘ Ä‘Æ¡n tá»‘i thiá»ƒu 120,000Ä‘', 0, 120000, 10000, 'TEWUYG', 30);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giáº£m 5% Giáº£m tá»‘i Ä‘a 500,000Ä‘ Ä‘Æ¡n tá»‘i thiáº¿u 300,000Ä‘', 0.05, 300000, 500000, 'OIUYTR', 30);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giáº£m 150,000Ä‘ Ä‘Æ¡n tá»‘i thiá»ƒu 1,500,000Ä‘', 0, 1500000, 150000, 'RTEGDT', 30);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giáº£m 10% Giáº£m tá»‘i Ä‘a 60,000Ä‘ Ä‘Æ¡n tá»‘i thiá»ƒu 400,000Ä‘', 0.1, 400000, 60000, 'YEUWGY', 30);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giáº£m 10% Giáº£m tá»‘i Ä‘a 100,000Ä‘ Ä‘Æ¡n tá»‘i thiá»ƒu 600,000Ä‘', 0.1, 600000, 100000, 'EGYFGE', 30);

--TABLE CART_COUPON
CREATE SEQUENCE cart_coupon_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;
    
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 1, 1);
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 2, 1);
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 3, 3);
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 4, 7);
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 5, 6);
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 6, 9);
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 7, 10);
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 8, 1);
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 9, 1);
INSERT INTO cart_coupon VALUES (cart_coupon_seq.NEXTVAL, 10, 1);

--TABLE ROLE
CREATE SEQUENCE role_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;
    
INSERT INTO role VALUES (role_seq.NEXTVAL, 'Admin');
INSERT INTO role VALUES (role_seq.NEXTVAL, 'KhÃ¡ch');
INSERT INTO role VALUES (role_seq.NEXTVAL, 'Quáº£n lÃ½ kho');
INSERT INTO role VALUES (role_seq.NEXTVAL, 'NhÃ¢n viÃªn bÃ¡n hÃ ng');
INSERT INTO role VALUES (role_seq.NEXTVAL, 'Thu ngÃ¢n');
INSERT INTO role VALUES (role_seq.NEXTVAL, 'Quáº£n lÃ½ nhÃ¢n sá»±');

--TABLE ROLE_USER
CREATE SEQUENCE role_user_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;

INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 1, 1);
INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 2, 2);
INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 1, 3);
INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 1, 4);
INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 2, 5);
INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 2, 6);
INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 3, 7);
INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 4, 8);
INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 5, 9);
INSERT INTO user_role VALUES (role_user_seq.NEXTVAL, 6, 10);

--TABLE FUNCTION
CREATE SEQUENCE function_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;
    
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Quáº£n lÃ½ sáº£n pháº©m', 'ThÃªm, xÃ³a, sá»­a sáº£n pháº©m');
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Quáº£n lÃ½ khÃ¡ch hÃ ng', 'Tra cá»©u cÃ¡c thÃ´ng tin cá»§a khÃ¡ch hÃ ng');
INSERT INTO function VALUES (function_seq.NEXTVAL, 'PhÃ¢n quyá»?n', 'Giá»›i háº¡n chá»©c nÄƒng cho má»™t ngÆ°á»?i dÃ¹ng');
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Mua hÃ ng', 'Xem vÃ  thÃªm sáº£n pháº©m vÃ o giá»? vÃ  tiáº¿n hÃ nh thanh toÃ¡n');
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Quáº£n lÃ½ khuyáº¿n mÃ£i', 'ThÃªm, xÃ³a, sá»­a khuy?n mÃ£i');
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Quáº£n lÃ½ Ä‘Æ¡n hÃ ng', 'Xem, tra cá»©u táº¥t cáº£ Ä‘Æ¡n hÃ ng cá»§a cá»­a hÃ ng');
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Tra cá»©u sáº£n pháº©m', 'Tra cá»©u thÃ´ng tin sáº£n pháº©m');
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Quáº£n lÃ½ kho hÃ ng', 'Thay Ä‘á»•i sá»‘ lÆ°á»£ng sáº£n pháº©m Ä‘ang cÃ³ trong cá»­a hÃ ng');
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Thá»‘ng kÃª vÃ  bÃ¡o cÃ¡o', 'Xem bÃ¡o cÃ¡o kinh doanh cá»­a cá»­a hÃ ng');
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Ä?Ã¡nh giÃ¡ sáº£n pháº©m', 'Ä?Ã¡nh giÃ¡ sáº£n pháº©m');

--TABLE ROLE_FUNCTION
CREATE SEQUENCE role_function_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;
    
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 1, 1);
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 1, 2);
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 1, 3);
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 1, 5);
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 1, 6);
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 2, 4);
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 2, 7);
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 3, 8);
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 1, 9);
INSERT INTO role_function VALUES (role_function_seq.NEXTVAL, 2, 2);


--TABLE BILL_STATUS
CREATE SEQUENCE bill_status_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;

insert into bill_status(id,status) values(bill_status_seq.NEXTVAL, 'Pending');
insert into bill_status(id,status) values(bill_status_seq.NEXTVAL, 'Complete');
insert into bill_status(id,status) values(bill_status_seq.NEXTVAL, 'payment');
insert into bill_status(id,status) values(bill_status_seq.NEXTVAL, 'Yes');
insert into bill_status(id,status) values(bill_status_seq.NEXTVAL, 'No');
insert into bill_status(id,status) values(bill_status_seq.NEXTVAL, 'Cancel');
insert into bill_status(id,status) values(bill_status_seq.NEXTVAL, 'Pháº¡t');
insert into bill_status(id,status) values(bill_status_seq.NEXTVAL, 'Error');
insert into bill_status(id,status) values(bill_status_seq.NEXTVAL, 'Continues');
insert into bill_status(id,status) values(bill_status_seq.NEXTVAL, 'Sending');

--TABLE BILL
CREATE SEQUENCE bill_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;

insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,codemomo,created_at, updated_at)
values(bill_seq.NEXTVAL,1,1,TO_DATE('02/02/2022', 'dd/mm/yyyy'),3000,'Note1','Payment online','ewu',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));

insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,codemomo,created_at, updated_at)
values(bill_seq.NEXTVAL,2,2,TO_DATE('02/02/2022', 'dd/mm/yyyy'),3000,'Note1','Payment online','ewu',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));

insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,codemomo,created_at, updated_at)
values(bill_seq.NEXTVAL,1,3,TO_DATE('02/02/2022', 'dd/mm/yyyy'),3600,'Note1','Payment online','ewu',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));

insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,codemomo,created_at, updated_at)
values(bill_seq.NEXTVAL,2,3,TO_DATE('02/02/2022', 'dd/mm/yyyy'),8000,'Note1','Payment online','ewu',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));

insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,codemomo,created_at, updated_at)
values(bill_seq.NEXTVAL,3,1,TO_DATE('02/02/2022', 'dd/mm/yyyy'),3700,'Note1','Payment online','ewuewe',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));

insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,codemomo,created_at, updated_at)
values(bill_seq.NEXTVAL,3,2,TO_DATE('02/02/2022', 'dd/mm/yyyy'),3000,'Note1','Payment online','ewu',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));

insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,codemomo,created_at, updated_at)
values(bill_seq.NEXTVAL,3,3,TO_DATE('02/02/2022', 'dd/mm/yyyy'),7000,'Note1','Payment online','ewu',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));

insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,codemomo,created_at, updated_at)
values(bill_seq.NEXTVAL,4,1,TO_DATE('02/02/2022', 'dd/mm/yyyy'),3000,'Note1','Payment online','ewu',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));

insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,codemomo,created_at, updated_at)
values(bill_seq.NEXTVAL,4,2,TO_DATE('02/02/2022', 'dd/mm/yyyy'),3000,'Note1','Payment online','ewu',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));

insert into bill(bill_id,customer_id, id_bill_status, date_order,total, note,payment,codemomo,created_at, updated_at)
values(bill_seq.NEXTVAL,4,3,TO_DATE('02/02/2022', 'dd/mm/yyyy'),5000,'Note1','Payment online','ewu',TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'));

--TABLE RATING
CREATE SEQUENCE rating_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;
    
insert into rating(id, comment_product,ratescore,iduser) values(rating_seq.NEXTVAL,'Comment1', 1,1);
insert into rating(id, comment_product,ratescore,iduser) values(rating_seq.NEXTVAL,'Comment1', 1,1);
insert into rating(id, comment_product,ratescore,iduser) values(rating_seq.NEXTVAL,'Comment', 1,1);
insert into rating(id, comment_product,ratescore,iduser) values(rating_seq.NEXTVAL,'Comment', 1,1);
insert into rating(id, comment_product,ratescore,iduser) values(rating_seq.NEXTVAL,'Ä?áº¹p', 4,2);
insert into rating(id, comment_product,ratescore,iduser) values(rating_seq.NEXTVAL,'Comment3', 1,1);
insert into rating(id, comment_product,ratescore,iduser) values(rating_seq.NEXTVAL,'Comment4', 1,1);
insert into rating(id, comment_product,ratescore,iduser) values(rating_seq.NEXTVAL,'Comment4', 2,1);
insert into rating(id, comment_product,ratescore,iduser) values(rating_seq.NEXTVAL,'Comment6', 1,1);
insert into rating(id, comment_product,ratescore,iduser) values(rating_seq.NEXTVAL,'Tuyá»‡t', 2,1);

--TABLE CATEGORIES
CREATE SEQUENCE categories_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;
    
insert into categories(categoriesid,categories_name,image) values(categories_seq.NEXTVAL,'Laptop','Anh1.jpg');
insert into categories(categoriesid,categories_name,image) values(categories_seq.NEXTVAL,'DienThoai','Anh1.jpg');
insert into categories(categoriesid,categories_name,image) values(categories_seq.NEXTVAL,'Tai Nghe','Anh1.jpg');
insert into categories(categoriesid,categories_name,image) values(categories_seq.NEXTVAL,'Quan Ao','Anh1.jpg');
insert into categories(categoriesid,categories_name,image) values(categories_seq.NEXTVAL,'GiÃ y','Anh1.jpg');
insert into categories(categoriesid,categories_name,image) values(categories_seq.NEXTVAL,'PC','Anh1.jpg');
insert into categories(categoriesid,categories_name,image) values(categories_seq.NEXTVAL,'Tá»§ Lanh','Anh1.jpg');
insert into categories(categoriesid,categories_name,image) values(categories_seq.NEXTVAL,'Báº¿p','Anh1.jpg');
insert into categories(categoriesid,categories_name,image) values(categories_seq.NEXTVAL,'Phá»¥ Kien','Anh1.jpg');
insert into categories(categoriesid,categories_name,image) values(categories_seq.NEXTVAL,'Dich Vu','Anh1.jpg');

--TABLE PRODUCT
CREATE SEQUENCE product_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;

insert into product (id, name,type, discount, avgrating, material, categoryid, height,width,weight,description,image,brand,madein,amount, color,price) values
(product_seq.NEXTVAL,'B?m kim Kanex HD10 - 10 t?','Kanex', 2000,200,'Nh?a',1,720,1080,2,'B?m kim Kanex HD10 - 10 t? (B-11.6) v?i thân máy b?ng nh?a, d?ng b?m, l?c b?m 10 t?, s? d?ng kim b?m no.10 liên k?t và phân nhóm tài li?u b?ng l?c b?m ho?c ?óng nhi?u t? gi?y thành x?p ho?c cu?n phù h?p v?i nghi?p v? photocopy và l?u tr? ch?ng t? liên k?t và phân nhóm tài li?u b?ng l?c b?m ho?c ?óng nhi?u t? gi?y thành x?p ho?c cu?n phù h?p v?i nghi?p v? photocopy và l?u tr? ch?ng t? ?Ti?t ki?m t? 10% - 30%
Kanex là nhà s?n xu?t uy tín t?i ?n ?? v? s?n ph?m máy b?m kim, b?m l?, kim b?m v?n phòng
S?n ph?m B?m kim chính hãng ?áp ?ng tiêu chu?n b?o v? s?c kh?e ng??i dùng và nâng cao t?i ?a hi?u qu? công vi?c v?n phòng m?i ngày.
B?n có th? ??t mua nhi?u dòng v?n phòng ph?m Kanex t?i Officexinh.com v?i m?c giá th?t ?u ?ãi và nh?n ???c chính sách v?n chuy?n mi?n phí n?u có d?a trên t?ng giá tr? ??n hàng và tùy theo khu v?c nh?n hàng.
B?m kim Kanex HD10 - 10 t? còn g?i là b?m ghim, b?m kim s? 10 có ??n v? tính là Cái và ???c ?óng gói theo quy cách: 10 cái / l?c
M?u mã và thông tin s?n ph?m có th? thay ??i theo chính sách nhà s?n xu?t.','bam-kim-kanex-hd10-10-to.jpg', 'Kanex','Inda',4000,'white and blue','20000');
insert into product (id, name,type, discount, avgrating, material, categoryid, height,width,weight,description,image,brand,madein,amount, color,price) values
(product_seq.NEXTVAL,'Gi?y Double A A4 80','Gi?y in ?n', 2000,200,'Gi?y',1,720,1080,2,'Gi?y Double A A4 80 (G-61) v?i kích th??c: A4 (210 mm x 297 mm), ??nh l??ng: 80gms, n?n gi?y tr?ng in ?n ch?ng t?, tài li?u, h?p ??ng d?a trên kh? gi?y chu?n phù h?p v?i các nghi?p v? v?n phòng in ?n ch?ng t?, tài li?u, h?p ??ng d?a trên kh? gi?y chu?n phù h?p v?i các nghi?p v? v?n phòng ?Ti?t ki?m t? 10% - 30%
Double A là hãng s?n xu?t uy tín t?i Thái Lan v? s?n ph?m gi?y photocopy, gi?y in, gi?y bìa màu
S?n ph?m Gi?y in ?n chính hãng ?áp ?ng tiêu chu?n b?o v? s?c kh?e ng??i dùng và nâng cao t?i ?a hi?u qu? công vi?c v?n phòng m?i ngày.
B?n có th? ??t mua nhi?u dòng v?n phòng ph?m Double A t?i Officexinh.com v?i m?c giá th?t ?u ?ãi và nh?n ???c chính sách v?n chuy?n mi?n phí n?u có d?a trên t?ng giá tr? ??n hàng và tùy theo khu v?c nh?n hàng.
Gi?y Double A A4 80 còn g?i là gi?y photocopy, gi?y in, gi?y v?n phòng có ??n v? tính là Ream và ???c ?óng gói theo quy cách: 5 reams / thùng
M?u mã và thông tin s?n ph?m có th? thay ??i theo chính sách nhà s?n xu?t.','giay-double-a-a4-80.jpg', 'Double A','Thái Lan',4000,'red','93000');

insert into product (id, name,type, discount, avgrating, material, categoryid, height,width,weight,description,image,brand,madein,amount, color) values
(product_seq.NEXTVAL,'LapTop Dell x202','Asus', 2000,200,'Dell',1,720,1080,2,'decription Dell','img.jpg', 'Asus','Chinna',4000,'black');
insert into product (id, name,type, discount, avgrating, material, categoryid, height,width,weight,description,image,brand,madein,amount, color) values
(product_seq.NEXTVAL,'LapTop Dell 5005','Asus', 2000,200,'Dell',1,720,1080,2,'decription Dell','img.jpg', 'Asus','Chinna',4000,'white');

insert into product (id, name,type, discount, avgrating, material, categoryid, height,width,weight,description,image,brand,madein,amount, color) values
(product_seq.NEXTVAL,'LapTop Dell 4500','Asus', 2000,200,'Dell',1,720,1080,2,'decription Dell','img.jpg', 'Asus','Chinna',4000,'black');
insert into product (id, name,type, discount, avgrating, material, categoryid, height,width,weight,description,image,brand,madein,amount, color) values
(product_seq.NEXTVAL,'LapTop Dell 302','Asus', 2000,200,'Dell',1,720,1080,2,'decription Dell','img.jpg', 'Asus','Chinna',4000,'black');

insert into product (id, name,type, discount, avgrating, material, categoryid, height,width,weight,description,image,brand,madein,amount, color) values
(product_seq.NEXTVAL,'LapTop Dell 4500','Asus', 2000,200,'Dell',1,720,1080,2,'decription Dell','img.jpg', 'Asus','Chinna',4000,'black');
insert into product (id, name,type, discount, avgrating, material, categoryid, height,width,weight,description,image,brand,madein,amount, color) values
(product_seq.NEXTVAL,'LapTop Dell 302','Asus', 2000,200,'Dell',1,720,1080,2,'decription Dell','img.jpg', 'Asus','Chinna',4000,'black');

insert into product (id, name,type, discount, avgrating, material, categoryid, height,width,weight,description,image,brand,madein,amount, color) values
(product_seq.NEXTVAL,'Tai Nghe gameming','Asus', 2000,200,'Gameming',3,720,1080,2,'decription Tai nghe','img.jpg', 'Asus','Chinna',4000,'black');
insert into product (id, name,type, discount, avgrating, material, categoryid, height,width,weight,description,image,brand,madein,amount, color) values
(product_seq.NEXTVAL,'Quáº§n bÃ²','quáº§n', 7000,200,'Dell',4,720,1080,2,'decription quáº§n','img.jpg', 'quáº§n','Chinna',7000,'black');

--TABLE CART_PRODUCT
CREATE SEQUENCE cart_product_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;
    
insert into cart_product(id,cart_id,product_id,quantity) values(cart_product_seq.NEXTVAL,1,1,10);
insert into cart_product(id,cart_id,product_id,quantity) values(cart_product_seq.NEXTVAL,2,1,10);
insert into cart_product(id,cart_id,product_id,quantity) values(cart_product_seq.NEXTVAL,2,2,10);
insert into cart_product(id,cart_id,product_id,quantity) values(cart_product_seq.NEXTVAL,4,2,10);
insert into cart_product(id,cart_id,product_id,quantity) values(cart_product_seq.NEXTVAL,3,3,10);
insert into cart_product(id,cart_id,product_id,quantity) values(cart_product_seq.NEXTVAL,3,3,10);
insert into cart_product(id,cart_id,product_id,quantity) values(cart_product_seq.NEXTVAL,2,5,10);
insert into cart_product(id,cart_id,product_id,quantity) values(cart_product_seq.NEXTVAL,2,3,10);
insert into cart_product(id,cart_id,product_id,quantity) values(cart_product_seq.NEXTVAL,2,4,10);

--TABLE BILL_DETAIL
CREATE SEQUENCE bill_detail_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;
    
insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_rating,id_product) 
values(bill_detail_seq.NEXTVAL,3000,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),1,1,1);

insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_rating,id_product) 
values(bill_detail_seq.NEXTVAL,3000,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),1,1,1);

insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_rating,id_product) 
values(bill_detail_seq.NEXTVAL,3500,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),1,1,1);

insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_rating,id_product) 
values(bill_detail_seq.NEXTVAL,5000,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),1,1,1);

insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_rating,id_product) 
values(bill_detail_seq.NEXTVAL,7000,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),2,1,1);

insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_rating,id_product) 
values(bill_detail_seq.NEXTVAL,8000,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),1,1,1);

insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_rating,id_product) 
values(bill_detail_seq.NEXTVAL,3000,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),1,1,1);

insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_rating,id_product) 
values(bill_detail_seq.NEXTVAL,9000,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),2,2,2);

insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_rating,id_product) 
values(bill_detail_seq.NEXTVAL,3000,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),1,1,1);

insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_rating,id_product) 
values(bill_detail_seq.NEXTVAL,3000,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),1,3,1);


--DROP KHOA NGOAI GIUA RATING VA BILL_DETAIL
ALTER TABLE BILL_DETAIL
    DROP CONSTRAINT FK14;
    
--DROP COLUMN ID_RATING TRONG BILL_DETAIL
ALTER TABLE BILL_DETAIL
    DROP COLUMN ID_RATING;
    
--SUA Báº¢NG RATING
ALTER TABLE RATING
    ADD PRODUCT_ID NUMBER(10, 0);
    
DELETE FROM RATING;

ALTER TABLE RATING
    ADD CONSTRAINT FK14 FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID);

DROP SEQUENCE RATING_SEQ;

CREATE SEQUENCE rating_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;
    
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'sáº£n pháº©m tá»‘t', 1, 1, 1);
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'sáº£n pháº©m nhanh há»?ng', 1, 1, 2);
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'giÃ¡ tá»‘t', 1, 1, 3);
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'sáº£n pháº©m tá»‘t trong táº§m giÃ¡', 1, 1, 4);
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'Comment2', 1, 1, 5);
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'DÃ¹ng nhanh há»?ng', 1, 1, 6);
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'GiÃ¡ tá»‘t', 1, 1, 7);
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'Comment1', 1, 1, 1);
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'Comment1', 1, 1, 1);
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'Comment1', 1, 1, 1);