package a1.cmartinez136;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
        private static final Random ran = new Random();
        private static final FoodItems[] foodItems = FoodItems.values();
        private static final Tools[] tools = Tools.values();
        private static final ToolUses[] toolUses = ToolUses.values();
        private static final Weapons[] weapons = Weapons.values();
        private static final Materials[] mats = Materials.values();
        private static final MatUses[] matUses = MatUses.values();

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        Scanner scan = new Scanner(System.in); //takes info from console

        System.out.print("How many items do you want: ");
        int itemCnt = Integer.parseInt(scan.nextLine());
        for(int i=0; i < itemCnt; i++){
            int type = ran.nextInt(4);
            switch (type) {
                case 0 -> items.add(genFood());
                case 1 -> items.add(genTool());
                case 2 -> items.add(genWep());
                case 3 -> items.add(genMat());
            }
        }
        for(Item i : items){
            System.out.println(i);
        }
    }
    public static Food genFood(){
        int foodIndex = ran.nextInt(foodItems.length);
        String foodName = foodItems[foodIndex].toString();
        float foodPrice = ran.nextFloat(10);
        int foodQty = ran.nextInt(30); //random and gives max of 30
        int foodUses = ran.nextInt(6);
        float healthGain = ran.nextFloat(20);
        return new Food(foodName, foodPrice, foodQty, foodUses, healthGain);
    }
    public static Tool genTool(){
        int toolIndex = ran.nextInt(tools.length);
        String toolName = tools[toolIndex].toString();
        float toolPrice = ran.nextFloat(200);
        int toolQty = ran.nextInt(15);
        String use = toolUses[toolIndex].toString();
        return new Tool(toolName, toolPrice, toolQty, use);
    }
    public static Weapon genWep(){
        int weaponIndex = ran.nextInt(weapons.length);
        String weaponName = weapons[weaponIndex].toString();
        float weaponPrice = ran.nextFloat(300);
        int wepQty = ran.nextInt(20);
        int wepAtk = ran.nextInt(20);
        return new Weapon(weaponName, weaponPrice, wepQty,wepAtk);
    }
    public static Material genMat(){
        int materialIndex = ran.nextInt(mats.length);
        String matName = mats[materialIndex].toString();
        float matPrice = ran.nextFloat(10);
        int matQty = ran.nextInt(15);
        String mUses = matUses[materialIndex].toString();
        return new Material(matName, matPrice, matQty, mUses);
    }
}
