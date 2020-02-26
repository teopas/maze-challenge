package com.challenge.maze;

import com.challenge.maze.domain.Block;
import com.challenge.maze.domain.Maze;
import com.challenge.maze.error.MazeException;
import com.challenge.maze.service.MazeLoaderService;
import com.challenge.maze.service.MultiplePathService;
import com.challenge.maze.service.ShortestPathService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class PathFinder {

    public static final String SHORTEST_PATH = "shortest";
    public static final String MULTIPLE_PATHS = "multiple";
    private static Logger logger = LogManager.getLogger(PathFinder.class);

    public static void main(String[] args) {
        logger.info("Maze solver started");
        MazeLoaderService mazeLoader = new MazeLoaderService();
        String filePath = args[1];
        String method = args[0];
        if (validateArgs(filePath, method)) {
            logger.info("Wrong program arguments.");
            return;
        }
        Maze maze = null;
        try {
            maze = mazeLoader.readFile(filePath);
            if (method.equals(SHORTEST_PATH)) {
                callShortestPathService(maze);
            } else if (method.equals(MULTIPLE_PATHS)) {
                callMultiplePathService(maze);
            }
        } catch (MazeException e) {
            logger.error(e.getMessage());
        }
    }

    private static void callMultiplePathService(Maze maze) throws MazeException {
        MultiplePathService multiplePathService = new MultiplePathService();

        List<List<Block>> paths = multiplePathService.findMultiplePaths(maze);
        logger.info("The paths to exit the maze are:");
        for (List<Block> path : paths) {
            for (Block block : path) {
                System.out.print(block.getBlockCoordinate().toString());
            }
            System.out.println();
        }
    }

    private static void callShortestPathService(Maze maze) throws MazeException {
        ShortestPathService shortestPathService = new ShortestPathService();
        List<Block> path = null;
        path = shortestPathService.findPath(maze);
        logger.info("The path to exit the maze is:");
        for (Block block : path) {
            System.out.print(block.getBlockCoordinate().toString());
        }
    }

    private static boolean validateArgs(String filePath, String method) {
        return filePath == null || method == null
                || (!method.equalsIgnoreCase(SHORTEST_PATH) && !method.equalsIgnoreCase(MULTIPLE_PATHS));
    }
}
