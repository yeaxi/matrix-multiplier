package ua.dudka;

import lombok.Value;
import ua.dudka.Matrix.Column;
import ua.dudka.Matrix.Row;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * @author Rostislav Dudka
 */
public class StreamMatrixMultiplier implements MatrixMultiplier {

    @Override
    public Matrix multiply(Matrix leftMatrix, Matrix rightMatrix) {
        final int length = leftMatrix.getSize();

        return leftMatrix.createRows().parallelStream()
                .flatMap(row -> rightMatrix.createColumns()
                        .stream()
                        .map(column -> rowToColumnMultiplier.apply(row, column)))
                .reduce(new Matrix(length), accumulator, combiner);
    }

    private final BiFunction<Matrix, MultiplyResult, Matrix> accumulator =
            (matrix, result) -> {
                int[][] values = matrix.getValues();
                values[result.getRowIndex()][result.getColumnIndex()] = result.getSum();
                return new Matrix(values);
            };

    private final BinaryOperator<Matrix> combiner = (firstMatrix, secondMatrix) -> {
        int size = firstMatrix.getSize();
        int[][] firstMatrixValues = firstMatrix.getValues();
        int[][] secondMatrixValues = secondMatrix.getValues();
        int[][] newMatrixValues = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (firstMatrixValues[i][j] == 0 && secondMatrixValues[i][j] != 0)
                    newMatrixValues[i][j] = secondMatrixValues[i][j];
                else
                    newMatrixValues[i][j] = firstMatrixValues[i][j];
            }
        }
        return new Matrix(newMatrixValues);
    };

    private final BiFunction<Row, Column, MultiplyResult> rowToColumnMultiplier = (row, column) -> {
        int[] rowValues = row.getValues();
        int[] columnValues = column.getValues();
        int sum = 0;

        for (int i = 0; i < rowValues.length; i++) {
            sum += rowValues[i] * columnValues[i];
        }
        return new MultiplyResult(row.getIndex(), column.getIndex(), sum);
    };


    @Value
    private static class MultiplyResult {
        private int rowIndex;
        private int columnIndex;
        private int sum;
    }
}