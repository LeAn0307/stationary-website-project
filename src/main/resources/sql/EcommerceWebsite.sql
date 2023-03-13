--
--drop table ACCOUNT;
--drop table BILL;
--drop table BILL_DETAIL;
--drop table BILL_STATUS;
--drop table CART;
--drop table CART_COUPON;
--drop table CART_PRODUCT;
--drop table CATEGORIES;
--drop table COUPON;
--drop table FUNCTION;
--drop table PRODUCT;
--drop table RATING;
--drop table ROLE;
--drop table ROLE_FUNCTION;
--drop table USER_ROLE;
--drop table USER_WEBSITE;
--
--drop sequence "ECOMMERCEWEBSITE"."ACCOUNT_SEQ";
--drop sequence "ECOMMERCEWEBSITE"."BILL_DETAIL_SEQ";
--drop sequence "ECOMMERCEWEBSITE"."BILL_SEQ";
--drop sequence "ECOMMERCEWEBSITE"."BILL_STATUS_SEQ";
--drop sequence "ECOMMERCEWEBSITE"."CART_COUPON_SEQ";
--drop sequence "ECOMMERCEWEBSITE"."CART_PRODUCT_SEQ";
--drop sequence "ECOMMERCEWEBSITE"."CART_SEQ";
--drop sequence "ECOMMERCEWEBSITE"."CATEGORIES_SEQ";
--drop sequence "ECOMMERCEWEBSITE"."COUPON_SEQ";
--drop sequence "ECOMMERCEWEBSITE"."FUNCTION_SEQ";
--drop sequence "ECOMMERCEWEBSITE"."PRODUCT_SEQ";
--drop sequence "ECOMMERCEWEBSITE"."RATING_SEQ";
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
    description VARCHAR2(2000),
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
    image           VARCHAR2(200),
    
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
    product_id          NUMBER(10, 0),
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
ALTER TABLE rating
    ADD CONSTRAINT fk18 FOREIGN KEY ( ipproduct )
        REFERENCES product ( id );
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
    
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'Nguyễn Viết Quý', 'Chư Puh, Gia Lai', '0134567896', 1, 1);
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'Phạm Phúc Hậu', 'Vũng Tàu', '0125487898', 2, 2);
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'Lê Thành Luân', 'Vũng Tàu', '0125487897', 3, 3);
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'Nguyễn Văn A', 'TP.Hồ Chí Minh', '0125487154', 4, 4);
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'Đinh Công Lương', 'Quảng Bình', '0125487458', 5, 5);
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'Duy Mạnh', 'Nghệ An', '0125487487', 6, 6);
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'Nguyễn Văn Minh', 'Bình Thuận', '0125487879', 7, 7);
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'Nguyễn Văn Sú', 'Lào Cai', '0125487147', 8, 8);
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'Aladin', 'Bình Dương', '0125484587', 9, 9);
INSERT INTO user_website values (user_website_seq.NEXTVAL, 'Nguyễnn Văn Hiếu', 'Dak Lak', '0125487852', 10, 10);


