package com.challenge.maze.service;

import com.challenge.maze.domain.Maze;

import java.util.ArrayList;
import java.util.List;

import static com.challenge.maze.domain.Block.BlockType.WALL;

public class MultiplePathService {

    private List findPaths(Maze maze) {
        List result = new ArrayList();
        if (maze.getStart().getBlockCoordinate().getRow() < 0 ||
                maze.getStart().getBlockCoordinate().getColumn() < 0) {
            return null;
        }
        if (maze.getStart().getBlockCoordinate().getRow() == 0 ||
                maze.getStart().getBlockCoordinate().getColumn() == maze.getWidth()) {
            return null;
        }
        if (maze.getStart().getBlockType().equals(WALL)) {
            return null;
        }
        if (maze.getStart().equals(maze.getEnd())) {
            List path = new ArrayList();
            path.add(maze.getStart());
            result.add(path);
            return result;
        }


        return null;
    }
}
