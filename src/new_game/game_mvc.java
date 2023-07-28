package new_game;

import java.awt.*;
import javax.swing.*;

class game_mvc {
	public static void main(String[] args) {
		new MyPanel();
		game_controller mgr = new game_controller();
		mgr.run();
	}
}
