import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { ReceiptComponent } from './components/receipt/receipt.component';


const routes: Routes = [
  {
    path:'login',
    component:LoginComponent
  },
  {
    path:'receipt',
    component: ReceiptComponent
  },
  {
    path:'home',
    component: HomeComponent
  },
  {
    path:"register",
    component:RegisterComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
