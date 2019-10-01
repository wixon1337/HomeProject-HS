import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Card } from '../model/card';

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
    return this.http.get('http://localhost:8081/api/getUsername/' + userId);
  }

  playWithFriend(username, socketUrl) {
    return this.http.get('/server/playWithFriend/' + username + '/' + socketUrl);
  }

  convertBoardsideArray(array) {
    for (let i = 0; i < 5; i++) {
      if (array[i] === null) {
        array[i] = null;
      } else {
        array[i] = new Card(array[i].id, array[i].name, array[i].attack, array[i].health, array[i].taunt, array[i].charge, array[i].summoned);
      }
    }
    return array;
  }

  convertArrayToCardArray(array) {
    array.forEach(element => {
      element = new Card(element.id, element.name, element.attack, element.health, element.taunt, element.charge, element.summoned);
    });
    return array;
  }
}
