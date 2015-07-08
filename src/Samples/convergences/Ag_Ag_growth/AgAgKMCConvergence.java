/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Samples.convergences.Ag_Ag_growth;

import geneticAlgorithm.geneticOperators.evaluationFunctions.psdEvaluator.AbstractPSDEvaluation;
import geneticAlgorithm.GeneticAlgorithm;
import geneticAlgorithm.GeneticAlgorithmConfiguration;
import geneticAlgorithm.IGeneticAlgorithm;
import geneticAlgorithm.Individual;
import geneticAlgorithm.geneticAlgorithmDatabase.genetic_algorithm_config_factory;
import graphicInterfaces.gaConvergence.GaProgressFrame;
import ratesLibrary.siEtching.SiEtchRatesFactory;
import ratesLibrary.diffusion.agAgGrowth.AgAgGrowthRatesFactory;

/**
 *
 * @author Nestor
 */
public class AgAgKMCConvergence {

    public static void main(String[] args) {

        float experitental_temp = 135;
        double deposition_rate = new AgAgGrowthRatesFactory().getDepositionRate("COX_PRB", experitental_temp);
        double island_density = new AgAgGrowthRatesFactory().getIslandDensity("COX_PRB", experitental_temp);
        double diffusion_rate = new AgAgGrowthRatesFactory().getRates("COX_PRB", experitental_temp)[0];

        GeneticAlgorithmConfiguration geneticConfiguration = new genetic_algorithm_config_factory()
                .create_Ag_Ag_convergence_configuration(diffusion_rate, island_density, deposition_rate);

        GeneticAlgorithm GA = new GeneticAlgorithm(geneticConfiguration);

        new GaProgressFrame(GA).setVisible(true);
        AbstractPSDEvaluation evaluator = geneticConfiguration.mainEvaluator;

        //--------------------------------
        evaluator.setRepeats(evaluator.getRepeats() * 5);
        Individual individual = new Individual(new AgAgGrowthRatesFactory().getRates("COX_PRB", experitental_temp));
        float[][] experimentalPSD = evaluator.calculate_PSD_from_individual(individual);
        double simulationTime = individual.getSimulationTime();
        evaluator.setRepeats(evaluator.getRepeats() / 5);
         //--------------------------------
        
        geneticConfiguration.setExperimentalPSD(experimentalPSD);
        geneticConfiguration.expected_simulation_time = simulationTime;

        GA.initialize();
        GA.iterate(100);
        printResult(GA);

    }
    
        private static void printResult(IGeneticAlgorithm GA) {
        Individual individual = GA.getIndividual(0);
        System.out.print(individual.getTotalError() + " ");
        for (int gene = 0; gene < individual.getGeneSize(); gene++) {
            System.out.print(individual.getGene(gene) + " ");
        }
        System.out.println();
    }
    
    

}
