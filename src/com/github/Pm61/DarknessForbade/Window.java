package com.github.Pm61.DarknessForbade;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


//DEPRECATED 
//in favor of gdx api system
public class Window extends JPanel implements MouseListener, MouseMotionListener {
	
	int width, height;
	int mouseX, mouseY, startX, startY;
	
	boolean guiOn;
	
	File splatterFilterFile = new File("SplatterTransparent.png");
	Image splatterFilter;
	
	BufferedImage display;
	
	DarknessForbade parent;
	
	public Window(DarknessForbade parent) {	
		this.parent = parent;
		width = 640;
		height = 400;
		display =  new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		setImage(display);
		Graphics g = display.getGraphics();
	    g.setColor(Color.GRAY);
	    g.fillRect(0, 0, width, height);
	    g.dispose();
		try {
			splatterFilter = ImageIO.read(splatterFilterFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		guiOn = false;
		createGUI();
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g.setColor(Color.GRAY);
	    g.fillRect(0, 0, width, height);
		g2.drawImage(display, 0, 0, null);
//		g2.setColor(Color.RED);
//		g2.draw3DRect(320, 200, 320-mouseX, 200-mouseY, true);
//		
//		
//		g2.drawImage(splatterFilter, 0, 0, null);
		g2.dispose();
	}
	
	public void createGUI() {
		if(!guiOn) {
			Graphics2D g = this.display.createGraphics();
			
			g.drawImage(splatterFilter, 0, 0, null);
			
			guiOn = true;
			g.dispose();
			this.repaint();
		}
	}
	
	public void setImage(BufferedImage image) {
		display = image;
		height = image.getHeight();
		width = image.getWidth();
		Graphics2D g = display.createGraphics();
		
	}
	public BufferedImage getImage() {
		return display;
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		startX = e.getX();
		startY = e.getY();
	}

	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
		mouseX = e.getX();
		mouseY = e.getY();
		Graphics g = display.getGraphics();
		g.setColor(Color.GRAY);
	    g.fillRect(0, 0, width, height);
	    g.setColor(Color.WHITE);
	    if(startX < mouseX && startY < mouseY) 
	    	g.fillRect(startX, startY, mouseX-startX, mouseY-startY);
	    else if(startX > mouseX && startY < mouseY) 
	    	g.fillRect(mouseX, startY, startX-mouseX, mouseY-startY);
	    else if(startX < mouseX && startY > mouseY) 
	    	g.fillRect(startX, mouseY, mouseX-startX, startY-mouseY);
	    else if(startX > mouseX && startY > mouseY) 
	    	g.fillRect(mouseX, mouseY, startX-mouseX, startY-mouseY);
	    
	    g.setColor(Color.GREEN);
	    if(startX < mouseX && startY < mouseY) 
	    	g.drawRect(startX, startY, mouseX-startX, mouseY-startY);
	    else if(startX > mouseX && startY < mouseY) 
	    	g.drawRect(mouseX, startY, startX-mouseX, mouseY-startY);
	    else if(startX < mouseX && startY > mouseY) 
	    	g.drawRect(startX, mouseY, mouseX-startX, startY-mouseY);
	    else if(startX > mouseX && startY > mouseY) 
	    	g.drawRect(mouseX, mouseY, startX-mouseX, startY-mouseY);
	    
	    g.drawImage(splatterFilter, 0, 0, null);
	    g.dispose();
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
//		Graphics g = display.getGraphics();
//		g.setColor(Color.GRAY);
//	    g.fillRect(0, 0, width, height);
//	    g.setColor(Color.WHITE);
//	    g.drawLine(5, 5, mouseX, mouseY);
//	    g.drawImage(splatterFilter, 0, 0, null);
//	    g.dispose();
//		repaint();
	}
}
