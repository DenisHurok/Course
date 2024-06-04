<#import "parts/common.ftlh" as c>
<@c.page>
<div class="form-row">
<div class="form-group col-md-6">
    <form method="get" action="/main" class="form-inline">
        <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by name">
        <button type="submit" class="btn btn-primary ml-2">Search</button>
    </form>
</div>
</div>

    <div>Подборки </div>

<table  class="table" >
<thead>
<tr>
    <th scope="col">Id</th>
    <th scope="col">Amount</th>
    <th scope="col">Name</th>
</tr>
</thead>
<#list products as product>
    <tr>
        <td>${product.id}</td>
        <td >${product.amount}</td>
        <td>${product.name}</td>
    </tr>
<div>
<#else>
<h4>No movies</h4>

 </#list>

</div>

</@c.page>


