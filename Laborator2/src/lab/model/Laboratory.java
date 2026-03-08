package lab.model;

/**
 * Demonstreaza array multidimensional si initializarea atributelor.
 */
public class Laboratory {
    private String room;
    private int[][] seatingMatrix;

    // bloc de initializare
    {
        seatingMatrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6}
        };
    }

    public Laboratory() {
        this("L-404");
    }

    public Laboratory(String room) {
        this.room = room;
    }

    public void printSeating() {
        System.out.println("\nLaborator in sala " + room + ":");
        for (int i = 0; i < seatingMatrix.length; i++) {
            for (int j = 0; j < seatingMatrix[i].length; j++) {
                System.out.print(seatingMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int getSeatFromRowColumn(int row, int column) {
        return seatingMatrix[row][column];
    }
}
