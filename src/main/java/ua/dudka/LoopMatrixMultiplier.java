package ua.dudka;

import java.util.logging.Logger;

/**
 * @author Rostislav Dudka
 */
public class LoopMatrixMultiplier implements MatrixMultiplier {

    @Override
    public int[][] multiply(int[][] left, int[][] right) {
        int length = left.length;
        int[][] resultMatrix = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int sum = 0;
                for (int k = 0; k < length; k++) {
                    sum += left[i][k] * right[k][j];
                }
                resultMatrix[i][j] = sum;
            }
        }
        return resultMatrix;
    }
}
