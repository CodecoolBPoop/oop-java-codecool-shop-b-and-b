ALTER TABLE "order" DROP CONSTRAINT IF EXISTS "Order_fk0";

ALTER TABLE lineItems DROP CONSTRAINT IF EXISTS "LineItems_fk0";

ALTER TABLE lineItems DROP CONSTRAINT IF EXISTS "LineItems_fk1";

ALTER TABLE products DROP CONSTRAINT IF EXISTS "Products_fk0";

ALTER TABLE products DROP CONSTRAINT IF EXISTS "Products_fk1";

DROP TABLE IF EXISTS "order";

DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS lineitems;

DROP TABLE IF EXISTS products;

DROP TABLE IF EXISTS categories;

DROP TABLE IF EXISTS suppliers;

CREATE TABLE "order" (
	"id" serial NOT NULL,
	"user_id" integer NOT NULL,
	"total_price" FLOAT,
	"total_items" integer,
	"date" DATE,
	CONSTRAINT Order_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "users" (
	"id" serial NOT NULL,
	"password" VARCHAR(255) NOT NULL,
	"first_name" VARCHAR(255) NOT NULL,
	"last_name" VARCHAR(255) NOT NULL,
	"email" VARCHAR(255) NOT NULL,
	"phone_number" VARCHAR(255) NOT NULL,
	"shipping_address" VARCHAR(255) NOT NULL,
	"billing_address" VARCHAR(255),
	CONSTRAINT Users_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "lineitems" (
	"order_id" integer NOT NULL,
	"quantity" integer NOT NULL,
	"product_id" integer NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "products" (
	"id" serial NOT NULL,
	"name" VARCHAR(255) NOT NULL,
	"category_id" integer NOT NULL,
	"supplier_id" integer NOT NULL,
	"price" FLOAT NOT NULL,
	"currency" VARCHAR(255) NOT NULL,
  "description" varchar(255) NOT NULL,
	CONSTRAINT Products_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);


CREATE TABLE "categories" (
	"id" serial NOT NULL,
	"name" VARCHAR(255) NOT NULL,
	"description" VARCHAR(255) NOT NULL,
	"department" VARCHAR(255) NOT NULL,
	CONSTRAINT Categories_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "suppliers" (
	"id" serial NOT NULL,
	"name" VARCHAR(255) NOT NULL,
	"description" VARCHAR(255) NOT NULL,
	CONSTRAINT Suppliers_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "order" ADD CONSTRAINT "Order_fk0" FOREIGN KEY ("user_id") REFERENCES users("id");


ALTER TABLE lineitems ADD CONSTRAINT "LineItems_fk0" FOREIGN KEY ("order_id") REFERENCES "order"("id");
ALTER TABLE lineitems ADD CONSTRAINT "LineItems_fk1" FOREIGN KEY ("product_id") REFERENCES products("id");

ALTER TABLE products ADD CONSTRAINT "Products_fk0" FOREIGN KEY ("category_id") REFERENCES categories("id");
ALTER TABLE products ADD CONSTRAINT "Products_fk1" FOREIGN KEY ("supplier_id") REFERENCES suppliers("id");



