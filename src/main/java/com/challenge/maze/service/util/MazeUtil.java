package com.challenge.maze.service.util;

import com.challenge.maze.domain.Block;
import com.challenge.maze.domain.Maze;

public class MazeUtil {

    private MazeUtil() {
    }

    public static boolean isOutOfBounds(Maze maze, Block block) {
        return block.getBlockCoordinate().getRow() < 0 ||
                block.getBlockCoordinate().getColumn() < 0 ||
                block.getBlockCoordinate().getRow().equals(maze.getHeight()) ||
                block.getBlockCoordinate().getColumn().equals(maze.getWidth());
    }
}
