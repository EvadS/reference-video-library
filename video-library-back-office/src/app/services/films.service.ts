import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpHeaders, HttpRequest} from '@angular/common/http';
import { Observable, of } from 'rxjs';
import {Country} from "../models/Countryl";
import {LogService} from "./log.service";

import {FilmItem} from "../models/Films";
// TODO: BASE URL
const baseUrl = 'http://localhost:8090/film';

const  httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class FilmsService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient,
              private logger: LogService) { }


  /**
   * mocked data
   */
  // getAll(){
  //   return this.http.get('assets/mocked/films/films-list.json')
  // }


  /**
   * actual data
   */
  getAll(): Observable<Country[]> {
    this.logger.log("get all");
    return this.http.get<FilmItem[]>(baseUrl+"/list" );
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  findByTitle(title: any): Observable<FilmItem[]> {
    return this.http.get<FilmItem[]>(`${baseUrl}?title=${title}`);
  }

  get(id: any): Observable<FilmItem> {
    return this.http.get(baseUrl+ "/" + id);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  upload(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);

    const req = new HttpRequest('POST', `${baseUrl}/upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }

  getFiles(): Observable<any> {
    return this.http.get(`${this.baseUrl}/files`);
  }
}
