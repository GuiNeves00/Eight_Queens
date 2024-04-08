public class Main {
    public static void main(String[] args) {
        char[][] matrix = new char[8][8];
        Solver solver = new Solver(matrix);

        solver.showBoard();

        solver.solve();
    }
}
