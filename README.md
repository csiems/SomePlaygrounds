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

## License
Copyright (c) 2018 Christopher Siems

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
