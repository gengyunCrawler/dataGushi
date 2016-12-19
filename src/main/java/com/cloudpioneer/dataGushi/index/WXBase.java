package com.cloudpioneer.dataGushi.index;

/**
 * Created by Tijun on 2016/12/19.
 * 基数
 */
public abstract class WXBase {

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

    private static final float WDA = 0.50F;

    private static final float WDE = 0.50F;

    private static final float WRA = 0.60F;

    private static final float WRE = 0.40F;

    private static final float WLA = 0.50F;

    private static final float WLE = 0.50F;
    /**
     *
     * @param dayNum 每天发文总数
     * @return
     */
    private static final double D1(int dayNum){
        return WXBase.CALLOG(dayNum,WXBase.DB);
    }

    /**
     *
     * @param everageDayNum 平均发文数
     * @return
     */
    private static final double ED1(int everageDayNum){
        return WXBase.CALLOG(everageDayNum,EDB);
    }

    /**
     *
     * @param readNum 阅读数
     * @return
     */
    private static final double R1(int readNum){
        return WXBase.CALLOG(readNum,RB);
    }

    private static final double ER1(int evgReadNum){
        return WXBase.CALLOG(evgReadNum,ERB);
    }

    private static final double L1(int likeNum){
        return WXBase.CALLOG(likeNum,LB);
    }
    private static final double EL1(int evgLikeNum){
        return WXBase.CALLOG(evgLikeNum,ELB);
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
     * @param readNum
     * @param evgPublishNum
     * @return
     */
    public static double DI(int readNum,int evgPublishNum){
       return WDA*D1(readNum) + WDE * evgPublishNum;
    }

    public static double RI(int readNum,int evgReadNum){
        return WRA * R1(readNum) + WRE *ER1(evgReadNum);
    }
    public static double LI(int likeNum,int everageReadNum){
        return  WLA * L1(likeNum) + WLE * EL1(everageReadNum);
    }



}
