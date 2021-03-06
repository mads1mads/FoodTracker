package model.ingredients;

import model.exceptions.QuantityException;
import model.inventories.KitchenInventory;

import java.io.Serializable;
import java.time.LocalDate;

public class Ingredient implements Serializable {
    private double quantity;
    private GroceryCategory category;
    private Measurement measurement;
    private LocalDate purchaseDate;
    private int lifespan; //in days
    private String name;
    private LocalDate expiryDate;
    private boolean purchased;

    // Stores purchase date, expiry date, category, quantity, and name
    // TODO: Figure out how to do fractions

    public Ingredient(String name, GroceryCategory category, double quantity, Measurement measurement) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.measurement = measurement;
        this.purchased = false;
    }


    public boolean equals(Ingredient other) {
        return getName().equalsIgnoreCase(other.getName());
                //&& getCategory().toString().equals(other.getCategory().toString());
    }

    public String toString() {
        return getName();
    }

    //EFFECTS: returns category
    public GroceryCategory getCategory(){
        return category;
    }
    //EFFECTS: returns measurement
    public Measurement getMeasurement() {
        return measurement;
    }

    //EFFECTS: sets measure
    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public String sayHi(){
        return "ji";
    }

    //MODIFIES: this
    //EFFECTS: sets Ingredient's purchase date today, and corresponding expiry date
    public void purchase() throws QuantityException {
        KitchenInventory ki = new KitchenInventory();
        purchased = true;
        setExpiryDate(LocalDate.now());
        ki.add(this);
    }

    //MODIFIES: this
    //REQUIRES: a valid Grocery Category
    //EFFECTS: sets the lifespan depending on the grocery Category
    public void setLifespan(GroceryCategory cat) {
        if (cat.equals(GroceryCategory.DAIRY)) {
            lifespan = 14;
        } else if (cat.equals(GroceryCategory.DRIED_GOODS)) {
            lifespan = 60;
        } else if (cat.equals(GroceryCategory.PANTRY_BAKING)) {
            lifespan = 30 * 6;
        } else if (cat.equals(GroceryCategory.PRODUCE)) {
            lifespan = 5;
        } else if (cat.equals(GroceryCategory.PROTEIN)) {
            lifespan = 2;
        }
    }

    //MODIFIES: this
    //EFFECTS: sets Ingredient's purchase date today, and corresponding expiry date
    public void purchase(LocalDate date) {
        purchased = true;
        setPurchaseDate(date);
        setExpiryDate(purchaseDate);
    }

    //MODIFIES: this
    //EFFECTS: sets Ingredient's expiry date
    private void setExpiryDate(LocalDate date) {
        expiryDate = date.plusDays(lifespan);
    }


    //MODIFIES: this
    //EFFECTS: sets Ingredient's purchase date
    public void setPurchaseDate(LocalDate date) {
        purchaseDate = date;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }


    //REQUIRES: 0 >= amount <= current quantity
    //MODIFIES: this
    //EFFECTS: removes quantity amount from ingredient
    public void removeQuantity(double amount) {
        quantity -= amount;
    }

    //REQUIRES: amount >= 0
    //MODIFIES: this
    //EFFECTS: adds amount to quantity
    public void addToQuantity(double amount) {
        quantity += amount;
    }

    //EFFECTS: returns quantity
    public double getQuantity() {
        return quantity;
    }

    //EFFECTS: returns name
    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS: sets name
    public void setName(String name) {
        this.name = name;
    }

    //REQUIRES: quantity >= 0
    //MODIFIES: this
    //EFFECTS: sets quantity
    public void setQuantity(double quantity){
        this.quantity = quantity;
    }

    public String getExpiryDate() {
        LocalDate today = LocalDate.now();
        if (expiryDate.isBefore(today)) {
            return "EXPIRED";
        }
        return "Expires " + expiryDate.toString();
    }

    public static void main(String[] args) throws Exception {
        Ingredient i = new Ingredient("carrot", GroceryCategory.PRODUCE, 2, Measurement.NONE);
        i.purchase();
        System.out.println(i.getExpiryDate());
        System.out.println(i.getPurchaseDate());
    }

//    public boolean isExpired() {
//        LocalDate today = LocalDate.now();
//        return expiryDate.isBefore(today);
//    }
}
