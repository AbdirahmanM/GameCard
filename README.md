# GameCard
Android app that allows you to manage your video game collection. This app uses web-api calls to get the latest and most up to date data of all the games that exist. This app is made in kotlin for android API 16+

### Features
  - Save already released games to your collection (MyCollectionFragment)
  - Delete games from your collection (GameDetailActivity)
  - Save upcoming games to your wishlist (MyWishlistFragment)
  - Delete games from your wishlist (GameDetailActivity)
  - View the details of a game (GameDetailActivity)
  - View all released games (ExploreGamesFragment)
  - View all upcoming games (UpcomingGamesFragment)
  - DrawerNavigation with fragmentmanager to navigate to the different fragments  (MainActivity)

### Persistence
Data is saved in a local database using Room

### Testing
  - There are unit tests for all the functions in the Util file (Junit4)
  - Automated UI tests with Espresso
