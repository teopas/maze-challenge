package com.challenge.maze;

import com.challenge.maze.error.ErrorConstants;
import com.challenge.maze.error.MazeException;
import com.challenge.maze.service.MazeLoaderService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazeLoaderServiceTest {

    @Test
    public void testWrongFilePath() throws MazeException {
        String wrongFilePath = "F:\\projects\\maze-challenge\\src\\test\\resources\\wrongDirecory\\MazeFile";
        MazeException mazeException = assertThrows(MazeException.class, () -> new MazeLoaderService().readFile(wrongFilePath));
        assertTrue(mazeException.getMessage().contains(ErrorConstants.FILE_NOT_FOUND));
    }

    @Test
    public void testWrongFileStructure() throws MazeException {
        String filePath = "F:\\projects\\maze-challenge\\src\\test\\resources\\files\\maze-file-wrong-structure";
        MazeException mazeException = assertThrows(MazeException.class, () -> new MazeLoaderService().readFile(filePath));
        assertTrue(mazeException.getMessage().contains(ErrorConstants.MAZE_STRUCTURE_ERROR));
    }

    @Test
    public void testWrongFileCharacter() throws MazeException {
        String filePath = "F:\\projects\\maze-challenge\\src\\test\\resources\\files\\maze-file-wrong-character";
        MazeException mazeException = assertThrows(MazeException.class, () -> new MazeLoaderService().readFile(filePath));
        assertTrue(mazeException.getMessage().contains(ErrorConstants.WRONG_CHARACTER));
    }

    @Test
    public void testDuplicateStartPoint() throws MazeException {
        String filePath = "F:\\projects\\maze-challenge\\src\\test\\resources\\files\\maze-file-duplicate-start";
        MazeException mazeException = assertThrows(MazeException.class, () -> new MazeLoaderService().readFile(filePath));
        assertTrue(mazeException.getMessage().contains(ErrorConstants.DUPLICATE_STARTING_POINT));
    }

    @Test
    public void testDuplicateEndPoint() throws MazeException {
        String filePath = "F:\\projects\\maze-challenge\\src\\test\\resources\\files\\maze-file-duplicate-end";
        MazeException mazeException = assertThrows(MazeException.class, () -> new MazeLoaderService().readFile(filePath));
        assertTrue(mazeException.getMessage().contains(ErrorConstants.DUPLICATE_END_POINT));
    }

}
