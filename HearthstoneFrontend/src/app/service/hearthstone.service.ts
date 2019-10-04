import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Card } from '../model/card';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HearthstoneService {

  constructor(private http: HttpClient) { }

  getBoard(username) {
    return this.http.get('http://localhost:8081/api/newgame/' + username);
  }

  getBoardP2(username) {
    return this.http.get('http://localhost:8081/api/newgamep2/' + username);
  }

  getClass(heroSelected, username) {
    return this.http.get('http://localhost:8081/api/getHero/' + username + '/' + heroSelected);
  }

  getClassP2(heroSelected, username) {
    return this.http.get('http://localhost:8081/api/getHeroP2/' + username + '/' + heroSelected);
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

  updateBoard(username) {
    return this.http.get('http://localhost:8081/api/update/' + username);
  }

  convertBoardsideArray(array) {
    for (let i = 0; i < 5; i++) {
      if (array[i] === null) {
        array[i] = null;
      } else {
        array[i] = new Card(array[i].id, array[i].name, array[i].attack, array[i].health, array[i].taunt, array[i].charge, array[i].summoned, array[i].cost);
      }
    }
    return array;
  }

  convertArrayToCardArray(array) {
    array.forEach(element => {
      element = new Card(element.id, element.name, element.attack, element.health, element.taunt, element.charge, element.summoned, element.cost);
    });
    return array;
  }

  p2endTurn() {

  }
}
