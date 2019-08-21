package com.liuwq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/7/17 0017 上午 10:35
 * @version: V1.0
 */
public class Test2 {
    public static void main(String[] args) {
        Map<String, List<User>> m = new HashMap<>();
        List<User> list = new ArrayList<>();
        list.add(new User("I", "21"));
        list.add(new User("U", "22"));
        list.add(new User("I", "23"));
        list.add(new User("D", "20"));
        m.put("table1", list);
        List<User> list2 = new ArrayList<>();
        list2.add(new User("U", "24"));
        list2.add(new User("I", "25"));
        list2.add(new User("U", "26"));
        list2.add(new User("D", "27"));
        list2.add(new User("D", "28"));
        m.put("table2", list2);
        System.out.println(m);

        Map<String, Map<String, List<User>>> map = new HashMap<>();
        for(Map.Entry<String, List<User>> entry: m.entrySet()) {
            String tableName = entry.getKey();
            List<User> list3 = entry.getValue();
            for (User user : list3) {
                if (map.get(tableName) == null) {
                    List<User> list4 = new ArrayList<>();
                    list4.add(user);
                    Map<String, List<User>> m2 = new HashMap<>();
                    m2.put(user.getName(), list4);
                    map.put(tableName, m2);
                } else {
                    if (map.get(tableName).get(user.getName()) == null) {
                        List<User> list5 = new ArrayList<>();
                        list5.add(user);
                        map.get(tableName).put(user.getName(), list5);
                    } else {
                        map.get(tableName).get(user.getName()).add(user);
                    }
                }
            }
        }
        System.out.println(">>>");
        System.out.println(map);
    }
}

class User {
    private String name;
    private String age;

    public User (String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}