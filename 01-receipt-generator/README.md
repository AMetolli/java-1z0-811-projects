# Receipt Generator (Java CLI)

A command-line receipt and tax calculator built in pure Java. Handles itemized shopping carts, tax categorization, and formatted terminal output.

## Features

* **Product Catalog:** Predefined store products with pricing and tax categories.
* **Cart Management:** Add multiple products with custom quantities.
* **Precise Tax Calculations:** Automatic breakdown of reduced (7%) and standard (19%) VAT rates.
* **Formatted Output:** Clean terminal-rendered receipt printing.

## Architecture & Diagrams

### 1. Class Diagram (Static Structure)

```mermaid
classDiagram
    class ReceiptApp {
        -Scanner scanner
        -ReceiptService service
        +main(args: String[]) void
    }

    class ReceiptService {
        -List~ReceiptItem~ items
        +addItem(Product, int) void
        +calculateSubtotal() double
        +calculateVat() double
        +calculateTotal() double
        +generateReceiptString() String
    }

    class ReceiptItem {
        -Product product
        -int quantity
        +getSubtotal() double
    }

    class Product {
        -String name
        -double price
        -TaxCategory taxCategory
    }

    class TaxCategory {
        <<enumeration>>
        REDUCED (7%)
        STANDARD (19%)
    }

    class ProductCatalog {
        +getProduct(String) Product
        +printCatalog() void
    }

    ReceiptApp --> ReceiptService : uses
    ReceiptService --> ReceiptItem : manages
    ReceiptItem --> Product : references
    Product --> TaxCategory : has
    ReceiptService --> ProductCatalog : queries
```

### 2. Sequence Diagram (Item Addition & Calculation Flow)

```mermaid
sequenceDiagram
    autonumber
    actor User as User / CLI
    participant App as ReceiptApp
    participant Catalog as ProductCatalog
    participant Service as ReceiptService
    participant Item as ReceiptItem

    User->>App: Select product & quantity
    App->>Catalog: getProduct(code)
    Catalog-->>App: Product instance
    App->>Service: addItem(product, quantity)
    Service->>Item: new ReceiptItem(product, quantity)
    
    Note over Service: User requests final receipt checkout
    
    Service->>Service: calculateSubtotal() & calculateVat()
    Service-->>App: Formatted receipt text
    App-->>User: Print receipt to console
```

## Setup & Execution

### Prerequisites
- JDK 17 or higher

### Build & Run Commands
Make sure you are in the project root directory, then compile and run:

```bash
# Step 1: Compile all Java source files into the binary directory
javac -d bin src/receipt/*.java

# Step 2: Run the application entry point
java -cp bin receipt.ReceiptApp
