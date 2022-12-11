import java.awt.datatransfer.Clipboard;
import java.io.*;
import java.util.Scanner;

public class tic_tac_toe_game {

	static public char[][] board = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
	static public int player = 1;
	static public int place;
	static public char player1_symbol;
	static public char player2_symbol;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		symbol_choosing();
		while (!game_ended()) {
		print_board();
		place = ask_move(player);
		add_board_move(player, place);
		change_player();
		}
		
	}
	
	private static void symbol_choosing() {
		Scanner s = new Scanner(System.in);
		do {
			System.out.println("Game started. Player 1 enter your symbol: ");
			player1_symbol = s.next().charAt(0);
			//s.nextLine();
			System.out.println("Player 2 enter your symbol: ");
			player2_symbol = s.next().charAt(0);
			//s.nextLine();
			if (player1_symbol == player2_symbol) {
				System.out.println("Not valid, re-enter your symbols");
			}
		} while (player1_symbol == player2_symbol);

		
	}

	private static boolean game_ended() {
		// TODO Auto-generated method stub
		
		boolean player1_win = board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][0] == player1_symbol || //Horizontal 
				board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][0] == player1_symbol || 
				board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][0] == player1_symbol||
				board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[0][0] == player1_symbol|| //Vertical
				board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[0][1] == player1_symbol|| 
				board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[0][2] == player1_symbol||
				board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == player1_symbol|| //Diagonals
				board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] == player1_symbol;
				
		boolean player2_win = board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][0] == player2_symbol || //Horizontal 
				board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][0] == player2_symbol || 
				board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][0] == player2_symbol||
				board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[0][0] == player2_symbol|| //Vertical
				board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[0][1] == player2_symbol|| 
				board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[0][2] == player2_symbol||
				board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == player2_symbol||
				board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] == player2_symbol;
		if (player1_win) {
			System.out.println("Player 1 won!");
			return true;
		} else if (player2_win) {
			System.out.println("Player 1 won!");
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
				if (board[i][j] == '1' || board[i][j] == '2' || board[i][j] == '3' || board[i][j] == '4' || board[i][j] == '5' ||
						board[i][j] == '6' || board[i][j] == '7' || board[i][j] == '8' || board[i][j] == '9') {
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
		}
		else if (player == 2) {
			player = 1;
		}
		
		
	}

	static public void print_board() {
		for (int i = 0; i < board.length; ++i) {
			System.out.println(board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
			if (i != board.length-1) {
				System.out.println("---------");
			}
		}
		
	}
	
	
	static public void add_board_move(int player_turn, int place) {
		if (player_turn == 1) {
			if (place == 1 || place == 2 || place == 3) {
				board[0][place-1] = player1_symbol;
			} else if (place == 4 || place == 5 || place == 6) {
				board[1][place-4] = player1_symbol;
			} else if (place == 7 || place == 8 || place == 9) {
				board[2][place-7] = player1_symbol;
			}
		} else if (player_turn == 2) {
			if (place == 1 || place == 2 || place == 3) {
				board[0][place-1] = player2_symbol;
			} else if (place == 4 || place == 5 || place == 6) {
				board[1][place-4] = player2_symbol;
			} else if (place == 7 || place == 8 || place == 9) {
				board[2][place-7] = player2_symbol;
			}
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
		if (move == 1 && (board[0][0] != player1_symbol || board[0][0] != player2_symbol) || move == 2 && (board[0][1] != player1_symbol || board[0][1] != player2_symbol) ||
			move == 3 && (board[0][2] != player1_symbol || board[0][2] != player2_symbol) || move == 4 && (board[1][0] != player1_symbol || board[1][0] != player2_symbol) || 
			move == 5 && (board[1][1] != player1_symbol || board[1][1] != player2_symbol) || move == 6 && (board[1][2] != player1_symbol || board[1][2] != player2_symbol) || 
			move == 7 && (board[2][0] != player1_symbol || board[2][0] != player2_symbol) || move == 8 && (board[2][1] != player1_symbol || board[2][1] != player2_symbol) || 
			move == 9 && (board[2][2] != player1_symbol || board[2][2] != player2_symbol) ) {
			return true;
		} else {
			System.out.println("Not a valid move!");
			return false;
		}
	}

}
