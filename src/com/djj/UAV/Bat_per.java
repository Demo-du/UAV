package com.djj.UAV;

import java.util.Random;

/*
 * 蝙蝠个体建模
 */

public class Bat_per {
	public  double A=0.3;//响度
	public  double r=0.4;//脉冲发射频率
	public  double V1;//取值-（a-1）到(a-1),a为目标数量
	public  double [] V2;//取值-（a-1）到(a-1),a为目标数量
	public int x1=0;
	public int x2=0;
	public  double f=0;
	public  double fitness=Double.MAX_VALUE;
	int[]Xa;//=new int[num_target];
	int[]Xb;//=new int[num_target];
    public Bat_per(int target_num,int []s_Xa,int []s_Xb){
    	Xa=new int[target_num];
    	Xb=new int[target_num];
    	V2=new double[target_num];
    	for(int i=0;i<target_num;i++){
    		Xa[i]=s_Xa[i];
    		Xb[i]=s_Xb[i];
    		V2[i]=0;
    	}
	} 
	
}
