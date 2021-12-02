import {Component, Input, OnInit} from '@angular/core';
import {CountryService} from "../../../services/country.service";
import {ActivatedRoute, Router} from '@angular/router';
import {Country} from "../../../models/Countryl";
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-country-details',
  templateUrl: './country-details.component.html',
  styleUrls: ['./country-details.component.css']
})
export class CountryDetailsComponent implements OnInit {

  @Input() currentTutorial: Country = {
    name: '',
    enabled: false
  };
  message = '';

  id: number | undefined;

  constructor( private tutorialService: CountryService,
               private activateRoute: ActivatedRoute,
               private  router:Router){ }

  ngOnInit(): void {
    this.message = '';


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

}
