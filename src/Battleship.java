public class Battleship {
    private String name;   //stores name and number of the battleship
    private String location;

    public Battleship(String name, String location) {  //constructor for the battleship name and location
        this.name = name;  // sets the battleship name
        this.location = location;  //sets the battle number
    }

    public String getName() {  //creating a method for the battleship location
        return name;   //returning the name
    }

    public boolean checkLocation(String userGuess) {  //checking the user-guess matches the location of the battleship
        return location.equals(userGuess);  //compare the user's guess with the battleship's location
    }
}