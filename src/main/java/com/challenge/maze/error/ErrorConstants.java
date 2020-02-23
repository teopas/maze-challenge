package com.challenge.maze.error;

public class ErrorConstants {
    private ErrorConstants() {
    }

    public static final String FILE_NOT_FOUND = "File not founded in path provided!";
    public static final String MAZE_STRUCTURE_ERROR = "The file doesn't represent a two-dimensional array!";
    public static final String WRONG_CHARACTER = "Wrong character in the file!";
    public static final String EMPTY_FILE = "The file provided is empty!";
    public static final String DUPLICATE_STARTING_POINT = "Maze can't have two starting points!";
    public static final String DUPLICATE_END_POINT = "Duplicate end point";

}
