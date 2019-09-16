import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GreetingsService {

  constructor(private httpClient: HttpClient) { }

  /*   public getGreetingsMessage() {
      return this.httpClient.get("http://localhost:8081/api/hello")
    } */
}
