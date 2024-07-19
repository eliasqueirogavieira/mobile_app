import {Component} from '@angular/core';
import {MatCard, MatCardContent} from "@angular/material/card";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {CommonModule} from "@angular/common";
import {MusicDetailsDTO} from "../../dto/musicDetails.dto";


@Component({
  selector: 'app-card-datails',
  standalone: true,
    imports: [
      CommonModule,
      MatIconModule,
      MatButtonModule,
      MatCardContent,
      MatCard
    ],
  templateUrl: './card-datails.component.html',
  styleUrl: './card-datails.component.css'
})
export class CardDatailsComponent {

  songs: MusicDetailsDTO[] = [
    { title: 'Hotline Bling', artist: 'Drake', user: 'echianelli', score: 58 },
    { title: 'O alvo', artist: 'Diego e Victor Hugo', user: 'deboraskalat', score: 45 },
    { title: 'Corpo Sensual', artist: 'Pablo Vittar', user: 'lpabernaz', score: 36 },
    { title: 'Que tiro foi esse', artist: 'Jojo Maronttinni', user: 'arturomuro', score: 14 },
    { title: 'Casou Certinho', artist: 'Matheus e Kauan', user: 'dilan', score: 10 }
  ];

  increaseScore(index: number) {
    this.songs[index].score++;
    this.songs.sort((a, b) => b.score - a.score);
  }

}
