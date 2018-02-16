package i3project



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {
	CalculationService calculationservice

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	String csvMimeType
	
	   String encoding
	
	   def go(Transaction transactionInstance,User userInstance) {
		   final String filename = 'expense.csv'
		   def lines = Transaction.findAll().collect { [it.owner,it.item, it.amount, it.dollarAmt].join('   -   ') } as List<String>
	
		   def outs = response.outputStream
		   response.status = OK.value()
		   response.contentType = "${csvMimeType};charset=${encoding}";
		   response.setHeader "Content-disposition", "attachment; filename=${filename}"
	//Content-disposition
		   lines.each { String line ->
			   outs << "${line}\n\n"
		   }
	
		   outs.flush()
		   outs.close()
	   }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model:[userInstanceCount: User.count()]
		

    }

    def show(User userInstance) {
        respond userInstance
    }

    def create() {
        respond new User(params)
    }
	
		

    @Transactional
    def save(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'create'
            return
        }
		//User userObj = new User
		//User userInstance
		//userInstance.currentBal = transactionInstance.amount
		userInstance.currentBal = userInstance.startingBal
        userInstance.save flush:true
		
		//chain(controller:'transactionInstance',action:'save',model:[user:userInstance])
		
		//userInstance?.currentBal = transactionInstance.amount

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*' { respond userInstance, [status: CREATED] }
        }
    }

    def edit(User userInstance) {
        respond userInstance
    }

    @Transactional
    def update(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'edit'
            return
        }

        userInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*'{ respond userInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(User userInstance) {

        if (userInstance == null) {
            notFound()
            return
        }

        userInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
