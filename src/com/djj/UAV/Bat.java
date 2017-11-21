package com.djj.UAV;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bat implements Tsp_interface {
	public  double A=0.3;//响度
	public  double r=0.4;//脉冲发射频率
	public  double fmin=0;//脉冲频率最小值
	public  double fmax=7;//脉冲频率最大值，根据实际需求选
	public  int N_gen=1000;//迭代次数
	public  int n=10;//种群数量
	public  double bestvalue=Double.MAX_VALUE;
	public int[] X;//最优解
    public void find_best(int [][]Graph,List<List<Integer>> list,int num_target,int num_Uav){
    	int[]Xa=new int[num_target];
    	int[]Xb=new int[num_target];
    	Setcode(Xa,Xb,list);
    	bestvalue=Fun(Getcode2(Xa,Xb,num_Uav),Graph);//初始化最短路径
    	X=new int[num_target];
    	Bat_per []bat=new Bat_per[n];
    	for(int i=0;i<n;i++){
    		bat[i]=new Bat_per(num_target,Xa,Xb);
    	}
    	for(int i=0;i<num_target;i++){
    		X[i]=Xb[i];
    	}
    	for(int i=0;i<N_gen;i++){
    		for(int j=0;j<n;j++){
    			update(bat[j],num_target,Graph,num_Uav);
    		}
    	}
    	System.out.println(Getcode(Xa,Xb,list.size()));
    	System.out.println(Getcode2(Xa,Xb,list.size()));
    }
    
    /*
     * update
     */
    public void update(Bat_per bat,int num_target,int [][]Graph,int num_Uav){
    	Random rand=new Random();
    	bat.f=fmin+(fmax-fmin)*(rand.nextInt(100)/100.0);//更新fi
    	for(int i=0;i<num_target;i++){//更新Vi
    		bat.V2[i]=bat.V2[i]+(bat.Xb[i]-X[i])*bat.f;
    	}
    	for(int i=0;i<num_target;i++){//更新Xi
    		bat.Xb[i]=bat.Xb[i]+(int)bat.V2[i];
    		if(bat.Xb[i]<0){
    			bat.Xb[i]=(bat.Xb[i]+2*num_target)%num_target;
    		}else{
    			bat.Xb[i]=bat.Xb[i]%num_target;
    		}
    	}
    	int[] Xnew=new int[num_target];
    	
    	if(rand.nextDouble()>r){//随机扰动的条件
    		for(int i=0;i<num_target;i++){//产生Xnew
        		Xnew[i]=(int)Get_Xnew(bat.Xb[i],3);//At可以修改
        		if(Xnew[i]<0){//越界处理
        			Xnew[i]=(Xnew[i]+2*num_target)%num_target;
        		}else{
        			Xnew[i]=Xnew[i]%num_target;
        		}
        	}
		}
    	double dis=Fun(Getcode2(bat.Xa,bat.Xb,num_Uav),Graph);
    	if(dis<23){
    		System.out.println("新路径长度"+dis);
    	}
    	if(rand.nextDouble()<A&&dis<bestvalue){//接受新解
    		for(int i=0;i<num_target;i++){//更新Vi
        		X[i]=Xnew[i];
        	}
    		bestvalue=dis;
    		System.out.println("接受"+bestvalue);
    	}
    }
    /*
     * 解的编码
     */
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
    
    /*
     * 解的解码
     */
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
     * 解码2
     */
    public List<List<Integer>> Getcode2(int[]Xa,int[]Xb,int num_Uav){
    	/*System.out.print("Xa");
    	for(int a:Xa){
    		System.out.print(a+" ");
    	}*/
    	List<List<Integer>> list=new ArrayList<List<Integer>>();
    	List[] route=new ArrayList[num_Uav];
    	boolean []known=new boolean[Xb.length];
    	for(int i=0;i<known.length;i++){
    		known[i]=false;
    	}
    	for(int i=0;i<route.length;i++){
    		route[i]=new ArrayList<Integer>();
    	}
    	int x_index;
    	int index=1;
    	for(int i=0;i<Xb.length;i++){
    		x_index=get_min_index(known,Xb);
    		//System.out.println(Xb[i]);
    		//System.out.println("最小坐标"+x_index);
    		index=Xa[x_index];//UAV编号
    		route[index-1].add(x_index+1);
    	}
    	for(List a:route){
    		list.add(a);
    	}
    	return list;
    }
    
    /*
     * Xb中最小数的下标
     */
    
    public int get_min_index(boolean []known,int[]Xb){
    	int xiabiao=0;
    	int index=Integer.MAX_VALUE;
    	for(int i=0;i<Xb.length;i++){
    		if(Xb[i]<=index&&!known[i]){
    			
    			index=Xb[i];
    			xiabiao=i;
    			//break;
    		}
    	}
    	known[xiabiao]=true;
    	return xiabiao;
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
    
    /*
	 *局部搜索更新解，At为所有蝙蝠在同一时间的平均音量
	 */
	public double Get_Xnew(double Xold,double At){
		double Xnew;
		Random random=new Random();
		double e=(random.nextInt(200)-100)/100.0;//-1到1的随机数
		Xnew=Xold+e*At;
		return Xnew;
	}
	
	/*
	 * 目标函数
	 */
	
	public double Fun(List<List<Integer>> list,int [][]Graph){
		double dis_total=0;
		for(List<Integer> route:list){
			dis_total+=calroute(route,Graph);
		}
		return dis_total;
	}
	
	/*
	 * 求每条路径长度
	 */
	public double calroute(List<Integer> route,int [][]Graph){
		int index=0;
		double dis=0;
		for(int n:route){
			dis+=Graph[index][n];
			index=n;
		}
		dis+=Graph[index][0];
		return dis;
	}
}
