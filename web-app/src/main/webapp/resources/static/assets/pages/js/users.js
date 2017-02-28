
function editUser(id) {
    $.ajax(
        {
            url: '/user',
            data: {"id": id},
            dataType: 'html',
            success: function (response) {
                $('#contentcontainer').html(response);
            },
            error: function (response) {
                console.error(response);
                alert('server error!');
            }
        }
    );
    return false;
}

function addUser() {
    $.ajax(
        {
            url: '/adduser',
            dataType: 'html',
            success: function (response) {
                $('#contentcontainer').html(response);
            },
            error: function (response) {
                console.error(response);
                alert('server error!');
            }
        }
    );
    return false;
}

// TODO: fix delete
function deleteUser(id) {
    $.ajax(
        {
            url: '/deleteuser',
            type: 'DELETE',
            data: {"id": id}
        }
    );
}

function cancelEditUser () {
    window.location = "/users";
}



