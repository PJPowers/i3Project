<%@ page import="i3project.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'userName', 'error')} required">
	<label for="userName">
		<g:message code="user.userName.label" default="User Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="userName" required="" value="${userInstance?.userName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'startingBal', 'error')} required">
	<label for="startingBal">
		<g:message code="user.startingBal.label" default="Starting Bal" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="startingBal" required="" value="${userInstance?.startingBal}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'currentBal', 'error')} required">
	<label for="currentBal">
		<g:message code="user.currentBal.label" default="Current Bal" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="currentBal" value="${fieldValue(bean: userInstance, field: 'currentBal')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'transactions', 'error')} ">
	<label for="transactions">
		<g:message code="user.transactions.label" default="Transactions" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.transactions?}" var="t">
    <li><g:link controller="transaction" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="transaction" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'transaction.label', default: 'Transaction')])}</g:link>
</li>
</ul>


</div>

