<#import "parts/common.ftlh" as c>
<@c.page>
<div>Search</div>
<div class="form-row">
<div class="form-group col-md-6">
    <form method="get" action="/editProduct" class="form-inline">
        <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by name">
        <button type="submit" class="btn btn-primary ml-2">Search</button>
    </form>
</div>
</div>
Add
<div class="form-row">
<div class="form-group col-md-6">
<form method="post"action="add">
    <input  type="number" name="amount" class="form-control mt-2" placeholder="Input product amout"/>
    <input type="text" name="name" class="form-control  mt-2" placeholder="name">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit" class="btn btn-primary ml-0  mt-2">submit</button>

</form>
</div>
</div>
<h5 class="text-center">Product list</h5>
<table  class="table" >
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Amount</th>
        <th scope="col">Name</th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    </thead>
    <#list products as product>
    <tr>
        <td>${product.id}</td>
        <td >${product.amount}</td>
    <td>${product.name}</td>
  <td><form method="post" action="/delete">
      <input type="hidden" name="id" value="${product.id}" />
      <input type="hidden" name="_csrf" value="${_csrf.token}">
      <button type="button" class="btn btn-danger">Dlete</button>
  </td>
    <td><a class="list-group-item list-group-item-action  "  href="/product/${product.id}">Edit</a></td>

</tr>
<div>
    <#else>
    <h4>No product</h4>

</#list>
</div>
</@c.page>