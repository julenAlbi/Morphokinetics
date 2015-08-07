/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import graphicInterfaces.diffusion2DGrowth.DiffusionKmcFrame;
import graphicInterfaces.diffusion2DGrowth.grapheneCvdGrowth.GrapheneKmcCanvas;
import kineticMonteCarlo.kmcCore.diffusion.GrapheneKmc;
import kineticMonteCarlo.lattice.Abstract2DDiffusionLattice;
import ratesLibrary.GrapheneRatesFactory;

/**
 *
 * @author J. Alberdi-Rodriguez
 */
public class GrapheneSimulation extends AbstractGrowthSimulation {

  public GrapheneSimulation(Parser parser) {
    super(parser);
  }

  @Override
  public void initialiseKmc() {
    super.initialiseKmc();
    
    this.ratesFactory = new GrapheneRatesFactory();
    this.kmc = new GrapheneKmc(config, 
            parser.getCartSizeX(), 
            parser.getCartSizeY(), 
            parser.justCentralFlake(), 
            parser.randomSeed(), 
            parser.useMaxPerimeter(),
            parser.getPerimeterType());
  }

  @Override
  public void createFrame() {
    try {
      frame = new DiffusionKmcFrame(new GrapheneKmcCanvas((Abstract2DDiffusionLattice) kmc.getLattice()));
    } catch (Exception e) {
      System.err.println("Error: The execution is not able to create the X11 frame");
      System.err.println("Finishing");
      throw e;
    }
    if (parser.visualize()) {
      frame.setVisible(true);
    }
  }
}
