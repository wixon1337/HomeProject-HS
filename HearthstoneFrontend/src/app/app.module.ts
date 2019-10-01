import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { HearthstoneComponent } from './component/hearthstone/hearthstone.component';
import { GreetingsService } from './service/greetings.service';
import { FormsModule } from '@angular/forms'
import { HearthstoneService } from './service/hearthstone.service';
import { HearthstonePlayer2Component } from './component/hearthstone-player2/hearthstone-player2.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatNativeDateModule } from '@angular/material/core';
import { MatCardModule } from '@angular/material/card';
import { MaterialModule } from '../material.module';
import { CardOnBoardComponent } from './component/card-on-board/card-on-board.component';
import { CardInHandComponent } from './component/card-in-hand/card-in-hand.component';
import { HearthstonePlayer1Component } from './component/hearthstone-player1/hearthstone-player1.component'

@NgModule({
  declarations: [
    AppComponent,
    HearthstoneComponent,
    HearthstonePlayer2Component,
    CardOnBoardComponent,
    CardInHandComponent,
    HearthstonePlayer1Component
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatNativeDateModule,
    MatCardModule,
    MaterialModule
  ],
  providers: [
    GreetingsService,
    HearthstoneService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
