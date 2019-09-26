import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HearthstoneService {

  constructor(private http: HttpClient) { }

  getBoard(username) {
    return this.http.get('http://localhost:8081/api/newgame/' + username);
  }

  getPlayer2Id(scoketUrl) {
    return scoketUrl; // TODO
  }

  getUserName(userId) {
    return userId; // TODO
  }

  playWithFriend(username, socketUrl) {
    return this.http.get('/server/playWithFriend/' + username + '/' + socketUrl);
  }
}
