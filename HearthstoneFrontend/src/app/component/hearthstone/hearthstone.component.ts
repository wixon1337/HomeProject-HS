import { Component, OnInit } from '@angular/core';
import { HearthstoneService } from 'src/app/service/hearthstone.service';
import * as Stomp from 'stompjs';
import { Card } from 'src/app/model/card';
import { Hero } from 'src/app/model/hero';
import { Warlock } from 'src/app/model/Warlock';


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
  player1Boardside;
  player1Deck;
  player1Hand;
  player2Boardside;
  player2Deck;
  player2Hand;
  usernameGiven;
  mana;
  selected = [null, null];
  heroSelected: string;
  hero;
  newData2;
  opponentHeroName;
  opponentHeroHealth;

  constructor(private hearthstoneService: HearthstoneService) { }

  ngOnInit() {
  }

  start() {
    if (this.userName !== null && this.userName !== "") {
      this.usernameGiven = true;
      let username = this.userName;
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
          console.log(this.socketUrl);
          this.playGameUrl = 'http://localhost:4200' + '/playWithFriend/' + this.userName + '/' + this.socketUrl;

          this.board = this.newData.board;
          console.log(this.board);
          this.player1Boardside = this.hearthstoneService.convertBoardsideArray(this.board.player1Boardside);

          this.player2Boardside = this.hearthstoneService.convertBoardsideArray(this.board.player2Boardside);
          //this.player2Boardside[2] = new Card("azaz", 3, 2, false, false, false);

          this.player1Hand = this.hearthstoneService.convertArrayToCardArray(this.board.player1Hand);

          /*         this.player1Hand.push(new Card("da", 1, 1, false, false, false));
                  this.player1Hand.push(new Card("da", 1, 1, false, false, false));
                  this.player1Hand.push(new Card("da", 1, 1, false, false, false)); */

          this.player2Hand = this.hearthstoneService.convertArrayToCardArray(this.board.player2Hand);

          /*         this.player2Hand.push(new Card("nemanyád", 2, 3, false, false, false));
                  this.player2Hand.push(new Card("nemanyád", 2, 3, false, false, false));
                  this.player2Hand.push(new Card("nemanyád", 2, 3, false, false, false)); */

          this.player1Deck = this.board.player1Deck;
          this.player2Deck = this.board.player2Deck;
          this.mana = this.board.maxMana;
          this.ourTurn = true;

        }
      )
      setTimeout(() => { this.connect() }, 3000)
      setTimeout(() => {
        this.hearthstoneService.getClass(this.heroSelected, username).subscribe(data => {
          console.log("eztet kaptam hero gyanánt: ")
          console.log(data);
          this.newData2 = data;
        }, err => {
          console.log(err);
        }, () => {
          this.hero = new Warlock(this.newData2.abilityCost, this.newData2.health, this.newData2.name, this.newData2.abilityDamage);
        })
      }, 4500)
    }
  }



  sendMessage() {
    this.ws.send('/app/message/' + this.socketUrl, {}, { name: "hello" });
  }

  endTurn() {
    this.ws.send('/app/message/' + this.socketUrl, {}, JSON.stringify({ type: "endTurn", username: this.userName }));
    console.log(this.ourTurn);
    this.ourTurn = !this.ourTurn;
  }

  summonMinion(cardId) {
    this.ws.send('/app/message/' + this.socketUrl, {}, JSON.stringify({ type: "summon", username: this.userName, cardId: cardId }));
  }

  attack() {
    if (this.selected[1] === "hero") {
      this.ws.send('/app/message/' + this.socketUrl, {}, JSON.stringify({ type: "attackHero", username: this.userName, target: "player2Hero", selected: this.selected[0] }));
    } else {
      this.ws.send('/app/message/' + this.socketUrl, {}, JSON.stringify({ type: "attack", username: this.userName, selected: this.selected[0], target: this.selected[1] }));
    }
  }

  selectHero() {
    this.selected[1] = "hero";
    console.log(this.selected);
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

  attackHero(cardId) {
    this.ws.send('/app/message/' + this.socketUrl, {}, JSON.stringify({ type: "attackHero", username: this.userName, cardId: cardId }));
  }

  lifeTap() {
    this.ws.send('/app/message/' + this.socketUrl, {}, JSON.stringify({ type: "lifeTap", username: this.userName }));
  }

  connect() {
    const socket = new WebSocket('ws://localhost:8081/greeting');
    this.ws = Stomp.over(socket);
    this.ws.connect({}, () => {
      this.ws.subscribe('/topic/reply/' + this.socketUrl, message => {
        console.log("eztet kaptam tetya: ");
        console.log(message.body);
        if (message.body === "update") {
          console.log(this.userName);
          this.hearthstoneService.updateBoard(this.userName).subscribe(data => {
            this.newData = data;
          }, err => {
            console.log(err);
          },
            () => {
              console.log(this.newData);
              this.player1Boardside = this.hearthstoneService.convertBoardsideArray(this.newData.player1Boardside);
              this.player2Boardside = this.hearthstoneService.convertBoardsideArray(this.newData.player2Boardside);
              this.player1Hand = this.hearthstoneService.convertArrayToCardArray(this.newData.player1Hand);
              this.player2Hand = this.hearthstoneService.convertArrayToCardArray(this.newData.player2Hand);
              this.player1Deck = this.newData.player1Deck;
              this.player2Deck = this.newData.player2Deck;
              this.ourTurn = this.newData.p1Turn;
              this.mana = this.newData.player1Mana;
              this.hero.health = this.newData.player1Hero.health;
              this.opponentHeroName = this.newData.player2Hero.name;
              this.opponentHeroHealth = this.newData.player2Hero.health;
            })
        } else {
          /*           let s = message.body;
                    let result = s.split(":");
                    if (result[0] === "alert") {
          
                    } */
          if (this.ourTurn) {
            alert(message.body);
          }
        }
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
    this.ws.send('/app/message/' + this.socketUrl, {}, value);
  }


  overlayOn() {
    document.getElementById('overlay').style.display = 'block';
  }

  overlayOff() {
    document.getElementById('overlay').style.display = 'none';
  }

  copyMessage(val: string) {
    let selBox = document.createElement('textarea');
    selBox.style.position = 'fixed';
    selBox.style.left = '0';
    selBox.style.top = '0';
    selBox.style.opacity = '0';
    selBox.value = val;
    document.body.appendChild(selBox);
    selBox.focus();
    selBox.select();
    document.execCommand('copy');
    document.body.removeChild(selBox);
  }
}
