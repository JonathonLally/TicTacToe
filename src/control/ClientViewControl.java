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

    }

    @FXML void addMiddleLeft(ActionEvent event) {

    }

    @FXML void addMiddleMiddle(ActionEvent event) {

    }

    @FXML void addMiddleRight(ActionEvent event) {

    }

    @FXML void addBottomLeft(ActionEvent event) {

    }

    @FXML void addBottomMiddle(ActionEvent event) {

    }

    @FXML void addBottomRight(ActionEvent event) {

    }

    @FXML void connect(ActionEvent event) throws IOException {		//Connects to serverSocket
    	portNumber = 4444;
    	serverIp = "localhost";			//LocalHost for now TODO Change this
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
					System.out.println("Fail");
					System.out.println(input);
					System.out.println(input.charAt(0));
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
		}
		
	}

}
