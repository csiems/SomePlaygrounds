# SomePlaygrounds
A playground library that implements business logic for a playground management module

## Prerequisites

You will need the following things properly installed on your computer.
* [Git]
* [Java 1.8]

## Installation

* Import the latest JAR from 'Releases' tab: [https://github.com/csiems/SomePlaygrounds/releases] and add as a dependency to your project.

## Using the SomePlaygrounds library

```Java
// Creating a Playground with play sites
Playground playground = new Playground();

DoubleSwingSite swings = new DoubleSwingSite(4); // int is number of swings (each holds two kids)
BallPitSite ballpit = new BallPitSite(10); // int is capacity
SlideSite slides = new SlideSite(2); // int is number of slides

playground.add(swings);
playground.add(ballpit);
playground.add(slides);

// Look up site onsite numbers and onqueue numbers
swings.getKidsOnSite();
swings.getKidsOnQueue();

// Creating kids to add to and remove from sites
Kid rasmus = new Kid("Rasmus", 5,
        new Ticket(Ticket.Type.GENERAL, 100000000L), true);
Kid hanna = new Kid("Hanna", 4,
        new Ticket(Ticket.Type.GENERAL, 100000001L), false);
Kid helgi = new Kid("Helgi", 3,
        new Ticket(Ticket.Type.VIP, 100000002L), true);

swings.addKid(rasmus);
ballpit.addKid(hanna);
slides.addKid(helgi);
swings.removeKid(rasmus);

// Get kid entry and exit stats
rasmus.getCurrentVisit().getTimeEntered();
rasmus.getCurrentVisit().getTimeExited();
rasmus.getCurrentVisit().getVisitLength();

// Access history on what play sites have been played
rasmus.getVisits();

// Get list of all visitors
playground.getVisitorsAsList();

// Get list of visitors within specific range
playground.getVisitorsAsList(start, end);

// total visitor count within a specific range
playground.getVisitorsAsList(start, end).size();

// Get current utilization statistics
playground.getCurrentUtilizationStat();

// Get utilization statistics within a specific range
getUtilizationSnapShot(start, end);

```


