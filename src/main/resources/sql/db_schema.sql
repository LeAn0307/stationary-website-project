--Some commands may need to be used

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
--drop sequence "ECOMMERCEWEBSITE"."ROLE_FUNCTION_SEQ";
--drop sequence "ECOMMERCEWEBSITE"."ROLE_SEQ";
--drop sequence "ECOMMERCEWEBSITE"."ROLE_USER_SEQ";
--drop sequence "ECOMMERCEWEBSITE"."USER_WEBSITE_SEQ";

--CREATE TABLE

CREATE TABLE product (
    id          NUMBER(10, 0) NOT NULL,
    name        VARCHAR2(255),
    type        VARCHAR2(255),
    discount    NUMBER(10, 0),
    avgrating   NUMBER(10, 0),
    material    VARCHAR2(255),
    category_id  NUMBER(10, 0),
    height      NUMBER(10, 0),
    width       NUMBER(10, 0),
    weight      NUMBER(10, 0),
    description VARCHAR2(2000),
    image       VARCHAR2(255),
    brand       VARCHAR2(255),
    made_in      VARCHAR2(255),
    amount      NUMBER(10, 0),
    color       VARCHAR2(255),
    price       NUMBER(10, 0),
    deleted     NUMBER(1,0) DEFAULT 0,
    
    CONSTRAINT product_pk PRIMARY KEY ( id )
);

CREATE TABLE categories (
    categories_id    NUMBER(20) NOT NULL,
    categories_name VARCHAR2(100),
    image           VARCHAR2(200),
    deleted     NUMBER(1,0) DEFAULT 0,
    
    CONSTRAINT categories_pk PRIMARY KEY ( categories_id )
);

CREATE TABLE bill (
    bill_id        NUMBER(10, 0) NOT NULL,
    customer_id    NUMBER(10, 0),
    id_bill_status NUMBER(10, 0),
    date_order     DATE,
    total          DECIMAL(10, 0),
    note           VARCHAR2(255),
    payment        VARCHAR2(255),
    code_momo       VARCHAR2(255),
    created_at     DATE,
    updated_at     DATE,
    deleted     NUMBER(1,0) DEFAULT 0,
    
    CONSTRAINT bill_pk PRIMARY KEY ( bill_id )
);

CREATE TABLE bill_detail (
    id         NUMBER(10, 0) NOT NULL,
    quantity   NUMBER(10, 0),
    price      DECIMAL(10, 0),
--    createdat  TIMESTAMP,
--    updatedat  TIMESTAMP,
    id_bill    NUMBER(10, 0),
    id_product NUMBER(10, 0),
    deleted     NUMBER(1,0) DEFAULT 0,
    
    CONSTRAINT bill_detail_pk PRIMARY KEY ( id )
);

CREATE TABLE bill_status (
    id     NUMBER(10, 0) NOT NULL,
    status VARCHAR(100),
    deleted     NUMBER(1,0) DEFAULT 0,
    
    CONSTRAINT bill_status_pk PRIMARY KEY ( id )
);

CREATE TABLE cart_product (
    id         NUMBER(10, 0) NOT NULL,
    cart_id    NUMBER(10, 0),
    product_id NUMBER(10, 0),
    quantity   NUMBER(10, 0),
    deleted     NUMBER(1,0) DEFAULT 0,
    
    CONSTRAINT cart_product_pk PRIMARY KEY ( id )
);

CREATE TABLE rating (
    id              NUMBER(10, 0) NOT NULL,
    comment_product VARCHAR2(200),
    rate_score      NUMBER(10, 0),
    id_user         NUMBER(10, 0),
    product_id      NUMBER(10, 0),
    deleted         NUMBER(1,0) DEFAULT 0,
    
    CONSTRAINT rating_pk PRIMARY KEY ( id )
);

CREATE TABLE cart_coupon (
    id        NUMBER(10, 0) NOT NULL,
    cart_id   NUMBER(10, 0),
    coupon_id NUMBER(10, 0),
    deleted         NUMBER(1,0) DEFAULT 0,
    
    CONSTRAINT cart_coupon_pk PRIMARY KEY ( id )
);

