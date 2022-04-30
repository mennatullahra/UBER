# UBER

  this is a simulating project of an uber system using java programming language, applying software design
patterns like ( singleton - observer - decorator ).

- this program consists of 3 main actuators admin, passenger, and driver. 
    there's only one admin ( user can't create another because of the singleton pattern ) who can verify drivers,
    suspend them, suspend passengers, and apply discounts on specific areas.

    user can create passenger accounts as many as he wants. once the passenger's account is logged in, he can request
    a ride, and rate a driver.

    user can create driver accounts as many as he wants. once the driver account is logged in, he can identify some
    areas as his favorites, he receives trips to these areas, then he would be able to offer a price to these areas.

All events have been recorded in EVENT CLASS with data like date, passenger, driver, source, and destination.
