<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<div class="container mt-3">
<h2 class="mb-3">Регистрация</h2>
<#if message??>
<div class="alert alert-info" role="alert">
    ${message}
</div>
</#if>
<@l.login "/registration" true />
        </div>
        </@c.page>
