package com.challenge.maze.domain;

public class Maze {

    private Block[][] grid;
    private Block start;
    private Block end;
    private Integer height;
    private Integer width;

    public Maze() {
    }


    public Block moveTo(Block current, Direction direction) {
        if (current.getBlockCoordinate().getRow() == 0 && direction.equals(Direction.SOUTH)
                || (current.getBlockCoordinate().getRow().equals(this.height - 1) && direction.equals(Direction.NORTH)
                || (current.getBlockCoordinate().getColumn() == 0 && direction.equals(Direction.WEST)
                || (current.getBlockCoordinate().getColumn().equals(this.width - 1) && direction.equals(Direction.EAST))))) {
            return null;
        }
        Coordinate coordinate = direction.moveTo(current.getBlockCoordinate());
        return getGridBlock(coordinate);
    }

    public Block[][] getGrid() {
        return grid;
    }

    public Maze setGrid(Block[][] grid) {
        this.grid = grid;
        return this;
    }

    public Block getGridBlock(Coordinate coordinate) {
        return grid[coordinate.getRow()][coordinate.getColumn()];
    }

    public Block getStart() {
        return start;
    }

    public Maze setStart(Block start) {
        this.start = start;
        return this;
    }

    public Block getEnd() {
        return end;
    }

    public Maze setEnd(Block end) {
        this.end = end;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public Maze setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public Maze setWidth(Integer width) {
        this.width = width;
        return this;
    }
}
