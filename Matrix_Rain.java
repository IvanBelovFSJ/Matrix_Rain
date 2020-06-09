import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*
 *	Most of the below code was found at:
 *				https://stackoverflow.com/questions/4710693/java-falling-matrix-code-like-the-movie-continued
 *	posted by:	javaDude.
 *
 *	Below code was adjusted to the needed specifications.
 */

@SuppressWarnings("serial")
public class Matrix_Rain extends JFrame {
	public static JPanel screen = new JPanel(null); // Creates an instance of JPanel object - memory address.
	public static Random random = new Random(); // Creates an instance of Random object - memory address.
	public static int FONT_SIZE; // Font of the characters populated in the label and outputted on the JPanel.
	public final static String TEXT = new String("あたアカサザジズゼゾスセソキクケコイウエオャな"); // String with Japanese characters
	public static int columnNumCheck; // Helps facilitate a mechanism preventing from ever having to display
	public static int[] columnPositions;
	public static JLabel[][] labelArray;
	public static int[][] characterSymbol;
	public static Color characterColor;
	
	public Matrix_Rain() {
	Color backgroundColor = new Color(13, 2, 8); // Setting a color.
	this.add(screen); // Adding panel to this class.
	screen.setBackground(backgroundColor); /* Setting background color. */ } // end of Matrix_Rain()

	public static void fade(Color colorP, JLabel[][] labelArray) {
		int R = 0;
		int G = 255;
		int B = 85;
	while ((G != 0) && (B !=0 )) {	
		G = G - 3;
		B = B - 1;
	for (int screenRow = 0; screenRow < labelArray.length; screenRow++) {
		colorP = new Color(R, G, B); // Setting a color for drop down characters.
	try { if ( ( screenRow >= (labelArray.length) ) ) { break; }
	for (int columnCount = 0; columnCount < labelArray[screenRow].length; columnCount++) {
	if ( ( columnCount >= labelArray[screenRow].length ) ) { break; }
	labelArray[screenRow][columnCount].setForeground(colorP);
	screen.repaint(); } // End of population for lopp
		Thread.sleep(2); // The speed of characters appearing on the window
		screen.repaint(); } // End of try statement
	catch (Exception e) {
	    throw new NullPointerException(
	    " corrupt data #1 ");
	} /* End of catch statement */ } /* End of for */ } /* End of while */ } // End of fade

	private static void printCharacters(int[] columnPositions, JLabel[][] labelArray, int[][] characterSymbol) {
		for (int screenRow = 0; screenRow < columnPositions.length; screenRow++) {
		int changingDepth = random.nextInt(50)+1;
		if ( ( screenRow >= columnPositions.length ) ) { break; }
		for (int columnCount = 0; columnCount < labelArray[screenRow].length; columnCount++) {
		if ( ( columnCount >= labelArray[screenRow].length) ) { break; }
		try {
			characterSymbol[screenRow][columnCount] = random.nextInt(TEXT.length()); // Character array element in each column is
			labelArray[screenRow][columnCount] = new JLabel("" + TEXT.charAt(characterSymbol[screenRow][columnCount]));
			screen.add(labelArray[screenRow][columnCount]); // Each label is added to a screen.
			labelArray[screenRow][columnCount].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, FONT_SIZE));
			labelArray[screenRow][columnCount].setForeground(characterColor); // Setting each label's foreground color.
			labelArray[screenRow][columnCount].setBounds(
				columnPositions[screenRow], (columnCount + changingDepth ) * FONT_SIZE, FONT_SIZE, FONT_SIZE);
			Thread.sleep(50); } // End of try statement
		catch (Exception e) { throw new NullPointerException(
	        " corrupt data #2 "); } /* End of catch statement */ }
	} /* End of for loop */ } // End of printCharacters()

	private static void erase(JLabel[][] labelArray) {		
		try {
		for (int screenRow = 0; screenRow < labelArray.length; screenRow++) {
		if ( screenRow > labelArray.length ) { break; }
		for (int columnCount = 0; columnCount < labelArray[screenRow].length; columnCount++) {
			if ( columnCount > labelArray[screenRow].length ) { break; }
				screen.remove(labelArray[screenRow][columnCount]);
				screen.repaint(); } /* End of population for loop */ } }
		catch (Exception e) { throw new NullPointerException(" corrupt data #3 "); } }

	public static void initializeVariables(int FONT_SIZE,
		int columnNumCheck, int[] columnPositions,
		JLabel[][] labelArray, int[][] characterSymbol, Color characterColor) {
		FONT_SIZE = ( random.nextInt(40) + 20 ); // new font for each iteration.
		columnNumCheck = random.nextInt(50) + 5;	// +1 prevents ever picking zero columns to display.
		columnPositions = new int[columnNumCheck]; // An array of integers is set to host random positions of column
		labelArray = new JLabel[columnPositions.length][]; // create N amount of label arrays
		characterSymbol = new int[columnPositions.length][]; // An array of Characters
		characterColor = new Color(0, 255, 65); /* Setting a color for drop down characters. */ }

	public static void assignColumnPositions(int[] columnPositions) {
		try { for (int count = 0; count < columnPositions.length; count++) {
				int intermediateStorage = random.nextInt(screen.getWidth() + 1 );
				columnPositions[count] = intermediateStorage;
			} /* End of for */ } catch (Exception e) {
		throw new NullPointerException(" corrupt data #4 ");
	} } // End of assignColumnPositions

	public static void createColumnsAssignLengths
	(int columnNumCheckP, JLabel[][] labelArrayP, int[][] characterSymbolP) {
		for (int i = 0; i < columnNumCheckP; i++) {
			int randomLength = random.nextInt(10) + 5;
			labelArrayP[i] = new JLabel[randomLength];
			characterSymbolP[i] = new int[randomLength];
	} /* End of for loop */ } // End of createColumnsAssignLengths


	private static void dripCharacteres(JLabel[][] labelArrayP)
	{
		
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Matrix_Rain frame = new Matrix_Rain();
		while (true) {
			FONT_SIZE = ( random.nextInt(30) + 15 ); // new font for each iteration.
			columnNumCheck = random.nextInt(10) + 5;	// +1 prevents ever picking zero columns to display.
			columnPositions = new int[columnNumCheck]; // An array of integers is set to host random positions of column
			labelArray = new JLabel[columnPositions.length][]; // create N amount of label arrays
			characterSymbol = new int[columnPositions.length][]; // An array of Characters
			characterColor = new Color(0, 255, 65); /* Setting a color for drop down characters. */
			
			frame.setExtendedState(frame.MAXIMIZED_BOTH); // This program's display is set to be fully expanded based on
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			createColumnsAssignLengths(columnNumCheck, labelArray, characterSymbol);
			assignColumnPositions(columnPositions);
			printCharacters(columnPositions, labelArray, characterSymbol);
			dripCharacteres(labelArray);
			fade(characterColor,labelArray);
			erase(labelArray);
	} } /* End of Main() */ } // End of Matrix_Rain class