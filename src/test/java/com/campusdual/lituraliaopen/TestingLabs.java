package com.campusdual.lituraliaopen;

import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class TestingLabs {

    @Test
    public void test() {

        try {
            Runnable runnable = new Runnable() {
                public void run() {
                    Scanner scanner = new Scanner(System.in);
                    scanner.nextLine();
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }


    }
}
