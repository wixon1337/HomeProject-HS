import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HearthstoneComponent } from './component/hearthstone/hearthstone.component';
import { HearthstonePlayer2Component } from './component/hearthstone-player2/hearthstone-player2.component';
import { HearthstonePlayer1Component } from './component/hearthstone-player1/hearthstone-player1.component';


const routes: Routes = [
  { path: '', component: HearthstoneComponent },
  { path: 'hearthstone', component: HearthstonePlayer1Component },
  { path: 'playWithFriend/:userName/:socketUrl', component: HearthstonePlayer2Component }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
