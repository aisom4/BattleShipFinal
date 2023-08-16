import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GameHelper {  //setting up the grid
    private static final String ALPHABET = "abcdefg";  //letters for columns
    private static final int GRID_LENGTH = 7;  //rows and columns amount
    private static final int GRID_SIZE = 49;  //entire size of the 7 x 7 grid
    private static final int MAX_ATTEMPTS = 200; //maximum number of attempts for the game

    static final int HORIZONTAL_INCREMENT = 1; //incrementing horizontal movements
    static final int VERTICAL_INCREMENT = GRID_LENGTH;  //vertical movements

    private final int[] grid = new int[GRID_SIZE];  //tracking occupied cells within the grid
    private final Random random = new Random(); //random generator

    private int startupCount = 0; //counter for the battleship placement

    //grabbing user output
    public String getUserInput(String prompt) {
        System.out.print(prompt + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase();
    }

    //method for placing battleships locations
    public ArrayList<String> placeStartup(int startupSize) {
        int[] startupCoords = new int[startupSize];
        int attempts = 0;
        boolean success = false;

        startupCount++; //incrementing the start-up count
        int increment = getIncrement();


        //satisfying conditions for placing battleships
        while (!success & attempts++ < MAX_ATTEMPTS) {
            int location = random.nextInt(GRID_SIZE);

            for (int i = 0; i < startupCoords.length; i++) { //generating start up coordinates list
                startupCoords[i] = location;
                location += increment;
            }

            if (startupFits(startupCoords, increment)) { //checking if the placement is correct for the grid
                success = coordsAvailable(startupCoords);  //checking cell availability
            }
        }

        savePositionToGrid(startupCoords);  //mark the occupied cell on the grid
        ArrayList<String> alphaCells = convertCoordsToAlphaFormat(startupCoords);
        return alphaCells;
    }

    boolean startupFits(int[] startupCoords, int increment) {  //checking battleship placement
        int finalLocation = startupCoords[startupCoords.length - 1];
        if (increment == HORIZONTAL_INCREMENT) {
            return calcRowFromIndex(startupCoords[0]) == calcRowFromIndex(finalLocation); //check cell rows
        } else {
            return finalLocation < GRID_SIZE;  //check if final location is in the grid bounds
        }
    }

    boolean coordsAvailable(int[] startupCoords) {  //method created to check if coordinates are available
        for (int coord : startupCoords) {
            if (grid[coord] != 0) {
                return false;  // if occupied placement will not be possible
            }
        }
        return true;  //all cells will be available
    }

    void savePositionToGrid(int[] startupCoords) { //method created to mark cells as occupied
        for (int index : startupCoords) {
            grid[index] = 1;  // marking cell as occupied
        }
    }

    private ArrayList<String> convertCoordsToAlphaFormat(int[] startupCoords) {  //method created to convert numeric values to letter-numbers
        ArrayList<String> alphaCells = new ArrayList<>();
        for (int index : startupCoords) {
            String alphaCoords = getAlphaCoordsFromIndex(index);
            alphaCells.add(alphaCoords);
        }
        return alphaCells;
    }

    String getAlphaCoordsFromIndex(int index) {  //converting numericals to letter-number
        int row = calcRowFromIndex(index);
        int column = index % GRID_LENGTH;

        String letter = ALPHABET.substring(column, column + 1);
        return letter + row;
    }

    private int calcRowFromIndex(int index) {  //calculating row from index
        return index / GRID_LENGTH;
    }

    private int getIncrement() {  //method for movement based on startup count
        if (startupCount % 2 == 0) {
            return HORIZONTAL_INCREMENT;  //placing horizontally
        } else {
            return VERTICAL_INCREMENT; //vertical placement
        }
    }
}
