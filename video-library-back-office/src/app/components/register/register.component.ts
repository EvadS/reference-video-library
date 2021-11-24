import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {RegisterRequest} from "../../models/RegisterRequest";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerRequest: RegisterRequest = new RegisterRequest();
  errors?: Error;

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    console.log(":-->" + form.value.username);

    this.authService.register(form.value.username,
      form.value.email,
      form.value.password).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        this.errorMessage = err.error;
        this.isSignUpFailed = true;
        this.errorMessage =  err.error.detail;
        console.log(this.errorMessage);
      }
    );
    console.log(form);
  }
}
