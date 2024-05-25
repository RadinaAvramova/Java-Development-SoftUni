import java.util.Scanner;

public class TinySporebat {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int startingHealth = 5800;
        int tinySporebatCost = 30;
        long totalGlowcaps = 0;

        String line = scn.nextLine();
        while (!line.equals("Sporeggar")) {
            int glowcaps = line.charAt(line.length() - 1) - '0';
            char[] enemies = line.substring(0, line.length() - 1).toCharArray();
            for (char enemy : enemies) {
                if (enemy != 'L') {
                    startingHealth -= enemy;
                    if (startingHealth <= 0) {
                        System.out.println("Died. Glowcaps: " + totalGlowcaps);
                        return;
                    }
                } else {
                    startingHealth += 200;
                }
            }
            totalGlowcaps += glowcaps;
            line = scn.nextLine();
        }

        System.out.println("Health left: " + startingHealth);

        if (totalGlowcaps >= tinySporebatCost) {
            System.out.println("Bought the sporebat. Glowcaps left: " + (totalGlowcaps - tinySporebatCost));
        } else {
            System.out.printf("Safe in Sporeggar, but another %d Glowcaps needed.", (tinySporebatCost - totalGlowcaps));
        }
    }
}