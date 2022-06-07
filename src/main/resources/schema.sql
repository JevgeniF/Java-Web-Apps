DROP SEQUENCE IF EXISTS seq1 cascade;
DROP SEQUENCE IF EXISTS seq2 cascade;


DROP TABLE IF EXISTS order_rows;
DROP TABLE IF EXISTS orders;

CREATE SEQUENCE seq1 START WITH 1;
CREATE SEQUENCE seq2 START WITH 1;

CREATE TABLE orders
(
    order_id     BIGINT       NOT NULL DEFAULT nextval('seq1'),
    order_number VARCHAR(255) NOT NULL,
    PRIMARY KEY (order_id)
);

CREATE TABLE order_rows
(
    row_id    BIGINT       NOT NULL DEFAULT nextval('seq2'),
    order_id  BIGINT,
    item_name VARCHAR(255) NOT NULL,
    quantity  INT          NOT NULL,
    price     INT          NOT NULL,
    CONSTRAINT fk_order
        FOREIGN KEY (order_id)
            REFERENCES orders (order_id)

);