package com.challenge.maze.service;

import com.challenge.maze.domain.Block;
import com.challenge.maze.domain.Maze;
import com.challenge.maze.error.ErrorConstants;
import com.challenge.maze.error.MazeException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShortestPathServiceTest {

    @Test
    public void testSuccessFoundPath() throws MazeException {
        ShortestPathService shortestPathService = new ShortestPathService();
        List<Block> path = shortestPathService.findPath(createMaze("files/MazeFile"));
        assertEquals(path.get((path.size() - 1)).getBlockType(), Block.BlockType.END);
        assertEquals(path.get(0).getBlockType(), Block.BlockType.START);
    }

    @Test
    public void testWrongMaze() {
        ShortestPathService shortestPathService = new ShortestPathService();
        MazeException mazeException = assertThrows(MazeException.class, () -> shortestPathService.findPath(createMaze("files/wrong-maze")));
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
