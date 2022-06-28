package com.formos.interview;

import com.formos.interview.domain.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        int choose = -1;
        Stock stock = new Stock();
        //
        stock.initTestValue(100, 1000);
        while (choose != 0) {
            menu();
            choose = waitInteger();
            switch (choose) {
                case 0:
                    //quit application
                    break;
                case 1:
                    stock.view();
                    break;
                case 2:
                    sellSmoothie(stock);
                    break;
                case 3:
                    checkingStock(stock);
                    break;
                default:
                    System.out.println("Incorrect !!!, Please choose in the menu");
                    //do nothing
            }
        }
    }

    private static void checkingStock(Stock stock) {
        List<Product.Flavor> flavors = Arrays.asList(Product.Flavor.Banana, Product.Flavor.Mango, Product.Flavor.Strawberry);
		boolean stockIsGood = true;
        for (Product.Flavor flavor: flavors) {
            if (!checkRecipes(4, flavor, stock)) {
                stockIsGood = false;
                break;
            }
        }
        if (stockIsGood) {
            System.out.println("OK... :: Stock now can make more than 4 smoothies for each flavors");
        } else {
            System.out.println("WARN... :: Please buy more flavor .!!!!!!");
        }
    }

    private static void sellSmoothie(Stock stock) {
        int choose = -1;
        while (choose != 0) {
            subMenu();
            choose = waitInteger();
            switch (choose) {
                case 0:
                    //back to main menu
                    break;
                case 1:
                    System.out.print("Please input total smoothies to make: ");
                    int totalSmoothies = waitInteger();
                    while (totalSmoothies < 0) {
                        System.out.println("Please choose a correct value. ");
                        System.out.print("Please input total smoothies to make: ");
                        totalSmoothies = waitInteger();
                    }

                    Product.Flavor flavor = null;
                    while (flavor == null) {
                        System.out.print("Please flavor of smoothie to make: ");
                        try {
                            flavor = Product.Flavor.valueOf(waitAnInput());
                        } catch (Exception ignore) {
                            System.out.println("Incorrect value, please enter again");
                        }
                    }

                    boolean canMake = checkRecipes(totalSmoothies, flavor, stock);
                    if (canMake) {
                        System.out.printf("Yay!!!, we have enough for %s smoothies with flavor %s %n", totalSmoothies, flavor);
                        System.out.println(" ... ");
                        updateStock(totalSmoothies, flavor, stock);
                        System.out.println("Updated your stock");
                    } else {
                        System.out.printf("Opp!!!, we can't make for %s smoothies of %s %n", totalSmoothies, flavor);
                    }
                    break;

                default:
                    System.out.println("Opp!!!, Please choose again.!!");
                    break;
            }
        }
    }

    private static void updateStock(int totalSmoothies, Product.Flavor flavor, Stock stock) {
        int totalFruitNeed = totalSmoothies * 150;
        int totalIceNeed = totalSmoothies * 90;
        int totalMilkNeed = totalSmoothies * 60;
        int totalSugarNeed = totalSmoothies * 24;
        for (Product product : stock.allProducts()) {
            if (flavor.equals(product.getFlavor())) {
                product.decreaseProduct(totalFruitNeed);
            }
            if (product instanceof Ice) {
                product.decreaseProduct(totalIceNeed);
            }
            if (product instanceof CondensedMilk) {
                product.decreaseProduct(totalMilkNeed);
            }
            if (product instanceof Sugar) {
                product.decreaseProduct(totalSugarNeed);
            }
        }
        stock.view();
    }

    private static boolean checkRecipes(int totalSmoothies, Product.Flavor flavor, Stock stock) {
        //For every 100ml of smoothie, her recipe requires 50ml of blended fruit, 30ml of ice, 20ml of
        //condensed milk, and 8g of sugar
        // 1 smoothie => 300ml => 150ml fruit, 90ml ice, 60ml milk, 24g sugar.
        int totalFruitNeed = totalSmoothies * 150;
        int totalIceNeed = totalSmoothies * 90;
        int totalMilkNeed = totalSmoothies * 60;
        int totalSugarNeed = totalSmoothies * 24;
        System.out.println(String.format("The recipes for %s smoothies: %s: %s, Ice: %s, Milk: %s, Sugar: %s",
                totalSmoothies, flavor, totalFruitNeed, totalIceNeed, totalMilkNeed, totalSugarNeed));
        int counter = 0;
        for (Product product : stock.allProducts()) {
            if (flavor.equals(product.getFlavor())) {
                counter = getCounter(product, totalFruitNeed, counter);
            }
            if (product instanceof Ice) {
                counter = getCounter(product, totalIceNeed, counter);
            }
            if (product instanceof CondensedMilk) {
                counter = getCounter(product, totalMilkNeed, counter);
            }
            if (product instanceof Sugar) {
                counter = getCounter(product, totalSugarNeed, counter);
            }
        }
        return counter == 4;
    }

    private static int getCounter(Product product, int totalIceNeed, int counter) {
        if (product.canGet(totalIceNeed)) {
            counter++;
        } else {
            System.out.printf("%s in stock: %s %n", product.productName, product.inStock);
        }
        return counter;
    }

    private static int waitInteger() {
        try {
            return Integer.parseInt(waitAnInput());
        } catch (Exception ignore) {
        }
        return -1;
    }

    private static String waitAnInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void subMenu() {
        System.out.println("\n\n");
        System.out.println("==============================================");
        System.out.println("1. Choose total smoothies to sell");
        System.out.println("0. Back to main menu");
        System.out.print("Please choose: ");
    }

    public static void menu() {
        System.out.println("\n\n");
        System.out.println("==============================================");
        System.out.println("1. View stocks");
        System.out.println("2. Sell smoothie");
        System.out.println("3. Check stock for more 4 smoothies");
        System.out.println("0. Exit application");
        System.out.print("Please choose: ");
    }


}
