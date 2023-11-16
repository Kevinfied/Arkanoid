/*
	Util.java
	Kevin Xu

	Contains all my utilities! I still dont understand half of them
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;



public class Util {
    public static int randInt(int a, int b){
		return (int)(Math.random()*(b-a+1)+a);
	}
	public static double randDouble(double a, double b){
		return (Math.random()*(b-a)+a);
	}
	public static Image loadImg(String fileName) {
		return new ImageIcon(fileName).getImage();
	}
	public static Image loadScaledImg(String fileName, int width, int height){ //returns scaled Image with file name fileName, width, and height
		Image img = loadImg(fileName);
		return img.getScaledInstance(width, height, Image.SCALE_SMOOTH); //returns scaled image
	}
}