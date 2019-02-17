package br.com.jardelnovaes.games.javafx.tictactoefx.controller;

import br.com.jardelnovaes.games.javafx.tictactoefx.StringUtils;
import br.com.jardelnovaes.games.javafx.tictactoefx.TicTacToeApp;
import br.com.jardelnovaes.games.javafx.tictactoefx.model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

public class GameController {

	@FXML
	private TitledPane titledPane;

	@FXML
	private GridPane gridBoard;

	private Game game;

	private TicTacToeApp app;

	public void setGame(final Game game) {
		this.game = game;
	}

	public void setApp(final TicTacToeApp app) {
		this.app = app;
	}

	protected void setTitle() {
		titledPane.setText(String.format("%s vs %s - %s is playing", game.getPlayer1().getName(),
				game.getPlayer2().getName(), game.getCurrentPlayer().getName()));
	}

	@FXML
	public void play(final ActionEvent e) {
		final Button button = (Button) e.getSource();
		if (!game.isFinished() && !StringUtils.isEmpty(button.getText())) {
			app.error("Please choose other one!");
		} else {
			final String[] pos = button.getId().split("_");
			final int line = Integer.parseInt(pos[0]);
			final int column = Integer.parseInt(pos[1]);

			play(line, column);

		}

	}

	public void play(final int line, final int column) {
		if (game.isFinished()) {
			app.error("The game was finished!");

		} else {
			if (game.getCurrentPlayer().attempt(game.getBoard(), line, column)) {
				final String buttonId = "#" + game.getCurrentPlayer().getLastLine() + "_"
						+ game.getCurrentPlayer().getLastColumn();
				final Button button = (Button) gridBoard.lookup(buttonId);
				button.setText(game.getCurrentPlayer().getPlayerId());

				if (game.checkWinner()) {
					if (game.getCurrentPlayer().isHuman()) {
						app.info("Congrats " + game.getCurrentPlayer().getName() + "! You won!");
					} else {
						app.error(game.getCurrentPlayer().getName() + " you lose!");
					}

				} else {
					if (game.isFullBoard()) {
						app.error("Nobody won! Try again!");
					} else {
						game.changePlayer();
						setTitle();
						if (game.getCurrentPlayer().isComputer()) {
							playAsComputer();

						}
					}
				}
			}
		}

	}

	public void playAsComputer() {
		gridBoard.setDisable(true);
		play(0, 0);
		gridBoard.setDisable(false);
	}

	public void clearBoard() {

		if (gridBoard != null) {
			gridBoard.getChildren().forEach(i -> {
				if (i instanceof Button) {
					((Button) i).setText("");
				}
			});
		}
	}

}
