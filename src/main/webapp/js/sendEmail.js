/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    $("#sendMail").click(function() {
        let sEmail = $("#email").val();
        if (sEmail !== ""
            && /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(sEmail)) {
            $.ajax({
//                url: "/gameCard/SendMail",
                url: "/gameCard/SendMail",
                method: "POST",
                data: {
                    email: sEmail
                },
                dataType: "text",
                success: function(aData) {
                    alert(aData);
                },
                error: function() {
                    alert("error");
                },
                beforeSend: function() {
                    $("#loading").css("display", "inline");
                },
                complete: function() {
                    $("#loading").css("display", "none");
                }
            });
        } else {
            alert("Please include an '@' in the email address.")
        }
    });
});
