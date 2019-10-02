import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Card } from 'src/app/model/card';
import { HearthstoneService } from 'src/app/service/hearthstone.service';

@Component({
  selector: 'app-card-in-hand',
  templateUrl: './card-in-hand.component.html',
  styleUrls: ['./card-in-hand.component.css']
})
export class CardInHandComponent implements OnInit {

  @Input() card: Card;
  @Output() summonEvent = new EventEmitter<number>();

  constructor(private hearthstoneService: HearthstoneService) { }

  ngOnInit() {
  }

  /*   summon() {
      console.log(this.card);
      this.hearthstoneService.summon(this.card.id);
    } */

  summon() {
    this.summonEvent.next(this.card.id);
  }
}