CREATE TABLE coupon (
    id              NUMBER(10, 0) NOT NULL,
    name            VARCHAR2(200),
    discount        NUMBER(3,2),
    MIN_PRICE_APPLY NUMBER(10,0),
    MAX_PRICE       NUMBER(10,0),
    CODE            VARCHAR2(20),
    AMOUNT          NUMBER(10,0),
    deleted         NUMBER(1,0) DEFAULT 0,
    
    CONSTRAINT coupon_pk PRIMARY KEY ( id )
);

CREATE TABLE cart (
    id      NUMBER(10, 0) NOT NULL,
    total   DECIMAL(10, 0),
    USER_ID NUMBER(10, 0),
    deleted NUMBER(1,0) DEFAULT 0,
    
    CONSTRAINT cart_pk PRIMARY KEY ( id )
);

CREATE TABLE account (
    id               NUMBER(10, 0) NOT NULL,
    email            VARCHAR2(255),
    account_password VARCHAR2(255),
    deleted          NUMBER(1,0) DEFAULT 0,
    
    CONSTRAINT account_pk PRIMARY KEY ( id )
);

CREATE TABLE user_website (
    id          NUMBER(10, 0) NOT NULL,
    user_name   VARCHAR2(255),
    address     VARCHAR2(255),
    phone       VARCHAR2(15),
    id_account  NUMBER(10, 0),
    deleted     NUMBER(1,0) DEFAULT 0,
    
    CONSTRAINT user_website_pk PRIMARY KEY ( id )
);

CREATE TABLE user_role (
    id          NUMBER(10, 0) NOT NULL,
    id_role     NUMBER(10, 0),
    id_user     NUMBER(10, 0),
    deleted     NUMBER(1,0) DEFAULT 0,
    
    CONSTRAINT user_role_pk PRIMARY KEY ( id )
);

CREATE TABLE role (
    id          NUMBER(10, 0) NOT NULL,
    name_role   VARCHAR2(50),
    deleted     NUMBER(1,0) DEFAULT 0,
    
    CONSTRAINT role_pk PRIMARY KEY ( id )
);
--
CREATE TABLE function (
    id              NUMBER(10, 0) NOT NULL,
    name            VARCHAR2(255),
    display_name    VARCHAR2(255),
    deleted         NUMBER(1,0) DEFAULT 0,
    
    CONSTRAINT function_pk PRIMARY KEY ( id )
);
--
CREATE TABLE role_function (
    id          NUMBER(10, 0) NOT NULL,
    role_id     NUMBER(10, 0),
    function_id NUMBER(10, 0),
    deleted     NUMBER(1,0) DEFAULT 0,
    
    CONSTRAINT role_function_pk PRIMARY KEY ( id )
);

--ADD FOREIGN KEY
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
    ADD CONSTRAINT fk04 FOREIGN KEY ( id_user )
        REFERENCES user_website ( id );
        
--  USER_WEBSITE- ACCOUNT
ALTER TABLE user_website
    ADD CONSTRAINT fk05 FOREIGN KEY ( id_account )
        REFERENCES account ( id );
        
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
    ADD CONSTRAINT fk10 FOREIGN KEY ( id_user )
        REFERENCES user_website ( id );
        
ALTER TABLE rating
    ADD CONSTRAINT FK18 FOREIGN KEY (PRODUCT_ID)
        REFERENCES PRODUCT (ID);

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
    ADD CONSTRAINT fk17 FOREIGN KEY ( category_id )
        REFERENCES categories ( categories_id );

--CART - USER_WEBSITE
ALTER TABLE CART
    ADD CONSTRAINT FK06 FOREIGN KEY (USER_ID) 
        REFERENCES USER_WEBSITE(ID);
/
-- Trigger for insert cartProduct.
CREATE OR REPLACE TRIGGER INSERT_CARTPRODUCT
BEFORE INSERT
    ON CART_PRODUCT
    FOR EACH ROW
