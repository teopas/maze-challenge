package com.challenge.maze.domain;

import java.util.Objects;

public class Coordinate {
    private Integer row;
    private Integer column;

    public Coordinate(Integer row, Integer column) {
        this.row = row;
        this.column = column;
    }

    public Integer getRow() {
        return row;
    }

    public Coordinate setRow(Integer row) {
        this.row = row;
        return this;
    }

    public Integer getColumn() {
        return column;
    }

    public Coordinate setColumn(Integer column) {
        this.column = column;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(row, that.row) &&
                Objects.equals(column, that.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ')';
    }
}
