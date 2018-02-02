<%@ page import="i3project.Transaction" %>



<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'item', 'error')} required">
	<label for="item">
		<g:message code="transaction.item.label" default="Item" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="item" required="" value="${transactionInstance?.item}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="transaction.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="amount" value="${fieldValue(bean: transactionInstance, field: 'amount')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'owner', 'error')} required">
	<label for="owner">
		<g:message code="transaction.owner.label" default="Owner" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="owner" name="owner.id" from="${i3project.User.list()}" optionKey="id" required="" value="${transactionInstance?.owner?.id}" class="many-to-one"/>

</div>

