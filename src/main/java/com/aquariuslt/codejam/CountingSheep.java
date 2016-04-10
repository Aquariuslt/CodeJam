package com.aquariuslt.codejam;

import com.aquariuslt.codejam.utils.Reader;

import org.junit.Test;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

/** Created by Aquariuslt on 4/9/16.*/
public class CountingSheep {
    private static int numberOfCases;
    private static int startSheepNumber[];
    private static int result[];


    private static void input(){
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("A/A-large.in");
        Reader.init(inputStream);
        try{
            numberOfCases = Reader.nextInt();
            startSheepNumber = new int[numberOfCases];
            result = new int[numberOfCases];
            for(int i=0;i<numberOfCases;i++){
                startSheepNumber[i] = Reader.nextInt();
            }
        }
        catch (Exception e){
            //Do nothing
        }

    }

    private static void solve(){
        for(int i=0;i<numberOfCases;i++){
            result[i] = solveSingleNumber(startSheepNumber[i]);
        }
    }

    private static int solveSingleNumber(int singleNumber){
        Set<Integer> digitalSet = new HashSet<>();
        if(singleNumber==0){
            return 0;
        }
        else{
            int currentNumber = singleNumber;
            while(digitalSet.size()<10){
                digitalSet.addAll(convertIntToDigitalSet(currentNumber));
                currentNumber += singleNumber;
            }
            return currentNumber;
        }
    }

    private static Set<Integer> convertIntToDigitalSet(int number){
        int currentNumber = number;
        Set<Integer> digitalSet = new HashSet<>();
        while(currentNumber/10>0){
            digitalSet.add(currentNumber % 10);
            currentNumber = currentNumber/10;
        }
        return digitalSet;
    }

    private static void output(){
        for(int i=0;i<numberOfCases;i++){
            if(result[i] == 0){
                System.out.printf("Case #%d: INSOMNIA\n",(i+1));
            }
            else{
                System.out.printf("Case #%d: %d\n",(i+1),result[i]);
            }
        }
    }


    @Test
    public void testCountingSheep() {
        input();
        solve();
        output();
    }
}
