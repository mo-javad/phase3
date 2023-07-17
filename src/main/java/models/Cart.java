package models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import enums.Message;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Cart {
    private static ArrayList<Food> chosenFoods = new ArrayList<>();
    private static ArrayList<Cart> allPersonCart = new ArrayList<>();

    private int customerID;
    private int restaurantID;

    public Cart(int customerID, int restaurantID) {
        this.customerID = customerID;
        this.restaurantID = restaurantID;
        addCart(this);
    }

    public void addFood(Food chosenFood) {
        this.chosenFoods.add(chosenFood);
        System.out.println(Message.SUCCESS);
        saveCartToFile();
    }

    public void deleteFood(Food chosenFood) {
        chosenFoods.remove(chosenFood);
        System.out.println(Message.SUCCESS);
        saveCartToFile();
    }

    public ArrayList<Food> getChosenFoods() {
        if (loadCartFromFile() != null)
            allPersonCart = new ArrayList<>(loadCartFromFile());
        return chosenFoods;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public static ArrayList<Cart> getAllPersonCart() {
        if (loadCartFromFile() != null)
            allPersonCart = new ArrayList<>(loadCartFromFile());
        return Cart.allPersonCart;
    }

    private void addCart(Cart cart) {
        if (loadCartFromFile() != null)
            allPersonCart = new ArrayList<>(loadCartFromFile());
        allPersonCart.add(cart);
        saveCartToFile();
    }

    public static void saveCartToFile() {
        try {
            FileWriter fileWriterCart = new FileWriter("src\\\\main\\\\oop\\files\\carts.json");
            Gson gson = new Gson();
            gson.toJson(allPersonCart, fileWriterCart);
            fileWriterCart.close();
        } catch (IOException e) {
            System.out.println(" ");
        }
    }

    public static ArrayList<Cart> loadCartFromFile() {
        try {
            FileReader fileReaderCart = null;
            fileReaderCart = new FileReader("src\\\\main\\\\oop\\files\\carts.json");
            Type type = new TypeToken<ArrayList<Cart>>() {
            }.getType();
            Gson gson = new Gson();
            ArrayList<Cart> allC = new ArrayList<>();
            allC = gson.fromJson(fileReaderCart, type);
            fileReaderCart.close();
            allPersonCart = new ArrayList<>();
            if (allC != null)
                allPersonCart.addAll(allC);
        } catch (IOException e) {
            System.out.println(" ");
        }
        return allPersonCart;
    }
}