package models;

public class CoworkingSpace {
    private String id;
    private String type;
    private double price;
    private boolean availability;
    public CoworkingSpace(String id, String type, double price, boolean availability) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.availability = availability;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
