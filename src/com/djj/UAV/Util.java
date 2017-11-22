package com.djj.UAV;

import java.util.List;

public class Util {
     public double[][]TargettoGraph(Target[] targets){
    	 int num=targets.length;//目标数量
    	 double [][]Graph=new double [num][num];
    	 for(int i=0;i<num;i++){
    		 for(int j=0;j<num;j++){
    			 Graph[i][j]=dis_target(targets[i],targets[j]);
    		 }
    	 }
    	 return Graph;
     }
     public double dis_target(Target n1,Target n2){
    	 double dis;
    	 dis= Math.sqrt((n1.x-n2.x)*(n1.x-n2.x)+(n1.y-n2.y)*(n1.y-n2.y));
    	 return dis;
     }
}
