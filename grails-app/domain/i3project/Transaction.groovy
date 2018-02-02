package i3project

class Transaction {
	String item
	double amount
	String toString(){
		"${item}"
	}
	
	static belongsTo = [owner : User]

    static constraints = {
		
		item()
		amount()
    }
}
