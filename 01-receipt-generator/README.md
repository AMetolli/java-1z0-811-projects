# Receipt Generator

A Java CLI application that calculates VAT, itemized totals, and prints formatted receipts in the terminal.

## Overview & Goals

This project demonstrates Object-Oriented Design (OOD) principles in Java by building a modular command-line receipt generator. The main objective is to enforce a strict separation of concerns between domain data, business logic, and terminal presentation.

## Architecture & Design Decisions

### 1. Data-Driven Modeling (`TaxCategory` & `Product`)
* **`TaxCategory` (Enum):** Encapsulates tax logic and rates rather than using hardcoded floating-point constants. Provides type safety, prevents invalid inputs, and makes tax calculations extensible.
* **`Product` (Class):** Pure domain entity representing item state with private fields and getter methods (`name`, `price`, `taxCategory`).

### 2. Data Access & Catalog (`ProductCatalog`)
* **`ProductCatalog` (Class):** Acts as a data repository layer (simulating a database or persistent storage in-memory). It manages and provides access to the available store products.

### 3. Separation of Concerns
* **`ReceiptService`:** Encapsulates business logic (calculating subtotals, VAT amounts, and totals).
* **`ReceiptApp`:** CLI entry point responsible purely for user input and console rendering.

### 4. Product Catalog / Sample Data
The application provides an initial set of hardcoded sample products via the catalog:
* **Apple:** 0.99 € (`REDUCED`)
* **Milk:** 1.49 € (`REDUCED`)
* **Cola:** 2.49 € (`STANDARD`)

## Concepts & Exam Topics Practiced (Java Foundations)

* **Type Safety & Enums:** Working with fixed value sets and constants.
* **Encapsulation:** Private field scope with getter methods.
* **Arithmetic & Primitive Types:** Precise calculation handling with Java primitive types.

## Project Structure

```text
01-receipt-generator/
├── README.md
└── src/
    └── receipt/
        ├── TaxCategory.java
        ├── Product.java
        ├── ProductCatalog.java
        ├── ReceiptService.java
        └── ReceiptApp.java
```

## Setup & Execution

### Prerequisites

* JDK 17 or higher

### Build & Run Commands

1. Compile all Java source files into the binary directory:

   
```bash
   javac -d bin src/receipt/*.java
   
```

2. Run the application entry point:

   
```bash
   java -cp bin receipt.ReceiptApp
   
```

## Development Workflow
* This project adheres to Conventional Commits to track architectural milestones clearly.