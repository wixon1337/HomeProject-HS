import { Component, OnInit } from '@angular/core';
import { HearthstoneService } from 'src/app/service/hearthstone.service';
import * as Stomp from 'stompjs';
import { ActivatedRoute } from '@angular/router';
import { Card } from 'src/app/model/card';

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
  newData;
  board;
  player1Boardside;
  player1Deck;
  player1Hand;
  player2Boardside;
  player2Deck;
  player2Hand;
  usernameGiven = false;
  mana;
  selected = [null, null];

  constructor(private hearthstoneService: HearthstoneService, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.ourTurn = false;
    /*     this.hearthstoneService.currentMessage.subscribe(message => {
          this.message = message;
          if (message === "endedTurn") {
            this.ourTurn = true;
          }
        }) */
  }

  connect() {
    const socket = new WebSocket('ws://localhost:8081/greeting');
    this.ws = Stomp.over(socket);
    this.ws.connect({}, () => {
      this.ws.subscribe('/topic/reply/' + this.socketUrl, message => {
        console.log("eztet kaptam tetya: ");
        console.log(message.body);
        if (message.body === "update") {
          this.hearthstoneService.updateBoard(this.opponentUserName).subscribe(data => {
            this.newData = data;
          }, err => {
            console.log(err);
          },
            () => {
              console.log(this.newData);
              this.player2Boardside = this.hearthstoneService.convertBoardsideArray(this.newData.player1Boardside);
              this.player1Boardside = this.hearthstoneService.convertBoardsideArray(this.newData.player2Boardside);
              this.player2Hand = this.hearthstoneService.convertArrayToCardArray(this.newData.player1Hand);
              this.player1Hand = this.hearthstoneService.convertArrayToCardArray(this.newData.player2Hand);
              this.player2Deck = this.newData.player1Deck;
              this.player1Deck = this.newData.player2Deck;
              this.ourTurn = !this.newData.p1Turn;
              this.mana = this.newData.player2Mana;
            })
        }
      })
    });
  }

  start() {
    if (this.userName !== null && this.userName !== "") {
      this.usernameGiven = true;
      this.initPlayer2();
    }
  }

  initPlayer2() {
    this.socketUrl = this.activatedRoute.snapshot.params.socketUrl;
    this.opponentUserName = this.activatedRoute.snapshot.params.userName;
    console.log(this.activatedRoute.snapshot.params);

    this.hearthstoneService.getBoard(this.opponentUserName).subscribe(data => {
      this.newData = data;
    },
      err => {
        console.log(err);
      },
      () => {
        // console.log((this.newData));
        this.opponentUserId = this.newData.userId;

        this.board = this.newData.board;
        console.log(this.board);
        this.player2Boardside = this.hearthstoneService.convertBoardsideArray(this.board.player1Boardside);

        this.player1Boardside = this.hearthstoneService.convertBoardsideArray(this.board.player2Boardside);
        // this.player1Boardside[2] = new Card("azaz", 3, 2, false, false, false);

        this.player2Hand = this.hearthstoneService.convertArrayToCardArray(this.board.player1Hand);

        /*         this.player2Hand.push(new Card("da", 1, 1, false, false, false));
                this.player2Hand.push(new Card("da", 1, 1, false, false, false));
                this.player2Hand.push(new Card("da", 1, 1, false, false, false)); */

        this.player1Hand = this.hearthstoneService.convertArrayToCardArray(this.board.player2Hand);

        /*         this.player1Hand.push(new Card("nemanyád", 2, 3, false, false, false));
                this.player1Hand.push(new Card("nemanyád", 2, 3, false, false, false));
                this.player1Hand.push(new Card("nemanyád", 2, 3, false, false, false)); */

        this.player2Deck = this.board.player1Deck;
        this.player1Deck = this.board.player2Deck;

      }
    )
    /*     let tempUserNameObject;
        this.socketUrl = this.activatedRoute.snapshot.params.socketUrl;
        console.log(this.activatedRoute.snapshot.params);
        this.opponentUserId = this.activatedRoute.snapshot.params.userId;
        console.log(this.opponentUserId);
        this.hearthstoneService.getUserName(this.opponentUserId).subscribe(data => {
          tempUserNameObject = data;
        }, error => {
          console.error(error);
        }, () => {
          this.opponentUserName = tempUserNameObject.userName;
          console.log('Opp User Name: ' + this.opponentUserName);
        }); */
    this.connect();
  }

  endTurn() {
    this.ws.send('/app/message/' + this.socketUrl, {}, JSON.stringify({ type: "endTurnp2", username: this.opponentUserName }));
    console.log(this.ourTurn);
    this.ourTurn = !this.ourTurn
  }

  summonMinion(cardId) {
    this.ws.send('/app/message/' + this.socketUrl, {}, JSON.stringify({ type: "summonp2", username: this.opponentUserName, cardId: cardId }))
  }

  attack() {
    this.ws.send('/app/message/' + this.socketUrl, {}, JSON.stringify({ type: "attack", username: this.opponentUserName, selected: this.selected[0], target: this.selected[1] }));
  }

  select(card) {
    // console.log(card);
    this.selected[0] = card.id;
    console.log(this.selected);
  }

  selectTarget(card) {
    this.selected[1] = card.id;
    console.log(this.selected);
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
        console.log(message);
        if (message.body === 'start') {
          this.ourTurn = true;
        } else {
          const stringInfo = (message.body);
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
