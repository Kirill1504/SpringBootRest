$(document).ready(function () {
    $.getJSON('http://localhost:8080/admin/users', function (json) {
        var tr = [];
        for (var i = 0; i < json.length; i++) {
            tr.push('<tr>');
            tr.push('<td>' + json[i].id + '</td>');
            tr.push('<td>' + json[i].name + '</td>');
            tr.push('<td>' + json[i].surname + '</td>');
            tr.push('<td>' + json[i].age + '</td>');
            tr.push('<td>' + json[i].username + '</td>');
            tr.push('<td>' + json[i].password + '</td>');
            tr.push('</tr>');
        }
        $('usersTbody').append($(tr.join('')));
    })
});