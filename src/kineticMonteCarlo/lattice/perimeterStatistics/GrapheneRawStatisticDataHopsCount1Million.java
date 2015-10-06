/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kineticMonteCarlo.lattice.perimeterStatistics;

/**
 * Estos datos corresponden a un estudio estadístico donde se mide la reentrada de átomos de una
 * región activa circular.
 *
 * Para cierta cantidad de átomos se mide, la cantidad media de steps necesarios para la reentrada
 * en función del desfase de ángulo del átomo original (cuantos con un desfase de 0º, cuantos con un
 * desfase de 1º)
 *
 * La disposición de la matriz 2D es [radio de la zona activa][ángulo de desfase respecto a la
 * salida]:
 *
 * Primer direccionamiento: pos 0: radio de 20 atomos, incrementos de 5 unidades. Máximo, 195 atomos
 *
 * Segundo direccionamiento: pos:0 desfase de 0 grados. incrementos de 1 grado. máximo 180 grados.
 * la última columna almacena la suma de todos, que debe ser 1 millon de átomos.
 *
 * El valor almacenado es la cantidad media de pasos necesarios para dicha reentrada.
 *
 * @author U010531
 */
@Deprecated
public class GrapheneRawStatisticDataHopsCount1Million extends AbstractStatistics {

  public GrapheneRawStatisticDataHopsCount1Million() {
    readAndSetStatistics("hopsPerAngleHexagonalHoneycomb1million.txt");
  }
  /**
   * 
           +------+-------+------+------+-------+------+------+-------+------+
           |                                                                 |
     1e+08 |                                                                 |
           |                                                                 |
     1e+07 |                                                                 |
           |                                 *   **   ****  ** * * **** ******
           |                 * *  * *****************************************|
     1e+06 |              ************ *******  *  **                    *   |
           |        ************  *                 *                        |
           |    ********  *                                                  |
    100000 |   ***** *    *                                                  |
           |  **                                                             |
     10000 | **                                                              |
           |*                                                                |
           |*                                                                |
      1000 |*                                                                |
           |*                                                                |
       100 *                                                                 |
           +------+-------+------+------+-------+------+------+-------+------+
           0      20      40     60     80     100    120    140     160    180
                                          Angle
   */
}
