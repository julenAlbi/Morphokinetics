/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticAlgorithm.populationInitialisation;

import geneticAlgorithm.Population;

/**
 *
 * @author Nestor
 */
public class AgInitialisator extends AbstractInitialisator implements IInitialisator {

  /**
   * Robust initialisation methods, it uses a logarithmic distribution of process rates, more
   * similar to what is expected from a real system.
   *
   * @param populationSize
   * @return new Population with given parameters
   */
  @Override
  public Population createRandomPopulation(int populationSize) {
    return  createRandomPopulation(populationSize, 49, 0.1, 10000000, true);
  }
}