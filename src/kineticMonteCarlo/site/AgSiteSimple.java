/* 
 * Copyright (C) 2018 J. Alberdi-Rodriguez
 *
 * This file is part of Morphokinetics.
 *
 * Morphokinetics is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Morphokinetics is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Morphokinetics.  If not, see <http://www.gnu.org/licenses/>.
 */
package kineticMonteCarlo.site;

import utils.StaticRandom;

/**
 *
 * @author J. Alberdi-Rodriguez
 */
public class AgSiteSimple extends AgSite {
  
  public AgSiteSimple(int id, short iHexa, short jHexa) {
    super(id, iHexa, jHexa);
  }

  public AgSiteSimple(int id, int i) {
    super(id, i);
  }
  
  @Override
  public boolean isPartOfImmobilSubstrate() {
    return false;
  }
          
          
  /**
   * Returns the type of the neighbour atom if current one would not exist. Essentially, 
   * it has one less neighbour.
   *
   * @param position ignored.
   * @return the type.
   */
  @Override
  public byte getTypeWithoutNeighbour(int position) {
    return (byte) (getType() - 1);
  }
  
  /**
   * Returns the real type of the current atom. This means nothing, 
   * in the current atom class is the same as the type.
   *
   * @return real type of the current atom.
   */
  @Override
  public byte getRealType() {
    return getType();
  }
  
  @Override
  public boolean isEligible() {
    return isOccupied() && getType() < 4;
  }
  
  /**
   * Simpler implementation than {@link AgSite#chooseRandomHop()}. This one, does not consider
   * corner rounding.
   *
   * @return
   */
  @Override
  public AbstractGrowthSite chooseRandomHop() {

    double linearSearch = StaticRandom.raw() * getProbability();

    double sum = 0;
    int cont = 0;
    while (true) {
      sum += getBondsProbability(cont++);
      if (sum >= linearSearch) {
        break;
      }
      if (cont == getNumberOfNeighbours()) {
        break;
      }
    }
    cont--;
    return getNeighbour(cont);
  }
    
  @Override
  public double probJumpToNeighbour(int ignored, int position) {
    if (getNeighbour(position).isOccupied()) {
      return 0;
    }

    byte originType = getType();
    byte destination = getNeighbour(position).getTypeWithoutNeighbour(position);

    return getProbability(originType, destination);
  }

  /**
   * This method tells if two terraces are going to form a dimer. This method should be called from
   * an empty lattice location. In affirmative case, generally, it has two terrace neighbours.
   *
   * @return true if two terraces are going to be together.
   */
  @Override
  public boolean areTwoTerracesTogether() {
    switch (getType()) {
      //case 1:
      //  return unoccupiedCornerOneTerrace();
      case 2:
        return unoccupiedEdgeTwoTerraces();
      case 3:
        return unoccupiedKinkThreeTerraces();
      default:
        return false;

    }
  }
  
  /**
   * It has two neighbours. Check if those neighbours are terraces.
   *
   * @return
   */
  private boolean unoccupiedEdgeTwoTerraces() {
    int cont = 0;
    int i = 0;
    while (cont < 2 && i < getNumberOfNeighbours()) {
      if (getNeighbour(i).isOccupied()) {
        if (getNeighbour(i).getType() != TERRACE) {
          return false;
        }
        cont++;
      }
      i++;
    }
    return true;
    
  }
  
  /**
   * It has three neighbours. Check if those neighbours are terraces.
   *
   * @return
   */
  private boolean unoccupiedKinkThreeTerraces() {
    int cont = 0;
    int i = 0;
    while (cont < 3 && i < getNumberOfNeighbours()) {
      if (getNeighbour(i).isOccupied()) {
        if (getNeighbour(i).getType() != TERRACE) {
          return false;
        }
        cont++;
      }
      i++;
    }
    return true;
    
  }
}
