package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SearchMaze {
    @EpiUserType(ctorParams = {int.class, int.class})

    public static class Coordinate {
        public int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

		@Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Coordinate that = (Coordinate) o;
            if (x != that.x || y != that.y) {
                return false;
            }
            return true;
        }
    }

    public enum Color {WHITE, BLACK}

    public static List<Coordinate> searchMaze(List<List<Color>> maze,
                                              Coordinate s, Coordinate e) {
        if (maze == null || maze.isEmpty() || s == null || e == null) {
            return new ArrayList<>();
        }
		LinkedList<Coordinate> path = new LinkedList<>();
		if (isFeasible(maze, s)) {
			maze.get(s.x).set(s.y, Color.BLACK);
        	path.addLast(s);
		} else {
        	return path;
		}

        if(!searchMaze(maze, path, s, e)) {
			path.removeLast();
		}
		
		return path;
    }

    private static boolean searchMaze(List<List<Color>> maze, LinkedList<Coordinate> path,
									  Coordinate current, Coordinate end) {
        if (current.equals(end)) {
            return true;
        }

        int[][] shift = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		for (int[] s : shift) {
			Coordinate next = new Coordinate(current.x + s[0], current.y + s[1]);
			if (isFeasible(maze, next)) {
				maze.get(next.x).set(next.y, Color.BLACK);
				path.addLast(next);
				if (searchMaze(maze, path, next, end)) {
					return true;
				}
				path.removeLast();
			}
		}

        return false;
    }

    private static boolean isFeasible(List<List<Color>> maze, Coordinate current) {
        return 0 <= current.x && current.x < maze.size()
                && 0 <= current.y && current.y < maze.get(0).size()
				&& maze.get(current.x).get(current.y) == Color.WHITE;
    }

    public static boolean pathElementIsFeasible(List<List<Integer>> maze,
                                                Coordinate prev, Coordinate cur) {
        if (!(0 <= cur.x && cur.x < maze.size() && 0 <= cur.y &&
                cur.y < maze.get(cur.x).size() && maze.get(cur.x).get(cur.y) == 0)) {
            return false;
        }
        return cur.x == prev.x + 1 && cur.y == prev.y ||
                cur.x == prev.x - 1 && cur.y == prev.y ||
                cur.x == prev.x && cur.y == prev.y + 1 ||
                cur.x == prev.x && cur.y == prev.y - 1;
    }

    @EpiTest(testDataFile = "search_maze.tsv")
    public static boolean searchMazeWrapper(List<List<Integer>> maze,
                                            Coordinate s, Coordinate e)
            throws TestFailure {
        List<List<Color>> colored = new ArrayList<>();
        for (List<Integer> col : maze) {
            List<Color> tmp = new ArrayList<>();
            for (Integer i : col) {
                tmp.add(i == 0 ? Color.WHITE : Color.BLACK);
            }
            colored.add(tmp);
        }
        List<Coordinate> path = searchMaze(colored, s, e);
        if (path.isEmpty()) {
            return s.equals(e);
        }

        if (!path.get(0).equals(s) || !path.get(path.size() - 1).equals(e)) {
            throw new TestFailure("Path doesn't lay between start and end points");
        }

        for (int i = 1; i < path.size(); i++) {
            if (!pathElementIsFeasible(maze, path.get(i - 1), path.get(i))) {
                throw new TestFailure("Path contains invalid segments");
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchMaze.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
