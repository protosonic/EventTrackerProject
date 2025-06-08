import { Hunter } from "./hunter";
import { Target } from "./target";

export class Bounty {
  id: number;
  amount: number;
  status: boolean;
  description: string;
  dangerLevel: number;
  issueDate: string;
  claimDate: string;
  hunter: Hunter;
  target: Target;
  imageUrl: string;

  constructor(
  id: number = 0,
  amount: number = 0,
  status: boolean = true,
  description: string = '',
  dangerLevel: number = 0,
  issueDate: string = '',
  claimDate: string = '',
  hunter: Hunter = new Hunter(),
  target: Target = new Target(),
  imageUrl: string = '',
  ){
    this.id = id;
    this.amount = amount;
    this.status = status;
    this.description = description;
    this.dangerLevel = dangerLevel;
    this.issueDate = issueDate;
    this.claimDate = claimDate;
    this.hunter = hunter;
    this.target = target;
    this.imageUrl = imageUrl;
  }
}
