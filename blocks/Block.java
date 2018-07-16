package game.blocks;

import java.lang.StringBuffer;

/**
 * 条块基类
 */
public abstract class Block {

    /* 条块类型 */
    protected String type;
    /* 条块状态 */
    protected int[][][] matrixs;
    /* 旋转状态 */
    protected int state;
    /* 条块结构 */
    protected int[][] square;

    protected Block(int[][] matrix) {
        this.square = matrix;
    }

    /**
     * 返回结构矩阵
     */
    public int[][] getMatrix() {
        return square;
    }

    /**
     * 条块旋转至初始形态
     */
    public void recover() {
        square = matrixs[0];
    }

    /**
     * 条块旋转
     */
    public void rotate(boolean direction) {
        if (direction) {
            this.state = (matrixs.length + state + 1) % matrixs.length;
            this.square = matrixs[this.state];
        } else {
            this.state = (matrixs.length + state - 1) % matrixs.length;
            this.square = matrixs[this.state];
        }
    }

    /**
     * 获取当前条块类型
     */
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < square.length; i++) {
            buffer.append("| ");
            for (int j = 0; j < square[i].length; j++) {
                buffer.append(square[i][j] + " ");
            }
            buffer.append("|\n");
        }
        return buffer.toString();
    }

    /**
     * 矩阵复制
     */
    protected int[][] copy() {
        int[][] copy = new int[square.length][square[0].length];
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square[i].length; j++) {
                copy[i][j] = square[i][j];
            }
        }
        return copy;
    }
}
