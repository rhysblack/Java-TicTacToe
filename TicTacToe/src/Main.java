import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // variables
        int playerPosition, computerPosition, turnCounter;
        String keepPlaying = "";
        boolean xWin, oWin;

        // input
        Scanner input = new Scanner(System.in);

        // loop until player quits
        while(!keepPlaying.equalsIgnoreCase("quit")) {
            // reset list and counter
            //list of available spaces
            String [] spaces = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

            turnCounter = 0;

            // loop until game end
            while(true) {
                //x turn
                turnCounter += 1;
                System.out.println("\nTurn: " + turnCounter);
                System.out.println("X Turn");

                //get valid input
                playerPosition = getInput(spaces);

                //set position to x
                //red then reset back to normal colour
                spaces[playerPosition] = "\u001B[31mX\u001B[0m";

                //check for win
                xWin = checkWin(spaces, "\u001B[31mX\u001B[0m");
                if(xWin){
                    printGrid(spaces);
                    System.out.println("\nX wins!");
                    break;
                }

                //check for draw
                if (turnCounter == 9){
                    System.out.println("\nDraw!");
                    printGrid(spaces);
                    break;
                }

                //o turn
                turnCounter += 1;
                System.out.println("\nTurn: " + turnCounter);
                System.out.println("O Turn");

                //second player
                //get valid input
                //playerPosition = getInput(spaces);

                //set position to o
                //spaces[playerPosition] = "O";


                //computer
                computerPosition = computerTurn(spaces);

                //set position to o
                //cyan then reset back to normal colour
                spaces[computerPosition] = "\u001B[36mO\u001B[0m";


                // check for win
                oWin = checkWin(spaces, "\u001B[36mO\u001B[0m");
                if(oWin){
                    printGrid(spaces);
                    System.out.println("\nO wins!");
                    break;
                }
            }

            // ask player to quit or keep playing
            System.out.println("\nEnter 'quit' to stop playing or hit enter to keep playing: ");
            keepPlaying = input.nextLine();
        }
    }

    // print out the grid
    public static void printGrid(String [] spaces){
        //variables
        int i = 0, j = 0, spot = 0;

        // for loops to write out the grid
        // write 3 items from the list into 3 separate rows
        System.out.print("-------------\n");
        for(i = 0; i < 3; i++){
            for(j = 0; j < 3; j++){
                // j + (i * 3) so that it doesn't just write 123 123 123
                // i * 3 = 0, 3, 6
                spot = j + (i * 3);
                System.out.print("| " + spaces[spot] + " ");
            }
            System.out.print("|\n");
            System.out.print("-------------\n");
        }
    }

    //search through the grid to check if position is valid
    public static boolean searchGrid(String [] spaces, String position){
        //variables
        int i = 0;
        boolean result = false;

        //loop through list to find if the space is available
        for(i = 0; i < 9; i++){
            if(spaces[i].equals(position) && !position.equals("\u001B[31mX\u001B[0m") && !position.equals("\u001B[36mO\u001B[0m")){
                // result is true if the space is available
                result = true;
            }
        }

        return result;
    }

    //get user input and validate
    public static int getInput(String [] spaces){
        // variables
        int position;
        String playerInput = "";
        boolean positionCheck = false;

        // input
        Scanner input = new Scanner(System.in);

        // loop until valid position has been input
        while(!positionCheck){
            //print grid using list of spaces
            printGrid(spaces);

            System.out.println("\nPick one of the available numbered spaces: ");
            playerInput = input.next();

            //check if position is available
            positionCheck = searchGrid(spaces, playerInput);
        }

        // convert to int
        position = Integer.parseInt(playerInput);

        return position - 1;
    }

    //get computers position
    public static int computerTurn(String [] spaces){
        // variables
        int position = 0;
        String computerInput = "";
        boolean positionCheck = false;

        // loop until valid position has been chosen
        while(!positionCheck){
            //random num inclusive
            //int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
            position = (int)Math.floor(Math.random() * (9 - 1 + 1) + 1);

            System.out.println(position);

            //convert to string
            computerInput = Integer.toString(position);

            //System.out.println("Debug show random num: " + position);

            //check if position is available
            positionCheck = searchGrid(spaces, computerInput);
        }


        return position - 1;
    }

    //check if a line of 3 has been complete
    public static boolean checkWin(String [] spaces, String player){
        //variables
        int i = 0;
        boolean win = false;

        // check for horizontal line
        for(i = 0; i < 7; i += 3){
            if(spaces[i].equals(player) && spaces[i + 1].equals(player) && spaces[i + 2].equals(player)){
                // set win to true
                win = true;

            }
        }

        // check for vertical line
        for(i = 0; i < 3; i++){
            if(spaces[i].equals(player) && spaces[i + 3].equals(player) && spaces[i + 6].equals(player)){
                // set win to true
                win = true;

            }
        }

        //check for diagonal lines
        if(spaces[0].equals(player) && spaces[4].equals(player) && spaces[8].equals(player)){
            // set win to true
            win = true;

        }
        if(spaces[2].equals(player) && spaces[4].equals(player) && spaces[6].equals(player)){
            // set win to true
            win = true;

        }

        return win;
    }
}