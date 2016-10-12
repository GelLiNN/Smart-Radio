import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


@SuppressWarnings("serial")
public class YesButton extends JPanel implements ActionListener {
	private RadioFrame frame;
	private JFrame jframe;
	

	public YesButton(RadioFrame frame, JFrame jframe) {
		super(new BorderLayout());
		this.jframe = jframe;
		this.frame = frame;
		JButton button = new JButton("     Yes     ");
		add(button, BorderLayout.CENTER);
		button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (frame.question.equals("NO MORE AVAILABLE QUESTIONS"))
			return;
		
		if(frame.index > 0 && frame.index < 14) {
			frame.setAnswer(1);
			jframe.setVisible(false);
			frame.questionStrings();
		} else if (frame.index > 13 && frame.index < 32) {
			frame.setAnswer(-1);
			jframe.setVisible(false);
			frame.questionStrings();
		} else {
			frame.setAnswer(0);
			jframe.setVisible(false);
			frame.questionStrings();
		}
	}

}
