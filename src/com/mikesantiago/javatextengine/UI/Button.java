package com.mikesantiago.javatextengine.UI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.mikesantiago.javatextengine.Core.SimpleGLDrawer;
import com.mikesantiago.javatextengine.Core.WindowManager;
import com.mikesantiago.javatextengine.Core.SimpleGLDrawer.FONTSIZE;

public class Button 
{
	private String buttonName, buttonText;
	private float x, y;
	private Texture leftSide, center, rightSide;
	private float width, height;

	/**
	 * We load the texture depending on the texture pack
	 * @param buttonName
	 * @param buttonText
	 * @param x
	 * @param y
	 */
	public Button(String buttonName, String buttonText, float x, float y, int width, int height)
	{
		this.buttonName = buttonName;
		this.buttonText = buttonText;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		leftSide = SimpleGLDrawer.QuickLoad("/ui/button-leftside");
		center = SimpleGLDrawer.QuickLoad("/ui/button-center");
		rightSide = SimpleGLDrawer.QuickLoad("/ui/button-rightside");
	}
	
	public void Draw()
	{
		//The x and y values given are relative from the left side of the button
		
		int textWidth = SimpleGLDrawer.GetFontWidth(FONTSIZE.SMALL, buttonText);
		int totalWidthOfButton = (int)width + 4;
		int whereToStartText = ((totalWidthOfButton / 2) - textWidth) + (int)x;
		
		SimpleGLDrawer.DrawRectangle(leftSide, x, y, 32, 32);
		SimpleGLDrawer.DrawRectangleRepeating(center, (x + 2), y, width - 4 /*we minus 4 to account for the end cap, times 2 to allow to fit on the grid*/, 32);
		SimpleGLDrawer.DrawRectangle(rightSide, width * 32, y, 32, 32); //34 is just a random constant
		if(isMouseInside())
			SimpleGLDrawer.DrawText(buttonText, whereToStartText, y + 8, FONTSIZE.SMALL, Color.yellow);
		else
			SimpleGLDrawer.DrawText(buttonText, whereToStartText, y + 8, FONTSIZE.SMALL, Color.white);
	}
	
	public boolean isMouseInside()
	{
		float startX = x;
		float startY = y;
		float endY = y + 32;
		float endX = x + width;
		float mouseY = WindowManager.SCREEN_HEIGHT - Mouse.getY() - 1;
		
		if(Mouse.getX() > startX && Mouse.getX() < endX && mouseY > startY && mouseY < endY)
		{
			return true;
		}
		else
			return false;
	}
	
	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public String getButtonText() {
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Texture getLeftSide() {
		return leftSide;
	}

	public void setLeftSide(Texture leftSide) {
		this.leftSide = leftSide;
	}

	public Texture getCenter() {
		return center;
	}

	public void setCenter(Texture center) {
		this.center = center;
	}

	public Texture getRightSide() {
		return rightSide;
	}

	public void setRightSide(Texture rightSide) {
		this.rightSide = rightSide;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
	
}
