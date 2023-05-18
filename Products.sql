CREATE TABLE IF NOT EXISTS public."Products"
(
    "id" SERIAL NOT NULL,
    "name" VARCHAR(30) NOT NULL,
    "price" INTEGER NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id)
);
