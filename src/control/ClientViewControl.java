package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ClientViewControl implements Runnable{
	
	//FXML Variables
	
	@FXML private Button topLeft;
	@FXML private Button topMiddle;
	@FXML private Button topRight;
	@FXML private Button middleLeft;
	@FXML private Button middleMiddle;
	@FXML private Button middleRight;
	@FXML private Button bottomLeft;
	@FXML private Button bottomMiddle;
	@FXML private Button bottomRight;
	
	@FXML private ImageView topLeftImage;
	@FXML private ImageView topMiddleImage;
	@FXML private ImageView topRightImage;
	@FXML private ImageView middleLeftImage;
	@FXML private ImageView middleMiddleImage;
	@FXML private ImageView middleRightImage;
	@FXML private ImageView bottomLeftImage;
	@FXML private ImageView bottomMiddleImage;
	@FXML private ImageView bottomRightImage;
	
	@FXML private TextField serverIP;
	@FXML private TextArea messageArea;
	@FXML private Button connectButton;
	
	//Variables
	private int portNumber;
	private String serverIp;
	private Socket server;
	private Image imageO;
	private Image imageX;
	private BufferedReader socketRead;
	private PrintWriter socketOut;
	private Thread myThread;
	
	//FXML Methods
	
    @FXML void addTopLeft(ActionEvent event) {
    	topLeft.setDisable(true);
		topLeftImage.setDisable(false);
		topLeftImage.setImage(imageX);
		toMessageArea("Client took turn Top Left");
		socketOut.println("01");
    }

    @FXML void addTopMiddle(ActionEvent event) {
    	topMiddle.setDisable(true);
    	topMiddleImage.setDisable(false);
    	topMiddleImage.setImage(imageX);
    	toMessageArea("Client took turn Top Middle");
    	socketOut.println("02");

    }

    @FXML void addTopRight(ActionEvent event) {
    	topRight.setDisable(true);
    	topRightImage.setDisable(false);
    	topRightImage.setImage(imageX);
    	toMessageArea("Client took turn Top Right");
    	socketOut.println("03");

    }

    @FXML void addMiddleLeft(ActionEvent event) {
    	middleLeft.setDisable(true);
    	middleLeftImage.setDisable(false);
    	middleLeftImage.setImage(imageX);
    	toMessageArea("Client took turn Middle Left");
    	socketOut.println("04");

    }

    @FXML void addMiddleMiddle(ActionEvent event) {
    	middleMiddle.setDisable(true);
    	middleMiddleImage.setDisable(false);
    	middleMiddleImage.setImage(imageX);
    	toMessageArea("Client took turn Middle Middle");
    	socketOut.println("05");

    }

    @FXML void addMiddleRight(ActionEvent event) {
    	middleRight.setDisable(true);
    	middleRightImage.setDisable(false);
    	middleRightImage.setImage(imageX);
    	toMessageArea("Client took turn Middle Right");
    	socketOut.println("06");
    }

    @FXML void addBottomLeft(ActionEvent event) {
    	bottomLeft.setDisable(true);
    	bottomLeftImage.setDisable(false);
    	bottomLeftImage.setImage(imageX);
    	toMessageArea("Client took turn Bottom Left");
    	socketOut.println("07");

    }

    @FXML void addBottomMiddle(ActionEvent event) {
    	bottomMiddle.setDisable(true);
    	bottomMiddleImage.setDisable(false);
    	bottomMiddleImage.setImage(imageX);
    	toMessageArea("Client took turn Bottom Middle");
    	socketOut.println("08");
    	
    }

    @FXML void addBottomRight(ActionEvent event) {
    	bottomRight.setDisable(true);
    	bottomRightImage.setDisable(false);
    	bottomRightImage.setImage(imageX);
    	toMessageArea("Client took turn Bottom Right");
    	socketOut.println("09");

    }

    @FXML void connect(ActionEvent event) throws IOException {		//Connects to serverSocket
    	portNumber = 4444;
    	String tempIP = "";
    	if ((tempIP = serverIP.getText()) != null) {
    		serverIp = tempIP;
    	} else {
        	serverIp = "localhost";		
    	}
    	
    	System.out.println("This is severIp ---" + serverIp);
    	
    	try {
			server = new Socket(serverIp, portNumber);
			toMessageArea("Connected to " + server.getInetAddress() + " on port " + server.getLocalPort());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
    	
    	socketRead = new BufferedReader(new InputStreamReader(server.getInputStream()));
    	socketOut = new PrintWriter(server.getOutputStream(), true);
    	
    	myThread = new Thread(this);
    	myThread.start();
    	
    }
    
    //Constructor
    
    public ClientViewControl() {
    	loadImages();
    }
    
    //Methods
    
    private void toMessageArea(String input) {		//Sends message to messageArea
    	messageArea.setText(input);
    }
    
    private void loadImages() {		//Loads Images to be used, Xs and Os
    	File oFile = new File("src/view/resources/o.png");
		imageO = new Image(oFile.toURI().toString());
		File xFile = new File("src/view/resources/x.png");
		imageX = new Image(xFile.toURI().toString());
    }

	@Override
	public void run() {
		while (true) {
			try {
				String input = socketRead.readLine();
				System.out.println(input);
				if (input.charAt(0) == '0') {
					System.out.println("Sucess");
					process(input.charAt(1));
				} else {
					System.out.println("Not valid move");
					System.out.println(input);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void process(char input) {
		if (input == '1') {
			topLeft.setDisable(true);
			topLeftImage.setDisable(false);
			topLeftImage.setImage(imageO);
			toMessageArea("Host Moved Top Left");
		} else if (input == '2') {
			topMiddle.setDisable(true);
			topMiddleImage.setDisable(false);
			topMiddleImage.setImage(imageO);
			toMessageArea("Host Moved Top Middle");
		} else if (input == '3') {
			topRight.setDisable(true);
			topRightImage.setDisable(false);
			topRightImage.setImage(imageO);
			toMessageArea("Host moved Top Right");
		} else if (input == '4') {
			middleLeft.setDisable(true);
			middleLeftImage.setDisable(false);
			middleLeftImage.setImage(imageO);
			toMessageArea("Host moved Middle Left");
		} else if (input == '5') {
			middleMiddle.setDisable(true);
			middleMiddleImage.setDisable(false);
			middleMiddleImage.setImage(imageO);
			toMessageArea("Host moved Middle Middle");
		} else if (input == '6' ) {
			middleRight.setDisable(true);
			middleRightImage.setDisable(false);;
			middleRightImage.setImage(imageO);
			toMessageArea("Host moved Middle Right");
		} else if (input == '7') {
			bottomLeft.setDisable(true);
			bottomLeftImage.setDisable(false);
			bottomLeftImage.setImage(imageO);
			toMessageArea("Host moved Bottom Left");
		} else if (input == '8') {
			bottomMiddle.setDisable(true);
			bottomMiddleImage.setDisable(false);
			bottomMiddleImage.setImage(imageO);
			toMessageArea("Host moved bottom Middle");
		} else if (input == '9') {
			bottomRight.setDisable(true);
			bottomRightImage.setDisable(false);
			bottomRightImage.setImage(imageO);
			toMessageArea("Host moved bottom Right");
		}
	}
}
