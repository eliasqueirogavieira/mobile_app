import {Component} from '@angular/core';
import {CardDatailsComponent} from "../card-datails/card-datails.component";

@Component({
  selector: 'app-music-list',
  standalone: true,
  imports: [
    CardDatailsComponent
  ],
  templateUrl: './music-list.component.html',
  styleUrl: './music-list.component.css'
})
export class MusicListComponent {

}
