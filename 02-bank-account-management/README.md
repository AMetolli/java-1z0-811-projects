# Banking Account Management System (Java CLI)

An object-oriented banking system built in pure Java featuring custom regex-based JSON persistence, PIN authentication, and an automated build script.

---

## Features

* **Account Management:** Create bank accounts with custom account numbers, account holders, and initial PINs.
* **Security:** PIN authentication required for account access and transactions.
* **Transactions:** Deposits, withdrawals (with overdraft protection), and transfers between accounts.
* **File Persistence:** Saves and loads state via `data/accounts.json` without external third-party dependencies.
* **Build Automation:** One-click automated clean build and execution via a Windows Batch script (`start.bat`).

---

## Architecture & Diagrams

The application follows a clean layered architecture (Separation of Concerns) and Domain-Driven Design principles ("Core First").

### 1. Class Diagram (Static Structure)

```mermaid
classDiagram
    class BankApp {
        -Scanner scanner
        -BankService bankService
        +main(args: String[]) void
        +start() void
    }

    class BankService {
        -AccountRepository repository
        -BankAccount loggedInAccount
        +authenticate(accNum, pin) boolean
        +deposit(amount) void
        +withdraw(amount) boolean
        +transfer(targetAccNum, amount) boolean
        +createAccount(accNum, holder, pin) BankAccount
    }

    class AccountRepository {
        -String filePath
        +findAll() List~BankAccount~
        +saveAll(accounts) void
    }

    class AccountJsonParser {
        +toJson(accounts) String
        +fromJson(json) List~BankAccount~
    }

    class BankAccount {
        -String accountNumber
        -String accountHolder
        -double balance
        -String pin
        +deposit(amount) void
        +withdraw(amount) boolean
        +validatePin(pin) boolean
    }

    BankApp --> BankService : uses
    BankService --> AccountRepository : uses
    BankService --> BankAccount : manages
    AccountRepository --> AccountJsonParser : uses (static)
    AccountRepository --> BankAccount : loads / saves
```

---

### 2. Sequence Diagram (Dynamic Transfer Flow)

```mermaid
sequenceDiagram
    autonumber
    actor User as User / CLI
    participant App as BankApp
    participant Service as BankService
    participant Repo as AccountRepository
    participant Parser as AccountJsonParser
    participant File as accounts.json

    User->>App: Input transfer details
    App->>Service: transfer(targetAccNum, amount)
    
    Note over Service: Verify authentication & balance

    Service->>Repo: findAll()
    Repo->>File: Read JSON string
    File-->>Repo: Raw JSON
    Repo->>Parser: fromJson(json)
    Parser-->>Repo: List BankAccount
    Repo-->>Service: List BankAccount

    Note over Service: Deduct from source & add to target

    Service->>Repo: saveAll(accounts)
    Repo->>Parser: toJson(accounts)
    Parser-->>Repo: Formatted JSON String
    Repo->>File: Write to file
    
    Repo-->>Service: Success
    Service-->>App: return true
    App-->>User: Show success message
```
