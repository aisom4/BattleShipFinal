import java.util.ArrayList;

public class BattleshipGame {
    private ArrayList<Battleship> battleships; //stores battleships and the gamehelper
    private GameHelper helper; //adds in counter
    private int numOfGuesses;

    public BattleshipGame(int numBattleships) { //constructor creating a battleship game
        battleships = new ArrayList<>();  //empty list to store battleships
        helper = new GameHelper();   //game helper is created to help with game
        numOfGuesses = 0;   //initializes the guess counter
        setupGame(numBattleships);  //set up the game with a certain number of battleship
    }

    public void playGame() {  //method for playing the game
        System.out.println("Welcome to Battleship!");
        while (!battleships.isEmpty()) { //will not play while the battleship empty
            String userGuess = helper.getUserInput("Enter a guess:");  //getting user guest
            checkGuess(userGuess);  //check if the user guess is correct
        }

        //When all battleships are sunk
        System.out.println("Congratulations! You sunk all battleships.");
        System.out.println("Total number of guesses: " + numOfGuesses);
    }

    private void checkGuess(String guess) { //method to check if a user's guess is correct
        numOfGuesses++;
        String result = "Miss";
        for (Battleship battleship : battleships) {
            if (battleship.checkLocation(guess)) {
                result = battleship.getName() + " Sunk!";  //when the user guesses correctly
                battleships.remove(battleship); //remove the battleship
                break;  //exiting the loop
            }
        }
        System.out.println(result);
    }

    private void setupGame(int numBattleships) {  //method used to set up the game
        for (int i = 1; i <= numBattleships; i++) {
            String location = helper.placeStartup(1).get(0); // Get a single startup location
            battleships.add(new Battleship("Battleship " + i, location));   //creating a battleship to add to the list
        }
    }

    public static void main(String[] args) {  //this main method begins the game
        BattleshipGame game = new BattleshipGame(5); // Change the number of battleships as needed
        game.playGame();  //starts the game
    }
}