package ua.dudka;

/**
 * @author Rostislav Dudka
 */
public class LoopMatrixMultiplier implements MatrixMultiplier {

    private int[][] multiply(int[][] leftMatrix, int[][] rightMatrix) {
        int length = leftMatrix.length;
        int[][] resultMatrix = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int sum = 0;
                for (int k = 0; k < length; k++) {
                    sum += leftMatrix[i][k] * rightMatrix[k][j];
                }
                resultMatrix[i][j] = sum;
            }
        }
        return resultMatrix;
    }

    @Override
    public Matrix multiply(Matrix left, Matrix right) {
        int[][] values = multiply(left.getValues(), right.getValues());
        return new Matrix(values);
    }

}