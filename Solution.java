package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
    testStrategy(new HashMapStorageStrategy(),10000);
        testStrategy(new OurHashMapStorageStrategy(),10000);
        testStrategy(new OurHashBiMapStorageStrategy(),10000);
        testStrategy(new HashBiMapStorageStrategy(),10000);
        testStrategy(new DualHashBidiMapStorageStrategy(),10000);
       // testStrategy(new FileStorageStrategy(),10000);
    }
    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> ans = new HashSet<>();
        for(String s : strings){
            ans.add(shortener.getId(s));
        }
        return ans;
    }
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> ans = new HashSet<>();
        for(Long l : keys){
            ans.add(shortener.getString(l));
        }
        return ans;
    }
    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> strings = new HashSet<>();
        for (int i = 0; i< elementsNumber;i++){
            strings.add(Helper.generateRandomString());
        }
        Shortener testShortner = new Shortener(strategy);
        long startTimeId = new Date().getTime();
        Set<Long> idS = getIds(testShortner,strings);
        long endTimeId = new Date().getTime();
        Helper.printMessage("ID time: " + (endTimeId-startTimeId));
        long startTimeString = new Date().getTime();
        Set<String> strSet = getStrings(testShortner,idS);
        long endTimeString = new Date().getTime();
        Helper.printMessage("Strings time: " + (endTimeString-startTimeString));
        if ((strings.size() == strSet.size())) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }

    }
}
