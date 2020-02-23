package com.challenge.maze;

import com.challenge.maze.domain.Block;
import com.challenge.maze.domain.Maze;
import com.challenge.maze.error.MazeException;
import com.challenge.maze.service.MazeLoaderService;
import com.challenge.maze.service.PathService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class PathFinder {

    private static Logger logger = LogManager.getLogger(PathFinder.class);

    public static void main(String[] args) {
        MazeLoaderService mazeLoader = new MazeLoaderService();
        Maze maze = null;
        try {
            //maze = mazeLoader.readFile(args[0]);
            maze = mazeLoader.readFile("F:\\projects\\maze-challenge\\src\\test\\resources\\files\\MazeFile");
        } catch (MazeException e) {
            logger.error(e.getMessage());
            return;
        }

        PathService pathService = new PathService();
        List<Block> path = pathService.findPath(maze);

        logger.info("The path to exit the maze is:");
        for (Block block : path) {
            System.out.print(block.getBlockCoordinate().toString());
        }
    }
}
