$(document).ready(function () {
    let ws;
    let username = document.getElementById("username").value;
    ws = new WebSocket("ws://" + document.location.host + "/chat-websocket-application/chat/" + username);


    ws.onmessage = function (event) {
        let log = document.getElementById("log");
        console.log(event.data);
        let message = JSON.parse(event.data);
        log.innerHTML += message.from + " : " + message.content + "\n";
    };

    $("#sendMsg").click(function () {
        let content = document.getElementById("msg").value;
        let to = document.getElementById("to").value;
        let json = JSON.stringify({
            "to": to,
            "content": content
        });

        ws.send(json);
        log.innerHTML += "Me : " + content + "\n";
    });
});