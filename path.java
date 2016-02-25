import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner ;
import java.util.Vector;

class PrimePath {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("please input start number...");
		int num1 = scanner.nextInt();
		System.out.println("please input target number...");
		int num2 = scanner.nextInt();
		System.out.println("please input range...");
		int range = scanner.nextInt();
		System.out.println("Lets start...");
		int depth = findDistance(num1, num2, range);
		System.out.println("The depth between "+num1+" and "+num2+" is "+depth);
	}
	
	public static int findDistance(int num1, int num2, int range){
		int depth = 0;
		if(num1 == num2) return depth;

		Collection<Integer> visited = new HashSet<Integer>();
		Collection<Integer> currents = new Vector<Integer>();
		currents.add(num1); 
		
		HashSet<Integer> allPrimesNumSet = getAllPrimeNums(range);
		HashMap<Integer, Collection<Integer>> neighboursMap = new HashMap<Integer, Collection<Integer>>(); 
		
		while(!currents.isEmpty()){
			Collection<Integer> next = new Vector<Integer>();

			for(Integer current : currents){
				visited.add(current);
				Collection<Integer> neighbours = getNeighbours(current, allPrimesNumSet, neighboursMap);
				// get all unvisited neigubours
				neighbours.removeAll(visited);
				// add all unvisited neighbours into NEXT currents
				next.addAll(neighbours);
			}

			if(next.contains(num2)){
				return depth+1;
			}else{
				depth++;
			}

			currents = next;
		}
		return -1;
	}
	
	public static Collection<Integer> getNeighbours(int number, HashSet<Integer> allPrimes, HashMap<Integer, Collection<Integer>> allNeighbour){
		Collection<Integer> result = new Vector<Integer>();
		for(int i: allPrimes){
			if(isOneDight(number, i)){
				result.add(i);
			}
			
			allNeighbour.put(number, result);
		}
		
		return new Vector<Integer>(allNeighbour.get(number));
	}
	
	public static HashSet<Integer> getAllPrimeNums(int n){
		HashSet<Integer> primes = new HashSet<Integer>();
		int[] primeArray = new int[n];
		int i = 0;
		
		boolean[] primeBoolean = new boolean[n+1];
		for(i=2; i < n; i++){
			primeBoolean[i]=true;
		}
		int limit = (int) Math.sqrt(n);
		for (i = 2; i <= limit; i++){
			if(primeBoolean[i]){
				for(int j = i*i; j <= n; j += i){
					primeBoolean[j] = false;					
				}
			}
		}
		
		for(i=1; i < n; i++){
			if(primeBoolean[i]){
				primeArray[i] = i;
			}
		}
		
		for(int p: primeArray){
			if(p != 0){
				primes.add(p);
			}
		}
		
		return primes;
	}
	
	public static boolean isOneDight(int x, int y){
		int count = 0;
		if((x%10) == (y%10))
			count++;
		if(x/10%10 == y/10%10)
			count++;
		if(x/100%10 == y/100%10)
			count++;
		if(x/1000%10 == y/1000%10)
			count++;
		if(x/10000 == y/10000)
			count++;
		return count == 4;
	}
}
