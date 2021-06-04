const socket = io();
//ELEMENTS
const messageForm = $("#message-form");
const message = $("#message");
const chat = $("#chat");
const users = $("#users");
const loginWindows = $("#loginWindows");
const chatWindows = $("#chatWindows");
const loginForm = $("#loginForm");
const username = $("#username");

function privChat(privUser) {
    if(username.val() != privUser){
        $("#"+privUser).text("0");
        $("#chatPrivadoChat").html("");
        $("#usernamePrivadoChat").text(privUser);
        $("#privadoChat").show();
        socket.emit("load private chat", privUser, msgs => {
            msgs.forEach(data => {
                $("#chatPrivadoChat").append('<p><span style="font-weight: bold">'+data.from+': </span>'+data.msg+'</p>');
            });
        });
    }
};

function cerrar(){
    $("#chatPrivadoChat").html("");
    $("#usernamePrivadoChat").text("");
    $("#privadoChat").hide();
};

$(function() { 

    $("#privadoMessage-form").submit(e => {
        e.preventDefault();
        var patt = new RegExp(/^[<>]+$/g);
        var res = patt.test($("#privMessage").val());
        if(!res){
            socket.emit("send priv message", $("#usernamePrivadoChat").text(), $("#privMessage").val());
            $("#chatPrivadoChat").append('<p><span style="font-weight: bold">'+username.val()+': </span>'+$("#privMessage").val()+'</p>');
            $("#privMessage").val("");
        }else{
            $("#privMessage").val("");
        }
    });

    messageForm.submit(e => {
        e.preventDefault();
        var patt = new RegExp(/^[<>]+$/g);
        var res = patt.test(message.val());
        if(!res){
            socket.emit("send message", message.val());
            message.val("");
        }else{
            message.val("");
        }
    });

    loginForm.submit(e => {
        e.preventDefault();

        socket.emit("new user", username.val(), isValid => {
            var patt = new RegExp(/^[<>]+$/g);;
            var res = patt.test(username.val());
            if(!res){
                if(isValid == 0){
                    loginWindows.hide();
                    chatWindows.show();
                }
                if(isValid == 1){
                    $("#alerta").html(`<div class="alert alert-danger">Nickname already Exists</div>`)
                }
                if(isValid == 2){
                    $("#alerta").html(`<div class="alert alert-danger">You are already chating</div>`)
                }
            }else{
                $("#alerta").html(`<div class="alert alert-danger">Caracteres invalidos</div>`)
            }
        });

        socket.emit("load chat", msgs => {
            msgs.forEach(data => {
                chat.append('<p><span style="font-weight: bold">'+data.user+': </span>'+data.msg+'</p>');
            });
        });
    });

    socket.on("new message", data => { 
        chat.append('<p><span style="font-weight: bold">'+data.user+': </span>'+data.msg+'</p>');
        var messageBody = document.querySelector('#chat');
        messageBody.scrollTop = messageBody.scrollHeight - messageBody.clientHeight;
    });

    socket.on("new priv message", data => { 
        if(data.from == $("#usernamePrivadoChat").text()){
            $("#chatPrivadoChat").append('<p><span style="font-weight: bold">'+data.from+': </span>'+data.msg+'</p>');
            var messageBody = document.querySelector('#chatPrivadoChat');
            messageBody.scrollTop = messageBody.scrollHeight - messageBody.clientHeight;
        }else{
            let x = $("#"+data.from).text();
            x = parseInt(x);
            x += 1;
            $("#"+data.from).text(x);
        }
    });

    socket.on("usernames", data =>{
        let html = "";
        for (let i = 0; i < data.length; i++) {
            html += '<button class="btn" onClick="privChat(\''+data[i]+'\')" ><i class="fas fa-user pr-1"></i>'+data[i]+'<span class="badge badge-success ml-1" id="'+data[i]+'">0</span></button>';
        }
        users.html(html);
    });

});