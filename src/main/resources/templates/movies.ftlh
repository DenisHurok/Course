<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>
<div class="background-container">
<div class="background-overlay"></div>
<div class="quicfun-text-container" style="margin-top: -200px; text-align: center;">
    <span class="quicfun-text" style="font-size: 200px; display: block; text-align: center;">Movies</span>
    <div class="container mt-32">
    <form method="get" action="/movies" class="form-inline mb-4">
        <div class="form-group mr-2">
            <label for="name" class="mr-2">Title:</label>
            <input type="text" name="name" id="name" value="${name!''}" class="form-control" >
        </div>
        <div class="form-group mr-2">
            <label for="year" class="mr-2">Year:</label>
            <input type="number" name="year" id="year" value="${year!''}" class="form-control" >
        </div>
        <div class="form-group mr-2">
            <label for="genre" class="mr-2">Genre:</label>
            <select name="genreId" id="genre" class="form-control">
                <option value="">Все</option>
                <#list genres as g>
                <option value="${(g.id?c)!''}"
                <#if selectedGenreId?? && (g.id?string)!'' == selectedGenreId>
                selected
            </#if>
                    >${(g.name)!''}</option>
    </#list>
</select>
</div>
<div class="form-group mr-2">
    <label for="rating" class="mr-2">Rating:</label>
    <input type="number" name="rating" id="rating" value="${rating!''}" class="form-control" min="1" max="10">
</div>
<button type="submit" class="btn btn-primary">Filter</button>
<#if isAdmin>
<a href="/addMovie" class="btn btn-success ml-2">Добавить фильм</a>
</#if>
<table class="table table-striped">
<thead>
<tr>
    <th>Poster</th>
    <th>Title</th>
    <th>Genre</th>
    <th>Rating</th>
    <th>Year</th>
    <th></th>
</tr>
</thead>
<tbody>
<#list movies as movie>
<tr>
    <td><img src="${(movie.poster)!'/images/default_poster.jpg'}" alt="${(movie.title)!'No title'}" class="img-thumbnail" width="100"></td>
    <td>${(movie.name)!'Unknown Title'}</td>
    <td>
        <#list movie.genres as genre>
        ${genre.name}<#if genre_has_next>, </#if>
</#list>
</td>
<td>${(movie.rating)!'N/A'}</td>
<td>${(movie.year)!'N/A'}</td>
<td>
    <#if isAdmin>
    <a href="/editMovie?id=${movie.id}" class="btn btn-primary">Редактировать</a>
    <form action="/deleteMovie" method="post">
        <input type="hidden" name="id" value="${movie.id}"></input>
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>
</#if>
    <form action="/movies/${movie.id}" method="get">
        <input type="hidden" name="id" value="${movie.id}"></input>
        <button type="submit" class="btn btn-outline-secondary">About</button>
    </form>

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
