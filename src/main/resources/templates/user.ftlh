<#import "parts/common.ftlh" as c>
<@c.page>
List of users
<table class="table" >
    <thead>
    <tr>
    <th scope="col">Name</th>
        <th scope="col">Role</th>
        <th scope="col"></th>
    </tr>
    </thead>
<#list users as user>
    <tr>
        <td>${user.username}</td>
        <td><#list user.roles as role>${role}<#sep>, </#list></td>
        <td><a href="/user/${user.id}">Edit</a></td>
    </tr>
</#list>
</table>
</@c.page>






