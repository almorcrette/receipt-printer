# receipt-printer

## Requirements

- [x] Create a new Scala project, adding a testing library as a dependency
- [x] Create a ReceiptPrinter class
- [x] Used TDD to write the code of the receipt method
- [x] Create private methods to extract some logic from the receipt method

## Sketch

Use TDD to create a new class called `Till` which takes `CafeDetail` instance at initialisation. It should have methods that:
- [x] Show the menu
- [x] Allow the user to add an item to their order or throw an error if what they enter is not on the menu
- [x] Finalise the order and print the statement (by calling on the receipt printer)