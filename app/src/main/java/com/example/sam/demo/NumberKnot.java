package com.example.sam.demo;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by sam on 2018/3/6.
 */

public class NumberKnot {
    private boolean logSwitch = true;
    private Point[][] matrix = null;
    private int M;
    private int N;
    private int countResult = 0;
    private int blankIndex = -1;
    private int index = 0;
    private ArrayList<Integer> numsOfBoardNotHasRepeatNums = new ArrayList<>();
    private ArrayList<Integer> path = new ArrayList<>();
    private HashSet<Point> setFilled = new HashSet<>();

    public NumberKnot(int[][] _matrix) {
        System.out.println("初始化");
        matrix = new Point[_matrix.length][_matrix[0].length];
        for (int i = 0; i < _matrix.length; i++) {
            for (int j = 0; j < _matrix[0].length; j++) {
                matrix[i][j] = new Point(i,j,_matrix[i][j]);
                //System.out.println(matrix[i][j]);
            }
        }
        M = _matrix.length;
        N = _matrix[0].length;

        for(int i=1;i<=M*N;i++){
            numsOfBoardNotHasRepeatNums.add(i);
        }
    }


    public void log(String str){
        if(logSwitch){
            System.out.println(str);
        }
    }

    public boolean checkPath(int i1,int j1, int k1){
//        if (check(i1, j1, k1, true)) {
//            return true;
//        }

        path.clear();
        boolean isOk = false;
        //一条线走完
        for (int k = 0; k < numsOfBoardNotHasRepeatNums.size(); k++) {
            int curValue = numsOfBoardNotHasRepeatNums.get(k);
            path.add(curValue);
            //2.找到curValue在array中的位置(x,y)
            int x = -10, y = -10;
            boolean ifFound = false;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (matrix[i][j].getValue() == curValue) {
                        x = i;
                        y = j;
                        ifFound = true;
                        break;
                    }
                }
                if (ifFound) {
                    break;
                }
            }
            //3.arrar[x,y]的四周是否有 t+1,如果没有则打印该路径；如果有则把t加入到路径l中，同样的方式继续判断t后面一个数字
            int nextValue = curValue+1;

            //下一个值的xy
            int nextX =-10;
            int nextY =-10;
            ifFound = false;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (matrix[i][j].getValue() == nextValue) {
                        nextX = i;
                        nextY = j;
                        ifFound = true;
                        break;
                    }
                }
                if (ifFound) {
                    break;
                }
            }

            //3.1上，右，下，左
            boolean isExitNextValue = false;
            if(Math.abs(nextX-x)<=1&&Math.abs(nextY-y)<=1){
                isExitNextValue = true;
            }
            if(k==8){
                int a = 0;
            }
            //下一个值存在
            if (isExitNextValue) {

            }else{
                if(k==numsOfBoardNotHasRepeatNums.size()-1){
                    isOk = true;
                }else{
                    break;
                }
            }
        }
        return isOk;
    }

    /**
     * NumberKnot算法
     *
     * @param i 行号
     * @param j 列号
     */
    public void backTrace(int i, int j) {
        if(matrix[0][0].getValue()==8&&matrix[0][1].getValue()==7){
            int a = 0;
        }
        blankIndex++;
        index++;
        //成功条件
        if (i == M-1 && j == N) {
            if(index==587){
                int a=0;
            }
            //所有格子均填完
            //判断是否一条线走完
            if(checkPath(i, j-1, matrix[i][j-1].getValue())){
                countResult++;
                System.out.println("获取正确解,countResult:" + countResult);
                //打印
                printArray();
            } else{

            }
            return;
        }

        //已经到了列末尾了，还没到行尾，就换行
        if (j == N) {
            i++;
            j = 0;
        }

        //如果i行j列是空格，那么才进入给空格填值的逻辑
        if (matrix[i][j].getValue() == 0) {
            for (int k = 1; k <= M*N; k++) {

                if (check(i, j, k, false)) {

                    //将该值赋给该空格，然后进入下一个空格
                    matrix[i][j] = new Point(i,j,k);
                    backTrace(i, j + 1);

                    String blanks = getBlanks();
                    log(blanks+"回溯到,i:" + i+",j:"+j+",index:"+index);
                    printArrayHasFilled(i,j);
                    //初始化该空格
                    matrix[i][j] = new Point(i,j,0);
                    blankIndex--;
                }
            }
        } else {
            //如果该位置已经有值了，就进入下一个空格进行计算
            backTrace(i, j + 1);
        }
    }

    @NonNull
    private String getBlanks() {
        String blanks = "";
        for(int a=0;a<blankIndex;a++){
            blanks += " ";
        }
        return blanks;
    }

    private boolean checkPreSeqNumsIfCanLink(int row, int col, int number){
        setFilled.clear();
        boolean isCanLinkPreSeqNums = true;
        //获取填过的数字
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < N; j++) {
                setFilled.add(new Point(i,j,matrix[i][j].getValue()));
            }
        }
        for(int i=0;i<col;i++){
            setFilled.add(new Point(row,i,matrix[row][i].getValue()));
        }
        setFilled.add(new Point(row,col,number));
        //检查顺序的两个数字是否可以link
        int ii = 1;
        for (Point t:setFilled){
            ii++;
            for (Point tNext:setFilled){
                ii--;
                if(t.getValue()+1==tNext.getValue()){
                    if(Math.abs(t.getRow()-tNext.getRow())>1||Math.abs(t.getCol()-tNext.getCol())>1){
                        isCanLinkPreSeqNums = false;
                        break;
                    }
                }
            }
        }
        if(setFilled.size()>0){
            if(ii==setFilled.size()){
                isCanLinkPreSeqNums = true;
            }
        }else{
            isCanLinkPreSeqNums = true;
        }
        return  isCanLinkPreSeqNums;
    }

    /**
     * 判断给某行某列赋值是否符合规则
     *  判断的标准是：该数字四周都不满足则返回false，否则返回true(空的认为满足)
     * @param row    被赋值的行号
     * @param col   被赋值的列号
     * @param number 赋的值
     * @return
     */
    private boolean check(int row, int col, int number, boolean flag) {
        if(index==10){
            int a=0;
        }
        //判断该数组是否有重复数字 TODO 用集合优化
        if(!flag){
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (matrix[i][j].getValue() == number) {
                        return false;
                    }
                }
            }
        }
        int a=1;
        //检查前面的顺序数字是否可以连
        boolean isCanLinkPreSeqNums = checkPreSeqNumsIfCanLink(row,col,number);
