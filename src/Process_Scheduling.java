import java.util.ArrayList;
import java.util.Scanner;

public class Process_Scheduling {

public static void main(String[] args) {
		
		
	    try (Scanner sc = new Scanner(System.in)) {
			System.out.print("Enter Number of Process: ");		       
			int numberOfProcess = sc.nextInt();
			
			Process process[] = new Process[numberOfProcess]; 
			String processName;
			int priority;
			int ArrivalTime;
			int BurstTime;
			int overHeadTime;
                        int Quantam;
			for (int i = 0; i < numberOfProcess; i++)
			{  	
				System.out.print("Enter name for process "+(i+1)+":");
			    processName=sc.next();
			    System.out.print("Enter Priority for process " +(i+1)+":");
			    priority=sc.nextInt();
			    System.out.print("Enter arrival time for process "+(i+1)+":");
			    ArrivalTime=sc.nextInt();
			    System.out.print("Enter Burst time for process "+(i+1)+":");
			    BurstTime=sc.nextInt();	
                            System.out.print("Enter Quantam time for process "+(i+1)+":");
			    Quantam=sc.nextInt();	
			    Process proc=new Process(processName,priority,BurstTime,ArrivalTime,Quantam);
			    process[i]=proc;
                            System.out.println("================================================================");
			}
                        process=Process.ArrivalTimeArrange(process, numberOfProcess);
                        System.out.println("Select a schedular. ");
			System.out.println("1-Shortest Job First Schedular");
			System.out.println("2-Priority Schedular ");
			System.out.println("3-Shortest Remaining Time First ");
                        System.out.println("4-AGAT ");
			int choice=sc.nextInt();   
			//SJF
			if(choice==1)
			{
			     System.out.println("Assuming there is an over head time for context switching..");
                            System.out.println("Enter the over head time: ");
                            overHeadTime=sc.nextInt(); 
                            ArrayList<Process> Schedule = new ArrayList<Process>();
                             ShortestJobFirst SFJ=new ShortestJobFirst();
                             SFJ.contextSwitch=overHeadTime;
		             Schedule=SFJ.ShortestJobFirstSchedul(process, numberOfProcess);
                             System.out.println();
                             System.out.println("================================================================");    
                             System.out.print("||");  
                            for(int i =0;i<Schedule.size();i++)
			    {
                               
			    	System.out.print(Schedule.get(i).processName + "||");
                               
			    }
                                
				System.out.println("\n================================================================");
				System.out.println("Process 	AT     WT     TAT      BT      priority"); 	
                            
			    for(int i =0;i<numberOfProcess;i++)
			    {
			    	System.out.println(process[i].processName + "\t\t" +process[i].ArrivalTime+"\t"+process[i].waitingTime + "\t"+process[i].turnAroundTime+ "\t" +process[i].BurstTime+ "\t" +process[i].priority);
			    }
                              System.out.println("Average turn around time: "+  SFJ.AvgTurnAroundTime(process,numberOfProcess));
	                      System.out.println("Average waiting  time: "+SFJ.AvgWaitingTime(process,numberOfProcess));
			}
			else if(choice==2)
			{
		            System.out.println("Assuming there is an over head time for context switching..");
                            System.out.println("Enter the over head time: ");
                            overHeadTime=sc.nextInt(); 
                            ArrayList<Process> Schedule = new ArrayList<Process>();
                            
                             PrioritySchedular PF=new PrioritySchedular();
                             PF.setContextSwitch(overHeadTime);
		             Schedule=PF.PriorityScheduling(process, numberOfProcess);
                             System.out.println();
                             System.out.println("================================================================");    
                             System.out.print("||");  
                            for(int i =0;i<Schedule.size();i++)
			    {
			    	System.out.print(Schedule.get(i).processName + "||");
			    }
                                System.out.println();
				System.out.println("================================================================");
				System.out.println("Process 	AT     WT     TAT      BT"); 		
			    for(int i =0;i<numberOfProcess;i++)
			    {
			    	System.out.println(process[i].processName + "\t\t" +process[i].ArrivalTime+"\t"+process[i].waitingTime + "\t"+process[i].turnAroundTime+ "\t" +process[i].BurstTime);
			    }
                              System.out.println("Average turn around time: "+  PF.AvgTurnAroundTime(process,numberOfProcess));
	                      System.out.println("Average waiting  time: "+PF.AvgWaitingTime(process,numberOfProcess));
			}
			else if(choice==3)
			{
				ArrayList<Process> Schedule= new ArrayList<Process> ();
				System.out.println("Assuming there is an over head time for context switching..");
                                System.out.println("Enter the over head time: ");
                                overHeadTime=sc.nextInt(); 
                                      
                                 ShortestRemainTimeFirst SRTF=new ShortestRemainTimeFirst();
                                        SRTF.setContextSwitch(overHeadTime);
				Schedule=SRTF.Arrange(process, numberOfProcess  );
                             System.out.println();
                             System.out.println("================================================================");    
                             System.out.print("||");  
                            for(int i =0;i<Schedule.size();i++)
			    {
			    	System.out.print(Schedule.get(i).processName + "||");
			    }
                                System.out.println();
				System.out.println("================================================================");
				System.out.println("Process 	AT     WT     TAT      BT      priority"); 		
			    for(int i =0;i<numberOfProcess;i++)
			    {
                                
			    	System.out.println(process[i].processName + "\t\t" +process[i].ArrivalTime+"\t"+process[i].waitingTime + "\t"+process[i].turnAroundTime+ "\t" +process[i].BurstTime+ "\t" + process[i].priority);
			    }
                            
                             System.out.println("Average turn around time: "+  SRTF.AvgTurnAroundTime(process,numberOfProcess));
	                     System.out.println("Average waiting  time: "+SRTF.AvgWaitingTime(process,numberOfProcess));
			}
                        else if(choice==4)
			{
				ArrayList<Process> Schedule= new ArrayList<Process> ();
                                     ArrayList<Process> Q= new ArrayList<Process> (); 
                                 AGAT agat=new AGAT();
                                 for (int i = 0; i < numberOfProcess; i++) {
                                Q.add(process[i]);
                            }
			     Schedule=agat.ArrangeAGAT(Q);
                             System.out.println();
                             System.out.println("================================================================");    
                             System.out.print("||");  
                            for(int i =0;i<Schedule.size();i++)
			    {
			    	System.out.print(Schedule.get(i).processName + "||");
			    }
                                System.out.println();
				System.out.println("================================================================");
				System.out.println("Process 	AT     WT     TAT      BT      priority"); 		
			    for(int i =0;i<numberOfProcess;i++)
			    {
                                
			    	System.out.println(process[i].processName + "\t\t" +process[i].ArrivalTime+"\t"+process[i].waitingTime + "\t"+process[i].turnAroundTime+ "\t" +process[i].BurstTime+ "\t" + process[i].priority);
			    }
                            
                             System.out.println("Average turn around time: "+  agat.AvgTurnAroundTime(  Q,numberOfProcess));
	                     System.out.println("Average waiting  time: "+agat.AvgWaitingTime(Q,numberOfProcess));
			}
                        else
			{
				System.out.println("Wrong Choice!");
				System.out.println("The program terminates now.");
				System.exit(0);
			}
                         
		}
	}

}