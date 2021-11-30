import { Component, OnInit } from '@angular/core';

import {ActivatedRoute, Router} from "@angular/router";

import {FilmItem} from "../../../models/Films";
import  {FilmsService} from "../../../services/films.service";
import {HttpEventType, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";

@Component({
  selector: 'app-film-details',
  templateUrl: './film-details.component.html',
  styleUrls: ['./film-details.component.css']
})
export class FilmDetailsComponent implements OnInit {


  currentTutorial: FilmItem = {
    name: '',
    enabled: false
  };
  message = '';

  id: number | undefined;

  constructor( private tutorialService: FilmsService,
               private activateRoute: ActivatedRoute,
               private  router:Router){ }


  selectedFiles?: FileList;
  currentFile?: File;
  progress = 0;


  fileInfos?: Observable<any>;

  ngOnInit(): void {
    this.message = '';

    // this.route.params.subscribe(params=>this.id=params['id'])
    // this.getTutorial(this.route.snapshot.params.id);

    console.log("get all by: " + this.activateRoute.snapshot.params['id']);

    let id = this.activateRoute.snapshot.params['id'];
    if(id==null){
      this.router.navigate(['/countries']);
    }

    this.getTutorial(id);
  }

  getTutorial(id: string): void {
    console.log("get all by: "+ id);
    this.tutorialService.get(id)
      .subscribe(
        data => {
          this.currentTutorial = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  updatePublished(status: boolean): void {
    const data = {
      title: this.currentTutorial.name,
      enabled: status
    };

    this.message = '';

    this.tutorialService.update(this.currentTutorial.id, data)
      .subscribe(
        response => {
          this.currentTutorial.enabled = status;
          console.log(response);
          this.message = response.message ? response.message : 'The status was updated successfully!';
        },
        error => {
          console.log(error);
        });
  }

  updateTutorial(): void {
    this.message = '';

    this.tutorialService.update(this.currentTutorial.id, this.currentTutorial)
      .subscribe(
        response => {
          console.log(response);
          this.message = response.message ? response.message : 'This tutorial was updated successfully!';
        },
        error => {
          console.log(error);
        });
  }

  deleteTutorial(): void {
    this.tutorialService.delete(this.currentTutorial.id)
      .subscribe(
        response => {
          console.log(response);
          this.router.navigate(['/countries']);
        },
        error => {
          console.log(error);
        });
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  upload(): void {
    console.log("here")
    this.progress = 0;

    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);

      if (file) {
        this.currentFile = file;

        this.tutorialService.upload(this.currentFile).subscribe(
          (event: any) => {
            if (event.type === HttpEventType.UploadProgress) {
              this.progress = Math.round(100 * event.loaded / event.total);
            } else if (event instanceof HttpResponse) {
              this.message = event.body.message;
              this.fileInfos = this.tutorialService.getFiles();
            }
          },
          (err: any) => {
            console.log(err);
            this.progress = 0;

            if (err.error && err.error.message) {
              this.message = err.error.message;
            } else {
              this.message = 'Could not upload the file!';
            }

            this.currentFile = undefined;
          });

      }

      this.selectedFiles = undefined;
    }
  }
}
