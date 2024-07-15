package com.transactionsexample.spring_transactions;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    public static Long generateRandomId() {
        return ThreadLocalRandom.current().nextLong(1, 1000000); // Generates a number between 1 and 999999
    }
}
