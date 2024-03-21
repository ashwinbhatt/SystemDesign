The objective is to design and implement a system that not only performs various sorting operations on collections but also logs the steps, decisions, and outcomes of these operations. The challenge emphasizes adherence to low-level design principles, focusing on the SOLID principles and promoting clean, modular, and extensible code architecture. This system should be capable of sorting collections of integers through different algorithms and logging the process and results in a structured manner.
Functional Requirements:

    Sorting Algorithms: Implement at least three distinct sorting algorithms, such as Bubble Sort, Selection Sort, and Quick Sort. Each algorithm should adhere to a common interface, allowing for interchangeability and flexibility in sorting strategy.

    Logging Mechanism: Develop a logging mechanism that can log messages with varying levels of importance (INFO, WARNING, ERROR) and to multiple outputs (e.g., console, file). The logging format should be standardized and include timestamps.

    Sorting Logger: Integrate the logging mechanism with the sorting algorithms to log key events during the sorting process, such as initialization, comparisons, swaps, and completion. The logs should provide insights into the algorithm's behavior and performance.

Technical Requirements:

    Language and Frameworks: You are free to choose an object-oriented programming language like Java, Python, or C#. If using Java, consider interfaces and abstract classes; if Python, duck typing and abstract base classes can be utilized.
    SOLID Principles: Ensure your design strictly follows SOLID principles for OOP design.
    Design Patterns: Apply appropriate design patterns, such as Strategy for sorting algorithms and Factory or Singleton for the logging mechanism.
    Error Handling: Implement robust error handling to manage invalid inputs, unsupported operations, or failures during sorting or logging.

Example Usage:

    A user selects the Quick Sort algorithm and starts the sorting process on an array of integers. They choose to log the process to both the console and a file with INFO level.
    The system logs the start of the sorting process, each swap made, and the final sorted array, along with the total time taken to sort.
