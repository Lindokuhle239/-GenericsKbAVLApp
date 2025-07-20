# GenericsKbAVLApp - AVL Tree Knowledge Base Implementation

![Java](https://img.shields.io/badge/Java-17-blue)
![License](https://img.shields.io/badge/License-MIT-green)

An AVL Tree implementation for storing and retrieving knowledge base entries with terms, sentences, and confidence scores.

## Table of Contents
- [Features](#features)
- [Step-by-Step Guide](#step-by-step-guide)
- [Project Structure](#project-structure)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [File Formats](#file-formats)
- [Performance Testing](#performance-testing)
- [Documentation](#documentation)

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

## Step-by-Step Guide

1. Loading a Knowledge Base File
- Run the application:
```bash
make run
```

- From the main menu, select **option 1**
- Enter the path to your knowledge base file when prompted:
```
Enter file name: GenericsKB.txt
```
- The system will confirm successful loading:
```
Knowledge base loaded successfully.
```

2. Searching for a Term

- From the main menu, select **option 4**
- Enter the term you want to search for:
```
Enter term to search: mineral water
```
- The system will display results:
```
Term "mineral water" found. Sentence is "Mineral water is carbonated water.", and the confidence score is 1.0
```

3. Running Performance Experiments

- From the main menu, select **option 7**
- Enter the subset size (e.g., 5000):
```
Enter size of your subset: 5000
```
- The system will display operation counts:
```
Number of Insert Operations: 67236
Number of Search Operations: 72236
```

4. Processing Query Files

- Prepare a query file (e.g., `queries.txt`) with one term per line
- From the main menu, select **option 5**
-  Enter the query file path:
```
Enter file name for queries: queries.txt
```
- Results will be saved to `QueryOutput.txt`

5. Viewing Tree Structure

- From the main menu, select **option 6**
- The system will display all nodes in level order:
```
Term: tree, Sentence: Trees remove carbon dioxide..., Confidence Score: 1.0
Term: water, Sentence: Water is essential for..., Confidence Score: 0.95
...
```

6. Adding a New Entry Manually

- From the main menu, select **option 2**
- Enter the term:
```
Enter term: photosynthesis
```
- Enter the sentence:
```
Enter statement: Photosynthesis converts light energy to chemical energy.
```
- Enter the confidence score:
```
Enter confidence score: 0.9
```
- The system will confirm:
```
Statement for photosynthesis has been updated
```

## Project Structure
```
├── src/
│   ├── Data.java
│   ├── GenericsKbAVLApp.java
│   ├── GenericsKbAVLAppTester.java
│   └── Node.java
├── bin/
├── doc/
├── Makefile
└── README.md
```


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
```

term1
term2
...
```

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
