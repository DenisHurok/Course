<#import "parts/common.ftlh" as c>
<@c.page>
<h1>Add New Place</h1>
<form method="post" action="/places">
<div class="form-group">
    <label for="name">Name</label>
    <input type="text" id="name" name="name" class="form-control" required>
</div>
<div class="form-group">
    <label for="rating">Rating</label>
    <input type="text" id="rating" name="rating" class="form-control"required>
</div>
<div class="form-group">
    <label for="mapUrl">Map URL</label>
    <input type="text" id="mapUrl" name="mapUrl" class="form-control"required>
</div>
<div class="form-group">
    <label for="type">Type</label>
    <select id="type" name="typeIds" class="form-control" multiple required>
        <#list typePlaces as typePlace>
        <option value="${typePlace.id}">${typePlace.name}</option>
    </#list>
</select>
</div>
<button type="submit" class="btn btn-primary">Save</button>
        </form>

<a href="/places" class="btn btn-secondary">Cancel</a>
        </@c.page>
