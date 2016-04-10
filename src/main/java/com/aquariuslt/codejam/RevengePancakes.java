package com.aquariuslt.codejam;

import com.aquariuslt.codejam.utils.Reader;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/** Created by Aquariuslt on 4/9/16.*/
public class RevengePancakes {
    private static final int MAX_STRING_LENGTH = 101;

    private int caseCount;
    private int[][] pancakeIntArray;
    private int[] result;

    private void input() {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("B/B-large.in");
        Reader.init(inputStream);
        try {
            caseCount = Reader.nextInt();
            result = new int[caseCount];
            pancakeIntArray = new int[caseCount][MAX_STRING_LENGTH];
            for (int i = 0; i < caseCount; i++) {
                String currentPancakeString = Reader.next();
                pancakeIntArray[i] = convertStringToInt(currentPancakeString);
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }

    }

    private int[] convertStringToInt(String currentPancakeString) {
        int currentPancakeStringLength = currentPancakeString.length();
        int[] currentPancakeIntArray = new int[currentPancakeStringLength];
        for (int i = 0, strLength = currentPancakeString.length(); i < strLength; i++) {
            currentPancakeIntArray[i] = currentPancakeString.charAt(i) == '+' ? 1 : 0;
        }
        return currentPancakeIntArray;
    }

    private void solve() {
        for (int i = 0, length = result.length; i < length; i++) {
            result[i] = solveSingleCase(pancakeIntArray[i]);
        }
    }

    private int solveSingleCase(int[] pancakeArray) {
        int revengeCount = 0;
        int revengeFlag = 0;
        for (int pancakeLength = pancakeArray.length, i = pancakeLength - 1; i >= 0; i--) {
            if ((pancakeArray[i] ^ revengeFlag) == 0) {
                revengeCount++;
                revengeFlag = 1 - revengeFlag;
            }
        }
        return revengeCount;
    }

    private void output() {
        for (int i = 0; i < caseCount; i++) {
            System.out.printf("Case #%d: %d\n", (i + 1), result[i]);
        }
    }


    @Test
    public void testRevengePancakes() {
        input();
        solve();
        output();
    }
}


/**
 * if '-' means '0', '+' means '1' we can convert case to: Case 1: input : -            0 target: +
 * 1
 *
 * Case 2: input : -+           01 target: ++           11
 *
 * Case 3: input : +-           10 target: ++           11
 *
 * Case 4: input : +++          111 target: +++          111
 *
 * Case 5: input : --+-         0010 target: ++++         1111
 */