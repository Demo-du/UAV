package com.djj.UAV;

import java.util.List;

public class Util {
     public int[][]TargettoGraph(Target[] targets){
    	 int num=targets.length;//目标数量
    	 int [][]Graph=new int [num][num];
    	 for(int i=0;i<num;i++){
    		 for(int j=0;j<num;j++){
    			 Graph[i][j]=dis_target(targets[i],targets[j]);
    		 }
    	 }
    	 return Graph;
     }
     public int dis_target(Target n1,Target n2){
    	 int dis;
    	 dis=(int) Math.sqrt((n1.x-n2.x)*(n1.x-n2.x)+(n1.y-n2.y)*(n1.y-n2.y));
    	 return dis;
     }
}
