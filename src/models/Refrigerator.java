package models;

import java.util.ArrayList;
import java.util.List;

public class Refrigerator {

    NormalSlot[] normalSlots;
    FreezeSlot[] freezeSlots;

    public Refrigerator(){
        normalSlots = new NormalSlot[6];
        for (int i=0; i<6; i++){
            normalSlots[i] = new NormalSlot();
        }
        freezeSlots = new FreezeSlot[2];
        for (int i=0; i<2; i++){
            freezeSlots[i] = new FreezeSlot();
        }
    }

    public boolean addFood(Food food){
        int slotIndex = Integer.parseInt(""+food.getSlotComboBox().charAt(12))-1;
        if (food.getSlotComboBox().substring(0,11).equals("Freeze Slot")){
            return freezeSlots[slotIndex].addFood(food);
        }else{
            return normalSlots[slotIndex].addFood(food);
        }
    }

    public List<Food> getSortedFoodFromSlot(String slot){
        int slotIndex = Integer.parseInt(""+slot.charAt(10))-1;
        if (slot.substring(0,10).equals("freezeSlot")){
            return freezeSlots[slotIndex].getSortedFood();
        }else{
            return normalSlots[slotIndex].getSortedFood();
        }
    }

    public boolean removeFoodFromSlot(String slot,int n){
        int slotIndex = Integer.parseInt(""+slot.charAt(12))-1;
        if (slot.substring(0,11).equals("Freeze Slot")){
            return freezeSlots[slotIndex].removeFood(n);
        }else{
            return normalSlots[slotIndex].removeFood(n);
        }
    }

    public ArrayList<Food> getAllFood(){
        ArrayList<Food> allFood = new ArrayList<>();
        for (int i=0; i<2; i++){
            for (Food food : freezeSlots[i].getSortedFood()){
                allFood.add(food);
            }
        }
        for (int i=0; i<6; i++){
            for (Food food : normalSlots[i].getSortedFood()){
                allFood.add(food);
            }
        }
        return allFood;
    }
}
