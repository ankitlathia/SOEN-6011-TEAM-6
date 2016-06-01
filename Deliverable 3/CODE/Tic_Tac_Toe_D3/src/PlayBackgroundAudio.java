import javax.sound.midi.*;
import javax.swing.JOptionPane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

class playBackgroundAudio {
	static Sequencer sequencer = null;
    public static void playAudio(URL resource) throws MalformedURLException {
        URL url = null;
		url = resource;

        Sequence sequence = null;
		try {
			sequence = MidiSystem.getSequence(url);
		} catch (InvalidMidiDataException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		try {
			sequencer = MidiSystem.getSequencer();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
			sequencer.open();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			sequencer.setSequence(sequence);
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        sequencer.start();
        
    }
    
    public static void stopAudio() {
    	sequencer.stop();
    }

	
}