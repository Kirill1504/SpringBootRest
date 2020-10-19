$(document).ready(function () {
    loadUsersTable();
});

function loadUsersTable() {
    var tBody = document.getElementById("usersTbody");
    var div = document.createElement('div');
    div.id = 'tableDiv';
    $.get("/admin/users", function (data, status) {
        for (i = 0; i < data.length; i++) {
            updateRow(data[i]);
        }
    });
    tBody.appendChild(div);
}

function updateRow(user) {
    var div = document.getElementById("tableDiv");
    var tr = document.createElement('tr');
    var tdId = document.createElement('td');
    tdId.innerHTML = user.id;
    var tdName = document.createElement('td');
    tdName.innerHTML = user.name;
    var tdSurname = document.createElement('td');
    tdSurname.innerHTML = user.surname;
    var tdAge = document.createElement('td');
    tdAge.innerHTML = user.age;
    var tdUsername = document.createElement('td');
    tdUsername.innerHTML = user.username;
    var tdPassword = document.createElement('td');
    tdPassword.innerHTML = user.password;
    var tdRoles = document.createElement('td');
    tdRoles.innerHTML = getRolesNames(user);
    tr.appendChild(tdId);
    tr.appendChild(tdName);
    tr.appendChild(tdSurname);
    tr.appendChild(tdAge);
    tr.appendChild(tdUsername);
    tr.appendChild(tdPassword);
    tr.appendChild(tdRoles);

    var tdInputEdit = document.createElement('td');
    var inputEdit = document.createElement('input');
    inputEdit.type = "submit";
    inputEdit.className = "btn green";
    inputEdit.value = "Edit";
    inputEdit.dataset.toggle = "modal";
    inputEdit.dataset.target = "editUser";
    inputEdit.onclick = function () {
        fillEditWindow(user.username);
    };
    tdInputEdit.appendChild(tdInputEdit);
    tr.appendChild(tdInputEdit);

    var tdInputDelete = document.createElement('td');
    var inputDelete = document.createElement('input');
    inputDelete.type = "submit";
    inputDelete.className = "btn red";
    inputDelete.value = "Delete";
    inputDelete.onclick = function () {
        deleteUserById(user.id);
    };
    tdInputDelete.appendChild(inputDelete);
    tr.appendChild(tdInputDelete);

    div.appendChild(tr);
}

function refreshTable() {
    $('#tableDiv').remove();
    loadUsersTable();
}

function getRolesNames(user) {
    return user.roles.map(function (role) {
        return role.role;
    });
}

function fillEditWindow(userUsername) {
    $.get("admin/users",
        {login: userUsername},
        function (user) {
            document.getElementById("userIdEdit").value = user.id;
            document.getElementById("userNameEdit").value = user.name;
            document.getElementById("userSurnameEdit").value = user.surname;
            document.getElementById("userAgeEdit").value = user.age;
            document.getElementById("userUsernameEdit").value = user.username;
            document.getElementById("userPasswordEdit").value = user.password;

            var rolesNames = new Set(getRolesNames(user));

            if (rolesNames.has("admin")) {
                document.getElementById("checkboxAdminEdit").checked = true;
            } else {
                document.getElementById("checkboxAdminEdit").checked = false;
            }
            if (rolesNames.has("user")) {
                document.getElementById("checkboxUserEdit").checked = true;
            } else {
                document.getElementById("checkboxUserEdit").checked = false;
            }
        });
}
function addUser() {
    var roleSet = new Set(getCheckedCheckboxes());
    var roleAdmin;
    var roleUser;
    if (roleSet.has("ADMIN")) {
        roleAdmin = "ADMIN";
    }
    if (roleSet.has("USER")) {
        roleUser = "USER";
    }
    $.post("/admin/user",
        {
            name: $("#userNameAdd").val(),
            surname: $("#userSurnameAdd").val(),
            age: $("#userAgeAdd").val(),
            username: $("#userUsernameAdd").val(),
            password: $("#userPasswordAdd").val(),
            roleAdmin: roleAdmin,
            roleUser: roleUser
        },
        function () {
            refreshTable();
        });
}

function deleteUserById(id) {
    $.ajax({
        url: "/admin/user",
        type: "DELETE",
        data: {id: id},
        success: function () {
            refreshTable()
        }
    });
}

function updateUser() {
    var roleSet = new Set(getCheckedCheckboxes());
    var roleAdmin;
    var roleUser;
    if (roleSet.has("ADMIN")) {
        roleAdmin = "USER";
    }
    if (roleSet.has("USER")) {
        roleUser = "USER";
    }

    $.ajax({
        url: "admin/user",
        type: "PUT",
        data: {
            id: $("#userIdEdit").val(),
            name: $("#userNameEdit").val(),
            surname: $("#userPasswordEdit").val(),
            roleAdmin: roleAdmin,
            roleUser: roleUser
        },
        success: function () {
            refreshTable();
        }
    });
}

function getCheckedCheckboxes() {
    var selectedCheckBoxes = document.querySelectorAll('input.checkbox:checked');
    return Array.prototype.map.call(selectedCheckBoxes, function (cb) {
        return cb.value
    })
}