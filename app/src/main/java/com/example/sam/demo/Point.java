package com.example.sam.demo;

/**
 * Created by sam on 2018/3/6.
 */

public class Point {
    private int col;
    private int row;
    private int value;

    // 构造点
    public Point(int row, int col, int value) {
        super();
        this.col = col;
        this.row = row;
        this.value = value;
    }


    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Point{" +
                "col=" + col +
                ", row=" + row +
                ", value=" + value +
                '}';
    }
}
