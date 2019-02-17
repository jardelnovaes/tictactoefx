package br.com.jardelnovaes.games.javafx.tictactoefx.model;

public class BoardItem {
	private int column;
	private int line;
	private Player player;

	public BoardItem() {
		// Do nothing
	}

	public BoardItem(final int line, final int column) {
		this.line = line;
		this.column = column;
	}

	public int getColumn() {
		return column;
	}

	public int getLine() {
		return line;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(final Player player) {
		this.player = player;
	}
}
