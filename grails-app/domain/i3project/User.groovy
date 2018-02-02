package i3project

class User {
	String userName
	double startingBal
	double currentBal 
	
	
	/*public String getBal() {
		return currentBal;
	}
*/
	public void setBal(double bal) {
		def trans = new Transaction()
		currentBal = startingBal - bal
	}

	
	String toString(){
		"${userName}"
	}
	
	static hasMany = [transactions: Transaction]

    static constraints = {
		
		userName()
		startingBal()
		
    }
}
