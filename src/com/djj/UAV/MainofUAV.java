package com.djj.UAV;

import java.util.List;

public class MainofUAV {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        //坐标变为图
        Target []targets=new Target[13];
        targets[0]=new Target(3,1);
        targets[1]=new Target(1,1);
        targets[2]=new Target(1,2);
        targets[3]=new Target(1,3);
        targets[4]=new Target(1,4);
        targets[5]=new Target(3,5);
        targets[6]=new Target(3,3);
        targets[7]=new Target(4,4);
        targets[8]=new Target(4,2);
        targets[9]=new Target(5,2);
        targets[10]=new Target(5,3);
        targets[11]=new Target(1,4);
        targets[12]=new Target(5,1);
        Util util=new Util();
       // System.out.println(util.dis_target(targets[0], targets[0]));
        int[][]Graph=util.TargettoGraph(targets);
        Investigation x=new Investigation();
        List<List<Integer>> list=x.get_Initial_porgarame(8, Graph);//list为初始化解
        System.out.println(list);
	}

}
