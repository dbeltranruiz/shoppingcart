# shoppingcart

This projects aims to solve the following exams task

Requirements:
·         Design and implement supermarket checkout component with readable API that calculates the total price of a number of items.
·         Checkout mechanism can scan items and return actual price (is stateful)
·         Our goods are priced individually. In addition, some items are multi-priced: "buy n of them, and they’ll cost you y cents"
 
  Item   Unit      Special
              Price     Price
  --------------------------
    A        40       3 for 70
    B        10       2 for 15
    C        30
    D        25
    
    Assumptions in the solution:
    . Items are objects which have an id an additional fields related with them. 
    . PricePolicy define the price for a given item starting from a minimal amount of them. The normal price is also a pricing policy which requires a minimum of one item.
    . Items implements equals and hashCode in order to became keys in maps and sets
    . PricePolicies are kept in a sorted set in oder to efficiently find them depending on the particular item and its amount. For that purpose PricePolicy implements the Comparable interface.
