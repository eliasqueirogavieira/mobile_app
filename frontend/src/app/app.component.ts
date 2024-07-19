import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {MusicListComponent} from "./components/music-list/music-list.component";
import {MatButtonModule} from "@angular/material/button";
import {MatIcon, MatIconModule} from "@angular/material/icon";
import {MatSlideToggle, MatSlideToggleModule} from "@angular/material/slide-toggle";
import {BrowserModule} from "@angular/platform-browser";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [MatIconModule, MatButtonModule, MatSlideToggleModule, MusicListComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
