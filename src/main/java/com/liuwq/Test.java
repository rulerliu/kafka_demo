package com.liuwq;

/**
 * @description:
 * @author: liuwq
 * @date: 2019/7/16 0016 下午 3:38
 * @version: V1.0
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(new String("0x01"));
        System.out.println(new String("0x02"));
        byte[] bytes = "abcd".getBytes();
        System.out.println(bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            if (b == 'a') {
                System.out.println(bytes[i]);
            }
        }
    }
}

