package br.com.jardelnovaes.games.javafx.tictactoefx;

import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.jardelnovaes.games.javafx.tictactoefx.controller.AppController;
import br.com.jardelnovaes.games.javafx.tictactoefx.model.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TicTacToeApp extends Application {
	private static final Logger LOG = LoggerFactory.getLogger(TicTacToeApp.class);

	private Stage primaryStage;

	private BorderPane rootLayout;

	private Game game;

	private final HashMap<String, Pane> screenMap = new HashMap<>();

	private final HashMap<Class<?>, Object> controllerMap = new HashMap<>();

	public static void main(final String[] args) {
		LOG.info("Starting Tic Tac Toe FX App");
		Application.launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		this.primaryStage = stage;
		this.primaryStage.setTitle("Tic Tac Toe");
		initRootLayout();
	}

	private void initRootLayout() {
		try {
			setUserAgentStylesheet(STYLESHEET_CASPIAN);
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TicTacToeApp.class.getResource("/view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			final AppController controller = loader.getController();
			controller.setApp(this);

			final Scene scene = new Scene(rootLayout);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (final IOException e) {
			LOG.error("Cannot initialize root layout", e);
		}
	}

	public void closePage() {
		rootLayout.setCenter(null);
	}

	public Pane getScreen(final String resourcePath) {
		return screenMap.get(resourcePath);
	}

	public Object getController(final Class<?> clazz, final FXMLLoader loader) {
		Object controller = controllerMap.get(clazz);
		if (controller == null) {
			controller = loader.getController();
			controllerMap.put(clazz, controller);
		}
		return controller;
	}

	public void setPage(final String resourcePath) {
		setPage(resourcePath, null);
	}

	public void setPage(final String resourcePath, FXMLLoader loader) {
		try {
			if (loader == null) {
				loader = new FXMLLoader();
			}

			Pane page = getScreen(resourcePath);
			if (page == null) {
				loader.setLocation(TicTacToeApp.class.getResource(resourcePath));
				page = loader.load();
				screenMap.put(resourcePath, page);
			}
			rootLayout.setCenter(page);
		} catch (final IOException e) {
			LOG.error("Cannot set page {}", resourcePath, e);
		}
	}

	public Stage setDialogPage(final String resourcePath, final boolean isAutoShowAndWait, FXMLLoader loader) {
		final Stage dialogStage = new Stage();
		try {
			if (loader == null) {
				loader = new FXMLLoader();
			}

			loader.setLocation(TicTacToeApp.class.getResource(resourcePath));
			final AnchorPane page = (AnchorPane) loader.load();

			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			final Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			if (isAutoShowAndWait) {
				dialogStage.showAndWait();
			}
		} catch (final IOException e) {
			LOG.error("Cannot set dialog page {}", resourcePath, e);
		}

		return dialogStage;
	}

	public void sleep(final int secounds) {
		try {
			Thread.sleep(secounds * 1000);
		} catch (final InterruptedException e) {
			LOG.error("Invalid sleep", e);
		}
	}

	public void newGame() {
		game = new Game();
	}

	public Game getGame() {
		return game;
	}

	public void error(final String text) {
		final Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(text);

		alert.showAndWait();

	}

	public void info(final String text) {
		final Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText(text);

		alert.showAndWait();

	}

	public void navigate(final String uri) {
		getHostServices().showDocument(uri);
	}

}