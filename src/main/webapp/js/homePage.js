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
            console.log(aData);
            console.log(aData.TopUser[0].name);
            let sHtmlRanking = `<li class="titleRank">Ranking list</li>`;
            for(let i = 1; i <= 7; i++) {
                let sClass = "userRank" + i;
                let j = i - 1;
                sHtmlRanking += `
                    <li class="userRank `+ sClass +`">
                        <table>
                            <tbody>
                                <tr>
                                    <td style="1%"><div class="numRank">`+ i +`</div></td>
                                    <td style="width: 40px"><div class="avatar"></div></td>
                                    <td>
                                        <div class="infoUser">
                                            <div class="name">`+ aData.TopUser[j].name +`</div>
                                            <div class="money">`+ aData.TopUser[j].coin +`</div>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </li>`;
            }
            let sHtmlName = `<strong>Name: </strong>` + aData.name;
            let sHtmlCoin = `<strong>Coin: </strong>` + aData.coin;
            $("#rankingList").html(sHtmlRanking);
            $("#myname").html(sHtmlName);
            $("#mymoney").html(sHtmlCoin);
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
