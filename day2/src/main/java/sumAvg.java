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
   int mostfreq[]=new int[3];
   
   for(int i=1,j=0;i<freqArray.length;i++) {
	   if(freqArray[i]>freqArray[mostfreq[j]]) {
		   mostfreq[j]=i;
		   j=(j+1)%3;
	   }
   }
   double avg=sum/(double)arraySize;
   System.out.println("the sum of the numbers: "+sum);
   System.out.println("the avarege of the numbers: "+ avg);
   System.out.println("most frequented numbers: ");
   for(int i:mostfreq)
	   System.out.print(i+" ");
	  }
}