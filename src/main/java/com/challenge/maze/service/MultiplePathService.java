package com.challenge.maze.service;

import com.challenge.maze.domain.Block;
import com.challenge.maze.domain.Direction;
import com.challenge.maze.domain.Maze;
import com.challenge.maze.error.MazeException;
import com.challenge.maze.service.util.MazeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

import static com.challenge.maze.domain.Block.BlockType.WALL;
import static com.challenge.maze.error.ErrorConstants.NO_PATH_EXISTS;

public class MultiplePathService {

    private static Logger logger = LogManager.getLogger(MultiplePathService.class);

    public List<List<Block>> findMultiplePaths(Maze maze) throws MazeException {
        logger.info("Start the multiple path calculations...");
        List<List<Block>> paths = findPaths(maze, maze.getStart(), maze.getEnd());
        if (paths.isEmpty()) {
            throw new MazeException(NO_PATH_EXISTS);
        }
        return paths;
    }

    private List<List<Block>> findPaths(Maze maze, Block start, Block end) {

        List<List<Block>> result = new ArrayList<>();
        if (MazeUtil.isOutOfBounds(maze, start)) {
            return Collections.emptyList();
        }
        if (start.getBlockType().equals(WALL)) {
            return Collections.emptyList();
        }
        if (start.equals(end)) {
            List<Block> path = new ArrayList<>();
            path.add(start);
            result.add(path);
            return result;
        }
        start.setVisited(true);
        List<Block> neighbours = findNeighbours(maze, start);
        for (Block block : neighbours) {
            List<List<Block>> paths = findPaths(maze, block, end);
            if (!paths.isEmpty()) {
                for (List<Block> path : paths) {
                    path.add(0, start);
                }
                result.addAll(paths);
            }
        }
        start.setVisited(false);
        if (result.size() == 0) {
            return Collections.emptyList();
        }
        return result;
    }

    private List<Block> findNeighbours(Maze maze, Block current) {
        return Arrays.stream(Direction.values())
                .map(direction -> maze.moveTo(current, direction))
                .filter(Objects::nonNull)
                .filter(block -> !block.isVisited())
                .collect(Collectors.toList());
    }
}
