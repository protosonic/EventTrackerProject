import { Component, OnInit } from '@angular/core';
import { Bounty } from '../../models/bounty';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BountyService } from '../../services/bounty-service';
import { Target } from '@angular/compiler';

@Component({
  selector: 'app-home',
  imports: [CommonModule, FormsModule],
  templateUrl: './home.html',
  styleUrl: './home.css'
})

export class Home implements OnInit{
  title: string = 'Galactic Bounty Tracker'
  bounties: Bounty[] = [];
  selectedBounty: Bounty | null = null;
  showBounty: boolean = false;
  showAllBounties: boolean = false;
  showTopBounties: boolean = true;
  showNewBountyForm: boolean = false;
  newBounty: Bounty = new Bounty();
  editBounty: Bounty | null = null;
  topBounties: Bounty[] = [];

  constructor(
    private bountyService: BountyService
  ) {}

  ngOnInit(): void {
 this.loadBounties();
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

  getBountyCount() {
    return this.bounties.length;
  }

  displayBounty(bounty: Bounty) {
    this.selectedBounty = bounty;
  }

  displayBountiesTable() {
    this.selectedBounty = null;
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

  deleteTodo(bountyId: number) {
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
}
