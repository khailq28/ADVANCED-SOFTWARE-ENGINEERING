/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $("#form_user").focusout(function () {
        let userLength = $("#form_user").val().length;
        if (userLength < 5 || userLength > 20) {
            $("#form_user").val("");
            let html = `
                <div class="alert alert-warning fade show container" role="alert">
                Should be between 5-20 characters
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button></div>`;
            $("#username_erorr").html(html);
            $("#username_erorr").show();
        } else {
            $("#username_erorr").hide();
        }
    });
    
    $("#form_pass").focusout(function () {
        let userLength = $("#form_pass").val().length;
        if (userLength < 5 || userLength > 20) {
            $("#form_pass").val("");
            let html = `
                <div class="alert alert-warning fade show container" role="alert">
                Should be between 5-20 characters
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button></div>`;
            $("#password_erorr").html(html);
            $("#password_erorr").show();
        } else {
            $("#password_erorr").hide();
        }
    });
    
    $("#form_repeat").focusout(function () {
        let pass = $("#form_pass").val();
        let repeat = $("#form_repeat").val();
        if (pass !== repeat) {
            $("#form_repeat").val("");
            let html = `
                <div class="alert alert-warning fade show container" role="alert">
                Those password didn't match. Try again.
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button></div>`;
            $("#repeatPass_erorr").html(html);
            $("#repeatPass_erorr").show();
        } else {
            $("#repeatPass_erorr").hide();
        }
    });
});
