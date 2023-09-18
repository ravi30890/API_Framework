package com.apiframework.utils;

import java.util.Random;

public class RandomGenerator {
    public static final String DATA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    public static final String DATA1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static final String DATA2 = "1234567890";
    public static final String DATA3 = "123456789";
    public static final String DATA4 = "!@#$%^&*()_+=-{}:\"|<>?/.,;\'[]\'";
    public static Random RANDOM = new Random();

    public static String randomAlphaNumeric(int len) {
        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            sb.append(DATA.charAt(RANDOM.nextInt(DATA.length())));
        }
        return sb.toString();

    }

    public static String randomAlphabets(int len) {
        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            sb.append(DATA1.charAt(RANDOM.nextInt(DATA1.length())));
        }

        return sb.toString();

    }

    public static String randomNumeric(int len) {
        StringBuilder sb = new StringBuilder(len);

        for (int i = 0; i < len; i++) {
            sb.append(DATA2.charAt(RANDOM.nextInt(DATA2.length())));
        }
        return sb.toString();

    }

    public static String randomDecimalNumber(int len, int decimalPlace){
        StringBuilder sb = new StringBuilder(len+decimalPlace+1);
        for (int i = 0; i < len; i++) {
            sb.append(DATA2.charAt(RANDOM.nextInt(DATA2.length())));
        }
        sb.append(".");
        for (int i = 0; i < decimalPlace; i++) {
            sb.append(DATA3.charAt(RANDOM.nextInt(DATA3.length())));
        }
        return sb.toString();
    }

    public static int getRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }
}
