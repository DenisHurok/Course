<#import "parts/common.ftlh" as c>
<@c.page>
<h1>Movies</h1>
<form method="get" action="/movies">
<label for="genre">Genre:</label>
<select name="genre" id="genre">
    <option value="">All</option>
    <#list genres as genre>
    <option value="${genre}" <#if genre == selectedGenre>selected</#if>>${genre}</option>
        </#list>
        </select>
<label for="rating">Rating:</label>
<input type="number" name="rating" id="rating" min="0" max="10" step="1" <#if rating??>value="${rating}"</#if>>
<button type="submit">Filter</button>
        </form>
<ul>
<#list movies as movie>
<li>
    <h2>${movie.name}</h2>
    <p>Genre: <#list movie.genres as genre>${genre.name}<#if genre_has_next>, </#if></#list></p>
<p>Rating: ${movie.rating.kp}</p>
<img src="${movie.poster.url}" alt="Poster of ${movie.name}" width="200">
<a href="/movies/${movie.id}">Details</a>
</li>
        </#list>
        </ul>
        </@c.page>
