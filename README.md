# WatchThisSpaceship

## To run
I used Android Studio Bumblebee 2021.1.1 Patch 1, but the newer patches should be fine too!

## Some cool things to check out
* MVI architecture + State Reducer used in ViewModels
* Compose UI
* Use of Use Cases/Interactors to drive the business logic
* Unit tests for the UseCases + repo (would ideally write a few more)


## Things I ran out of time to implement
* Rocket type and name requires an additional api call (that you pass RocketId to) that I didn't get around to implementing.
This would be fairly easy to add to the repo and have SpaceXLaunchesUseCase call. Because I am using Flows, I could have SpaceXLaunchesUseCase
emit the launches data (without rocket info) to the viewlayer and then emit the updated laches data (with rocket info) once that second api call is done. 
That way the user doesn't have to wait for two api calls to finish before they can view data on the screen.

* Clicking on a launch item only opens the wikipidea page (if it has one) as opposed to a dialog with different link options.



