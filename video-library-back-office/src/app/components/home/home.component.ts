import { Component, OnInit } from '@angular/core';
import {LogService} from "../../services/log.service";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private logger: LogService) { }

  ngOnInit(): void {
    this.logger.log("Test the `log()` Method");
  }
}
