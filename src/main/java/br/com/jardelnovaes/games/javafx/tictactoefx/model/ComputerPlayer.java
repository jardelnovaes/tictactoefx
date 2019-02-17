package br.com.jardelnovaes.games.javafx.tictactoefx.model;

public class ComputerPlayer extends Player {

	public ComputerPlayer(final String playerId) {
		super(playerId);

	}

	@Override
	public boolean attempt(final Board board, final int line, final int column) {
		int lineToTry = 1;
		int columnToTry = 1;
		boolean ret = true;
		// It tries the center
		if (!checkAttempt(board, lineToTry, columnToTry)) {
			lineToTry = 0;
			columnToTry = 0;
			// It tries the corners
			if (!checkAttempt(board, lineToTry, columnToTry)) {
				columnToTry = 2;
				if (!checkAttempt(board, lineToTry, columnToTry)) {
					lineToTry = 2;
					if (!checkAttempt(board, lineToTry, columnToTry)) {
						columnToTry = 0;
						if (!checkAttempt(board, lineToTry, columnToTry)) {
							ret = attemptFreeSpace(board);
						}
					}
				}
			}
		}

		return ret;
	}

	public boolean attemptFreeSpace(final Board board) {
		boolean ret = false;
		for (int line = 0; line < 3; line++) {
			for (int column = 0; column < 3; column++) {
				if (checkAttempt(board, line, column)) {
					ret = true;
					break;
				}
			}
			if (ret) {
				break;
			}
		}
		return ret;
	}
}
