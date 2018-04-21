package util;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class LogHelper {

    private static HashMap<Long, AtomicInteger> counters = new HashMap<>();

    public static void logMe() {
        logWithCount(null);
    }

    public static void logMe(String msg) {
        logWithCount(msg);
    }

    public static void logMe(int msg) {
        logWithCount(String.valueOf(msg));
    }

    private static void logWithCount(String message) {
        if (message != null) {
            message = " : " + message;
        } else {
            message = "";
        }
        log(getCount() + ". " + Thread.currentThread().getStackTrace()[3].getMethodName() + message);
    }

    public static void log(String message) {
        System.out.println(message);
    }

    private static int getCount() {
        Long threadHash = Thread.currentThread().getId();
        if (!counters.containsKey(threadHash)) {
            counters.put(threadHash, new AtomicInteger(0));
        }
        return counters.get(threadHash) != null ? counters.get(threadHash).incrementAndGet() : 0;
    }


}
