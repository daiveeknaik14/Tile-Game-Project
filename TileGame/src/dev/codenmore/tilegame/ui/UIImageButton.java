package dev.codenmore.tilegame.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.image.BufferedImage;

import javax.swing.RepaintManager;

public class UIImageButton extends UIObject {

	private BufferedImage[] images;
	private ClickListener clicker;
	
	public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
		super(x, y, width, height);
		this.images = images;
		this.clicker = clicker;
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.CYAN);	
		g.fillRect(0, 0, 1000, 1000);
		g.setColor(Color.GRAY);
		g.setFont(new Font("Courier",Font.BOLD,20));
		g.drawString("INSTRUCTIONS:", 80, 60);
		g.drawString("->W,A,S,D TO MOVE", 80, 100);
		g.drawString("->USE ARROW KEYS TO BREAK ROCKS AND CUT TREES", 80, 140);
		g.drawString("->REACH THE FINISH TILE", 80,180);
		g.drawString("GAME MADE BY KRUSHITA PATEL AND DAIVEEK NAIK",80,380);
		

		if(hovering)
			g.drawImage(images[1], (int) x+55, (int) y+50, width, height, null);
		else {
			g.drawImage(images[0], (int) x+55, (int) y+50, width, height, null);
		}
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}

}
