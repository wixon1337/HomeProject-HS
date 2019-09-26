import { Component, OnInit } from '@angular/core';
import { HearthstoneService } from 'src/app/service/hearthstone.service';
import * as Stomp from 'stompjs';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-hearthstone-player2',
  templateUrl: './hearthstone-player2.component.html',
  styleUrls: ['./hearthstone-player2.component.css']
})
export class HearthstonePlayer2Component implements OnInit {

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

  constructor(private hearthstoneService: HearthstoneService, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    let tempUserNameObject;
    this.socketUrl = this.activatedRoute.snapshot.params.socketUrl;
    this.opponentUserId = this.activatedRoute.snapshot.params.userId;
    this.hearthstoneService.getUserName(this.opponentUserId).subscribe(data => {
      tempUserNameObject = data;
    }, error => {
      console.error(error);
    }, () => {
      this.opponentUserName = tempUserNameObject.userName;
      console.log('Opp User Name: ' + this.opponentUserName);
    });
  }

  connect() {
    const socket = new WebSocket('ws://localhost:8081/greeting');
    this.ws = Stomp.over(socket);
    const that = this;
    this.ws.connect({}, frame => {
      that.ws.subscribe('/errors', message => {
        alert('Error ' + message.body);
      });
      that.ws.subscribe('/topic/reply/' + this.socketUrl, message => {
        if (message.body === 'start') {
          this.ourTurn = true;
        } else {
          const stringInfo = JSON.parse(message.body);
          if (stringInfo.turnBy == 'p2') {
            this.ourTurn = false; // TODO
          } else if (stringInfo.turnBy == 'p1') {
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
      this.sendName('start');
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
    const data = JSON.stringify({
      name: value
    });
    this.ws.send('/app/message/' + this.socketUrl, {}, data);
    console.log('SEND-SEND ' + value);
  }


  overlayOn() {
    document.getElementById('overlay').style.display = 'block';
  }

  overlayOff() {
    document.getElementById('overlay').style.display = 'none';
  }

}
