ALTER TABLE "Order" DROP CONSTRAINT IF EXISTS "Order_fk0";

ALTER TABLE "LineItems" DROP CONSTRAINT IF EXISTS "LineItems_fk0";

ALTER TABLE "LineItems" DROP CONSTRAINT IF EXISTS "LineItems_fk1";

ALTER TABLE "Products" DROP CONSTRAINT IF EXISTS "Products_fk0";

ALTER TABLE "Products" DROP CONSTRAINT IF EXISTS "Products_fk1";

DROP TABLE IF EXISTS "Order";

DROP TABLE IF EXISTS "Users";

DROP TABLE IF EXISTS "LineItems";

DROP TABLE IF EXISTS "Products";

DROP TABLE IF EXISTS "Categories";

DROP TABLE IF EXISTS "Suppliers";

CREATE TABLE "Order" (
	"id" serial NOT NULL,
	"user_id" integer NOT NULL,
	"total_price" FLOAT,
	"total_items" integer,
	"date" DATE,
	CONSTRAINT Order_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Users" (
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



CREATE TABLE "LineItems" (
	"order_id" integer NOT NULL,
	"quantity" integer NOT NULL,
	"product_id" integer NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Products" (
	"id" serial NOT NULL,
	"category_id" integer NOT NULL,
	"supplier_id" integer NOT NULL,
	"price" FLOAT NOT NULL,
	"currency" VARCHAR(255) NOT NULL,
	CONSTRAINT Products_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Categories" (
	"id" serial NOT NULL,
	"name" VARCHAR(255) NOT NULL,
	"description" VARCHAR(255) NOT NULL,
	"department" VARCHAR(255) NOT NULL,
	CONSTRAINT Categories_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "Suppliers" (
	"id" serial NOT NULL,
	"name" VARCHAR(255) NOT NULL,
	"description" VARCHAR(255) NOT NULL,
	CONSTRAINT Suppliers_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "Order" ADD CONSTRAINT "Order_fk0" FOREIGN KEY ("user_id") REFERENCES "Users"("id");


ALTER TABLE "LineItems" ADD CONSTRAINT "LineItems_fk0" FOREIGN KEY ("order_id") REFERENCES "Order"("id");
ALTER TABLE "LineItems" ADD CONSTRAINT "LineItems_fk1" FOREIGN KEY ("product_id") REFERENCES "Products"("id");

ALTER TABLE "Products" ADD CONSTRAINT "Products_fk0" FOREIGN KEY ("category_id") REFERENCES "Categories"("id");
ALTER TABLE "Products" ADD CONSTRAINT "Products_fk1" FOREIGN KEY ("supplier_id") REFERENCES "Suppliers"("id");



