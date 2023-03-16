-- Trigger for insert cartProduct.
/
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
--DROP TRIGGER DELETE_CARTPRODUCT;
--DROP trigger UPDATE_CARTPRODUCT;


/
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
    :NEW.date_order := TO_DATE(SYSDATE, 'DD/MM/YYYY HH24:MI:SS');
    :NEW.created_at := TO_DATE(SYSDATE, 'DD/MM/YYYY HH24:MI:SS');
    :NEW.updated_at := TO_DATE(SYSDATE, 'DD/MM/YYYY HH24:MI:SS');

END;
/
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
/
--procedure


CREATE OR REPLACE
PROCEDURE INSERT_CART_PRODUCT(CART_ID1 NUMBER, PRODUCT_ID1 NUMBER,QUANTITY1 NUMBER)
AS
BEGIN
MERGE INTO CART_PRODUCT USING DUAL ON (CART_ID =CART_ID1 AND PRODUCT_ID=PRODUCT_ID1 AND CART_PRODUCT.DELETED =0 )
    WHEN NOT MATCHED THEN insert  (id,cart_id,product_id,quantity) values(cart_product_seq.NEXTVAL,CART_ID1,PRODUCT_ID1,QUANTITY1)
    WHEN MATCHED THEN UPDATE SET QUANTITY = QUANTITY+QUANTITY1;
END INSERT_CART_PRODUCT;
