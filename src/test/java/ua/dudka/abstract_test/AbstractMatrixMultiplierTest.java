package ua.dudka.abstract_test;

import org.junit.BeforeClass;
import ua.dudka.LoopMatrixMultiplier;

import java.util.Random;

/**
 * @author Rostislav Dudka
 */
public abstract class AbstractMatrixMultiplierTest {
    protected static final int MATRIX_SIZE = 100;
    protected static int[][] LEFT_MATRIX = new int[MATRIX_SIZE][MATRIX_SIZE];
    protected static int[][] RIGHT_MATRIX = new int[MATRIX_SIZE][MATRIX_SIZE];

    @BeforeClass
    public static void setUpMatrices() throws Exception {
        Random random = new Random();
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                LEFT_MATRIX[i][j] = random.nextInt(10);
                RIGHT_MATRIX[i][j] = random.nextInt(10);
            }
        }
    }

}
