package br.com.jardelnovaes.games.javafx.tictactoefx.model;

public class Game {
	private Board board;
	private Player player1;
	private Player player2;

	private Player currentPlayer;

	private boolean finished;

	public Board getBoard() {
		return board;
	}

	public void setBoard(final Board board) {
		this.board = board;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(final Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(final Player player2) {
		this.player2 = player2;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(final Player player) {
		this.currentPlayer = player;
	}

	public boolean isFinished() {
		return finished;
	}

	public void start() {
		board = new Board();
		currentPlayer = player1;
	}

	public void changePlayer() {
		if (currentPlayer == player1) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}

	}

	public boolean checkWinner() {
		boolean isWinner = false;
		isWinner = board.checkLines(currentPlayer);

		if (!isWinner) {
			isWinner = board.checkColumns(currentPlayer);
		}

		if (!isWinner) {
			isWinner = board.checkDiagonal(currentPlayer);
		}

		finished = isWinner;
		return isWinner;

	}

	public boolean isFullBoard() {
		finished = board.getItems().stream().filter(i -> i.getPlayer() == null).count() == 0;
		return finished;
	}

}
