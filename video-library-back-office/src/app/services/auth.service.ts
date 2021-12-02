import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

//http://localhost:8090/api/v1/auth/signin 4


// TODO: BASE URL
const AUTH_API = 'http://localhost:8090/api/v1/auth';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};


@Injectable({
  providedIn: 'root'
})

export class AuthService {
  constructor(private http: HttpClient) {
  }

  login(email: string, password: string): Observable<any> {

    return this.http.post(AUTH_API + '/signin', {
      email,
      password
    }, httpOptions);
  }

  register(username: string, email: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + '/signup', {
      username,
      email,
      password
    }, httpOptions);
  }

}
