package com.challenge.maze.service;

import com.challenge.maze.domain.Block;
import com.challenge.maze.domain.Maze;
import com.challenge.maze.error.MazeException;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MultiplePathServiceTest {

    @Test
    void findMultiple() throws MazeException {
        MultiplePathService multiplePathService = new MultiplePathService();
        Maze maze = createMaze("files/small-maze");
        List<List<Block>> paths = multiplePathService.findMultiplePaths(maze);
        for (List<Block> path : paths) {
            for (Block block : path) {
                System.out.print(block.getBlockCoordinate().toString());
            }
            System.out.println();
        }
    }

    private String resolvePath(String filename) {
        return getClass().getClassLoader().getResource(filename).getPath();
    }

    private Maze createMaze(String fileName) throws MazeException {
        MazeLoaderService mazeLoaderService = new MazeLoaderService();
        return mazeLoaderService.readFile(resolvePath(fileName));
    }
}
