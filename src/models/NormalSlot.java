package models;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class NormalSlot {
    List<Food> foodInSlot;

    public NormalSlot(){
        foodInSlot = new LinkedList<>();
    }

    public boolean addFood(Food food){
        if (checkFood(food.getFoodType())) {
            foodInSlot.add(food);
            return true;
        }
        return false;
    }

    public List<Food> getSortedFood(){
        Collections.sort(foodInSlot);
        return foodInSlot;
    }

    public boolean removeFood(int n){
        if (foodInSlot.size() < n){
            return false;
        }
        foodInSlot = foodInSlot.subList(n,foodInSlot.size());
        return true;
    }

    public boolean checkFood(String type){
        if (foodInSlot.size() == 0){
            return true;
        }else if (foodInSlot.get(0).getFoodType().equals(type)){
            return true;
        }
        return false;
    }
}
