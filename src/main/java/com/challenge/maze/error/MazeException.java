package com.challenge.maze.error;

public class MazeException extends Exception {

    private String[] args;

    public MazeException(String message, String... args) {
        super(message);
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }
}
