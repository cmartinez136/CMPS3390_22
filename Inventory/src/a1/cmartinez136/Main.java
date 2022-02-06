package a1.cmartinez136;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); //takes info from console
        Random ran = new Random();
        List<Item> items = new ArrayList<>();
        FoodItems[] foodItems = FoodItems.values();
        Tools[] tools = Tools.values();
        ToolUses[] toolUses = ToolUses.values();
        Weapons[] weapons = Weapons.values();
        Materials[] mats = Materials.values();
        MatUses[] matUses = MatUses.values();


        System.out.print("How many items do you want: ");
        int itemCnt = Integer.parseInt(scan.nextLine());

        for(int i=0; i < itemCnt; i++){
            int type = ran.nextInt(3);
            switch(type) {
                case 0:
                    int foodIndex = ran.nextInt(foodItems.length);
                    String foodName = foodItems[foodIndex].toString();
                    float foodPrice = ran.nextFloat(10);
                    int foodQty = ran.nextInt(30); //random and gives max of 30
                    int foodUses = ran.nextInt(6);
                    float healthGain = ran.nextFloat(20);
                    Food tempFood = new Food(foodName, foodPrice, foodQty, foodUses, healthGain);
                    items.add(tempFood);

                case 1:
                    int toolIndex = ran.nextInt(tools.length);
                    String toolName = tools[toolIndex].toString();
                    float toolPrice = ran.nextFloat(200);
                    int toolQty = ran.nextInt(15);
                    String use = toolUses[toolIndex].toString();
                    Tool tempTool = new Tool(toolName, toolPrice, toolQty, use);
                    items.add(tempTool);
                    break;

                case 2:
                    int weaponIndex = ran.nextInt(weapons.length);
                    String weaponName = weapons[weaponIndex].toString();
                    float weaponPrice = ran.nextFloat(300);
                    int wepQty = ran.nextInt(20);
                    int wepAtk = ran.nextInt(20);
                    Weapon tempWep = new Weapon(weaponName, weaponPrice, wepQty,wepAtk);
                    items.add(tempWep);

                case 3:
                    int materialIndex = ran.nextInt(mats.length);
                    String matName = mats[materialIndex].toString();
                    float matPrice = ran.nextFloat(10);
                    int matQty = ran.nextInt(15);
                    String mUses = matUses[materialIndex].toString();
                    Material tempMat = new Material(matName, matPrice, matQty, mUses);
                    items.add(tempMat);
            }
        }
        for(Item i : items){
            System.out.println(i);
        }
    }
}
