package edu.iiitb.os.test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.iiitb.os.clook.CLOOK;
import edu.iiitb.os.cscan.CSCAN;
import edu.iiitb.os.fcfs.FCFS;
import edu.iiitb.os.look.LOOK;
import edu.iiitb.os.scan.SCAN;
import edu.iiitb.os.sstf.SSTF;
public class Jtesting {

	int trackNo[]={23,89,132,42,187};
	int headPos=100;
	char dirU='U';
	char dirD='D';
	int maxPos=199;
	@Test
	public void fcfsTest() {
	
		FCFS fcfs=new FCFS(headPos, trackNo);
		fcfs.fcfs();
		assertEquals(421, fcfs.getHeadMov());
	}
	@Test
	public void sstfTest() {

		SSTF sstf=new SSTF(headPos, trackNo);
		sstf.sstf();
		assertEquals(273, sstf.getHeadMov());
	}
	@Test
	public void scanTestD() {

		SCAN scan=new SCAN(headPos, trackNo,dirD,maxPos);
		scan.scan();
		assertEquals(287, scan.getHeadMov());
	}
	@Test
	public void scanTestU() {

		SCAN scan=new SCAN(headPos, trackNo,dirU,maxPos);
		scan.scan();
		assertEquals(275, scan.getHeadMov());
	}

	@Test
	public void cscanTestD() {

		CSCAN cscan=new CSCAN(headPos, trackNo,maxPos,dirD);
		cscan.cscan();
		assertEquals(342, cscan.getHeadMov());
	}
	@Test
	public void cscanTestU() {

		CSCAN cscan=new CSCAN(headPos, trackNo,maxPos,dirU);
		cscan.cscan();
		assertEquals(341, cscan.getHeadMov());
	}

	@Test
	public void lookTestD() {

		LOOK look=new LOOK(headPos, trackNo,dirD);
		look.look();
		assertEquals(241, look.getHeadMov());
	}
	@Test
	public void lookTestU() {

		LOOK look=new LOOK(headPos, trackNo,dirU);
		look.look();
		assertEquals(251, look.getHeadMov());
	}

	@Test
	public void clookTestD() {
		
		CLOOK clook=new CLOOK(headPos, trackNo,dirD);
		clook.clook();
		assertEquals(296, clook.getHeadMov());
	}
	@Test
	public void clookTestU() {

		CLOOK clook=new CLOOK(headPos, trackNo,dirU);
		clook.clook();
		assertEquals(317, clook.getHeadMov());
	}

	
}
