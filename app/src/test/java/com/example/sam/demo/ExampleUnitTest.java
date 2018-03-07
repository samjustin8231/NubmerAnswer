package com.example.sam.demo;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ExampleUnitTest {
    @Test
    public void test_main() throws Exception {
        // 号称世界上最难数独
        int[][] sudoku3_a = {
                {0, 2, 0},
                {8, 0, 0},
                {0, 6, 0}
        };

        int[][] sudoku3_b = {
                {0, 8, 0},
                {0, 0, 1},
                {0, 5, 0}
        };


        int[][] sudoku3_d = {
                {0, 1, 0},
                {3, 0, 7},
                {0, 0, 0}
        };


        int[][] sudoku3_4_a = {
                {0, 2, 0},
                {0, 11, 0},
                {4, 0, 7},
                {0, 0, 0}
        };
        int[][] sudoku3_4_b = {
                {0, 0, 6},
                {9, 0, 0},
                {0, 4, 11},
                {0, 0, 0}
        };

        int[][] sudoku4_4_a = {
                {0, 5, 0, 0},
                {0, 0, 2, 14},
                {8, 0, 0, 0},
                {0, 16, 11, 0}
        };
        int[][] sudoku4_5_a = {
                {0, 8, 0, 0},
                {5, 0, 0, 12},
                {0, 18, 0, 0},
                {0, 3, 0, 0},
                {0, 0, 0, 16},
        };

        NumberKnot s = new NumberKnot(sudoku4_5_a);
        s.backTrace(0, 0);
    }

}