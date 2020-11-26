/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var myCards = document.getElementById('container');
var resultsArray = [];
var counter = 0;
var seconds = 00;
var tens = 00;
var appendTens = document.getElementById("tens");
var appendSeconds = document.getElementById("seconds");
var Interval;
var images = [
    'A♥',
    'A♦',
    'A♣',
    'A♠',
    'joker'
];

var clone = images.slice(0); // duplicate array
var cards = images.concat(clone); // merge to arrays 

// Shufffel function
function shuffle(o) {
    for (var j, x, i = o.length; i; j = Math.floor(Math.random() * i), x = o[--i], o[i] = o[j], o[j] = x)
        ;
    return o;
}
shuffle(cards);

for (var i = 0; i < cards.length; i++) {
    card = document.createElement('div');
    card.dataset.item = cards[i];
    card.dataset.view = "card";
    card.style.backgroundImage = "url('images/backside.png')";
    myCards.appendChild(card);

    card.onclick = function () {
        this.style.backgroundImage = "url('images/deck/" + this.dataset.item + ".png')";
        if (this.className != 'flipped' && this.className != 'correct') {
            this.className = 'flipped';
            var result = this.dataset.item;
            resultsArray.push(result);
            clearInterval(Interval);
            Interval = setInterval(startTimer, 10);
        }

        if (resultsArray.length > 1) {

            if (resultsArray[0] === resultsArray[1]) {
                check("correct");
                counter++;
                win();
                resultsArray = [];
            } else {
                check("reverse");
                resultsArray = [];
            }

        }

    }

}
;


var check = function (className) {
    var x = document.getElementsByClassName("flipped");
    setTimeout(function () {
        for (var i = (x.length - 1); i >= 0; i--) {
            if (className == "reverse")
                x[i].style.backgroundImage = "url('images/backside.png')";
            x[i].className = className;
        }

    }, 500);

}

var win = function () {

    if (counter === 5) {
        clearInterval(Interval);
        $.ajax({
            url: "/gameCard/BetServlet",
            method: "POST",
            data: {
                action: "minigame",
                whoWon: "Player"
            },
            dataType: "text",
            success: function () {
                let sHtml = `
                <p style="color: yellow; font-size: 2em">you have received 50 coins</p>
                <button class="startover" style="display: inline-block;" onclick="document.location.reload(true)">Restart Game</button>`;
                $("#result").html(sHtml);
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
    }

}

function startTimer() {
    tens++;

    if (tens < 9) {
        appendTens.innerHTML = "0" + tens;
    }

    if (tens > 9) {
        appendTens.innerHTML = tens;

    }

    if (tens > 99) {
        seconds++;
        appendSeconds.innerHTML = "0" + seconds;
        tens = 0;
        appendTens.innerHTML = "0" + 0;
    }

    if (seconds > 9) {
        appendSeconds.innerHTML = seconds;
    }

}

$(document).ready(function () {
    $.ajax({
        url: "/gameCard/MainServlet",
        method: "POST",
        data: {
            action: "uifInGame"
        },
        dataType: "json",
        success: function (aData) {
            let sHtml = `
                <h2>` + aData.name + `</h2>
                <p>
                    <b>Username: </b>` + aData.username + `<br>
                    <b>Coins: </b>` + aData.coin + `<br>
                    <b>Lv: </b>` + aData.lv + `<br>
                    <b>Exp: </b>` + aData.exp + `<br>
                </p>`;
            iUserCoin = aData.coin;
            $("#info").html(sHtml);
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