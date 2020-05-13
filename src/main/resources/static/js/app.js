$(document).ready(function() {
    $('#allUsers').DataTable( {
        responsive: true
    } );

    $("#cart-alert").hide();
    $("#refreshCart").on('click',function(){
        const cartLineId = $(this).attr('value');
        const countField = $('#count_' + cartLineId);
        const originalCount = countField.attr('value');
        // do the checking only the count has changed
        if(countField.val() !== originalCount) {
            // check if the quantity is within the specified range
            if(countField.val() < 1 || countField.val() > 3) {
                // set the field back to the original field
                countField.val(originalCount);
                $("#cart-alert").show();
            }
            else {
                // use the window.location.href property to send the request to the server
                const root=window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
                alert(root);
                var updateUrl = root + '/cart/' + cartLineId + '/update?count=' + countField.val();
                window.location.href = updateUrl;
            }
        }
    });
    window.setTimeout(function() {
        $(".alert-auto-close").fadeTo(500, 0).slideUp(500, function(){
            $(this).remove();
        });
    }, 2000);
} );