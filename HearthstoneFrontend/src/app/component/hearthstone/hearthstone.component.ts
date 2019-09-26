import { Component, OnInit } from '@angular/core';
import { HearthstoneService } from 'src/app/service/hearthstone.service';
import * as Stomp from 'stompjs';


@Component({
  selector: 'app-hearthstone',
  templateUrl: './hearthstone.component.html',
  styleUrls: ['./hearthstone.component.css']
})
export class HearthstoneComponent implements OnInit {

  ws;
  socketUrl;
  userName: string;
  userId: string;
  opponentUserId;
  opponentUserName;
  playGameUrl;
  ourTurn: boolean;
  gameover: boolean;
  currentMessage: String;
  newData;
  board;
  player1Boardside: [];
  player2Boardside: [];
  player1Deck = ["", "", "", "", ""];

  constructor(private hearthstoneService: HearthstoneService) { }

  ngOnInit() {
  }

  start() {
    let username = this.userName;
    console.log(username);
    this.hearthstoneService.getBoard(username).subscribe(data => {
      this.newData = data;
    },
      err => {
        console.log(err);
      },
      () => {
        // console.log((this.newData));
        this.userId = this.newData.userId;
        this.socketUrl = this.newData.socketUrl;
        this.playGameUrl = '/playWithFriend/' + this.userName + '/' + this.socketUrl;
        this.board = this.newData.board;
        this.player1Deck = this.newData.board.player1Deck;
        for (let i = 0; i < 5; i++) {
          if (this.player1Deck[i] === undefined) this.player1Deck[i] = "";
        }
        console.log(this.player1Deck);
      }
    )
  }

  sendMessage() {
    this.ws.send('/app/message/' + this.socketUrl, {}, { name: "hello" });
  }

  connect() {
    const socket = new WebSocket('ws://localhost:8081/greeting');
    this.ws = Stomp.over(socket);
    this.ws.connect({}, () => {
      this.ws.subscribe('/topic/reply/' + this.socketUrl, message => {
        console.log("eztet kaptam tetya: ");
        console.log(message);
      })
    });
  }

  connect2() {
    const socket = new WebSocket('ws://localhost:8081/greeting');
    this.ws = Stomp.over(socket);
    const that = this;
    this.ws.connect({}, frame => {
      that.ws.subscribe('/errors', message => {
        alert('Error ' + message.body);
      });
      that.ws.subscribe('/topic/reply/' + this.socketUrl, message => {
        let tempData;
        let tempUserNameObject;
        if (message.body === 'start') {
          setTimeout(() => {
            this.hearthstoneService.getPlayer2Id(this.socketUrl).subscribe(
              data => {
                tempData = data;
                this.opponentUserId = tempData.userId;
              }, error1 => {
                console.log(error1);
              }, () => {
                this.hearthstoneService.getUserName(this.opponentUserId).subscribe(data => {
                  tempUserNameObject = data;
                }, error => {
                  console.error(error);
                }, () => {
                  this.opponentUserName = tempUserNameObject.userName;
                });
              }
            )
          }, 1500);
        } else {
          const stringInfo = JSON.parse(message.body);
          if (stringInfo.turnBy == 'p1') {
            this.ourTurn = false; // TODO
          } else if (stringInfo.turnBy == 'p2') {
            this.ourTurn = true; // TODO
          }
          if (stringInfo.winningMove == 'true') {
            this.gameover = true;
          }
          if (stringInfo.turnBy == 'p1') {
            this.currentMessage = 'Congratulations, You Won! Refresh to play again.';
          } else {
            this.currentMessage = 'Bad Luck! ' + this.opponentUserName + ' won! Refresh to play again.';
          }
        }
      });
    }, error => {
      // this.overlayOn();
    })
  }

  disconnect() {
    if (this.ws != null) {
      this.ws.ws.close();
    }
    console.log('Disconnected');
  }

  sendName(value) {
    /*     const data = JSON.stringify({
          name: value
        }); */
    // this.ws.send('/app/message/' + this.socketUrl, {}, data);
    this.ws.send('/app/message/' + this.socketUrl, {}, { name: value });
  }


  overlayOn() {
    document.getElementById('overlay').style.display = 'block';
  }

  overlayOff() {
    document.getElementById('overlay').style.display = 'none';
  }
}
