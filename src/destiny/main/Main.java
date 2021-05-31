package destiny.main;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import destiny.assets.Constants;
import destiny.assets.Player;
import destiny.net.MongoHandler;
import destiny.panels.Window;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

/**
 * 
 * The class that holds the main method of the program
 * 
 * @author Nathaniel
 * @version 5/13/2021
 *
 */
public class Main {

	/**
	 * 
	 * Runs the program
	 * 
	 * @param args The arguments that the program should be run with
	 */
	public static void main(String[] args) {

		if (System.getProperty("os.name").startsWith("Windows")) {
			System.setProperty("java.library.path", "lib/win32-x86-64");
			System.setProperty("jna.library.path", "lib/win32-x86-64");
		} else {
			System.setProperty("java.library.path", "lib/macosx");
			System.setProperty("jna.library.path", "lib/macosx");
		}
		System.out.println(System.getProperty("java.library.path"));
		System.out.println(System.getProperty("jna.library.path"));
		
		Scanner sc = null;
		try {
			sc = new Scanner(new File("res/userData/login.txt"));
		} catch (FileNotFoundException e) {

			userLogin();

			try {
				sc = new Scanner(new File("res/userData/login.txt"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		String userName = sc.nextLine();
		String pswd = sc.nextLine();

		if (!MongoHandler.checkUserLogin(userName, pswd)) {

			JOptionPane.showMessageDialog(null,
					"Your saved username and password were not found (maybe the login file was tampered with?), please redo the login");
			userLogin();
			
			try {
				sc = new Scanner(new File("res/userData/login.txt"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		int choice = JOptionPane.showConfirmDialog(null, "Would you like to login as: " + userName);

		if (choice == 1) {

			userLogin();
			
			try {
				sc = new Scanner(new File("res/userData/login.txt"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			userName = sc.nextLine();
			pswd = sc.nextLine();

		} else if (choice == 2) {

			System.exit(0);

		}
		
		
		
		Player.loadFromDocument(MongoHandler.getUserDoc(userName));

		Window drawing = new Window();
		PApplet.runSketch(new String[] { "" }, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame) canvas.getFrame();

		window.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		window.setLocation(0, 0);
		window.setMinimumSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
		window.setMaximumSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		canvas.requestFocus();

	}

	private static void userLogin() {

		int option = JOptionPane.showConfirmDialog(null,
				"Would you like to create a new account or use an existing one? (YES - create new, NO - use existing)");

		if (option == 0) {

			boolean checkName = false;

			String userName = null;

			while (!checkName) {

				userName = JOptionPane.showInputDialog("Enter a username:");
				
				if (userName == null)
					System.exit(0);
				
				checkName = MongoHandler.getUserDoc(userName) == null;
				
				if (!checkName) {
					
					JOptionPane.showMessageDialog(null, "That username already exists. Please try a different one");
					
				}

			}

			boolean checkPass = false;
			String pswd = null;

			while (!checkPass) {

				JPanel panel = new JPanel();
				JLabel label = new JLabel("Enter a password:");
				JPasswordField pass = new JPasswordField(10);
				panel.add(label);
				panel.add(pass);
				String[] options = new String[] { "OK" , "Cancel"};
				int chosen = JOptionPane.showOptionDialog(null, panel, "Password", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, options, options[0]);

				if (chosen != 0)
					System.exit(0);
				
				pswd = new String(pass.getPassword());
				
				JPanel panel2 = new JPanel();
				JLabel label2 = new JLabel("Re-Enter your password:");
				JPasswordField pass2 = new JPasswordField(10);
				panel2.add(label2);
				panel2.add(pass2);
				String[] options2 = new String[] { "OK" , "Cancel"};
				chosen = JOptionPane.showOptionDialog(null, panel2, "Password", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, options2, options2[0]);

				if (chosen != 0)
					System.exit(0);
				
				checkPass = pswd.equals(new String(pass2.getPassword()));
				
				if (!checkPass) {
					
					JOptionPane.showMessageDialog(null, "The passwords did not match :( Try again");
					
				}

			}

			PrintWriter writer = null;
			try {
				writer = new PrintWriter(new FileWriter("res/userData/login.txt"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			writer.println(userName);
			writer.println(pswd);
			writer.close();

			MongoHandler.addUser(userName, pswd);

		} else if (option == 1) {

			boolean correctName = false;
			String userName = null;
			while (!correctName) {
				userName = JOptionPane.showInputDialog("Enter a username:");
				
				if (userName == null)
					System.exit(0);
				
				correctName = MongoHandler.getUserDoc(userName) != null;
				
				if (!correctName) {
					
					JOptionPane.showMessageDialog(null, "That username doesn't exist. Please try again");
					
				}
				
			}

			JPanel panel = new JPanel();
			JLabel label = new JLabel("Enter a password:");
			JPasswordField pass = new JPasswordField(10);
			panel.add(label);
			panel.add(pass);

			boolean correctPass = false;
			String pswd = null;
			while (!correctPass) {

				String[] options = new String[] { "OK", "Cancel" };
				int choice = JOptionPane.showOptionDialog(null, panel, "Password", JOptionPane.NO_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

				if (choice != 0)
					System.exit(0);
				
				pswd = new String(pass.getPassword());

				correctPass = MongoHandler.checkUserLogin(userName, pswd);

				if (!correctPass)
					JOptionPane.showMessageDialog(null, "Incorrect Password. Try again");

			}
			
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(new FileWriter("res/userData/login.txt"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			writer.println(userName);
			writer.println(pswd);
			writer.close();

		} else {

			JOptionPane.showMessageDialog(null, "Goodbye!");
			System.exit(0);

		}

	}

}
