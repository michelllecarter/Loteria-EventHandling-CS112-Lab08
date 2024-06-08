//package replit;

//IMPORTED PACKAGES
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main extends Application {
		//CONSTANTS
		private static final LoteriaCard[] LOTERIA_CARDS = {
						new LoteriaCard("Las matematicas", "1.png", 1),
						new LoteriaCard("Las ciencias", "2.png", 2),
						new LoteriaCard("La Tecnología", "8.png", 8),
						new LoteriaCard("La ingeniería", "9.png", 9),
		};

		//CLASS-LEVEL VARIABLES; DECLARE GUI COMPONENTS (STEP 1)
		private Label messageLabel;
		private ImageView cardImageView;
		private Button drawCardButton;
		private ProgressBar gameProgressBar;
		private int count = 0;
		private LoteriaCard[] shuffledCards = Main.shuffleCards(LOTERIA_CARDS);

		private static LoteriaCard[] shuffleCards(LoteriaCard[] cards){
				LoteriaCard[] copy = cards.clone();
				List<LoteriaCard> list = Arrays.asList(copy);
				Collections.shuffle(list);
				list.toArray(copy);
				return copy;
		}

	//GUI methods
		public static void main(String[] args) {
				launch(args);
		}

		@Override
		public void start(Stage primaryStage) throws Exception {
				//INSTANTIATE COMPONENTS (STEP 2)
				Label titleLabel = new Label();
				cardImageView = new ImageView();
				messageLabel = new Label();
				drawCardButton = new Button();
				gameProgressBar = new ProgressBar();
				//0.0 allows us to set the progress bar to 0%

				//SET COMPONENT PROPERTIES (STEP 3)
				titleLabel.setText("Welcome to EChALE STEM Loteria!");
				titleLabel.setFont(new Font(18.0));
				titleLabel.setTextAlignment(TextAlignment.CENTER);

				LoteriaCard tempCard = new LoteriaCard();
				cardImageView.setImage(tempCard.getImage());
				cardImageView.setFitWidth(300);
				cardImageView.setPreserveRatio(true);

				messageLabel.setText("Click the button to randomly draw a card. The progress bar will indicate how far we're into the game.");
				messageLabel.setWrapText(true);
				messageLabel.setTextAlignment(TextAlignment.CENTER);

				drawCardButton.setText("Draw Random Card");

				gameProgressBar.setProgress(0.0);


				//SETUP LAYOUT (STEP 4)
				VBox mainLayout = new VBox();

				mainLayout.getChildren().addAll(titleLabel, cardImageView, messageLabel, drawCardButton, gameProgressBar);
				mainLayout.setAlignment(Pos.CENTER);

				//ADD EVENT HANDLING (STEP 6)
				drawCardButton.setOnAction(new EventHandler<ActionEvent>() {
							 @Override
							 public void handle(ActionEvent event) {
									 //int cardNum = (int) (Math.random() * LOTERIA_CARDS.length); (random 0-3)
									 //System.out.println("Drew new card, index:" + cardNum); (checking cards are drawn)
									 if(count == LOTERIA_CARDS.length) {
											 //end game condition
											 gameProgressBar.setStyle("-fx-accent: red");
											 drawCardButton.setDisable(true);
											 messageLabel.setText("GAME OVER. No more cards! Exit and run program again to reset ^_^");
											 cardImageView.setImage(new LoteriaCard().getImage());
											 // ^anonymous object
									 } else {
											 LoteriaCard drawnCard = shuffledCards[count];
											 count ++;
											 //System.out.println("Something happened #" + count);

											 //change GUI based on card drawn
											 gameProgressBar.setProgress(count/(double)LOTERIA_CARDS.length);
											 cardImageView.setImage(drawnCard.getImage());
											 messageLabel.setText(drawnCard.getCardName());
									 }
							 }
					 }
				);

				//SETUP SCENE AND SHOW (STEP 5)
				Scene scene = new Scene(mainLayout, 350, 500);
				primaryStage.setScene(scene);
				primaryStage.setTitle("EChALE STEM Loteria!");
				primaryStage.show();
		}

}
