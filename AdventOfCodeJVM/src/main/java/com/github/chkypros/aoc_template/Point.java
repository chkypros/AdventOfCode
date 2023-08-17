package com.github.chkypros.aoc_template;

public class Point {
    public final int row;
    public final int column;

    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    public Point add(Point other) {
        return add(other.row, other.column);
    }

    public Point add(Pair<Integer, Integer> pair) {
        return add(pair.first, pair.second);
    }

    public Point add(kotlin.Pair<Integer, Integer> pair) {
        return add(pair.getFirst(), pair.getSecond());
    }

    public Point add(int row, int column) {
        return of(this.row + row, this.column + column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (row != point.row) return false;
        return column == point.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }

    public static Point of(int row, int column) {
        return new Point(row, column);
    }
}
