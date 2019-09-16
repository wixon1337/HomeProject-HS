import { Component } from '@angular/core';
import { GreetingsService } from './service/greetings.service';
import { WebSocketAPI } from './WebSocketAPI';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'HearthstoneFrontend';
  webSocketAPI: WebSocketAPI;
  name: string;
  greeting = "\"asdasdsad\"";

  constructor() {
  }

  ngOnInit() {
    // this.greetingsService.getGreetingsMessage().subscribe(message => this.title = message[0]);
    this.webSocketAPI = new WebSocketAPI(new AppComponent());
  }

  connect() {
    this.webSocketAPI._connect();
  }

  disconnect() {
    this.webSocketAPI._disconnect();
  }

  sendMessage() {
    this.webSocketAPI._send(this.name);
  }

  handleMessage(message) {
    console.log(message);
    this.greeting = message;
    console.log(this.greeting)
  }
}