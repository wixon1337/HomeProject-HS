import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HearthstoneComponent } from './component/hearthstone/hearthstone.component';
import { HearthstonePlayer2Component } from './component/hearthstone-player2/hearthstone-player2.component';


const routes: Routes = [
  { path: "hearthstone", component: HearthstoneComponent },
  { path: 'playWithFriend/:userId/:socketUrl', component: HearthstonePlayer2Component }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
