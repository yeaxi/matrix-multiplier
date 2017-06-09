package ua.dudka;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rostislav Dudka
 */
@Getter
@EqualsAndHashCode(of = "values")
@ToString(of = "values")
public class Matrix {

    private final int[][] values;
    private final int size;

    public Matrix(int size) {
        this.size = size;
        this.values = new int[size][size];
    }

    public Matrix(int[][] values) {
        this.size = values.length;
        this.values = values;

    }

    public List<Row> createRows() {
        List<Row> rows = new ArrayList<>(this.size);
        for (int i = 0; i < this.size; i++) {
            rows.add(new Row(i, values[i]));
        }
        return rows;
    }

    public List<Column> createColumns() {
        List<Column> columns = new ArrayList<>(this.size);
        for (int i = 0; i < this.size; i++) {
            int[] column = new int[this.size];
            for (int j = 0; j < values[i].length; j++) {
                column[j] = values[j][i];
            }
            columns.add(new Column(i, column));
        }
        return columns;
    }


    @Value
    @Getter
    @EqualsAndHashCode
    @ToString
    static class Row {
        private int index;
        private int[] values;
    }

    @Value
    @EqualsAndHashCode
    @ToString
    static class Column {
        private int index;
        private int[] values;
    }
}