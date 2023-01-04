import java.awt.datatransfer.Clipboard;
import java.lang.Math;
import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class ticTacToeGame {
    /** 
     * This class is made for a tic tac toe game, where two players play and choose their own symbols.
     */

    static public char[][] board = { { '1', '2', '3' }, { '4', '5', '6' }, { '7', '8', '9' } };
    static public int player = 1; //This variable is either 1 or 2 depending on the turn of the user.
    static public int place;
    static public char player1Symbol;
    static public char player2Symbol;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        boolean gameStarted = checkIfGameExists();
        if (!gameStarted) {
            loadGame();
        } else {
            symbol_choosing();
        }
        
        while (!game_ended()) {
            print_board();
            place = ask_move(player);
            add_board_move(player, place);
            change_player();
            saveGameState();
        }

    }

    private static void loadGame() {
        // TODO Auto-generated method stub
        File gameState = new File("data/gameState.csv");
        
        try {
            Scanner stateScanner = new Scanner(gameState);
            player = Integer.parseInt(stateScanner.nextLine());
            player1Symbol = stateScanner.nextLine().charAt(0);
            player2Symbol = stateScanner.nextLine().charAt(0);
            
            for(int i = 0; i < board.length; ++i) {
                for(int j = 0; j < board[0].length; ++j) {
                    board[i][j] = stateScanner.nextLine().charAt(0);
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static boolean checkIfGameExists() {
        // TODO Auto-generated method stub
        File gameState = new File("data/gameState.csv");
        
        if (gameState.exists()) {
            return true;
        } else {
            return false;
        }
        
    }

    public static void saveGameState() {
        // TODO Auto-generated method stub
        try {
            FileWriter gameState = new FileWriter("data/gameState.csv");
            gameState.write(player + "\n");
            gameState.write(player1Symbol + "\n");
            gameState.write(player2Symbol + "\n");
            for(int i = 0; i < board.length; ++i) {
                for(int j = 0; j < board[0].length; ++j) {
                    gameState.write(board[i][j] + "\n");
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void symbol_choosing() {
        Scanner s = new Scanner(System.in);
        System.out.println("Game started. Player 1 enter your symbol: ");
        player1Symbol = s.next().charAt(0); //Symbol choice is taken as the first character entered by player 1.
        do {
            // s.nextLine();
            System.out.println("Player 2 enter your symbol: ");
            player2Symbol = s.next().charAt(0); //Symbol choice is taken as the first character entered by player 1.
            // s.nextLine();
            if (player1Symbol == player2Symbol) {
                System.out.println("This symbol is taken. Player 2, re-enter your symbol:");
            }
        } while (player1Symbol == player2Symbol);

    }

    private static boolean game_ended() {
        // TODO Auto-generated method stub

        boolean player1_win = board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][0] == player1Symbol
                || // Horizontal
                board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][0] == player1Symbol
                || board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][0] == player1Symbol
                || board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[0][0] == player1Symbol || // Vertical
                board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[0][1] == player1Symbol
                || board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[0][2] == player1Symbol
                || board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == player1Symbol || // Diagonals
                board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] == player1Symbol;

        boolean player2_win = board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][0] == player2Symbol
                || // Horizontal
                board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][0] == player2Symbol
                || board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][0] == player2Symbol
                || board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[0][0] == player2Symbol || // Vertical
                board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[0][1] == player2Symbol
                || board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[0][2] == player2Symbol
                || board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == player2Symbol
                || board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] == player2Symbol;
        if (player1_win) {
            System.out.println("Player 1 won!");
            return true;
        } else if (player2_win) {
            System.out.println("Player 2 won!");
            return true;
        } else {
            if (board_full()) {
                System.out.println("It is a tie!");
                return true;
            } else {
                return false;
            }
        }

    }

    private static boolean board_full() {
        // TODO Auto-generated method stub
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == '1' || board[i][j] == '2' || board[i][j] == '3' || board[i][j] == '4'
                        || board[i][j] == '5' || board[i][j] == '6' || board[i][j] == '7' || board[i][j] == '8'
                        || board[i][j] == '9') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void change_player() {
        // TODO Auto-generated method stub
        if (player == 1) {
            player = 2;
        } else if (player == 2) {
            player = 1;
        }

    }

    static public void print_board() {
        for (int i = 0; i < board.length; ++i) {
            System.out.println(board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i != board.length - 1) {
                System.out.println("---------");
            }
        }

    }

    static public void add_board_move(int player_turn, int place) {
        int row_index = (int) Math.ceil(((double)place)/3) - 1;
        int column_index = (place%3) -1;
        if (player_turn == 1) {
            board[row_index][column_index] = player1Symbol;
//            if (place == 1 || place == 2 || place == 3) {
//                board[0][place - 1] = player1_symbol;
//            } else if (place == 4 || place == 5 || place == 6) {
//                board[1][place - 4] = player1_symbol;
//            } else if (place == 7 || place == 8 || place == 9) {
//                board[2][place - 7] = player1_symbol;
//            }
        } else if (player_turn == 2) {
            board[row_index][column_index] = player2Symbol;
//            if (place == 1 || place == 2 || place == 3) {
//                board[0][place - 1] = player2_symbol;
//            } else if (place == 4 || place == 5 || place == 6) {
//                board[1][place - 4] = player2_symbol;
//            } else if (place == 7 || place == 8 || place == 9) {
//                board[2][place - 7] = player2_symbol;
//            }
        }
    }

    static public int ask_move(int player) {
        Scanner s = new Scanner(System.in);
        int move;

        do {
            System.out.println("Player " + player + " please enter your move: ");
            move = s.nextInt();
            s.nextLine();
        } while (!valid_move(move));

        return move;

    }

    private static boolean valid_move(int move) {
        // TODO Auto-generated method stub
        if (move == 1 && (board[0][0] != player1Symbol || board[0][0] != player2Symbol)
                || move == 2 && (board[0][1] != player1Symbol || board[0][1] != player2Symbol)
                || move == 3 && (board[0][2] != player1Symbol || board[0][2] != player2Symbol)
                || move == 4 && (board[1][0] != player1Symbol || board[1][0] != player2Symbol)
                || move == 5 && (board[1][1] != player1Symbol || board[1][1] != player2Symbol)
                || move == 6 && (board[1][2] != player1Symbol || board[1][2] != player2Symbol)
                || move == 7 && (board[2][0] != player1Symbol || board[2][0] != player2Symbol)
                || move == 8 && (board[2][1] != player1Symbol || board[2][1] != player2Symbol)
                || move == 9 && (board[2][2] != player1Symbol || board[2][2] != player2Symbol)) {
            return true;
        } else {
            System.out.println("Not a valid move!");
            return false;
        }
    }

}
