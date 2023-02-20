import java.util.Scanner;

public class sumAvg{
  public static void main(String args[]){
	  int arraySize = 0;
	  int array[];
    System.out.println("Enter the array size, *note: size should be larger than 10000");
   Scanner input=new Scanner(System.in);
   do {
	   while(!input.hasNextInt()) {
		   System.out.println("enter integer value");
		   input.nextLine();
	   }
	  
    arraySize=input.nextInt();
    array=new int[arraySize];
    if(arraySize<10000)
    	System.out.println("please make sure the number is larger than 10000");
	   
    }while(arraySize<10000);
   for(int i=0;i<arraySize;i++) {
	   array[i]=(int)(Math.random()*1000)+1;
   }
   int sum=0;
   for(int i=0;i<arraySize;i++) {
	   sum+=array[i];
   }
   int freqArray[]=new int[1001];
   for(int i=0;i<arraySize;i++) {
	   freqArray[array[i]]++;
   }
   double avg=sum/(double)arraySize;
   System.out.println(sum);
   System.out.println(avg);
	  }
}