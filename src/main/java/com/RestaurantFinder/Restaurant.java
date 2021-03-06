package com.RestaurantFinder;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }
    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public LocalTime getOpeningTime() { return openingTime; }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() { return closingTime; }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }
    public boolean isRestaurantOpen() {
        LocalTime time = this.getCurrentTime();
        int isRestaurantOpen = time.compareTo(this.getClosingTime());
        int isOpen = time.compareTo(this.getOpeningTime());
        if(isRestaurantOpen <0 && isOpen >= 0){
            return true;
        }
        return false;
    }



    public List<Item> getMenu() {
       return  this.menu;


        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public int getItemPrice(List<Item> item) {
        int price = 0;
        List<Item> menu = getMenu();
        /*
        This below code can be inproved for complexity
        We can take map instead of List for Menu.
        For time being I have used List
        * */
        for (Item item1: item) {
            for(Item item2: menu) {
                if(item1.getName().equals(item2.getName()))
                    price += item2.getPrice();
            }
        }

        return price;
    }

}
