package br.com.jardelnovaes.games.javafx.tictactoefx.model;

public class HumanPlayer extends Player {

	public HumanPlayer(final String playerId) {
		super(playerId);

	}

	@Override
	public boolean attempt(final Board board, final int line, final int column) {
		return checkAttempt(board, line, column);
	}

}
