package ua.dudka;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.dudka.abstract_test.AbstractMatrixMultiplierTest;

/**
 * @author Rostislav Dudka
 */
public class StreamMatrixMultiplierTest extends AbstractMatrixMultiplierTest {

    private static MatrixMultiplier streamMatrixMultiplier;

    @BeforeClass
    public static void setUp() throws Exception {
        streamMatrixMultiplier = new StreamMatrixMultiplier();
    }

    @Test
    public void multiplySmallMatricesShouldReturnResultMatrix() throws Exception {
        Matrix resultMatrix = streamMatrixMultiplier.multiply(LEFT_SMALL_MATRIX, RIGHT_SMALL_MATRIX);

        checkResultMatrixSizes(SMALL_MATRIX_SIZE, resultMatrix);
        checkResultMatrixContent(resultMatrix);
    }

    @Test
    public void multiplyBigMatricesShouldReturnResultMatrix() throws Exception {
        Matrix resultMatrix = streamMatrixMultiplier.multiply(LEFT_BIG_MATRIX, RIGHT_BIG_MATRIX);

        checkResultMatrixSizes(BIG_MATRIX_SIZE, resultMatrix);
    }
}