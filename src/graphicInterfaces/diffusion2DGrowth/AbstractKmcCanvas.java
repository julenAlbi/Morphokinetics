/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicInterfaces.diffusion2DGrowth;

import kineticMonteCarlo.lattice.Abstract2DDiffusionLattice;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Nestor
 */
public abstract class AbstractKmcCanvas extends Canvas {

  public static float constant_Y = (float) Math.sqrt(3) / 2.0f;

  protected int baseX = 0;
  protected int baseY = 0;
  protected BufferStrategy strategy;  //BufferStratrgy
  protected boolean initialized = false;
  protected Abstract2DDiffusionLattice lattice;
  public int scale = 2;

  public AbstractKmcCanvas(Abstract2DDiffusionLattice lattice) {
    this.lattice = lattice;
  }

  public void setBaseLocation(int baseX, int baseY) {
    this.baseX += baseX;
    this.baseY += baseY;
  }

  public int getScale() {
    return scale;
  }

  public int getSizeX() {
    return this.lattice.getSizeX() * scale;
  }

  public int getSizeY() {
    return this.lattice.getSizeY() * scale;
  }

  public void setScale(int scale) {
    this.scale = scale;
  }

  public AbstractKmcCanvas() {   //constructor
    this.setIgnoreRepaint(true); //we repaint manually
    this.setFocusable(false);
  }

  public void dispose() {
    strategy.dispose();
  }

  /**
   * call this before starting game loop, it initializes the bufferStrategy
   */
  public void initialize() {
    createBufferStrategy(2);  //double buffering
    strategy = getBufferStrategy();
  }

  /**
   * public drawing method, call this from your game loop for update image
   */
  public void performDraw() {

    Graphics g;
    try {
      g = strategy.getDrawGraphics();
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
    this.paint((Graphics2D) g);
    g.dispose();

    if (!strategy.contentsLost()) {
      strategy.show();
    }

  }

  /**
   * This method prints the current canvas to a file
   *
   * @param i Simulation number
   */
  public void performDrawToImage(int i) {
    BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = image.createGraphics();
    paint(graphics);
    graphics.dispose();
    try {
      String imageName = "outfile_simulation_" + i + ".png";
      System.out.println("Exporting image: " + imageName);
      FileOutputStream out = new FileOutputStream(imageName);
      ImageIO.write(image, "png", out);
      out.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
