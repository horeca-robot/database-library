package edu.fontys.horecarobot.databaselibrary.utils;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    /**
     * Ignore warnings, this method is needed as Arrays.asList() returns an unmodifiable list.
     */
    public static <T> List<T> asList(T... t) {
        List<T> list = new ArrayList<>();
        for (T obj : t) {
            list.add(obj);
        }
        return list;
    }
}
