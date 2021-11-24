import { Component, OnInit } from '@angular/core';
import {FilmsService} from "../../services/films.service";
import {FilmItemResponse} from "../../models/FilmItemResponse";


@Component({
  selector: 'app-films',
  templateUrl: './films.component.html',
  styleUrls: ['./films.component.css']
})
export class FilmsComponent implements OnInit {


  filmItems: FilmItemResponse[]=[];
  constructor(
    private filmService: FilmsService
  ) { }

  ngOnInit(): void {
    this.filmService.getAll().subscribe((data: any) => this.filmItems=data);
  }
}
