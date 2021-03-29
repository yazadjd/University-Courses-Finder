import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {HomeComponent} from "./home/home.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'courseFinder-app';
}
