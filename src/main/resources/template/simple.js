'use strict';
 
 
var stompClient = null;
var username = null;
let game = null;
 
function connect() {
      
    var socket = new SockJS('http://localhost:8095/game');
    stompClient = Stomp.over(socket);
 
    stompClient.connect({}, onConnected, onError);
}
 
// Connect to WebSocket Server.
connect();
 
function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/bugsbutchery', onMessageReceived);
}
 
 
function onError(error) {
    $('#content').val('Could not connect to WebSocket server. Please refresh this page to try again!');
}
 
function newGame() {
    if(stompClient) {
        stompClient.send("/app/newGame")
    }
}

function sendMessage() {
    let username = $('#name').val().trim();
    let antBreed = $('#breed').val().trim()
    if(stompClient) {
        let player = {
            playerName: username,
            playerAntsBreed: antBreed,
        };
        stompClient.send("/app/newPlayer", {}, JSON.stringify(player));
        $('#name').val('');
        $('#breed').val('');
    }
}

function pickTerritory() {
    let territoryId = $('#territory').val()
    if(stompClient){
        let message = {
            territory1: territoryId,
        }
        stompClient.send("/app/pickTerritory", {}, JSON.stringify(message))
        $('#territory').val('')
    }
}

function onMessageReceived(payload) {
    game = JSON.parse(payload.body)
}