package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ServerViewControl implements Runnable{

	//FXML Variables
	//FXML Buttons, used for user input
	@FXML private Button topLeft;
	@FXML private Button topMiddle;
	@FXML private Button topRight;
	@FXML private Button middleLeft;
	@FXML private Button middleMiddle;
	@FXML private Button middleRight;
	@FXML private Button bottomLeft;
	@FXML private Button bottomMiddle;
	@FXML private Button bottomRight;
	
	
	//FXML ImageViews, show X or O image
	@FXML private ImageView topLeftImage;
	@FXML private ImageView topMiddleImage;
	@FXML private ImageView topRightImage;
	@FXML private ImageView middleLeftImage;
	@FXML private ImageView middleMiddleImage;
	@FXML private ImageView middleRightImage;
	@FXML private ImageView bottomLeftImage;
	@FXML private ImageView bottomMiddleImage;
	@FXML private ImageView bottomRightImage;
	
	@FXML private ImageView topWin;
	@FXML private ImageView midWin;
	@FXML private ImageView botWin;
	@FXML private ImageView leftWin;
	@FXML private ImageView middleWin;
	@FXML private ImageView rightWin;
	@FXML private ImageView dia1Win;
	@FXML private ImageView dia2Win;
	
	//FXML TextArea, used to deliver messages to user
    @FXML private TextArea messageArea;	
    
  //Variables
  	private int portNumber;
  	private ServerSocket serverSocket;
  	private Socket clientSocket;
  	private Image imageO;
  	private Image imageX;
  	private BufferedReader clientRead;
  	private PrintWriter output;
  	private int[] boardArray;
  	private Thread myThread;
    
    //FXML Methods

	@FXML void addTopLeft(ActionEvent event) {
		topLeft.setDisable(true);
		topLeftImage.setDisable(false);
		topLeftImage.setImage(imageO);
		boardArray[0] = 2;
		toMessageArea("Host Moved Top Left");
		output.println("01");
		checkWinner();
	}

	@FXML void addTopMiddle(ActionEvent event) {
		topMiddle.setDisable(true);
		topMiddleImage.setDisable(false);
		topMiddleImage.setImage(imageO);
		boardArray[1] = 2;
		toMessageArea("Host Moved Top Middle");
		output.println("02");
		checkWinner();

	}

	@FXML void addTopRight(ActionEvent event) {
		topRight.setDisable(true);
		topRightImage.setDisable(false);
		topRightImage.setImage(imageO);
		boardArray[2] = 2;
		toMessageArea("Host Moved Top Middle");
		output.println("03");
		checkWinner();

	}

	@FXML void addMiddleLeft(ActionEvent event) {
		middleLeft.setDisable(true);
		middleLeftImage.setDisable(false);
		middleLeftImage.setImage(imageO);
		boardArray[3] = 2;
		toMessageArea("Host Moved Middle Left");
		output.println("04");
		checkWinner();

	}

	@FXML void addMiddleMiddle(ActionEvent event) {
		middleMiddle.setDisable(true);
		middleMiddleImage.setDisable(false);
		middleMiddleImage.setImage(imageO);
		boardArray[4] = 2;
		toMessageArea("Host moved Middle Middle");
		output.println("05");
		checkWinner();

	}

	@FXML void addMiddleRight(ActionEvent event) {
		middleRight.setDisable(true);
		middleRightImage.setDisable(false);
		middleRightImage.setImage(imageO);
		boardArray[5] = 2;
		toMessageArea("Host moved Middle Right");
		output.println("06");
		checkWinner();

	}

	@FXML void addBottomLeft(ActionEvent event) {
		bottomLeft.setDisable(true);
		bottomLeftImage.setDisable(false);
		bottomLeftImage.setImage(imageO);
		boardArray[6] = 2;
		toMessageArea("Host moved Bottom Left");
		output.println("07");
		checkWinner();

	}

	@FXML void addBottomMiddle(ActionEvent event) {
		bottomMiddle.setDisable(true);
		bottomMiddleImage.setDisable(false);
		bottomMiddleImage.setImage(imageO);
		boardArray[7] = 2;
		toMessageArea("Host moved Bottom Middle");
		output.println("08");
		checkWinner();

	}

	@FXML void addBottomRight(ActionEvent event) {
		bottomRight.setDisable(true);
		bottomRightImage.setDisable(false);
		bottomRightImage.setImage(imageO);
		boardArray[8] = 2;
		toMessageArea("Host moved Bottom Right");
		output.println("09");
		checkWinner();

	}
	
	@FXML void host(ActionEvent event) throws IOException {		//Hosts a ServerSocket
		portNumber = 4444;
				
		try {
			serverSocket = new ServerSocket(portNumber);
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.out.println("Problem with serverSocket");
			e.printStackTrace();
		}
		toMessageArea("User connected on " + clientSocket.getInetAddress() + "\n" + 
					"on port " + clientSocket.getLocalPort());
		
		clientRead = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		output = new PrintWriter(clientSocket.getOutputStream(), true);
		
		myThread = new Thread(this);
		myThread.start();
		
	
	}
	
	@FXML void resetGame() {				//Resets Game to play again
		for (int i = 0; i < boardArray.length; i++) {
			boardArray[i] = 0;
		}
		topLeftImage.setImage(null);
		topLeftImage.setDisable(true);
		topLeft.setDisable(false);
		topMiddleImage.setImage(null);
		topMiddleImage.setDisable(true);
		topMiddle.setDisable(false);
		topRightImage.setImage(null);
		topRightImage.setDisable(true);
		topRight.setDisable(false);
		middleLeftImage.setImage(null);
		middleLeftImage.setDisable(true);
		middleLeft.setDisable(false);
		middleMiddleImage.setImage(null);
		middleMiddleImage.setDisable(true);
		middleMiddle.setDisable(false);
		middleRightImage.setImage(null);
		middleRightImage.setDisable(true);
		middleRight.setDisable(false);
		bottomLeftImage.setImage(null);
		bottomLeftImage.setDisable(true);
		bottomLeft.setDisable(false);
		bottomMiddleImage.setImage(null);
		bottomMiddleImage.setDisable(true);
		bottomMiddle.setDisable(false);
		bottomRightImage.setImage(null);
		bottomRightImage.setDisable(true);
		bottomRight.setDisable(false);
		
		 topWin.setVisible(false);
		 midWin.setVisible(false);
		 botWin.setVisible(false);
		 leftWin.setVisible(false);
		 middleWin.setVisible(false);
		 rightWin.setVisible(false);
		 dia1Win.setVisible(false);
		 dia2Win.setVisible(false);
		
		messageArea.setText("Game Reset");
		output.println("r");
	}

	//Constructor	
	public ServerViewControl() {		
		loadImages();
		newGame();
	}
	
	//Methods
	
	public void toMessageArea(String input) {	//Updates Message Area Text
		messageArea.setText(input);
	}	
	
	void newGame() {				//Setup a new array for TicTacToe 0 = null 1 = X 2 = O
		boardArray = new int[9];
		for (int element : boardArray) {
			boardArray[element] = 0;
		}
	}
	
	public void loadImages() {		//Loads Images, Xs and Os
		File oFile = new File("src/view/resources/o.png");
		imageO = new Image(oFile.toURI().toString());
		File xFile = new File("src/view/resources/x.png");
		imageX = new Image(xFile.toURI().toString());
	}

	@Override
	public void run() {		
		while (true) {
			try {
				String input = clientRead.readLine();
				System.out.println(input);
				if (input.charAt(0) == '0') {
					System.out.println("Sucess");
					process(input.charAt(1));
				} else {
					System.out.println("Not a valid move");
					System.out.println(input);					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}

	private void process(char input) {		//This takes the string from the client and turns it into a tictactoe move
		if (input == '1') {					//1 is top left, 2 is top middle ... 9 is bottom right
			topLeft.setDisable(true);
			topLeftImage.setDisable(false);
			topLeftImage.setImage(imageX);
			toMessageArea("Client Moved Top Left");
			boardArray[0] = 1;
			checkWinner();
		} else if (input == '2') {
			topMiddle.setDisable(true);
			topMiddleImage.setDisable(false);
			topMiddleImage.setImage(imageX);
			toMessageArea("Client Moved Top Middle");
			boardArray[1] = 1;
			checkWinner();
		} else if (input == '3') {
			topRight.setDisable(true);
			topRightImage.setDisable(false);
			topRightImage.setImage(imageX);
			toMessageArea("Client moved Top Right");
			boardArray[2] = 1;
			checkWinner();
		} else if (input == '4') {
			middleLeft.setDisable(true);
			middleLeftImage.setDisable(false);
			middleLeftImage.setImage(imageX);
			toMessageArea("Client moved Middle Left");
			boardArray[3] = 1;
			checkWinner();
		} else if (input == '5') {
			middleMiddle.setDisable(true);
			middleMiddleImage.setDisable(false);
			middleMiddleImage.setImage(imageX);
			toMessageArea("Client moved Middle Middle");
			boardArray[4] = 1;
			checkWinner();
		} else if (input == '6') {
			middleRight.setDisable(true);
			middleRightImage.setDisable(false);
			middleRightImage.setImage(imageX);
			toMessageArea("Client moved Middle Right");
			boardArray[5] = 1;
			checkWinner();
		} else if (input == '7') {
			bottomLeft.setDisable(true);
			bottomLeftImage.setDisable(false);
			bottomLeftImage.setImage(imageX);
			toMessageArea("Client Moved Bottom Left");
			boardArray[6] = 1;
			checkWinner();
		} else if (input == '8') {
			bottomMiddle.setDisable(true);
			bottomMiddleImage.setDisable(false);
			bottomMiddleImage.setImage(imageX);
			toMessageArea("Client moved Bottom Middle");
			boardArray[7] = 1;
			checkWinner();
		} else if (input == '9') {
			bottomRight.setDisable(true);
			bottomRightImage.setDisable(false);
			bottomRightImage.setImage(imageX);
			toMessageArea("Client moved Bottom Right");
			boardArray[8] = 1;
			checkWinner();
		}
		
	}
	
	public void checkWinner() {	//Checks to see if anyone has won the game
		checkXwin();
		checkOwin();
		checkDraw();
	}
	
	public void checkXwin() { //Checks to see if the X player has won
		if ((boardArray[0] == 1)&&(boardArray[1] == 1)&& (boardArray[2] == 1)) {
			xWins();
			topWin.setVisible(true);
			output.println("l1");
			} 
		else if ((boardArray[3] == 1) &&(boardArray[4] == 1) && (boardArray[5] == 1)) {
			xWins(); 
			midWin.setVisible(true);
			output.println("l2");
		}
		else if ((boardArray[6] == 1) &&(boardArray[7] == 1) && (boardArray[8] == 1)) {
			xWins();	
			botWin.setVisible(true);
			output.println("l3");
			} 
		else if ((boardArray[0] == 1) &&(boardArray[3] == 1) && (boardArray[6] == 1)) {
			xWins(); 
			leftWin.setVisible(true);
			output.println("l4");
			}
		else if ((boardArray[1] == 1) &&(boardArray[4] == 1) && (boardArray[7] == 1)) {
			xWins(); 
			middleWin.setVisible(true);
			output.println("l5");
			}
		else if ((boardArray[2] == 1) &&(boardArray[5] == 1) && (boardArray[8] == 1)) {
			xWins(); 
			rightWin.setVisible(true);
			output.println("l6");
			}
		else if ((boardArray[0] == 1) &&(boardArray[4] == 1) && (boardArray[8] == 1)) {
			xWins(); 
			dia1Win.setVisible(true);
			output.println("l7");
			}
		else if ((boardArray[2] == 1) &&(boardArray[4] == 1) && (boardArray[6] == 1)) {
			xWins(); 
			dia2Win.setVisible(true);
			output.println("l8");
			}
		
	}
	
	public void checkOwin() { //Checks to see if the O player has won
		if ((boardArray[0] == 2)&&(boardArray[1] == 2)&& (boardArray[2] == 2)) {			
			oWins();	
			topWin.setVisible(true);
			output.println("l1");
			} 
		else if ((boardArray[3] == 2) &&(boardArray[4] == 2) && (boardArray[5] == 2)) {
			oWins(); 
			midWin.setVisible(true);
			output.println("l2");
			}
		else if ((boardArray[6] == 2) &&(boardArray[7] == 2) && (boardArray[8] == 2)) {
			oWins();	
			botWin.setVisible(true);
			output.println("l3");
			} 
		else if ((boardArray[0] == 2) &&(boardArray[3] == 2) && (boardArray[6] == 2)) {
			oWins(); 
			leftWin.setVisible(true);
			output.println("l4");
			}
		else if ((boardArray[1] == 2) &&(boardArray[4] == 2) && (boardArray[7] == 2)) {
			oWins(); 
			middleWin.setVisible(true);
			output.println("l5");
			}
		else if ((boardArray[2] == 2) &&(boardArray[5] == 2) && (boardArray[8] == 2)) {
			oWins(); 
			rightWin.setVisible(true);
			output.println("l6");
			}
		else if ((boardArray[0] == 2) &&(boardArray[4] == 2) && (boardArray[8] == 2)) {
			oWins(); 
			dia1Win.setVisible(true);
			output.println("l7");
			}
		else if ((boardArray[2] == 2) &&(boardArray[4] == 2) && (boardArray[6] == 2)) {
			oWins(); 
			dia2Win.setVisible(true);
			output.println("l8");
			}
	}
	
	public void checkDraw() {
		int total = 0;
		for (int i = 0; i < boardArray.length; i++) {
			if (boardArray[i] != 0) {
				total++;
			}
		}
		if (total >= 9) {
			toMessageArea("Game is a draw");
			output.println("Game is a draw!");
		}
	}
	
	public void xWins() {
		toMessageArea("Client Player Has Won The Game!!!");
		output.println("Client Player Has Won The Game!!!");
	}
	
	public void oWins() {
		toMessageArea("Host Player Has Won The Game!!!");
		output.println("Host Player Has Won The Game!!!");
	}

}
