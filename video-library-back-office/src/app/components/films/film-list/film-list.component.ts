import { Component, OnInit } from '@angular/core';
import  {FilmsService} from "../../../services/films.service";
import {FilmItem} from "../../../models/Films";

@Component({
  selector: 'app-film-list',
  templateUrl: './film-list.component.html',
  styleUrls: ['./film-list.component.css']
})
export class FilmListComponent implements OnInit {
  title = '';
  currentFilm: FilmItem = {
  }

  films?: FilmItem[];
  currentIndex: any;
  message = '';


  constructor(
    private filmService: FilmsService
  ) { }

  ngOnInit(): void {
    this.message = '';
    this.retrieveTutorials()
  }

  retrieveTutorials(): void {
    this.filmService.getAll()
      .subscribe(
        data => {
          this.films = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }
  refreshList(): void {
    this.retrieveTutorials();
    this.currentFilm = {};
    this.currentIndex = -1;
  }


  setActive(film: FilmItem, index: number): void {
    this.currentFilm = film;
    this.currentIndex = index;
  }



  searchTitle(): void {
    this.currentFilm = {};
    this.currentIndex = -1;

    this.filmService.findByTitle(this.title)
      .subscribe(
        data => {
          this.films = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  removeAll() {
    this.filmService.deleteAll()
      .subscribe(
        response => {
          console.log(response);
          this.refreshList();
        },
        error => {
          console.log(error);
        });
  }
}
