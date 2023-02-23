import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
   
   numbersGenerator(array);
   printToFile(array);
   int sum=sumOfArray(array);
   
   
   int mostfreq[]=new int[3];
   mostFreqNums(array,mostfreq);
   
   double avg=sum/(double)arraySize;
   
   System.out.println("the sum of the numbers: "+sum);
   System.out.println("the avarege of the numbers: "+ avg);
   System.out.println("most frequented numbers: ");
   for(int i:mostfreq)
	   System.out.print(i+" ");
   
	  }
  private static void mostFreqNums(int[] array, int[] mostFreq) {
	  int freqArray[]=new int[1001];
	   for(int i=0;i<array.length;i++) {
		   freqArray[array[i]]++;
	   }
	   int most;
	   for(int j=0;j<mostFreq.length;j++) {
		   most=0;
		   for(int i=1;i<freqArray.length;i++) {
			   if(most<freqArray[i])
			   {
				   most=freqArray[i];
				   mostFreq[j]=i;
			   }
		   }
		   freqArray[mostFreq[j]]=0;
	   }
	   
  }
 private static int sumOfArray(int[] array) {
	 int sum=0;
	 for(int i=0;i<array.length;i++) {
		   sum+=array[i];
	   }
	return sum;
}
private static void numbersGenerator(int[] array) {
	  for(int i=0;i<array.length;i++) {
		   array[i]=(int)(Math.random()*1000)+1;
	   }
	}
private static void printToFile(int[] array) {
	try {
	      PrintWriter myWriter = new PrintWriter("D:/filename.txt");
	      for(int i:array)
	    	  myWriter.println(i);
	      myWriter.close();
	      System.out.println("Successfully wrote to the file.");
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
}

}