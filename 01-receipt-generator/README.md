# Receipt Generator

A Java CLI application that calculates VAT, itemized totals, and prints formatted receipts in the terminal.

## Overview & Goals

This project demonstrates Object-Oriented Design (OOD) principles in Java by building a modular command-line receipt generator. The main objective is to enforce a strict separation of concerns between domain data, business logic, and terminal presentation.

## Architecture & Design Decisions

### 1. Data-Driven Modeling (`TaxCategory` & `Product`)

* **Decision:** Encapsulate tax logic inside a dedicated `TaxCategory` enum rather than using hardcoded floating-point constants.
* **Rationale:** Tax rules (e.g., standard vs. reduced rate) represent fixed domain concepts. Enums provide type safety, prevent invalid inputs, and make tax calculations extensible.

### 2. Separation of Concerns

* **`Product`**: Pure domain entity representing item state (Name, Price, Tax Category).
* **`ReceiptService`**: Encapsulates business logic (calculating subtotals, VAT amounts, and totals).
* **`ReceiptApp`**: CLI entry point responsible purely for user input and console rendering.

## Concepts & Exam Topics Practiced (Java Foundations)

* **Type Safety & Enums:** Working with fixed value sets and constants.
* **Encapsulation:** Private field scope with getter methods.
* **Arithmetic & Primitive Types:** Precise calculation handling with Java primitive types.

## Project Structure

```
01-receipt-generator/
├── README.md
└── src/
    └── receipt/
        ├── TaxCategory.java
        ├── Product.java
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