package com.example.sam.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    static int M = 2;
    static int N = 2;
    static int arr[][];
    static ArrayList<Integer> listNotFill;
    static ArrayList<Integer> listHasFill;
    static int CountPath = 0;
    static int curIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[][] arr3 = {{"1","2","3"},{"4","5","6"},{"7","8","9"}};


        //一维数组全排列
//        int[] array={1,2,3,4};
//        allSort(array, 0, array.length-1);


//        for (int i=0;i<resultArray.length;i++){
//            String temp = "";
//            for (int j=0;i<resultArray[i].length;j++){
//                temp = temp + resultArray[i][j] + ",";
//            }
//            System.out.println(temp);
//        }


    }

    static void allSort(int[] array,int begin,int end)
    {
        //打印数组的内容
        if(begin==end){
            System.out.println(Arrays.toString(array));
            return;
        }
        //把子数组的第一个元素依次和第二个、第三个元素交换位置
        for(int i=begin;i<=end;i++){
            swap(array,begin,i );
            allSort(array, begin+1, end);
        //交换回来
            swap(array,begin,i );
        }
    }

    static void swap(int[] array,int a,int b){
        int tem=array[a];
        array[a]=array[b];
        array[b]=tem;
    }

}
