<#import "parts/common.ftlh" as c>

<@c.page>
        User editor

<form action="/user" method="post">
<input type="text" name="username" value="${user.username}">
    <#list roles as role>
    <div>
        <label class="col-sm-2 col-form-label"><input type="checkbox" name="${role}"
            ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
    </div>
</#list>
<input type="hidden" value="${user.id}" name="userId">
<button type="submit">Save</button>
</form>
        </@c.page>




