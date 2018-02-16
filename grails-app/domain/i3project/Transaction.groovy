package i3project

class Transaction {
	String item
	double amount
	double dollarAmt
	double dollarTimes = 0.086
	

	String toString(){
		"${item}"
	}
	
	static belongsTo = [owner : User]

    static constraints = {
		
		item()
		amount()
    }
}
