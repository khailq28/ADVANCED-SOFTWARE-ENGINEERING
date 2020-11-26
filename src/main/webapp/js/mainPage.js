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
            let sHtmlRanking = `<li class="titleRank">Ranking list</li>`;
            for (let i = 1; i <= 6; i++) {
                let sClass = "userRank" + i;
                let j = i - 1;
                sHtmlRanking += `
                    <li class="userRank ` + sClass + `">
                        <table>
                            <tbody>
                                <tr>
                                    <td style="1%"><div class="numRank">` + i + `</div></td>
                                    <td style="width: 40px"><div class="avatar"></div></td>
                                    <td>
                                        <div class="infoUser">
                                            <div class="name">` + aData.TopUser[j].name + `</div>
                                            <div class="money">Coin: ` + aData.TopUser[j].coin + ` - Lv: `+ aData.TopUser[j].lv +`</div>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </li>`;
            }
            let sHtmlName = `<strong>Name: </strong>` + aData.name;
            let sHtmlCoin = `<strong>Coin: </strong>` + aData.coin;
            let sHtmlLv = `<strong>Lv: </strong>` + aData.lv;
            let sHtmlExp = `<strong>Exp: </strong>` + aData.exp;
            let sHtmlModalContent = `
                <div class="container">
                    <div class="row">
                        <div class="col-md-4">
                            <img src="/home/khai/Working/2020/ChuyenDeCNPM/Final/gameCard/target/gameCard-1.0-SNAPSHOT/avatar/download.png" style="width: 130px; height: 130px; border: 5px solid orange;">
                        </div>
                        <div class="col-md-8">
                            <h4>` + aData.name + `</h4>
                            <p>
                                <b>Username: </b>` + aData.username + `<br>
                                <b>Coins: </b>` + aData.coin + `<br>
                                <b>Lv: </b>` + aData.lv + `<br>
                                <b>Exp: </b>` + aData.exp + `<br>
                            </p>
                            <form action="UploadServlet" method="post" enctype="multipart/form-data" >
                                <b>Change avatar:</b>
                                <input type="file" style="overflow: hidden;" name="file">
                                <br>
                                <div style="text-align: right; padding: 10px">
                                    <input type="submit" value="Save changes"
                                    class="btn btn-primary">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>`;
            $("#rankingList").html(sHtmlRanking);
            $("#myname").html(sHtmlName);
            $("#mymoney").html(sHtmlCoin);
            $("#mylv").html(sHtmlLv);
            $("#myexp").html(sHtmlExp);
            $("#modal-content").html(sHtmlModalContent);
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
