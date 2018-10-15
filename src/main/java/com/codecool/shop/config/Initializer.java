package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier bohi = new Supplier("Bohi", "The big nothing seller.");
        supplierDataStore.add(bohi);
        Supplier bazsi = new Supplier("Bazsi", "A real scammer.");
        supplierDataStore.add(bazsi);


        //setting up a new product category
        ProductCategory nothing = new ProductCategory("Nothing", "Nothing", "Nothing is .... nothing, right?? I don't know what you expected.");
        productCategoryDataStore.add(nothing);
        ProductCategory scam = new ProductCategory("Scam", "Scam", "Usually these products are nothing but scam!");
        productCategoryDataStore.add(scam);

        //setting up products and printing it
        productDataStore.add(new Product("Money to the trashcan", 10, "USD", "Fantastic price. Our bank account is the trashcan ;)", nothing, bohi));
        productDataStore.add(new Product("Nothing", 99, "USD", "Keyboard is not included. No usb ports. Basically it is NOTHING.", nothing, bohi));
        productDataStore.add(new Product("For the fishes", 2, "USD", "This is for the fishes", nothing, bohi));
        productDataStore.add(new Product("Make it rain",70,"USD","You have so much money and you can't do anything with it so just send it to us!",nothing,bazsi));
        productDataStore.add(new Product("Not a real product", 146,"USD", "This site doesn't sell anything i swear!", scam,bazsi));
        productDataStore.add(new Product("HUGE SCAM", 2000,"USD", "LIMITED OFFER! HUGE SCAM! BUY NOW!", scam,bazsi));
        productDataStore.add(new Product("Huge mistake", 911,"USD", "This is a huge mistake!",scam,bazsi));
        productDataStore.add(new Product("What is this??", 255,"USD", "What are you doing on this website brother??", scam,bohi));
        productDataStore.add(new Product("The money taker", 400,"USD", "Shut up, don't ask questions.We are just taking your money!",nothing,bohi));
        productDataStore.add(new Product("Trap", 300,"USD","It's a trap!",scam,bazsi));

    }
}
