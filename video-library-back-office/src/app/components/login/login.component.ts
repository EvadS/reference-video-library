import { Component, OnInit } from '@angular/core';
import { AuthService} from "../../services/auth.service";
import {TokenStorageService} from "../../services/token-storage.service";
import {Router} from '@angular/router';
import {LoginRequest, RegisterRequest} from "../../models/RegisterRequest";
import { NgForm} from '@angular/forms';

import {LogService} from "../../services/log.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginRequest: LoginRequest = new LoginRequest();

  isSuccessful = false;
  isSignUpFailed = false;

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private logService: LogService,
              private router: Router) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorage.getToken();

    if (this.isLoggedIn) {
      this.goHome();
    }
  }

  reloadPage(): void {
    window.location.reload();
  }

  goHome(){
    this.router.navigate(['']);
  }

  onSubmit(form: NgForm) {
    this.authService.login(form.value.email,
       form.value.password).subscribe(
      data => {

        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.reloadPage();
      },
      err => {
        this.errorMessage = err.error.message;
        console.log("here----------------")
        if(err.error.status== 401){
          this.errorMessage = "invalid user name or password"
        }
        this.isLoginFailed = true;
      }
    );
   }
}
