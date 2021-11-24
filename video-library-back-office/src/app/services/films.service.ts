import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';

// TODO: BASE URL
const AUTH_API = 'http://localhost:8090/api/film/';


const  httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class FilmsService {

  constructor(private http: HttpClient) { }

  /**
   * actual data
   */
  // getAll(email: string, password: string): Observable<any> {
  //   return this.http.get(AUTH_API + '/all', httpOptions);
  // }

  /**
   * mocked data
   */
  getAll(){
    return this.http.get('assets/mocked/films/films-list.json')
  }
}
