package com.nhnacademy.family_relationship_certification;

import java.util.Random;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();

        String temp = Integer.toString( rand.nextInt(8) + 1);

        for (int i = 0; i < 15; i++) {
            temp+= Integer.toString(rand.nextInt(9));
        }

        System.out.println(temp);
    }
}
