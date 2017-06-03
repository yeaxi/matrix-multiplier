package ua.dudka;


/**
 * @author Rostislav Dudka
 */
@FunctionalInterface
public interface MatrixMultiplier {

    Matrix multiply(Matrix left, Matrix right);
}