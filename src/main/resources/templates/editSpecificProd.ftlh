<#import "parts/common.ftlh" as c>

<@c.page>
        Product Editor

<form action="/product" method="post">
<label>Amount: </label>
<input type="number" name="amount" value="${product.amount}">
<label>Name: </label>
<input type="text" name="name" value="${product.name}">

<input type="hidden" value="${product.id}" name="id">
<input type="hidden" value="${_csrf.token}" name="_csrf">
<button type="submit">Save</button>
</form>
        </@c.page>



