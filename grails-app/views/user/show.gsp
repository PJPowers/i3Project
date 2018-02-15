
<%@ page import="i3project.User" %>
<%@ page import="i3project.Transaction" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		<r:require module="export"/>
	</head>
	<body>
		<a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-user" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list user">
			
				<g:if test="${userInstance?.userName}">
				<li class="fieldcontain">
					<span id="userName-label" class="property-label"><g:message code="user.userName.label" default="User Name" /></span>
					
						<span class="property-value" aria-labelledby="userName-label"><g:fieldValue bean="${userInstance}" field="userName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.startingBal}">
				<li class="fieldcontain">
					<span id="startingBal-label" class="property-label"><g:message code="user.startingBal.label" default="Starting Bal" /></span>
					
						<span class="property-value" aria-labelledby="startingBal-label"><g:fieldValue bean="${userInstance}" field="startingBal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.currentBal}">
				<li class="fieldcontain">
					<span id="currentBal-label" class="property-label"><g:message code="user.currentBal.label" default="Current Bal" /></span>
					
						<span class="property-value" aria-labelledby="currentBal-label"><g:fieldValue bean="${userInstance}" field="currentBal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.transactions}">
				<li class="fieldcontain">
					<span id="transactions-label" class="property-label"><g:message code="user.transactions.label" default="Transactions" /></span>
					
						<g:each in="${userInstance.transactions}" var="t">
						<span class="property-value" aria-labelledby="transactions-label"><g:link controller="transaction" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:userInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
				<fieldset class="buttons">
					
					<g:link  controller="Transaction"action="newTrans">Track Expenses Transaction</g:link>
				</fieldset>
				<fieldset class="buttons">
				<export:formats formats="['Download to csv']" action="go"/>
				</fieldset>
			</g:form>
		</div>
		
	</body>
</html>
