import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HearthstoneComponent } from './component/hearthstone/hearthstone.component';


const routes: Routes = [
  { path: "hearthstone", component: HearthstoneComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
