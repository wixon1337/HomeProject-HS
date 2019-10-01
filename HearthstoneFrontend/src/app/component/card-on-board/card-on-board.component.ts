import { Component, OnInit, Input } from '@angular/core';
import { Card } from 'src/app/model/card';

@Component({
  selector: 'app-card-on-board',
  templateUrl: './card-on-board.component.html',
  styleUrls: ['./card-on-board.component.css']
})
export class CardOnBoardComponent implements OnInit {

  @Input() card: Card;

  constructor() { }

  ngOnInit() {
  }

  attack() {
    console.log(this.card);
  }

}
