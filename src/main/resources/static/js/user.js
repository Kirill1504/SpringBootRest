$(document).ready(function () {
    $.getJSON('http://localhost:8080/user', function (json) {
        var tr = [];
        for (var i = 0; i < json.length; i++) {
            tr.push('<tr>');
            tr.push('<td>' + json[i].id + '</td>');
            tr.push('<td>' + json[i].name + '</td>');
            tr.push('</tr>');
        }
        $('table').append($(tr.join('')));
    });
});
