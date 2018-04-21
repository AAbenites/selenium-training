package model;

import java.util.Map;

public class Product {

    private String manufacturer;

    private String[] keywords;

    private String description;

    private Map<Float, String> priceCurrency;

    private String name;

    private String code;

    private int quantity;

    private String imagePath;

    private Product(ProductBuilder builder) {
        this.manufacturer = builder.getManufacturer();
        this.keywords = builder.getKeywords();
        this.description = builder.getDescription();
        this.priceCurrency = builder.getPriceCurrency();
        this.quantity = builder.getQuantity();
        this.name = builder.getName();
        this.code = builder.getCode();
        this.imagePath = builder.getImagePath();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public String getDescription() {
        return description;
    }

    public Map<Float, String> getPriceCurrency() {
        return priceCurrency;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCode() {
        return code;
    }

    public String getImagePath() {
        return imagePath;
    }

    public static class ProductBuilder {


        private String manufacturer = "ACME Corp.";

        private String[] keywords = {"yellow", "hat", "duck"};

        private String description = "test description";

        private Map<Float, String> priceCurrency;

        private int quantity = 100;

        private String name = "Sherlock Duck-" + System.currentTimeMillis();

        private String code = "SD" + System.currentTimeMillis();

        private String imagePath;

        public ProductBuilder setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public ProductBuilder setKeywords(String[] keywords) {
            this.keywords = keywords;
            return this;
        }

        public ProductBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder setPriceCurrency(Map<Float, String> priceCurrency) {
            this.priceCurrency = priceCurrency;
            return this;
        }

        public ProductBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public ProductBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder setCode(String code) {
            this.code = code;
            return this;
        }

        public ProductBuilder setImagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public String[] getKeywords() {
            return keywords;
        }

        public String getDescription() {
            return description;
        }

        public Map<Float, String> getPriceCurrency() {
            return priceCurrency;
        }

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }

        public String getImagePath() {
            return imagePath;
        }

        public Product build() {
            return new Product(this);
        }

        public int getQuantity() {
            return quantity;
        }
    }
}
