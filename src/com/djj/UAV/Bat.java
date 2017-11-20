package com.djj.UAV;

import java.util.ArrayList;
import java.util.List;

public class Bat implements Tsp_interface {
	public  double A=0.3;//响度
	public  double r=0.4;//脉冲发射频率
	public  int fmin=0;//脉冲频率最大值
	public  int fmax=2;//脉冲频率最小值
	public  int N_gen=1000;//迭代次数
	public  int n=10;//种群数量
    public void find_best(int [][]Graph,List<List<Integer>> list,int num_target){
    	int[]Xa=new int[num_target];
    	int[]Xb=new int[num_target];
    	Setcode(Xa,Xb,list);
    	System.out.println(Getcode(Xa,Xb,list.size()));
    }
    public void Setcode(int[]Xa,int[]Xb,List<List<Integer>> list){
    	int index=1;
    	int num_uav=1;
    	//a为每辆飞机的路径，b为每个点,b<=num
    	for(List<Integer> a:list){
    		for(int b:a){
    			Xa[b-1]=num_uav;
    			Xb[b-1]=index;
    			index++;
    		}
    		num_uav++;
    	}
    }
    public List<List<Integer>> Getcode(int[]Xa,int[]Xb,int num_Uav){
    	List<List<Integer>> list=new ArrayList<List<Integer>>();
    	List[] route=new ArrayList[num_Uav];
    	for(int i=0;i<route.length;i++){
    		route[i]=new ArrayList<Integer>();
    	}
    	int index=1;
    	int shunxu=1;
    	for(int i=0;i<Xa.length;i++){
    		int xiabiao=find_num_index(Xb,shunxu);//执行顺序
    		index=Xa[xiabiao];//UAV编号
    		//System.out.println(index+" "+xiabiao);
    		route[index-1].add(xiabiao+1);
    		shunxu++;
    	}
    	for(List a:route){
    		list.add(a);
    	}
    	return list;
    }
    /*
     * 目标的执行顺序（下标）
     */
    public int find_num_index(int[]Xb,int num){
    	int res=0;
    	for(int i=0;i<Xb.length;i++){
    		if(num==Xb[i]){
    			res=i;
    			break;
    		}		
    	}
    	return res;
    }
}
