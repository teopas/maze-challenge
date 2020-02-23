package com.challenge.maze.domain;


import java.util.Objects;

public class Block {

    public enum BlockType {
        FREE, WALL, START, END;
    }

    private BlockType blockType;
    private Coordinate blockCoordinate;
    private Block parent;
    private boolean isVisited;

    public Block(BlockType blockType, Coordinate blockCoordinate) {
        this.blockType = blockType;
        this.blockCoordinate = blockCoordinate;
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public Block setBlockType(BlockType blockType) {
        this.blockType = blockType;
        return this;
    }

    public Coordinate getBlockCoordinate() {
        return blockCoordinate;
    }

    public Block setBlockCoordinate(Coordinate blockCoordinate) {
        this.blockCoordinate = blockCoordinate;
        return this;
    }

    public Block getParent() {
        return parent;
    }

    public Block setParent(Block parent) {
        this.parent = parent;
        return this;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public Block setVisited(boolean visited) {
        isVisited = visited;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return Objects.equals(blockCoordinate, block.blockCoordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockCoordinate);
    }
}
