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

import static com.challenge.maze.error.ErrorConstants.NO_PATH_EXISTS;

public class ShortestPathService {

    private static Logger logger = LogManager.getLogger(ShortestPathService.class);

    /**
     * Method to evaluate the shortest path with the use of breath first search algorithm
     */
    public List<Block> findPath(Maze maze) throws MazeException {
        logger.info("Start the shortest path calculations...");
        LinkedList<Block> nextToVisit = new LinkedList<>();
        Block start = maze.getStart();
        nextToVisit.add(start);

        while (!nextToVisit.isEmpty()) {
            Block current = nextToVisit.remove();
            if (MazeUtil.isOutOfBounds(maze, current) || current.isVisited()) {
                continue;
            }
            if (current.getBlockType().equals(Block.BlockType.WALL)) {
                current.setVisited(true);
                continue;
            }
            if (current.getBlockType().equals(Block.BlockType.END)) {
                return backtrackPath(current);
            }
            List<Block> neighbours = findNeighbours(maze, current);
            nextToVisit.addAll(neighbours);
            current.setVisited(true);
        }
        throw new MazeException(NO_PATH_EXISTS);
    }

    /**
     * Find the neighbours of the block that aren't visited
     *
     * @param maze where we want to find the path
     * @param current block that is processed
     * @return all the neighbours block of the current block
     */
    private List<Block> findNeighbours(Maze maze, Block current) {
        return Arrays.stream(Direction.values())
                .map(direction -> maze.moveTo(current, direction))
                .filter(Objects::nonNull)
                .filter(block -> !block.isVisited())
                .map(block -> block.setParent(current))
                .collect(Collectors.toList());
    }

    private List<Block> backtrackPath(Block exit) {
        List<Block> path = new ArrayList<>();
        Block current = exit;
        while (current != null) {
            path.add(current);
            current = current.getParent();
        }
        Collections.reverse(path);
        return path;
    }
}
