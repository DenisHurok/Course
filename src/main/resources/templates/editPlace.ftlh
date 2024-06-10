<#import "parts/common.ftlh" as c>
<@c.page>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit Place</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Edit Place</h1>
    <form action="/places/${place.id}/edit" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${place.id}">
            <div class="mb-3">
                <label for="name" class="form-label">Name:</label>
                <input type="text" class="form-control" id="name" name="name" value="${place.name}" required>
            </div>
            <div class="mb-3">
                <label for="rating" class="form-label">Rating:</label>
                <input type="number" class="form-control" id="rating" name="rating" value="${place.rating}" min="1" max="10" required>
            </div>
            <div class="mb-3">
                <label for="mapUrl" class="form-label">Map URL:</label>
                <input type="text" class="form-control" id="mapUrl" name="mapUrl"  required>
            </div>
            <div class="mb-3">
                <label for="types" class="form-label">Types:</label>
                <select multiple class="form-control" id="types" name="typeIds">
                    <#list allTypes as type>
                    <option value="${type.id}"<#if place.types?seq_contains(type)> selected</#if>>${type.name}</option>
        </#list>
    </select>
</div>
<button type="submit" class="btn btn-primary">Save</button>
<a href="/places" class="btn btn-secondary">Back</a>
</form>
        </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        </body>
        </@c.page>
