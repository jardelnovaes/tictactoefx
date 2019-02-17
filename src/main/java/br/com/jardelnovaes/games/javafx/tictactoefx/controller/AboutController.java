package br.com.jardelnovaes.games.javafx.tictactoefx.controller;

import br.com.jardelnovaes.games.javafx.tictactoefx.TicTacToeApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class AboutController {
	@FXML
	private Button btnClose;

	private TicTacToeApp app;

	@FXML
	private void initialize() {
		btnClose.setOnAction((e) -> app.closePage());
	}

	@FXML
	public void navigate(final ActionEvent e) {
		final Hyperlink link = (Hyperlink) e.getSource();
		app.navigate(link.getText());
	}

	public void setApp(final TicTacToeApp app) {
		this.app = app;
	}
}
