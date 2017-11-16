package com.djj.UAV;

import java.util.ArrayList;
import java.util.List;

public class Investigation {
	/*
	 * 初始化UAV的解:基于贪心策略
	 */
     public List<List<Integer>> get_Initial_porgarame(int limit_dis,int [][]Graph){
    	 int row=Graph.length;//行数
    	 int col=Graph[0].length;//列数
    	 List<Integer> Per_Uav=new ArrayList<Integer>();
    	 List<List<Integer>> Total_Uav=new ArrayList<List<Integer>>();
    	 boolean[]chosen=new boolean[row];
    	 int min=Integer.MAX_VALUE;
    	 int min_index=0;
    	 //找出太远不可能侦查的点
    	 for(int i=1;i<row;i++){
    		 if(Graph[0][i]*2>limit_dis){
    			 chosen[i]=true;
    		 }
    	 }
    	 //==================================================//
    	 int node=0;
    	 int dis=0;
    	 while(isleft(chosen)){//条件：还有点没选择
    		 //找距离0最近的一个初始值
    		 //System.out.println("sss");
    		 for(int i=1;i<row;i++){
        		 if(Graph[node][i]<min&&!chosen[i]&&i!=node){
        			 min_index=i;//最近的点
        			 min=Graph[node][i];//最近的距离
        		 }
        		 //System.out.println(i+":"+chosen[i]);
        	 }
    		// System.out.println(min_index);
    		// System.out.println(dis+Graph[node][min_index]+Graph[0][min_index]);
    		 if(dis+Graph[node][min_index]+Graph[0][min_index]<=limit_dis){//在航程以内
    			 //System.out.println(node+" "+min_index);
    			 Per_Uav.add(min_index);
    			 dis+=Graph[node][min_index];
    			 node=min_index;
    			 chosen[min_index]=true;
    			 min=Integer.MAX_VALUE;
    			 if(!isleft(chosen)){
    				 Total_Uav.add(Per_Uav);
        			 Per_Uav=new ArrayList<Integer>();//新增一架飞机
        			 dis=0;
        			 node=0;
        			 min=Integer.MAX_VALUE;
    			 }
    		 }else{//超过航程，完成一架UAV初始化
    			// System.out.println("ss");
    			// System.out.println(Per_Uav);
    			 Total_Uav.add(Per_Uav);
    			 Per_Uav=new ArrayList<Integer>();//新增一架飞机
    			 dis=0;
    			 node=0;
    			 min=Integer.MAX_VALUE;
    		 }
    	 }
    	 return Total_Uav;
     }
     /*
      * 判断是否有没选择的点
      */
     public boolean isleft(boolean []chosen){
    	 for(int i=1;i<chosen.length;i++){
    		 if(chosen[i]==false){
    			 return true;
    		 }
    	 }
    	 return false;
     }
     
     
}
