package br.com.jardelnovaes.games.javafx.tictactoefx.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.jardelnovaes.games.javafx.tictactoefx.StringUtils;
import br.com.jardelnovaes.games.javafx.tictactoefx.TicTacToeApp;
import br.com.jardelnovaes.games.javafx.tictactoefx.model.ComputerPlayer;
import br.com.jardelnovaes.games.javafx.tictactoefx.model.Game;
import br.com.jardelnovaes.games.javafx.tictactoefx.model.HumanPlayer;
import br.com.jardelnovaes.games.javafx.tictactoefx.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class PlayersController {

	private static final Logger LOG = LoggerFactory.getLogger(PlayersController.class);

	@FXML
	private CheckBox chkP1Computer;

	@FXML
	private TextField txtP1Name;

	@FXML
	private CheckBox chkP2Computer;

	@FXML
	private TextField txtP2Name;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnClose;

	private Game game;

	private TicTacToeApp app;

	@FXML
	private void initialize() {
		btnSave.setOnAction((e) -> save(e));
		btnClose.setOnAction((e) -> app.closePage());

		chkP1Computer.setOnAction((e) -> checkboxClick(e));
		chkP2Computer.setOnAction((e) -> checkboxClick(e));
	}

	private void checkboxClick(final ActionEvent e) {
		if (e.getSource() == chkP1Computer) {
			txtP1Name.setText("");
			txtP1Name.setDisable(chkP1Computer.isSelected());
		}

		if (e.getSource() == chkP2Computer) {
			txtP2Name.setText("");
			txtP2Name.setDisable(chkP2Computer.isSelected());
		}
	}

	public void setGame(final Game game) {
		this.game = game;
	}

	public void setApp(final TicTacToeApp app) {
		this.app = app;
	}

	public void save(final ActionEvent event) {
		final Player player1 = createPlayer(chkP1Computer, txtP1Name, "X");
		final Player player2 = createPlayer(chkP2Computer, txtP2Name, "O");

		game.setPlayer1(player1);
		game.setPlayer2(player2);
		try {
			validate();
		} catch (final Exception e) {
			app.error(e.getMessage());
			LOG.error("Invalid players", e);
			return;
		}

		final FXMLLoader loader = new FXMLLoader();
		app.setPage("/view/Board.fxml", loader);

		game.start();

		final GameController controller = (GameController) app.getController(GameController.class, loader);
		controller.setApp(app);
		controller.setGame(app.getGame());
		controller.clearBoard();
		controller.setTitle();

		if (game.getCurrentPlayer().isComputer()) {
			controller.playAsComputer();
		}

	}

	private void validate() throws Exception {
		if (game.getPlayer1().isComputer() && game.getPlayer2().isComputer()) {
			throw new Exception("Just  one player can be a Computer Player!");
		}

		validatePlayer(game.getPlayer1(), 1);
		validatePlayer(game.getPlayer2(), 2);
	}

	private void validatePlayer(final Player player, final int id) throws Exception {
		if (player.isHuman() && StringUtils.isEmpty(player.getName())) {
			throw new Exception("Player " + id + " is not a computer player, it needs a name!");
		}
	}

	private Player createPlayer(final CheckBox chkComputer, final TextField txtName, final String playerId) {
		final Player player;
		if (chkComputer.isSelected()) {
			player = new ComputerPlayer(playerId);
			player.setName("Computer");
		} else {
			player = new HumanPlayer(playerId);
			player.setName(txtName.getText());
		}
		return player;
	}
}
