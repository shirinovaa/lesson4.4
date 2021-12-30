
package com.company;

import java.util.Random;

public class Main {

    public static int[] heroesHealth = {250, 250, 250, 500};
    public static int[] heroesDamage = {20, 15, 25, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic"};
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int roundNumber = 0;

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss chose: " + bossDefenceType);
    }

    public static int returnHP() {
        Random random = new Random();
        int hp = random.nextInt(200);
        return hp;
    }

    public static void medicalReturnHp() {
        int help = 50;
        int indexMedical = 0;
        for (String name : heroesAttackType) {
            if (name.equals("Medic")) {
                if (heroesHealth[indexMedical] > 0){
                    for (int i = 0; i < heroesHealth.length; i++) {
                        if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && indexMedical != i) {
                            heroesHealth[i] += help;
                            System.out.println("Medic " + heroesAttackType[i] + "  "  + heroesHealth[i]);
                            break;
                        }
                    }
                }
                }
            indexMedical++;
        }
    }


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        medicalReturnHp();
        printStatistics();
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] < bossDamage) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(8) + 2; // 2,3,4,5,6,7,8,9
                    if (bossHealth < heroesDamage[i] * coeff) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth < heroesDamage[i]) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println(roundNumber + " ROUND ----------------------");
        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " [" + heroesDamage[i] + "]");
        }
        System.out.println("--------------------");
    }
}