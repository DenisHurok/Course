<#import "parts/common.ftlh" as c>
<@c.page>
<h1>Movie Details</h1>
<div>
<h2>${movieDetails.name}</h2>
<p>Genre: <#list movieDetails.genres as genre>${genre.name}<#if genre_has_next>, </#if></#list></p>
<p>Rating: ${movieDetails.rating.kp?c}</p>
<p>Description: ${movieDetails.description}</p>
<img src="${movieDetails.poster.url}" alt="Poster of ${movieDetails.name}" width="200">
<a href="https://www.kinopoisk.ru/film/${movieDetails.id}" class="btn btn-primary">Visit Movie Page</a>
</div>
        </@c.page>
