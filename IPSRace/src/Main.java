import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import ui.GraphicsUI;
import ui.TextUI;
import ui.UI;

public class Main {

	public static void main(String[] args) throws IOException {
		String mode = null;
		String track = null;
		String[] players = null;
		UI ui;
		
		if (args.length > 3 && args.length < 8) {
			if ("text".equals(args[0].toLowerCase()) || "graphics".equals(args[0].toLowerCase()))
				mode = args[0].toLowerCase();
			if ((new File(args[1])).exists())
				track = args[1];
			players = Arrays.copyOfRange(args, 2, args.length);
		}
		
		if (mode == null || track == null)
			usage();
		else {
			if ("text".equals(mode))
				ui = new TextUI(track, players);
			else
				ui = new GraphicsUI(track, players);
			ui.play();
		}
	}

	private static void usage() {
		System.out.println("Usage:");
		System.out.println("  java -jar IPSRace.jar text|graphics trackfile player...");
	}
}
