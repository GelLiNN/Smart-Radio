import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



@SuppressWarnings("serial")
public class MaybeButton extends JPanel implements ActionListener {
	private RadioFrame frame;
	private JFrame jframe;

	public MaybeButton(RadioFrame frame, JFrame jframe) {
		super(new BorderLayout());
		this.jframe = jframe;
		this.frame = frame;
		JButton button = new JButton("    Maybe    ");
		add(button, BorderLayout.CENTER);
		button.addActionListener(this);
	}
	
	public static void main(String[] args) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (frame.question.equals("NO MORE AVAILABLE QUESTIONS"))
			return;
		
		frame.setAnswer(0);
		jframe.setVisible(false);
		frame.questionStrings();
	}

}
