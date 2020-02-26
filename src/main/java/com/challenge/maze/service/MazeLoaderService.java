package com.challenge.maze.service;

import com.challenge.maze.domain.Block;
import com.challenge.maze.domain.Coordinate;
import com.challenge.maze.domain.Maze;
import com.challenge.maze.error.MazeException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.challenge.maze.error.ErrorConstants.*;

public class MazeLoaderService {

    private static Logger logger = LogManager.getLogger(MazeLoaderService.class);

    public Maze readFile(String filePath) throws MazeException {
        logger.info("Starting reading the maze file...");
        File mazeFile = new File(filePath);
        List<String> fileLines = new ArrayList<>();
        try (Scanner scanner = new Scanner(mazeFile)) {
            while (scanner.hasNextLine()) {
                fileLines.add(scanner.nextLine());
            }
            Integer lineSize = validateMazeFile(fileLines);
            Maze maze = createMazeMatrix(fileLines, lineSize);
            logger.info("Reading the maze file ended.");
            return maze;
        } catch (FileNotFoundException e) {
            logger.log(Level.DEBUG, e.getMessage(), e);
            throw new MazeException(FILE_NOT_FOUND);
        }
    }

    private Integer validateMazeFile(List<String> fileLines) throws MazeException {
        Integer lineCharSize = fileLines.stream()
                .findFirst()
                .map(String::length)
                .orElseThrow(() -> new MazeException(EMPTY_FILE));

        boolean differentLineSize = fileLines.stream()
                .anyMatch(line -> line.length() != lineCharSize);
        if (differentLineSize) {
            throw new MazeException(MAZE_STRUCTURE_ERROR);
        }
        return lineCharSize;
    }

    private Maze createMazeMatrix(List<String> fileLines, Integer lineSize) throws MazeException {
        Block[][] mazeMatrix = new Block[fileLines.size()][lineSize];
        Maze maze = new Maze()
                .setGrid(mazeMatrix);
        for (int i = 0; i < fileLines.size(); i++) {
            char[] chars = fileLines.get(i).toCharArray();
            for (int j = 0; j < chars.length; j++) {
                char character = chars[j];
                Block.BlockType blockType = mapType(character);
                Block block = new Block(blockType, new Coordinate(i, j));
                setStartingBlock(maze, blockType, block);
                setEndingBlock(maze, blockType, block);
                mazeMatrix[i][j] = block;
            }
        }
        maze.setWidth(lineSize)
                .setHeight(fileLines.size());
        return maze;
    }

    private void validateEndPoint(boolean endBlockAlreadySet, Block.BlockType blockType) throws MazeException {
        if(blockType.equals(Block.BlockType.END) && endBlockAlreadySet) {
            throw new MazeException(DUPLICATE_END_POINT);
        }
    }
    /**
     * Method that sets the starting point in the maze object.
     * @param maze the loading maze
     * @param blockType the type of the block to check
     * @throws MazeException if the starting point is already set.
     */
    private void setStartingBlock(Maze maze, Block.BlockType blockType, Block block) throws MazeException {
        if (blockType.equals(Block.BlockType.START)) {
            if (maze.getStart() != null) {
                throw new MazeException(DUPLICATE_STARTING_POINT);
            }
            maze.setStart(block);
        }
    }

    private void setEndingBlock(Maze maze, Block.BlockType blockType, Block block) throws MazeException {
        if (blockType.equals(Block.BlockType.END)) {
            if (maze.getEnd() != null) {
                throw new MazeException(DUPLICATE_END_POINT);
            }
            maze.setEnd(block);
        }
    }

    /**
     * Maps the character of the file to a maze block type.
     * @param c the character of the file
     * @return the block type
     * @throws MazeException if the character doesn't map to any type.
     */
    private Block.BlockType mapType(char c) throws MazeException {
        switch (c) {
            case '_':
                return Block.BlockType.FREE;
            case 'X':
                return Block.BlockType.WALL;
            case 'S':
                return Block.BlockType.START;
            case 'G':
                return Block.BlockType.END;
            default:
                throw new MazeException(WRONG_CHARACTER);
        }
    }
}
