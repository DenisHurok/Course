<#import "parts/common.ftlh" as c>
<@c.page>
<div>Фильтрация</div>
<div class="form-row">
<div class="form-group col-md-6">
    <form method="get" action="/movies/filters" class="form-inline">
        <input type="text" name="category" class="form-control" value="${category?ifExists}" placeholder="Введите категорию">
        <input type="number" name="rating" class="form-control ml-2" value="${rating?ifExists}" placeholder="Введите минимальный рейтинг">
        <button type="submit" class="btn btn-primary ml-2">Фильтровать</button>
    </form>
</div>
</div>
<h5 class="text-center">Список фильмов</h5>
<table class="table">
<thead>
<tr>
    <th scope="col">Название</th>
    <th scope="col">Рейтинг</th>
    <th scope="col"></th>
</tr>
</thead>
<#list movies as movie>
<tr>
    <td>${movie.title}</td>
    <td>${movie.vote_average}</td>
    <td>
        <a class="btn btn-success" href="/movies/${movie.id}">Просмотреть</a>
    </td>
</tr>
<#else>
<tr>
    <td colspan="3" class="text-center">Нет фильмов</td>
</tr>
</#list>
        </table>
        </@c.page>
