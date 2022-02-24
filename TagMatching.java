import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class TagMatching{
	public static void main(String[] args) {
		StringStack<String> stack= new StringStackImpl<>();
		StringStack<String> tempstack= new StringStackImpl<>();
		String tempa;
		String tempb;
		boolean f;
		char s;
		String str;
		String content= "";
		double total=0;
		double total2=0;
		String name;
		try{
			String filename = args[0];
			BufferedReader file = new BufferedReader(new FileReader(filename));
			
			while ((str = file.readLine()) != null) {
				content +=str;
				}
			file.close();
			}
	catch (IOException e) {
            System.out.println	("Error reading line in file ...");
			System.exit(0) ;
		}	
		
	for (int i=0 ; i< content.length();i++){
		name="";
			s=content.charAt(i);
			if (s== '<'){
				for (int j=i;i< content.length();i++){
					name+= Character.toString(content.charAt(i));
					if (content.charAt(i)=='>'){
						//name+= Character.toString(content.charAt(i));
						stack.push(name);
						break;
					}
				}
			}
					
		}	
	//stack.printStack(System.out);
	f=true;
	total2=stack.size();
	while ((stack.size()>0) || (tempstack.size()>0)){
		f=true;
		if (stack.size()>0){
			tempa=stack.pop();
		}else{
			tempa=tempstack.pop();
		}
		//briskoume to anti8eto tou panw panw stoixeioy
		s=tempa.charAt(1);
		if (s!='/'){
			tempb="</";
			for (int i=1;i<tempa.length();i++){
				tempb+=tempa.charAt(i);
			}
			}
		else{
			tempb="<";
			for (int i=2;i<tempa.length();i++){
				tempb+=tempa.charAt(i);
			}
		}
		// brikame to antiueto
	
	
		//sygkrinoume to prwto me to deytero stoixeio kai an moiazoyn diagrafontai an oxi stelnoyme ta stoixeia se allh lista mexri na broume ayto p psaxnoume
		
		
		while ((stack.size()>0) && (f)){
			
			tempa=stack.peek();
			
			if (tempb.contains(tempa)){ //an einai idia exoume epistrofh 0
				stack.pop();
				total++;
				f=false;

				}
			else{
				tempstack.push(tempa);
				stack.pop();
				continue;
			}
		}
		
		while ((tempstack.size()>0) && (f)){
			tempa=tempstack.peek();

			
			if (tempb.contains(tempa)){ //an einai idia exoume epistrofh 0
				tempstack.pop();
				total++;
				f=false;

				}
			else{
				stack.push(tempa);
				tempstack.pop();
				continue;
			}	
		}
		
	}

		if (total!=total2/2){
			System.out.println("ERROR: Not matching tags");
			System.exit(0);
		}

	
	System.out.println("Tags are okay");
	}	
}