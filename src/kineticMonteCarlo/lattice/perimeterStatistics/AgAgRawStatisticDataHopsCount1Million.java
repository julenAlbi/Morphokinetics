/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kineticMonteCarlo.lattice.perimeterStatistics;

/**
 *
 * @author Nestor
 */
public class AgAgRawStatisticDataHopsCount1Million extends AbstractStatistics {
  
  public AgAgRawStatisticDataHopsCount1Million() {
    this.setData(rawData);
  }
   /**
     * This matrix is 22 x 181. Each row corresponds to a given radius. They are 22 different possible radius; from 20 to 125 (increasing 5 each time).  
     * Each column represents a value of a degree. The last column is the sum of all the columns.
     * This is shape of the data:
     *                                           ..............................                           
                                     ..... ............................ .....                      
                                ..... .....           ............     ...........
                            .... .....      ..........            .........     .......
                           . ....      .....     *     ***                 .....  .... ....
                        ... .      ....*      ** **    * **  *  *               ..... .... ..
                       . ...  .....   ***  ** * ** * * * *** * ***     **            ...  .. ...
                    ... .  ...  *      ****  **  *  ****    ******     **               ..  ... ..
                   . ...  .     *********    **      *      *** **  **** **  *            ..   .....
                ... .  ...  ****** ****              *      **  * ***  ** ***   ***         ..    ....
               . ...       ** ** ** ** *              *     **  * *   **   ***** ****         .     . .
            ...     ...***   ** **    *               *     *   * *   *   **    ** *           ..    . ..
              ...        ****  **                     *         * *  *    *    ** ***            ..   .  .
           .     ...      ****** **                   *        * *       *   **  * ***             ..  .  ..
          ....          ******  *                              * *          **    * ****             .  ..  .
         .     *****  *** *** ***                              **           *     ***  *              .   .   :
        . .    . ***** **    *** **                            **          *    ****  ****             .   .. :
       .  :   .      ****       ****                          **               **    ***                .    ..
      .   :  .    ****** ***       **                         *               *     **                   .    : :
      : .   .     ****  *****                                                    ********                 .   : :
      : :   : *******       **                                                  **********                 .  . .
     . .    : ********                                                         ************                 :   :.
     : :  .  ****** *                                                           *****  **                   :   : .
     : :  :     ******                                                         **   ****                    .   .  :
    . .  .   *********                                                         *******                       :   : :
    : :  :     ********                                                       *******                        .   . .
    : :  :   ***********                                             ************                            :   : :
   . .   .    **********************                     ....       ***********                              .   . .
  ***********************************                   .. .---*************---------------------------------.---.-..
   
     */
 private static int[][] rawData={{207,376,556,1942,3215,702,1192,605,6500,3674,6393,571,4450,16983,14753,21746,8015,13633,5064,26712,36066,39217,47727,71798,57065,68179,7320,19545,84575,13503,58962,82482,13766,72433,16941,48045,11980,32112,166815,66198,72927,139056,175179,174315,193497,84712,24708,131879,235304,107215,133696,168670,261623,132834,288475,507716,169096,30477,298156,574214,314166,286451,259311,297079,57082,458428,217019,337650,240203,434889,316490,251208,73073,700322,720655,367180,254724,171347,329128,99851,200011,4748,585274,182851,717650,513120,278317,475772,829122,81407,537607,529865,1131903,557808,1252062,1139374,348953,297513,622398,30835,580598,920232,1273773,718659,395539,1086878,878890,877614,336687,403359,709948,824053,654122,722085,1733290,613804,1189774,578188,618670,818860,265590,1024400,225732,1518748,1137886,496869,658115,883473,1062524,653620,1304942,198779,1126732,214737,345136,429274,578640,400063,1513189,603535,449996,35177,653845,505828,514634,777620,543002,365052,471993,379093,497472,2193760,88405,355000,671530,708430,342924,711485,536740,297698,699320,1093666,405563,458823,1109444,230812,1186766,574461,191492,672745,925603,149958,461255,627851,114297,972219,103312,196426,689542,8571,74107430,
},{336,123,393,1172,1575,1496,1617,2758,7786,4290,8093,8983,15675,8888,12225,6411,29008,22912,14094,37970,20442,19298,38015,87344,107557,16962,26623,23053,47027,1343,83891,98675,58670,93849,69157,165151,46060,106844,79136,113591,204345,118756,97549,185430,126058,201142,153649,53856,66014,18263,195540,3539,254558,63416,215124,50870,126686,311003,324875,217995,255665,382760,385521,291106,300373,311857,331570,344313,408108,425949,237110,350569,447621,38960,341083,753804,1202451,223992,173284,464255,462640,130688,323808,218243,395646,335662,556634,245610,477245,792021,829142,888311,370638,324929,255409,607633,171557,699676,542001,616652,154264,844700,365202,775020,1020833,454896,1036970,637455,364440,198623,705380,0,666589,817116,288279,7726,1154766,133060,1032670,566949,761556,863808,291091,445346,780023,523511,754547,693377,990053,55369,674927,1943294,140734,318825,670853,1339204,374893,400522,670618,545952,1805033,886143,1207973,425200,636960,331755,218773,554525,1260202,305298,483143,485762,740657,542195,719755,729142,1099892,404699,494352,366372,856185,1099458,241288,1289755,292452,404028,208362,762920,138708,336474,208354,1310159,138172,1207109,56548,224262,941587,33795,862047,9720,70174312,
},{108,470,135,1971,784,2763,6839,4975,8350,2612,8157,5913,7096,12465,13550,13569,10855,30107,18774,10831,37229,31561,32481,51281,52656,42749,51042,46073,78231,15800,63679,28490,77527,41523,31221,96791,75524,61959,74506,57277,64910,162012,89699,94141,118300,180032,101375,158140,249859,345114,109240,230840,164045,353192,106197,420299,367063,84704,426088,119524,258310,151721,464121,215377,362499,100773,174443,365133,565132,296408,155674,812453,426424,520825,516756,166897,485404,410140,85681,146574,551947,118680,421728,383834,181309,646529,345009,821843,580197,212524,569939,596462,350056,428147,329745,706797,188995,701054,1203485,860893,652600,329958,382059,419696,829889,1095707,401314,570571,1365573,147473,455180,23461,928723,977094,526333,510076,212402,576148,552671,908443,660732,490513,465178,980882,231440,712131,794155,1037894,421364,885251,218153,334524,436870,1821,880221,1205307,674869,973395,673063,397245,348706,376069,550937,379176,779254,1375021,404007,845398,439377,1636565,642673,361471,1007807,800329,428595,480237,345422,392874,577972,918673,135171,159080,283090,283194,304122,483797,827390,486750,238616,259418,224286,700944,134949,80602,769462,115830,549066,32381,696402,7514,66235622,
},{200,249,33,647,1274,514,3299,6290,8083,6430,9500,17908,2465,7288,22091,6207,29103,18904,25244,32743,37262,26523,61565,13863,49108,86120,53250,38053,59491,30024,49220,57903,111924,43761,79838,97673,247252,103297,135472,156987,68149,119062,55829,114368,136473,34628,149847,133347,162212,180731,218003,172942,130699,121717,70393,274679,227420,138476,223943,272820,274718,265767,97191,395355,345949,245551,209129,300174,150752,207522,188214,301204,67572,494708,404124,240629,255925,282898,365526,953072,516190,462929,293191,357472,926523,908219,219451,464125,422341,254223,324404,81976,200179,433215,578644,387423,646287,92088,1012424,469091,624456,678903,1158994,373651,504890,888737,666475,926897,843079,371275,802159,458089,538302,948800,877724,253969,567945,937311,630208,868439,1412335,524358,902775,777547,477818,259815,612730,177125,1098551,497023,331841,851817,651721,1034546,766645,709297,639737,279345,975915,418288,740566,633930,720568,924733,701481,1853872,520836,702089,97886,1272426,523529,794239,262875,599744,200896,867847,486432,763894,207661,491716,634789,450425,2306442,549150,310255,117746,922936,345570,416453,596872,324732,522315,1019191,271357,118023,242197,46175,1101454,14717,7900,70844622,
},{211,149,298,1729,2289,2696,4158,7127,6612,6019,7947,8907,3976,15912,20913,21544,27241,26061,31508,33876,15394,39101,21994,35129,47512,67432,70588,51770,78608,36269,87754,91534,70220,113071,79961,98172,102460,51014,97903,103058,38426,57236,136885,208230,79929,125087,132922,204998,75221,364600,131755,230673,589256,129134,170319,176677,158683,65890,504697,449749,121544,143770,155725,92345,207845,226144,440921,204614,263008,254433,392636,207644,493131,250507,615877,382714,110573,433987,240965,474942,464203,426197,229785,367308,906677,523174,723112,93604,520776,85756,90204,82390,154263,186920,501432,417670,719871,391670,280348,406241,386210,289320,369808,820531,59834,1107317,326513,780853,332643,838701,347864,588113,409924,419754,521273,653877,1046318,722908,378276,914454,1826057,348350,433178,408358,808603,1197682,790900,350303,280617,1282015,711551,458041,204727,585091,1716662,554331,738238,960312,276569,978978,435799,659119,526617,247734,475478,258307,887294,1261906,188096,1030090,601617,1189590,253064,502143,342589,791031,562451,601723,965650,678791,706407,673107,337711,451029,311660,180213,370923,371957,1039228,407211,308196,560627,1899325,340975,391895,1964,101547,731578,42763,11961,66827830,
},{74,208,377,484,2075,1018,2656,2322,3674,11027,8647,18214,6963,13254,4375,15396,11009,40794,25013,18157,25737,73369,19700,17463,98476,65863,60933,61508,79763,61131,142484,87071,103967,39964,116561,42038,150123,30723,63002,209952,155018,103907,99993,81162,281907,218572,270550,181316,237505,100054,66356,141806,311860,298020,221142,221129,104902,268915,81493,172945,118552,204710,246360,218757,330433,427062,419596,426711,559427,57776,254990,160323,540710,358105,64801,89109,297721,832157,424656,356011,409865,318976,276820,451159,312051,205127,865300,639554,828440,281025,221182,523859,591963,277777,391161,648182,831946,725048,502051,77654,402063,778215,480930,688703,314313,1204341,322090,863128,326158,383423,365954,884736,246615,419396,833173,814784,682876,281429,488661,462922,1047543,684435,377826,691800,813169,443980,429347,296619,618136,455998,398121,1101941,839579,476599,1108302,649890,913164,824389,665792,813804,952336,865901,727029,737886,1021165,1165775,548029,828661,1410915,801926,457263,721652,794081,689100,457195,268384,625151,32093,847088,818243,99426,417842,640244,368555,407215,577129,373161,509472,366202,569193,339117,172154,138157,253403,155691,220927,184807,73418,14,18725,66670293,
},{29,127,351,558,1945,852,3198,2634,6617,8205,9215,12267,8774,6753,19472,20880,27207,32997,28338,25717,66879,66670,60078,26283,57267,94386,100081,71768,80610,85676,52580,36696,148747,57423,194079,143078,137134,13456,139259,181000,58786,93885,205682,195196,87561,184867,94621,202000,238335,228872,214340,106520,441870,184936,239002,404674,284217,219461,302905,173514,108043,448983,355981,123290,354738,284408,444986,271231,145350,282360,139645,162106,559689,286411,282305,335679,156755,381606,286047,277835,165673,423758,500009,282194,388357,339840,725851,210974,507625,676975,749411,572433,592152,361746,362613,699010,607520,479084,816302,382661,707734,247984,379867,653824,404779,704170,846513,438738,226726,586326,870925,919952,275627,388289,1136663,489470,312786,667279,542153,978577,595143,663940,567459,692834,996557,709630,62216,529514,201684,698182,760114,532500,680666,715801,470083,1153612,865663,508829,646639,336278,393318,798475,756858,439753,584154,703783,465042,508745,1012850,1959271,639899,1130383,626933,375808,791446,962797,328914,369161,1224103,416754,454432,433207,798878,263200,276470,187368,710651,554142,418082,370515,1445588,181044,3970,907310,218353,136169,60581,60384,33337,17478,67907498,
},{136,224,496,837,1551,2100,5756,5941,5154,6568,7542,8176,17072,4661,16957,19656,14813,25724,12815,37012,30911,34609,25620,35016,55802,48732,59182,66188,60180,125787,35908,94798,156215,31999,184462,68289,128016,102872,169434,116114,144392,63585,118594,133080,80972,217793,292807,111276,336656,137745,31203,201760,397680,176073,201453,200696,410318,264437,355775,121302,349770,491537,195588,425036,193677,632191,117657,344663,472772,240344,516902,355137,498943,103677,284060,274419,204381,324398,172585,167462,315860,261147,238763,288132,462776,633348,338130,547827,713232,624383,337749,322033,376441,792031,375439,564925,447505,558778,807212,505593,319842,507142,858567,586862,434636,388076,492473,371544,464231,640536,841341,92971,1011547,589567,733899,581386,701993,786498,845064,768858,473035,217289,800057,816081,401748,671241,993604,305161,641395,585707,1093773,706196,435813,850311,658273,820775,223020,600523,636672,647486,486428,459699,303689,910631,575711,808734,783518,938903,359497,462414,262578,797174,60918,799829,214390,1257309,450294,873328,895139,330761,515074,421403,262393,577664,806923,1057097,594650,743813,375456,546253,139072,339726,335076,205163,124779,204889,126970,65083,42911,162,65808119,
},{158,98,408,1788,818,3493,2487,3764,10276,4042,15123,14053,12307,25670,11257,15161,6999,27040,28093,36528,17844,31902,27912,19057,108297,37137,78696,141151,54139,70968,38812,145915,103033,44148,96943,68331,115938,119538,221818,161717,147401,101183,134034,177937,98284,220252,359913,269231,102529,112207,162132,181128,219974,116948,141905,235520,161053,163165,291052,214749,327017,144911,229804,592716,392872,419734,481595,92534,322089,286739,239763,366855,336006,317773,204343,280626,175521,383925,481626,141499,578424,74319,390214,346982,314407,425754,457281,433466,672894,665399,216574,389028,611586,430834,325716,814105,309694,230148,655997,570641,576734,590432,784570,397205,703293,585340,337978,877553,214531,707979,801997,199064,951889,554238,577194,1154624,738203,413365,581928,709063,251093,694033,507209,595566,156132,558630,217174,549305,673764,426312,947038,563406,444837,247931,769718,649011,979922,612444,417826,749779,818977,821646,511841,869987,325409,391330,477484,694097,635180,840513,563043,600382,628849,603744,946483,607399,254444,623544,511443,498281,729016,849266,473987,308960,793681,834863,377861,364407,495700,370400,156523,323712,384184,370513,328964,84176,149501,140467,122213,17153,64032800,
},{67,78,465,1403,1079,2159,4203,4402,12126,9822,12407,11823,8531,13293,28505,19111,22791,16252,32440,27458,6286,38489,39924,62694,57386,45804,37730,65938,102782,70726,35085,88977,92520,129908,121930,100418,149632,159345,110667,53283,73377,89833,103066,128275,181527,147997,269755,258231,242000,156734,247246,269304,291871,47531,239415,184051,196455,135767,234427,241046,292815,148825,465832,441336,206821,333237,189320,253278,198304,287609,298764,274042,240213,236514,522437,571926,619100,171534,559982,346176,471106,551062,473668,376873,334194,456021,415939,517407,373756,293196,184181,897218,241600,273237,372846,248059,704803,469882,419913,677222,351946,776284,549707,196646,457861,669779,367628,835300,389453,509792,172416,637890,323619,834539,428644,678276,778790,764345,129621,1102783,168521,578654,563217,391670,670190,516607,793181,586361,459798,628746,783563,775570,805146,485561,588008,455112,635278,60579,575459,719638,709600,691543,592563,673100,708331,313626,727045,902919,744318,428404,725553,557106,1148204,693705,494722,367505,432119,844865,604843,383928,503607,927558,419680,723664,919763,736958,518103,727861,417649,297022,279325,408551,111518,208306,522899,211644,30058,61481,44776,15021,63971716,
},{50,120,230,822,2661,669,2226,5062,3186,11006,9857,11590,18805,22065,11653,31220,43663,24220,60321,29379,15599,40085,59810,20412,30921,82550,44987,119278,66938,46286,65197,115721,60672,78521,157469,175596,135683,106686,102764,142458,211829,56187,72688,142493,156363,135009,276441,189929,190872,95431,249555,152836,226582,98801,59465,243716,92321,347807,366347,360894,306512,275411,391674,362841,269564,95599,91661,390628,736098,383839,454778,381469,304591,250912,563315,34979,384016,507166,310047,429354,548915,239892,457577,241776,268836,495845,465922,481131,511536,221738,367966,256906,274158,269291,421276,424849,335649,548980,391178,502989,404824,700848,1386195,251215,293275,414576,762890,480296,544497,254825,462123,595965,101079,628641,319390,892425,747729,915365,651519,475287,635705,719260,1325755,536119,871166,806854,390441,664930,325288,450431,402294,466874,637200,471483,407960,368573,861956,370113,186690,833427,526611,632075,558972,567406,445441,421954,842825,1034415,645621,177730,97439,1448423,927514,176772,294659,700448,790682,520040,275946,724359,953995,550074,546525,248679,727782,424475,678564,408285,642919,269601,340637,525726,457994,384474,203201,172298,118697,57714,69540,23918,62962781,
},{47,89,322,842,1384,2733,4332,7283,5142,11023,12799,8838,9530,15395,13298,32653,15941,24620,51261,15595,45748,35737,46131,59808,67180,71398,35488,48266,89179,54810,65072,47998,59387,112271,119926,106799,98837,101073,182203,66597,97434,216213,109315,163023,169448,239545,177659,231193,221796,232180,188662,108518,169714,296518,287936,213994,315686,321214,173430,335899,165460,376384,198745,237444,204266,174245,393074,331000,287324,402578,444338,633996,316643,447122,409226,329904,132305,367239,145579,599192,345826,397335,143960,483888,160357,763556,447602,691868,262543,97692,707292,281419,304057,460018,333389,368195,377223,232411,466794,760442,176834,343271,311417,547208,353571,607514,448335,324684,671827,269918,366636,610860,525166,601817,587288,295953,883472,477619,886422,1044457,688635,645189,534852,460755,252940,662138,207210,677489,584001,550191,360731,330018,333154,294775,279051,477003,870114,769854,318864,560238,532937,590059,664033,803886,880642,541839,625870,781156,363040,326155,532126,998317,1175403,603533,600103,445536,750781,805855,728356,225225,319754,594760,500728,677703,1671518,518023,299154,157101,951149,583626,453256,537610,207939,402322,145948,140340,162489,190492,21626,31589,62431793,
},{49,146,308,702,1028,3067,3951,7552,8657,4320,6108,17374,11765,25629,12689,34507,23943,29128,26954,48876,33268,35697,47820,48359,21369,91616,69324,59673,49142,41612,80790,72000,90110,114171,70200,139331,72550,131983,111078,126748,79810,166793,199920,258402,142449,127728,187164,161634,227631,208750,352902,270254,144383,192042,311870,242701,207928,169272,306160,334931,218645,188322,490288,180249,360207,206430,375409,383248,218405,481393,176811,372904,331420,384974,503511,311716,335875,206859,338571,403933,419162,208925,158962,182615,243024,356072,389051,473174,242918,478778,403790,386489,256129,512640,417878,380832,838200,402277,753500,557448,564675,264261,300723,523515,545825,303818,424611,230390,777551,611726,622965,442866,961944,588891,367705,325047,592731,462181,769812,517528,314862,692350,416027,485734,344233,479762,396490,841748,660511,552447,413815,581401,690995,203021,486694,409942,721165,543313,563623,504707,424369,630848,351625,464164,654579,414201,935050,990485,432339,313515,359579,334866,775925,317620,247436,700251,435900,758034,732661,419898,323676,521033,744823,619549,275938,672038,239672,241749,624530,852239,458310,166642,588033,220340,448503,228742,202563,146813,45417,34036,59446948,
},{59,66,405,853,1372,2194,3603,5768,9530,9181,8014,12556,7132,14093,29626,30467,18196,25949,37621,19744,48915,55622,32212,41567,34585,61889,91823,80217,110485,51214,83193,41306,157693,20085,70194,92206,179395,63797,97188,190966,221020,126715,99019,138530,258989,126843,339210,89432,199100,202448,204751,211988,346236,309399,214704,279948,294062,252181,214235,237014,322174,326011,375185,153218,135015,316156,498916,105715,363017,248556,258128,429062,331074,751119,257051,557065,528248,291883,373133,265060,421967,256548,352417,108172,328684,210264,441899,309427,307804,636226,528752,251790,312405,422633,406215,877069,566896,391038,212093,733046,709875,539826,331168,311279,383854,336315,309961,644540,438140,696664,352254,389135,417520,289796,273604,465306,750564,802751,413966,612022,489627,203626,774444,668473,312156,454862,465528,462231,345470,388026,359559,434398,586180,349970,622059,446273,740881,356919,328680,426841,321878,496956,251074,551215,763256,689133,435023,339195,388865,487626,635763,465497,649118,606776,608446,850507,142562,415486,255864,943508,384534,208018,650178,404947,612330,492657,303916,669808,177324,391945,390803,287573,432841,591159,216490,193093,235894,94061,60060,4015,56952073,
},{28,135,414,811,1052,3954,3361,3974,5415,10167,10555,12005,16076,29802,20603,31290,27434,19455,46059,45212,32359,29928,30122,25820,41228,65979,60985,55699,113014,42728,85284,66118,93257,116735,126720,250613,209362,102279,93484,127110,140055,260402,122231,133211,67747,152798,168655,279438,141426,178898,192833,204117,284914,347458,242917,83143,129508,196856,316298,291936,433705,179385,316354,467615,300420,263533,470430,371265,458379,312775,375056,215978,414709,250920,540465,248542,560920,270322,282211,392532,385520,363588,209400,531278,260794,386272,379335,337190,787450,245814,213756,450946,400149,380236,426003,620154,338114,575718,345223,298242,525603,376386,663933,437126,677855,254123,314839,309194,340814,692509,659411,217233,600442,720357,615161,524503,348600,549234,577097,393010,826234,572620,402028,394642,249429,264754,1037528,397785,460565,326086,861146,405362,532793,443863,459939,502910,284698,479036,396732,513161,357330,303094,424626,484183,413503,441298,468545,379349,619802,669838,527719,714919,386364,240111,941908,436086,393525,282958,459408,565816,472071,560640,633640,389045,374979,120932,423598,52508,475292,322935,340181,398439,92170,112373,439538,135202,94964,216388,41027,23043,55890253,
},{79,77,426,900,1637,1736,3406,6473,6621,7774,8050,7955,18288,20509,22782,20447,20771,16170,47431,60565,35147,43045,58873,36569,57935,61666,56615,87024,94261,94127,140710,51270,98342,81076,98752,74706,141523,116847,144578,169168,182863,119553,192081,152944,237950,168200,197397,119356,329775,163035,157193,171423,160778,337581,119044,159713,274640,388375,266253,397820,275538,247155,192638,196823,206331,599579,142716,285480,418614,502185,290695,254736,360189,459049,614600,417750,250572,265883,251508,423133,445788,442739,567450,743234,361401,308385,407646,274643,195532,324340,291624,411272,631483,414688,240498,539872,433950,394105,321188,330782,306436,494739,387308,297330,468909,562680,509935,465235,230564,596152,562240,420137,699335,801345,578341,352510,626923,304101,663454,387433,571871,283426,532819,236321,259222,347742,486041,308004,1041607,501089,407102,471132,452678,638333,563757,345683,421631,551102,461898,343418,367110,506287,394534,642114,534523,273581,503771,497741,298034,398304,1003761,613530,594057,314800,626928,342202,604455,489511,752051,445367,324433,591617,420837,427613,108127,294521,401025,64271,345714,260045,217842,358842,437544,239211,187525,334115,165107,58710,28576,21239,55475657,
},{40,200,318,1206,1549,4308,3928,4874,7691,5618,19458,14222,17942,22910,28055,24881,15951,39492,32822,29102,49977,33952,37815,76984,83313,44342,47205,76794,60669,74052,172029,68242,60215,117577,127763,62592,111625,99325,199933,156085,200760,141058,134805,190716,147579,123996,176730,265434,135692,220345,222554,196393,265095,364993,232637,121659,187442,281289,260936,553523,195131,193267,278146,216133,254690,362647,368724,133467,303704,14843,424736,429746,349051,344836,286059,313810,570929,377187,366676,418375,397803,274131,417545,213109,555644,506029,518191,243625,274830,512575,337162,495355,337095,460857,349513,286478,203746,372706,506020,444921,488039,352099,302542,556885,292031,358346,466326,350762,472680,611087,288489,458816,307501,679984,400852,564365,582724,528649,333223,231835,438736,503632,448192,223952,667864,717264,607713,321098,372086,703289,529196,426717,397477,259347,604884,373734,368267,657843,482690,500018,510394,333589,317716,525196,422709,516174,588210,418570,423795,343913,765784,385580,114513,348661,519902,561857,694822,434724,493985,227052,430548,542261,343516,241684,364389,410274,178750,464595,296097,389339,259952,235362,399352,228121,216439,253072,91380,50790,33725,45785,53021995,
},{24,105,405,1341,2688,2795,6139,5199,6901,7912,12899,8183,15974,24253,18567,25912,34469,24621,44694,32685,28318,43233,84915,35116,45289,110290,60905,77797,59373,96897,82012,74605,119230,96598,86030,124455,100281,140403,103267,173279,142459,124691,196460,235187,189230,213644,211609,216384,203232,202641,227145,228493,283409,201521,205191,236614,231093,258528,194363,333515,372783,232007,251719,135302,90317,396553,181171,263544,321999,328649,504238,440386,282420,324979,248211,369921,393309,299623,641822,546535,235794,427569,643798,234291,547588,585707,282542,314766,434182,315558,176898,222511,488680,357413,477326,521228,565909,519915,361534,545062,526987,487542,574702,264856,465980,317312,243269,695523,545590,313830,371336,253345,174969,658351,274138,329439,376431,522950,657151,487176,284085,756136,444879,222549,375360,446548,311772,337924,719467,635993,157027,316660,417370,573594,212931,645794,369973,427738,468527,359304,215916,353655,745664,474832,593343,253043,378430,317326,519119,350449,259972,634321,269801,649259,427005,380854,578441,433843,560194,330466,483993,436283,354486,220368,238288,309930,393653,507517,271206,399720,308903,491076,318142,185376,187193,81669,218275,100948,83090,19541,52597325,
},{29,155,619,1034,2460,2446,3343,8747,8047,9604,11569,13710,19341,21581,26642,23406,22967,33632,19181,33918,35575,63495,49768,59000,83138,40143,87006,62614,110679,76372,82914,144553,73511,79187,95231,82121,121153,103539,178043,69293,138092,218166,190140,161899,96056,171133,142292,207491,209604,183587,251187,237508,258653,219606,208014,220081,280163,208817,286637,284036,274887,283642,270637,245453,315087,428902,387781,242985,258018,354305,202597,390026,553885,356907,506524,251207,239156,345806,212869,423059,387846,279481,341787,275247,425596,411020,413375,590180,463839,501154,569667,388154,279082,337837,345100,433968,435571,311795,331733,414050,343150,308646,337504,710465,212494,313691,392642,429025,227522,181802,332148,166189,534708,395149,659910,388150,315113,274179,683103,753824,706166,454818,552769,174453,532379,415461,411982,252803,315335,364881,282364,612248,406753,572692,571919,412824,278025,257161,457927,567983,438802,524863,376902,453114,478555,305125,334414,339171,303140,641740,410158,482636,426922,466469,268945,220190,428220,680333,202553,407250,408689,319420,173990,529995,959420,394996,234237,335947,383295,175388,405674,247465,318041,364145,232480,204649,40634,131619,32625,26480,51282191,
},{40,203,566,1286,2185,2124,4149,6150,9221,8840,16499,17617,18293,24218,15911,20300,29895,33448,37270,47471,30835,22954,30339,123373,44653,60623,54452,115589,83234,140399,61700,90447,68665,123767,104504,175199,90170,171858,84751,150594,156609,76811,84300,108670,122736,219880,131052,188763,379267,210621,177619,156561,172651,233281,139682,298097,158273,256817,308998,367440,317337,308752,203371,190517,294032,260904,400688,299178,343279,331863,258806,285062,432201,320367,303116,480714,296771,245346,224720,406712,322178,208117,281986,404538,427500,236132,527136,426060,383078,332714,466677,383112,395515,434484,407519,264528,344070,318641,494672,378871,502529,511904,313839,335153,194162,618356,419153,356596,327922,351591,335238,416438,320389,540902,784996,310716,458525,566259,664404,441865,255092,408084,571099,378550,262172,340157,416078,537996,261182,544063,306150,507370,387880,782198,339010,442867,348326,477050,289825,204256,407732,528254,468810,569636,361346,515970,319426,732785,491797,432226,478118,408482,390598,365549,443031,283675,197339,282635,381786,354669,459282,397214,278656,284044,176574,520136,293505,211923,333409,349662,227157,333648,275710,111379,295442,171480,139951,102590,16975,17817,49953944,
},{22,180,680,1346,1512,3189,7270,7304,9893,7695,9776,11688,27309,29999,14014,38994,34856,32098,42646,60156,43419,48412,40680,33840,61907,79327,44996,72021,84314,114557,104327,105675,73153,96174,153653,54498,114532,66009,121856,57874,158131,169436,140869,107795,209429,60888,271855,279851,236107,268186,207245,240410,358830,141844,263762,283875,250733,215990,190720,160206,238502,284865,316034,328497,188080,140083,299990,230651,258718,337352,311014,466898,352225,325420,300712,356723,283695,389775,371480,320529,272690,334053,309793,263784,406645,246305,404607,466464,465966,500884,472340,242781,336869,524023,257603,505953,362616,431640,310077,310471,505227,245614,433802,282773,414741,422007,378684,220314,273256,284774,306658,101282,205126,401418,531681,208241,453971,609506,624453,356876,424469,284385,418517,428984,500644,527914,513050,572522,531803,558042,530786,272710,289873,560287,411541,332241,428466,565920,398395,577826,246530,431861,418780,569601,840985,370873,454753,421377,346388,255098,187023,450725,417944,416186,297385,478078,347949,187744,300292,226468,276064,281207,237732,518279,220508,443145,221099,85896,222121,114126,288579,204794,273355,290665,251846,84123,131729,46801,72942,8595,47983245,
},{25,180,485,1484,1860,2380,6699,4831,6839,9446,22319,18845,24372,18579,25777,36983,33497,35715,42356,34803,40567,43827,40728,36438,49930,42925,81780,59552,59549,87063,59436,110451,156485,113700,104922,87784,119255,148594,57493,229567,163848,176131,230492,142760,175042,232188,170718,145721,333383,163455,362455,269424,180602,198111,302028,260770,206365,310188,227925,226444,295082,230928,217921,328202,239349,543154,197396,187834,288942,359426,187813,377260,182908,359298,383175,342362,180300,405986,303947,352959,303449,334905,439092,496164,524091,289197,344891,443936,409419,245810,233837,248958,696144,420608,387763,432918,493553,469920,394108,397798,285501,349351,231671,361045,578857,378481,140350,198762,387315,508573,235678,340375,209157,234219,272861,293020,380725,477125,527073,197999,582876,224873,322920,742777,258185,498244,400801,142887,333016,393750,462993,645954,585674,264192,250888,234378,251822,500157,327498,466215,397671,373127,737658,573728,229288,186222,521219,443629,362422,296653,402967,415755,223043,332600,354454,486103,273262,500046,196727,360656,150687,396459,253180,225857,337300,172923,266639,240270,337064,366002,186543,176362,150076,199909,326083,102161,166791,58431,28491,13669,46806739,
}};
 
    
}
