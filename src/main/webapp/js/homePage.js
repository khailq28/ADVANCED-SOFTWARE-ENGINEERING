/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $.ajax({
        url: "/gameCard/MainServlet",
        method: "POST",
        data: {
            action: "userInfo"
        },
        dataType: "json",
        success: function (aData) {
            let sHtmlName = `<strong>Name: </strong>` + aData.name;
            let sHtmlCoin = `<strong>Coin: </strong>` + aData.coin;
            $(".name").html(sHtmlName);
            $(".money").html(sHtmlCoin);
        },
        error: function () {
            alert("error");
        },
        beforeSend: function () {
            $("#loading").css("display", "inline");
        },
        complete: function () {
            $("#loading").css("display", "none");
        }
    });
});
