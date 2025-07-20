# GenericsKbAVLApp - AVL Tree Knowledge Base Implementation

![Java](https://img.shields.io/badge/Java-17-blue)
![License](https://img.shields.io/badge/License-MIT-green)

An AVL Tree implementation for storing and retrieving knowledge base entries with terms, sentences, and confidence scores.

## Table of Contents
- [Features](#features)
- [Project Structure](#project-structure)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [File Formats](#file-formats)
- [Performance Testing](#performance-testing)
- [Documentation](#documentation)
- [License](#license)

## Features
- **AVL Tree** implementation with automatic rebalancing
- **File operations**:
  - Load knowledge bases from text files
  - Process search queries from files
- **CRUD operations**:
  - Insert new entries
  - Delete existing entries
  - Search by term
- **Performance instrumentation**:
  - Counts comparison operations
  - Measures insert/search operations
- **User-friendly menu** interface
- **Automated testing** with randomized subsets

## Project Structure
├── src/
│ ├── Data.java
│ ├── GenericsKbAVLApp.java
│ ├── GenericsKbAVLAppTester.java
│ └── Node.java
├── bin/
├── doc/
├── Makefile
└── README.md


## Requirements
- Java JDK 17+
- Make utility

## Installation
1. Clone the repository:
```bash
git clone https://github.com/yourusername/GenericsKbAVLApp.git
cd GenericsKbAVLApp
```

2. Compile the project:
```bash
make compile
```

## Usage
1. Run the application:
```bash
make run
```

2. Main Menu Options:
- Load knowledge base from file
- Insert new entry
- Delete entry
- Search by term
- Search query file
- Print tree (level order)
- Run experiments
- Exit

## File Formats
1. Knowledge Base File
Tab-separated values:
term<TAB>sentence<TAB>confidence_score

2. Query File
One term per line:

term1
term2
...

## Performance Testing
The system includes instrumentation to measure:
- Comparison operations count
- Insert/search operation counts
- Performance across dataset sizes (5-50,000 entries)

# Sample Output:

```bash
Number of Insert Operations: 5815
Number of Search Operations: 6315
```

## Documentation
Generate and view Javadocs:

```bash
make javadoc
```

Documentation will be available in the doc/ directory.