# receipt-printer

## Requirements

- [x] Create a new Scala project, adding a testing library as a dependency
- [ ] Create a ReceiptPrinter class
- [ ] Used TDD to write the code of the receipt method
- [ ] Create private methods to extract some logic from the receipt method

## Sketch

Use TDD to create a new class called `Till` which takes `CafeDetail` instance at initalisation. It should have methods that:
- [ ] Show the menu
- [ ] Allow the user to add an item to their order or thrw an error if what they enter is not on the menu
- [ ] Finalise the order and print the statement (by calling on the receipt printer)