import java.util.*;

public class TicTacToe {
    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();
    public static void main(String[] args) {

        char [] [] gameBoard = {{' ', '|' , ' ', '|', ' '},
                                {'-', '+' , '-', '+', '-'} ,
                                {' ', '|' , ' ', '|', ' '},
                                {'-', '+' , '-', '+', '-'} ,
                                {' ', '|' , ' ', '|', ' '}};

        printGameBoard(gameBoard);


        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your placements 1 - 9 : ");
            int playerPosition = sc.nextInt();
            while (playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)){
                System.out.println("Position taken! \n Enter a new position");
                playerPosition = sc.nextInt();
            }

            placePiece(gameBoard,playerPosition,"player");
            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuPosition = rand.nextInt(9) +1;
            while (playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)){
                System.out.println("Position taken! \n Enter a new position");
                cpuPosition = rand.nextInt(9) +1;
            }
            placePiece(gameBoard,cpuPosition,"CPU");

            printGameBoard(gameBoard);

            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

        }

        
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row:gameBoard ) {
            for (char column: row) {
                System.out.print(column);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard , int position, String user) {
        char symbol = ' ';
        if (user.equals("player")) {
          symbol = 'X';
          playerPositions.add(position);
        }else if(user.equals("CPU")){
            symbol ='O';
            cpuPositions.add(position);
        }

        switch (position) {
            case 1: gameBoard[0][0] = symbol;
                break;
            case 2: gameBoard[0][2] = symbol;
                break;
            case 3: gameBoard[0][4] = symbol;
                break;
            case 4: gameBoard[2][0] = symbol;
                break;
            case 5: gameBoard[2][2] = symbol;
                break;
            case 6: gameBoard[2][4] = symbol;
                break;
            case 7: gameBoard[4][0] = symbol;
                break;
            case 8: gameBoard[4][2] = symbol;
                break;
            case 9: gameBoard[4][4] = symbol;
                break;
            default:
                break;

        }
    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1,2,3);
        List middleRow = Arrays.asList(4,5,6);
        List bottomRow = Arrays.asList(7,8,9);

        List leftCol = Arrays.asList(1,4,7);
        List middleCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);

        List leftDiagonal = Arrays.asList(1,5,9);
        List rightDiagonal = Arrays.asList(3,5,7);

        List<List> winningCondition = new ArrayList<>();
        winningCondition.add(topRow);
        winningCondition.add(middleRow);
        winningCondition.add(bottomRow);
        winningCondition.add(leftCol);
        winningCondition.add(middleCol);
        winningCondition.add(rightCol);
        winningCondition.add(leftDiagonal);
        winningCondition.add(rightDiagonal);

        for (List l:winningCondition) {
            if (playerPositions.containsAll(l)){
                return "CONGRATULATIONS YOU WON";
            }else if(cpuPositions.containsAll(l)) {
                return "CPU WINS ! SORRY :(";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "CAT!";
            }
        }

        return "";
    }
}
