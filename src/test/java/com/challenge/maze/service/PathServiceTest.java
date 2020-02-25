package com.challenge.maze.service;

import com.challenge.maze.domain.Block;
import com.challenge.maze.domain.Maze;
import com.challenge.maze.error.ErrorConstants;
import com.challenge.maze.error.MazeException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathServiceTest {

    @Test
    void testSuccessFoundPath() throws MazeException {
        PathService pathService = new PathService();
        List<Block> path = pathService.findPath(createMaze("files/MazeFile"));
        assertTrue(path.get((path.size() - 1)).getBlockType().equals(Block.BlockType.END));
        assertTrue(path.get(0).getBlockType().equals(Block.BlockType.START));
    }

    @Test
    void testWrongMaze() throws MazeException {
        PathService pathService = new PathService();
        MazeException mazeException = assertThrows(MazeException.class, () -> pathService.findPath(createMaze("files/wrong-maze")));
        assertTrue(mazeException.getMessage().contains(ErrorConstants.NO_PATH_EXISTS));
    }


    private Maze createMaze(String fileName) throws MazeException {
        MazeLoaderService mazeLoaderService = new MazeLoaderService();
        return mazeLoaderService.readFile(resolvePath(fileName));
    }

    private String resolvePath(String filename) {
        return getClass().getClassLoader().getResource(filename).getPath();
    }
}
