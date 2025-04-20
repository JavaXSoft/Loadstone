# Loadstone: Minecraft Server Stress Testing Tool

## Project Description

Loadstone is a Java-based tool designed to stress test Minecraft servers. It simulates multiple concurrent player connections to evaluate server performance under heavy load. This tool helps server administrators identify potential bottlenecks, assess server stability, and optimize performance.

## Features (Planned/In Development)

* **Simulated Players (Bots):**
    * Ability to simulate a configurable number of concurrent player connections.
    * Basic connection establishment.
    * (Planned) Support for simulating various player actions (e.g., movement, block breaking, chatting).
* **Configuration:**
    * Server address and port configuration.
    * Number of simulated players configuration.
    * (Planned) Bot behavior configuration.
* **Basic Reporting:**
    * Console output of connection status.
    * (Planned) Server performance metrics (TPS, CPU usage, etc.).

## Current Status

This project is currently in early development. The basic framework for establishing multiple TCP connections is in place. The next steps involve implementing the Minecraft handshake and login protocol.

## Prerequisites

* Java Development Kit (JDK) 8 or higher
* Maven

## Getting Started

1.  **Clone the repository:**
    ```bash
    git clone <repository_url>  # Replace <repository_url> with the actual URL
    cd Loadstone
    ```

2.  **Configure the server:**
    * Create a `config.properties` file in the project root directory.
    * Add the following content and modify it as needed:
        ```properties
        server.address=localhost  # Replace with your server address
        server.port=25565        # Replace with your server port
        bots.count=10           # Number of simulated players
        ```

3.  **Build the project:**
    ```bash
    mvn clean compile
    ```

4.  **Run the tool:**
    ```bash
    mvn exec:java -Dexec.mainClass="org.loadstone.MinecraftStressTester"
    ```

## Usage

The tool will attempt to establish the specified number of connections to the Minecraft server.  Currently, it only establishes basic TCP connections without completing the full Minecraft protocol handshake and login.

## Contributing

Contributions are welcome!  If you'd like to contribute to this project, please fork the repository and submit a pull request.  Please follow the project's coding style and conventions.


##  Important Notes

* This tool is intended for testing purposes only.  Please use it responsibly and obtain permission before testing any server you do not own.
* Be mindful of the resources on your testing machine and the target server.  Simulating a very large number of players can consume significant resources.