--TABLE COUPON  
CREATE SEQUENCE coupon_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;
    
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Khuyến mãi cho khách hàng mới', 0.1, 0, 50000, 'KMKHM', 50);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giảm 10% Giảm tối đa 50,000đ đơn tối thiểu 99,000đ', 0.1, 99000, 50000, 'HUJUI', 30);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giảm 20,000đ đơn tối thiểu 199,000đ', 0, 199000, 20000, 'HUYEJ', 30);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giảm 10% Giảm tối đa 300,000đ đơn tối thiểu 499,000đ', 0.1, 499000, 300000, 'YUITLR', 30);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giảm 50,000đ đơn tối thiểu 0đ', 0, 0, 50000, 'QIEHFU', 30);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giảm 10,000đ đơn tối thiểu 120,000đ', 0, 120000, 10000, 'TEWUYG', 30);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giảm 5% Giảm tối đa 500,000đ đơn tối thiếu 300,000đ', 0.05, 300000, 500000, 'OIUYTR', 30);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giảm 150,000đ đơn tối thiểu 1,500,000đ', 0, 1500000, 150000, 'RTEGDT', 30);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giảm 10% Giảm tối đa 60,000đ đơn tối thiểu 400,000đ', 0.1, 400000, 60000, 'YEUWGY', 30);
INSERT INTO coupon VALUES (coupon_seq.NEXTVAL, 'Giảm 10% Giảm tối đa 100,000đ đơn tối thiểu 600,000đ', 0.1, 600000, 100000, 'EGYFGE', 30);

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
INSERT INTO role VALUES (role_seq.NEXTVAL, 'Khách');
INSERT INTO role VALUES (role_seq.NEXTVAL, 'Quản lý kho');
INSERT INTO role VALUES (role_seq.NEXTVAL, 'Nhân viên bán hàng');
INSERT INTO role VALUES (role_seq.NEXTVAL, 'Thu ngân');
INSERT INTO role VALUES (role_seq.NEXTVAL, 'Quản lý nhân sự');

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
    
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Quản lý sản phẩm', 'Thêm, xóa, sửa sản phẩm');
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Quản lý khách hàng', 'Tra cứu các thông tin của khách hàng');
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Phân quyền', 'Giới hạn chức năng cho một ngư�?i dùng');
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Mua hàng', 'Xem và thêm sản phẩm vào gi�? và tiến hành thanh toán');
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Quản lý khuyến mãi', 'Thêm, xóa, sửa khuy?n mãi');
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Quản lý đơn hàng', 'Xem, tra cứu tất cả đơn hàng của cửa hàng');
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Tra cứu sản phẩm', 'Tra cứu thông tin sản phẩm');
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Quản lý kho hàng', 'Thay đổi số lượng sản phẩm đang có trong cửa hàng');
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Thống kê và báo cáo', 'Xem báo cáo kinh doanh cửa cửa hàng');
INSERT INTO function VALUES (function_seq.NEXTVAL, 'Đánh giá sản phẩm', '�?ánh giá sản phẩm');

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
insert into bill_status(id,status) values(bill_status_seq.NEXTVAL, 'Phạt');
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
    
insert into rating(id, comment_product,ratescore,product_id,iduser) values(rating_seq.NEXTVAL,'Comment1',5, 1,1);
insert into rating(id, comment_product,ratescore,product_id,iduser) values(rating_seq.NEXTVAL,'Comment1', 1,1);
insert into rating(id, comment_product,ratescore,product_id,iduser) values(rating_seq.NEXTVAL,'Comment', 1,1);
insert into rating(id, comment_product,ratescore,product_id,iduser) values(rating_seq.NEXTVAL,'Comment', 1,1);
insert into rating(id, comment_product,ratescore,product_id,iduser) values(rating_seq.NEXTVAL,'�?ẹp', 4,1,2);
insert into rating(id, comment_product,ratescore,product_id,iduser) values(rating_seq.NEXTVAL,'Comment3', 1,1,1);
insert into rating(id, comment_product,ratescore,product_id,iduser) values(rating_seq.NEXTVAL,'Comment4',1, 1,1);
insert into rating(id, comment_product,ratescore,product_id,iduser) values(rating_seq.NEXTVAL,'Comment4',1, 2,1);
insert into rating(id, comment_product,ratescore,product_id,iduser) values(rating_seq.NEXTVAL,'Comment6',1, 1,1);
insert into rating(id, comment_product,ratescore,product_id,iduser) values(rating_seq.NEXTVAL,'Tuyệt',1, 2,1);

--TABLE CATEGORIES
CREATE SEQUENCE categories_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;
    
insert into categories(categoriesid,categories_name,image) values(categories_seq.NEXTVAL,'Dụng cụ văn phòng','dungcuvanphong.png');
insert into categories(categoriesid,categories_name,image) values(categories_seq.NEXTVAL,'Giấy','giay.jpg');
insert into categories(categoriesid,categories_name,image) values(categories_seq.NEXTVAL,'Bìa hồ sơ','biahoso.jpg');
insert into categories(categoriesid,categories_name,image) values(categories_seq.NEXTVAL,'Bút viết','but.jpg');
insert into categories(categoriesid,categories_name,image) values(categories_seq.NEXTVAL,'Tập sổ','tapso.jpg');
insert into categories(categoriesid,categories_name,image) values(categories_seq.NEXTVAL,'Lưu trữ','luutru.jpg');
insert into categories(categoriesid,categories_name,image) values(categories_seq.NEXTVAL,'Thiết bị','thietbi.jpg');
insert into categories(categoriesid,categories_name,image) values(categories_seq.NEXTVAL,'Gia dụng vệ sinh','giadungvesinh.jpg');
insert into categories(categoriesid,categories_name,image) values(categories_seq.NEXTVAL,'Mực in - Ruban','mucinruban.jpg');

