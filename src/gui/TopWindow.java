package gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.*;

public class TopWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextPane label;
	private RollerSkateTrack track;
	

	
	public TopWindow() {
		JFrame frame = new JFrame("Tor Rolkarski");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		label = new JTextPane();
		setCenter(label);
		label.setEditable(false);
		label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
		track = RollerSkateTrack.getInstance(this);
		label.setText(track.buildTrack());
		frame.getContentPane().add(label, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	public JTextPane getLabel() {
		return label;
	}
	
	private void setCenter(JTextPane pane){
		StyledDocument doc = pane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
	}
	
	
	
}
