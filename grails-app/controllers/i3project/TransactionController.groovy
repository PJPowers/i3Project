package i3project



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional



@Transactional(readOnly = true)
class TransactionController {
def exportService
def grailsApplication  //inject GrailsApplication

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def newTrans() {
		
		render(view:"create");
		
	}
	def export() {
		
		render(view:"exportTest");
		//final String filename = 'book.csv'
		//def lines = Transaction.findAll().collect { [it.item, it.amount].join('---') } as List<String>
		
	}
	
	def exportTrans()
	{
		if(!params.max) params.max = 10		
				if(params?.format && params.format != "html")
				{
					response.contentType = grailsApplication.config.grails.mime.types[params.format]
					response.setHeader("Content-disposition", "attachment; filename=books.${params.extension}")
		
					exportService.export(params.format, response.outputStream,Transaction.list(params), [:], [:])
				}
		
				[ transactionInstance: Transaction.list( params ) ]
	}
	    
    
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Transaction.list(params), model:[transactionInstanceCount: Transaction.count()]
		
		
    }

    def show(Transaction transactionInstance) {
        respond transactionInstance
    }

    def create() {
        respond new Transaction(params)
    }

	def deduct(Transaction transactionInstance){
		/*def userObj = new User()
		def transObj = new Transaction()
		double bal = transObj.amount
		userObj.setBal(bal)
		*/
		
		
	}
    @Transactional
    def save(Transaction transactionInstance, User userInstance) {
	
        if (transactionInstance == null) {
            notFound()
            return
        }

        if (transactionInstance.hasErrors()) {
            respond transactionInstance.errors, view:'create'
            return
        }
		
		//User userInstance 
		//userInstance.currentBal = userInstance.startingBal - transactionInstance.amount
		//userInstance.save flush:true
	
		transactionInstance.dollarAmt = transactionInstance.amount * transactionInstance.dollarTimes
		userInstance.currentBal = userInstance.currentBal - transactionInstance.amount
		userInstance.save flush:true
		transactionInstance.save flush:true
		
		

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'transaction.label', default: 'Transaction'), transactionInstance.id])
                redirect transactionInstance
				
				
            }
            '*' { respond transactionInstance, [status: CREATED] }
        }
		
    }

    def edit(Transaction transactionInstance) {
        respond transactionInstance
    }

    @Transactional
    def update(Transaction transactionInstance) {
        if (transactionInstance == null) {
            notFound()
            return
        }

        if (transactionInstance.hasErrors()) {
            respond transactionInstance.errors, view:'edit'
            return
        }

        transactionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Transaction.label', default: 'Transaction'), transactionInstance.id])
                redirect transactionInstance
            }
            '*'{ respond transactionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Transaction transactionInstance) {

        if (transactionInstance == null) {
            notFound()
            return
        }

        transactionInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Transaction.label', default: 'Transaction'), transactionInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
