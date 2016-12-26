package com.cloudpioneer.dataGushi.index;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by Tijun on 2016/12/19.
 * 基数
 */
public abstract class WXIndex {

    /**发文总基数*/
    private static final int DB = 30<<3;
    /**平均发文基数*/
    private static final int EDB = 1<<3;
    /**总阅读量基数*/
    private static final int RB = 30*100000<<3;
    /**平均总阅读量基数*/
    private static final int ERB = 100000;
    /**总点赞数基数*/
    private static final int LB = 30*100000<<3;
    /**平均点赞数基数*/
    private static final int ELB = 100000;

    /**权重*/

    private static  float WDA = 0.50F;

    private static  float WDE = 0.50F;

    private static  float WRA = 0.50F;

    private static  float WRE = 0.50F;

    private static  float WLA = 0.50F;

    private static  float WLE = 0.50F;

    private static  float WD = 0.30F;

    private static  float WR = 0.40F;

    private static  float WL = 0.30F;


    static {
        ResourceBundle bundle = PropertyResourceBundle.getBundle("account");
        WDA = Float.parseFloat(bundle.getString("WDA"));
        WDE = 1 - WDA;

        WRA = Float.parseFloat(bundle.getString("WRA"));
        WRE = 1 - WRA;

        WLA = Float.parseFloat(bundle.getString("WLA"));
        WLE = 1 - WLA;


    }

    /**
     *
     * @param articlesNum 每天发文总数
     * @return
     */
    private static final double D1(int articlesNum){
        return WXIndex.CALLOG(articlesNum, WXIndex.DB);
    }

    /**
     *
     * @param everageDayNum 平均发文数
     * @return
     */
    private static final double ED1(int everageDayNum){
        return WXIndex.CALLOG(everageDayNum,EDB);
    }

    /**
     *
     * @param readNum 阅读数
     * @return
     */
    private static final double R1(int readNum){
        return WXIndex.CALLOG(readNum,RB);
    }

    private static final double ER1(int evgReadNum){
        return WXIndex.CALLOG(evgReadNum,ERB);
    }

    private static final double L1(int likeNum){
        return WXIndex.CALLOG(likeNum,LB);
    }
    private static final double EL1(int evgLikeNum){
        return WXIndex.CALLOG(evgLikeNum,ELB);
    }
    /**
     *
     * @return
     */
    private static final double CALLOG(int A,int B){
    return Math.log10(A+1)/Math.log10(B+1)*1000;
    }


    /**计算 DI,RI,LI*/

    /**
     *
     * @param dayNum
     * @param evgPublishNum
     * @return
     */
    public static double DI(int dayNum,int evgPublishNum){
       return WDA*D1(dayNum) + WDE * evgPublishNum;
    }

    public static double RI(int readNum,int evgReadNum){
        return WRA * R1(readNum) + WRE *ER1(evgReadNum);
    }
    public static double LI(int likeNum,int everageReadNum){
        return  WLA * L1(likeNum) + WLE * EL1(everageReadNum);
    }

   public static double GWI(double di,double ri,double li){
       return WD*di+WR*ri+WL*li;
   }

}
