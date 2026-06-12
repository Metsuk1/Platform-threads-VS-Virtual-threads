package org.example;

import java.util.HashMap;

public class Leetcode {

    public static String intToRoman(int num) {
       int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
       String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

       StringBuilder result = new StringBuilder();

       for (int i = 0; i < values.length; i++) {
           while(num >= values[i]) {
               result.append(symbols[i]);
               num -= values[i];
           }
       }

       return result.toString();
    }

    public boolean exist(char[][] board, String word) {
        int[] boardCount = new int[128];
        int[] wordCount = new int[128];

        for (char[] row : board) {
            for (char c : row) {
                boardCount[c]++;
            }
        }

        for (char c : word.toCharArray()) {
            wordCount[c]++;
            if (wordCount[c] > boardCount[c]) {
                return false;
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int row, int col, int index) {
        if (index == word.length()) return true;

        if (row < 0 || row >= board.length ||
                col < 0 || col >= board[0].length ||
                board[row][col] != word.charAt(index)) {
            return false;
        }

        char temp = board[row][col];
        board[row][col] = '#';

        boolean found = dfs(board, word, row - 1, col, index + 1) ||
                dfs(board, word, row + 1, col, index + 1) ||
                dfs(board, word, row, col - 1, index + 1) ||
                dfs(board, word, row, col + 1, index + 1);

        board[row][col] = temp;
        return found;
    }
}
