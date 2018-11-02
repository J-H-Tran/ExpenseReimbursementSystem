import { Component, OnInit } from '@angular/core';
import { Hero } from '../model/hero';
import { HeroService } from '../hero.service';

//this metadata is how angular knows about the components
@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit{

  heroes: Hero[];
  selectedHero: Hero;

  //is how dependency injector gets to component
  constructor(private heroService: HeroService) { };

  ngOnInit() {
    this.getHeroes();
  }

  getHeroes(): void { //notify to get data
    // this.heroes = this.heroService.getHeroes();
    //for observables
    this.heroService.getHeroes().subscribe(heroes => this.heroes = heroes);
  }

  onSelect(hero) {
    if (this.selectedHero == hero){
      this.selectedHero = null;
    }
    else this.selectedHero = hero;
  }
}
