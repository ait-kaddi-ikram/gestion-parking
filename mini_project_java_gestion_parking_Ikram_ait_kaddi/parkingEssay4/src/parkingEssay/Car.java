package parkingEssay;

import java.awt.Dimension;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public  class Car  extends JLabel implements Runnable{
    private int id;
	
	
	ImageIcon car ;
	
	int i;
	public static int ramp=0;
	public static boolean plein1=false;
	public static boolean plein2=false;
	public static boolean plein3=false;
    public static int place1 =-1;
    public static int place2 =-1;
  
  
    public  boolean amer;  ///tester si la voiture est d�ja entrer dans la palce
    /** Initial Available spaces
     */
private static int NBR_PLACES = 2;
private static int NBR_RAMPE  = 1;

private static Semaphore semPlace = new Semaphore( NBR_PLACES , true );
private static Semaphore semRampe = new Semaphore( NBR_RAMPE  , true );


	Car(int id){
		   
		   this.id=id;
	   
	   
	       if(id==1 || id==2 || id==3) {
	           car=new ImageIcon("src/img/car"+this.id+".png");
	           this.setIcon(car);
	           }else {
	        	   car=new ImageIcon("src/img/car3.png");
	               this.setIcon(car);
	           }
	 
	   
	}
   public int getId() {
	   return this.id;
   }


	/** reference time
     */
    private static final long referenceTime = System.currentTimeMillis();

    private String getAccesVoitureDesc() {
        return "[" + ( System.currentTimeMillis() - referenceTime ) + "] (Proc : " + Thread.currentThread().getName() + ")";
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		 try {
             System.out.println( getAccesVoitureDesc() + " veut rentrer dans le parking !");
             this.entrer_parking();
             Thread.sleep(2);
             System.out.println( getAccesVoitureDesc() + " veut sortir du parking !");
             this.sortir_parking();
     } catch (InterruptedException e) {
             e.printStackTrace();
     
	}
	}


		 
	
	public void entrer_ramp() throws InterruptedException {
	             amer = false;
		 if(this.plein1 == false && amer == false) {
			 amer=true;
			 this.plein1=true;
			 this.place1= this.getId();
			   for(int i=20;i<=290;i++) {
				   this.setBounds(i, 160, 140, 140);
				   Thread.sleep(2);
			   
		 			if(i==290) {
		 				for(int j=160;j>0; j--) {
		 					this.setBounds(290,j,140,140);
		 					Thread.sleep(1);
		 				}}
			   
		 }
		 }
		 if(this.plein2 ==false && amer == false) {
			 amer=true;
			 this.plein2 =true;
			 this.place2= this.getId();
			   for(int i=20;i<=560;i++) {
				   this.setBounds(i, 160, 140, 140);
				   Thread.sleep(3);
			   
		 			if(i==560) {
		 				for(int j=160;j>0; j--) {
		 					this.setBounds(560,j,140,140);
		 					Thread.sleep(1);
		 				}}
			   
		 }
		 }
	
	}
  
	
	
	public void ramp_sorte() throws InterruptedException {
		
	     if(this.getId() == this.place1) {
	    	   this.plein1=false;
	    	  System.out.println("plac1 qui veut sortir");
	    	
	    	 for(int j= 0; j <=160;j++) {
	    		 this.setBounds(290,j,140,140);
	    		 Thread.sleep(1);
	    		 if(j==160) {
	    			 for(i=290;i<1200;i++){
	    				 this.setBounds(i,160,140,140);
	    				 Thread.sleep(1);
	    			 }
	    		 }
	    	 }
	     }
	     if(this.getId() == this.place2) {
	    	 this.plein2=false;
	    	 for(int j= 0; j <=160;j++) {
	    		 this.setBounds(560,j,140,140);
	    		 Thread.sleep(1);
	    		 if(j==160) {
	    			 for(i=560;i<1200;i++){
	    				 this.setBounds(i,160,140,140);
	    				 Thread.sleep(1);
	    			 }
	    		 }
	    	 }
	     }
	 
	}
	

	
	public void entrer_parking() throws InterruptedException{
		
		try {
            semPlace.acquire();
        	
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }

semRampe.acquire();
System.out.println( getAccesVoitureDesc() + " passe sur la rampe");
entrer_ramp();



semRampe.release();

System.out.println( getAccesVoitureDesc() + " est descendu de la rampe et est garé a sa place !");
ramp=0;
		
	}
 
	public void sortir_parking() throws InterruptedException{
		
		
		semRampe.acquire();
		System.out.println( getAccesVoitureDesc() + " passe sur la rampe (pour sortir)");
	

		semRampe.release();
		System.out.println( getAccesVoitureDesc() + " est descendu de la rampe (pour sortir)");
		

		
		semPlace.release();
		System.out.println( getAccesVoitureDesc() + " est sorti du parking...Place liberée !");
		ramp_sorte();
		
	

	}
}