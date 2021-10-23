package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        long start = new Date().getTime();
        for(String s: strings){
            ids.add(shortener.getId(s));
        }
        long end = new Date().getTime();
        return end-start;
    }
    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        long start = new Date().getTime();
        for(Long id: ids){
            strings.add(shortener.getString(id));
        }
        long end = new Date().getTime();
        return end-start;
    }
    @Test
    public void testHashMapStorage(){
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        Set<Long> biId = new HashSet<>();
        Set<Long> hashId = new HashSet<>();
        for(int i = 0; i<10000 ; i++){
            origStrings.add(Helper.generateRandomString());
        }

        long biIdTime  = getTimeToGetIds(shortener2,origStrings,biId);
        long hashIdTime = getTimeToGetIds(shortener1,origStrings,hashId);
        Assert.assertTrue(biIdTime<hashIdTime);

        long biStrTime = getTimeToGetStrings(shortener2,biId,new HashSet<String >());
        long hashStrTime = getTimeToGetStrings(shortener1,hashId,new HashSet<String >());

        Assert.assertEquals(biStrTime,hashStrTime,30);


    }
}
