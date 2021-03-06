import functions as f
import csv
import re
import numpy as np
import matplotlib.pyplot as plt
import results
import scipy.special
import traceback
import sys
import os
import warnings
from scipy.optimize import curve_fit

def myPrint(vector):
    for i in vector:
        print(i, end=" ")
    print()
    
def getXy(i, length):
    x = int(i / length)
    y = i % length
    return x,y

def getAeStudy(verbose = False):
    if (verbose):
        print("***")
    ratioTimesPossible = np.loadtxt(fname="AeRatioTimesPossible")
    possible = np.loadtxt(fname="AePossibleFromList")
    sum = np.sum(ratioTimesPossible)
    ratio = ratioTimesPossible/np.sum(ratioTimesPossible)
    # Get the most probable processes
    orderedIndexes = np.argsort(ratio, axis=None)
    ratioSum = 0.0
    resultsDict = {}
    for i in orderedIndexes[::-1]:
        x,y = getXy(i, len(ratioTimesPossible))
        ratioSum += ratio[x][y]
        resultsDict[i] = [ratio[x][y],possible[x][y]]
        if (verbose):
            print(i, x, y, ratio[x][y], ratioSum)
        if (ratioSum > 1):
            break
    return resultsDict


def readAllValues(maxCoverage, temperature, flux):
    """ reads average data from previously computed data """
    chunk = 1 # it is not used
    averageData = results.AverageData(maxCoverage, chunk) # create return object
    fileName = "dataFile"+'{:E}'.format(flux)+"_"+str(temperature)+".txt"
    if os.path.getmtime(fileName) < os.path.getmtime("results/dataEvery1percentAndNucleation.txt"):
        raise OSError
    if sum(1 for line in open(fileName)) < maxCoverage + 1: # ensure that cached file has enough lines
        raise OSError
    csvfile = open(fileName, newline='')
    outreader = csv.reader(csvfile, delimiter=' ', quotechar='|', quoting=csv.QUOTE_MINIMAL)
    for row in outreader:
        if row[0][0] != "%":
            # average
            averageData.appendData(row)
    return averageData

def readFractalD(flux):
    fileName = "fractalD"+'{:E}'.format(flux)+".txt"
    csvfile = open(fileName, newline='')
    outreader = csv.reader(csvfile, delimiter=' ', quotechar='|', quoting=csv.QUOTE_MINIMAL)
    dimensions = []
    for row in outreader:
        if row[0][0] != "%":
            # average
            dimensions.append(row[3])
    return dimensions
    

def getAllValues(maxCoverage, getSlopes=True):
    """ reads all the values for the corresponding coverage """

    # read original morphokinetics output
    fileName = "dataEvery1percentAndNucleation.txt"
    try:
        f = open(fileName)
    except OSError:
        try:
            f = open("results/"+fileName)
        except OSError:
            print("Input file {} can not be openned. Exiting! ".format(fileName))
            raise OSError("file not found")
        
    #get something like 0.05000 expression to be grepped 
    regExpression = '(0\...00)|(^0\...\s)' # corresponds to 0.??00 expression or 0.??[:spaces:] expression
    completeData = results.CompleteData(maxCoverage) # init results class
    cov = 0
    previousLine = ""
    dataLine = ""
    readLines = 0
    # if found the coverage, save the next line (with the histogram)
    for line in f:
        if cov and getSlopes:
            readLines += 1
            dataList = re.split(',|\[|\]', dataLine)
            iterList = iter(dataList) # get island histogram
            next(iterList) # skip the first and second entries of histogram ("histogram" word and value of unoccupied positions)
            next(iterList)
            j = next(iterList)
            while j != '\n': # save the current values (island sizes) to an array
                completeData.appendIslandSize(cov-1, j)
                j = next(iterList)
            cov = 0
        if re.match(regExpression, line):      # just hit a coverage
            if line[9] == "A":                 # it is activation energy data, skip it
                continue
            cov = int(line[2]+line[3])         # get coverage
            if (cov > maxCoverage):           
                cov = 0                        # coverage is bigger than wanted, skip
            elif (cov > 0):                    # we have to actually skip 0 coverages
                completeData.appendData(cov-1, line)
                dataLine = previousLine
        previousLine = line
    return completeData

