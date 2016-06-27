package de.bht.wht.uebung;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
public class Experiment extends JPanel {
  Color color;
  
  public Experiment(Color color) {
    this.color = color;
    this.validate();
  }
  
  public void paintComponent(Graphics g) {
    int width = getWidth();
    int height = getHeight();
    g.setColor(color);
    g.fillRect(0, 0, width, height);
    g.drawRect( 0, 0, width, height);
  } 
  
  public void setColor(Color newColor){
	  this.color = newColor;
  }
  
  public Color getColor(){
	  return this.color;
  }
}