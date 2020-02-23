package com.challenge.maze.domain;

import com.challenge.maze.domain.Coordinate;

public enum Direction {
    NORTH(1, 0), SOUTH(-1 , 0), WEST(0, -1), EAST(0, 1);

    private Integer row;
    private Integer column;

    Direction(Integer row, Integer column) {
        this.row = row;
        this.column = column;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public Coordinate moveTo(Coordinate coordinate) {
        return new Coordinate(coordinate.getRow() + this.getRow(),
                coordinate.getColumn() + this.getColumn());
    }
}
