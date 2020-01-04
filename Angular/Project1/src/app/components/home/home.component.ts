import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  currentUser: User;
  constructor(private us: UserService, private router: Router) { }

  

  ngOnInit() {
    let userString: string = sessionStorage.getItem('currentUser');
    if(!userString){
      this.router.navigate(['/login']);
    } else {
      this.currentUser = JSON.parse(userString);
    }
  }

}
