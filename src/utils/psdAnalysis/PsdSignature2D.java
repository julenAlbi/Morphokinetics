/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.psdAnalysis;

import basic.io.Restart;
import edu.emory.mathcs.jtransforms.fft.FloatFFT_2D;
import java.util.concurrent.Semaphore;

import java.util.ArrayList;
import utils.MathUtils;

/**
 *
 * @author Nestor
 */
public class PsdSignature2D {

  private FloatFFT_2D fftCore;
  private float[][] psd;
  private ArrayList<float[][]> psdVector;
  private float[][] psdTmp;
  private float[][] buffer;
  private int measures;
  private Semaphore semaphore;
  private boolean averaged = false;
  private int binsY;
  private int binsX;
  private Restart restart;
  public static final int HORIZONTAL_SIMMETRY = 0;
  public static final int VERTICAL_SIMMETRY = 1;

  public PsdSignature2D(int binsY, int binsX) {

    fftCore = new FloatFFT_2D(binsY, binsX);
    psd = new float[binsY][binsX];
    psdTmp = new float[binsY][binsX];
    buffer = new float[binsY][binsX * 2];
    measures = 0;
    semaphore = new Semaphore(1);
    psdVector = new ArrayList<>();
    this.binsY = binsY;
    this.binsX = binsX;
    restart = new Restart();
  }

  public void addSurfaceSample(float[][] sampledSurface) {

    if (averaged) {
      throw new RuntimeException("PSD measures averaged, new samples cannot be added without signature reset.");
    }

    try {
      semaphore.acquire();
    } catch (InterruptedException e) {
      System.err.println("Thread interrupted while writting PSD signature");
    }

    for (int i = 0; i < sampledSurface.length; i++) {
      System.arraycopy(sampledSurface[i], 0, buffer[i], 0, sampledSurface[0].length);
    }

    fftCore.realForwardFull(buffer);

    for (int i = 0; i < binsY; i++) {
      for (int j = 0; j < binsX; j++) {
        psdTmp[i][j] = buffer[i][j * 2] * buffer[i][j * 2] + buffer[i][j * 2 + 1] * buffer[i][j * 2 + 1];
        psd[i][j] += psdTmp[i][j];
      }
    }
    psdVector.add(psdTmp);
    measures++;
    semaphore.release();

  }

  public float[][] getPsd() {

    if (!averaged) {
      for (int i = 0; i < binsY; i++) {
        for (int j = 0; j < binsX; j++) {
          psd[i][j] /= measures;
        }
      }
      averaged = true;
    }
    return psd;
  }

  public void reset() {
    averaged = false;
    measures = 0;
    psd = new float[binsY][binsX];
  }

  public void applySimmetryFold(int simmetryType) {
    switch (simmetryType) {
      case HORIZONTAL_SIMMETRY:
        for (int i = 0; i < binsY; i++) {
          for (int j = 1; j < binsX / 2; j++) {
            float temp = (psd[i][j] + psd[i][binsX - j - 1]) * 0.5f;
            psd[i][j] = psd[i][binsX - j - 1] = temp;
          }
        }
        break;

      case VERTICAL_SIMMETRY:
        for (int i = 1; i < binsY / 2; i++) {
          for (int j = 0; j < binsX; j++) {
            float temp = (psd[i][j] + psd[binsY - i - 1][j]) * 0.5f;
            psd[i][j] = psd[binsY - i - 1][j] = temp;
          }
        }
        break;
    }
  }

  public void printToFile(int simulationNumber) {
    int dimensions = 2;
    int sizes[] = new int[2];
    sizes[0] = binsY;
    sizes[1] = binsX;
    restart.writePsdBinary(dimensions, sizes, psdVector.get(simulationNumber), simulationNumber);
  }

  public void printAvgToFile(){
    int dimensions = 2;
    int sizes[] = new int[2];
    sizes[0] = binsY;
    sizes[1] = binsX;
    restart.writePsdBinary(dimensions, sizes, MathUtils.avgFilter(this.getPsd(), 1), "psdAvgFil");
    restart.writePsdBinary(dimensions, sizes, this.getPsd(), "psdAvgRaw");
  }
  
  public void setRestart(Restart restart) {
    this.restart = restart;
  }
}
