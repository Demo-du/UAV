package com.djj.UAV;

import java.util.List;

public class MainofUAV {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int[][]Graph={{0,2,3,3,4,3},
        		      {2,0,2,2,4,4},
        		      {3,2,0,1,2,5},
        		      {3,2,1,0,2,3},
        		      {4,4,2,2,0,2},
        		      {3,4,5,3,2,0}};
        Investigation x=new Investigation();
        List list=x.get_Initial_porgarame(8, Graph);
        System.out.println(list);
	}

}
