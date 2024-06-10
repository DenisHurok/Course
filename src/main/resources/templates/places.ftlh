<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">
<@c.page>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Places to Visit</h1>
    <form method="get" action="/places" class="form-inline mb-4">
        <div class="form-group mr-2">
            <label for="name" class="mr-2">Name:</label>
            <input type="text" name="name" id="name" value="${name!''}" class="form-control">
        </div>
        <div class="form-group mr-2">
            <label for="rating" class="mr-2">Rating:</label>
            <input type="number" step="0.1" name="rating" id="rating" value="${rating?default('')}" class="form-control">
        </div>
        <div class="form-group mr-2">
            <label for="type" class="mr-2">Type:</label>
            <select name="typeId" id="type" class="form-control">
                <option value="">All</option>
                <#list types as type>
                <option value="${type.id?c}"
                <#if selectedTypeId?has_content && type.id?string == selectedTypeId>
                selected
            </#if>
                    >${type.name}</option>
    </#list>
</select>
<#if isAdmin>
<a href="/places/addPlace" class="btn btn-success ml-2">Add New Place</a>
</#if>
</div>
<button type="submit" class="btn btn-primary">Filter</button>
</form>

<table class="table table-striped">
<thead>
<tr>
    <th>Name</th>
    <th>Rating</th>
    <th>Map URL</th>
    <th>Types</th>
    <th>Actions</th>
</tr>
</thead>
<tbody>
<#list places as place>
<tr>
    <td>${place.name}</td>
    <td>${place.rating}</td>
    <td>
        <#if place.mapUrl??>
        <iframe src="${place.mapUrl}" width="400" height="300" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        <#else>
        <p>Карта недоступна</p>
    </#if>
</td>

<td>
        <#list place.types as type>
        ${type.name}<#if type_has_next>, </#if>
</#list>
</td>
<td>
    <#if isAdmin>
    <a href="/places/${place.id}/edit" class="btn btn-primary">Edit</a>
    <form action="/places/${place.id}/delete" method="post" style="display:inline;">
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>
</#if>
</td>
</td>
</tr>
        </#list>
        </tbody>
        </table>
        </div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        </@c.page>
