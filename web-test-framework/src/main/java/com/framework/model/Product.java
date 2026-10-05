package com.framework.model;

import java.util.Objects;

public record Product(String name, String price) {

    /** PATTERN (bonus): Builder. */
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private String price;

        public Builder name(String name)   { this.name = name; return this; }
        public Builder price(String price) { this.price = price; return this; }

        public Product build() {
            return new Product(Objects.requireNonNull(name, "name is required"),
                               Objects.requireNonNull(price, "price is required"));
        }
    }
}