def getSlope(times, valueList, maxCoverage, sqrt=False, verbose=False, tmpFileName="tmpFig.png"):
    x = np.array(times)
    x = np.array(times).astype(float)[0:maxCoverage]
    averageValues = np.array(valueList).astype(float)[0:maxCoverage]
    try:
        a, b = np.polyfit(x, averageValues, 1)
        popt = curve_fit(f.power, x, averageValues)
        aPower = popt[0][0]
        bPower = popt[0][1]
        if verbose:
            plt.figure(num=None, figsize=(3,3), dpi=80, facecolor='w', edgecolor='k')
            label = "{}x+{}".format(a, b)
            print(label)
            plt.plot(x, averageValues)
            y = a*np.array(x)+b
            plt.plot(x, y, label=label)
            y = f.power(x, aPower, bPower)
            label = "{}x^{}".format(aPower, bPower)
            plt.plot(x, y, label=label)
            plt.legend(loc='upper left', prop={'size':6})
            plt.savefig(tmpFileName)
            plt.close()
        if sqrt:
            valueSlope = aPower
        else:
            valueSlope = a
    except (TypeError, RuntimeError, ValueError):
        if (verbose):
            print("Error fitting...")
            print(x)
            print(averageValues)
            traceback.print_exc(file=sys.stdout)
        valueSlope = 0

    return valueSlope


def getIslandSizeError(numberOfIslands, valueList):
    """On the one hand, all island's atoms are counted and divided by the
    number of them. On the other hand, coverage is multiplied by
    simulation sizes and divided by the number of islands. """
    numberOfIslands = numberOfIslands[1:31]
    averageValues = []
    averageNumberOfIslands = []
    index = 0
    for value in valueList:
        if value: #ensure that it is not null
            averageValues.append(np.mean(value))
            numberOfIslandsCoverage = np.array(numberOfIslands[index]).astype(np.float)
            if (len(numberOfIslandsCoverage) > 0):
                averageNumberOfIslands.append(np.mean(numberOfIslandsCoverage)/(400*400))
        index += 1
    coverages = (np.arange(0.01, 0.31, 0.01))/averageNumberOfIslands
    x = np.array(np.array(coverages))
    return abs((averageValues/coverages)-1)
    

def getFractalDimensionSingle(verbose=False):
    """returns fractal dimension of the current single flake
simulation. Takes only into account the regime where the islands are
growing or steady. It requires to have matrix.txt file in results
folder, matrix.sh script is useful to obtain this from
dataEvery1percentAndNucleation.txt output file    """

    try:
        matrix = np.loadtxt(fname="matrix.txt", delimiter="\t")
        warnings.simplefilter("error")
    except (OSError, UserWarning):
        print("error opening matrix.txt file")
        return -1
    
    # Get columns 13 and 14 (only when the number of islands is 1)
    gyradius =  [matrix[i][9] for i in range(0,matrix.shape[0]) if matrix[i][3] == 1]
    islandSize = [matrix[i][13] for i in range(0,matrix.shape[0]) if matrix[i][3] == 1]
    popt = curve_fit(f.power, gyradius, islandSize)
    a = popt[0][0]
    b = popt[0][1]
    return b

def plot(x, y):
    maxCoverage = 31
    #gyradiusList vs averageSizes
    indexes = range(1, maxCoverage)
    xPlot = np.array(x)[indexes]
    xPlot = [np.mean(i) for i in xPlot]
    yPlot = np.array(y)
    plt.xlabel("Gyradius")
    plt.ylabel("Island size")
    plt.grid(True)
    plt.loglog(xPlot, yPlot, ".")
    # fit
    popt = curve_fit(f.power, xPlot, yPlot)
    aPower = popt[0][0]
    bPower = popt[0][1]
    label = "{}x^{}".format(aPower, bPower)
    yPlot = f.power(xPlot, aPower, bPower)
    plt.loglog(xPlot, yPlot, label=label)
    plt.legend(loc='upper left', prop={'size':12})
    plt.savefig("gyradiusVsSize.png")
    plt.close()
