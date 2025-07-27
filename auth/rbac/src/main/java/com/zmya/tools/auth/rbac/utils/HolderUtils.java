package com.zmya.tools.auth.rbac.utils;

import java.util.HashMap;
import java.util.Map;

public class HolderUtils {

    private static final Map<String, String> users = new HashMap<>();

    public static void saveUser(String username, String password) {
        users.put(username, password);
    }

    public static Map<String, String> obtainUser(String username) {
        if (users.containsKey(username)) {
            HashMap<String, String> result = new HashMap<>();
            result.put(username, users.get(username));
            return result;
        } else {
            return null;
        }
    }

    private HolderUtils() {
    }

}
