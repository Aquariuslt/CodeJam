package com.aquariuslt.codejam;

import com.aquariuslt.codejam.utils.Reader;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** Created by Aquariuslt on 4-16-2016.*/
public class TheLastWord {
  private int caseCount;
  private List<String> inputStringList;
  private List<String> resultStringList;

  private void input(){
    InputStream inputStream = ClassLoader.getSystemResourceAsStream("R1A/A/A-large.in");
    Reader.init(inputStream);
    try {
      caseCount = Reader.nextInt();
      inputStringList = new ArrayList<>();
      for(int i=0;i<caseCount;i++){
        String currentWord = Reader.next();
        inputStringList.add(currentWord);
      }
    } catch (IOException e) {
      //e.printStackTrace();
    }

  }

  private void solveNext(List<String> allResultList,String currentWord,int currentIndex,String currentCombineString,char nextChar){
    if(currentIndex == currentWord.length()-1){
      allResultList.add(currentCombineString+nextChar);
      allResultList.add(nextChar+currentCombineString);
    }
    else{
      solveNext(allResultList,currentWord,currentIndex+1,currentCombineString+nextChar,currentWord.charAt(currentIndex+1));
      solveNext(allResultList,currentWord,currentIndex+1,nextChar+currentCombineString,currentWord.charAt(currentIndex+1));
    }
  }

  private String solveSingleCase(String currentWord){
    List<String> allResultList = new ArrayList<>();
    solveNext(allResultList,currentWord,0,"",currentWord.charAt(0));
    Collections.sort(allResultList);

    return allResultList.get(allResultList.size()-1);
  }

  private void solve(){
    resultStringList = new ArrayList<>();
    for(String currentWord:inputStringList){
      resultStringList.add(solveSingleCase(currentWord));
    }
  }

  private void output(){
    for (int i = 0; i < caseCount; i++) {
      System.out.printf("Case #%d: %s\n", (i + 1), resultStringList.get(i));
    }
  }





  @Test
  public void testTheLastWord(){
    input();
    solve();
    output();
  }
}
