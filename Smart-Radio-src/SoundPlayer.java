import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class SoundPlayer {
	public int shouldPlay;
	

	
	public static void playSong(String soundFile) {		
		try {	
			URL path = SoundPlayer.class.getClassLoader().getResource(soundFile);
			if(path == null)
				throw new Exception (soundFile + "not found");
			AudioStream audioStream = new AudioStream(path.openStream());
			AudioPlayer.player.start(audioStream);
		} catch (Exception e) {
			System.out.println("There was an error!!" + e.getMessage());
		}
	}

}
