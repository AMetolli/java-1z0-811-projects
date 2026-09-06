# ☕ Java SE 8 Programmer I (1Z0-811) Projects

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)

Hands-on Java projects built to master core programming concepts and software engineering standards for the **Oracle 1Z0-811** certification.

## Scope & Approach
The projects in this repository go beyond the 1Z0-811 certification. While the exam focuses on core syntax and basic concepts, these projects are built to reflect real-world software engineering standards. The underlying philosophy is simple: intentionally practicing slightly above exam requirements creates the technical depth needed to handle edge cases reliably and pass real-world challenges with confidence.

- **Modular Architecture:** Clear separation of concerns by splitting data models, business logic, and the user interface.
- **Defensive Design:** Proper input validation and error handling to prevent unexpected crashes.
- **Clean Git Workflow:** Using atomic commits and conventional commit standards.

---

## 🚀 Projects Overview

| Project | Description | Core Concepts Covered | Status |
| :--- | :--- | :--- | :---: |
| **01 Receipt Generator** | CLI-based receipt & tax calculator | Primitive types, Operators, String formatting | ✅ Completed |
| **02 Bank Account** | Account management logic | OOP, Encapsulation, Methods | ⏳ Planned |
| **03 Task Manager** | Task tracking application | Control Flow, Arrays/ArrayLists | ⏳ Planned |

---

## 🛠️ How to Run
Make sure you are in the **root directory of the project** (where the `src` folder is located), then run:

```bash
# Step 1: Compile the source files
javac -d bin src/<package_name>/*.java

# Step 2: Run the compiled application
java -cp bin <package_name>.<MainClassName>
