# Vending Machine
This project is supposed to emulate a vending machine. In the main screen you can see the current stock level and the currently stored cash.
There is a button to restock (which takes you to a screen that simply asks how much stock you want to add), and there's also
a button to purchase an item (which takes to a screen to select the currency denominations you want to insert and allows you 
to finally to a purchase).

This is by no means complete, and just serves to demonstrate a few use cases for unit and instrumentation testing, along with
a simple way to achieve dependency injection in android.

## The Main Project

 The project is separated in 4 packages, based on MVC. There is a Model package which contains all the classes having to do with
 the model (mainly the vending machine interface and an implementation for that). There's also a Controller package
 for controller classes (in my mind these are Android Activities and Fragments), and a View package for all view
 related classes (the toaster which displays toasts is a good example). Finally there's the module package which holds modules.
 Each module is essentially a static factory for each class that we want to inject somewhere. Of course, instead of having a factory,
 this can also be back by a static instance of an item (essentially a Singleton). Modules are a simple way to achieve DI
 without using a framework for that.

## Unit Tests

For unit tests, JUnit4 is used, along with Robolectric, Hamcrest and Fest for matching, and Mockito for mocking. I tried to be as thorough
as possible in order to demonstrate how far we can go with unit testing and how much can actually be covered.
 
## Instrumentation Tests
For instrumentation tests, only Espresso is used and that's sufficient for this project. Again, I tried to be really thorough
here, trying to cover all possible use cases. You'll even notice that there's some overlap between unit and 
instrumentation tests. In reality this should be avoided in real projects, and decided after discussion what needs to be
tested on each level. Either way, this is just a demonstration and it's better to have demonstrate more :)