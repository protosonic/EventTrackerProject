# Galactic Bounty Tracker API

**Track. Hunt. Claim. Repeat.**  
Welcome to **Galactic Bounty Tracker**, a powerful RESTful API where legendary space-ferring bounty hunters wrangle their targets and forge their reputations. 

Built with **Spring Boot**, **Hibernate/JPA**, and **MySQL**, this project is the backend engine for managing bounty assignments, tracking dangerous targets, and empowering hunters across the galaxy with a centralized data system.

May the hunt begin.

---

## Core Features

- Full CRUD for **Hunters**, **Bounties**, and **Targets**
- Assign targets to bounties
- View all bounties claimed by a specific hunter
- Ready-to-roll with simple configs and clean code structure

---

## Tech Stack

| Layer          | Tool                        |
|----------------|-----------------------------|
| Language       | Java 21                    |
| Framework      | Spring Boot (RESTful API)   |
| ORM            | JPA + Hibernate             |
| Database       | MySQL (or compatible)       |
| Build Tool     | Maven                       |

---
## Entity-Relationship diagram
![ER Diagram](/DB/trackdb.png)

---

## REST Endpoints

| Method | Endpoint                              | Description                             |
|--------|---------------------------------------|-----------------------------------------|
| GET    | `/api/bounties`                       | Get all bounties                        |
| GET    | `/api/bounties/{bountyId}`            | Get bounty by ID                        |
| POST   | `/api/bounties`                       | Create a new bounty                     |
| PUT    | `/api/bounties/{bountyId}`            | Update a bounty                         |
| DELETE | `/api/bounties/{bountyId}`            | Delete a bounty                         |
| GET    | `/api/hunters`                        | Get all hunters                         |
| GET    | `/api/hunters/{hunterId}`             | Get hunter by ID                        |
| POST   | `/api/hunters`                        | Create a new hunter                     |
| PUT    | `/api/hunters/{hunterId}`             | Update a hunter                         |
| DELETE | `/api/hunters/{hunterId}`             | Delete a hunter                         |
| GET    | `/api/targets`                        | Get all targets                         |
| GET    | `/api/targets/{targetId}`             | Get target by ID                        |
| POST   | `/api/targets`                        | Create a new target                     |
| PUT    | `/api/targets/{targetId}`             | Update a target                         |
| DELETE | `/api/targets/{targetId}`             | Delete a target                         |
| GET    | `/api/hunters/{hunterId}/bounties`    | Find bounties claimed by a hunter       |

---

## Data Model Overview

- One **Hunter** can claim many **Bounties**
- One **Target** can have many **Bounties** placed
- Each **Bounty** links to *one* Hunter and *one* Target

---


### 1. Clone this repo
```bash
git clone https://github.com/yourusername/bounty-tracker.git
cd bounty-tracker
