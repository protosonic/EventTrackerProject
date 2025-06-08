// src/app/models/hunter.ts

import { Bounty } from './bounty';

export class Hunter {
  id: number;
  name: string;
  rank: string;
  reputation: number;
  imageUrl: string;
  bounties: Bounty[];

  constructor(
    id: number = 0,
    name: string = '',
    rank: string = '',
    reputation: number = 0,
    imageUrl: string = '',
    bounties: Bounty[] = []
  ) {
    this.id = id;
    this.name = name;
    this.rank = rank;
    this.reputation = reputation;
    this.imageUrl = imageUrl;
    this.bounties = bounties;
  }
}
