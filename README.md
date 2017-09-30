# Maze-Solver

The Maze Solver program prompts the user for the path and filename of the maze
puzzle, formatted as a .txt, to be solved and loads and converts the .txt
file into a maze object. The .txt file should be formatted as follows: The
first line of the file contains two integers N and M, separated by a space,
respectively representing the height and width of the maze. Following the
first line are lines of periods, separated by spaces, with each line
separated by empty lines. Sheffer strokes and hyphens separating periods
horizontally and vertically represent the 'walls' of the maze. The starting
position is indicated by an 'S', and the goal is indicated by an 'F'. Note that 
this repository contains 9 sample maze text files for example and testing purposes. 
After a file has been loaded, the program runs depth-first, breadth-first, and uniform cost
searches to find the solution path to the maze. The solutions are printed to the console,
along with the number of steps necessary for each of the searches to find the solution.

The source code contained in this repository was written by Andrew Edinger as part 
of an assignment for Dr. Michael Zmuda's course in Intro to Artificial Intelligence 
at Miami University of Oxford, Ohio.
