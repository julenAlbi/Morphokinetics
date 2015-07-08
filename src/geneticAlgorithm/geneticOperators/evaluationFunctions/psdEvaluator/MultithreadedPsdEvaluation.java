/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticAlgorithm.geneticOperators.evaluationFunctions.psdEvaluator;

import kineticMonteCarlo.kmcCore.worker.IFinishListener;
import kineticMonteCarlo.kmcCore.worker.IIntervalListener;
import kineticMonteCarlo.kmcCore.worker.KmcWorker;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Nestor
 */
public abstract class MultithreadedPsdEvaluation extends AbstractPSDEvaluation implements IFinishListener, IIntervalListener {

    protected KmcWorker[] workers;
    protected int num_threads;
    protected int finishedSimulation;
    protected Semaphore evalation_complete;

    
    public MultithreadedPsdEvaluation(int repeats, int measureInterval,int num_threads) {
        super(repeats, measureInterval);
        
        this.workers=new KmcWorker[num_threads];
        this.num_threads=num_threads;
        evalation_complete = new Semaphore(0);
    }
    
    @Override
    public synchronized void handleSimulationFinish(int workerID, int workID) {
        
        finishedSimulation++;
        if (currentSimulation < currentPopulation.size() * repeats) {
            assignNewWork(workerID);
        }

        if (finishedSimulation == currentPopulation.size() * repeats) {
            evalation_complete.release();
        }
    }
    
    
    protected void assignNewWork(int workerID) {
               
        int individual = currentSimulation / repeats;
        
        workers[workerID].initialize(currentPopulation.getIndividual(individual).getGenes());
        workers[workerID].simulate(this, this, measureInterval, individual);
        currentSimulation++;  
    }
    
    public void dispose(){
    
    for (int i=0;i<workers.length;i++) workers[i].destroy();
    
    }
    
    

}