BEGIN
UPDATE CART
SET CART.TOTAL=(CART.TOTAL+(SELECT :NEW.QUANTITY*P.PRICE FROM PRODUCT P WHERE CART.ID=:NEW.CART_ID AND  :NEW.PRODUCT_ID=P.ID ))
WHERE CART.ID=:NEW.CART_ID;
END;
/
CREATE OR REPLACE TRIGGER UPDATE_CARTPRODUCT
AFTER UPDATE
                 ON CART_PRODUCT
                 FOR EACH ROW
BEGIN
UPDATE CART
SET CART.TOTAL=(CART.TOTAL+(SELECT (:NEW.QUANTITY-:OLD.QUANTITY)*P.PRICE FROM  PRODUCT P WHERE CART.ID=:NEW.CART_ID AND :NEW.PRODUCT_ID=P.ID))
WHERE CART.ID=:NEW.CART_ID;
END;
/
-- DELETE CARTPRODUCT.
CREATE OR REPLACE TRIGGER DELETE_CARTPRODUCT
BEFORE DELETE
ON CART_PRODUCT
    FOR EACH ROW
BEGIN
UPDATE CART
SET CART.TOTAL=(CART.TOTAL-(SELECT :OLD.QUANTITY*P.PRICE FROM PRODUCT P WHERE CART.ID=:OLD.CART_ID AND  P.ID=:OLD.PRODUCT_ID))
WHERE CART.ID=:OLD.CART_ID;
END;
--DROP TRIGGER DELETE_CARTPRODUCT;
/
--phan cua tiep moi update
DROP TRIGGER DELETE_CARTPRODUCT;
DROP trigger UPDATE_CARTPRODUCT;

ALTER TABLE bill ADD address VARCHAR2(255);
ALTER TABLE bill ADD discount number(10,0);

create or replace TRIGGER UPDATE_CARTPRODUCT
AFTER UPDATE
                 ON CART_PRODUCT
                 FOR EACH ROW
BEGIN
UPDATE CART
SET CART.TOTAL=(CART.TOTAL+(SELECT (:NEW.QUANTITY-:OLD.QUANTITY)*P.PRICE FROM  PRODUCT P WHERE CART.ID=:NEW.CART_ID AND :NEW.PRODUCT_ID=P.ID)
                - (SELECT (:NEW.QUANTITY*:NEW.DELETED)*P.PRICE FROM  PRODUCT P WHERE CART.ID=:NEW.CART_ID AND :NEW.PRODUCT_ID=P.ID)
                )
WHERE CART.ID=:NEW.CART_ID;
END;

/
CREATE OR REPLACE TRIGGER create_bill
BEFORE INSERT ON bill
FOR EACH ROW
BEGIN
    :NEW.date_order := TO_CHAR(SYSDATE, 'DD/MM/YYYY HH24:MI:SS');
    :NEW.created_at := TO_CHAR(SYSDATE, 'DD/MM/YYYY HH24:MI:SS');
    :NEW.updated_at := TO_CHAR(SYSDATE, 'DD/MM/YYYY HH24:MI:SS');

END;

create or replace TRIGGER create_bill_after
AFTER INSERT ON bill
FOR EACH ROW
BEGIN
    insert into bill_detail (id, quantity, price,id_bill, id_product, deleted)
    select BILL_DETAIL_SEQ.NEXTVAL,cp.quantity,cp.quantity*p.price,:NEW.bill_id,cp.product_id, 0  from cart_product cp, product p
    where :NEW.customer_id= cp.cart_id and cp.product_id=p.id and cp.deleted=0;

    UPDATE cart_product
    SET deleted = '1'
    WHERE :NEW.customer_id= cart_id;

    UPDATE cart
    SET total = '0'
    WHERE :NEW.customer_id= id;

END;
/
CREATE OR REPLACE TRIGGER update_bill
BEFORE UPDATE ON bill
FOR EACH ROW
BEGIN
    :NEW.updated_at := TO_CHAR(SYSDATE, 'DD/MM/YYYY HH24:MI:SS');
END;







--Fix user_role table:
ALTER TABLE user_role
    DROP CONSTRAINT fk04;

ALTER TABLE user_role
    RENAME COLUMN id_user TO id_account;

ALTER TABLE user_role
    ADD CONSTRAINT fk04 FOREIGN KEY (id_account)
        REFERENCES account (id);

