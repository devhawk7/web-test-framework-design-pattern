package com.framework.service;

import com.framework.config.ConfigReader;
import com.framework.model.Product;
import com.framework.model.User;

/** Builds business objects (via Builders) from the environment property file. */
public final class TestDataCreator {
    private TestDataCreator() { }

    public static User validUser() {
        ConfigReader c = ConfigReader.getInstance();
        return User.builder().username(c.get("user.valid.username")).password(c.get("user.valid.password")).build();
    }

    public static User lockedUser() {
        ConfigReader c = ConfigReader.getInstance();
        return User.builder().username(c.get("user.locked.username")).password(c.get("user.valid.password")).build();
    }

    public static Product product() {
        ConfigReader c = ConfigReader.getInstance();
        return Product.builder().name(c.get("product.name")).price(c.get("product.price")).build();
    }
}
