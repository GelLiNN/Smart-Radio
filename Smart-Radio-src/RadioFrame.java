
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class RadioFrame {
	
	private int starter = 0;
	public int index;
	private int i = 0;
	public Visualization vis;
	
	public int profile;
	
	public boolean runVis = false;
	public String question;
	private ArrayList<String> questions = new ArrayList<String>();
	private String[] questionsToAdd = {	//heavy metal
								/* 0 */ "Do you consider yourself a violent person?",
										"Do you like really loud noises and sounds?",
										"Do you like artists like Slipknot?", 
										"Do you like to head bang?",
								
										//rock
								/* 4 */ "Do you battle it out on guitar hero?",
										"Do you practice your “air guitar” constantly?",
										"Do you like artists like Aerosmith or ACDC?",
										"If there's a drum-set nearby, will you play around with it?",
										"Have you ever been to a rock concert?",
								
										//rap/hip-hop
								/* 9 */ "Do you like to release your inner hood?",
										"Does being gangster sound like an average day for you?",
										"Do you like to bust out rhymes?",
										"Do you like a good rap battle?",
										"Do you like artists like Jay-Z or Tupac?",
								
										//dance/electronic
								/* 14 */"Did you excitedly go see the Transformers movies when released?",
										"Do you like artists like Skrillex or Deadmau5?",
										"Do you like to go to raves?",
										"Do you practice dancing the robot in your spare time?",
										
								
										//pop
								/* 18 */"Do you like music that plays on the radio?",
										"Do you like artists like Katy Perry or Bruno Mars?",
										"Have you ever been to a pop concert?",
										"Would you consider yourself someone who watches Disney Channel?",
								
										//country
								/* 22 */"Do you like to bring out your inner cowboy/cowgirl?",
										"Do you like artists like Taylor Swift or Tim McGraw?",
										"Does southern folk interest you at all?",
										"Do you like bull riding or rodeos?",
										"Do you like Texas?",
										"Do you like to square dance?",
								
										//classical
								/* 28 */"Do you like Beethoven or Bach?",
										"Does going to see the Seattle symphony interest you?",
										"Do you like being classy like Mozart?",
										"Does the sound of stringed instruments get you going?" };

	public RadioFrame(Visualization vis) {
		if (this.starter == 0) {
			for (int i = 0; i < questionsToAdd.length; i++) {
				questions.add(questionsToAdd[i]);
			}
			this.starter = 1;
		}

		this.vis = vis;
		questionStrings();
	}
	
    @SuppressWarnings("unused")
	public void populateContentPane(Container contentPane, JFrame jframe) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        
        //Create the frame options, these are possible utilities
        JLabel questionLine = new JLabel(question);
        //JLabel questionLine2 = new JLabel(" e Insert question here Insert question here Inse...");
        JLabel empty1 = new JLabel(" ");
        JLabel empty2 = new JLabel(" ");
        
        //Create the buttons
        YesButton yesButton = new YesButton(this, jframe);
        yesButton.setVisible(true);
        
        NoButton noButton = new NoButton(this, jframe);
        noButton.setVisible(true);
        
        MaybeButton maybeButton = new MaybeButton(this, jframe);
        maybeButton.setVisible(true);
        
        //Create boxes and add components to them
        Box boxy = Box.createVerticalBox();
        Box box = Box.createHorizontalBox();
        
    	//JButton iconButton = new JButton();
        JButton iconButton = new JButton();
    	iconButton.setIcon(new ImageIcon("C:/Users/KellaN/workspace/Smart Radio/src/SMARTradio.png"));
        iconButton.setVisible(true);
        
    	boxy.add(iconButton);
        
        boxy.add(questionLine);
        //boxy.add(questionLine2);
        boxy.add(empty1);
        //boxy.add(empty2);
        
        box.add(yesButton);
        box.add(noButton);
        box.add(maybeButton);
        
        //Add boxes to the pane
        contentPane.add(boxy, BorderLayout.CENTER);
        contentPane.add(box, BorderLayout.AFTER_LAST_LINE);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI(RadioFrame rad) {
        //Create and set up the window.
        JFrame frame = new JFrame("SMART Radio");
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(new Point(500, 300));
        frame.setResizable(false);
        //frame.setSize(new Dimension(300, 300));
    	
        //Create and set up the content pane.
        rad.populateContentPane(frame.getContentPane(), frame);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public void questionStrings() {
    	int[] unusedIndexes = new int[questions.size()];
    	for (int a = 0; a < questions.size(); a++) {
    		unusedIndexes[a] = a;
    	}
    	
    	if (i < 6) {
			int x = (int) (Math.random() * unusedIndexes.length);
			
			while (unusedIndexes[x] == -1) {
				x = (int) (Math.random() * unusedIndexes.length);
			}
			
			this.index = unusedIndexes[x];
			this.question = questions.get(x);
			unusedIndexes[x] = -1;
			askQuestion(this);
			i++;
		} else {
			//return;
			String path = chooseSong(profile, vis);
			if (!runVis) {
				SoundPlayer.playSong(path);
				Player musicPlayer = new Player();
				runVis = true;
			}
		}
    }
    
    public void askQuestion(RadioFrame rad) {
    	createAndShowGUI(rad);
    }
    
    public void setAnswer(int answer) {
    	this.profile += answer;
    }
	
    public String chooseSong(int profile, Visualization vis) {
    	int rand = 0;
    	if (-6 <= profile && profile <= -3) {
    		rand = ((int) (Math.random() * vis.inds.length));
    		return vis.paths[vis.inds[rand]];
    	} else if (-2 <= profile && profile <= 0) {
    		rand = ((int) (Math.random() * vis.inds2.length));
    		return vis.paths[vis.inds2[rand]];
    	} else if (1 <= profile && profile <= 3) {
    		rand = ((int) (Math.random() * vis.inds3.length));
    		return vis.paths[vis.inds3[rand]];
    	} else if (4 <= profile && profile <= 6) {
    		rand = ((int) (Math.random() * vis.inds4.length));
    		return vis.paths[vis.inds4[rand]];
    	}
    	return vis.paths[0];
    }
}
