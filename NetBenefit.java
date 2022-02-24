import java.util.*;
import java.io.*;

public class NetBenefit{
	public static void main(String[] args) {
		char c;
		int totalamount;
		int sellprice;
		boolean f=true; //prwth grammh
		boolean end=false; //teleutaia grammh
		int i ;
		int g;
		boolean k=false;
		int boughtprice;
		int sum=0;
		int amount=0;
		String value;
		String value2;
		int a=-1000000000; //akoloy8ei entolh sell 
		int b=-2000000000 ;// telos ouras
		int kl=-300000000; //eisagwgh kerdwn
		int s;
		IntQueue<Integer> stack= new IntQueueImpl<>();
		//BAZOUME TA DEDOMENA STHN LISTA MAS
		try{
			String filename = args[0];
			 BufferedReader  reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			while (line != null) {
				// read buy 
				value = ""; 
				c = line.charAt(0);
				i = 0;
				if (f){ //elegxow prwths grammhs oti ksekinaei me buy
					if (c!='b'){
						System.out.println("ERROR : THE FILE DOESNT START WITH BUY");
						System.exit(0);	
					}
					f=false;
				}
				while (c!=' '){
					value+= String.valueOf(c);
					i++;
					c=line.charAt(i);
				}
				if( (!value.contains("buy")) && (!value.contains("sell"))){
					System.out.println("ERROR : Command "+ value + " does not exist");
					System.exit(0);
				}
				i++; 
				value2 = "";
				c = line.charAt(i);
				while (c!=' '){
					value2+= String.valueOf(c);
					i++;
					c = line.charAt(i);	
				}
				try{
					amount = Integer.parseInt(value2);
					}
				catch (NumberFormatException ex){
					System.out.println("ERROR : <amount> must be integer ");
					System.exit(0);}
				if (amount<0){
					System.out.println("ERROR : <amount> must be positive or zero");
					System.exit(0);
				}
				if (value.contains("buy")){
					stack.put(amount);
					end=false;
				}
				else{
					stack.put(a);
					stack.put(amount);
					end=true;
				}
				i++;
				value2 = "";
				c = line.charAt(i);
				//System.out.println(c);
				while (c!=' '){
					value2 += String.valueOf(c);
					i++;
					c = line.charAt(i);
					//System.out.println(c);		
				}
				//System.out.println(value);
				if (!value2.contains("price")){ 
					System.out.println("ERROR : Expected <price> got "+ value2);
					System.exit(0);
				}			
				i++;
				try{
					amount=Integer.parseInt(line.substring(i));
					//value=line.substring(i);
					//System.out.println(amount);
				}
				catch (NumberFormatException ex) {
					System.out.println("ERROR : <price> must be integer ");
					System.exit(0);
				 }
				if (amount<0){
					System.out.println("ERROR : <price> must be positive or zero");
					System.exit(0);
				}
				if (value.contains("buy")){
					stack.put(amount);
				}
				else{
					stack.put(amount);
					stack.put(a);
				}	
				line = reader.readLine();
			}
			reader.close();	
		}
		catch (IOException e) {
            System.out.println	("Error reading line in file ...");

		}
		if (!end){ // elegxei an to arxeio teleivnei me sell sthn teleytaia toy grammh
			System.out.println("ERROR : The last command has to be <sell> ");
			System.exit(0);
		}
		stack.put(b);
		//MPHKAN TA DEDOMENA STHN LISTA MAS
		
		
		
		i=0; // DEDOMENA KERDOUS P EXOYME PERASEI
		
		/////////////////////////////////////////
		
		System.out.println("\n---------ARXIKH LISTA -------------\n");			
		stack.printQueue(System.out);	
		
		///////////////////////////////////////
		
		while (!stack.isEmpty()){
			/////////////////////////////////////////
		
			System.out.println("\n------- ------------\n");			
			stack.printQueue(System.out);	
		
			///////////////////////////////////////
			s=stack.get();
			if (s!=a&&s!=kl){ // den akolou8ei entolh sell k yparxoun kialles entoles mesa
				stack.put(s);
				g=0;
				//elegxoume an yparxei sell mesa
				while (stack.peek()!=a){
					s=stack.get();
					stack.put(s);
					 g++;
					if (g==stack.size()){//den yparxei allo sell mesa
						k=true;
						break;}
				
			//telos if (s!=a)
			}
			if (k){break;}	
			}//telos if (s!=a)
			
			if (s==a){ //brhkame entolh sell
				totalamount=stack.get(); //phrame to amount toy sell
				sellprice=stack.get(); //phrame to price toy sell
				s=stack.peek();
				while (s!=b){
					stack.put(stack.get());
					s=stack.peek();	
				}//telos while (s!=b) 
				stack.put(stack.get()); // balame kai to b mesa sthn nea lista
				s=stack.peek();
					while (totalamount>0 && s!=a &&s!=b&&s!=kl){
						amount=stack.get();
						boughtprice=stack.get();
						if (amount==totalamount){
							//System.out.println("delete " +stack.peek());
							stack.get();//bgainei to a pou eixe perastei 
							stack.put(kl);//akolou8ei ekxwrhsh kerdous
							stack.put(amount*(sellprice-boughtprice));
							totalamount-=amount;
							//i++; //perasthke ena kerdos
					
						}//telos if (amount==totalamount
						else if (amount > totalamount){
							//System.out.println("delete " +stack.peek());
							stack.put(amount-totalamount);
							stack.put(boughtprice);
							while (stack.peek()!=a){
								stack.put(stack.get()); 
							}
							stack.get(); // //bgainei to a pou eixe perastei
							stack.put(kl);//akolou8ei ekxwrhsh kerdous
							stack.put(totalamount*(sellprice-boughtprice));
							//i++; //perasthke ena kerdos
							totalamount-=amount;
						}
						else {
							stack.put(kl);//akolou8ei ekxwrhsh kerdous
							stack.put(amount*(sellprice-boughtprice));
							totalamount-=amount;
							continue;
						}//telos else amount<total
						s=stack.peek();
						}//telos while (totalamount>0 && s!=a )

				if (totalamount>0 &&(s==a ||s==b||s==kl) ){
						System.out.println ("ERROR : You cannot sell this amount of shares");
						System.exit(0);	
					}//telos if (totalamount>0)
				if (totalamount<=0 &&stack.peek()==a){
					stack.get(); //feygei to a p emene
				}
				
			}//telos else if (s==a)
		
		else if(s==kl){ //an exoume brei entolh kerdous
			stack.put(stack.get());
			stack.put(stack.get());

		}//telos else if(s==-10)
		/*else if(s==b){

			System.out.println("size"+ stack.size());
			System.out.println("1+2*i"+ 1+2*i);
			if (stack.size()!= 1+2*i ){//1 h entolh b kai kaue entolh kerdwn epi 2 gt yparxei k to anagnwristiko ths ara dn teleiwse o ypologismos tvn epimerous kerdwn g n prepei n ginei pros8esh
				stack.put(stack.get());
			}//if (stack.size()!= 1+2*i )
			//else{break;}
			else{
				//prepei na ginei pros8esh 
				stack.get(); //bgainei to b
				while(! stack.isEmpty()){
					s=stack.get();
					if (s!=kl){
						sum+=stack.get();
					}//telos if (s!=-10){
				}//telos while
				System.out.println ("Your credits are: "+sum);
				System.exit(0);		
			}//telos else
			*/
		//}//telos else if(s==b)
		}//telos while (stack.isEmpty())
		
			while(! stack.isEmpty()){
					s=stack.get();
					if (s==kl){
						stack.get();
						sum+=stack.get();
					}//telos if (s==-10){
					if (s!=kl){
					stack.get();}
				}//telos while
				System.out.println ("Your credits are: "+sum);
				System.exit(0);	
			}
			
}