--TABLE PRODUCT
CREATE SEQUENCE product_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;

insert into product (id, name,type, discount, avgrating, material, categoryid, height,width,weight,description,image,brand,madein,amount, color,price) values
(product_seq.NEXTVAL,'Bấm kim Kanex HD10 - 10 t?','Kanex', 2000,'','Nhật Bản',1,720,1080,2,'Bấm kim Kanex HD10 - 10 tờ (B-11.6) với thân máy bằng nhựa, dạng bấm, lực bấm 10 tờ, sử dụng kim bấm no.10 liên kết và phân nhóm tài liệu bằng lực bấm hoặc đóng nhiều tờ giấy thành xấp hoặc cuốn phù hợp với nghiệp vụ photocopy và lưu trữ chứng từ liên kết và phân nhóm tài liệu bằng lực bấm hoặc đóng nhiều tờ giấy thành xấp hoặc cuốn phù hợp với nghiệp vụ photocopy và lưu trữ chứng từ ✓Tiết kiệm từ 10% - 30%
Kanex là nhà sản xuất uy tín tại Ấn Độ về sản phẩm máy bấm kim, bấm lổ, kim bấm văn phòng
Sản phẩm Bấm kim chính hãng đáp ứng tiêu chuẩn bảo vệ sức khỏe người dùng và nâng cao tối đa hiệu quả công việc văn phòng mỗi ngày.
Bạn có thể đặt mua nhiều dòng văn phòng phẩm Kanex tại Officexinh.com với mức giá thật ưu đãi và nhận được chính sách vận chuyển miễn phí nếu có dựa trên tổng giá trị đơn hàng và tùy theo khu vực nhận hàng.
Bấm kim Kanex HD10 - 10 tờ còn gọi là bấm ghim, bấm kim số 10 có đơn vị tính là Cái và được đóng gói theo quy cách: 10 cái / lốc
Mẫu mã và thông tin sản phẩm có thể thay đổi theo chính sách nhà sản xuất.','product-1.jpg', 'Kanex','Inda',4000,'Trắng xanh','20000');
insert into product (id, name,type, discount, avgrating, material, categoryid, height,width,weight,description,image,brand,madein,amount, color,price) values
(product_seq.NEXTVAL,'Giấy Double A A4 80','Giấy in', 2000,'','Giấy',2,720,1080,2,'Giấy Double A A4 80 (G-61) với kích thước: A4 (210 mm x 297 mm), định lượng: 80gms, nền giấy trắng in ấn chứng từ, tài liệu, hợp đồng dựa trên khổ giấy chuẩn phù hợp với các nghiệp vụ văn phòng in ấn chứng từ, tài liệu, hợp đồng dựa trên khổ giấy chuẩn phù hợp với các nghiệp vụ văn phòng ✓Tiết kiệm từ 10% - 30%
Double A là hãng sản xuất uy tín tại Thái Lan về sản phẩm giấy photocopy, giấy in, giấy bìa màu
Sản phẩm Giấy in ấn chính hãng đáp ứng tiêu chuẩn bảo vệ sức khỏe người dùng và nâng cao tối đa hiệu quả công việc văn phòng mỗi ngày.
Bạn có thể đặt mua nhiều dòng văn phòng phẩm Double A tại Officexinh.com với mức giá thật ưu đãi và nhận được chính sách vận chuyển miễn phí nếu có dựa trên tổng giá trị đơn hàng và tùy theo khu vực nhận hàng.
Giấy Double A A4 80 còn gọi là giấy photocopy, giấy in, giấy văn phòng có đơn vị tính là Ream và được đóng gói theo quy cách: 5 reams / thùng
Mẫu mã và thông tin sản phẩm có thể thay đổi theo chính sách nhà sản xuất.','product-2.jpg', 'Double A','Thái Lan',4000,'red','93000');

insert into product (id, name,type, discount, avgrating, material, categoryid, height,width,weight,description,image,brand,madein,amount, color,price) values
(product_seq.NEXTVAL,'Bìa còng F4 5cm',' Bìa hồ sơ', 2000,'','Nhựa',3,'','',2,'Bìa còng F4 5cm (loại 1) với độ dày 5 cm, thân bọc simili, dạng còng bật 2 lổ, lưu trữ 300 tờ giấy A4 lưu trữ, phân loại và bảo vệ bề mặt chứng từ giấy có kích thước A4, A5 đã được tạo khoen lổ dọc thân lưu trữ, phân loại và bảo vệ bề mặt chứng từ giấy có kích thước A4, A5 đã được tạo khoen lổ dọc thân ✓Tiết kiệm từ 10% - 30%
Sản phẩm được sản xuất bởi các doanh nghiệp văn phòng phẩm uy tín tại Việt Nam
Sản phẩm Bìa còng chính hãng đáp ứng tiêu chuẩn bảo vệ sức khỏe người dùng và nâng cao tối đa hiệu quả công việc văn phòng mỗi ngày.
Bạn có thể đặt mua nhiều dòng văn phòng phẩm Việt Nam tại Officexinh.com với mức giá thật ưu đãi và nhận được chính sách vận chuyển miễn phí nếu có dựa trên tổng giá trị đơn hàng và tùy theo khu vực nhận hàng.
Bìa còng F4 5cm (loại 1) còn gọi là bìa 2 còng có đơn vị tính là Cái và được đóng gói theo quy cách: 50 cái / thùng
Mẫu mã và thông tin sản phẩm có thể thay đổi theo chính sách nhà sản xuất.','product-3.jpg', 'F4','Việt Nam',4000,'black','7000');
insert into product (id, name,type, discount, avgrating, material, categoryid, height,width,weight,description,image,brand,madein,amount, color,price) values
(product_seq.NEXTVAL,'Bút bi cắm bàn TL','Bút Bi', 2000,'','Nhựa',1,'','',4,'Bút bi cắm bàn TL (B-10.8) với thân nhựa, dạng để bàn, ngòi bi, nét mực êm, cở ngòi 0.5 mm, có dây nối với đế, có lớp keo trống trượt mm, màu mực xanh ghi chú nội dung ,ký kết hợp đồng ghi chú nội dung ,ký kết hợp đồng ✓Tiết kiệm từ 10% - 30%
Thiên Long là thương hiệu hàng đầu tại Việt Nam về sản phẩm bút viết, đồ dùng văn phòng
Sản phẩm Bút bi chính hãng đáp ứng tiêu chuẩn bảo vệ sức khỏe người dùng và nâng cao tối đa hiệu quả công việc văn phòng mỗi ngày.
Bạn có thể đặt mua nhiều dòng văn phòng phẩm Thiên Long tại Officexinh.com với mức giá thật ưu đãi và nhận được chính sách vận chuyển miễn phí nếu có dựa trên tổng giá trị đơn hàng và tùy theo khu vực nhận hàng.
Bút bi cắm bàn TL còn gọi là viết bi, bút mực, viết mực, bút bi bấm có đơn vị tính là Cây Mẫu mã và thông tin sản phẩm có thể thay đổi theo chính sách nhà sản xuất.','product-3.jpg', 'Thiên Long','Việt Nam',4000,'Trắng và xanh','50000');

insert into product (id, name,type, discount, avgrating, material, categoryid, height,width,weight,description,image,brand,madein,amount, color,price) values
(product_seq.NEXTVAL,'Sổ bìa da Vivaone S804','Sổ bìa',5000,'', 'Da',5,17,28,2,'Sổ bìa da Vivaone S804 với kích thước: 17 x 24 cm, bìa bọc simili, có nút gài, nền giấy trắng kẻ ngang, 240 trang ghi chú nội dung chuyên dụng ghi chú nội dung chuyên dụng ✓Tiết kiệm từ 10% - 30%
Sản phẩm được sản xuất bởi các doanh nghiệp văn phòng phẩm uy tín tại Việt Nam
Sản phẩm Sổ tập chính hãng đáp ứng tiêu chuẩn bảo vệ sức khỏe người dùng và nâng cao tối đa hiệu quả công việc văn phòng mỗi ngày.
Bạn có thể đặt mua nhiều dòng văn phòng phẩm Việt Nam tại Officexinh.com với mức giá thật ưu đãi và nhận được chính sách vận chuyển miễn phí nếu có dựa trên tổng giá trị đơn hàng và tùy theo khu vực nhận hàng.
Sổ bìa da Vivaone S804 có đơn vị tính là Cuốn Mẫu mã và thông tin sản phẩm có thể thay đổi theo chính sách nhà sản xuất.','product-4.jpg', 'Vivaone ','Việt Nam',4000,'Nâu','85000');
insert into product (id, name,type, discount, avgrating, material, categoryid, height,width,weight,description,image,brand,madein,amount, color,price) values
(product_seq.NEXTVAL,'Khay đứng 4 ngăn Xukiva 212','Khay', 2000,'','Nhựa',6,'','',2,'Khay đứng 4 ngăn Xukiva 212 (K-32) với dạng xéo, thân vỏ nhựa, 3 ngăn, khả năng lưu trữ 1800 tờ lưu trữ và phân loại tài liệu F4, A4 lưu trữ và phân loại tài liệu F4, A4 ✓Tiết kiệm từ 10% - 30%
Xukiva là nhà sản xuất uy tín tại Việt Nam về sản phẩm đồ dùng văn phòng
Sản phẩm Khay kệ chính hãng đáp ứng tiêu chuẩn bảo vệ sức khỏe người dùng và nâng cao tối đa hiệu quả công việc văn phòng mỗi ngày.
Bạn có thể đặt mua nhiều dòng văn phòng phẩm Xukiva tại Officexinh.com với mức giá thật ưu đãi và nhận được chính sách vận chuyển miễn phí nếu có dựa trên tổng giá trị đơn hàng và tùy theo khu vực nhận hàng.
Khay đứng 4 ngăn Xukiva 212 còn gọi là kệ rỗ, kệ xéo, kệ liên hoàn có đơn vị tính là Cái Mẫu mã và thông tin sản phẩm có thể thay đổi theo chính sách nhà sản xuất.','product-5.jpg', 'Xukiva','Việt Nam',4000,'Xanh','55000');

insert into product (id, name,type, discount, avgrating, material, categoryid, height,width,weight,description,image,brand,madein,amount, color,price) values
(product_seq.NEXTVAL,'Casio AX-120B','Máy tính', 2000,'','Nhựa',7,175,5,2,'Casio AX-120B (G-336) với màn hình 12 số, kích thước: 25(Dày) × 110(Rộng) × 175,5(Dài) mm, phím tính cơ bản, sử dụng năng lượng mặt trời và pin tính toán chuyên dụng đa năng ✓Tiết kiệm từ 10% - 30%
Casio là hãng sản xuất uy tín tại Nhật Bản về sản phẩm máy tính học sinh, máy tính văn phòng
Sản phẩm Máy tính chính hãng đáp ứng tiêu chuẩn bảo vệ sức khỏe người dùng và nâng cao tối đa hiệu quả công việc văn phòng mỗi ngày.
Bạn có thể đặt mua nhiều dòng văn phòng phẩm Casio tại Officexinh.com với mức giá thật ưu đãi và nhận được chính sách vận chuyển miễn phí nếu có dựa trên tổng giá trị đơn hàng và tùy theo khu vực nhận hàng.
Casio AX-120B còn gọi là máy tính bàn, máy tính văn phòng có đơn vị tính là Cái Mẫu mã và thông tin sản phẩm có thể thay đổi theo chính sách nhà sản xuất.','product-6.jpg', 'Casio','Nhật Bản',3000,'Đen xám','290000');

insert into product (id, name,type, discount, avgrating, material, categoryid, height,width,weight,description,image,brand,madein,amount, color,price) values
(product_seq.NEXTVAL,'Nước rửa tay Lifebuoy 493ml','Nước rửa tay', 2000,'','Nước rửa tay',8,'','','','Nước rửa tay Lifebuoy 493ml với dạng lỏng, dung tích 493ml, có vòi xịt, sát khuẩn mạnh, bảo vệ da, hương thơm tự nhiên vệ sinh cơ thể vệ sinh cơ thể ✓Tiết kiệm từ 10% - 30%
Lifebuoy là nhà sản xuất nổi tiếng tại Việt Nam về sản phẩm xà phòng, nước rửa tay, dung dịch tẩy rửa
Sản phẩm Chất tẩy rửa - xịt phòng chính hãng đáp ứng tiêu chuẩn bảo vệ sức khỏe người dùng và nâng cao tối đa hiệu quả công việc văn phòng mỗi ngày.
Bạn có thể đặt mua nhiều dòng văn phòng phẩm Lifebuoy tại Officexinh.com với mức giá thật ưu đãi và nhận được chính sách vận chuyển miễn phí nếu có dựa trên tổng giá trị đơn hàng và tùy theo khu vực nhận hàng.
Nước rửa tay Lifebuoy 493ml còn gọi là xà phòng rửa tay có đơn vị tính là Chai Mẫu mã và thông tin sản phẩm có thể thay đổi theo chính sách nhà sản xuất.','product-7.jpg', 'Lifebuoy','Việt Nam',5000,'Trắng xanh','75000');

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
    
insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_product) 
values(bill_detail_seq.NEXTVAL,3000,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),1,1);

insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_product) 
values(bill_detail_seq.NEXTVAL,3000,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),1,1);

insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_product) 
values(bill_detail_seq.NEXTVAL,3500,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),1,1);

insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_product) 
values(bill_detail_seq.NEXTVAL,5000,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),1,1);

insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_product) 
values(bill_detail_seq.NEXTVAL,7000,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),1,1);

insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_product) 
values(bill_detail_seq.NEXTVAL,8000,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),1,1,1);

insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_product) 
values(bill_detail_seq.NEXTVAL,3000,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),1,1);

insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_product) 
values(bill_detail_seq.NEXTVAL,9000,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),2,2);

insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_product) 
values(bill_detail_seq.NEXTVAL,3000,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),1,1);

insert into bill_detail(id,quantity,price,createdat,updatedat,id_bill,id_product) 
values(bill_detail_seq.NEXTVAL,3000,3000,TO_DATE('02/02/2022', 'dd/mm/yyyy'),TO_DATE('02/02/2022', 'dd/mm/yyyy'),3,1);



DROP SEQUENCE RATING_SEQ;

CREATE SEQUENCE rating_seq
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 10;
    
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'sản phẩm tốt', 1, 1, 1);
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'sản phẩm nhanh h�?ng', 1, 1, 2);
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'giá tốt', 1, 1, 3);
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'sản phẩm tốt trong tầm giá', 1, 1, 4);
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'Comment2', 1, 1, 5);
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'Dùng nhanh hỏng', 1, 1, 6);
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'Giá tốt', 1, 1, 7);
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'Comment1', 1, 1, 1);
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'Comment1', 1, 1, 1);
INSERT INTO rating(id, comment_product, ratescore, iduser, product_id) values(rating_seq.NEXTVAL, 'Comment1', 1, 1, 1);

