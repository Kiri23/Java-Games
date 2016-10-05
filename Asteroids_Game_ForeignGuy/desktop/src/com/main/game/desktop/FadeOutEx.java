package com.main.game.desktop;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Surface extends JPanel
implements ActionListener {

	private Image img;
	private Timer timer;
	private float alpha = 0f;

	private final int DELAY = 40;
	private final int INITIAL_DELAY = 500;
	int h;
	int w;
	String [] array;
	String hola = "Hola:soy:una:String";

	public Surface() {

		loadImage();
		setSurfaceSize();
		initTimer();
	}

	private void loadImage() {

		img = new ImageIcon("/Users/Christiannogueras/Documents/Eclipse_Java_Gaming_Workspace/"
				+ "Asteroids_Game_ForeignGuy/core/assets/badlogic.jpg").getImage();
	}

	private void setSurfaceSize() {

		h = img.getHeight(this);
		w = img.getWidth(this);
		setPreferredSize(new Dimension(250,250));
	}

	private void initTimer() {

		timer = new Timer(5, this);
		timer.setInitialDelay(1);
		timer.start();
	}

	private void doDrawing(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();
		Graphics2D g3d = (Graphics2D)g.create();

		AlphaComposite acomp = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, alpha);
		g2d.setComposite(acomp);
		//g3d.drawImage(img, 0, 0, null);
		g2d.setColor(Color.BLUE);
		g2d.setFont(new Font(Font.SERIF, Font.PLAIN, 30));

		g2d.drawString(hola, 50, 100);

		g2d.dispose();
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		doDrawing(g);
	}

	private void step() {
		alpha += 0.01f;


		if (alpha >= 1) {

			alpha = 0;
			// timer.stop();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		step();
		repaint();
	}
}

public class FadeOutEx extends JFrame {

	public FadeOutEx() {

		initUI();
	}

	private void initUI() {

		add(new Surface());

		pack();

		setTitle("Fade out");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				FadeOutEx ex = new FadeOutEx();
				ex.setVisible(true);
			}
		});
	}
}