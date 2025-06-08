// src/app/models/target.ts

import { Bounty } from './bounty';

export class Target {
  id: number;
  name: string;
  species: string;
  wantedLevel: number | null;
  planetName: string;
  lastSeen: string;
  imageUrl: string;
  bountiesOnTarget: Bounty[];

  constructor(
    id: number = 0,
    name: string = '',
    species: string = '',
    wantedLevel: number | null = null,
    planetName: string = '',
    lastSeen: string = '',
    imageUrl: string = '',
    bountiesOnTarget: Bounty[] = []
  ) {
    this.id = id;
    this.name = name;
    this.species = species;
    this.wantedLevel = wantedLevel;
    this.planetName = planetName;
    this.lastSeen = lastSeen;
    this.imageUrl = imageUrl;
    this.bountiesOnTarget = bountiesOnTarget;
  }
}
