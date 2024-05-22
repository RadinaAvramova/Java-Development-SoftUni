package easterBasket;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        Basket basket = new Basket("Wood",20);
//
//
//        Egg egg1 = new Egg("Blue",9,"pointy");
//
//        Egg egg = new Egg("Red",10,"oval");
//        System.out.println(egg);
//        basket.addEgg(egg);
//        basket.addEgg(egg1);
//        System.out.println(basket.report());
//
//        Object egg = createObjectInstance(getType("Egg"),new Object[]{"Yellow",12,"pointy"});
//        Object egg2 = createObjectInstance(getType("Egg"),new Object[]{"Red",22,"round"});
//        Object egg3 = createObjectInstance(getType("Egg"),new Object[]{"Blue",2,"cracked"});
//        getMethodValue(this.classType, this.classObject, "addEgg", new Object[]{egg},getType("Egg"));
//        getMethodValue(this.classType, this.classObject, "addEgg", new Object[]{egg2},getType("Egg"));
//        getMethodValue(this.classType, this.classObject, "addEgg", new Object[]{egg3},getType("Egg"));

        Basket basket = new Basket("Cloth",5);
        Egg egg = new Egg("Yellow",12,"pointy");
        Egg egg2 = new Egg("Red",22,"round");
        Egg egg3 = new Egg("Blue",2,"cracked");
        basket.addEgg(egg);
        basket.addEgg(egg2);
        basket.addEgg(egg3);
        basket.removeEgg("Blue");
        System.out.println(basket.report());

    }
}
