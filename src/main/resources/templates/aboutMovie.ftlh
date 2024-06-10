<#import "parts/common.ftlh" as c>
<@c.page>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit Movie</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Edit Movie</h1>
            <div class="mb-3">
                <label for="name" class="form-label">Name:</label>
                <input type="text" class="form-control" id="name" name="name" value="${movie.name}" readonly>
            </div>
            <div class="mb-3">
                <label for="year" class="form-label">Year:</label>
                <input type="number" class="form-control" id="year" name="year" value="${movie.year}" readonly>
            </div>
            <div class="mb-3">
                <label for="rating" class="form-label">Rating:</label>
                <input type="number" class="form-control" id="rating" name="rating" value="${movie.rating}" min="1" max="10" readonly>
            </div>
            <div class="mb-3">
                <#if movie.poster?has_content>
                <img src="${movie.poster}" alt="Poster" style="max-width: 200px; margin-top: 10px;">
                </#if>
            </div>
            <div class="mb-3">
                <label for="shortDescription" class="form-label">Short Description:</label>
                <textarea class="form-control" id="shortDescription" name="shortDescription" readonly>${movie.shortDescription}</textarea>
            </div>
            <div class="mb-3">
                <label for="longDescription" class="form-label">Long Description:</label>
                <textarea class="form-control" id="longDescription" name="longDescription" readonly>${movie.longDescription}</textarea>
            </div>
            <div class="mb-3">
                <label for="genres" class="form-label">Genres:</label>
                <select multiple class="form-control" id="genres" name="genreIds">
                    <#list allGenres as genre>
                    <#if movie.genres?seq_contains(genre)>
                    <option value="${genre.id}" selected>${genre.name}</option>
                    <#else>
                    <option value="${genre.id}">${genre.name}</option>
                </#if>
            </#list>
        </select>
</div>
<a href="/movies" class="btn btn-secondary">
<i class="fas fa-arrow-left"></i> Назад
</a>
</form>
        </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        </body>
        </@c.page>