//        boolean isCanLinkPreSeqNums = true;
        if(!isCanLinkPreSeqNums){
            return false;
        }
        //检查自己的四周是否满足
        boolean isOkCurNum = checkRound(row, col, number);
        if(!isOkCurNum){
            return false;
        }

        //检查前面所有的数，只要有一个数的四周都不满足，则返回false
        boolean isOkPreNums = true;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < N; j++) {
                isOkPreNums = checkRoundForPres(i, j, matrix[i][j].getValue());
                if(!isOkPreNums){
                    break;
                }
            }
            if(!isOkPreNums){
                break;
            }
        }

        String blanks = getBlanks();
        String str = blanks+"check cur,"+isOkCurNum+" row:"+row+",col:"+col+",number:"+number+",    check pre nums,"+isOkPreNums+",index:"+index;
        log(str);
        return isOkPreNums;
    }

    private boolean checkNextValueRound(int row,int col, int next){
        boolean isLinkUp = false;
        boolean isLinkUpLeft = false;
        boolean isLinkUpRight = false;
        boolean isLinkMidLeft = false;
        boolean isLinkMidRight = false;
        boolean isLinkDown = false;
        boolean isLinkDownLeft = false;
        boolean isLinkDownRight = false;

        //上
        if(row>0){
            if(matrix[row-1][col].getValue() == next || matrix[row-1][col].getValue() == 0){
                isLinkUp = true;
            }
        }
        //左上
        if(row>0 && col>0){
            if(matrix[row-1][col-1].getValue() == next || matrix[row-1][col-1].getValue() == 0){
                isLinkUpLeft = true;
            }
        }
        //右上
        if(row>0 && col+1<N){
            if(matrix[row-1][col+1].getValue() == next || matrix[row-1][col+1].getValue() == 0){
                isLinkUpRight = true;
            }
        }
        //下
        if(row+1<M){
            if(matrix[row+1][col].getValue() == next || matrix[row+1][col].getValue() == 0){
                isLinkDown = true;
            }
        }
        //左下
        if(row+1<M && col>0){
            if(matrix[row+1][col-1].getValue() == next || matrix[row+1][col-1].getValue() == 0){
                isLinkDownLeft = true;
            }
        }
        //右下
        if(row+1<M && col+1<N){
            if(matrix[row+1][col+1].getValue() == next || matrix[row+1][col+1].getValue() == 0){
                isLinkDownRight = true;
            }
        }
        //左
        if(col>0){
            if(matrix[row][col-1].getValue() == next || matrix[row][col-1].getValue() == 0){
                isLinkMidLeft = true;
            }
        }
        //右
        if(col+1<N){
            if(matrix[row][col+1].getValue() == next || matrix[row][col+1].getValue() == 0){
                isLinkMidRight = true;
            }
        }

        boolean isLinkOfRound = false;
        isLinkOfRound = isLinkUp || isLinkUpLeft||isLinkUpRight||isLinkMidLeft||isLinkMidRight||isLinkDown||isLinkDownLeft||isLinkDownRight;

        return isLinkOfRound;
    }

    private boolean checkRound(int row, int col, int number){
        //判断上下左右是否相连
        int next = number+1;

        boolean isLinkOfRound = false;
        boolean isLinkUp = false;
        boolean isLinkUpLeft = false;
        boolean isLinkUpRight = false;
        boolean isLinkMidLeft = false;
        boolean isLinkMidRight = false;
        boolean isLinkDown = false;
        boolean isLinkDownLeft = false;
        boolean isLinkDownRight = false;

        boolean isMinOfRound = false;
        boolean isLetterUp = false;
        boolean isLetterUpLeft = false;
        boolean isLetterUpRight = false;
        boolean isLetterMidLeft = false;
        boolean isLetterMidRight = false;
        boolean isLetterDown = false;
        boolean isLetterDownLeft = false;
        boolean isLetterDownRight = false;
        //上
        if(row>0){
            if(matrix[row-1][col].getValue() == next || matrix[row-1][col].getValue() == 0){
                isLinkUp = true;
            }
            if(matrix[row-1][col].getValue() > number){
                isLetterUp = true;
            }
        }
        //左上
        if(row>0 && col>0){
            if(matrix[row-1][col-1].getValue() == next || matrix[row-1][col-1].getValue() == 0){
                isLinkUpLeft = true;
            }
            if(matrix[row-1][col-1].getValue() > number){
                isLetterUpLeft = true;
            }
        }
        //右上
        if(row>0 && col+1<N){
            if(matrix[row-1][col+1].getValue() == next || matrix[row-1][col+1].getValue() == 0){
                isLinkUpRight = true;
            }
            if(matrix[row-1][col+1].getValue() > number){
                isLetterUpRight = true;
            }
        }
        //下
        if(row+1<M){
            if(matrix[row+1][col].getValue() == next || matrix[row+1][col].getValue() == 0){
                isLinkDown = true;
            }
            if(matrix[row+1][col].getValue() > number){
                isLetterDown = true;
            }
        }
        //左下
        if(row+1<M && col>0){
            if(matrix[row+1][col-1].getValue() == next || matrix[row+1][col-1].getValue() == 0){
                isLinkDownLeft = true;
            }
            if(matrix[row+1][col-1].getValue() > number){
                isLetterDownLeft = true;
            }
        }
        //右下
        if(row+1<M && col+1<N){
            if(matrix[row+1][col+1].getValue() == next || matrix[row+1][col+1].getValue() == 0){
                isLinkDownRight = true;
            }
            if(matrix[row+1][col+1].getValue() > number){
                isLetterDownRight = true;
            }
        }
        //左
        if(col>0){
            if(matrix[row][col-1].getValue() == next || matrix[row][col-1].getValue() == 0){
                isLinkMidLeft = true;
            }
            if(matrix[row][col-1].getValue() > number){
                isLetterMidLeft = true;
            }
        }
        //右
        if(col+1<N){
            if(matrix[row][col+1].getValue() == next || matrix[row][col+1].getValue() == 0){
                isLinkMidRight = true;
            }
            if(matrix[row][col+1].getValue() > number){
                isLetterMidRight = true;
            }
        }

        boolean isMinOk = false;
        isMinOfRound = isLetterUp && isLetterUpLeft&&isLetterUpRight&&isLetterMidLeft&&isLetterMidRight&&isLetterDown&&isLetterDownLeft&&isLetterDownRight;
        isLinkOfRound = isLinkUp || isLinkUpLeft||isLinkUpRight||isLinkMidLeft||isLinkMidRight||isLinkDown||isLinkDownLeft||isLinkDownRight;
        if(isMinOfRound){
            if(number==1){
                isMinOk = false;
            }else{
                isMinOk= true;
            }
        }
        //System.out.print(""+isLetterUp + isLetterUpLeft+isLetterUpRight+isLetterMidLeft+isLetterMidRight+isLetterDown+isLetterDownLeft+isLetterDownRight);
//        isMinOk = true;
        return isLinkOfRound && !isMinOk;
    }

    private boolean checkRoundForPres(int row, int col, int number){
        //判断上下左右是否相连

        boolean isLinkOfRound = false;
        boolean isLinkUp = false;
        boolean isLinkUpLeft = false;
        boolean isLinkUpRight = false;
        boolean isLinkMidLeft = false;
        boolean isLinkMidRight = false;
        boolean isLinkDown = false;
        boolean isLinkDownLeft = false;
        boolean isLinkDownRight = false;

        boolean isMinOfRound = false;
        boolean isLetterUp = false;
        boolean isLetterUpLeft = false;
        boolean isLetterUpRight = false;
        boolean isLetterMidLeft = false;
        boolean isLetterMidRight = false;
        boolean isLetterDown = false;
        boolean isLetterDownLeft = false;
        boolean isLetterDownRight = false;
        //上
        if(row>0){
            if(Math.abs(matrix[row-1][col].getValue() - number) == 1 || matrix[row-1][col].getValue() == 0){
                isLinkUp = true;
            }
            if(matrix[row-1][col].getValue() > number){
                isLetterUp = true;
            }
        }
        //左上
        if(row>0 && col>0){
            if(Math.abs(matrix[row-1][col-1].getValue()-number)==1 || matrix[row-1][col-1].getValue() == 0){
                isLinkUpLeft = true;
            }
            if(matrix[row-1][col-1].getValue() > number){
                isLetterUpLeft = true;
            }
        }
        //右上
        if(row>0 && col+1<N){
            if(Math.abs(matrix[row-1][col+1].getValue()-number)==1 || matrix[row-1][col+1].getValue() == 0){
                isLinkUpRight = true;
            }
            if(matrix[row-1][col+1].getValue() > number){
                isLetterUpRight = true;
            }
        }
        //下
        if(row+1<M){
            if(Math.abs(matrix[row+1][col].getValue()-number)==1 || matrix[row+1][col].getValue() == 0){
                isLinkDown = true;
            }
            if(matrix[row+1][col].getValue() > number){
                isLetterDown = true;
            }
        }
        //左下
        if(row+1<M && col>0){
            if(Math.abs(matrix[row+1][col-1].getValue()-number)==1|| matrix[row+1][col-1].getValue() == 0){
                isLinkDownLeft = true;
            }
            if(matrix[row+1][col-1].getValue() > number){
                isLetterDownLeft = true;
            }
        }
        //右下
        if(row+1<M && col+1<N){
            if(Math.abs(matrix[row+1][col+1].getValue()-number)==1|| matrix[row+1][col+1].getValue() == 0){
                isLinkDownRight = true;
            }
            if(matrix[row+1][col+1].getValue() > number){
                isLetterDownRight = true;
            }
        }
        //左
        if(col>0){
            if(Math.abs(matrix[row][col-1].getValue()-number)==1|| matrix[row][col-1].getValue() == 0){
                isLinkMidLeft = true;
            }
            if(matrix[row][col-1].getValue() > number){
                isLetterMidLeft = true;
            }
        }
        //右
        if(col+1<N){
            if(Math.abs(matrix[row][col+1].getValue()-number)==1|| matrix[row][col+1].getValue() == 0){
                isLinkMidRight = true;
            }
            if(matrix[row][col+1].getValue() > number){
                isLetterMidRight = true;
            }
        }

        boolean isMinOk = false;
        isMinOfRound = isLetterUp && isLetterUpLeft&&isLetterUpRight&&isLetterMidLeft&&isLetterMidRight&&isLetterDown&&isLetterDownLeft&&isLetterDownRight;
        isLinkOfRound = isLinkUp || isLinkUpLeft||isLinkUpRight||isLinkMidLeft||isLinkMidRight||isLinkDown||isLinkDownLeft||isLinkDownRight;
        if(isMinOfRound){
            if(number==1){
                isMinOk = false;
            }else{
                isMinOk= true;
            }
        }
        //System.out.print(""+isLetterUp + isLetterUpLeft+isLetterUpRight+isLetterMidLeft+isLetterMidRight+isLetterDown+isLetterDownLeft+isLetterDownRight);
//        isMinOk = true;
        return isLinkOfRound && !isMinOk;
    }

    /**
     * 打印矩阵
     */
    public void printArrayHasFilled(int x,int y) {
        String blanks = getBlanks();
        if(x==0){
            String temp = "";
            for (int j = 0; j <= y; j++) {
                temp = temp +String.format("%2d", matrix[x][j].getValue()) + " ";
            }
            log(blanks+temp);
        }else if(x>0){
            String temp = "";
            for (int i = 0; i <= x-1; i++) {
                temp = "";
                for (int j = 0; j < N; j++) {
                    temp = temp +String.format("%2d", matrix[i][j].getValue()) + " ";
                }
                log(blanks+ temp);
            }

            temp = "";
            for (int j = 0; j <= y; j++) {
                temp = temp +String.format("%2d", matrix[x][j].getValue()) + " ";
            }
            log(blanks + temp);
        }
    }

    /**
     * 打印矩阵
     */
    public void printArray() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                String temp = String.format("%2d", matrix[i][j].getValue());
                System.out.print(temp + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
