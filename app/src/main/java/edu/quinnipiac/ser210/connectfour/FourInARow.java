package edu.quinnipiac.ser210.connectfour;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import edu.quinnipiac.ser210.connectfour.IGame;

/**
 * TicTacToe class implements the interface
 * @author relkharboutly
 * @author hhanif
 * @date 2/20/2022
 */
public class FourInARow implements IGame {

	// The game board and the game status
	private static final int ROWS = 6, COLS = 6; // number of rows and columns
	private int[][] board = new int[ROWS][COLS]; // game board in 2D array

	/**
	 * clear board and set current player   
	 */
	public FourInARow(){

	}
	
	//clears board
	@Override
	public void clearBoard() {
		// TODO Auto-generated method stub
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				board[ROWS][COLS] = EMPTY;
			}
		}
	}

	//sets move for player and computer without overwriting another move
	@Override
	public void setMove(int player, int location) {
		// TODO Auto-generated method stub
		int row = ((location/6)%6);
		int col = (location%6);
		if (board[row][col] == EMPTY) {
			board[row][col] = player;
		} else if (board[row][col] != EMPTY && player == FourInARow.RED){
			getComputerMove();
		} else {
			System.out.println("Please enter a valid move.");
			Scanner input = new Scanner(System.in);
			setMove(FourInARow.BLUE, input.nextInt());
		}

	}

	//generates best move for the computer 
	@Override
	public int getComputerMove() {
		// TODO Auto-generated method stub
		int min = 0;
		int max = 35;
		int range = (max - min) + 1;
		int bestMove = (int) (Math.random()*range) + min;
		return bestMove;
	}

	//checks for winner horizontally, vertically, and diagonally
	//if no winner then the game is either continuing or their is a tie
	@Override
	public int checkForWinner() {
		// TODO Auto-generated method stub

		int tieCounter = 0;

		for (int row = 0; row < board.length - 3; row++) {
			for (int col = 0; col < board[0].length - 3; col++) {
				if (board[row][col] == board[row + 1][col + 1] && board[row][col] == board[row + 2][col + 2]
						&& board[row][col] == board[row + 3][col + 3]) {
					if (board[row][col] == BLUE) {
						return BLUE_WON;
					} else if (board[row][col] == RED) {
						return RED_WON;
					}
				}
			}
		}

		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length - 3; col++) {
				if (board[row][col] == board[row][col + 1] && board[row][col] == board[row][col + 2]
						&& board[row][col] == board[row][col + 3]) {
					if (board[row][col] == BLUE) {
						return BLUE_WON;
					} else if (board[row][col] == RED) {
						return RED_WON;
					}
				}
				if (board[row][col] != EMPTY) {
					tieCounter++;
				}
				if (tieCounter == 36) {
					return TIE;
				}
			}
		}

		for (int row = 0; row < board.length - 3; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col] == board[row + 1][col] && board[row][col] == board[row + 2][col]
						&& board[row][col] == board[row + 3][col]) {
					if (board[row][col] == BLUE) {
						return BLUE_WON;
					} else if (board[row][col] == RED) {
						return RED;
					}
				}
			}
		}

		return 0;
	}


	public int getPosition(int pos) {
		return board[(pos/6)][pos%6];
	}

	/**
	 *  Print the game board 
	 */
	public  void printBoard() {

		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				printCell(board[row][col]); // print each of the cells
				if (col != COLS - 1) {
					System.out.print("|");   // print vertical partition
				}
			}
			System.out.println();
			if (row != ROWS - 1) {
				System.out.println("-----------"); // print horizontal partition
			}
		}
		System.out.println(); 
	}

	/**
	 * Print a cell with the specified "content" 
	 * @param content either BLUE, RED or EMPTY
	 */
	public void printCell(int content) {
		switch (content) {
		case EMPTY:  System.out.print("   "); break;
		case BLUE: System.out.print(" B "); break;
		case RED:  System.out.print(" R "); break;
		}
	}

}
