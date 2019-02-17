package br.com.jardelnovaes.games.javafx.tictactoefx.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private final List<BoardItem> items;

	public Board() {
		items = new ArrayList<>();
		init();
	}

	public int getPosition(final int[] attempt) {
		return 0;
	}

	public List<BoardItem> getItems() {
		return items;
	}

	private void init() {
		items.clear();
		for (int line = 0; line < 3; line++) {
			for (int column = 0; column < 3; column++) {
				items.add(new BoardItem(line, column));
			}
		}
	}

	public boolean checkLines(final Player currentPlayer) {
		boolean isWinner = false;
		for (int pos = 0; pos < 3; pos++) {
			final int line = pos;
			isWinner = items.stream()
					.filter(i -> i.getPlayer() == currentPlayer && i.getLine() == line)
					.count() == 3;
			if (isWinner) {
				break;
			}
		}
		return isWinner;
	}

	public boolean checkColumns(final Player currentPlayer) {
		boolean isWinner = false;
		for (int pos = 0; pos < 3; pos++) {
			final int column = pos;
			isWinner = items.stream()
					.filter(i -> i.getPlayer() == currentPlayer && i.getColumn() == column)
					.count() == 3;
			if (isWinner) {
				break;
			}
		}
		return isWinner;
	}

	public boolean checkDiagonal(final Player currentPlayer) {
		boolean isWinner = items.stream()
				.filter(i -> i.getPlayer() == currentPlayer
						&& i.getColumn() == 0 && i.getLine() == 0
						&& i.getColumn() == 1 && i.getLine() == 1
						&& i.getColumn() == 2 && i.getLine() == 2)
				.count() == 3;
		if (!isWinner) {
			isWinner = items.stream()
					.filter(i -> i.getPlayer() == currentPlayer
							&& i.getColumn() == 0 && i.getLine() == 2
							&& i.getColumn() == 1 && i.getLine() == 1
							&& i.getColumn() == 2 && i.getLine() == 0)
					.count() == 3;
		}
		return isWinner;
	}
}
