package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Random;

public class calcscreen extends BorderPane {// the screen that will show the result of the program
	private int num; // int variable to store the number entered by the user in the text field "t"
	private TextArea a; // TextArea to display the result of the program in it.
	private int[][] dp; // 2D array to store the dynamic programming table in it for the maximum number
						// of light.
	private int[] ascarr;// 1D array to store the ascending array in it.
	private int[] arr;// 1D array to store the array in it "leds array that the user entered in the
						// text field.
	private HBox leds;// HBox to display the LEDs in it.
	private int[] ll;// 1D array to store the lighted LEDs in it. that the value taken in shled
						// method that eill store the lighted leds on it.
	private char[][] b;

	public calcscreen() {// no argument constructor .
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color: #89CFF0");
		Label l1 = new Label("-Welcome to the digital program, please enter LEDs number");
		l1.setFont(new Font("Impact", 20));
		l1.setTextFill(Color.web("#0047AB"));
		Label l2 = new Label("-Now fill those LEDs ordered by numbers for each");
		l2.setFont(new Font("Impact", 20));
		l2.setTextFill(Color.web("#0047AB"));
		Label l3 = new Label("LEDs Number:");
		l3.setFont(new Font("Comic Sans MS", 15));
		l3.setTextFill(Color.web("#191970"));
		TextField t = new TextField();
		t.setMaxWidth(50);
		t.setMaxHeight(50);
		Label l4 = new Label("**the number should be greater than 0 (positive integer)**");
		l4.setFont(new Font("Comic Sans MS", 10));
		l4.setTextFill(Color.web("#C70039"));
		Label l5 = new Label("**then u have the choice to enter number or choose them randomly or from file**");
		l5.setFont(new Font("Comic Sans MS", 10));
		l5.setTextFill(Color.web("#C70039"));
		Label l6 = new Label("**u can represent the board for 20 more than it i advise u just for rest buttons**");
		l6.setFont(new Font("Comic Sans MS", 10));
		l6.setTextFill(Color.web("#C70039"));
		VBox vl = new VBox();
		vl.getChildren().addAll(l4, l5, l6);
		HBox b = new HBox(10);
		b.getChildren().addAll(l3, t, vl);
		b.setAlignment(Pos.CENTER);
		leds = new HBox(10);
		leds.setAlignment(Pos.CENTER);
		a = new TextArea();
		a.setEditable(false);
		a.setWrapText(true);
		a.setMaxWidth(300);
		Button sb = new Button("Show Boards");
		sb.setFont(new Font("Comic Sans MS", 15));
		sb.setStyle("-fx-background-color: #191970");
		sb.setTextFill(Color.web("#FFFFFF"));
		Button calculate = new Button("MAX NUM LEDs ");
		calculate.setFont(new Font("Comic Sans MS", 15));
		calculate.setStyle("-fx-background-color: #191970");
		calculate.setTextFill(Color.web("#FFFFFF"));
		Button Db = new Button("Print DP Table");
		Db.setFont(new Font("Comic Sans MS", 15));
		Db.setStyle("-fx-background-color: #191970");
		Db.setTextFill(Color.web("#FFFFFF"));
		Button cp = new Button("Connected Pairs");
		cp.setFont(new Font("Comic Sans MS", 15));
		cp.setStyle("-fx-background-color: #191970");
		cp.setTextFill(Color.web("#FFFFFF"));
		Button sl = new Button("Show Lighted LEDs");
		sl.setFont(new Font("Comic Sans MS", 15));
		sl.setStyle("-fx-background-color: #191970");
		sl.setTextFill(Color.web("#FFFFFF"));
		HBox hb = new HBox(10);
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().addAll(calculate, sl, Db, cp, sb);
		hb.setSpacing(10);
		hb.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
		Button random = new Button("Random");
		random.setFont(new Font("Comic Sans MS", 15));
		random.setStyle("-fx-background-color: #191970");
		random.setTextFill(Color.web("#FFFFFF"));
		Button file = new Button("File");
		file.setFont(new Font("Comic Sans MS", 15));
		file.setStyle("-fx-background-color: #191970");
		file.setTextFill(Color.web("#FFFFFF"));
		HBox hb2 = new HBox(10);
		hb2.setAlignment(Pos.CENTER);
		hb2.getChildren().addAll(random, file);
		vbox.getChildren().addAll(l1, b, l2, leds, hb2, a, hb);
		vbox.setSpacing(10);
		setCenter(vbox);
		// **************set on actions**************//
		t.setOnAction(e -> {// to get the number entered by the user in the text field "t" and check if it
							// is between 1 and 100 and update the LEDs text fields if it is valid.
			String val = t.getText();// get the number entered by the user in the text field "t"
			if (!val.isEmpty()) {// check if the text field is not empty and update the LEDs text fields if it is
									// valid.
				try {// to make sure that no errors will occur when converting the string to an
						// integer and check if it is between 1 and 100 and update the LEDs text fields
						// if it is valid.
					num = Integer.parseInt(val);// convert the string to an integer.
					if (num > 0) {// check if the number is greater than 0.
						update();// update the LEDs text fields with the new 'num' value if it is valid.
					} else {
						error("Please enter a number greater than 0 (positive integer)!!!!!!");// display message ig the
																								// number is not greater
																								// than 0.
						t.setText(""); // clear the TextField if the number is not graeter than 0.
					}
				} catch (Exception ex) {
					error("Please enter a valid input !!!!!!!!!!!");// Display an error message if the input is not a
																	// valid number.
					t.setText(""); // Clear the TextField if the input is not a valid number.
				}
			} else {
				error("an empty value!!!");
			}
		});
		// Button to calculate the result using MaxNumberOfLight method and display the
		// result in the TextArea and update the LEDs text fields with the new 'num'
		// value if it is valid.
		calculate.setOnAction(event -> {
			arr = arrtxtFields();// get the input array from the LEDs text fields and store it in the global
									// variable "inputArray".
			ascarr = AscendingFromNum();// create an ascending array from the LEDs text fields and store it in the
										// global variable "ascendingArray".
			dp = new int[ascarr.length + 1][arr.length + 1]; // Added: initialize the dp array
			int res = MaxNumberOfLight(ascarr, arr);// Call MaxNumberOfLight method and store the result in the global
													// variable "result".
			a.setText("The max number of LEDs that will be lighted is: " + res);// Display the result in the TextArea.
		});
		// Button to show the lighted LEDs in the TextArea .
		sl.setOnAction(event -> {
			if (dp == null) {// make sure that it will not work if the dp table is null that to work i should
								// press the first max LEDs light that its work as calculate button.
				error("Please calculate Max LEDs Light at first to make sure there are connected pairs.");
			} else {
				shLed();// display the lighted LEDs in text area by this method.
			}
		});
		// Button to show connected pairs in the TextArea
		cp.setOnAction(event -> {
			if (dp == null) {// make sure that it will not work if the dp table is null that to work i should
								// press the first max LEDs light that its work as calculate button.
				error("Please calculate (Max LEDs Light) first to make sure there are connected pairs and ordered on the DP table!!!!!");
			} else {
				Pairs();// this method will get the connected pairs that connected to each other power
						// source to leds.
			}
		});
		// Button to print the DP table in the TextArea
		Db.setOnAction(event -> {
			if (dp == null) {// make sure that it will not work if the dp table is null that to work i should
								// press the first max LEDs light that its work as calculate button.
				error("Please calculate Max LEDs Light at first to make sure there are connected pairs and ordered on the DP table!!!!.");
			} else {
				DP();// this method will print the DP table and with the arrows that i take their
						// symbols char from internet on one table.
			}
		});
		random.setOnAction(event -> {
			ascarr = AscendingFromNum(); // Update the ascending array
			shuffle(ascarr); // Shuffle the ascarr array
			arr = Arrays.copyOf(ascarr, ascarr.length); // Copy the shuffled array to arr
			for (int i = 0; i < num; i++) {// this for loop will update the text fields with the random values from
											// shuffling the ascarr array.
				TextField txt = (TextField) leds.getChildren().get(i);// Get the text field of the i LED .
				txt.setText("" + arr[i]);// Update the text field with the new 'num' value.
			}
		});
		file.setOnAction(event -> {// Button to load values from a file and update the LEDs text fields with the
									// new 'num' value if it is valid.
			FileChooser fileChooser = new FileChooser();// Create a new FileChooser instance for selecting a file to
														// load values from it and update the LEDs text fields with the
														// new 'num' value if it is valid.
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));// Add a
																											// filter
																											// for text
																											// files to
																											// the
																											// FileChooser
																											// .
			File filee = fileChooser.showOpenDialog(null);// Show the FileChooser and get the selected file.

			if (filee != null) {// Check if a file was selected .
				try (BufferedReader br = new BufferedReader(new FileReader(filee))) {// Try to read the file and update
																						// the LEDs text fields with the
																						// new 'num' value if it is
																						// valid.
					String line;// Read the file line by line
					while ((line = br.readLine()) != null) {
						String[] values = line.split(",");// Split the line into an array of values using the comma as
															// the delimiter .
						if (values.length == num && valdup(values) == 1) {// Check if the number of values is equal to
																			// num and if the values are valid .
							// Update the arr array with the values from the file
							for (int i = 0; i < num; i++) {
								arr[i] = Integer.parseInt(values[i]);
							}
							for (int i = 0; i < num; i++) {// Update the LEDs text fields with the new 'num' value if it
															// is valid.that i read from the text file
								TextField txt = (TextField) leds.getChildren().get(i);// Get the text field of the i LED
																						// add casting it to TextField .
								txt.setText("" + arr[i]);// Update the text field with the new 'num' value.
							}
							// Display a success message if it reads from the file correctly
							a.setText("Values loaded from file successfully!!!!!!!!!!!!!!!");
						} else {
							error("Invalid or duplicate values on line !!!!!!!1: " + line);// Display an error message
																							// if the values are not
																							// valid .
						}
					}
				} catch (Exception e) {// If an error occurs while reading the file .
					error("Error while  reading the file!!!!!: ");
				}
			}
		});
		sb.setOnAction(event -> {// Button to show the digital board in the new window and update the LEDs text
									// fields with the new 'num' value if it is valid.
			digitalBoard();
		});
	}

	// ************** methods**************//
	private void shuffle(int arr[]) {// shuffle the LEDs array values that is from 1 to num .that i well shuffle the
										// contant of ascarr and put them in the arr array.
		Random rand = new Random();// Create a Random object that will be used to generate random numbers.
		for (int i = arr.length - 1; i > 0; i--) {// Shuffle the LEDs array 0-5.
			int j = rand.nextInt(i + 1);// here random value between 1 to 6 .
			int temp = arr[i];// swap the elements .
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}

	private int[] AscendingFromNum() {// create an ascending array from the LEDs text fields and store it in the
										// global variable "ascendingArray".
		int[] ascending = new int[num];// Create an array of size 'num' and store it in the global variable
										// "ascendingArray".
		for (int i = 0; i < num; i++) { // create an ascending array from the LEDs text fields and store it in the
										// global variable "ascendingArray".
			ascending[i] = i + 1;// Set the initial value of each element in the array to "i + 1".
		}
		return ascending;// return the ascending array.
	}

	private int[] arrtxtFields() {// get the input array from the LED text field .
		int[] arr = new int[num];// Create an array of size 'num' and store it in the global variable
									// "inputArray".
		for (int i = 0; i < num; i++) {// get the input array from the LEDs text fields and store it in the global
										// variable "inputArray".
			TextField txt = (TextField) leds.getChildren().get(i);// get the input array from the LEDs text fields and
																	// store it in the global variable "inputArray".
			try {// to make sure that no errors will occur when converting the string to an
					// integer and store it in the global variable "inputArray".
				arr[i] = Integer.parseInt(txt.getText());// convert the string to an integer.
			} catch (Exception e) {
				error("Please enter valid numbers in all text fields");// Display an error message if the input is not a
																		// valid number.
				return null;
			}
		}
		return arr;// return the input array if it is valid.
	}

	private void update() {// update the LEDs text fields with the new 'num' value if it is valid on the
							// text fields.
		arr = new int[num];// Create an array of size 'num' and store it .
		leds.getChildren().clear(); // Clear existing text fields
		// Create text fields based on the new 'num' and add them to the HBox "leds"
		for (int i = 1; i <= num; i++) {
			TextField txt = new TextField();
			txt.setPromptText(" " + "fill it!"); // Set the initial text of the text field to "i" for each text field.
			int i2 = i - 1;// get the index of the text field in the "leds" HBox that starts from 0 .
			txt.setOnAction(e -> {
				try {
					int val = Integer.parseInt(txt.getText());// get the input array from the LEDs text fields
					if (val < 1 || val > num || isDuplicate(val, arr, i2)) {// to make sure that the number is grater
																			// than .
						error("Please enter a number greater then 0 and not dublicate in previous text fields!!!!!!!!!!!!!!");
					} else {
						arr[i2] = val;// store the input array for leds .
					}
				} catch (Exception ex) {
					error("Please enter a valid number!");
					txt.setText("");// Clear the text field if the input is not a valid number.
				}
			});
			leds.getChildren().add(txt);// Add the text field to the HBox "leds"
		}
	}

	private boolean isDuplicate(int val, int[] array, int curr) {// check if the number is dublicate or not .
		for (int i = 0; i < curr; i++) {
			if (array[i] == val) {// check if the number is dublicate or not .
				return true;// return true if the number is dublicate.
			}
		}
		return false;// return false if the number is not dublicate.
	}

	public int MaxNumberOfLight(int arr1[], int arr2[]) {
		int m = arr1.length;// get the length of the array.
		int n = arr2.length;// get the length of the array.
		for (int i = 0; i <= m; i++) {// Initialize the first row and column of the 2D array with 0.
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {// if the row or column is 0, set the value of the element to 0.
					dp[i][j] = 0;// Initialize the first row and column of the 2D array with 0.
				} else if (arr1[i - 1] == arr2[j - 1]) {// if the elements in the array are equal, set the value of the
														// element to the sum of the previous element+1.
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);// if the elements in the array are not equal, set
																	// the value of the element to the maximum of the
																	// previous element or the previous element.
				}
			}
		}
		return dp[m][n];// return the value of the last element in the 2D array that is the maximum
						// number of leds that gonna light.
	}

	private void Pairs() {// method that get the connected pairs of the LEDs that will light and got the
							// same value.
		StringBuilder str = new StringBuilder("Connected Pairs:\n");// Create a string builder to store the output.
		int i = ascarr.length;// get the length of the array.
		int j = arr.length; // get the length of the array.
		str.append("The Connected Pairs are:\n");// Append the output to the string builder.
		while (i > 0 && j > 0) {// while the row or column is greater than 0 and i use while loop to move
								// diagonally up and left so its better than using for loop in this case .
			if (ascarr[i - 1] == arr[j - 1]) {// if the elements in the array are equal and the value of the element in
												// the previous row is greater than or equal to the value of the element
												// in the previous column .
				str.append("-->> [").append(ascarr[i - 1]).append(",").append(arr[j - 1]).append("]\n");// Append the
																										// output to the
																										// string
																										// builder.
				// move diagonally up and left by one cell
				i--;// up
				j--;// left
			} else if (dp[i - 1][j] >= dp[i][j - 1]) {// if the elements in the array are not equal and the value of the
														// element in the previous row is greater than or equal to the
														// value of the element in the previous column
				i--;// up
			} else {
				j--;// left
			}
		}

		a.setText(str.toString());// Display the output in the text area.
	}

	private void DP() {// create a 2D array to store the length of the longest common subsequence but
						// for array of integers.
		int m = ascarr.length; // length of acending array.
		int n = arr.length; // length of array.
		int[][] c = new int[m + 1][n + 1]; // create a 2D array to store the length of the longest common subsequence
											// for array of integers.
		b = new char[m + 1][n + 1];// create a 2D array to store the direction of the longest common
									// subsequence for array of integers that will stor the direction of the
									// longest common subsequence for array of integers in the 2D array.
		for (int i = 1; i <= m; i++) { // initialize the first row and column of the 2D array with 0.
			c[i][0] = 0;
		}
		for (int j = 1; j <= n; j++) {// initialize the first row and column of the 2D array with 0.
			c[0][j] = 0;
		}
		for (int i = 1; i <= m; i++) {// loop through the rows of the 2D array.
			for (int j = 1; j <= n; j++) {// loop through the columns of the 2D array.
				if (ascarr[i - 1] == arr[j - 1]) {// if the elements in the array are equal
					c[i][j] = c[i - 1][j - 1] + 1;// set the value of the element to the sum of the previous element+1.
					b[i][j] = '↖';// set the direction of the element to '↖'.
				} else {// if the elements in the array are not equal .
					if (c[i][j - 1] >= c[i - 1][j]) {// if the value of the element in the previous column is greater
														// than or equal to the value of the element in the previous row
						c[i][j] = c[i][j - 1];// set the value of the element to the value of the element in the
												// previous column +1.
						b[i][j] = '←';// set the direction of the element to '←'.
					} else {// if the value of the element in the previous column is less than the value of
							// the element in the previous row .
						c[i][j] = c[i - 1][j];// set the value of the element to the value of the element in the
												// previous row +1.
						b[i][j] = '↑';// set the direction of the element to '↑'.
					}
				}
			}
		}
		StringBuilder str = new StringBuilder("\t**DP Table**\n");// Create a string builder to store the output.
		for (int i = 0; i <= m; i++) {// loop through the rows of the 2D array.
			for (int j = 0; j <= n; j++) {// loop through the columns of the 2D array.
				str.append(c[i][j]);// Append the value of the element to the string builder.
				if (b[i][j] != 0) {// if the direction of the element is not 0 .
					str.append(b[i][j]);// Append the direction of the element to the string builder.
				}
				str.append("\t");// Append a tab to the string builder.
			}
			str.append("\n");// Append a new line to the string builder.
		}

		a.setText(str.toString()); // Display the output in the text area.
	}

	private void shLed() {// create a 2D array to store the element th of the longest common subsequence
							// but for array of integers.
		int i = ascarr.length;// length of acending array.
		int j = arr.length;// length of leds array.
		ll = new int[j]; // Array to store lighted LEDs in it to use it later in the show the digital
							// board .
		int index = 0;// index of the array ll ro help us store the lighted LEDs in it.
		StringBuilder str = new StringBuilder("The LEDs numbers that are lighted: ");// Create a string builder to store
																						// the output.
		while (i > 0 && j > 0) {// loop through the rows and columns of the 2D array.
			if (ascarr[i - 1] == arr[j - 1]) {
				str.append(ascarr[i - 1]).append(" "); // Append the number of the lighted LED
				ll[index++] = ascarr[i - 1];// add the number of the lighted LED to the array ll .
				// move diagonally up and left by one cell
				i--; // up
				j--;// left
			} else if (dp[i - 1][j] >= dp[i][j - 1]) {// if the value of the element in the previous row is greater than
														// or equal to the value of the element in the previous column .
				i--; // up
			} else {
				j--; // left
			}
		}

		a.setText(str.toString()); // Display the numbers of the lighted LEDs

	}

	private int valdup(String str[]) {// check if the input is valid or not and if there is duplicated numbers or not
										// values.
		int[] val = new int[num + 1];// create a boolean array to store the values of the numbers from 1 to num.
		for (int i = 0; i < str.length; i++) {
			try {
				int intval = Integer.parseInt(str[i]);// convert the string to integer.
				if (intval < 1 || intval > num || val[intval] == 1) {// check if the number is between 1 and num and if
																		// the number is duplicated.
					return 0;// return false if the number is not valid.
				}
				val[intval] = 1;// set the value of the number to true.
			} catch (Exception e) {// if the string is not a number.
				return 0;// return false .
			}
		}
		return 1;// return true if the input is valid.
	}

	private void digitalBoard() {// create a digital board for user to see the result in the new window
		Stage stg = new Stage();// create a new stage for the digital board
		stg.setTitle("digital board:)");
		GridPane gp = new GridPane();// Create a new GridPane for the digital board thet i will fill the element with
										// squares that reperesents thesource power and circles that will represents the
										// leds.
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setAlignment(Pos.CENTER); // Center the GridPane in the window .
		// Set background image for the GridPane we use this for grid pane cuz if i want
		// to use the normal way that we learn will not work for the whole window it
		// just set by default on 0,0 position and that is not good.
		gp.setStyle(
				"-fx-background-image: url('https://w0.peakpx.com/wallpaper/518/413/HD-wallpaper-green-circuit-board-texture-green-circuit-digital-texture-circuit-board-green-technology-background-green-circuit-board.jpg');-fx-background-size: stretch;-fx-background-position:center top;");
		// Add rectangles for array elements in the first row (ascarr) that represents
		// the source power .
		for (int i = 0; i < ascarr.length; i++) {// Add rectangles for array elements in the first row (ascarr) that
													// represents the source power in squares.
			VBox sqr = squares(ascarr[i] + "");// Create a VBox with a Rectangle and a Label
			gp.add(sqr, i, 0);// Add the VBox to the GridPane at position i, 0 in the GridPane .
		}
		// Add circles for array elements in the second row (arr) that reperesents the
		// leds that represent the leds in circles.
		for (int i = 0; i < arr.length; i++) {// Add circles for array elements in the second row (arr) that reperesents
												// the leds that represent the leds in circles.
			VBox crcl = circles(arr[i] + "");// Create a VBox with a Circle and a Label
			gp.add(crcl, i, 5);// Add the VBox to the GridPane at position i, 5 in the GridPane .
		}
		Scene scene = new Scene(gp, 600, 300);
		stg.setScene(scene);
		stg.show();
	}

	private VBox squares(String text) {// create a vbox with a rectangle and a label that reperesents the leds in
										// squares and the label is the number in the array that i initialize in the
										// shled method and store their values in it
		Rectangle square = new Rectangle(60, 60);
		square.setFill(Color.LIGHTGREEN);
		square.setStroke(Color.GREEN);
		Label label = new Label(text);
		label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		label.setTextFill(Color.WHITE);// Set text color to white for other numbers
		VBox vBox = new VBox(10);
		int number = Integer.parseInt(text);
		if (isInLLArray(number)) {
			label.setTextFill(Color.GOLD); // Set text color to yellow if the number is in ll array
			label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		}
		vBox.getChildren().addAll(square, label);
		vBox.setAlignment(Pos.CENTER);
		return vBox;// return the vbox with the rectangle and the label
	}

	private VBox circles(String text) {// create a vbox with a circle and a label that reperesents the leds in circles
										// and the label is the number in the array that i initialize in the shled
										// method and store their values in it
		Circle circle = new Circle(30);
		Label label = new Label(text);
		label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		label.setTextFill(Color.WHITE);// Set text color to white for other numbers
		VBox vBox = new VBox(10);
		int number = Integer.parseInt(text);
		if (isInLLArray(number) == true) {
			circle.setFill(Color.YELLOW); // Set fill color to yellow if the number is in ll array
			label.setTextFill(Color.GOLD); // Set text color to yellow if the number is in ll array
			label.setFont(Font.font("Arial", FontWeight.BOLD, 16)); // Increase font size
		} else {
			circle.setFill(Color.GREY); // Set fill color to grey if the number is not in ll array
			label.setTextFill(Color.WHITE);
		}
		vBox.getChildren().addAll(circle, label);
		vBox.setAlignment(Pos.CENTER);
		return vBox;// return the vbox with the circle and the label
	}

	private boolean isInLLArray(int number) {// check if the number is in the array that i initialize in the shled
												// method and store their values in it
		if (ll != null) {// check if the array is not null
			for (int i = 0; i < ll.length; i++) {// check if the number is in the array
				if (ll[i] == number) {// check if the number is in the array
					return true;// return true if the number is in the array
				}
			}
		}
		return false;// return false if the number is not in the array
	}

	private void error(String msg) {// Display an error message if the number is not between 1 and 100.
		Alert alert = new Alert(Alert.AlertType.ERROR);// Create an alert to display the error message.
		alert.setTitle("Error!!!");
		alert.setHeaderText(null);
		alert.setContentText(msg);// Set the content of the alert to the error message.
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.showAndWait();// Display the alert.
	}
}
