
class BottleProblem{
	public static void main(String[] args) {
		int bottles = calculateBottles(10);
		System.out.println("Bottles are " + bottles);
	}

	/* 
	$2 = 1 new bottle
	4 bottle caps = 1 new bottle
	2 empty bottles = 1 new bottle
	at most how many bottles can we drink with given money? 
	(we can say that money is even)
	**/
	public static int calculateBottles(int money){
		int usedBottles = 0, cap = 0, count = 0;
		int newBottles = money / 2;

		while(newBottles > 0){
			cap += newBottles;
			usedBottles += newBottles;
			count += newBottles;
			// drink all the bottles
			newBottles = 0;
			// see if we can change caps for new bottles
			if(cap >= 4){
				newBottles += cap / 4;
				cap -= 4 * (cap/4);
			}
			// see if we can change old bottles for new bottles
			if(usedBottles >= 2){
				newBottles += usedBottles / 2;
				usedBottles -= 2 * (usedBottles/2);
			}
		}
		return count;
	}

}