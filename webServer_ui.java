/**
	Author: Charmaine T. Matienzo
	Section: B - 1L
	Project 2: Web Server
	Reference: https://fragments.turtlemeat.com/javawebserver.php
**/


// import statements
import javax.swing.*;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;

// class declaration for the mini-webServer's UI
public class webServer_ui extends JFrame {
	// UI elements declaration
	JPanel panel = new JPanel();
	JScrollPane scroll_pane = new JScrollPane();
	JTextArea output = new JTextArea();
	static Integer listen_port = null;

	// class constructor
	public webServer_ui() {
		try {
			setup_ui();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// UI set up
	private void setup_ui() throws Exception {
		output.setBackground(new Color(0, 0, 0));
		output.setForeground(new Color(151, 138, 255));
		output.setBorder(BorderFactory.createLoweredBevelBorder());
		output.setToolTipText("");
		output.setEditable(false);
		output.setColumns(40);
		output.setRows(30);
		
		this.setTitle("CMSC 137 Project 2 Web Server");
		
		// window closing handler
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				this_windowClosed(e);
			}
		});
		
		// ui elements positioning
		scroll_pane.getViewport().add(output);
		panel.add(scroll_pane);
		this.getContentPane().add(panel, BorderLayout.EAST);

		// other ui options set up
		this.setVisible(true);
		this.setSize(573, 525);
		this.setResizable(false);
		this.validate();
		
		// creates the Server
		new Server(listen_port.intValue(), this);
	}

	// exits program when "X" is pressed.
	void this_windowClosed(WindowEvent e) {
		System.exit(1);
	}

	// shows messages from Server to UI window
	public void send_message_to_window(String s) {
		output.append(s);
		output.setCaretPosition(output.getCaretPosition()+s.length());
	}

	// starts this class if run
	public static void main(String[] args) {
		// uses argument of main for what port to start on
		try {
			listen_port = new Integer(args[0]);
		}
		// if no port is specified, use port 80 (default)
		catch (Exception e) {
			listen_port = new Integer(80);
		}
		
		// creates an instance of this class
		webServer_ui ui = new webServer_ui();
	}

} // class ends
