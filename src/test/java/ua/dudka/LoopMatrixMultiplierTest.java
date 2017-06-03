package ua.dudka;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.dudka.abstract_test.AbstractMatrixMultiplierTest;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * @author Rostislav Dudka
 */
public class LoopMatrixMultiplierTest extends AbstractMatrixMultiplierTest {

    private static MatrixMultiplier loopMatrixMultiplier;

    @BeforeClass
    public static void setUp() throws Exception {
        loopMatrixMultiplier = new LoopMatrixMultiplier();
    }

    @Test
    public void multiplyShouldReturnResultMatrix() throws Exception {
        int[][] resultMatrix = loopMatrixMultiplier.multiply(LEFT_MATRIX, RIGHT_MATRIX);

        assertEquals(MATRIX_SIZE, resultMatrix.length);
        assertEquals(MATRIX_SIZE, resultMatrix[0].length);

        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                int sum = 0;
                for (int k = 0; k < MATRIX_SIZE; k++) {
                    sum += LEFT_MATRIX[i][k] * RIGHT_MATRIX[k][j];
                }
                assertEquals(sum, resultMatrix[i][j]);
            }
        }
    }
}