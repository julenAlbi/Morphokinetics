import functions as fun
import Concerted
import matplotlib.pyplot as plt
import matplotlib.ticker as plticker
from matplotlib.font_manager import FontProperties
import numpy as np
import roman

import pdb

class ConcertedPlot:

    kb = 8.617332e-5
    out = ".svg"
    cm = plt.get_cmap('tab20')
    markers=["o", "s","D","^","d","h","p"]

    def plotTotalRate(self, concerted):
        if not concerted.total:
            concerted.totalRateEvents = np.copy(concerted.rates[:,:,concerted.ratesI]) # it is a inner rate
        
        latSize = concerted.info.sizI * concerted.info.sizJ
        fig, axarr = plt.subplots(1, 1, sharey=True, figsize=(5,4))
        fig.subplots_adjust(wspace=0.1)
        axarr.plot(1/self.kb/concerted.temperatures, concerted.totalRateEvents[-1]/latSize, "x", label="Total rate from events")
        axarr.plot(1/self.kb/concerted.temperatures, concerted.totalRate[-1], "+",label="Total rate from M")
        axarr.plot(1/self.kb/concerted.temperatures, abs(concerted.totalRateEvents[-1]/latSize-concerted.totalRate[-1]), label="Error abs")
        axarr.plot(1/self.kb/concerted.temperatures, abs(concerted.totalRateEvents[-1]/latSize-concerted.totalRate[-1])/(concerted.totalRateEvents[-1]/latSize), label="Error rel")
        axarr.set_yscale("log")
        axarr.legend(loc="best", prop={'size':6})
        fig.savefig("totalRates"+self.out,  bbox_inches='tight')


    def allSlopes(self,x,y):
        return fun.timeDerivative(np.log(y),x)
    
    def fitA(self,x,y,slopes, i):
        b = slopes[i]
        if np.isinf(b) or np.isnan(b):
            b = 0
            a = 0
        else:
            a = np.log(y[i])-b*x[i]
        return a,b
    
    def plotMultiplicities(self, concerted):
        p = concerted.info
        maxR = concerted.maxRanges
        fig, axarr = plt.subplots(1, maxR, sharey=True, figsize=(maxR,4))
        fig.subplots_adjust(wspace=0.1)
            
        axarr[0].set_ylabel("eV")
        minCov = 0
        #cov = list(range(minCov,concerted.info.mCov-1))
        cov = concerted.cov[:concerted.info.mCov]
        concerted.tgt = []
        concerted.rct = []
        concerted.err = []
        for i in range(0,maxR): # different temperature ranges (low, medium, high)
            rcmpt = concerted.activationEnergyC[minCov:,maxR-1-i]
            targt = concerted.activationEnergy[minCov:,maxR-1-i]
            error = abs(1-concerted.activationEnergyC[minCov:,maxR-1-i]/concerted.activationEnergy[minCov:,maxR-1-i])
            handles = self.__plotSimple(cov, targt, rcmpt, error, axarr[i],
                                     maxR, i, not concerted.rAndM and not concerted.omegas)
            concerted.tgt.append(targt[-1])
            concerted.rct.append(rcmpt[-1])
            concerted.err.append(error[-1])

        plt.savefig("multiplicities"+concerted.ext+self.out)#, bbox_inches='tight')
        if concerted.omegas:
            for t in range(0,maxR): # different temperature ranges (low, medium, high)
                mk = np.sum(concerted.omega[minCov:,t,:]*(concerted.ratioEa[minCov:,t,:]-concerted.multiplicityEa[minCov:,t,:]), axis=1)
                for i,a in enumerate(range(p.minA,p.maxA)): #alfa
                    mk = concerted.omega[minCov:,t,a]*(concerted.ratioEa[minCov:,t,a]-concerted.multiplicityEa[minCov:,t,a])
                    if any(concerted.omega[:,t,a] > 1e-2):
                        axarr[maxR-1-t].scatter(cov[31::10], mk[31::10], label=concerted.labelAlfa[a],color=self.cm(abs((a%20)/20)), alpha=0.75, marker=self.markers[a%7], s=10, edgecolors=self.getMec(a))
                    axarr[maxR-1-t].legend(loc="best",  prop={'size':4})
                    
            plt.savefig("multiplicitiesOmegas"+concerted.ext+"2"+self.out)#, bbox_inches='tight')
        


    def plotResume(self, concerted):
        x = list(reversed(1/self.kb/concerted.temperatures))
        figR, ax = plt.subplots(1, figsize=(5,3))
        if concerted.total:
            rl = "R"
        else:
            rl = "TOF"
        figR.subplots_adjust(top=0.95,left=0.15,right=0.95,bottom=0.15)
        ax.plot(x, concerted.tgt, label=r"$E^{"+rl+r"}_{app}$", color="red")
        ax.plot(x, concerted.rct, "--", label=r"$\sum \epsilon^{"+rl+r"}_\alpha$")
        for i,a in enumerate(range(concerted.minAlfa,concerted.maxAlfa)):
            if any(abs(concerted.epsilon[-1,::-1,i]) > 0.005):
                #ax.plot(x, epsilon[-1,::-1,i], label=labelAlfa[a], color=cm(abs(i/20)), marker=markers[i%8])
                ax.fill_between(x, concerted.lastOmegas[:,i], label=concerted.labelAlfa[a], color=self.cm(a%20/(19)))
        # ax2 = ax.twinx()
        # ax2.plot(x, err, label="Relative error")
        #ax.set_ylim(0,3.2)
        #ax.set_xlim(20,30)
        labels = [item for item in ax.get_xticklabels()]
        ax.plot(x, abs(np.array(concerted.tgt)-np.array(concerted.rct)), label="Absolute error", color="black")
        ax.legend(loc="best", prop={'size':6})
        #ax.set_xticklabels(labels)
        ax.set_xlabel(r"$1/k_BT$")
        ax.set_ylabel(r"Energy $(eV)$")
        #ax.set_yscale("log")
        #mp.setY2TemperatureLabels(ax,self.kb)
        ax.annotate(r"$\epsilon^{"+rl+r"}_\alpha=\omega^{"+rl+r"}_\alpha(E^k_\alpha+E^M_\alpha)$", xy=(0.45,0.2), xycoords="axes fraction")
        ax.set_xlim(18,100)
        plt.savefig("multiplicitiesResume"+concerted.ext+self.out)#, bbox_inches='tight')

    def __plotSimple(self, x, targt, rcmpt, error, ax, maxRanges, i, legend):
        #print(maxRanges-i, targt[-1],"\t", rcmpt[-1], "\t", error[-1],"\t", abs(targt[-1]-rcmpt[-1]))
        cm = plt.get_cmap('gist_earth')
        ax.text(0.5, 0.95, r"$"+roman.toRoman(maxRanges-i)+r"$", color="gray", transform=ax.transAxes)
        ax.set_xlabel(r"$\theta$")
        lgRcmpt, = ax.plot(x, rcmpt,
                           ls="dashed", solid_capstyle="round", lw=5,
                           alpha=0.6, color=cm(1/3),
                           label="Recomputed AE")
        lgTargt, = ax.plot(x, targt,
                           "-",  solid_capstyle="round", lw=5,
                           alpha=0.6, color=cm(2/3),
                           label="Activation energy")
        ax2 = ax.twinx()
        lgError, = ax2.plot(x, error,
                            ls="dotted", solid_capstyle="round", lw=5,
                            color=cm(3/4),
                            label="relative error")
        #ax.set_ylim(0,5)
        ax2.set_ylim(0,1)
        # maxY = max(error[30:])+0.015 # get maximum for the arrow (>30% co2)
        # if maxY > 1:
        #     maxY = 1
        # ax2.annotate(' ', xy=(.6, maxY), xytext=(.2, maxY-1e-2), arrowprops=dict(arrowstyle="->", connectionstyle="angle3", edgecolor=cm(3/4), facecolor=cm(3/4)))
        # maxYlabel = "{:03.2f}%".format(maxY*100)
        if i != maxRanges-1:
            ax2.yaxis.set_major_formatter(plticker.NullFormatter())
        else:
            ax2.set_ylabel("Relative error")
    
        # Annotate last energies and absolute error
        font = FontProperties()
        font.set_size(6)
        label = "{:03.4f}".format(rcmpt[-1])
        bbox_props = dict(boxstyle="round", fc=cm(1/3), ec=cm(1/3), alpha=0.7)
        ax.text(30,rcmpt[-1]+1,label, bbox=bbox_props, fontproperties=font)
        label = "{:03.4f}".format(targt[-1])
        bbox_props = dict(boxstyle="round", fc=cm(2/3), ec=cm(2/3), alpha=0.7)
        ax.text(30,targt[-1]-1,label, bbox=bbox_props, fontproperties=font)
        label = "{:03.4f}".format(abs(rcmpt[-1]-targt[-1]))
        bbox_props = dict(boxstyle="round", fc=cm(3/3), ec=cm(3/3), alpha=0.7)
        ax.text(30,targt[-1]-0.5,label, bbox=bbox_props, fontproperties=font)
        bbox_props = dict(boxstyle="round", fc="w", ec="1", alpha=0.8)
        #ax2.text(0.65, maxY, maxYlabel, bbox=bbox_props)
    
        handles = [lgTargt, lgRcmpt, lgError]
        if legend and i == maxRanges-1:
            ax.legend(handles=handles, loc="upper right", prop={'size':8})
        return handles

    def getMec(self, i):
        if i  >= 192:
            mec = "black"
        else:
            mec = "none"
        return mec
