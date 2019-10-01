import { Component, OnInit, Input } from '@angular/core';
import { Card } from 'src/app/model/card';

@Component({
  selector: 'app-card-in-hand',
  templateUrl: './card-in-hand.component.html',
  styleUrls: ['./card-in-hand.component.css']
})
export class CardInHandComponent implements OnInit {

  @Input() card: Card;

  constructor() { }

  ngOnInit() {
  }

  summon() {
    console.log(this.card);
  }
}
