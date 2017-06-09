package ua.dudka;

import lombok.Value;
import ua.dudka.Matrix.Column;
import ua.dudka.Matrix.Row;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

/**
 * @author Rostislav Dudka
 */
public class StreamMatrixMultiplier implements MatrixMultiplier {

    @Override
    public Matrix multiply(Matrix leftMatrix, Matrix rightMatrix) {
        final int length = leftMatrix.getSize();
        final List<Column> columns = rightMatrix.createColumns();

        return leftMatrix.createRows().parallelStream()
                .flatMap(row -> columns
                        .stream()
                        .map(column -> rowToColumnMultiplier.apply(row, column)))
                .reduce(new Matrix(length), accumulator, combiner);
    }

    private final BiFunction<Row, Column, MultiplyResult> rowToColumnMultiplier = (row, column) -> {
        int[] rowValues = row.getValues();
        int[] columnValues = column.getValues();
        int sum = 0;

        for (int i = 0; i < rowValues.length; i++) {
            sum += rowValues[i] * columnValues[i];
        }
        return new MultiplyResult(row.getIndex(), column.getIndex(), sum);
    };

    private final BiFunction<Matrix, MultiplyResult, Matrix> accumulator = (matrix, result) -> {
                int[][] values = matrix.getValues();

                values[result.getRowIndex()][result.getColumnIndex()] = result.getSum();
                return new Matrix(values);
            };

    private final BinaryOperator<Matrix> combiner = (leftMatrix, rightMatrix) -> {
        int size = leftMatrix.getSize();
        int[][] leftMatrixValues = leftMatrix.getValues();
        int[][] rightMatrixValues = rightMatrix.getValues();
        int[][] newMatrixValues = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int leftMatrixValue = leftMatrixValues[i][j];
                int rightMatrixValue = rightMatrixValues[i][j];
                boolean isLeftMatrixValueEmpty = leftMatrixValue == 0 && rightMatrixValue != 0;
                if (isLeftMatrixValueEmpty)
                    newMatrixValues[i][j] = rightMatrixValue;
                else
                    newMatrixValues[i][j] = leftMatrixValue;
            }
        }
        return new Matrix(newMatrixValues);
    };



    @Value
    private static class MultiplyResult {
        private int rowIndex;
        private int columnIndex;
        private int sum;
    }
}