# SomePlaygrounds
A playground library that implements business logic for a playground management module

## Prerequisites

You will need the following things properly installed on your computer.
* [Git]
* [Java 1.8]

## Installation

* Import the latest JAR from 'Releases' tab: [https://github.com/csiems/SomePlaygrounds/releases]
(https://github.com/csiems/SomePlaygrounds/releases) and add a dependency.

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



```


