/*package gui;

import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Color;
import java.lang.*;

public class Triangle extends CenteredGraphicalElement
{
    private Color drawColor;
    private Color fillColor;
    private int height;
    private double angle;
    
    public Triangle(final int x, final int y, final double angle, final Color drawColor, final Color fillColor,  final int height) {
        super(x, y);
        this.drawColor = drawColor;
        this.fillColor = fillColor;
	this.height = height;
	this.angle = angle;
    }
    
    public void paint(final Graphics2D g2d) {
	int[] xPoints = {(int)(this.getX() - Math.sin(angle) * height / 1.5), (int)(this.getX() + height * Math.sin(angle - Math.toRadians(56.31)) / 2.5), (int)(this.getX() + height * Math.sin(angle + Math.toRadians(56.31)) / 2.5)};
	int[] yPoints = {(int)(this.getY() - height * Math.cos(angle) / 1.5), (int)(this.getY() + height * Math.cos(angle - Math.toRadians(56.31)) / 2.5), (int)(this.getY() + height * Math.cos(angle + Math.toRadians(56.31)) / 2.5)};
	//System.out.println(xPoints[0] + " " + xPoints[1] + " " + xPoints[2]);
	//System.out.println(yPoints[0] + " " + yPoints[1] + " " + yPoints[2]);
        final Stroke currentStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(2.0f));
        final Color current = g2d.getColor();
        if (this.fillColor != null) {
            g2d.setColor(this.fillColor);
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
        g2d.setColor(this.drawColor);
        g2d.drawPolygon(xPoints, yPoints, 3);
        g2d.setColor(current);
        g2d.setStroke(currentStroke);
    }
    
    public String toString() {
        return String.valueOf(this.drawColor.toString()) + " triangle";
    }
}*/
