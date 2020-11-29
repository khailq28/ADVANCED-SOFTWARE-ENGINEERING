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
                                            <div class="money">Coin: ` + aData.TopUser[j].coin + ` - Lv: ` + aData.TopUser[j].lv + `</div>
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
            $("#exampleModalLabel").html("User Info");

            if (aData.giftdate) {
                let sHtmlGift = `<img id="received" src="images/gift.png">`;
                $("#gift").html(sHtmlGift);

                $("#received").click(function () {
                    $.ajax({
                        url: "/gameCard/MainServlet",
                        method: "POST",
                        data: {
                            action: "received",
                            lv: aData.lv
                        },
                        dataType: "json",
                        success: function (aData) {
                            if (aData.status) {
                                location.reload(true);
                                alert("You received " + aData.coinReceived + " coins.");
                            }
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
            }
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
    
    $("#gameInfo").click(function () {
        let sHtmlModalContent = `
                <div class="container">
                    <strong>1. Điểm danh hằng ngày: </strong>Số tiền nhận được của mỗi
                    ngày sẽ phụ thuộc vào cấp độ của người chơi<br>
                    Lv 1 - 10: 100 coins<br>
                    Lv 10 - 20: 300 coins<br>
                    Lv 20 - 40: 500 coins<br>
                    Lv 40 - 60: 700 coins<br>
                    Lv 60 trở lên: 1000 coins<br>
                    <strong>2. Quy luật chơi:</strong><br>
                    - Số tiền cược: tiền cược sẽ được nhân 2 nếu bạn được xì bàng.<br>
                    - Trận thắng sẽ được 25 exp, trận thua và hòa sẽ nhận được 10 exp.
                     Cứ đủ 100 exp thì sẽ được lên 1 cấp.
                    - Cách tính điểm trường hợp đặc biệt:<br>
                    xì bàng > xì dách > ngũ linh<br>
                    <strong>3. Minigame: </strong> minigame ở đây là game lật bài
                    người chơi sẽ tìm 5 cặp lá bài giống nhau. Hoàn thành bạn sẽ
                    nhận được 50 coins<br>
                </div>`;
        $("#exampleModalLabel2").html("Thông tin game");
        $("#modal-content2").html(sHtmlModalContent); 
    });
});
