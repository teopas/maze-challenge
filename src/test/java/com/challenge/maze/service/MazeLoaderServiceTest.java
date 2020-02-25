package com.challenge.maze.service;

import com.challenge.maze.error.ErrorConstants;
import com.challenge.maze.error.MazeException;
import com.challenge.maze.service.MazeLoaderService;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazeLoaderServiceTest {

    @Test
    public void testWrongFilePath() {
        String wrongFilePath = "wrongPath/MazeFile";
        MazeException mazeException = assertThrows(MazeException.class, () -> new MazeLoaderService().readFile(wrongFilePath));
        assertTrue(mazeException.getMessage().contains(ErrorConstants.FILE_NOT_FOUND));
    }

    @Test
    public void testWrongFileStructure() {
        String filePath = resolvePath("files/maze-file-wrong-structure");
        MazeException mazeException = assertThrows(MazeException.class, () -> new MazeLoaderService().readFile(filePath));
        assertTrue(mazeException.getMessage().contains(ErrorConstants.MAZE_STRUCTURE_ERROR));
    }

    @Test
    public void testWrongFileCharacter() {
        String filePath = resolvePath("files/maze-file-wrong-character");
        MazeException mazeException = assertThrows(MazeException.class, () -> new MazeLoaderService().readFile(filePath));
        assertTrue(mazeException.getMessage().contains(ErrorConstants.WRONG_CHARACTER));
    }

    @Test
    public void testDuplicateStartPoint() {
        String filePath = resolvePath("files/maze-file-duplicate-start");
        MazeException mazeException = assertThrows(MazeException.class, () -> new MazeLoaderService().readFile(filePath));
        assertTrue(mazeException.getMessage().contains(ErrorConstants.DUPLICATE_STARTING_POINT));
    }

    @Test
    public void testDuplicateEndPoint() {
        String filePath = resolvePath("files/maze-file-duplicate-end");
        MazeException mazeException = assertThrows(MazeException.class, () -> new MazeLoaderService().readFile(filePath));
        assertTrue(mazeException.getMessage().contains(ErrorConstants.DUPLICATE_END_POINT));
    }

    private String resolvePath(String filename) {
        return getClass().getClassLoader().getResource(filename).getPath();
    }

}