--FIX TABLE USER_WEBSITE AND CART
ALTER TABLE USER_WEBSITE
    DROP CONSTRAINT FK06;

ALTER TABLE USER_WEBSITE
    DROP COLUMN IDCART;

ALTER TABLE CART
    ADD USER_ID NUMBER(10, 0);

--UPDATE USER_ID IN CART
DECLARE
    TEMP NUMBER(10,0) := 1;
BEGIN
    FOR UPDATE_USERID_INTO_CART IN 1..10
    LOOP
        UPDATE CART
        SET USER_ID = TEMP
        WHERE ID = TEMP;
        TEMP := TEMP + 1;
    END LOOP;
END;

--CREATE FOREIGN KEY FROM CART TO USER_WEBSITE
ALTER TABLE CART
    ADD CONSTRAINT FK06 FOREIGN KEY (USER_ID) REFERENCES USER_WEBSITE(ID);

--CREATE TRIGGER FOR CREATE CART AFTER INSERT USER_WEBSITE
CREATE OR REPLACE TRIGGER CREATE_CART
AFTER INSERT
    ON USER_WEBSITE
    FOR EACH ROW
BEGIN

    INSERT INTO CART(ID, TOTAL, USER_ID) VALUES (CART_SEQ.NEXTVAL, 0, :NEW.ID);

END;

--Có thể dùng lệnh dưới để drop column status trong bảng bill trong trư�?ng hợp lỡ tạo db
--alter table bill
--    drop column status;
-- ADD COLUMN PRICE INTO PRODUCT TABLE
ALTER TABLE PRODUCT
    ADD PRICE NUMBER(10,0);

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