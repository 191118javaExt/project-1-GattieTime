import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { User } from "src/app/models/user";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string = '';
  password: string = '';

  constructor(private us: UserService, private router: Router) { }

  ngOnInit() {
  }

  sendLogin() {this.us.login(this.username, this.password).subscribe(
      (response:User) => {
        sessionStorage.setItem('currentUser', JSON.stringify(response));
        this.router.navigate(['/home']);
      }
    )

  }

}
