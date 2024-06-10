<#import "parts/common.ftlh" as c>
<@c.page>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Movie</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Add Movie</h1>
    <form action="/addMovie" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="name" class="form-label">Name:</label>
        <input type="text" class="form-control" id="name" name="name" required>
    </div>
    <div class="mb-3">
        <label for="year" class="form-label">Year:</label>
        <input type="number" class="form-control" id="year" name="year" required>
    </div>
    <div class="mb-3">
        <label for="rating" class="form-label">Rating:</label>
        <input type="number" name="rating" id="rating" value="${rating!''}" class="form-control" min="1" max="10" required>

    </div>
    <div class="mb-3">
        <label for="poster" class="form-label">Poster:</label>
        <input type="file" class="form-control" id="poster" name="poster" required>
    </div>
    <div class="mb-3">
        <label for="shortDescription" class="form-label">Short Description:</label>
        <textarea class="form-control" id="shortDescription" name="shortDescription" required></textarea>
    </div>
    <div class="mb-3">
        <label for="longDescription" class="form-label">Long Description:</label>
        <textarea class="form-control" id="longDescription" name="longDescription" required></textarea>
    </div>
    <div class="mb-3">
        <label for="genres" class="form-label">Genres:</label>
        <select multiple class="form-control" id="genres" name="genreIds">
            <#list genres as genre>
            <option value="${genre.id}">${genre.name}</option>
        </#list>
    </select>
</div>

<button type="submit" class="btn btn-primary">Add Movie</button>
</form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        </body>

        </@c.page>