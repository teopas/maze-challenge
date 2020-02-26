package com.challenge.maze.service;

import com.challenge.maze.domain.Block;
import com.challenge.maze.domain.Maze;
import com.challenge.maze.error.ErrorConstants;
import com.challenge.maze.error.MazeException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MultiplePathServiceTest {

    @Test
    void findMultiple() throws MazeException {
        MultiplePathService multiplePathService = new MultiplePathService();
        Maze maze = createMaze("files/small-maze");
        List<List<Block>> paths = multiplePathService.findMultiplePaths(maze);
        for (List<Block> path : paths) {
            assertEquals(path.get((path.size() - 1)).getBlockType(), Block.BlockType.END);
            assertEquals(path.get(0).getBlockType(), Block.BlockType.START);
        }
    }

    @Test
    void testWrongMaze() {
        MultiplePathService multiplePathService = new MultiplePathService();
        MazeException mazeException = assertThrows(MazeException.class, () -> multiplePathService.findMultiplePaths(createMaze("files/wrong-maze")));
        assertTrue(mazeException.getMessage().contains(ErrorConstants.NO_PATH_EXISTS));
    }

    private String resolvePath(String filename) {
        return getClass().getClassLoader().getResource(filename).getPath();
    }

    private Maze createMaze(String fileName) throws MazeException {
        MazeLoaderService mazeLoaderService = new MazeLoaderService();
        return mazeLoaderService.readFile(resolvePath(fileName));
    }
}
