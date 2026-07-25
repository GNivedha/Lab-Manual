public class CheckStraightLine {

    // Method to check whether all points lie on the same straight line
    public static boolean checkStraightLine(int[][] coordinates) {

        if (coordinates.length <= 2) {
            return true;
        }

        int x0 = coordinates[0][0];
        int y0 = coordinates[0][1];

        int x1 = coordinates[1][0];
        int y1 = coordinates[1][1];

        int dx = x1 - x0;
        int dy = y1 - y0;

        for (int i = 2; i < coordinates.length; i++) {

            int x = coordinates[i][0];
            int y = coordinates[i][1];

            int dxCurr = x - x0;
            int dyCurr = y - y0;

            if (dx * dyCurr != dy * dxCurr) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        // Test Case 1
        int[][] coordinates1 = {
            {1,2},
            {2,3},
            {3,4},
            {4,5},
            {5,6},
            {6,7}
        };

        System.out.println("Test Case 1");
        System.out.print("Coordinates: ");
        for (int[] point : coordinates1) {
            System.out.print("[" + point[0] + "," + point[1] + "] ");
        }
        System.out.println();
        System.out.println("Output: " + checkStraightLine(coordinates1));

        // Test Case 2
        int[][] coordinates2 = {
            {1,1},
            {2,2},
            {3,4},
            {4,5},
            {5,6}
        };

        System.out.println("\nTest Case 2");
        System.out.print("Coordinates: ");
        for (int[] point : coordinates2) {
            System.out.print("[" + point[0] + "," + point[1] + "] ");
        }
        System.out.println();
        System.out.println("Output: " + checkStraightLine(coordinates2));
    }
}