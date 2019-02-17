package br.com.jardelnovaes.games.javafx.tictactoefx.model;

public abstract class Player {

	protected String name;

	protected String playerId;

	private int lastLine = -1;

	private int lastColumn = -1;

	public Player(final String playerId) {
		this.playerId = playerId;
	}

	public abstract boolean attempt(final Board board, final int line, final int column);

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPlayerId() {
		return playerId;
	}

	public int getLastLine() {
		return lastLine;
	}

	public void setLastLine(final int lastLine) {
		this.lastLine = lastLine;
	}

	public int getLastColumn() {
		return lastColumn;
	}

	public void setLastColumn(final int lastColumn) {
		this.lastColumn = lastColumn;
	}

	public boolean checkAttempt(final Board board, final int line, final int column) {
		lastLine = line;
		lastColumn = column;
		final BoardItem item = board.getItems().stream()
				.filter(i -> i.getColumn() == column && i.getLine() == line)
				.findFirst().orElse(null);

		if (item.getPlayer() == null) {
			item.setPlayer(this);
			return true;
		} else {
			return false;
		}
	}

	public boolean isComputer() {
		return this instanceof ComputerPlayer;
	}

	public boolean isHuman() {
		return this instanceof HumanPlayer;
	}
}
