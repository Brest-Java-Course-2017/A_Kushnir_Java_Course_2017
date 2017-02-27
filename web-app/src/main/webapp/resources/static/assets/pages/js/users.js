$(document).ready(function(){
        PopUpHide();
    }
);

function PopUpShow(){
    $("#popup").show();
}

function PopUpHide(){
    $("#popup").hide();
}

function editUser(id) {
    $.ajax(
        {
            url: '/user',
            data: {"id": id},
            dataType: 'html',
            success: function (response) {
                PopUpShow();
                $('#popupconrtent').html(response);
            },
            error: function (response) {
                console.error(response);
                alert('server error!');
            }
        }
    );
    return false;
}

function confirmEdit (user) {
}

function closePopup() {
    PopUpHide();
    $('#popupconrtent').html('<img src="../../resources/static/assets/img/pbar.gif" alt="clock">');
}



