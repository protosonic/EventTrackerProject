import { Component, OnInit } from '@angular/core';
import { Bounty } from '../../models/bounty';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BountyService } from '../../services/bounty-service';

import { TargetService } from '../../services/target-service';
import { HunterService } from '../../services/hunter-service';
import { Hunter } from '../../models/hunter';
import { Target } from '../../models/target';

@Component({
  selector: 'app-home',
  imports: [CommonModule, FormsModule],
  templateUrl: './home.html',
  styleUrl: './home.css'
})

export class Home implements OnInit{
  title: string = 'Galactic Bounty Tracker'
  bounties: Bounty[] = [];
  targets: Target[] = [];
  hunters: Hunter[] = [];
  selectedBounty: Bounty | null = null;
  showBounty: boolean = false;
  showAllBounties: boolean = false;
  showTopBounties: boolean = true;
  showNewBountyForm: boolean = false;
  showMostWanted: boolean = false;
  newBounty: Bounty = new Bounty();
  editBounty: Bounty | null = null;
  topBounties: Bounty[] = [];
  sortKey: string = '';
  sortAsc: boolean = true;
  mostWanted: Target[] = [];

  constructor(
    private bountyService: BountyService,
    private targetService: TargetService,
    private hunterService: HunterService
  ) {}

  ngOnInit(): void {
    this.loadBounties();
    this.loadTargets();
    this.loadHunters();
  }

  loadBounties(): void {
    this.bountyService.index().subscribe({
      next: (bountyList) => {
        this.bounties = bountyList;
        this.setTopBounties();

      },
      error: (badnews) => {
        console.error("Home.loadBounties: error getting bounties");
        console.log(badnews);
      }
    });
  }

  loadTargets(): void {
  this.targetService.index().subscribe({
    next: (targetList) => {
        this.targets = targetList;
      },
      error: (badnews) => {
        console.error("Home.loadTargets: error getting targets");
        console.log(badnews);
    }
  });

  }
  loadHunters(): void {
    this.hunterService.index().subscribe({
      next: (hunterList) => {
          this.hunters = hunterList;
        },
        error: (badnews) => {
          console.error("Home.loadHunters: error getting hunters");
          console.log(badnews);
      }
    });
  }
  getBountyCount() {
    return this.bounties.length;
  }

  displayBountiesTable() {
    this.selectedBounty = null;
  }

  displayBounty(bounty: Bounty) {
    this.selectedBounty = bounty;
  }

  toggleShowAllBounties(value: boolean) {
    this.showAllBounties = value;
  }

  toggleShowTopBounties(value: boolean) {
    this.showTopBounties = value;
  }

  toggleShowNewBountyForm(value: boolean) {
    this.showNewBountyForm = value;
  }

  toggleSowMostWanted(value: boolean) {
    this.showMostWanted = value;
  }

  setTopBounties() {
  this.topBounties = [...this.bounties]
    .sort((a, b) => b.amount - a.amount)
    .slice(0, 5);
  }

  setEditBounty() {
    this.editBounty = Object.assign({}, this.selectedBounty)
  }

  cancelEditBounty() {
  this.editBounty = null;
  this.toggleShowNewBountyForm(false);
  }

  cancelNewBounty() {
    this.newBounty = new Bounty();
    this.toggleShowNewBountyForm(false);
  }

  createBounty(bounty: Bounty) {
  this.bountyService.create(bounty).subscribe({
    next: (newBounty) => {
      this.loadBounties();
      this.newBounty = new Bounty();
      this.showNewBountyForm = false;
    },
    error: (err) => {
      console.log(err);
      console.log('Home.ts Component: error loading bounties');
    }
  });
  }

  updateBounty(updatedBounty: Bounty) {
  this.bountyService.update(updatedBounty, updatedBounty.id).subscribe({
    next: (newTodo) => {
      this.loadBounties();
      this.selectedBounty = updatedBounty;
      this.editBounty = null;
    },
    error: (err) => {
      console.log(err);
      console.log('Home.ts Component: error updating Bounty with ID: ' + updatedBounty.id);
    }
  });
  }

  deleteBounty(bountyId: number) {
    this.bountyService.destroy(bountyId).subscribe({
      next: (todos) => {
        this.loadBounties();
      },
      error: (err) => {
        console.log(err);
        console.log('Home.ts Component: error deleting bounties');
      }
    });
    this.loadBounties();
  }

  sortBy(key: string) {
    if (this.sortKey === key) {
      this.sortAsc = !this.sortAsc; // toggle asc/desc
    } else {
      this.sortKey = key;
      this.sortAsc = true;
    }

    this.bounties.sort((a: any, b: any) => {
      const valA = this.getValueByKey(a, key);
      const valB = this.getValueByKey(b, key);

      if (valA < valB) {
        return this.sortAsc ? -1 : 1;
      }
      if (valA > valB)
        {return this.sortAsc ? 1 : -1;
        }
      return 0;
    });
  }

  getValueByKey(obj: any, key: string): any {
    if (key === 'name') return obj.target.name;
    return obj[key];
  }

  setMostWanted() {

  }

  confirmDeleteBounty(bounty: Bounty): void {
  const confirmed = confirm(`Are you sure you want to permanently delete the bounty on ${bounty.target.name}?`);
  if (confirmed) {
    this.deleteBounty(bounty.id);
  }
}
}
