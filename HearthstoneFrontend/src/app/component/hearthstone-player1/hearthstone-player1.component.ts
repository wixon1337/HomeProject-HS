import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-hearthstone-player1',
  templateUrl: './hearthstone-player1.component.html',
  styleUrls: ['./hearthstone-player1.component.css']
})
export class HearthstonePlayer1Component implements OnInit {

  board;
  player1Boardside;
  player1Deck;
  player1Hand;
  player2Boardside;
  player2Deck;
  player2Hand;

  constructor() { }

  ngOnInit() {
  }

}
