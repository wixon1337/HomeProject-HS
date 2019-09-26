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

@NgModule({
  declarations: [
    AppComponent,
    HearthstoneComponent,
    HearthstonePlayer2Component
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule
  ],
  providers: [
    GreetingsService,
    HearthstoneService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
