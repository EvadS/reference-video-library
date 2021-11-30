import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Country} from "../models/Countryl";
import {LogService} from "./log.service";

const baseUrl = 'http://localhost:8090/country';

@Injectable({
  providedIn: 'root'
})
export class CountryService {

  constructor(private http: HttpClient,
              private logger: LogService) { }

  getAll(): Observable<Country[]> {
    this.logger.log("get all");
    return this.http.get<Country[]>(baseUrl + "/list");
  }

  get(id: any): Observable<Country> {
    return this.http.get(baseUrl+ "/" + id);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  findByTitle(title: any): Observable<Country[]> {
    return this.http.get<Country[]>(`${baseUrl}?title=${title}`);
  }
}
