/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Genetic_Algorithm.Genetic_Operators.Restrictions;

import Genetic_Algorithm.Individual;

/**
 *
 * @author Nestor
 */
public abstract class Gene_restriction {
    
    
    
    public static final int NO_RESTRICTION=0;
    public static final int BOUNDED_VALUES=1;
    public static final int REPLICATE_GENE=2;
    public static final int FIXED_VALUE=3;
    
    protected int genePosition;    
    
    
    public Gene_restriction(int genePosition){this.genePosition=genePosition;}
    
    public abstract void restrictGene(Individual i);
    
    public abstract int getRestrictionType();

    public int getGenePosition() {
        return genePosition;
    }
    
    
    
    
    
    
    
    
    
}
