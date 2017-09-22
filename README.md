# shoppingcart

This projects aims to solve the following exams task

## Requirements
- Design and implement supermarket checkout component with readable API that calculates the total price of a number of items.
- Checkout mechanism can scan items and return actual price (is stateful)
- Our goods are priced individually. In addition, some items are multi-priced: "buy n of them, and they’ll cost you y cents"

|Item | Unit Price | Special Price |
|-----|------------|---------------|
|A|40|3 for 70|
|B|10|2 for 15|
|C|30||
|D|25||

## Assumptions in the solution
- Items are objects which have an id an additional fields related with them. 
- PricePolicy define the price for a given item for the defined amount of them. The normal price is also a pricing policy which requires a minimum of one item.
- Items implements equals and hashCode in order to became keys in maps and sets
- PricePolicies are kept in a sorted set in oder to efficiently find them depending on the particular item and its amount. For that purpose PricePolicy implements the Comparable interface.
- MultiplePolicies are supported for a given item. They are used in descendant order by applying as much offers for the the biggest disscount as possible and continuing with the rest of offers if there still any item remaining out of the previous offer.
- The ShoppingCartService interface provide a simple API to manipulate the Cart and to calculate Total Price and ShoppingSummary which can be used as model to generate a view for the customer.
- Exceptions are not used by intend in the API to not complicate if usage and to keep it lightweight. Instead returning values should be enough to determine the result of a given operation.
- The only exception used by the API is the UndefinedNormalPriceException which is a runtime exception. The reason for it is because it can occur only in case the PricingPolicies have not properly defined which is not considered a normal exception case to which the user of the API should normally react.
