package com.journaldev.design.structural.flyweight;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.journaldev.design.structural.flyweight.ShapeFactory.ShapeType;

public class FlyweightTest extends JFrame {

	private static final long serialVersionUID = -1350200437285282550L;
	private final int WIDTH;
	private final int HEIGHT;

	private static final ShapeType shapeTypes[] = { ShapeType.LINE, ShapeType.OVAL_FILL, ShapeType.OVAL_NOFILL };
	private static final Color colors[] = { Color.RED, Color.GREEN, Color.YELLOW };

	public FlyweightTest(int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;

		Container contentPane = getContentPane();

		JButton drawButton = new JButton("Draw");
		final JPanel panel = new JPanel();

		contentPane.add(panel, BorderLayout.CENTER);
		contentPane.add(drawButton, BorderLayout.SOUTH);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		drawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Graphics graphics = panel.getGraphics();
				for (int counter = 0; counter < 20; ++counter) {
					// Retrieves the instance of a random shape.
					Shape shape = ShapeFactory.getShape(getRandomShape());
					// Requests the drawing of the random shape.
					// Observation: Since the object instance is shared, it cannot store properties
					// such as X and Y position, width and height. This is a extrinsic property (i.
					// e. its coordinates and dimension are based on the JFrame class drawing
					// coordinates which is extended by the FlyweightTest class).
					shape.draw(graphics, getRandomX(), getRandomY(), getRandomWidth(), getRandomHeight(),
							getRandomColor());
				}
			}
		});
	}

	private ShapeType getRandomShape() {
		return shapeTypes[(int) (Math.random() * shapeTypes.length)];
	}

	private int getRandomX() {
		return (int) (Math.random() * WIDTH);
	}

	private int getRandomY() {
		return (int) (Math.random() * HEIGHT);
	}

	private int getRandomWidth() {
		return (int) (Math.random() * (WIDTH / 10));
	}

	private int getRandomHeight() {
		return (int) (Math.random() * (HEIGHT / 10));
	}

	private Color getRandomColor() {
		return colors[(int) (Math.random() * colors.length)];
	}

	public static void main(String[] args) {
		new FlyweightTest(500, 600);
	}
}