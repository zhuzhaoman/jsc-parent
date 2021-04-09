package com.zzm.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhuzhaoman
 * @date: 2021-03-03
 * @description:
 **/
public class test {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");

        List<String> list2 = new ArrayList<>();
        list2.add("2");
        list2.add("1");
        list2.add("1");

//        if (list1.size() != list2.size()) {
//            System.out.println("1.不相同");
//            return;
//        }
//
//        for (String s : list1) {
//            if (!list2.contains(s)){
//                System.out.println("2.不相同");
//                return;
//            }
//        }
        System.out.println(list1.retainAll(list2));
    }
}
