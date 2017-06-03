package ua.dudka;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * @author Rostislav Dudka
 */
@FunctionalInterface
public interface MatrixMultiplier {
    Logger log = LogManager.getRootLogger();

    int[][] multiply(int[][] left, int[][] right);

    default int[][] loggingMultiply(int[][] left, int[][] right) {
        log.info("input left matrix {}, right matrix {}", left, right);

        int[][] result = multiply(left, right);

        log.info("result matrix {}", Arrays.deepToString(result));
        return result;
    }
}