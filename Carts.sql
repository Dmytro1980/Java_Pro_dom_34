CREATE TABLE IF NOT EXISTS public."Carts"(
    "cart_id" INT NOT NULL,
    "product_id" INT NOT NULL,
    "product_name" VARCHAR(30) NOT NULL,
    "product_price" INTEGER NOT NULL,
    CONSTRAINT "cart_products_fk" FOREIGN KEY (product_id) REFERENCES "Products"(id)
);