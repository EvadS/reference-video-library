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

    console.log(form.value.email);

    this.authService.login(form.value.email,
       form.value.password).subscribe(
      data => {

        console.log("--> response: " + data);
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.reloadPage();
      },
      err => {
        this.errorMessage = err.error.message;

        this.isLoginFailed = true;
      }
    );

   }

   displayError( error? :String) {


    // thisForm.querySelector('.loading').classList.remove('d-block');
    // thisForm.querySelector('.error-message').innerHTML = error;
    // thisForm.querySelector('.error-message').classList.add('d-block');
  }
}
