package ua.dudka.abstract_test;

import org.junit.BeforeClass;
import ua.dudka.Matrix;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * @author Rostislav Dudka
 */
public abstract class AbstractMatrixMultiplierTest {
    protected static final int SMALL_MATRIX_SIZE = 4;

    protected static Matrix LEFT_SMALL_MATRIX;
    protected static Matrix RIGHT_SMALL_MATRIX;

    protected static Matrix LEFT_BIG_MATRIX;
    protected static Matrix RIGHT_BIG_MATRIX;

    protected static final int BIG_MATRIX_SIZE = 1250;

    @BeforeClass
    public static void setUpMatrices() throws Exception {
        Random random = new Random();
        int[][] leftMatrix = new int[SMALL_MATRIX_SIZE][SMALL_MATRIX_SIZE];
        int[][] rightMatrix = new int[SMALL_MATRIX_SIZE][SMALL_MATRIX_SIZE];

        for (int i = 0; i < SMALL_MATRIX_SIZE; i++) {
            for (int j = 0; j < SMALL_MATRIX_SIZE; j++) {
                leftMatrix[i][j] = random.nextInt(10);
                rightMatrix[i][j] = random.nextInt(10);
            }
        }
        LEFT_SMALL_MATRIX = new Matrix(leftMatrix);
        RIGHT_SMALL_MATRIX = new Matrix(rightMatrix);

        leftMatrix = new int[BIG_MATRIX_SIZE][BIG_MATRIX_SIZE];
        rightMatrix = new int[BIG_MATRIX_SIZE][BIG_MATRIX_SIZE];
        for (int i = 0; i < BIG_MATRIX_SIZE; i++) {
            for (int j = 0; j < BIG_MATRIX_SIZE; j++) {
                leftMatrix[i][j] = random.nextInt(10);
                rightMatrix[i][j] = random.nextInt(10);
            }
        }
        LEFT_BIG_MATRIX = new Matrix(leftMatrix);
        RIGHT_BIG_MATRIX = new Matrix(rightMatrix);

    }

    protected void checkResultMatrixSizes(int expectedSize, Matrix resultMatrix) {
        assertEquals(expectedSize, resultMatrix.getSize());
        assertEquals(expectedSize, resultMatrix.getValues().length);
    }

    protected void checkResultMatrixContent(Matrix resultMatrix) {
        int[][] leftMatrixValues = LEFT_SMALL_MATRIX.getValues();
        int[][] rightMatrixValues = RIGHT_SMALL_MATRIX.getValues();
        int[][] resultMatrixValues = resultMatrix.getValues();
        for (int i = 0; i < SMALL_MATRIX_SIZE; i++) {
            for (int j = 0; j < SMALL_MATRIX_SIZE; j++) {
                int sum = 0;
                for (int k = 0; k < SMALL_MATRIX_SIZE; k++) {
                    sum += leftMatrixValues[i][k] * rightMatrixValues[k][j];
                }
                assertEquals(sum, resultMatrixValues[i][j]);
            }
        }
    }
}