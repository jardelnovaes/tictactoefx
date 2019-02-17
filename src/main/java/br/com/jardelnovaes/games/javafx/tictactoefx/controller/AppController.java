package br.com.jardelnovaes.games.javafx.tictactoefx.controller;

import br.com.jardelnovaes.games.javafx.tictactoefx.TicTacToeApp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;

public class AppController {
	@FXML
	private MenuItem mnuNew;

	@FXML
	private MenuItem mnuExit;

	@FXML
	private MenuItem mnuAbout;

	private TicTacToeApp app;

	@FXML
	private void initialize() {

		mnuNew.setOnAction((e) -> newGame(e));
		mnuExit.setOnAction((e) -> closeApp(e));
		mnuAbout.setOnAction((e) -> about(e));
	}

	public void setApp(final TicTacToeApp app) {
		this.app = app;
	}

	public void newGame(final ActionEvent event) {
		app.newGame();
		final FXMLLoader loader = new FXMLLoader();
		app.setPage("/view/Players.fxml", loader);

		final PlayersController controller = (PlayersController) app.getController(PlayersController.class, loader);
		controller.setApp(app);
		controller.setGame(app.getGame());

	}

	public void closeApp(final ActionEvent event) {
		Platform.exit();
	}

	public void about(final ActionEvent event) {
		final FXMLLoader loader = new FXMLLoader();
		app.setPage("/view/About.fxml", loader);

		final AboutController controller = (AboutController) app.getController(AboutController.class, loader);
		controller.setApp(app);

	}

	public void showBoard() {
		app.setPage("/view/Board.fxml");
	}
}
